package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import java.awt.*;
import java.awt.event.*;

public class VentanaCalcularTotal extends Frame implements ActionListener {

    private GestionDeComprasModelo modelo;
    private TextField numeroField;
    private TextArea resultadoArea;

    public VentanaCalcularTotal(String title, GestionDeComprasModelo modelo) {
        super(title);
        this.modelo = modelo;

        setLayout(new FlowLayout());
        setBackground(new Color(255, 255, 204));

        Label numeroLabel = new Label("Ingrese Número de Solicitud:");
        numeroField = new TextField(20);
        Button calcularButton = new Button("Calcular Total");
        resultadoArea = new TextArea(5, 40);

        calcularButton.addActionListener(this);

        add(numeroLabel);
        add(numeroField);
        add(calcularButton);
        add(resultadoArea);

        setSize(500, 300);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String numero = numeroField.getText().trim();
        if (numero.isEmpty()) {
            resultadoArea.setText("Por favor ingrese un número de solicitud.");
            return;
        }

        double total = modelo.calcularTotalSolicitud(numero);
        if (total > 0) {
            resultadoArea.setText("Total de la solicitud " + numero + ": $" + total);
        } else {
            resultadoArea.setText("No se encontró la solicitud o no tiene detalles.");
        }
    }
}