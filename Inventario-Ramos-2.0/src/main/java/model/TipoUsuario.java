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
public class TipoUsuario {
    private int idtipousuario;
    private String descripcion;

    public TipoUsuario() {
    }
    

    public TipoUsuario(int idtipousuario, String descripcion) {
        this.idtipousuario = idtipousuario;
        this.descripcion = descripcion;
    }

    public int getIdTipoUsuario() {
        return idtipousuario;
    }

    public void setIdTipoUsuario(int idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" + "idtipousuario=" + idtipousuario + ", descripcion=" + descripcion + '}';
    }
    
}
