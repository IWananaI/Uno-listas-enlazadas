/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wanana
 */
public class ListaDePila {
    private Nodo cabeza;
    int tam;

    public ListaDePila() {
        cabeza = null;
        tam = 0;
    }

    public ListaDePila(int tam) {
        this.tam = tam;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public boolean esVacia() {
        return cabeza == null;
    }
    public void PUSH(int numero,String color) {
        Nodo nuevo = new Nodo();
        nuevo.setColor(color);
        nuevo.setNumero(numero);
        if (esVacia()) {
            cabeza = nuevo;
        } else {
            nuevo.setSgte(cabeza);
            cabeza = nuevo;
        }
        tam++;
    }
    public void POP(){
         if (!esVacia()) {
            cabeza = cabeza.getSgte();
        }
    }
    public void listar() {
        Nodo aux = cabeza;
        tam = 1;
        while (aux != null) {
            System.out.print(tam + "[" + aux.getColor() +aux.getNumero()+ "]→");
            tam++;
            aux = aux.getSgte();
        }
        System.out.println("null");
    }
}
