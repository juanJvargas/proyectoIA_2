/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia2;

import java.util.Vector;

/**
 *
 * @author krypth
 */
public class ProyectoIA2 {

    /*
    *Tablero del juego
    *1:Espacio Blanco
    *0:Espacio Negro
    *2:Caballo Blanco
    *3:Caballo Negro
    *4:Manzanas
     */
    int id = 0;

    //Funcion para encontrar las coordenadas del caballo blanco en el tablero
    int[] EncontrarB(int[][] tablero) {
        int[] solucion = {0, 0};
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 2) {
                    solucion[0] = i;
                    solucion[1] = j;
                }
            }
        }
        return solucion;
    }

    //Funcion para encontrar las coordenadas del caballo negro en el tablero
    int[] EncontrarN(int[][] tablero) {
        int[] solucion = {0, 0};
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 3) {
                    solucion[0] = i;
                    solucion[1] = j;
                }
                
            }
        }
        return solucion;
    }

    //Funcion para contar la cantidad de manzanas en el tablero
    Vector<int[]> contarM(int[][] tablero) {
        Vector<int[]> solucion = new Vector();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 4) {
                    int[] aux = new int[2];
                    aux[0] = i;
                    aux[1] = j;
                    solucion.add(aux);
                }
            }
        }
        return solucion;
    }

    //funcion que retorna un bolleano si en la nueva posicion del nodo hay una manzana
    boolean manzana(int[] posicion, int[][] tablero) {
        boolean solucion = false;
        //coordenadas 
        int x = posicion[0];
        int y = posicion[1];
        if (tablero[x][y] == 4) {
            solucion = true;
        }
        return solucion;
    }

    //Funcion para hallar todos los posibles movimientos de un jugador
    Vector<int[]> movimientos(Nodo nodo, int[][] tablero) {
        Vector<int[]> solucion = new Vector();
        //posicion del jugador que vamos a obtener todas las posibles jugadas
        int[] jugador = {0, 0};
        int caballo = 0;
        //si la porfuncidad el par sabemos que es el turno de mover al caballo blanco
        if (nodo.getProfundidad() % 2 == 0) {
            jugador = EncontrarB(nodo.getTablero().clone());
            caballo = 3;
        } else {
            //Si no entonces es el turno de mover al caballo negro
            jugador = EncontrarN(nodo.getTablero().clone());
            caballo = 2;
        }
        //System.out.println("posicion inicial : (" + jugador[0] + "," + jugador[1] + ").");
        //Movimientos izquierda

        /*
        si el jugador no se encuentra en las casillas superiores o en las primeras dos 
        columnas del tablero entonces se puede mover de la siguiente manera
        2 izquierda 1 arriba
         */
        if (jugador[0] > 1 && jugador[1] > 2) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 1;
            movimiento[1] -= 2;
            if (tablero[movimiento[0]][movimiento[1]] != caballo) {
                if (tablero[movimiento[0]][movimiento[1]] != caballo) {
                    solucion.add(movimiento);
                }
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
            if (tablero[movimiento[0]][movimiento[1]] != caballo) {
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
            if (tablero[movimiento[0]][movimiento[1]] != caballo) {
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
            if (tablero[movimiento[0]][movimiento[1]] != caballo) {
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
            if (tablero[movimiento[0]][movimiento[1]] != caballo) {
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
            if (tablero[movimiento[0]][movimiento[1]] != caballo) {
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
            if (tablero[movimiento[0]][movimiento[1]] != caballo) {
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
            if (tablero[movimiento[0]][movimiento[1]] != caballo) {
                solucion.add(movimiento);
            }
        }
        return solucion;
    }
    
    Nodo mejor(Vector<Nodo> nodos) {
        Nodo aux = nodos.get(0);
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).getProfundidad() % 2 == 0) {
                if (nodos.get(i).getProductividad() < aux.getProductividad()) {
                    
                    aux = nodos.get(i);
                }
            } else {
                if (nodos.get(i).getProductividad() > aux.getProductividad()) {
                    aux = nodos.get(i);
                }
            }
            
        }
        
        return aux;
    }
    
    Nodo jugar2(Nodo nodo) {
        
        Vector<int[]> mov = movimientos(nodo, nodo.getTablero().clone());
        Vector<Nodo> nod = new Vector();
        Nodo nuevo = new Nodo();
        int[][] table = nodo.getTablero().clone();
        if (contarM(nodo.getTablero().clone()).size() != 0 && nodo.getProfundidad() < 8) {
            
            for (int i = 0; i < mov.size(); i++) {
                int[][] aux = new int[table.length][table[0].length];
                for (int j = 0; j < table.length; j++) {
                    for (int k = 0; k < table[0].length; k++) {
                        aux[j][k] = table[j][k];
                    }
                }
                id++;
                int id_padre = nodo.getId();
                int profundidad = nodo.getProfundidad() + 1;
                int manzanasB = nodo.getManzanasB();
                int manzanasN = nodo.getManzanasN();
                int productividad = 0;
                if (profundidad % 2 == 0) {
                    int[] move = EncontrarN(table);
                    aux[move[0]][move[1]] = 0;
                    if (manzana(mov.get(i), table)) {
                        manzanasN++;
                    }
                    move = mov.get(i);
                    aux[move[0]][move[1]] = 3;
                    
                } else {
                    int[] move = EncontrarB(table);
                    aux[move[0]][move[1]] = 0;
                    if (manzana(mov.get(i), table)) {
                        manzanasB++;
                    }
                    move = mov.get(i);
                    aux[move[0]][move[1]] = 2;
                }
                nuevo = new Nodo(id, id_padre, aux, productividad, profundidad, manzanasB, manzanasN + 1);
                nod.add(jugar2(nuevo));
            }
            
            nuevo = mejor(nod);
            nodo.setProductividad(nuevo.getProductividad());
            if (nodo.getProfundidad() % 2 == 0) {
                int[] move = EncontrarB(nuevo.getTablero());
                nodo.setMejorJugada(move);
            } else {
                int[] move = EncontrarN(nuevo.getTablero());
                nodo.setMejorJugada(move);
            }
            
        } else {
            nodo.setProductividad(nodo.getManzanasB() - nodo.getManzanasN());
        }
        
        return nodo;
    }
    
    void fin() {
        
    }
    
    void pintar(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }
    }
    
    int[] init(int[][] tablero, int manzanasB, int manzanasN) {
        
        Nodo nod = new Nodo(0, 0, tablero, 0, 0, manzanasB, manzanasN);
        ProyectoIA2 aux = new ProyectoIA2();
        nod = aux.jugar2(nod);
        int[] mejor = nod.getMejorJugada();
        return mejor;
        
    }
    
}
