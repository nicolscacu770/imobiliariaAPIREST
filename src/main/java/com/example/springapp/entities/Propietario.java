package com.example.springapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "propietarios")
public class Propietario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String apellido;

    @Column(nullable = false, length = 40)
    private String direccion;

    @Column(nullable = false, length = 30)
    private String telefono;

    @Column(nullable = false, length = 20)
    private String correo;

    @OneToMany(mappedBy = "propietario")
    @JsonIgnore
    private List<TituloPropiedad> titulosPropiedades;

    public Propietario(){
        titulosPropiedades = new ArrayList<>();
    }

    public Propietario( String nombre, String apellido, String direccion, String telefono, String correo ){
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<TituloPropiedad> getTitulosPropiedades() {
        return titulosPropiedades;
    }

    public void setTitulosPropiedades(List<TituloPropiedad> titulosPropiedades) {
        this.titulosPropiedades = titulosPropiedades;
    }
}
