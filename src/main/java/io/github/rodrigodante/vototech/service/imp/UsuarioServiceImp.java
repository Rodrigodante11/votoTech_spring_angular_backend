package io.github.rodrigodante.vototech.service.imp;

import io.github.rodrigodante.vototech.entity.Usuario;
import io.github.rodrigodante.vototech.exception.ErroUsuarioException;
import io.github.rodrigodante.vototech.repository.UsuarioRepository;
import io.github.rodrigodante.vototech.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());

        if(!usuarioOptional.isEmpty()){
            throw new ErroUsuarioException("Email já registrado nno Sistema");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deletar(Usuario usuario) {
        Objects.requireNonNull(usuario.getId());
        usuarioRepository.delete(usuario);
    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if( usuario.isEmpty()){
            throw new ErroUsuarioException("Usuario não encontrado pelo email informado");
        }

        /*
        boolean senhaBatem = passwordEncoder.matches(senha, usuario.get().getSenha());
        if(!senhaBatem){
            throw new ErroUsuarioException("Senha inválida");
        }*/

        return usuario.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscar(Usuario usuarioFiltro) {
        return usuarioRepository.findByEmailContainingIgnoreCaseAndNomeContainingIgnoreCase(
                usuarioFiltro.getNome(), usuarioFiltro.getEmail()
        );
    }

    @Override
    public List<Usuario> obterTodos() {
        return usuarioRepository.findAll();
    }
}
