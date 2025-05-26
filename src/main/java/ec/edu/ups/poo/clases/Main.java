package ec.edu.ups.poo.clases;
import ec.edu.ups.poo.enums.EstadoSolicitud;
import ec.edu.ups.poo.enums.UnidadDeMedida;
import ec.edu.ups.poo.enums.Talla;
import ec.edu.ups.poo.enums.EstadoProducto;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.vista.VentanaPrincipal;

import java.util.*;
public class Main {
    private static List<Proveedor> proveedores = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();
    private static List<SolicitudDeCompra> solicitudes = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GestionDeComprasModelo model = new GestionDeComprasModelo();
        VentanaPrincipal ventana= new VentanaPrincipal("Sistema de Gestión de Compras", model);
        int opcion;
        do {
            System.out.println("===== SISTEMA DE GESTIÓN DE COMPRAS ERP =====");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Registrar producto");
            System.out.println("3. Registrar solicitud de compra");
            System.out.println("4. Listar proveedores");
            System.out.println("5. Listar productos");
            System.out.println("6. Listar solicitudes de compra");
            System.out.println("7. Buscar proveedor por RUC");
            System.out.println("8. Buscar producto por nombre");
            System.out.println("9. Buscar solicitud por número");
            System.out.println("10. Aprobar / Rechazar solicitud de compra");
            System.out.println("11. Calcular total de una solicitud");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("===== Registrar Proveedor =====");
                    System.out.print("Id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombreProveedor = scanner.nextLine();
                    System.out.print("RUC: ");
                    int ruc = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    int telefono = scanner.nextInt();
                    scanner.nextLine();
                    proveedores.add(new Proveedor(id, nombreProveedor, ruc, direccion, telefono));
                    System.out.println("Proveedor registrado exitosamente.");
                    break;

                case 2:
                    System.out.println("===== Registrar Producto =====");
                    System.out.print("ID: ");
                    int idProducto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombreProducto = scanner.nextLine();
                    System.out.print("Precio unitario: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Tipo de producto: ");
                    System.out.println("1. Alimento");
                    System.out.println("2. Ropa");
                    System.out.println("3. Tecnológico");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    if (tipo == 1) {
                        System.out.println("Unidad de medida (LIBRA, KILOGRAMO, LITRO, GRAMO): ");
                        UnidadDeMedida medidaAlimento = UnidadDeMedida.valueOf(scanner.nextLine().toUpperCase());
                        System.out.println("Ingrese fecha de expiración (año mes día): ");
                        int anio = scanner.nextInt();
                        int mes = scanner.nextInt() - 1;
                        int dia = scanner.nextInt();
                        scanner.nextLine();
                        productos.add(new ProductoAlimento(idProducto, nombreProducto, precio, medidaAlimento, new GregorianCalendar(anio, mes, dia)));
                    } else if (tipo == 2) {

                        System.out.print("Ingrese talla (M, L, S, X): ");
                        String tallaStr = scanner.nextLine().toUpperCase();
                        Talla talla = Talla.valueOf(tallaStr);
                        productos.add(new ProductoRopa(idProducto, nombreProducto, precio, talla));
                    } else if (tipo == 3) {

                        System.out.print("Garantía (días): ");
                        int garantia = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Estado (NUEVO, SEMINUEVO): ");
                        String estadoStr = scanner.nextLine().toUpperCase();
                        EstadoProducto estado = EstadoProducto.valueOf(estadoStr);
                        productos.add(new ProductoTecnologico(idProducto, nombreProducto, precio, garantia, estado));
                    } else {
                        System.out.println("Tipo inválido. ");
                    }
                    System.out.println("Producto registrado exitosamente. ");
                    break;

                case 3:
                    System.out.println("===== Registrar Solicitud de Compra =====");
                    System.out.print("ID de solicitud: ");
                    int idSolicitud = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Número de solicitud: ");
                    String numeroSolicitud = scanner.nextLine();
                    System.out.println("Fecha de emisión (año mes día): ");
                    int anioSolicitud = scanner.nextInt();
                    int mesSolicitud = scanner.nextInt() - 1;
                    int diaSolicitud = scanner.nextInt();
                    scanner.nextLine();

                    Departamento departamento;
                    if (departamentos.isEmpty()) {
                        System.out.println("No hay departamentos registrados, creando uno nuevo...");
                        System.out.print("ID Empleado Responsable: ");
                        int idEmpleado = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nombre Empleado: ");
                        String nombreEmpleado = scanner.nextLine();
                        System.out.print("Cargo Empleado: ");
                        String cargoEmpleado = scanner.nextLine();
                        System.out.print("Teléfono Empleado: ");
                        String telefonoEmpleado = scanner.nextLine();
                        Empleado empleado = new Empleado(idEmpleado, nombreEmpleado, cargoEmpleado, telefonoEmpleado);
                        departamento = new Departamento(empleado);
                        departamentos.add(departamento);
                    } else {
                        System.out.println("¿Desea usar un departamento existente? (S/N): ");
                        String respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("S")) {
                            for (int i = 0; i < departamentos.size(); i++) {
                                System.out.println((i + 1) + ". " + departamentos.get(i).getResponsable().getNombre());
                            }
                            System.out.print("Seleccione el número del departamento: ");
                            int indice = scanner.nextInt() - 1;
                            scanner.nextLine();
                            departamento = departamentos.get(indice);
                        } else {
                            System.out.print("ID Empleado Responsable: ");
                            int idEmpleado = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Nombre Empleado: ");
                            String nombreEmpleado = scanner.nextLine();
                            System.out.print("Cargo Empleado: ");
                            String cargoEmpleado = scanner.nextLine();
                            System.out.print("Teléfono Empleado: ");
                            String telefonoEmpleado = scanner.nextLine();
                            Empleado empleado = new Empleado(idEmpleado, nombreEmpleado, cargoEmpleado, telefonoEmpleado);
                            departamento = new Departamento(empleado);
                            departamentos.add(departamento);
                        }
                    }

                    List<DetalleCompra> detalles = new ArrayList<>();
                    String agregarMas;
                    do {
                        System.out.println("Seleccione producto (por ID): ");
                        for (Producto p : productos) {
                            System.out.println(p.getId() + ". " + p.getNombre());
                        }
                        int idProd = scanner.nextInt();
                        scanner.nextLine();
                        Producto productoSeleccionado = null;
                        for (Producto p : productos) {
                            if (p.getId() == idProd) {
                                productoSeleccionado = p;
                                break;
                            }
                        }
                        if (productoSeleccionado != null) {
                            System.out.print("Cantidad: ");
                            int cantidad = scanner.nextInt();
                            scanner.nextLine();
                            detalles.add(new DetalleCompra(productoSeleccionado, cantidad));
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                        System.out.print("¿Agregar otro producto? (S/N): ");
                        agregarMas = scanner.nextLine();
                    } while (agregarMas.equalsIgnoreCase("S"));

                    solicitudes.add(new SolicitudDeCompra(idSolicitud, detalles, EstadoSolicitud.SOLICITADA, departamento, numeroSolicitud, new GregorianCalendar(anioSolicitud, mesSolicitud, diaSolicitud)));
                    System.out.println("Solicitud registrada exitosamente.");
                    break;

                case 4:
                    System.out.println("===== Lista de Proveedores =====");
                    for (Proveedor p : proveedores) {
                        System.out.println(p);
                    }
                    break;

                case 5:
                    System.out.println("===== Lista de Productos =====");
                    for (Producto p : productos) {
                        System.out.println(p.imprimirDetalle());
                    }
                    break;

                case 6:
                    System.out.println("===== Lista de Solicitudes de Compra =====");
                    for (SolicitudDeCompra s : solicitudes) {
                        s.imprimir();
                    }
                    break;

                case 7:
                    System.out.print("Ingrese RUC: ");
                    int rucBuscar = scanner.nextInt();
                    scanner.nextLine();
                    for (Proveedor p : proveedores) {
                        if (p.getRuc() == rucBuscar) {
                            System.out.println(p);
                            break;
                        }
                    }
                    break;

                case 8:
                    System.out.print("Ingrese nombre del producto: ");
                    String nombreBuscar = scanner.nextLine();
                    for (Producto p : productos) {
                        if (p.getNombre().equalsIgnoreCase(nombreBuscar)) {
                            System.out.println(p.imprimirDetalle());
                            break;
                        }
                    }
                    break;

                case 9:
                    System.out.print("Ingrese número de solicitud: ");
                    String numBuscar = scanner.nextLine();
                    for (SolicitudDeCompra s : solicitudes) {
                        if (s.getNumero().equalsIgnoreCase(numBuscar)) {
                            s.imprimir();
                            break;
                        }
                    }
                    break;

                case 10:
                    System.out.print("Ingrese número de solicitud: ");
                    String numAprobar = scanner.nextLine();
                    for (SolicitudDeCompra s : solicitudes) {
                        if (s.getNumero().equalsIgnoreCase(numAprobar)) {
                            System.out.println("1. Aprobar");
                            System.out.println("2. Rechazar");
                            int opcionAprobar = scanner.nextInt();
                            scanner.nextLine();
                            if (opcionAprobar == 1) {
                                s.aprobar();
                                System.out.println("Solicitud aprobada.");
                            } else if (opcionAprobar == 2) {
                                s.rechazar();
                                System.out.println("Solicitud rechazada.");
                            } else {
                                System.out.println("Opción inválida.");
                            }
                            break;
                        }
                    }
                    break;

                case 11:
                    System.out.print("Ingrese número de solicitud: ");
                    String numTotal = scanner.nextLine();
                    for (SolicitudDeCompra s : solicitudes) {
                        if (s.getNumero().equalsIgnoreCase(numTotal)) {
                            double total = 0;
                            for (DetalleCompra d : (List<DetalleCompra>) s.getList()) {
                                total += d.calcularTotal();
                            }
                            System.out.println("Total de la solicitud: $" + total);
                            break;
                        }
                    }
                    break;

                case 12:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }

            System.out.println();

        } while (opcion!=12);
    }
}