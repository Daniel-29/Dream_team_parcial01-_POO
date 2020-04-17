package com.JAVJ.x00199919;

public class PlazaFija extends Empleado{
    private int Extencion;

    public PlazaFija(String nombre, String puesto, double salario, int extencion) {
        super(nombre, puesto, salario);
        Extencion = extencion;
    }

    public int getExtencion() {
        return Extencion;
    }

    public void setExtencion(int extencion) {
        Extencion = extencion;
    }
}
