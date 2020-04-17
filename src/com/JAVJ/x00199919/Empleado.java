package com.JAVJ.x00199919;

import java.util.ArrayList;

public abstract class Empleado {
    private String nombre,puesto;
    private ArrayList<Documento> documentos;
    private double salario;

    public Empleado(String nombre, String puesto, double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        documentos =new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public void addDocumento(Documento documento){
        documentos.add(documento);
    }
    public void removeDocumento(String nomDoc){
        documentos.removeIf(documento -> (documento.getNombre().equals(nomDoc)) );
    }
}
