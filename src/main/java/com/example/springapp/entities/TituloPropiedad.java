package com.example.springapp.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "titulos_propiedades")
public class TituloPropiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String notaria;

    @Column(nullable = false, length = 20)
    private String forma_adquisicion;

    @Column(nullable = false, length = 20)
    private Date fecha_adquision;

    @OneToOne
    private Propiedad propiedad;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_titulo_to_propietario"))
    private Propietario propietario;

    public TituloPropiedad(){

    }

    public TituloPropiedad(String notaria, String forma_adquisicion, Date fecha_adquision ) {
        this.notaria = notaria;
        this.forma_adquisicion = forma_adquisicion;
        this.fecha_adquision = fecha_adquision;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotaria() {
        return notaria;
    }

    public void setNotaria(String notaria) {
        this.notaria = notaria;
    }

    public String getForma_adquisicion() {
        return forma_adquisicion;
    }

    public void setForma_adquisicion(String forma_adquisicion) {
        this.forma_adquisicion = forma_adquisicion;
    }

    public Date getFecha_adquision() {
        return fecha_adquision;
    }

    public void setFecha_adquision(Date fecha_adquision) {
        this.fecha_adquision = fecha_adquision;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}
