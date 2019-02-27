package com.alvar.practica7pmdm.Model;

public class Lugar {
    private Long id;
    private Float latitud;
    private Float longitud;
    private String nombre;
    private String comentarios;
    private Float valoracion;
    private Integer categoria;

    public Lugar(Long dataId, Float dataLatitud, Float dataLongitud, String dataNombre, String dataComentarios, Float dataValoracion, Integer dataCategoria) {
        this.id = null;
        this.latitud=0.0f;
        this.longitud=0.0f;
        this.nombre = "";
        this.comentarios = "";
        this.valoracion = 0.0f;
        this.categoria = 0;
    }

    public Lugar(Float latitud, Float longitud, String nombre, String comentarios, Float valoracion, Integer categoria) {
        this.id = null;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.comentarios = comentarios;
        this.valoracion = valoracion;
        this.categoria = categoria;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCometarios() {
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
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud +
                ", nombre='" + nombre + '\'' +
                ", comentarios=" + comentarios +
                ", valoracion='" + valoracion + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}