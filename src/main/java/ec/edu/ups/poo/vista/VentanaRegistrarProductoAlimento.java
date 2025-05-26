package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.ProductoAlimento;
import ec.edu.ups.poo.enums.UnidadDeMedida;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;

public class VentanaRegistrarProductoAlimento extends Frame implements ActionListener {

    private GestionDeComprasModelo model;

    private Label etiquetaId;
    private TextField campoId;
    private Label etiquetaNombre;
    private TextField campoNombre;
    private Label etiquetaPrecio;
    private TextField campoPrecio;
    private Label etiquetaUnidad;
    private CheckboxGroup grupoUnidadDeMedida;

    private Label etiquetaFechaExpiracion;
    private TextField campoAnioExp;
    private TextField campoMesExp;
    private TextField campoDiaExp;

    private Button botonGuardar;
    private Button botonCerrar;
    private TextArea areaMensajes;

    public VentanaRegistrarProductoAlimento(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 255, 204));

        Panel panelGeneral = new Panel(new GridLayout(6, 2, 5, 5));

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

        etiquetaUnidad = new Label("Unidad de Medida:");
        panelGeneral.add(etiquetaUnidad);
        Panel panelUnidad = new Panel(new FlowLayout(FlowLayout.LEFT));
        grupoUnidadDeMedida = new CheckboxGroup();

        for (UnidadDeMedida um : UnidadDeMedida.values()) {
            panelUnidad.add(new Checkbox(um.name(), grupoUnidadDeMedida, false));
        }

        panelGeneral.add(panelUnidad);

        etiquetaFechaExpiracion = new Label("Fecha Expiración (Año Mes Día):");
        panelGeneral.add(etiquetaFechaExpiracion);
        Panel panelFecha = new Panel(new FlowLayout(FlowLayout.LEFT));
        campoAnioExp = new TextField(4);
        campoMesExp = new TextField(2);
        campoDiaExp = new TextField(2);
        panelFecha.add(campoAnioExp);
        panelFecha.add(new Label("/"));
        panelFecha.add(campoMesExp);
        panelFecha.add(new Label("/"));
        panelFecha.add(campoDiaExp);
        panelGeneral.add(panelFecha);

        add(panelGeneral, BorderLayout.NORTH);

        areaMensajes = new TextArea("Info", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaMensajes.setEditable(false);
        add(areaMensajes, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonGuardar = new Button("Guardar Producto Alimento");
        botonGuardar.addActionListener(this);
        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(500, 450);
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

        if (command.equals("Guardar Producto Alimento")) {
            areaMensajes.setText("");

            String idStr = campoId.getText().trim();
            String nombre = campoNombre.getText().trim();
            String precioStr = campoPrecio.getText().trim();
            Checkbox selectedUnitCheckbox = grupoUnidadDeMedida.getSelectedCheckbox();

            String anioStr = campoAnioExp.getText().trim();
            String mesStr = campoMesExp.getText().trim();
            String diaStr = campoDiaExp.getText().trim();

            if (idStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || selectedUnitCheckbox == null ||
                    anioStr.isEmpty() || mesStr.isEmpty() || diaStr.isEmpty()) {
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

                UnidadDeMedida medida = UnidadDeMedida.valueOf(selectedUnitCheckbox.getLabel().toUpperCase());

                int anio = Integer.parseInt(anioStr);
                int mes = Integer.parseInt(mesStr) - 1;
                int dia = Integer.parseInt(diaStr);

                GregorianCalendar fechaExpiracion = new GregorianCalendar(anio, mes, dia);
                fechaExpiracion.setLenient(false);
                fechaExpiracion.getTime();

                ProductoAlimento nuevoAlimento = new ProductoAlimento(id, nombre, precio, medida, fechaExpiracion);
                model.addProducto(nuevoAlimento);
                areaMensajes.append("Producto Alimento registrado exitosamente:\n" + nuevoAlimento.imprimirDetalle());

                campoId.setText("");
                campoNombre.setText("");
                campoPrecio.setText("");
                grupoUnidadDeMedida.setSelectedCheckbox(null);
                campoAnioExp.setText("");
                campoMesExp.setText("");
                campoDiaExp.setText("");

            } catch (NumberFormatException ex) {
                areaMensajes.append("Error de formato: Asegúrese de que ID, Precio, Año, Mes y Día sean números válidos.\n");
            } catch (IllegalArgumentException ex) {
                areaMensajes.append("Error en la fecha o unidad de medida: " + ex.getMessage() + "\n");
            } catch (Exception ex) {
                areaMensajes.append("Ocurrió un error inesperado al guardar el alimento: " + ex.getMessage() + "\n");
                ex.printStackTrace();
            }

        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
            dispose();
        }
    }
}