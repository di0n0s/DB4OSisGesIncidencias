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
public class Empleados {
    
    private String username;
    private String password;
    private String nombreCompleto;
    private String telefono;

    public Empleados() {
        username = "";
        password = "";
        nombreCompleto = "";
        telefono = "";
    }

    public Empleados(String username) {
        this.username = username;
    }

    public Empleados(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Empleados(String username, String nombreCompleto, String telefono) {
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }

    public Empleados(String username, String password, String nombreCompleto, String telefono) {
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

    @Override //Sobreescribir el método .toString para que muestre lo que queramos.
    public String toString() {
        return username;
    }
    
}
