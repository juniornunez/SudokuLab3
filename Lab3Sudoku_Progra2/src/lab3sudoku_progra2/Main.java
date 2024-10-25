/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3sudoku_progra2;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 *
 * @author Junior Nuñes
 */
public class Main extends JFrame implements ComponenteVisual {

    private JTextField[][] campos;
    private Juego juego;

    public Main(Juego juego) {
        this.juego = juego;
        setTitle("Sudoku");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelCuadricula = new JPanel(new GridLayout(9, 9));
        Celdas cuadricula = juego.getCuadricula();
        campos = new JTextField[9][9];

        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                IngresarValor celda = cuadricula.getCelda(fila, columna);
                campos[fila][columna] = new JTextField();
                campos[fila][columna].setHorizontalAlignment(JTextField.CENTER);
                campos[fila][columna].setFont(new Font("Arial", Font.PLAIN, 24));

                Border borde = BorderFactory.createMatteBorder(
                        fila % 3 == 0 ? 2 : 1,
                        columna % 3 == 0 ? 2 : 1,
                        (fila + 1) % 3 == 0 ? 2 : 1,
                        (columna + 1) % 3 == 0 ? 2 : 1,
                        Color.BLACK
                );
                campos[fila][columna].setBorder(borde);

                if (!celda.esEditable()) {
                    campos[fila][columna].setText(String.valueOf(celda.getValor()));
                    campos[fila][columna].setEditable(false);
                    campos[fila][columna].setBackground(Color.LIGHT_GRAY);
                }
                panelCuadricula.add(campos[fila][columna]);
            }
        }

        JPanel panelBotones = new JPanel();
        JButton botonResolver = new JButton("Completar");
        botonResolver.addActionListener(e -> {
            juego.resolver();
            actualizarCuadricula(juego.getCuadricula());
            deshabilitarCampos();
        });
        panelBotones.add(botonResolver);


        JButton botonVerificar = new JButton("Verificar");
        botonVerificar.addActionListener(e -> {
            actualizarCuadriculaDesdeCampos();
            boolean correcto = juego.getCuadricula().estaCompletoYValido();
            String mensaje = correcto ? "Sudoku correcto" : "Hay errores en el Sudoku.";
            JOptionPane.showMessageDialog(this, mensaje);
        });
        panelBotones.add(botonVerificar);

        JButton botonNuevoJuego = new JButton("Nueva Partida");
        botonNuevoJuego.addActionListener(e -> {
            juego.nuevoJuego();
            actualizarCuadricula(juego.getCuadricula());
        });
        panelBotones.add(botonNuevoJuego);

        add(panelCuadricula, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void dibujar() {
        setVisible(true);
    }

    @Override
    public void actualizar() {
        actualizarCuadricula(juego.getCuadricula());
    }

    private void deshabilitarCampos() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                campos[fila][columna].setEditable(false); 
            }
        }
    }

    private void actualizarCuadricula(Celdas cuadricula) {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                IngresarValor celda = cuadricula.getCelda(fila, columna);

                String texto = (celda.getValor() == 0) ? "" : String.valueOf(celda.getValor());
                campos[fila][columna].setText(texto);
                campos[fila][columna].setEditable(celda.esEditable());
                campos[fila][columna].setBackground(celda.esEditable() ? Color.WHITE : Color.LIGHT_GRAY);
            }
        }
    }

private void actualizarCuadriculaDesdeCampos() {
    Celdas cuadricula = juego.getCuadricula();
    boolean hayError = false; 

    for (int fila = 0; fila < 9; fila++) {
        for (int columna = 0; columna < 9; columna++) {
            String texto = campos[fila][columna].getText();
            try {
                int valor = texto.isEmpty() ? 0 : Integer.parseInt(texto);
                if (valor < 1 || valor > 9) { 
                    hayError = true;
                    campos[fila][columna].setText(""); 
                } else {
                    cuadricula.setCelda(fila, columna, valor);
                }
            } catch (NumberFormatException ex) {
                hayError = true;
                campos[fila][columna].setText(""); 
            }
        }
    }

    
    if (hayError) {
        JOptionPane.showMessageDialog(this,
                "Por favor ingrese solo números entre 1 y 9.",
                "Entrada no válida",
                JOptionPane.WARNING_MESSAGE);
    }
}
    public static void main(String[] args) {
        Juego juego = new Juego();
        Main gui = new Main(juego);
        gui.dibujar();
    }
}
