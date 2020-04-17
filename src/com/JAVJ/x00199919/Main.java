package com.JAVJ.x00199919;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Empresa mi_empresa = new Empresa("Dream team S.A. de C.V.");

        String[] opciones = {"Plaza fija", "Servicios profesionales"};
        String[] opciones_sino = {"SI", "NO"};

        char opcion = 'S';
        do{
            opcion = Menu();
            switch (opcion){
                case 'A':
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

                case 'D':
                    String nombre = InputDialog("Ingrese el nombre del empleado a despedir:");
                    mi_empresa.quitEmpleados(nombre);
                    Mensaje("Se despidio al empleado corectamente.");
                    break;

                case 'V':
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
                case 'C':
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

                case 'M':
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

                case 'S':  break;

                default:
                    Mensaje("Opcion invalida.");
                    break;
            }
        }while(opcion != 'S');
    }

    public static char Menu(){
        String[] opciones = {
                "Agregar empleado.",
                "Despedir empleado.",
                "Ver lista de empleados.",
                "Calcular sueldo.",
                "Mostrar totales.",
                "Salir."
        };
        return SelectInputDialog("MENU", opciones).charAt(0);
    }

    //como un combobox
    public static String SelectInputDialog(String mensaje, String[] opciones){
        return (String) JOptionPane.showInputDialog(null, mensaje, "Selecione una opcion", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }

    //para los inputs normales
    public static String InputDialog(String mensaje){
        String[] options = {"Aceptar"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel(mensaje);
        JTextField txt = new JTextField(100);
        panel.add(lbl);
        panel.add(txt);
        do{
            int selectedOption = JOptionPane.showOptionDialog(null, panel, "Informacion", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
            if(selectedOption == 0){
                return txt.getText();
            }else{
                Mensaje("Entrada de datos invalida.");
            }
        }while(true);
    }

    //para mostrar mensajes al usuario
    public  static void Mensaje(String mensaje){
        JOptionPane.showConfirmDialog(null, mensaje, "Mensaje", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
}
