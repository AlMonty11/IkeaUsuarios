package com.example.montaneralbertomyikea.services;

import com.example.montaneralbertomyikea.interfaces.municipiosService;
import com.example.montaneralbertomyikea.models.MunicipiosEntity;
import com.example.montaneralbertomyikea.repositories.municipiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class municipiosServiceJPA implements municipiosService {

    @Autowired
    private municipiosRepository mr;
    @Override
    public List<MunicipiosEntity> listarMunicipios() {
        return mr.findAll();
    }
    public Optional<MunicipiosEntity> findMunicipioById(int id){return mr.findById(id);}

}
