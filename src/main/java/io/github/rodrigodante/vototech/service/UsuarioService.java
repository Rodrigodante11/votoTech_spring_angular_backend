package io.github.rodrigodante.vototech.service;

import io.github.rodrigodante.vototech.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario salvar(Usuario usuario);

    void deletar(Usuario usuario);

    Optional<Usuario> obterPorId(Long id);

    Usuario autenticar(String email, String senha);

    List<Usuario> buscar(Usuario usuarioFiltro);

    List<Usuario> obterTodos();
}
