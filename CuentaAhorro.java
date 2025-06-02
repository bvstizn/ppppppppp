package com.mycompany.bostonbanks2;

public class CuentaAhorro extends Cuenta {
    public CuentaAhorro() {
        super();
    }

    public CuentaAhorro(int numero) {
        super(numero);
    }

    public CuentaAhorro(int numero, int saldoInicial) {
        super(numero, saldoInicial);
    }

    @Override
    public void depositar(int monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito realizado en cuenta de ahorro. Saldo actual: $" + saldo);
        } else {
            System.out.println("El monto debe ser mayor a cero.");
        }
    }

    @Override
    public void girar(int monto) {
        if (monto <= 0) {
            System.out.println("El monto del giro debe ser mayor a cero.");
        } else if (monto > saldo) {
            System.out.println("No se puede girar más dinero del que tiene en el saldo.");
        } else {
            saldo -= monto;
            System.out.println("Giro realizado en cuenta de ahorro. Saldo actual: $" + saldo);
        }
    }

    @Override
    public String getTipo() {
        return "Cuenta de Ahorro";
    }
}
