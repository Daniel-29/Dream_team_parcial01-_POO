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
                        nombres = InputDialog("Ingrese el nombre completo:",false);
                        puesto = InputDialog("Ingrese el puesto:",false);
                        salario = Double.parseDouble(InputDialog("Ingrese el salario:",true));
                        extension = Integer.parseInt(InputDialog("Ingrese el extension:",true));
                        PlazaFija nuevo_empleado = new PlazaFija(nombres, puesto, salario, extension);
                        do{
                            documentos_msg = SelectInputDialog("Desea agregar documentos al empleado:", opciones_sino);
                            if(documentos_msg.equals(opciones_sino[0])){
                                nombre_doc = InputDialog("Ingrese el nombre del documento",false);
                                num_doc = InputDialog("Ingrese el numero del documento",false);
                                Documento nuevo_doc = new Documento(nombre_doc, num_doc);
                                nuevo_empleado.addDocumento(nuevo_doc);
                            }
                        }while(documentos_msg.equals(opciones_sino[0]));
                        mi_empresa.addEmpleados(nuevo_empleado);
                        Mensaje("Se agrego corectamente.");
                    }else{
                        //servicio profesional
                        nombres = InputDialog("Ingrese el nombre completo:",false);
                        puesto = InputDialog("Ingrese el puesto:",false);
                        salario = Double.parseDouble(InputDialog("Ingrese el salario:",true));
                        meses_contrato = Integer.parseInt(InputDialog("Ingrese los meses del contrato:",true));
                        ServicioProfesional nuevo_empleado = new ServicioProfesional(nombres, puesto, salario, meses_contrato);
                        do{
                            documentos_msg = SelectInputDialog("Desea agregar documentos al empleado:", opciones_sino);
                            if(documentos_msg.equals(opciones_sino[0])){
                                nombre_doc = InputDialog("Ingrese el nombre del documento",false);
                                num_doc = InputDialog("Ingrese el numero del documento",false);
                                Documento nuevo_doc = new Documento(nombre_doc, num_doc);
                                nuevo_empleado.addDocumento(nuevo_doc);
                            }
                        }while(documentos_msg.equals(opciones_sino[0]));
                        mi_empresa.addEmpleados(nuevo_empleado);
                        Mensaje("Se agrego corectamente.");
                    }
                    break;

                case 'D':
                    String nombre = InputDialog("Ingrese el nombre del empleado a despedir:",false);
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
                    String nombre_busqueda = InputDialog("Ingrese el nombre del empleado a buscar:",false);
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
                    String nombre_busqueda_totales = InputDialog("Ingrese el nombre del empleado a buscar:",false);
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
       char r ='S';
        String[] opciones = {
                "Agregar empleado.",
                "Despedir empleado.",
                "Ver lista de empleados.",
                "Calcular sueldo.",
                "Mostrar totales.",
                "Salir."
        };
        try {
           r= SelectInputDialog("MENU", opciones).charAt(0);
        }catch (Exception e){
            Mensaje("Se cerro");
        }
        return r;
    }

    //como un combobox
    public static String SelectInputDialog(String mensaje, String[] opciones){
        do {
            try {
               String r = (String) JOptionPane.showInputDialog(null, mensaje, "Selecione una opcion", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
               r.equals("");
                return  r;
            }catch (Exception e){
                Mensaje("Opcion invalidad");
            }
        }while (true);
    }

    //para los inputs normales
    public static String InputDialog(String mensaje,Boolean numeric){
        String[] options = {"Aceptar"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel(mensaje);
        JTextField txt = new JTextField(100);
        panel.add(lbl);
        panel.add(txt);
        do{
            int selectedOption = JOptionPane.showOptionDialog(null, panel, "Informacion", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
            if(selectedOption == 0){
                if(numeric){
                   try {
                     Integer.parseInt(txt.getText().trim());
                     return txt.getText().trim();
                   }catch (Exception e){
                       try {
                           Double.parseDouble(txt.getText().trim());
                           return txt.getText().trim();
                       }catch (Exception a){
                           Mensaje("Entrada de datos invalida.");
                       }
                   }
                }else {
                    return txt.getText();
                }
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
