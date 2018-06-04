/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.Instant;
import java.util.HashMap;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author juand
 */
public class Principal implements ActionListener {

    JLabel ldimension, lmanzanas, lposicion, lconsejo,lPuntaje;
    JTextField tdiemnsion, tmanzanas, tposicionN;
    JFrame frame;
    JPanel pGlobal, pTablero, pOpciones;
    ButtonGroup group;
    JButton btEjecutar, btJugar, btJugada;
    private static Tablero mp;
    public static HashMap<Point, JLabel> Labels;
    public static Point caballoB;
    public static Point caballoN;
    File fichero;
    int manzanasB=0;
    int manzanasN=0;

    final public ImageIcon CABALLO_B = scalar_imagen("/Imagenes/caballoB.png");
    final public ImageIcon CABALLO_N = scalar_imagen("/Imagenes/caballoN.png");
    final public ImageIcon MANZANA = scalar_imagen("/Imagenes/apple.png");
    public int turno = 0;
    public int puntaje = 0;
    public int manzanas;
    public boolean falg = true;

    public static void main(String[] args) {
        new Principal().init();
    }

    public void init() {

        //Creacion de Panels
        frame = new JFrame();
        frame.setLayout(new MigLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setResizable(false);
        pGlobal = new JPanel(new MigLayout());
        frame.add(pGlobal, "span, width max(100%, 100%) ");

        //Creacion panel mapa
        pTablero = new JPanel();
        pTablero.setLayout(new GridLayout());
        pTablero.setVisible(true);
        pTablero.setPreferredSize(new Dimension(600, 600));
        pTablero.setBorder(BorderFactory.createTitledBorder("Tablero"));
        pGlobal.add(pTablero, "growx, growy");

        //Panel solucion
        pOpciones = new JPanel(new MigLayout());
        pOpciones.setVisible(true);
        btEjecutar = new JButton("Ejecutar");
        btJugar = new JButton("Iniciar Juego");
        btJugada = new JButton("Realizar jugada");
        ldimension = new JLabel("Dimension del tablero (solo un nuemro entero)");
        tdiemnsion = new JTextField(5);
        lmanzanas = new JLabel("Cantidad de Manzanas en el tablero (solo nuemros entero)");
        tmanzanas = new JTextField(5);
        lposicion = new JLabel("Posicion de la jugada (x,y)");
        lconsejo = new JLabel("Recuerda que X son las filas y Y las filas");
        lPuntaje = new JLabel("Maquina:   "+manzanasB+"     \nHumano:   "+manzanasN);
        tposicionN = new JTextField(10);
        pOpciones.add(ldimension, "wrap");
        pOpciones.add(tdiemnsion, "wrap");
        pOpciones.add(lmanzanas, "wrap");
        pOpciones.add(tmanzanas, "wrap");
        pOpciones.add(btEjecutar, "");
        pOpciones.add(btJugar, "wrap");
        pOpciones.add(lposicion, "wrap");
        pOpciones.add(tposicionN, "wrap");
        pOpciones.add(lconsejo, "");
        pOpciones.add(btJugada, "wrap");
        pOpciones.add(lPuntaje,"");
        pOpciones.setBorder(BorderFactory.createTitledBorder("opciones"));
        btEjecutar.addActionListener(this);
        btJugar.addActionListener(this);
        btJugar.setVisible(false);
        btJugada.addActionListener(this);
        btJugada.setVisible(false);
        pGlobal.add(pOpciones, "growx, growy, width max(40%, 40%)");
        lconsejo.setVisible(false);
        tposicionN.setVisible(false);
        lposicion.setVisible(false);
        lPuntaje.setVisible(false);

        frame.pack();
    }

    public ImageIcon scalar_imagen(String url) {
        ImageIcon imgIcoUV = new ImageIcon(this.getClass().getResource(url));
        Image image = imgIcoUV.getImage();
        Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String x = tdiemnsion.getText();
        String m = tmanzanas.getText();
        if (e.getSource() == btEjecutar) {
            //acomodar las coordenadas

            try {
                mp = new Tablero(Integer.parseInt(x) + 1, Integer.parseInt(x) + 1, Integer.parseInt(m));
                construir_mapa(mp.getTablero());
                Instant inicio = Instant.now();
                btJugar.setVisible(true);
                Instant fin = Instant.now();

            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, error);

            }

        } else if (e.getSource() == btJugar) {
            turno++;
            ProyectoIA2 aux = new ProyectoIA2();
            int[] sol = aux.init(mp.getTablero(),manzanasB, manzanasN);

            manzanas = aux.contarM(mp.getTablero()).size();
            if (manzanas != 0) {

                if (aux.manzana(sol, mp.getTablero())) {
                    System.out.println("entro1");
                    
                    manzanasB++;
                    manzanas--;
                    puntaje++;
                    lPuntaje.setText("Maquina:   "+manzanasB+"     \nHumano:   "+manzanasN);
                    if (manzanas == 0) {
                        terminar();
                    }
                }
                mp.setPosB(sol[0], sol[1]);
                construir_mapa(mp.getTablero());
                lPuntaje.setVisible(true);
                btJugar.setVisible(false);
                btEjecutar.setVisible(false);
                ldimension.setVisible(false);
                lmanzanas.setVisible(false);
                tdiemnsion.setVisible(false);
                tmanzanas.setVisible(false);
                btJugada.setVisible(true);
                lconsejo.setVisible(true);
                lposicion.setVisible(true);
                tposicionN.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "inciel el juego con al menos una manzana");
            }
        } else if (e.getSource() == btJugada) {
            if (turno % 2 != 0) {
                String mov = tposicionN.getText();
                boolean flag = true;
                int pos_x = 0;
                int pos_y = 0;
                for (int i = 0; i < mov.length(); i++) {
                    if (mov.charAt(i) == '(') {
                        String hlp = new String();
                        for (int j = +1; j < mov.length(); j++) {
                            if (mov.charAt(j) == ',') {
                                flag = false;
                            }
                            if (flag) {
                                hlp = hlp + String.valueOf(mov.charAt(j));
                            }
                        }
                        pos_x = Integer.parseInt(hlp);

                    }
                    flag = true;
                    if (mov.charAt(i) == ',') {
                        String hlp = new String();
                        for (int j = i + 1; j < mov.length(); j++) {
                            if (mov.charAt(j) == ')') {
                                flag = false;
                            }
                            if (flag) {
                                hlp = hlp + String.valueOf(mov.charAt(j));
                            }
                        }
                        pos_y = Integer.parseInt(hlp);
                    }
                }

                int[] auxiliar = mp.posN();
                int[] auxi = {pos_x, pos_y};
                if (mp.movimientos(auxiliar[0], auxiliar[1], auxi)) {
                    ProyectoIA2 aux = new ProyectoIA2();

                    if (aux.manzana(auxi, mp.getTablero())) {
                        System.out.println("entro");
                        manzanasN++;
                        manzanas--;
                        puntaje--;
                        lPuntaje.setText("Maquina:   "+manzanasB+"     \nHumano:   "+manzanasN);
                    }
                    mp.setPosN(pos_x, pos_y);
                    construir_mapa(mp.getTablero());
                    if (manzanas != 0) {
                        jugarB(mp);
                    } else {
                        terminar();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "selecciona un movimietno valido");
                }

            }
        }
    }

    public void terminar() {
        if (puntaje == 0) {
            JOptionPane.showMessageDialog(null, "Empate");
        } else if (puntaje < 0) {
            JOptionPane.showMessageDialog(null, "Gano el jugador");
        } else {
            JOptionPane.showMessageDialog(null, "Gano la maquina");
        }
        
        pOpciones.setVisible(false);
    }

    public void jugarB(Tablero tab) {
        turno++;
        ProyectoIA2 aux = new ProyectoIA2();
        int[] sol = aux.init(mp.getTablero(),manzanasB,manzanasN);
        if (aux.manzana(sol, mp.getTablero())) {
            System.out.println("entroB");
            manzanasB++;
            manzanas--;
            puntaje++;
            lPuntaje.setText("Maquina:   "+manzanasB+"     \nHumano:   "+manzanasN);
        }
        mp.setPosB(sol[0], sol[1]);
        construir_mapa(mp.getTablero());
        turno++;
        if (manzanas == 0) {
            terminar();
        }
    }

    public void construir_mapa(int[][] mapa) {

        pTablero.removeAll();
        pTablero.updateUI();
        pTablero.setLayout(new GridLayout(mapa.length, mapa[0].length));
        int colum = 0;
        int row = 0;
        JLabel lb;
        Labels = new HashMap<>();
        for (int i = 0; i < mapa.length; i++) {//y
            for (int j = 0; j < mapa[0].length; j++) {//x
                lb = new JLabel();

                lb.setOpaque(true);
                switch (mapa[i][j]) {
                    case 5:
                        lb.setText("");
                        if (i == 0 && j != 0) {
                            colum++;
                            lb.setText(String.valueOf(colum));
                        } else if (j == 0 && i != 0) {
                            row++;
                            lb.setText(String.valueOf(row));
                        }
                        lb.setBackground(Color.GRAY);
                        break;
                    case Tablero.BLANCO:

                        lb.setBackground(Color.WHITE);
                        break;
                    case Tablero.NEGRO:

                        lb.setBackground(Color.BLACK);
                        break;
                    case Tablero.CABALLOB:
                        if (i % 2 == 0) {
                            if (j % 2 == 0) {
                                lb.setBackground(Color.WHITE);
                            } else {
                                lb.setBackground(Color.BLACK);
                            }
                        } else {
                            if (j % 2 == 0) {
                                lb.setBackground(Color.BLACK);
                            } else {
                                lb.setBackground(Color.WHITE);
                            }
                        }
                        lb.setIcon(CABALLO_B);
                        caballoB = new Point(i, j);
                        break;
                    case Tablero.CABALLON:
                        if (i % 2 == 0) {
                            if (j % 2 == 0) {
                                lb.setBackground(Color.WHITE);
                            } else {
                                lb.setBackground(Color.BLACK);
                            }
                        } else {
                            if (j % 2 == 0) {
                                lb.setBackground(Color.BLACK);
                            } else {
                                lb.setBackground(Color.WHITE);
                            }
                        }
                        lb.setIcon(CABALLO_N);
                        caballoN = new Point(i, j);
                        break;
                    case Tablero.MANZANA:
                        if (i % 2 == 0) {
                            if (j % 2 == 0) {
                                lb.setBackground(Color.WHITE);
                            } else {
                                lb.setBackground(Color.BLACK);
                            }
                        } else {
                            if (j % 2 == 0) {
                                lb.setBackground(Color.BLACK);
                            } else {
                                lb.setBackground(Color.WHITE);
                            }
                        }
                        lb.setIcon(MANZANA);
                        break;

                }
                Dimension d = new Dimension(pTablero.getWidth() / mapa.length, pTablero.getWidth() / mapa.length);
                lb.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                lb.setHorizontalAlignment(JLabel.CENTER);
                lb.setMaximumSize(d);
                Labels.put(new Point(i, j), lb);
                pTablero.add(lb);
            }
        }
        pTablero.updateUI();
    }

}
