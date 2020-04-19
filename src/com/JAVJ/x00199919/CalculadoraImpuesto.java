package com.JAVJ.x00199919;

import java.util.ArrayList;

public final class CalculadoraImpuesto {
    private static Double totalRenta = 0.0, totalISSS = 0.0 , totalAFP = 0.0;
    private static Double isss=0.0, afp=0.0, renta=0.0,restante=0.0;

    private CalculadoraImpuesto() {}
    public static double CalcularPago(Empleado empleado){
        isss=0.0; afp=0.0; renta=0.0;restante=0.0;
        double pago = 0,salario=empleado.getSalario();
        if(empleado instanceof ServicioProfesional){
            renta +=salario*0.1;
            pago= salario- salario*0.1;
        }else if(empleado instanceof PlazaFija){
            isss = salario*0.03;
            afp = salario*0.0625;
            restante =  salario - isss - afp;
            if(restante <= 472 && restante >=0.01){
                renta=0.0;
            }else if(restante <= 895.24 && restante >=472.01){
                renta = 0.1*(restante-472)+17.67;
            }else if(restante <= 2038.10 && restante >=895.25){
                renta = 0.2*(restante-895.24)+60;
            }else if(restante >=2038.11){
                renta = 0.3*(restante-2038.10)+288.57;
            }
            pago = restante - renta;
        }
        return pago;
    }
    public static void updateTotales(){
        totalRenta +=renta;
        totalAFP +=afp;
        totalISSS +=isss;
    }
    public static String MostrarTotales(){
        return "{" +
                "Renta: $" + String.format("%.2f", totalRenta)  +" USD, "+
                "ISSS: $" +  String.format("%.2f", totalISSS)  +" USD, "+
                "AFP: $" + String.format("%.2f", totalAFP)  +" USD"+
                "}";
    }

}