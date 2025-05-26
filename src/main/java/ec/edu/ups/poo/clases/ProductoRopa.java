package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.Talla;

public class ProductoRopa extends Producto {
    private Talla talla;

    public ProductoRopa(int id, String nombre, double precioUnitario, Talla talla) {
        super(id, nombre, precioUnitario);
        this.talla = talla;
    }

    public Talla getTalla() {
        return talla;
    }

    @Override
    public double calcularDescuento(double porcentaje) {
        return getPrecioUnitario() - (getPrecioUnitario() * porcentaje / 100);
    }

    @Override
    public String imprimirDetalle() {
        return "ID: " + getId() + ", Nombre: " + getNombre() +
                ", Precio: " + getPrecioUnitario() +
                ", Talla: " + getTalla();
    }
}