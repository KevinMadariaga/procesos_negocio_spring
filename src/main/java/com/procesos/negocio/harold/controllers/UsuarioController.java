package com.procesos.negocio.harold.controllers;

import com.procesos.negocio.harold.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController


public class UsuarioController {
    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Harold");
        usuario.setApellidos("Martinez");
        usuario.setDocumento("1003168607");
        usuario.setFechaNacimiento(new Date(2001,06,21));
        usuario.setTelefono("3112884010");
        usuario.setDireccion("Carrera 10 #6-43 Tejarito");
        return usuario;
    }
}
