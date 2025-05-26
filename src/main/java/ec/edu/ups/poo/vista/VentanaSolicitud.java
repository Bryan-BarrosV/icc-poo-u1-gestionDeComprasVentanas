package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;

public class VentanaSolicitud extends Frame {
    private GestionDeComprasModelo modelo;
    public VentanaSolicitud(String title, GestionDeComprasModelo modelo ) {
        super(title);
        this.modelo = modelo;
        setBackground(new Color(255, 255, 204));
        setSize(500, 300);
        addWindowListener(new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
            setVisible(false);
        }
        });
    }

}