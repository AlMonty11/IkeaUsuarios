package com.example.montaneralbertomyikea.services;

import com.example.montaneralbertomyikea.interfaces.carritoService;
import com.example.montaneralbertomyikea.models.CarritoEntity;
import com.example.montaneralbertomyikea.repositories.carritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class carritoServiceJPA implements carritoService {
    @Autowired
    carritoRepository cr;


    public CarritoEntity getCarrito()
    {
        List<CarritoEntity> carrito = cr.findAll();
        if(carrito.size() == 0)
        {
            return new CarritoEntity();
        }
        CarritoEntity carritoFinal = carrito.get(carrito.size() - 1);
        if(carritoFinal.isPagado())
        {return new CarritoEntity();}
        return carritoFinal;
    }

    public CarritoEntity aniadirAlCarrito(CarritoEntity carrito){return cr.save(carrito);}

    public CarritoEntity eliminarDelCarrito(CarritoEntity carrito){return cr.save(carrito);}
}
