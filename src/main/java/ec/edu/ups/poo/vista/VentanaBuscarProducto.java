package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.Producto;

import java.awt.*;
import java.awt.event.*;

public class VentanaBuscarProducto extends Frame {

    private TextField nombreField;
    private TextArea resultadoArea;

    public VentanaBuscarProducto(String title, GestionDeComprasModelo modelo) {
        super(title);
        setSize(400, 300);
        setLayout(new FlowLayout());
        setBackground(new Color(255, 255, 204));

        add(new Label("Nombre del Producto:"));
        nombreField = new TextField(20);
        add(nombreField);

        Button buscarButton = new Button("Buscar");
        add(buscarButton);

        resultadoArea = new TextArea(10, 40);
        add(resultadoArea);

        buscarButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            Producto producto = modelo.findProductoByNombre(nombre);
            if (producto != null) {
                resultadoArea.setText("Producto encontrado:\n" + producto);
            } else {
                resultadoArea.setText("Producto no encontrado.");
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}