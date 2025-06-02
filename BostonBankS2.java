package com.mycompany.bostonbanks2;

import java.util.ArrayList;
import java.util.Scanner;

public class BostonBankS2 {

    private static ArrayList<Cliente> clientes = new ArrayList<>();

    private static Cliente buscarClientePorRut(String rut) {
        for (Cliente c : clientes) {
            if (c.getRut().equals(rut)) {
                return c;
            }
        }
        return null;
    }

    private static int validarInt(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un valor numérico válido.");
            }
        }
    }

    private static String validarTexto(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            if (entrada.matches(".*\\d.*")) {
                System.out.println("Por favor, ingrese solo letras para este campo.");
            } else if (entrada.trim().isEmpty()) {
                System.out.println("El campo no puede estar vacío.");
            } else {
                return entrada;
            }
        }
    }

    private static String validarTelefono(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            if (entrada.matches("\\d{7,15}")) {
                return entrada;
            } else {
                System.out.println("Ingrese solo números (entre 7 y 15 dígitos) para el teléfono y sin código de área, por favor.");
            }
        }
    }

    private static String validarRut(Scanner scanner) {
        while (true) {
            System.out.print("RUT (incluya puntos y guión): ");
            String rut = scanner.nextLine();
            if (rut.length() < 11 || rut.length() > 12) {
                System.out.println("El RUT debe tener entre 11 y 12 caracteres.");
            } else if (buscarClientePorRut(rut) != null) {
                System.out.println("El cliente con este RUT ya está registrado.");
            } else {
                return rut;
            }
        }
    }

    private static int mostrarMenuCuentas(Scanner scanner) {
        System.out.println("Seleccione el tipo de cuenta:");
        System.out.println("1. Cuenta Corriente");
        System.out.println("2. Cuenta de Ahorro");
        System.out.println("3. Cuenta de Crédito");
        return validarInt(scanner, "Ingrese opción: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos de cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Girar");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            opcion = validarInt(scanner, "Ingrese su opción: ");

            switch (opcion) {
                case 1: {
                    String rut = validarRut(scanner);
                    String nombre = validarTexto(scanner, "Nombre: ");
                    String apP = validarTexto(scanner, "Apellido Paterno: ");
                    String apM = validarTexto(scanner, "Apellido Materno: ");
                    System.out.print("Domicilio: ");
                    String domicilio = scanner.nextLine();
                    String comuna = validarTexto(scanner, "Comuna: ");
                    String telefono = validarTelefono(scanner, "Teléfono: ");
                    Cliente nuevoCliente = new Cliente(rut, nombre, apP, apM, domicilio, comuna, telefono);
                    clientes.add(nuevoCliente);
                    System.out.println("Cliente registrado con éxito.");
                    break;
                }
                case 2: {
                    System.out.print("Ingrese el RUT del cliente: ");
                    String rut = scanner.nextLine();
                    Cliente clienteMostrar = buscarClientePorRut(rut);
                    if (clienteMostrar == null) {
                        System.out.println("Cliente no encontrado.");
                    } else {
                        clienteMostrar.mostrarDatos();
                    }
                    break;
                }
                case 3: { // Depositar
                    System.out.print("Ingrese el RUT del cliente: ");
                    String rut = scanner.nextLine();
                    Cliente clienteDepositar = buscarClientePorRut(rut);
                    if (clienteDepositar == null) {
                        System.out.println("Cliente no encontrado.");
                    } else {
                        int tipo = mostrarMenuCuentas(scanner);
                        Cuenta cuenta = clienteDepositar.getCuentaPorTipo(tipo);
                        if (cuenta != null) {
                            int monto = validarInt(scanner, "Monto a depositar: ");
                            cuenta.depositar(monto);
                        } else {
                            System.out.println("Opción de cuenta inválida.");
                        }
                    }
                    break;
                }
                case 4: { // Girar
                    System.out.print("Ingrese el RUT del cliente: ");
                    String rut = scanner.nextLine();
                    Cliente clienteGirar = buscarClientePorRut(rut);
                    if (clienteGirar == null) {
                        System.out.println("Cliente no encontrado.");
                    } else {
                        int tipo = mostrarMenuCuentas(scanner);
                        Cuenta cuenta = clienteGirar.getCuentaPorTipo(tipo);
                        if (cuenta != null) {
                            int monto = validarInt(scanner, "Monto a girar: ");
                            cuenta.girar(monto);
                        } else {
                            System.out.println("Opción de cuenta inválida.");
                        }
                    }
                    break;
                }
                case 5: { // Consultar saldo
                    System.out.print("Ingrese el RUT del cliente: ");
                    String rut = scanner.nextLine();
                    Cliente clienteSaldo = buscarClientePorRut(rut);
                    if (clienteSaldo == null) {
                        System.out.println("Cliente no encontrado.");
                    } else {
                        int tipo = mostrarMenuCuentas(scanner);
                        Cuenta cuenta = clienteSaldo.getCuentaPorTipo(tipo);
                        if (cuenta != null) {
                            System.out.println("Saldo actual (" + cuenta.getTipo() + "): $" + cuenta.getSaldo());
                        } else {
                            System.out.println("Opción de cuenta inválida.");
                        }
                    }
                    break;
                }
                case 6:
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }
}
