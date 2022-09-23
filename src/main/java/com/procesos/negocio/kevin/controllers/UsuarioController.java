package com.procesos.negocio.kevin.controllers;

import com.procesos.negocio.kevin.models.Usuario;
import com.procesos.negocio.kevin.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController


public class UsuarioController {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
       Optional<Usuario> usuario= usuarioRepositorio.findById(id);

        return usuario;
    }
    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        usuarioRepositorio.save(usuario);
        return usuario;

    }
    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.findAll();

    }
    @GetMapping("/usuarios/{nombre}/{apellidos}")
    public List<Usuario> listarPorNombreAndApellidos(@PathVariable String nombre, @PathVariable String apellidos){
        return usuarioRepositorio.findByNombreAndApellidos(nombre, apellidos);

    }
    @GetMapping("/usuarios/nombre/{nombre}")
    public List<Usuario> listarPorNombre(@PathVariable String nombre) {
        return usuarioRepositorio.findAllByNombre(nombre);
    }
    @GetMapping("/usuarios/apellidos/{apellidos}")
    public List<Usuario> listarPorApellidos(@PathVariable String apellidos) {
        return usuarioRepositorio.findAllByApellidos(apellidos);
    }
    @PutMapping ("/usuarios/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){

        Usuario usuarioBD = usuarioRepositorio.findById(id).get();
        try{
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellidos(usuario.getApellidos());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioRepositorio.save(usuarioBD);
            return usuarioBD;

        }catch (Exception e){
            return null;
        }

        }
        @DeleteMapping("/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id) {
            Usuario usuarioBD = usuarioRepositorio.findById(id).get();
            try {
                usuarioRepositorio.delete(usuarioBD);
                return usuarioBD;

            } catch (Exception e) {
                return null;
            }
        }
}
