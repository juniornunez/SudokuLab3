/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3sudoku_progra2;

import java.util.Random;

/**
 *
 * @author Junior Nuñes
 */
public class Celdas {
    
     private IngresarValor[][] cuadricula;

    public Celdas() {
        cuadricula = new IngresarValor[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                cuadricula[fila][columna] = new IngresarValor(0, true);
            }
        }
        rellenarValoresAleatorios();
    }

    private void rellenarValoresAleatorios() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {  
            int fila = random.nextInt(9);
            int columna = random.nextInt(9);
            int valor = random.nextInt(9) + 1;
            if (esValido(fila, columna, valor)) {
                cuadricula[fila][columna].setValor(valor);
                cuadricula[fila][columna].setEditable(false);
            }
        }
    }

    public IngresarValor getCelda(int fila, int columna) {
        return cuadricula[fila][columna];
    }

    public void setCelda(int fila, int columna, int valor) {
        IngresarValor celda = cuadricula[fila][columna];
        if (celda.esEditable()) {
            celda.setValor(valor);
        }
    }

    public boolean esValido(int fila, int columna, int valor) {
        
        for (int cont = 0; cont < 9; cont++) {
            if (cont != columna && cuadricula[fila][cont].getValor() == valor) {
                return false;
            }
            if (cont != fila && cuadricula[cont][columna].getValor() == valor) {
                return false;
            }
        }
        
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;
        for (int cont2 = 0; cont2 < 3; cont2++) {
            for (int j = 0; j < 3; j++) {
                if ((inicioFila + cont2 != fila || inicioColumna + j != columna) &&
                    cuadricula[inicioFila + cont2][inicioColumna + j].getValor() == valor) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estaCompletoYValido() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                int valor = cuadricula[fila][columna].getValor();
                if (valor == 0 || !esValido(fila, columna, valor)) {
                    return false;
                }
            }
        }
        return true;
    }
}
