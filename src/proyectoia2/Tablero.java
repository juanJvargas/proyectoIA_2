/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Stream;
import javax.swing.JOptionPane;

/**
 *
 * @author juand
 */
public class Tablero implements Cloneable {

    public static final int BLANCO = 0;
    public static final int NEGRO = 1;
    public static final int CABALLON = 3;
    public static final int CABALLOB = 2;
    public static final int MANZANA = 4;

    private static int[][] tablero;

    public Tablero(int x, int y, int m) {
        tablero = new int[x][y];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (i != 0 && j != 0) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            tablero[i][j] = 0;
                        } else {
                            tablero[i][j] = 1;
                        }
                    } else {
                        if (j % 2 == 0) {
                            tablero[i][j] = 1;
                        } else {
                            tablero[i][j] = 0;
                        }
                    }
                } else {
                    tablero[i][j] = 5;
                }
            }

        }

        while (m != 0) {
            int pos_x = (int) (Math.random() * x - 1) + 1;
            int pos_y = (int) (Math.random() * y - 1) + 1;
            if (tablero[pos_x][pos_y] != 4 && pos_x != 0 && pos_y != 0) {
                tablero[pos_x][pos_y] = 4;
                m--;
            }

        }
        int aux = 2;
        while (aux != 1) {
            int pos_x = (int) (Math.random() * x - 1) + 1;
            int pos_y = (int) (Math.random() * y - 1) + 1;
            if (tablero[pos_x][pos_y] != 4 && tablero[pos_x][pos_y] != 2) {
                tablero[pos_x][pos_y] = 3;
                aux--;
            }
        }
        while (aux != 0) {
            int pos_x = (int) (Math.random() * x - 1) + 1;
            int pos_y = (int) (Math.random() * y - 1) + 1;
            if (tablero[pos_x][pos_y] != 4 && tablero[pos_x][pos_y] != 3) {
                tablero[pos_x][pos_y] = 2;
                aux--;
            }
        }
    }

    public Tablero() {
    }

    public boolean manzana(int x, int y) {
        boolean respuesta = false;
        if (tablero[x][y] == MANZANA) {
            respuesta = true;
        }
        return respuesta;

    }

    public boolean movimientos(int x, int y, int[] mov) {
        Vector<int[]> solucion = new Vector();
        //posicion del jugador que vamos a obtener todas las posibles jugadas
        int[] jugador = {x, y};

        /*
        si el jugador no se encuentra en las casillas superiores o en las primeras dos 
        columnas del tablero entonces se puede mover de la siguiente manera
        2 izquierda 1 arriba
         */
        if (jugador[0] > 1 && jugador[1] > 2) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 1;
            movimiento[1] -= 2;
            if (tablero[movimiento[0]][movimiento[1]] != CABALLOB) {
                solucion.add(movimiento);
            }
        }
        /*
        si el jugador no se encuentra en las casillas inferiores o en las primeras dos 
        columnas del tablero entonces se puede mover de la siguiente manera
        2 izquierda 1 abajo
         */
        if (jugador[0] != tablero[0].length - 1 && jugador[1] > 2) {
            int[] movimiento = jugador.clone();
            movimiento[0] += 1;
            movimiento[1] -= 2;
            if (tablero[movimiento[0]][movimiento[1]] != CABALLOB) {
                solucion.add(movimiento);
            }
        }
        //Movimientos derecha

        /*
        si el jugador no se encuentra en las casillas superiores o en las ultimas dos 
        columnas del tablero entonces se puede mover de la siguiente manera
        2 derecha 1 arriba
         */
        if (jugador[0] > 1 && jugador[1] < tablero.length - 2) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 1;
            movimiento[1] += 2;
            if (tablero[movimiento[0]][movimiento[1]] != CABALLOB) {
                solucion.add(movimiento);
            }
        }
        /*
        si el jugador no se encuentra en las casillas inferiores o en las ultimas dos 
        columnas del tablero entonces se puede mover de la siguiente manera
        2 derecha 1 abajo
         */
        if (jugador[0] != tablero[0].length - 1 && jugador[1] < tablero.length - 2) {
            int[] movimiento = jugador.clone();
            movimiento[0] += 1;
            movimiento[1] += 2;
            if (tablero[movimiento[0]][movimiento[1]] != CABALLOB) {
                solucion.add(movimiento);
            }
        }
        //Movimientos arriba

        /*
        si el jugador no se encuentra en las primeras dos filas superiores o en las casillas mas 
        izquierdas del tablero entonces se puede mover de la siguiente manera
        2 arriba 1 izquierda
         */
        if (jugador[0] > 2 && jugador[1] > 1) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 2;
            movimiento[1] -= 1;
            if (tablero[movimiento[0]][movimiento[1]] != CABALLOB) {
                solucion.add(movimiento);
            }
        }
        /*
        si el jugador no se encuentra en las primeras dos filas superiores o en casillas mas 
        a la derecha del tablero entonces se puede mover de la siguiente manera
        2 arriba 1 derecha
         */
        if (jugador[0] > 2 && jugador[1] != tablero.length - 1) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 2;
            movimiento[1] += 1;
            if (tablero[movimiento[0]][movimiento[1]] != CABALLOB) {
                solucion.add(movimiento);
            }
        }
        //Movimientos abajo

        /*
        si el jugador no se encuentra en las ultimas dos filas inferiores o en las casillas mas 
        a la izquierdas del tablero entonces se puede mover de la siguiente manera
        2 abajo 1 izquierda
         */
        if (jugador[0] < tablero[0].length - 2 && jugador[1] > 1) {
            int[] movimiento = jugador.clone();
            movimiento[0] += 2;
            movimiento[1] -= 1;
            if (tablero[movimiento[0]][movimiento[1]] != CABALLOB) {
                solucion.add(movimiento);
            }
        }
        /*
        si el jugador no se encuentra en las ultimas dos filas inferiores o en casillas mas 
        a la derecha del tablero entonces se puede mover de la siguiente manera
        2 abajo 1 derecha
         */
        if (jugador[0] < tablero[0].length - 2 && jugador[1] != tablero.length - 1) {
            int[] movimiento = jugador.clone();
            movimiento[0] += 2;
            movimiento[1] += 1;
            if (tablero[movimiento[0]][movimiento[1]] != CABALLOB) {
                solucion.add(movimiento);
            }
        }
        boolean sol = false;
        for (int i = 0; i < solucion.size(); i++) {
            int[] aux = solucion.get(i);
            if (aux[0] == mov[0] && aux[1] == mov[1]) {
                sol = true;
            }

        }

        return sol;
    }

    public void setPosN(int x, int y) {
        for (int i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == CABALLON) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            tablero[i][j] = 0;
                        } else {
                            tablero[i][j] = 1;
                        }
                    } else {
                        if (j % 2 == 0) {
                            tablero[i][j] = 1;
                        } else {
                            tablero[i][j] = 0;
                        }
                    }
                }

            }
        }
        tablero[x][y] = CABALLON;
    }

    public int[] posN() {
        int[] solucion = new int[2];
        for (int i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == CABALLON) {
                    solucion[0] = i;
                    solucion[1] = j;
                    return solucion;
                }
            }
        }
        return solucion;
    }

    public void setPosB(int x, int y) {
        for (int i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == CABALLOB) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            tablero[i][j] = 0;
                        } else {
                            tablero[i][j] = 1;
                        }
                    } else {
                        if (j % 2 == 0) {
                            tablero[i][j] = 1;
                        } else {
                            tablero[i][j] = 0;
                        }
                    }
                }

            }
        }
        tablero[x][y] = CABALLOB;
    }

    public int getPos(int x, int y) {
        return tablero[x][y];
    }

    public int[][] getTablero() {
        return tablero;
    }

}
