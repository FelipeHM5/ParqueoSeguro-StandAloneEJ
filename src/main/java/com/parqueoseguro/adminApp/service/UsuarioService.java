package com.parqueoseguro.adminApp.service;

import com.parqueoseguro.adminApp.model.Usuario;
import com.parqueoseguro.adminApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //--> Servicio Spring para inyeccion de dependencias
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    //Metodo para listar a los usuarios de la DB
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    //Metodo para guardar o actualizar
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    // Metodo para actualizar por ID
    public Usuario actualizarUsuario(Long id, Usuario usuarioDetalles) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setNombre(usuarioDetalles.getNombre());
            usuario.setCorreo(usuarioDetalles.getCorreo());
            usuario.setRol(usuarioDetalles.getRol());
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }
    
    //Metodo para eliminar por ID
    public boolean eliminarUsuario(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            usuarioRepository.delete(usuarioOpt.get());
            return true;
        } else {
            return false;
        }
    }
}
