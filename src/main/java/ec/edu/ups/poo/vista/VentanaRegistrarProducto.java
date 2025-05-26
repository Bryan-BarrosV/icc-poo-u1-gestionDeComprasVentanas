package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.vista.VentanaRegistrarProductoAlimento;
import ec.edu.ups.poo.vista.VentanaRegistrarProductoRopa;
import ec.edu.ups.poo.vista.VentanaRegistrarProductoTecnologico;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaRegistrarProducto extends Frame implements ActionListener {

    private GestionDeComprasModelo modelo;

    private Panel panelBotonesTipos;
    private Button botonRegistrarAlimento;
    private Button botonRegistrarRopa;
    private Button botonRegistrarTecnologico;
    private Button botonCerrar;

    public VentanaRegistrarProducto(String title, GestionDeComprasModelo modelo) {
        super(title);
        this.modelo = modelo;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 255, 204));

        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        Label tituloLabel = new Label("Seleccione Tipo de Producto a Registrar");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(tituloLabel);
        add(panelTitulo, BorderLayout.NORTH);

        panelBotonesTipos = new Panel();
        panelBotonesTipos.setLayout(new GridLayout(3, 1, 10, 10));
        panelBotonesTipos.setBackground(new Color(255, 255, 204));

        botonRegistrarAlimento = new Button("Registrar Producto Alimento");
        botonRegistrarRopa = new Button("Registrar Producto Ropa");
        botonRegistrarTecnologico = new Button("Registrar Producto Tecnológico");
        botonCerrar = new Button("Cerrar");

        botonRegistrarAlimento.addActionListener(this);
        botonRegistrarRopa.addActionListener(this);
        botonRegistrarTecnologico.addActionListener(this);
        botonCerrar.addActionListener(this);

        panelBotonesTipos.add(botonRegistrarAlimento);
        panelBotonesTipos.add(botonRegistrarRopa);
        panelBotonesTipos.add(botonRegistrarTecnologico);

        add(panelBotonesTipos, BorderLayout.CENTER);

        Panel panelCerrar = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelCerrar.add(botonCerrar);
        add(panelCerrar, BorderLayout.SOUTH);

        setSize(400, 300);
        setResizable(false);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Registrar Producto Alimento")) {
            VentanaRegistrarProductoAlimento ventana = new VentanaRegistrarProductoAlimento("Registrar Alimento", modelo);
            ventana.setVisible(true);
        } else if (command.equals("Registrar Producto Ropa")) {
            VentanaRegistrarProductoRopa ventana = new VentanaRegistrarProductoRopa("Registrar Ropa", modelo);
            ventana.setVisible(true);
        } else if (command.equals("Registrar Producto Tecnológico")) {
            VentanaRegistrarProductoTecnologico ventana = new VentanaRegistrarProductoTecnologico("Registrar Tecnológico", modelo);
            ventana.setVisible(true);
        } else if (command.equals("Cerrar")) {
            setVisible(false);
            dispose();
        }
    }
}