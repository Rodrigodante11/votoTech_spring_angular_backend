package io.github.rodrigodante.vototech.controller;

import io.github.rodrigodante.vototech.entity.Usuario;
import io.github.rodrigodante.vototech.exception.ErroUsuarioException;
import io.github.rodrigodante.vototech.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity salvarUsuario(@RequestBody @Valid Usuario usuario){

        try{
            usuarioService.salvar(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch(ErroUsuarioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity obterTodosUsuarios( ){
        try{

            List<Usuario> usuariosList = usuarioService.obterTodos();
            return new ResponseEntity<>(usuariosList, HttpStatus.OK);
        }catch (ErroUsuarioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("autenticar")
    public ResponseEntity autenticarUsuario(@RequestBody Usuario usuario){
        try {
            Usuario usuarioAutenticado = usuarioService.autenticar(usuario.getEmail(), usuario.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);

        }catch(ErroUsuarioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity obterUsuarioPorId(@PathVariable("id") Long id){

        return usuarioService.obterPorId(id)
                .map( usuario -> new ResponseEntity(
                        usuario, HttpStatus.OK
                )).orElseGet( () -> new ResponseEntity("Usuario Não encontrado", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(@PathVariable("id") Long id){

        return usuarioService.obterPorId(id).map( usuario -> {

            usuarioService.deletar(usuario);
            return new ResponseEntity<>(" Usuario " + usuario.getNome() + " Deletado com Sucesso",
                    HttpStatus.NO_CONTENT);
        }).orElseGet( () ->
                new ResponseEntity<>(" Usuario Não encontrado", HttpStatus.BAD_REQUEST));
    }


}
