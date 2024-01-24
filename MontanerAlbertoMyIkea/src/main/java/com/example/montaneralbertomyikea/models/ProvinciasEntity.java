package com.example.montaneralbertomyikea.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
@Data
@AllArgsConstructor
@Entity
@Table(name = "provincias", schema = "myikea", catalog = "")
public class ProvinciasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_provincia", nullable = false)
    private short idProvincia;
    @Basic
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;
    @OneToMany(mappedBy = "provincia")
    private Collection<MunicipiosEntity> municipios;

    public ProvinciasEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvinciasEntity that = (ProvinciasEntity) o;

        if (idProvincia != that.idProvincia) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idProvincia;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public Collection<MunicipiosEntity> getMunicipiosByIdProvincia() {
        return municipios;
    }

    public void setMunicipiosByIdProvincia(Collection<MunicipiosEntity> municipiosByIdProvincia) {
        this.municipios= municipiosByIdProvincia;
    }
}
