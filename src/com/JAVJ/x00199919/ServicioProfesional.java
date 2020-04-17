package com.JAVJ.x00199919;

public class ServicioProfesional extends Empleado {
    private int MesesContrato;

    public ServicioProfesional(String nombre, String puesto, double salario, int mesesContrato) {
        super(nombre, puesto, salario);
        MesesContrato = mesesContrato;
    }

    public int getMesesContrato() {
        return MesesContrato;
    }

    public void setMesesContrato(int mesesContrato) {
        MesesContrato = mesesContrato;
    }
}
