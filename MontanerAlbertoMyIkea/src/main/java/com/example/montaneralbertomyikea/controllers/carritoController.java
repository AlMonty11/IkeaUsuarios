package com.example.montaneralbertomyikea.controllers;

import com.example.montaneralbertomyikea.interfaces.carritoService;
import com.example.montaneralbertomyikea.interfaces.productofferService;
import com.example.montaneralbertomyikea.models.CarritoEntity;
import com.example.montaneralbertomyikea.models.ProductofferEntity;
import com.example.montaneralbertomyikea.models.User;
import com.example.montaneralbertomyikea.services.UserService;
import com.example.montaneralbertomyikea.services.carritoServiceJPA;
import com.example.montaneralbertomyikea.services.productofferServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class carritoController {
    @Autowired
    carritoServiceJPA carritoService;
    @Autowired
    productofferServiceJPA productofferService;
    @Autowired
    UserService us;
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/carrito")
    public String getCarrito(Model model) {
        CarritoEntity carrito = carritoService.getCarrito();
        model.addAttribute("carrito", carrito);
        return "/carrito";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/aniadirAlCarrito/{id}")
    public String aniadirAlCarrito(@PathVariable int id){
        ProductofferEntity producto = productofferService.details(id);
        CarritoEntity carrito = carritoService.getCarrito();
        double nuevoCostoTotal = carrito.getCosteTotal() + producto.getProductPrice();
        carrito.setCosteTotal(nuevoCostoTotal);
        carrito.getProductsCarrito().add(producto);
        carritoService.aniadirAlCarrito(carrito);

        return "redirect:/carrito";
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/eliminarProductoCarrito/{id}")
    public String eliminarProductoCarrito(@PathVariable int id){
        ProductofferEntity producto = productofferService.details(id);
        CarritoEntity carrito = carritoService.getCarrito();
        carrito.setCosteTotal(carrito.getCosteTotal()-producto.getProductPrice());
        carrito.getProductsCarrito().remove(producto);
        carritoService.eliminarDelCarrito(carrito);
        return "redirect:/carrito";
    }


}
