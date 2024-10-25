/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3sudoku_progra2;

/**
 *
 * @author Junior Nuñes
 */
public class Juego {
    
    
    private Celdas cuadricula;
    private Resolvedor solucionador;

    public Juego() {
        cuadricula = new Celdas();
        solucionador = new Resolvedor(cuadricula);
    }

    public void resolver() {
        solucionador.resolver();
    }

    public Celdas getCuadricula() {
        return cuadricula;
    }

    public void nuevoJuego() {
        cuadricula = new Celdas();
        solucionador = new Resolvedor(cuadricula);
    }
}
