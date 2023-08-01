/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**
 *
 * @author Wanana
 */
public class ListaCircular {

    int tam;
    Nodo cabeza;

    public ListaCircular() {
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public void AgregarAlFinal(int numero, String color) {
        Nodo nuevo = new Nodo();
        nuevo.setNumero(numero);
        nuevo.setColor(color);
        if (esVacia()) {
            cabeza = nuevo;
            cabeza.setSgte(cabeza);
        } else {
            Nodo aux = cabeza;
            while (aux.getSgte() != cabeza) {
                aux = aux.getSgte();
            }
            aux.setSgte(nuevo);
            nuevo.setSgte(cabeza);

        }
        tam++;
    }

    public void listar() {
        Nodo aux = cabeza;
        int tam = 1;
        do {
            System.out.print(tam + "[" + aux.getNumero() + ", " + aux.getColor() + "]→");
            tam++;
            aux = aux.getSgte();
        } while (aux != cabeza);
        System.out.println("cabeza");
    }

    public Nodo Aleatorio() {
        Random aleatorio = new Random();
        Nodo aux = cabeza;
        int pos = aleatorio.nextInt(tam);
        for (int i = 0; i < pos; i++) {
            aux = aux.getSgte();
        }
        return aux;
    }

    public void Eliminar(int Numero, String Color) {
        Nodo aux = cabeza;
        int contador = 0;
        do {
            if (aux.getNumero() == Numero && aux.getColor().equals(Color)) {
                break;
            }
            aux = aux.getSgte();
            contador++;
        } while (aux.getSgte() != cabeza);
        aux = cabeza;
        for (int i = 0; i < contador - 1; i++) {
            aux = aux.getSgte();
        }
        Nodo temp = aux.getSgte().getSgte();
        aux.setSgte(temp);
        tam--;
    }

    /*   public int validar(String nombre) {
     String[] parte = nombre.split("-");
     Nodo aux = cabeza;
     int contador=-1;
     boolean flag = false;
     do {
     contador++;
     if (aux.getColor().equals(parte[0])) {
     flag = true;
     System.out.println("holaaaaaaaaaaaaaaaaaaaa");
     }
     if (aux.getNumero() == Integer.parseInt(parte[1])) {
     flag = true;
                
     }
     aux = aux.getSgte();
            
     } while (aux.getSgte() != cabeza && flag != true);
     if(flag==false){
     contador=0;
     }
        
     while (!aux.getColor().equals(parte[0]) || aux.getNumero()!=Integer.parseInt(parte[1])){
     aux=aux.getSgte();
     }
     return contador;
     }*/
    public boolean EncontrarRef(String color, int numero) {
        Nodo aux = cabeza;
        boolean floag = false;
        do {
            if (aux.getColor().equals(color) || aux.getNumero() == numero) {
                floag = true;
                break;
            } else {
                aux = aux.getSgte();
            }
        } while (aux.getSgte() != cabeza);
        return floag;
    }

    public int validar(String nombre) {
        int posicion = 0;
        String[] parte = nombre.split("-");
        Nodo aux = cabeza;
        boolean floag = false;
        do {
            if (aux.getColor().equals(parte[0]) || aux.getNumero() == Integer.parseInt(parte[1])) {
                floag = true;
            } else {
                posicion++;
                aux = aux.getSgte();
            }
        } while (aux.getSgte() != cabeza && floag != true);
        return posicion;
    }
}
