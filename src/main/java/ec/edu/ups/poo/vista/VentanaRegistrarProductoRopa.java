package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.ProductoRopa;
import ec.edu.ups.poo.enums.Talla;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaRegistrarProductoRopa extends Frame implements ActionListener {

    private GestionDeComprasModelo model;

    private Label etiquetaId;
    private TextField campoId;
    private Label etiquetaNombre;
    private TextField campoNombre;
    private Label etiquetaPrecio;
    private TextField campoPrecio;

    private Label etiquetaTalla;
    private CheckboxGroup grupoTalla;

    private Button botonGuardar;
    private Button botonCerrar;
    private TextArea areaMensajes;

    public VentanaRegistrarProductoRopa(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 255, 204));

        Panel panelGeneral = new Panel(new GridLayout(4, 2, 5, 5)); // Ajustado a 4 filas

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

        etiquetaTalla = new Label("Talla:");
        panelGeneral.add(etiquetaTalla);
        Panel panelTalla = new Panel(new FlowLayout(FlowLayout.LEFT));
        grupoTalla = new CheckboxGroup();
        for (Talla t : Talla.values()) {
            panelTalla.add(new Checkbox(t.name(), grupoTalla, false));
        }
        panelGeneral.add(panelTalla);

        add(panelGeneral, BorderLayout.NORTH);

        areaMensajes = new TextArea("Info", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaMensajes.setEditable(false);
        add(areaMensajes, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonGuardar = new Button("Guardar Producto Ropa");
        botonGuardar.addActionListener(this);
        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(500, 350); // Ajustar tamaño si es necesario
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

        if (command.equals("Guardar Producto Ropa")) {
            areaMensajes.setText("");

            String idStr = campoId.getText().trim();
            String nombre = campoNombre.getText().trim();
            String precioStr = campoPrecio.getText().trim();
            Checkbox selectedTallaCheckbox = grupoTalla.getSelectedCheckbox();

            if (idStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || selectedTallaCheckbox == null) {
                areaMensajes.append("Todos los campos son obligatorios.");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                double precio = Double.parseDouble(precioStr);

                if (model.findProductoById(id) != null) {
                    areaMensajes.append("Error: Ya existe un producto con el ID " + id + ".\n");
                    return;
                }

                Talla talla = Talla.valueOf(selectedTallaCheckbox.getLabel().toUpperCase());

                ProductoRopa nuevaRopa = new ProductoRopa(id, nombre, precio, talla);
                model.addProducto(nuevaRopa);
                areaMensajes.append("Producto Ropa registrado exitosamente:\n" + nuevaRopa.imprimirDetalle());

                campoId.setText("");
                campoNombre.setText("");
                campoPrecio.setText("");
                grupoTalla.setSelectedCheckbox(null);

            } catch (NumberFormatException ex) {
                areaMensajes.append("Error de formato: Asegúrese de que ID y Precio sean números válidos.\n");
            } catch (IllegalArgumentException ex) {
                areaMensajes.append("Error en la talla seleccionada: " + ex.getMessage() + "\n");
            } catch (Exception ex) {
                areaMensajes.append("Ocurrió un error inesperado al guardar la ropa: " + ex.getMessage() + "\n");
                ex.printStackTrace();
            }

        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
            dispose();
        }
    }
}