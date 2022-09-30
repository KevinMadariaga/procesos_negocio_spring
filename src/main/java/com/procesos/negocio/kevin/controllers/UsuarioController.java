package com.procesos.negocio.kevin.controllers;

import com.procesos.negocio.kevin.models.Usuario;
import com.procesos.negocio.kevin.repository.UsuarioRepositorio;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController


public class UsuarioController {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
       Optional<Usuario> usuario= usuarioRepositorio.findById(id);
        if(usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
        return  ResponseEntity.notFound().build();

    }
    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario) {
        try{
            usuarioRepositorio.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    @GetMapping("/usuarios/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreAndApellidos(@PathVariable String nombre, @PathVariable String apellidos){

        List<Usuario> usuarios = usuarioRepositorio.findByNombreAndApellidos(nombre, apellidos);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    @GetMapping("/usuarios/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable String nombre) {
        List<Usuario> usuarios = usuarioRepositorio.findAllByNombre(nombre);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
        // return usuarioRepositorio.findAllByNombre(nombre);

    @GetMapping("/usuarios/apellidos/{apellidos}")
    public ResponseEntity listarPorApellidos(@PathVariable String apellidos) {
        List<Usuario> usuarios = usuarioRepositorio.findAllByApellidos(apellidos);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
        //return usuarioRepositorio.findAllByApellidos(apellidos);

    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> usuarioBD = usuarioRepositorio.findById(id);
        if(usuarioBD.isPresent()){
            try{
                usuarioBD.get().setNombre((usuario.getNombre()));
                usuarioBD.get().setApellidos((usuario.getApellidos()));
                usuarioBD.get().setDocumento((usuario.getDocumento()));
                usuarioBD.get().setFechaNacimiento((usuario.getFechaNacimiento()));
                usuarioBD.get().setTelefono((usuario.getTelefono()));
                usuarioBD.get().setDireccion((usuario.getDireccion()));
                usuarioRepositorio.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
        @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
            Optional<Usuario> usuarioBD = usuarioRepositorio.findById(id);
            if(usuarioBD.isPresent()){
                usuarioRepositorio.delete(usuarioBD.get());
                return ResponseEntity.noContent().build();

            }
            return ResponseEntity.notFound().build();
        }
}
