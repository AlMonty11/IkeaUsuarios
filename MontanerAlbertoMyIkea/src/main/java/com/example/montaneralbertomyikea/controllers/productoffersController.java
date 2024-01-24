package com.example.montaneralbertomyikea.controllers;

import com.example.montaneralbertomyikea.interfaces.municipiosService;
import com.example.montaneralbertomyikea.interfaces.productofferService;
import com.example.montaneralbertomyikea.interfaces.provinciasService;
import com.example.montaneralbertomyikea.models.MunicipiosEntity;
import com.example.montaneralbertomyikea.models.ProductofferEntity;
import com.example.montaneralbertomyikea.models.ProvinciasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class productoffersController {

    @Autowired
    productofferService productofferService;
    @Autowired
    municipiosService municipiosService;
    @Autowired
    provinciasService provinciasService;
    @GetMapping("/")
    public String index(){
        return "index";
    }


    @GetMapping("/productoffer")
    public String listarProductos(Model model) {
        List<ProductofferEntity> productoffer = productofferService.listarProductos();
        model.addAttribute("productoffer", productoffer);
        return "productoffer/productoffer";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/detailsProductoffer/{id}")
    public String details(@PathVariable int id, Model model) {
        ProductofferEntity productoffer = productofferService.details(id);
        model.addAttribute("productoffer", productoffer);
        return "productoffer/detailsProductoffer";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/createProductoffer")
    public String getCreate(Model model) {
        model.addAttribute("productoffer", new ProductofferEntity());
        List<MunicipiosEntity> municipios = municipiosService.listarMunicipios();
        model.addAttribute("municipios",municipios);
        List<ProvinciasEntity> provincias = provinciasService.listarProvincias();
        model.addAttribute("provincias",provincias);
        return "productoffer/createProductoffer";
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/createProductoffer")
    public String postCreate(@ModelAttribute ProductofferEntity productoffer,  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productoffer", productoffer);
            return "productoffer/createProductoffer";
        }
        productofferService.create(productoffer);
        return "redirect:/productoffer";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/modificarProducto/{id}")
    public String modificarProducto(@PathVariable int id,Model model){
        ProductofferEntity producto = productofferService.details(id);
        List<MunicipiosEntity> municipios = municipiosService.listarMunicipios();
        List<ProvinciasEntity> provincias= provinciasService.listarProvincias();

        model.addAttribute("producto",producto);
        model.addAttribute("municipios",municipios);
        model.addAttribute("provincias",provincias);

        return "/productoffer/editProductoffer";
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/modificarProducto")
    public String modificarProducto(@ModelAttribute ProductofferEntity producto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            List<MunicipiosEntity> municipios = municipiosService.listarMunicipios();
            List<ProvinciasEntity> provincias= provinciasService.listarProvincias();

            model.addAttribute("producto",producto);
            model.addAttribute("municipios",municipios);
            model.addAttribute("provincias",provincias);
            return "/productoffer/productoffer";
        }
        productofferService.update(producto);
        return "redirect:/productoffer";
    }


}
