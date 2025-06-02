package com.mycompany.bostonbanks2;

import java.util.ArrayList;

public class Cliente {
    private String rut;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String domicilio;
    private String comuna;
    private String telefono;
    private ArrayList<Cuenta> cuentas;

    public Cliente(String rut, String nombre, String apellidoP, String apellidoM, String domicilio, String comuna, String telefono, int numeroCuentaBase) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;
        this.cuentas = new ArrayList<>();
        // Crea las tres cuentas al registrar el cliente
        cuentas.add(new CuentaCorriente(numeroCuentaBase + 1));
        cuentas.add(new CuentaAhorro(numeroCuentaBase + 2));
        cuentas.add(new CuentaCredito(numeroCuentaBase + 3));
    }

    public String getRut() {
        return rut;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public Cuenta getCuentaPorTipo(int tipo) {
        if (tipo >= 1 && tipo <= cuentas.size()) {
            return cuentas.get(tipo - 1);
        }
        return null;
    }

    public void mostrarDatos() {
        System.out.println("----- Datos del Cliente -----");
        System.out.println("Rut: " + rut);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido Paterno: " + apellidoP);
        System.out.println("Apellido Materno: " + apellidoM);
        System.out.println("Domicilio: " + domicilio);
        System.out.println("Comuna: " + comuna);
        System.out.println("Teléfono: " + telefono);
        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta.getTipo() + " - N°: " + cuenta.getNumero() + " - Saldo: $" + cuenta.getSaldo());
        }
    }
}