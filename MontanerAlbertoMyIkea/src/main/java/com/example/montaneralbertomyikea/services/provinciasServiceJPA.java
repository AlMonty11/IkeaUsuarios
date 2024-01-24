package com.example.montaneralbertomyikea.services;

import com.example.montaneralbertomyikea.interfaces.provinciasService;
import com.example.montaneralbertomyikea.models.MunicipiosEntity;
import com.example.montaneralbertomyikea.models.ProvinciasEntity;
import com.example.montaneralbertomyikea.repositories.municipiosRepository;
import com.example.montaneralbertomyikea.repositories.provinciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class provinciasServiceJPA implements provinciasService {
    @Autowired
    private provinciasRepository pr;
    @Autowired
    private municipiosRepository mr;
    @Override
    public List<ProvinciasEntity> listarProvincias() {
        return pr.findAll();
    }
    /*@Override
    public ProvinciasEntity findByIdMunicipio(int idMunicipio){
        List<MunicipiosEntity> municipios = rp.findAll();
        List<ProvinciasEntity> provincias = pr.findAll();
        int idProvinciaByIdMunicipio = -1;
        int idMunicipioElegido=-1;
        MunicipiosEntity municipioElegido=null;
        ProvinciasEntity provinciaElegida=null;

        for (MunicipiosEntity municipio : municipios) {
            if (idMunicipio == municipio.getIdMunicipio()) {
                idProvinciaByIdMunicipio = municipio.getIdProvincia();
                municipioElegido = municipio;
                break;
            }
        }
        for (ProvinciasEntity provincia : provincias) {
            if (idProvinciaByIdMunicipio == provincia.getIdProvincia()) {
                provinciaElegida = provincia;
                break;
            }
        }
        return provinciaElegida;
    }*/

}
