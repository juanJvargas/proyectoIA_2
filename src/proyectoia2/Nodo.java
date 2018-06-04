package proyectoia2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author krypth
 */
import java.util.Vector;

public class Nodo {

    //mejor jugada
    int[] mejorJugada = {0, 0};
    //posicion del caballo blanco
    //int[] poscionB;
    //posicion del caballo negro
    //int[] poscionN;
    //Cantidad de manzanas en el tablero
    //Vector<int[]> manzanas;
    //Productividad
    int productividad;
    //Profundidad del nodo
    int profundidad;
    //cantidad de manzanas del caballo balnco
    int manzanasB;
    //cantidad de manzanas del caballo negro
    int manzanasN;
    //identificacion del nodo
    int id;
    //identificacion del padre
    int id_padre;

    int[][] tablero;

    public Nodo() {
    }

    public Nodo(int id, int id_padre, int[][] tablero, int productividad, int profundidad, int manzanasB, int manzanasN) {
        this.id = id;
        this.id_padre = id_padre;
        this.tablero=tablero.clone();
        this.productividad = productividad;
        this.profundidad = profundidad;
        this.manzanasB = manzanasB;
        this.manzanasN = manzanasN;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public int[] getMejorJugada() {
        return mejorJugada;
    }

    public void setMejorJugada(int[] mejorJugada) {
        this.mejorJugada = mejorJugada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_padre() {
        return id_padre;
    }

    public void setId_padre(int id_padre) {
        this.id_padre = id_padre;
    }

    public int getProductividad() {
        return productividad;
    }

    public void setProductividad(int productividad) {
        this.productividad = productividad;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public int getManzanasB() {
        return manzanasB;
    }

    public void setManzanasB(int manzanasB) {
        this.manzanasB = manzanasB;
    }

    public int getManzanasN() {
        return manzanasN;
    }

    public void setManzanasN(int manzanasN) {
        this.manzanasN = manzanasN;
    }

}
