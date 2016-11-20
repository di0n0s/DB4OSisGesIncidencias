/*
 * Código de acceso a la BBDD
 */
package DB4OSisGesIncidencias;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class IncidenciasDB4O {
    
    private ObjectContainer db; //Atributo para la persistencia

    public IncidenciasDB4O() {
        db = Db4oEmbedded.openFile("DB4OSisGesIncidencias");
        
    }
    
    //2A. Insertar un empleado nuevo en la B.D. (FUNCIONA)
    public boolean insertarEmpleado(Empleado e1) {
        try {
            db.store(e1);
            return true;
        } catch (Db4oException ex) {
            System.out.println("Error: "+ex.getMessage());
            return false;
        }
    }
    
    //2B. Validar la entrada de un empleado (suministrando usuario y contraseña) (FUNCIONA)
    public boolean validarEmpleado(Empleado e1){
        ObjectSet resultado = db.queryByExample(e1);
        Empleado elEmpleado = (Empleado) resultado.next();
        if(e1.getUsername() == elEmpleado.getUsername() && e1.getPassword() == elEmpleado.getPassword()){
            System.out.println("Empleado validado!");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
            Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
            String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
            Historial h = new Historial("I", fechaHoraenTexto, e1);
            db.store(h);
            System.out.println("--Se ha insertado en el historial ESTA validación del usuario: '"+e1.getUsername()+"'--");
            return true;
        } else {
            System.out.println("El empleado "+e1.getUsername()+" no existe en la BDOO");
            return false;
        }
        
    }
    
    //2C. Modificar empleado (FUNCIONA) (es necesario trar el objeto de la BBDD)
    public void modificarEmpleado (Empleado e1, Empleado eModificado){
        ObjectSet resultado = db.queryByExample(e1);//Trae uno igual que este
        Empleado elEmpleado = (Empleado) resultado.next(); //Cogo el empleado(resultado)
        //que me devuelve y lo meto en "elEmpleado"
        elEmpleado.setPassword(eModificado.getPassword()); //Modifico elEmpleado para meterle los datos nuevos
        elEmpleado.setNombreCompleto(eModificado.getNombreCompleto());
        elEmpleado.setTelefono(eModificado.getTelefono());
        db.store(elEmpleado);

    }
    
    //2D. Cambiar la contraseña de un Empleado existente (FUNCIONA)
    public void modificarPassword (Empleado e1, Empleado eModificaPassword){
        ObjectSet resultado = db.queryByExample(e1);//Trae uno igual que este
        Empleado elEmpleado = (Empleado) resultado.next(); //Cogo el empleado(resultado)
        //que me devuelve y lo meto en "elEmpleado"
        elEmpleado.setPassword(eModificaPassword.getPassword()); //Modifico elEmpleado para meterle el password nuevo
        db.store(elEmpleado);

    }
    
    //2E. Elimiar Empleado (FUNCIONA)
    public boolean eliminarEmpleado (Empleado e1){ 
        try {
            db.delete(e1);
            return true;
        } catch (Db4oException ex) {
            System.out.println("Error: "+ex.getMessage());
            return false;
        }
        
    }
    
    //3A. Obtener un objeto Incidencia a partir de su Id. (FUNCIONA)
    public Incidencia consultarIncidenciaByID (Incidencia i1){
        ObjectSet resultado = db.queryByExample(i1);
        Incidencia laIncidencia = (Incidencia) resultado.next();
        return laIncidencia;   
    } 
    
    //3B. Obtener la lista de todas las incidencias (FUNCIONA)
    public List<Incidencia> selectAllIncidencias() {
        List<Incidencia> todasLasIncidencias = new ArrayList<>();
        Query consulta = db.query();
        consulta.constrain(Incidencia.class);
        ObjectSet resultado = consulta.execute();
        while (resultado.hasNext()) {
            Incidencia i = (Incidencia) resultado.next();
            todasLasIncidencias.add(i); 
        }
        return todasLasIncidencias;
    }    
    
    //3C. Insertar una incidencia a partir de un objeto de clase Incidencia definido adecuadamente según los campos que presenta (incluido el empleado que la origina y el empleado destino). (FUNCIONA)
    public boolean insertarIncidencia(Incidencia i1) {
        try {
            db.store(i1);
            if (i1.getTipo()=="Urgente"){
            System.out.println("Se ha insertado una Incidencia Urgente!");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
            Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
            String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
            Historial h = new Historial("U", fechaHoraenTexto, i1.getOrigen());
            db.store(h);
            System.out.println("--Se ha insertado en el historial ESTA incidencia Urgente: '"+i1+"'--");                
            }
            return true;  
        } catch (Db4oException ex) {
            System.out.println("Error: "+ex.getMessage());
            return false;
        }
    }    
    
    //3D. Obtener las incidencias para un empleado a partir de un objeto de clase Empleado (FUNCIONA)
    public List<Incidencia> getAllIncidenciasParaEmpleado(Empleado e1) {
        List<Incidencia> todasLasIncidencias = new ArrayList<>();
        Query consulta = db.query();
        consulta.constrain(Incidencia.class);
        ObjectSet resultado = consulta.execute();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
        Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
        String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
        Historial h = new Historial("C", fechaHoraenTexto, e1);
        db.store(h);
        System.out.println("--Se ha insertado en el historial ESTA consulta de las Incidencias PARA el empleado '"+e1.getUsername()+"'--");
        while (resultado.hasNext()) {
            Incidencia i = (Incidencia) resultado.next();
            if(i.getDestino()==e1){
                todasLasIncidencias.add(i);
            }
        }
        return todasLasIncidencias;
    }       
    
    //3E. Obtener las incidencias creadas por un empleado concreto (FUNCIONA)
    public List<Incidencia> getAllIncidenciasByOrigen(Empleado e1) {
        List<Incidencia> todasLasIncidencias = new ArrayList<>();
        Query consulta = db.query();
        consulta.constrain(Incidencia.class);
        ObjectSet resultado = consulta.execute();
        while (resultado.hasNext()) {
            Incidencia i = (Incidencia) resultado.next();
            if(i.getOrigen()==e1){
                todasLasIncidencias.add(i);
            }
        }
        return todasLasIncidencias;
    }    
    
    //4B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto (FUNCIONA)
    public String getFechaHoraUltimoLogin(Empleado e1){
        Query consulta = db.query();
        consulta.constrain(Historial.class);
        ObjectSet resultado = consulta.execute();
        List<Historial> listaHistorial = new ArrayList<>();
        while (resultado.hasNext()) {
            Historial h = (Historial) resultado.next();
            if(h.getUsername()==e1){
                listaHistorial.add(h);
            }
            
        }
        if(listaHistorial.size()>0){ //Si la lista es mayor que 0, es decir si existe algún valor
            return listaHistorial.get(listaHistorial.size()-1).getFechaHora();
            //Devuelve la más reciente
        } else return null;        
    }
    
    public void cerrar(){
        db.close();
    }    
    
    
    
    
//  //ESTO VA FUERA DE LA ACTIVIDAD (NO DESCOMENTAR)    
    
//    public List<Empleado> empleadosOrdenadosPorNombre(){
//        List<Empleado> todosLosEmpleados = selectAllEmpleados();
//        Collections.sort(todosLosEmpleados);
//        return todosLosEmpleados;
//    }
//    
//    public List<Empleado> empleadosOrdenadosPorNombreInvertido(){
//        List<Empleado> todosLosEmpleados = selectAllEmpleados();
//        Collections.sort(todosLosEmpleados, Collections.reverseOrder());//Lo mismo pero con el orden invertido
//        return todosLosEmpleados;
//    }
            
}
