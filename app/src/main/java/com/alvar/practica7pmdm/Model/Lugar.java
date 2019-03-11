package com.alvar.practica7pmdm.Model;

public class Lugar
{
    private Long id;
    private String nombre;
    private Float latitud;
    private Float longitud;
    private String comentarios;
    private Float valoracion;
    private Integer categoria;

    public Lugar() {
        this.id = null;
        this.nombre = "";
        this.latitud = 0.0f;
        this.longitud = 0.0f;
        this.comentarios="";
        this.valoracion=0.0f;
        this.categoria=0;
    }

    public Lugar(String nombre, Float latitud, Float longitud, String comentarios, Float valoracion, Integer categoria) {
        this.id = null;
        this.nombre = nombre;
        this.latitud=latitud;
        this.longitud=longitud;
        this.comentarios=comentarios;
        this.valoracion=valoracion;
        this.categoria=categoria;
    }

    public Lugar(Long id, String nombre, Float latitud, Float longitud, String comentarios, Float valoracion, Integer categoria) {
        this.id = id;
        this.nombre = nombre;
        this.latitud=latitud;
        this.longitud=longitud;
        this.comentarios=comentarios;
        this.valoracion=valoracion;
        this.categoria=categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Float getLatitud() {
        return latitud;
    }
    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }
    public Float getLongitud() {
        return longitud;
    }
    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public Float getValoracion() {
        return valoracion;
    }
    public void setValoracion(Float valoracion) {
        this.valoracion = valoracion;
    }
    public Integer getCategoria() {
        return categoria;
    }
    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return "Producto {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", comentarios=" + comentarios +
                ", valoracion=" + valoracion +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
