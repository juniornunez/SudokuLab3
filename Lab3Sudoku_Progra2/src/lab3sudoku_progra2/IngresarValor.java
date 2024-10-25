/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3sudoku_progra2;

/**
 *
 * @author Junior Nuñes
 */
public class IngresarValor {
    
    
    private int valor;
    private boolean esEditable;

    public IngresarValor(int valor, boolean esEditable) {
        this.valor = valor;
        this.esEditable = esEditable;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        if (esEditable) {
            this.valor = valor;
        }
    }

    public boolean esEditable() {
        return esEditable;
    }

    public void setEditable(boolean esEditable) {
        this.esEditable = esEditable;
        
    }
}
