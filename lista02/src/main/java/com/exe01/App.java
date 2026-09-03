package com.exe01;

public class App{
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria();
        ContaBancaria destino = new ContaBancaria();
        conta.setTitular("Noah");
        System.out.println("\nTitular:"+conta.getTitular());

        conta.depositarSaldo(300);
        System.out.printf("\nSaldo:"+ conta.getSaldo());

        conta.sacarSaldo(200);
        System.out.printf("\nSaldo:"+ conta.getSaldo());

        conta.transferirSaldo(destino, 100);
        System.out.printf("\nSaldo conta:"+ conta.getSaldo());
        System.out.printf("\nSaldo destino:"+ destino.getSaldo());
    }
}