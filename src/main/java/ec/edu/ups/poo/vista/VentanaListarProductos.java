package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.Producto;
import ec.edu.ups.poo.clases.ProductoAlimento;
import ec.edu.ups.poo.clases.ProductoRopa;
import ec.edu.ups.poo.clases.ProductoTecnologico;
import java.util.List;

public class VentanaListarProductos extends Frame implements ActionListener {

    private GestionDeComprasModelo model;
    private TextArea areaListadoProductos;
    private Button botonActualizar;
    private Button botonRegresar;

    public VentanaListarProductos(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout());

        areaListadoProductos = new TextArea("Listado de Productos...", 15, 60, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaListadoProductos.setEditable(false);
        add(areaListadoProductos, BorderLayout.CENTER );

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));

        botonActualizar = new Button("Actualizar Lista");
        botonActualizar.addActionListener(this);
        panelBotones.add(botonActualizar);

        botonRegresar = new Button("Regresar");
        botonRegresar.addActionListener(this);
        panelBotones.add(botonRegresar);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(600, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });

        cargarListaProductos();
    }

    private void cargarListaProductos() {
        areaListadoProductos.setText("");

        List<Producto> productos = model.getProductos();

        if (productos.isEmpty()) {
            areaListadoProductos.append("No hay productos registrados.");
        } else {
            areaListadoProductos.append("--- LISTADO DE PRODUCTOS ---\n");
            for (Producto p : productos) {
                areaListadoProductos.append("ID: " + p.getId() + "\n");
                areaListadoProductos.append("  Nombre: " + p.getNombre() + "\n");
                areaListadoProductos.append("  Precio Unitario: " + p.getPrecioUnitario() + "\n");

                if (p instanceof ProductoAlimento) {
                    ProductoAlimento pa = (ProductoAlimento) p;
                    areaListadoProductos.append("  Tipo: Alimento\n");
                    areaListadoProductos.append("  Unidad de Medida: " + pa.getMedida() + "\n");
                    areaListadoProductos.append("  Fecha de Expiración: " + pa.imprimirDetalle().split("Fecha de Expiración: ")[1] + "\n");
                } else if (p instanceof ProductoRopa) {
                    ProductoRopa pr = (ProductoRopa) p;
                    areaListadoProductos.append("  Tipo: Ropa\n");
                    areaListadoProductos.append("  Talla: " + pr.getTalla() + "\n");
                } else if (p instanceof ProductoTecnologico) {
                    ProductoTecnologico pt = (ProductoTecnologico) p;
                    areaListadoProductos.append("  Tipo: Tecnológico\n");
                    areaListadoProductos.append("  Garantía: " + pt.getGarantia() + " días\n");
                    areaListadoProductos.append("  Estado: " + pt.getEstado() + "\n");
                } else {
                    areaListadoProductos.append("  Tipo: Genérico (o no reconocido)\n");
                }
                areaListadoProductos.append("--------------------------------\n");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Actualizar Lista")) {
            cargarListaProductos();
        } else if (command.equals("Regresar")) {
            setVisible(false);
            dispose();
        }
    }
}