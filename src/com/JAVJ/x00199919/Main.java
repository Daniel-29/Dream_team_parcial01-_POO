package com.JAVJ.x00199919;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Empresa mi_empresa = new Empresa("Dream team S.A. de C.V.");

        String[] opciones = {"Plaza fija", "Servicios profesionales"};
        String[] opciones_sino = {"SI", "NO"};

        int opcion = 0;
        do{
            opcion = Menu();
            switch (opcion){
                case 1:
                    String nombres, puesto, documentos_msg, nombre_doc, num_doc;
                    int extension, meses_contrato;
                    double salario;

                    String tipo_empleado = SelectInputDialog("Seleccione el tipo de empleado a agregar:", opciones);
                    if(tipo_empleado.equals(opciones[0])){
                        //plaza fija
                        nombres = InputDialog("Ingrese el nombre completo:");
                        puesto = InputDialog("Ingrese el puesto:");
                        salario = Double.parseDouble(InputDialog("Ingrese el salario:"));
                        extension = Integer.parseInt(InputDialog("Ingrese el extension:"));
                        PlazaFija nuevo_empleado = new PlazaFija(nombres, puesto, salario, extension);
                        do{
                            documentos_msg = SelectInputDialog("Desea agregar documentos al empleado:", opciones_sino);
                            if(documentos_msg.equals(opciones_sino[0])){
                                nombre_doc = InputDialog("Ingrese el nombre del documento");
                                num_doc = InputDialog("Ingrese el numero del documento");
                                Documento nuevo_doc = new Documento(nombre_doc, num_doc);
                                nuevo_empleado.addDocumento(nuevo_doc);
                            }
                        }while(documentos_msg.equals(opciones_sino[0]));
                        mi_empresa.addEmpleados(nuevo_empleado);
                        Mensaje("Se agrego corectamente.");
                    }else{
                        //servicio profesional
                        nombres = InputDialog("Ingrese el nombre completo:");
                        puesto = InputDialog("Ingrese el puesto:");
                        salario = Double.parseDouble(InputDialog("Ingrese el salario:"));
                        meses_contrato = Integer.parseInt(InputDialog("Ingrese los meses del contrato:"));
                        ServicioProfesional nuevo_empleado = new ServicioProfesional(nombres, puesto, salario, meses_contrato);
                        do{
                            documentos_msg = SelectInputDialog("Desea agregar documentos al empleado:", opciones_sino);
                            if(documentos_msg.equals(opciones_sino[0])){
                                nombre_doc = InputDialog("Ingrese el nombre del documento");
                                num_doc = InputDialog("Ingrese el numero del documento");
                                Documento nuevo_doc = new Documento(nombre_doc, num_doc);
                                nuevo_empleado.addDocumento(nuevo_doc);
                            }
                        }while(documentos_msg.equals(opciones_sino[0]));
                        mi_empresa.addEmpleados(nuevo_empleado);
                        Mensaje("Se agrego corectamente.");
                    }
                    break;

                case 2:
                    String nombre = InputDialog("Ingrese el nombre del empleado a despedir:");
                    mi_empresa.quitEmpleados(nombre);
                    Mensaje("Se despidio al empleado corectamente.");
                    break;

                case 3:
                    String todo = "";
                    String docs = "";
                    for (int i = 0; i < mi_empresa.getEmpleados().size(); i++) {
                        Empleado empleado = mi_empresa.getEmpleados().get(i);

                        //los documentos del empleado
                        for (int j = 0; j < empleado.getDocumentos().size(); j++) {
                            Documento doc = empleado.getDocumentos().get(j);
                            docs = "{" +
                                    "Nombre: " + doc.getNombre() + ", " +
                                    "Numero: " + doc.getNumero() + "" +
                                "}";
                        }

                        //la info del empleado
                        todo += "{\n" +
                                "\tnombre: "+empleado.getNombre()+",\n" +
                                "\tpuesto: "+empleado.getPuesto()+",\n" +
                                "\ttipo: " + (empleado instanceof ServicioProfesional ? "Servicio Profesional" : "Plaza fija") + ",\n" +
                                "\tsalario: $"+empleado.getSalario()+" USD,\n" +
                                "\tsalario liquido: $"+CalculadoraImpuesto.CalcularPago(empleado) + " USD,\n" +
                                "\tdescuentos: \n"+ CalculadoraImpuesto.MostrarTotales() + "\n" +
                                "\tdocumentos: \n"+ docs + "\n"
                                +
                                "}\n";
                    }
                    Mensaje(todo);
                    break;
                case 4:
                    String nombre_busqueda = InputDialog("Ingrese el nombre del empleado a buscar:");
                    Empleado empleado_calcular = null;
                    for (int i = 0; i < mi_empresa.getEmpleados().size(); i++) {
                        Empleado empleado = mi_empresa.getEmpleados().get(i);
                        if(empleado.getNombre().equals(nombre_busqueda)){
                            empleado_calcular = empleado;
                            break;
                        }
                    }
                    if(empleado_calcular != null){
                        Double salario_liquido = CalculadoraImpuesto.CalcularPago(empleado_calcular);
                        Mensaje("El salario liquido es: $" + salario_liquido);
                    }else{
                        Mensaje("No ha sido encontrado ningun usuario.");
                    }
                    break;

                case 5:
                    String nombre_busqueda_totales = InputDialog("Ingrese el nombre del empleado a buscar:");
                    Empleado empleado_totales = null;
                    for (int i = 0; i < mi_empresa.getEmpleados().size(); i++) {
                        Empleado empleado = mi_empresa.getEmpleados().get(i);
                        if(empleado.getNombre().equals(nombre_busqueda_totales)){
                            empleado_totales = empleado;
                            break;
                        }
                    }
                    if(empleado_totales != null){
                        Double salario_liquido = CalculadoraImpuesto.CalcularPago(empleado_totales);
                        String descuentos = CalculadoraImpuesto.MostrarTotales();

                        Mensaje("El salario liquido es: $" + String.format("%.2f", salario_liquido) + "\nDescuentos:"+descuentos);
                    }else{
                        Mensaje("No ha sido encontrado ningun empleado.");
                    }
                    break;

                case 6:  break;

                default:
                    Mensaje("Opcion invalida.");
                    break;
            }
        }while(opcion != 6);
    }

    public static int Menu(){
        String msg = "MENU\n" +
                "1) Agregar empleado.\n" +
                "2) Despedir empleado.\n" +
                "3) Ver lista de empleados.\n" +
                "4) Calcular sueldo.\n" +
                "5) Mostrar totales.\n" +
                "6) Salir.";
        return Integer.parseInt(JOptionPane.showInputDialog(msg));
    }

    //como un combobox
    public static String SelectInputDialog(String mensaje, String[] opciones){
        return (String) JOptionPane.showInputDialog(null, mensaje, "Selecione una opcion", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }

    //para los inputs normales
    public static String InputDialog(String mensaje){
        return (String) JOptionPane.showInputDialog(null, mensaje, "Informacion", JOptionPane.QUESTION_MESSAGE);
    }

    //para mostrar mensajes al usuario
    public  static void Mensaje(String mensaje){
        JOptionPane.showConfirmDialog(null, mensaje, "Mensaje", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
}
