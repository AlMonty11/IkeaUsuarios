package com.example.montaneralbertomyikea.controllers;

import com.example.montaneralbertomyikea.models.PedidoEntity;
import com.example.montaneralbertomyikea.models.ProductofferEntity;
import com.example.montaneralbertomyikea.models.User;
import com.example.montaneralbertomyikea.services.UserService;
import com.example.montaneralbertomyikea.services.pedidoServiceJPA;
import com.example.montaneralbertomyikea.services.productofferServiceJPA;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class authController {
    @Autowired
    private UserService userService;
    @Autowired
    private pedidoServiceJPA pedidoService;


    @GetMapping("/login")
    public String login()
    {
        return "/authentication/login";
    }
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/authentication/login";
    }
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "/authentication/register";
    }
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(user.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "Este nombre de usuario ya está registrado");

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/authentication/register";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/usuarios")
    public String listarUsuarios(@Valid @ModelAttribute("user") User user,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authentication", authentication);
        List<User> usuarios = userService.listarUsuarios();
        usuarios.remove(user);
        model.addAttribute("usuarios", usuarios);
        return "/authentication/usuarios";
    }
    @PostMapping("/ascenderManager")
    public String ascenderManager()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        userService.addRoleManager(email);
        return "redirect:/nuevoRolAdquirido";
    }
    @PostMapping("/ascenderAdmin")
    public String ascenderAdmin(Authentication authentication)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        userService.addRoleAdmin(email);
        return "redirect:/nuevoRolAdquirido";
    }
    @GetMapping("/nuevoRolAdquirido")
    public String confirmacionAscenso(Model model){
        return "/authentication/nuevoRolAdquirido";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/deleteUser/{email}")
    public String eliminarUsuario(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        pedidoService.deletePedidosUsuario(user);
        userService.deleteUserById(user.getId());
        return "redirect:/usuarios";
    }
}
