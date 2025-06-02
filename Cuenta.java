package com.mycompany.bostonbanks2;

import java.util.Random;

public abstract class Cuenta {
    protected int numero;
    protected int saldo;

    // Constructor con número aleatorio
    public Cuenta() {
        this.numero = generarNumeroCuentaAleatorio();
        this.saldo = 0;
    }

    // Constructor con número explícito
    public Cuenta(int numero) {
        this.numero = numero;
        this.saldo = 0;
    }

    // Constructor con número explícito y saldo inicial
    public Cuenta(int numero, int saldoInicial) {
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    // Generador de número aleatorio de 9 dígitos
    public static int generarNumeroCuentaAleatorio() {
        Random rand = new Random();
        return 100_000_000 + rand.nextInt(900_000_000);
    }

    public int getNumero() {
        return numero;
    }

    public int getSaldo() {
        return saldo;
    }

    public abstract void depositar(int monto);
    public abstract void girar(int monto);
    public abstract String getTipo();
}
