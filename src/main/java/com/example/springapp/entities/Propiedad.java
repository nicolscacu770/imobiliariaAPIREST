package com.example.springapp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "propiedades")
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String direccion;

    @Column(nullable = false)
    private String tipo_propiedad;

    @Column(nullable = false)
    private Integer num_habitaciones;

    @Column(nullable = false)
    private Integer num_baños;

    @Column(nullable = false)
    private Integer precio;

    @OneToOne
    private TituloPropiedad titulo_propiedad;

    @ManyToMany
    private List<Cliente> clientes_interesados;

    public Propiedad(){

    }

    public Propiedad(String direccion, String tipo_propiedad, Integer num_habitaciones, Integer num_baños, Integer precio) {
        this.direccion = direccion;
        this.tipo_propiedad = tipo_propiedad;
        this.num_habitaciones = num_habitaciones;
        this.num_baños = num_baños;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_propiedad() {
        return tipo_propiedad;
    }

    public void setTipo_propiedad(String tipo_propiedad) {
        this.tipo_propiedad = tipo_propiedad;
    }

    public Integer getNum_habitaciones() {
        return num_habitaciones;
    }

    public void setNum_habitaciones(Integer num_habitaciones) {
        this.num_habitaciones = num_habitaciones;
    }

    public Integer getNum_baños() {
        return num_baños;
    }

    public void setNum_baños(Integer num_baños) {
        this.num_baños = num_baños;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public TituloPropiedad getTitulo_propiedad() {
        return titulo_propiedad;
    }

    public void setTitulo_propiedad(TituloPropiedad titulo_propiedad) {
        this.titulo_propiedad = titulo_propiedad;
    }

    public List<Cliente> getClientes_interesados() {
        return clientes_interesados;
    }

    public void setClientes_interesados(List<Cliente> clientes_interesados) {
        this.clientes_interesados = clientes_interesados;
    }
}
