package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.SolicitudDeCompra;

import java.awt.*;
import java.awt.event.*;

public class VentanaBuscarSolicitud extends Frame {

    private TextField numeroField;
    private TextArea resultadoArea;

    public VentanaBuscarSolicitud(String title, GestionDeComprasModelo modelo) {
        super(title);
        setSize(400, 300);
        setLayout(new FlowLayout());
        setBackground(new Color(255, 255, 204));

        add(new Label("Número de Solicitud:"));
        numeroField = new TextField(20);
        add(numeroField);

        Button buscarButton = new Button("Buscar");
        add(buscarButton);

        resultadoArea = new TextArea(10, 40);
        add(resultadoArea);

        buscarButton.addActionListener(e -> {
            String numero = numeroField.getText();
            SolicitudDeCompra solicitud = modelo.findSolicitudByNumero(numero);
            if (solicitud != null) {
                resultadoArea.setText("Solicitud encontrada:\n" + solicitud);
            } else {
                resultadoArea.setText("Solicitud no encontrada.");
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