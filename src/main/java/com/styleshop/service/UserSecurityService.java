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
    @Transactional
    public class UserSecurityService implements UserDetailsService{

        private final UsuarioRepository usuarioRepository;

        public UserSecurityService(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            // Buscar usuario por email en lugar de nombre
            Usuario user = this.usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario con email: " + email));


            return User.builder()
                    .username(user.getEmail())  // Aquí usamos el email como nombre de usuario
                    .password(user.getPassword())
                    .disabled(user.isDisabled())
                    .accountLocked(user.isLocked())
                    .build();
        }


    }
