package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.Proveedor;

import java.awt.*;
import java.awt.event.*;

public class VentanaBuscarProveedor extends Frame {

    private TextField rucField;
    private TextArea resultadoArea;

    public VentanaBuscarProveedor(String title, GestionDeComprasModelo modelo) {
        super(title);
        setSize(400, 300);
        setLayout(new FlowLayout());
        setBackground(new Color(255, 255, 204));

        add(new Label("RUC del Proveedor:"));
        rucField = new TextField(20);
        add(rucField);

        Button buscarButton = new Button("Buscar");
        add(buscarButton);

        resultadoArea = new TextArea(10, 40);
        add(resultadoArea);

        buscarButton.addActionListener(e -> {
            try {
                int ruc = Integer.parseInt(rucField.getText());
                Proveedor proveedor = modelo.findProveedorByRuc(ruc);
                if (proveedor != null) {
                    resultadoArea.setText("Proveedor encontrado:\n" + proveedor);
                } else {
                    resultadoArea.setText("Proveedor no encontrado.");
                }
            } catch (NumberFormatException ex) {
                resultadoArea.setText("RUC inválido.");
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