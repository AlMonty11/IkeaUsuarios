package com.example.montaneralbertomyikea.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class CarritoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "carrito_id", nullable = false)
    private int carritoId;
    @ManyToMany
    @JoinTable(name="productos_carrito",joinColumns = @JoinColumn( name="carrito_id"),inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<ProductofferEntity> productsCarrito = new ArrayList<>();
    private double costeTotal;
    @Column(name = "pagado",nullable = false)
    private boolean pagado = false;


}
