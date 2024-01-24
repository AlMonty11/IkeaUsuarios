package com.example.montaneralbertomyikea.interfaces;

import com.example.montaneralbertomyikea.models.MunicipiosEntity;
import com.example.montaneralbertomyikea.models.ProvinciasEntity;

import java.util.List;

public interface provinciasService {
    List<ProvinciasEntity> listarProvincias();

    /*ProvinciasEntity findByIdMunicipio(int idMunicipio);*/
}
