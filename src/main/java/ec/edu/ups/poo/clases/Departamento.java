package ec.edu.ups.poo.clases;

public class Departamento {

    private Empleado responsable;

    public Departamento(Empleado responsable) {
        this.responsable = responsable;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "responsable=" + responsable +
                '}';
    }
}