package com.JAVJ.x00199919;

public final class CalculadoraImpuesto {
    private static Double totalRenta = 0.0, totalISSS = 0.0 , totalAFP = 0.0;

    private CalculadoraImpuesto() {

    }
    public static double CalcularPago(Empleado empleado){
        double pago = 0,salario=empleado.getSalario();
        if(empleado instanceof ServicioProfesional){
            totalRenta =salario*0.1;
            pago= salario- totalRenta;
        }else if(empleado instanceof PlazaFija){
            totalISSS = salario*totalISSS;
            totalAFP = salario*totalAFP;
            double restante =  salario - totalISSS - totalAFP;
            if(restante <= 472 && restante >=0.01){
                totalRenta=0.0;
            }else if(restante <= 895.24 && restante >=472.01){
                totalRenta = 0.1*(restante-472)+17.67;
            }else if(restante <= 2038.10 && restante >=895.25){
                totalRenta = 0.2*(restante-895.24)+60;
            }else if(restante >=2038.11){
                totalRenta = 0.3*(restante-2038.10)+288.57;
            }
            pago = restante - totalRenta;
        }
        return pago;
    }
    public static String MostrarTotales(){
        return "{" +
                "Renta: $" + totalRenta +" USD, "+
                "ISSS: $" + totalISSS +" USD, "+
                "AFP: $" + totalAFP +" USD"+
                "}";
    }

}