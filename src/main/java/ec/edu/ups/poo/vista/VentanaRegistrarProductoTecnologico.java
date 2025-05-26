package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.ProductoTecnologico;
import ec.edu.ups.poo.enums.EstadoProducto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaRegistrarProductoTecnologico extends Frame implements ActionListener {

    private GestionDeComprasModelo model;

    private Label etiquetaId;
    private TextField campoId;
    private Label etiquetaNombre;
    private TextField campoNombre;
    private Label etiquetaPrecio;
    private TextField campoPrecio;

    private Label etiquetaGarantia;
    private TextField campoGarantia;
    private Label etiquetaEstadoProducto;
    private CheckboxGroup grupoEstadoProducto;

    private Button botonGuardar;
    private Button botonCerrar;
    private TextArea areaMensajes;

    public VentanaRegistrarProductoTecnologico(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 255, 204));

        Panel panelGeneral = new Panel(new GridLayout(5, 2, 5, 5));

        etiquetaId = new Label("ID:");
        campoId = new TextField(10);
        panelGeneral.add(etiquetaId);
        panelGeneral.add(campoId);

        etiquetaNombre = new Label("Nombre:");
        campoNombre = new TextField(20);
        panelGeneral.add(etiquetaNombre);
        panelGeneral.add(campoNombre);

        etiquetaPrecio = new Label("Precio Unitario:");
        campoPrecio = new TextField(10);
        panelGeneral.add(etiquetaPrecio);
        panelGeneral.add(campoPrecio);

        etiquetaGarantia = new Label("Garantía (días):");
        campoGarantia = new TextField(10);
        panelGeneral.add(etiquetaGarantia);
        panelGeneral.add(campoGarantia);

        etiquetaEstadoProducto = new Label("Estado:");
        panelGeneral.add(etiquetaEstadoProducto);
        Panel panelEstado = new Panel(new FlowLayout(FlowLayout.LEFT));
        grupoEstadoProducto = new CheckboxGroup();
        for (EstadoProducto ep : EstadoProducto.values()) {
            panelEstado.add(new Checkbox(ep.name(), grupoEstadoProducto, false));
        }
        panelGeneral.add(panelEstado);

        add(panelGeneral, BorderLayout.NORTH);

        areaMensajes = new TextArea("Info", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaMensajes.setEditable(false);
        add(areaMensajes, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonGuardar = new Button("Guardar Producto Tecnológico");
        botonGuardar.addActionListener(this);
        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(500, 400);
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

        if (command.equals("Guardar Producto Tecnológico")) {
            areaMensajes.setText("");

            String idStr = campoId.getText().trim();
            String nombre = campoNombre.getText().trim();
            String precioStr = campoPrecio.getText().trim();
            String garantiaStr = campoGarantia.getText().trim();
            Checkbox selectedEstadoCheckbox = grupoEstadoProducto.getSelectedCheckbox();

            if (idStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || garantiaStr.isEmpty() || selectedEstadoCheckbox == null) {
                areaMensajes.append("Todos los campos son obligatorios.");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                double precio = Double.parseDouble(precioStr);
                int garantia = Integer.parseInt(garantiaStr);

                if (model.findProductoById(id) != null) {
                    areaMensajes.append("Error: Ya existe un producto con el ID " + id + ".\n");
                    return;
                }

                EstadoProducto estado = EstadoProducto.valueOf(selectedEstadoCheckbox.getLabel().toUpperCase());

                ProductoTecnologico nuevoTecnologico = new ProductoTecnologico(id, nombre, precio, garantia, estado);
                model.addProducto(nuevoTecnologico);
                areaMensajes.append("Producto Tecnológico registrado exitosamente:\n" + nuevoTecnologico.imprimirDetalle());

                campoId.setText("");
                campoNombre.setText("");
                campoPrecio.setText("");
                campoGarantia.setText("");
                grupoEstadoProducto.setSelectedCheckbox(null);

            } catch (NumberFormatException ex) {
                areaMensajes.append("Error de formato: Asegúrese de que ID, Precio y Garantía sean números válidos.\n");
            } catch (IllegalArgumentException ex) {
                areaMensajes.append("Error en el estado seleccionado: " + ex.getMessage() + "\n");
            } catch (Exception ex) {
                areaMensajes.append("Ocurrió un error inesperado al guardar el producto tecnológico: " + ex.getMessage() + "\n");
                ex.printStackTrace();
            }

        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
            dispose();
        }
    }
}