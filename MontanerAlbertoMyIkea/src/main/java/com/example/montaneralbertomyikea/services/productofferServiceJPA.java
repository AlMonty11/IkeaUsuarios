package com.example.montaneralbertomyikea.services;

import com.example.montaneralbertomyikea.interfaces.productofferService;
import com.example.montaneralbertomyikea.models.ProductofferEntity;
import com.example.montaneralbertomyikea.models.User;
import com.example.montaneralbertomyikea.repositories.productofferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productofferServiceJPA implements productofferService {
    @Autowired
    private productofferRepository por;

    @Override
    public List<ProductofferEntity> listarProductos() {
        return por.findAll();
    }
    @Override
    public ProductofferEntity details(int id) {
        return por.findById(id).orElse(new ProductofferEntity());
    }
    @Override
    public ProductofferEntity create(ProductofferEntity product) {
        return por.save(product);
    }
    @Override
    public ProductofferEntity update(ProductofferEntity product) {
        return por.save(product);
    }
    @Override
    public void delete(int id) {
        por.deleteById(id);
    }

}
