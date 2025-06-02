
package com.mycompany.bostonbanks2;

public abstract class Cuenta {
    protected int numero;
    protected int saldo;

    public Cuenta(int numero) {
        this.numero = numero;
        this.saldo = 0;
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