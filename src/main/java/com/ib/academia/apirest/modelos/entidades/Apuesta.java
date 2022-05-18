package com.ib.academia.apirest.modelos.entidades;


import com.ib.academia.apirest.enums.ColorRuleta;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name= "apuestas",schema = "ruleta")
public class Apuesta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="total")
    private Double premio;


    @Positive(message = "Debe ser mayor a 0")
    //@Max(value = 10000, message = "El maximo para apostar son $10,000 dolares")
    @Column(name="cantidad")
    private Double cantidad;



    @Column(name="color")
    @Enumerated(EnumType.STRING)
    private ColorRuleta colorApuesta;

    @Column (name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @ManyToOne(optional = true,cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ruleta_id", foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;

    public Apuesta(Integer id, Double premio, Double cantidad, ColorRuleta colorApuesta, Ruleta ruleta) {
        this.id = id;
        this.premio = premio;
        this.cantidad = cantidad;
        this.colorApuesta = colorApuesta;
        this.ruleta = ruleta;
    }

    @Override
    public String toString() {
        return "Apuesta{" +
                "id=" + id +
                ", premio=" + premio +
                ", cantidad=" + cantidad +
                ", colorApuesta=" + colorApuesta +
                ", fechaCreacion=" + fechaCreacion +
                ", ruleta=" + ruleta +
                '}';
    }

    @PrePersist
    private void antesPersistir()
    {
        this.fechaCreacion = new Date();
    }

    private static final long serialVersionUID = 5903562861051199600L;
}
