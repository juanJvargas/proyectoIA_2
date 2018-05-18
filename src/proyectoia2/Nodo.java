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
public class Nodo {
    //posicion del caballo blanco
    int[] poscionB;
    //posicion del caballo negro
    int[] poscionN;
    //Cantidad de manzanas en el tablero
    int manzanas;
    //Productividad
    int productividad;
    //Profundidad del nodo
    int profundidad;
    //cantidad de manzanas del caballo balnco
    int manzanasB=0;
    //cantidad de manzanas del caballo negro
    int manzanasN=0;
    //identificacion del nodo
    int id;
    //identificacion del padre
    int id_padre;
    
    public Nodo(int id, int id_padre,int[] poscionB, int[] poscionN, int manzanas, int productividad, int profundidad) {
        this.id = id;
        this.id_padre = id_padre;
        this.poscionB = poscionB;
        this.poscionN = poscionN;
        this.manzanas = manzanas;
        this.productividad = productividad;
        this.profundidad = profundidad;
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

   

    public int[] getPoscionB() {
        return poscionB;
    }

    public void setPoscionB(int[] poscionB) {
        this.poscionB = poscionB;
    }

    public int[] getPoscionN() {
        return poscionN;
    }

    public void setPoscionN(int[] poscionN) {
        this.poscionN = poscionN;
    }

    public int getManzanas() {
        return manzanas;
    }

    public void setManzanas(int manzanas) {
        this.manzanas = manzanas;
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
