package com.JAVJ.x00199919;

import java.util.ArrayList;

public class Empresa {
    private  String nombre;
    private ArrayList<Empleado> empleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        empleados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void addEmpleados(Empleado empleado) {
       empleados.add(empleado);

    }
    public void quitEmpleados(String nombre) {
        empleados.removeIf(e -> (e.getNombre().equals(nombre)));
    }
}
