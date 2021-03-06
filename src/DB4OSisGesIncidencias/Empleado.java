/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB4OSisGesIncidencias;

/**
 *
 * @author sfcar
 */
public class Empleado implements Cloneable, Comparable<Empleado> {
    
    private String username;
    private String password;
    private String nombreCompleto;
    private String telefono;

    public Empleado() {
        username = "";
        password = "";
        nombreCompleto = "";
        telefono = "";
    }

    public Empleado(String username) {
        this.username = username;
    }

    public Empleado(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Empleado(String username, String nombreCompleto, String telefono) {
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }

    public Empleado(String username, String password, String nombreCompleto, String telefono) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }
    
    
        
    public String getUsername() {
            return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empleado{" + "username=" + username + ", password=" + password + ", nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Empleado t) { 
        return username.compareTo(t.getUsername());
        //Esto se hace para que 2 objetos empleados sean comparables por su username
    }

    
    
}
