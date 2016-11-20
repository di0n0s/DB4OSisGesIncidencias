/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB4OSisGesIncidencias;

import com.sun.istack.internal.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class TestDB4O {
    
    public static void main (String [] args){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora

        IncidenciasDB4O gestor = new IncidenciasDB4O();
        Empleado e1 = new Empleado("emartinez", "nat654", "Ezequiel Martínez Palomera", "+34963846217");
        Empleado e2 = new Empleado("nnavarro", "muh665", "Natalia Navarro Fernández", "+34654852953");
        Historial h = new Historial("C", sdf.format(new Date()), e1);
        Incidencia i2 = new Incidencia(2, sdf.format(new Date()), e2, e1, "Urgente", "La impresora no tiene tóner");
        
        //Prueba de Insertar Empleado
        System.out.println("---------------------");
        System.out.println("2A. Insertar un empleado nuevo en la B.D.");
        System.out.println("Insertando Empleado "+e1.getUsername()+" ...");
        if (gestor.insertarEmpleado(e1)){
            System.out.println("Empleado insertado!");
        } else System.out.println("El empleado no se ha podido insertar");
        System.out.println("---------------------");

        
        //ValidarEmpleado
        System.out.println("---------------------");
        System.out.println("2B. Validar la entrada de un empleado (suministrando usuario y contraseña)");
        System.out.println("Validando Empleado "+e1.getUsername()+" ...");
        gestor.validarEmpleado(e1);
        System.out.println("---------------------");
        
        //Prueba de Modificar Empleado
        System.out.println("-----------------------");
        System.out.println("2C. Modificar empleado");
        Empleado eModificado;
        try {
            System.out.println("Modificando Empleado "+e1.getUsername()+" ...");
            eModificado = (Empleado) e1.clone(); //Clonamos e1 en eModificado
            eModificado.setNombreCompleto("Emilio Martínez López");
            eModificado.setPassword("mia874");
            eModificado.setTelefono("+34963846214");
            gestor.modificarEmpleado(e1, eModificado);
            System.out.println("Empleado Modificado");
            System.out.println("Los datos del Empleado se han actualizado a: "+eModificado);
        } catch (CloneNotSupportedException ex){
            System.out.println(ex.getMessage());
            //Logger.getLogger(IncidenciasDB4O.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-----------------------");
        
        //Prueba de modificar password
        System.out.println("-----------------------");
        System.out.println("2D. Cambiar la contraseña de un Empleado existente");
        Empleado eModificaPassword;
        try {
            System.out.println("Modificando Password de "+e1.getUsername()+" ...");
            eModificaPassword = (Empleado) e1.clone(); //Clonamos e1 en eModificado
            eModificaPassword.setPassword("mpq621");
            gestor.modificarPassword(e1, eModificaPassword);
            System.out.println("Password Modificado");
            System.out.println("El Password de "+e1.getUsername()+" se han actualizado a: "+eModificaPassword.getPassword());
        } catch (CloneNotSupportedException ex){
            System.out.println(ex.getMessage());
            //Logger.getLogger(IncidenciasDB4O.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-----------------------");
        
        //Eliminar Empleado
        System.out.println("---------------------");
        System.out.println("2E. Eliminar un Empleado existente");
        System.out.println("Eliminando Empleado "+e1.getUsername()+" ...");
        if (gestor.eliminarEmpleado(e1)){
            System.out.println("Empleado Eliminado!");
        } else System.out.println("El empleado no se ha podido eliminar");
        System.out.println("---------------------");
        
        //Prueba de Insertar una incidencia a partir de un objeto de clase Incidencia
        Incidencia i1 = new Incidencia(1, sdf.format(new Date()), e1, e2, "Urgente", "La impresora no tiene tóner");
        System.out.println("---------------------");
        System.out.println("3C. Insertar una incidencia a partir de un objeto de clase Incidencia definido adecuadamente según los campos que presenta (incluido el empleado que la origina y el empleado destino).");
        System.out.println("Insertando Incidencia "+i1.getIdIncidencia()+" ...");
        if (gestor.insertarIncidencia(i1)){
            System.out.println("Incidencia insertada!");
        } else System.out.println("La incidencia no se ha podido insertar");
        System.out.println("---------------------");
        
        //Obtener un objeto incidencia a partir de su ID
        System.out.println("--------------------");
        System.out.println("3A. Obtener un objeto incidencia a partir de su ID");
        System.out.println("Consultar la incidencia para la ID: "+i1.getIdIncidencia());
        Incidencia consultada = gestor.consultarIncidenciaByID(i1);
        System.out.println(consultada);
        System.out.println("--------------------");
        
        gestor.insertarIncidencia(i2);
        
        //Obtener la lista de todas las incidencias.
        System.out.println("--------------------");
        System.out.println("3B. Obtener la lista de todas las incidencias");
        System.out.println("Listado de Incidencias");
        List<Incidencia> todasLasIncidencias = gestor.selectAllIncidencias();
        for (Incidencia incidenciaActual : todasLasIncidencias){
            System.out.println(incidenciaActual);
        }       
        System.out.println("--------------------");
        
        //3D. Obtener las incidencias para un empleado a partir de un objeto de clase Empleado.
        System.out.println("--------------------");
        System.out.println("3D. Obtener las incidencias para un empleado a partir de un objeto de clase Empleado.");
        System.out.println("Listado de Incidencias para el empleado "+e1.getUsername());
        List<Incidencia> todasLasIncidenciasParaEmpleado = gestor.getAllIncidenciasParaEmpleado(e1);
        for (Incidencia incidenciaActual : todasLasIncidenciasParaEmpleado){
            System.out.println(incidenciaActual);
        }       
        System.out.println("--------------------");        
        
        //3E. Obtener las incidencias creadas por un empleado concreto.        
        System.out.println("--------------------");
        System.out.println("3E. Obtener las incidencias creadas por un empleado concreto.");
        System.out.println("Listado de Incidencias creadas el empleado "+e1.getUsername());
        List<Incidencia> todasLasIncidenciasPorEmpleado = gestor.getAllIncidenciasByOrigen(e1);
        for (Incidencia incidenciaActual : todasLasIncidenciasPorEmpleado){
            System.out.println(incidenciaActual);
        }       
        System.out.println("--------------------");
        
        //4B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto.
        System.out.println("--------------------");
        System.out.println("4B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto.");
        String ultimoLoginEmpleado = gestor.getFechaHoraUltimoLogin(e1);
        System.out.println("El último Login del Empleado "+e1.getUsername()+" fue: "+ultimoLoginEmpleado);
        System.out.println("--------------------");         
        
        gestor.cerrar();
        

   
        
        
    }
        
    }

// ESTO VA FUERA DE LA ACTIVIDAD (NO DESCOMENTAR)

/*Como 
SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
String fechaHoraenTexto = sdf.format(ahora); //Creamos un string indicando al sdf que mofique el formato del dato
System.out.println(fechaHoraenTexto); //Lo imprimimos por pantalla 
*/

//        //Consultar todos los empleados
//        System.out.println("Listado de Empleados");
//        System.out.println("--------------------");
//        List<Empleado> todosLosEmpleados = gestor.selectAllEmpleados();
//        for (Empleado empleadoActual : todosLosEmpleados){
//            System.out.println(empleadoActual);
//        }
        
 
//        //Consultar todos los empleados ordenados por nombre
//        System.out.println("-------------------------------------------");
//        System.out.println("Listado de Empleados ordenados por username");
//        List<Empleado> todosLosEmpleados = gestor.empleadosOrdenadosPorNombre();
//        for (Empleado empleadoActual : todosLosEmpleados){
//            System.out.println(empleadoActual);
//        }
//        System.out.println("-------------------------------------------");