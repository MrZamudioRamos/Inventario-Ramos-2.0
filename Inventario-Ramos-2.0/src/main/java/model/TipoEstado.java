package model;

public class TipoEstado {
    
    private int idtipoestado;
    private String descripcion;

    public TipoEstado() {
    }

    public TipoEstado(int idtipoestado, String descripcion) {
        this.idtipoestado = idtipoestado;
        this.descripcion = descripcion;
    }

    public int getIdtipoestado() {
        return idtipoestado;
    }

    public void setIdtipoestado(int idtipoestado) {
        this.idtipoestado = idtipoestado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoEstado{" + "idtipoestado=" + idtipoestado + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
