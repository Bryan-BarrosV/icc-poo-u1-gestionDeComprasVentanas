package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.UnidadDeMedida;
import java.util.GregorianCalendar;

public class ProductoAlimento extends Producto {
    private GregorianCalendar fechaExpiracion;
    private UnidadDeMedida medida;

    public ProductoAlimento(int id, String nombre, double precioUnitario, UnidadDeMedida medida, GregorianCalendar fechaExpiracion) {
        super(id, nombre, precioUnitario);
        this.medida = medida;
        this.fechaExpiracion = fechaExpiracion;
    }

    public UnidadDeMedida getMedida() {
        return medida;
    }

    public GregorianCalendar getFechaExpiracion() {
        return fechaExpiracion;
    }

    @Override
    public double calcularDescuento(double porcentaje) {
        return getPrecioUnitario() - (getPrecioUnitario() * porcentaje / 100);
    }

    @Override
    public String imprimirDetalle() {
        return "ID: " + getId() + ", Nombre: " + getNombre() +
                ", Precio: " + getPrecioUnitario() +
                ", Medida: " + getMedida() +
                ", Fecha de Expiración: " + fechaExpiracion.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                (fechaExpiracion.get(GregorianCalendar.MONTH) + 1) + "/" +
                fechaExpiracion.get(GregorianCalendar.YEAR);
    }
}