package com.projectjava.demosclient.controllers;

/*
import com.projectjava.demosclient.dao.UserDao;
import com.projectjava.demosclient.dto.UsuarioDTO;
import com.projectjava.demosclient.entity.Cliente;
import com.projectjava.demosclient.entity.Usuario;
import com.projectjava.demosclient.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            Principal principal, Model model){

        //Comprueba que no se inicien todo el tiempo
        if(principal != null){
            return "redirect:/";
        }
        if(error != null){
            model.addAttribute("error", "logeo incorrecto, usuario o contraseña");
        }
    return "/login";
    }


    @RequestMapping("/registro")
    public String mostrarRegistroUsuario(Model model){
        UsuarioDTO usuario = new UsuarioDTO();
        model.addAttribute("usuario", usuario);
        return "/registro";
    }



    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario")UsuarioDTO usuario, Model model){
        userService.save(usuario);
        return "redirect:/login";
    }


    @GetMapping("/usuarios")
    public String mostrarUsuarios(Model modelos){
        List<Usuario> usuarioList = userService.findAll();

        modelos.addAttribute("usuarios", usuarioList);

        return "/usuarios";
    }
}




*/