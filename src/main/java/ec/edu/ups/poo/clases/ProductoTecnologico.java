package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.EstadoProducto;

public class ProductoTecnologico extends Producto {

    private int garantia;
    private EstadoProducto estado;

    public ProductoTecnologico(int id, String nombre, double precioUnitario, int garantia, EstadoProducto estado) {
        super(id, nombre, precioUnitario);
        this.garantia = garantia;
        this.estado = estado;
    }

    public int getGarantia() {
        return garantia;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    @Override
    public double calcularDescuento(double porcentaje) {
        return getPrecioUnitario() - (getPrecioUnitario() * porcentaje / 100);
    }

    @Override
    public String imprimirDetalle() {
        return "ID: " + getId() + ", Nombre: " + getNombre() +
                ", Precio: " + getPrecioUnitario() +
                ", Garantía: " + garantia +
                ", Estado: " + getEstado();
    }
}