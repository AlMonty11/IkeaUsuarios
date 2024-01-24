package com.example.montaneralbertomyikea.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "municipios", schema = "myikea",catalog = "")
public class MunicipiosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_municipio", nullable = false)
    private short idMunicipio;
    @Basic
    @Column(name = "id_provincia",insertable=false, updatable=false)
    private short idProvincia;
    @Basic
    @Column(name = "cod_municipio", nullable = false)
    private int codMunicipio;
    @Basic
    @Column(name = "DC", nullable = false)
    private int dc;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia", nullable = false)
    private ProvinciasEntity provincia;
    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private List<ProductofferEntity> productos;


}
