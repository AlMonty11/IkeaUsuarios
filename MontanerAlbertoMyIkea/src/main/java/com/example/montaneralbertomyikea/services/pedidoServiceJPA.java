package com.example.montaneralbertomyikea.services;

import com.example.montaneralbertomyikea.interfaces.pedidoService;
import com.example.montaneralbertomyikea.models.PedidoEntity;
import com.example.montaneralbertomyikea.models.User;
import com.example.montaneralbertomyikea.repositories.UserRepository;
import com.example.montaneralbertomyikea.repositories.pedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class pedidoServiceJPA implements pedidoService {
    @Autowired
    pedidoRepository pr;
    @Autowired
    UserRepository ur;

    @Override
    public List<PedidoEntity> listarPedidos() {return pr.findAll();}
    public PedidoEntity realizarPedido(PedidoEntity pedido){return pr.save(pedido) ;}
    public PedidoEntity detailsPedido(int id){return pr.findById(id).orElse(new PedidoEntity());}
    @Override
    public void deletePedidosUsuario(User user){
        List<PedidoEntity> pedidosUsuario = user.getPedidos();
        pr.deleteAll(pedidosUsuario);
    }

}
