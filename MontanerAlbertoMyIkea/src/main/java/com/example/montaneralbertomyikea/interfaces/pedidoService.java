package com.example.montaneralbertomyikea.interfaces;

import com.example.montaneralbertomyikea.models.PedidoEntity;
import com.example.montaneralbertomyikea.models.User;

import java.util.List;

public interface pedidoService {
    List<PedidoEntity> listarPedidos();

    void deletePedidosUsuario(User user);
}
