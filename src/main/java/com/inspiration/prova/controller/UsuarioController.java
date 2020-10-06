package com.inspiration.prova.controller;

import com.inspiration.prova.model.Usuario;
import com.inspiration.prova.repository.UsuarioRepository;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/list")
    public String listUsuario(Model model) {

        model.addAttribute("usuarios", usuarioRepository.findAll(Sort.by("nome")));

        return "usuario/list";
    }

    @GetMapping("/usuario/add")
    public String addUsuario(Model model) {

        model.addAttribute("funcionario", new Usuario());
        return "usuario/add";
    }

    @PostMapping("/usuario/save")
    public String saveUsuario(Usuario usuario) {
        try{
            usuarioRepository.save(usuario);
        }
        catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
        return "redirect:/usuario/view/" + usuario.getId();
    }

    @GetMapping("/usuario/view/{id}")
    public String viewUsuario(@PathVariable long id, Model model){
        model.addAttribute("usuario",usuarioRepository.findById(id));

        return "usuario/view";
    }

    @GetMapping("/usuario/edit/{id}")
    public String editUsuario(@PathVariable long id,Model model){
        model.addAttribute("usuario", usuarioRepository.findById(id));

        return "usuario/edit";
    }

    @GetMapping("/usuario/delete/{id}")
    public String deleteUsuario(@PathVariable long id, Model model){
        model.addAttribute("usuario", usuarioRepository.findById(id));

        return "funcionario/delete";
    }
}
