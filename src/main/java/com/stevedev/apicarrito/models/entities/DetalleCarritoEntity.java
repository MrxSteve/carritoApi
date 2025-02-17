package com.stevedev.apicarrito.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "detalle_carritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCarritoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;
    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private CarritoEntity carrito;

    @Column(name = "reserva_expira")
    private LocalDateTime rerservaExpira;

    @PrePersist
    public void prePersist() {
        rerservaExpira = LocalDateTime.now().plusMinutes(15);
    }

    public boolean idExpirado() {
        return LocalDateTime.now().isAfter(rerservaExpira);
    }
}
