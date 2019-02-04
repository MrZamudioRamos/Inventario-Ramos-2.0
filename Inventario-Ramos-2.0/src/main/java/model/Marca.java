/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dam2
 */
public class Marca {

    private int idmarca;
    private String nombre;
    private String descripcion;

    public Marca() {
    }
    

    public Marca(int idmarca, String nombre, String descripcion) {
        this.idmarca = idmarca;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Marca(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Marca{" + "idmarca=" + idmarca + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
