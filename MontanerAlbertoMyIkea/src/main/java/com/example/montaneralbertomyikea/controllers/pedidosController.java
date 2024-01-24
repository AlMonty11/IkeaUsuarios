package com.example.montaneralbertomyikea.controllers;

import com.example.montaneralbertomyikea.interfaces.pedidoService;
import com.example.montaneralbertomyikea.models.CarritoEntity;
import com.example.montaneralbertomyikea.models.PedidoEntity;
import com.example.montaneralbertomyikea.models.ProductofferEntity;
import com.example.montaneralbertomyikea.models.User;
import com.example.montaneralbertomyikea.services.UserService;
import com.example.montaneralbertomyikea.services.carritoServiceJPA;
import com.example.montaneralbertomyikea.services.pedidoServiceJPA;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class pedidosController {
    @Autowired
    pedidoServiceJPA pedidoService;
    @Autowired
    carritoServiceJPA carritoService;
    @Autowired
    UserService userService;
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/pedidos")
    public String listarPedidos(@AuthenticationPrincipal UserDetails userDetails,Model model) {
        List<PedidoEntity> pedidos = pedidoService.listarPedidos();
        List<PedidoEntity> pedidosUser = new ArrayList<>();
        for (PedidoEntity pedido:pedidos) {
            if(Objects.equals(pedido.getUser().getEmail(), userDetails.getUsername())){
                pedidosUser.add(pedido);
            }
        }
        model.addAttribute("pedidos", pedidosUser);
        return "pedidos/pedidos";
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/realizarPedido")
    public String realizarPedido(@AuthenticationPrincipal UserDetails userDetails){
        CarritoEntity carrito = carritoService.getCarrito();
        User user = userService.findUserByEmail(userDetails.getUsername());

        PedidoEntity pedido = new PedidoEntity();
        pedido.setCarrito(carrito);
        pedido.setUser(user);
        pedidoService.realizarPedido(pedido);
        carrito.setPagado(true);
        carritoService.aniadirAlCarrito(carrito);
        return "redirect:/pedidos";
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/detailsPedido/{id}")
    public String detailsPedido(@PathVariable int id,Model model){
        PedidoEntity pedido = pedidoService.detailsPedido(id);
        model.addAttribute("pedido",pedido);
        return "/pedidos/detailsPedido";
    }
}
