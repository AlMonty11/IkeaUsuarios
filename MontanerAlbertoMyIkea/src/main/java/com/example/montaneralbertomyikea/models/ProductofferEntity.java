package com.example.montaneralbertomyikea.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "productoffer", schema = "myikea",catalog="")
public class ProductofferEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Basic
    @Column(name = "product_name", nullable = false, length = 512)
    private String productName;
    @Basic
    @Column(name = "product_price", nullable = true, precision = 0)
    private Double productPrice;
    @Basic
    @Column(name = "product_picture", nullable = true, length = 512)
    private String productPicture;
    @Basic
    @Column(name = "id_municipio", nullable = false)
    private short idMunicipio;
    @ManyToOne
    @JoinColumn(name = "id_municipio",insertable=false, updatable=false)
    private MunicipiosEntity municipio;
    @Basic
    @Column(name = "product_stock", nullable = false)
    private int productStock;
}