package com.JAVJ.x00199919;

import java.util.ArrayList;

public class Empresa {
    private  String nombre;
    private ArrayList<Empleado> planilla;

    public Empresa(String nombre) {
        this.nombre = nombre;
        planilla = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Empleado> getPlanilla() {
        return planilla;
    }

    public void addEmpleados(Empleado empleado) {
       planilla.add(empleado);

    }
    public void quitEmpleados(String nombre) {
        planilla.removeIf(e -> (e.getNombre().equals(nombre)));
    }
}
