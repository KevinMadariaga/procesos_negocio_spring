package com.procesos.negocio.kevin.repository;

import com.procesos.negocio.kevin.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    List<Usuario> findAllByNombre (String nombre);
    List<Usuario> findAllByApellidos (String apellidos);
    List<Usuario> findByNombreAndApellidos(String nombre,String apellidos);


}
