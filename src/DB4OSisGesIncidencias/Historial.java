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
public class Historial {
    
    private String tipoEvento;
    private String fechaHora;
    private Empleados username;

    public Historial() {
        tipoEvento = "";
        fechaHora = "";
        username = new Empleados();
    }

    public Historial(String tipoEvento, String fechaHora, Empleados username) {
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
        this.username = username;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Empleados getUsername() {
        return username;
    }

    public void setUsername(Empleados username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Historial{" + "tipoEvento=" + tipoEvento + ", fechaHora=" + fechaHora + ", username=" + username + '}';
    }





    
}
