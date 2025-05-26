package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.Proveedor;

public class VentanaListarProveedores extends Frame implements ActionListener {

    private GestionDeComprasModelo model;
    private TextArea areaProveedores;
    private Button botonActualizar;
    private Button botonCerrar;


    public VentanaListarProveedores(String title, GestionDeComprasModelo model ) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout());

        Label tituloLabel = new Label("Lista de Proveedores", Label.CENTER);
        add(tituloLabel, BorderLayout.NORTH);


        areaProveedores = new TextArea("Cargando proveedores...", 10, 60, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaProveedores.setEditable(false);
        add(areaProveedores, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));

        botonActualizar = new Button("Actualizar Lista");
        botonActualizar.addActionListener(this);
        panelBotones.add(botonActualizar);

        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonCerrar);
        add(panelBotones, BorderLayout.SOUTH);


        setSize(500, 300);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        cargarProveedores();
    }

    private void cargarProveedores() {
        areaProveedores.setText("");
        areaProveedores.append("===== Lista de Proveedores =====\n\n");

        List<Proveedor> proveedores = model.getProveedores();

        if (proveedores.isEmpty()) {
            areaProveedores.append("No hay proveedores registrados.");
        } else {
            for (Proveedor p : proveedores) {
                areaProveedores.append(p.toString() + "\n");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Actualizar Lista")) {
            cargarProveedores();
        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
        }
    }
}