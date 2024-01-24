package com.example.montaneralbertomyikea.interfaces;

import com.example.montaneralbertomyikea.models.ProductofferEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface productofferService {
    List<ProductofferEntity> listarProductos();
    ProductofferEntity details(int id);
    ProductofferEntity create(ProductofferEntity producto);
    ProductofferEntity update(ProductofferEntity producto);
    void delete(int id);
}
