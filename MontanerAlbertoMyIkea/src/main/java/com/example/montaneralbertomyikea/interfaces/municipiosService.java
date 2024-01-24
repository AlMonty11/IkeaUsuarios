package com.example.montaneralbertomyikea.interfaces;

import com.example.montaneralbertomyikea.models.MunicipiosEntity;
import com.example.montaneralbertomyikea.models.ProductofferEntity;

import java.util.List;
import java.util.Optional;

public interface municipiosService {
    List<MunicipiosEntity> listarMunicipios();

    Optional findMunicipioById(int id);
}
