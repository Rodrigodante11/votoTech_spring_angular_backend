package io.github.rodrigodante.vototech.repository;

import io.github.rodrigodante.vototech.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByEmailContainingIgnoreCaseAndNomeContainingIgnoreCase(String nome, String email);
}
