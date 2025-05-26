package ec.edu.ups.poo.clases;

public class Producto {
    private int id;
    private String nombre;
    private double precioUnitario;

    public Producto(int id, String nombre, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double calcularDescuento(double porcentaje) {
        return precioUnitario;
    }

    public String imprimirDetalle() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: " + precioUnitario;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}