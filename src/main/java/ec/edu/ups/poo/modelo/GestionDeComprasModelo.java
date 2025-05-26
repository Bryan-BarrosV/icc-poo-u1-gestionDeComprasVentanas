package ec.edu.ups.poo.modelo;

import ec.edu.ups.poo.clases.*;
import ec.edu.ups.poo.enums.EstadoSolicitud;
import ec.edu.ups.poo.enums.UnidadDeMedida; // Puedes eliminar esta importación si no se usa directamente en el modelo
import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;
import java.util.Scanner; // Puedes eliminar esta importación
import java.util.Optional; // Puedes eliminar esta importación

public class GestionDeComprasModelo {
    private List<Proveedor> proveedores;
    private List<Producto> productos;
    private List<SolicitudDeCompra> solicitudes;
    private List<Departamento> departamentos;

    public GestionDeComprasModelo() {
        this.proveedores = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
        this.departamentos = new ArrayList<>();
    }

    public void addProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public Proveedor findProveedorByRuc(int ruc) {
        for (Proveedor p : proveedores) {
            if (p.getRuc() == ruc) {
                return p;
            }
        }
        return null;
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Producto findProductoById(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public Producto findProductoByNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void addSolicitud(SolicitudDeCompra solicitud) {
        solicitudes.add(solicitud);
    }

    public List<SolicitudDeCompra> getSolicitudes() {
        return solicitudes;
    }

    public SolicitudDeCompra findSolicitudByNumero(String numero) {
        for (SolicitudDeCompra s : solicitudes) {
            if (s.getNumero().equalsIgnoreCase(numero)) {
                return s;
            }
        }
        return null;
    }

    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public double calcularTotalSolicitud(String numeroSolicitud) {
        SolicitudDeCompra solicitud = findSolicitudByNumero(numeroSolicitud);
        if (solicitud != null) {
            double total = 0;
            List<DetalleCompra> detallesGenericos = solicitud.getList();
            if (detallesGenericos != null) {
                for (DetalleCompra d : detallesGenericos) {
                    total += d.calcularTotal();
                }
            }
            return total;
        }
        return 0;
    }

    public boolean manejarEstadoSolicitud(String numeroSolicitud, int accion) {
        SolicitudDeCompra solicitud = findSolicitudByNumero(numeroSolicitud);
        if (solicitud != null) {
            if (accion == 1) {
                solicitud.aprobar();
                return true;
            } else if (accion == 2) {
                solicitud.rechazar();
                return true;
            }
        }
        return false;
    }

    public Departamento getCrearDepartamento(int idEmpleado, String nombreEmpleado, String cargoEmpleado, String telefonoEmpleado, boolean useExisting, int existingIndex) {
        if (useExisting && existingIndex >= 0 && existingIndex < departamentos.size()) {
            return departamentos.get(existingIndex);
        } else {
            Empleado empleado = new Empleado(idEmpleado, nombreEmpleado, cargoEmpleado, telefonoEmpleado);
            Departamento departamento = new Departamento(empleado);
            departamentos.add(departamento);
            return departamento ;
        }
    }
}