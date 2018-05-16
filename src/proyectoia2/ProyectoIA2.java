/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia2;

import proyectoia2.*;
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
    int[][] tablero = {
        {2, 0, 1, 0, 1, 0},
        {0, 1, 0, 1, 0, 1},
        {1, 0, 4, 0, 1, 0},
        {0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0},
        {3, 1, 0, 1, 0, 1}};

    //Funcion para encontrar las coordenadas del caballo blanco en el tablero
    int[] EncontrarB() {
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
    int[] EncontrarN() {
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
    int contarM() {
        int solucion = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 4) {
                    solucion++;
                }
            }
        }
        return solucion;
    }

    //funcion encargada de añadir los hijos al arbol
    Vector<Nodo> añadir(Vector<Nodo> arbol, Nodo hijo) {
        Vector<Nodo> solucion = arbol;
        //si el vector esta vacio añado el nuevo hijo 
        if (solucion.isEmpty()) {
            solucion.add(hijo);
        } else {
            //trato de añadir los nodos con productividad al final para tener todos los nodos hojas
            //al final del arreglo para facilitar el proceso MINIMAX
            for (int i = solucion.size() - 1; i <= 0; i--) {
                if (hijo.getProductividad() > solucion.get(i).getProductividad()) {
                };
                solucion.add(i, hijo);
            }
        }
        return solucion;
    }
    //funcion que retorna un bolleano si en la nueva posicion del nodo hay una manzana
    boolean manzana(int[] posicion) {
        boolean solucion = false;
        //coordenadas 
        int x = posicion[0];
        int y = posicion[1];
        if (tablero[x][y]==4) {
            solucion = true;
        }
        return solucion;
    }
    //funcion encargada de observar si un nodo a pasado antes por esa misma posicion antes
    boolean estuvoAqui (Nodo nodo, Vector <Nodo> arbol, int turno){
        //for (int i = arbol.size()-1; i <= 0; i--) {
            
            
        //}
        return true;
    }
    //Funcion para hallar todos los posibles movimientos de un jugador
    Vector<int[]> movimientos(Nodo nodo) {
        Vector<int[]> solucion = new Vector();
        //posicion del jugador que vamos a obtener todas las posibles jugadas
        int[] jugador = {0, 0};
        //si la porfuncidad el par sabemos que es el turno de mover al caballo blanco
        if (nodo.getProfundidad() % 2 == 0) {
            jugador = nodo.getPoscionB().clone();
        } else {
            //Si no entonces es el turno de mover al caballo negro
            jugador = nodo.getPoscionN().clone();
        }
        System.out.println("posicion inicial : (" + jugador[0] + "," + jugador[1] + ").");
        //Movimientos izquierda

        /*
        si el jugador no se encuentra en las casillas superiores o en las primeras dos 
        columnas del tablero entonces se puede mover de la siguiente manera
        2 izquierda 1 arriba
         */
        if (jugador[0] != 0 && jugador[1] >= 2) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 1;
            movimiento[1] -= 2;
            solucion.add(movimiento);
        }
        /*
        si el jugador no se encuentra en las casillas inferiores o en las primeras dos 
        columnas del tablero entonces se puede mover de la siguiente manera
        2 izquierda 1 abajo
         */
        if (jugador[0] != tablero[0].length - 1 && jugador[1] >= 2) {
            int[] movimiento = jugador.clone();
            movimiento[0] += 1;
            movimiento[1] -= 2;
            solucion.add(movimiento);
        }
        //Movimientos derecha

        /*
        si el jugador no se encuentra en las casillas superiores o en las ultimas dos 
        columnas del tablero entonces se puede mover de la siguiente manera
        2 derecha 1 arriba
         */
        if (jugador[0] != 0 && jugador[1] < tablero.length - 2) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 1;
            movimiento[1] += 2;
            solucion.add(movimiento);
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
            solucion.add(movimiento);
        }
        //Movimientos arriba

        /*
        si el jugador no se encuentra en las primeras dos filas superiores o en las casillas mas 
        izquierdas del tablero entonces se puede mover de la siguiente manera
        2 arriba 1 izquierda
         */
        if (jugador[0] >= 2 && jugador[1] != 0) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 2;
            movimiento[1] -= 1;
            solucion.add(movimiento);
        }
        /*
        si el jugador no se encuentra en las primeras dos filas superiores o en casillas mas 
        a la derecha del tablero entonces se puede mover de la siguiente manera
        2 arriba 1 derecha
         */
        if (jugador[0] >= 2 && jugador[1] != tablero.length - 1) {
            int[] movimiento = jugador.clone();
            movimiento[0] -= 2;
            movimiento[1] += 1;
            solucion.add(movimiento);
        }
        //Movimientos abajo

        /*
        si el jugador no se encuentra en las ultimas dos filas inferiores o en las casillas mas 
        a la izquierdas del tablero entonces se puede mover de la siguiente manera
        2 abajo 1 izquierda
         */
        if (jugador[0] < tablero[0].length - 2 && jugador[1] != 0) {
            int[] movimiento = jugador.clone();
            movimiento[0] += 2;
            movimiento[1] -= 1;
            solucion.add(movimiento);
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
            solucion.add(movimiento);
        }
        return solucion;
    }

    public static void main(String[] args) {
        //variables necesarias para el arbol
        //todos los nodos que no sean hojas se inicializaran con estos valores de productividad 
        //si es un nodo MAX entonces se inicializara con Minfinito de lo contrario se inicializara con
        //infinito
        int infinito = 100000;
        //Menos infinito
        int Minfinito = -100000;

        ProyectoIA2 aux = new ProyectoIA2();
        //Encontrar posiciones iniciales de los caballos
        int[] caballoB = aux.EncontrarB().clone();
        int[] caballoN = aux.EncontrarN().clone();
        //encontrar la cantidad de manzanas totales en el tablero
        int manzanasT = aux.contarM();
        //identificaciond e los nodos;
        //se inicializa en 0 por qeu aun no hay
        int id = 0;
        //Creadmos el nodo raiz del arbol
        //como es el nodo raiz entonces su id y el id de su padre es el mismo
        Nodo nodo = new Nodo(id, id, caballoB, caballoN, manzanasT, Minfinito, 0);
        //Creamos los vectores necesarios para la expancion del arbol
        //Nododos que ya se expandieron
        Vector<Nodo> arbol = new Vector();
        //nodos pendientes por expandir
        Vector<Nodo> hijos = new Vector();

        //arreglo que lamacenas los posibles movimentos en cada turno
        Vector<int[]> movimientos = aux.movimientos(nodo);
        hijos.add(nodo);
        
        //do while encargado de contruir el arbol
        do {
            movimientos.clear();
            movimientos = aux.movimientos(hijos.get(0));
            //Como sabemos que vamos a expandir el primer nodo de hijos cada vez entonces lo
            //alamacenamos el nodo dentro de arbol
            aux.añadir(arbol, hijos.get(0));
            for (int i = 0; i < movimientos.size(); i++) {
                //aumentamos el numero de identificacion del nodo
                id++;
                //booleano para saber si en la nueva posicon del nodo hay una manzana
                boolean manzana =aux.manzana(movimientos.get(i));
                int turno;
                //averiguamos si los nuevos nodos seran nosos MAX o MIN
                
                
                
                
                
                ///Error de logica mirar como se crea el nodo en base a la profundidad 
                if((hijos.get(0).profundidad+1)%2 == 0){
                    //Si al profundidad es par quiere decir que son nodos MAX
                    turno = 0;
                }else{
                    //si es inpar entonces son nodos MIN
                    turno = 1;
                }
                //creamos el nodo basandonos en si se mueva a una manzana y si es un nodo MAX o MIN
                Nodo nuevo_nodo;
                if(manzana){
                    if(turno == 0){
                        nuevo_nodo = new Nodo(id, hijos.get(0).getId(),movimientos.get(i), hijos.get(0).getPoscionN(), hijos.get(0).manzanas-1, Minfinito, hijos.get(0).profundidad+1);
                        //Aumentamos el marcador de el caballo blanco basados en la informaciond el padre  
                        nuevo_nodo.setManzanasB(hijos.get(0).getManzanasB()+1);
                        nuevo_nodo.setManzanasN(hijos.get(0).getManzanasN());
                    }else{
                        nuevo_nodo = new Nodo(id, hijos.get(0).getId(),hijos.get(0).getPoscionB(), movimientos.get(i), hijos.get(0).manzanas-1, infinito, hijos.get(0).profundidad+1);                    
                        //Aumentamos el marcador de el caballo negro basados en la informaciond el padre
                        nuevo_nodo.setManzanasN(hijos.get(0).getManzanasN()+1);
                        nuevo_nodo.setManzanasB(hijos.get(0).getManzanasB());
                    }
                }else{
                    if(turno == 0){
                        nuevo_nodo = new Nodo(id, hijos.get(0).getId(),movimientos.get(i), hijos.get(0).getPoscionN(), hijos.get(0).manzanas, infinito, hijos.get(0).profundidad+1);
                        //como no se a obtenido ningna manzana entonces no se aumenta el marcador para ninguno
                        nuevo_nodo.setManzanasB(hijos.get(0).getManzanasB());
                        nuevo_nodo.setManzanasN(hijos.get(0).getManzanasN());
                    }else{
                        nuevo_nodo = new Nodo(id, hijos.get(0).getId(),hijos.get(0).getPoscionB(), movimientos.get(i), hijos.get(0).manzanas, Minfinito, hijos.get(0).profundidad+1);                    
                        nuevo_nodo.setManzanasB(hijos.get(0).getManzanasB());
                        nuevo_nodo.setManzanasN(hijos.get(0).getManzanasN());
                    }
                }
                //averiguamos si el juego se acabo 
                if(nuevo_nodo.getManzanas()==0){
                    //como este nodo acaba de terminar el juego en su rama entonces procedemos a separarlo 
                    //de los nodos por expandir
                    
                    aux.añadir(arbol, nuevo_nodo);
                }else{
                    //añadirmos el nodo a los nodos pendientes por expandir siempre y cuando cumpla con 
                    //la condicion de ciclo
                    hijos.add(nuevo_nodo);
                }   
                
                
            }
            //finalmente quitamos el nodo de los nodos pendientes por expandir
            
            hijos.remove(0);
            
        } while (!hijos.isEmpty());

    }

}
