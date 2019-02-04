/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author dam2
 */
public class Producto {

    private int idproducto;
    private String nombre;
    private String categoria;
    private int marca;
    private String modelo;
    private String descripcion;
    private int ubicacion;
    private int responsable;
    private double precio;
    private Date fecha_entrada;
    private Date fecha_salida;
    private int estado;

    public Producto() {
    }

    public Producto(int idproducto, String nombre, String categoria, int marca, String modelo, String descripcion, int ubicacion, int responsable, double precio, Date fecha_entrada, Date fecha_salida, int estado) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.responsable = responsable;
        this.precio = precio;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.estado = estado;
    }

    public Producto(String nombre, String categoria, int marca, String modelo, String descripcion, int ubicacion, int responsable, double precio, Date fecha_entrada, Date fecha_salida, int estado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.responsable = responsable;
        this.precio = precio;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.estado = estado;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Producto{" + "idproducto=" + idproducto + ", nombre=" + nombre + ", categoria=" + categoria + ", marca=" + marca + ", modelo=" + modelo + ", descripcion=" + descripcion + ", ubicacion=" + ubicacion + ", responsable=" + responsable + ", precio=" + precio + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", estado=" + estado + '}';
    }

}
