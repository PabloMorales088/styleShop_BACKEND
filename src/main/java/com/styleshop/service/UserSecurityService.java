package com.styleshop.service;

import com.styleshop.model.Usuario;
import com.styleshop.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional // Asegura que las operaciones se ejecuten dentro de una transacción
public class UserSecurityService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserSecurityService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca un usuario en la base de datos usando el email como identificador
        Usuario user = this.usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con email: " + email));

        // Construye un objeto User (de Spring Security) con los datos obtenidos
        return User.builder()
                .username(user.getEmail())        // Usamos el email como nombre de usuario
                .password(user.getPassword())    // Contraseña cifrada
                .disabled(user.isDisabled())     // Si está deshabilitado, se bloquea el acceso
                .accountLocked(user.isLocked())  // Si está bloqueado, también se restringe el acceso
                .build(); // No se especifican roles (authorities) en este ejemplo
    }
}
