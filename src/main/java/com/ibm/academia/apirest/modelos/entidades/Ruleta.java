package com.ibm.academia.apirest.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ruletas",schema = "ruleta")
public class Ruleta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "total")
    private Double total;

    @Column(name = "autor")
    private String autor;

    @Column (name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "ruleta",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    //@JsonIgnoreProperties(("ruleta"))
    private List<Apuesta> apuestas;


    public Ruleta(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ruleta{" +
                "id=" + id +
                ", estado=" + estado +
                ", total=" + total +
                ", autor='" + autor + '\'' +
                '}';
    }

    @PrePersist
    private void antesPersistir()
    {
        this.fechaCreacion = new Date();
    }

    @PreUpdate
    private void antesActualizar(){
        this.fechaModificacion = new Date();

    }

    private static final long serialVersionUID = -7157681540135008986L;
}
