package com.mycompany.bostonbanks2;

public class CuentaCredito extends Cuenta {
    private int cupoCredito = 500000; // Cupo de ejemplo

    public CuentaCredito() {
        super();
        this.saldo = cupoCredito;
    }

    public CuentaCredito(int numero) {
        super(numero);
        this.saldo = cupoCredito;
    }

    public CuentaCredito(int numero, int saldoInicial) {
        super(numero, saldoInicial);
        if (saldoInicial > cupoCredito) this.saldo = cupoCredito;
    }

    @Override
    public void depositar(int monto) {
        if (monto > 0) {
            saldo += monto;
            if (saldo > cupoCredito) saldo = cupoCredito;
            System.out.println("Pago realizado a cuenta de crédito. Cupo disponible: $" + saldo);
        } else {
            System.out.println("El monto debe ser mayor a cero.");
        }
    }

    @Override
    public void girar(int monto) {
        if (monto <= 0) {
            System.out.println("El monto del avance debe ser mayor a cero.");
        } else if (monto > saldo) {
            System.out.println("No puede girar más que el cupo disponible.");
        } else {
            saldo -= monto;
            System.out.println("Avance realizado en cuenta de crédito. Cupo disponible: $" + saldo);
        }
    }

    @Override
    public String getTipo() {
        return "Cuenta de Crédito";
    }
}
