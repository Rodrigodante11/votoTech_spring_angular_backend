package io.github.rodrigodante.vototech.controller;

import io.github.rodrigodante.vototech.entity.Usuario;
import io.github.rodrigodante.vototech.exception.ErroUsuarioException;
import io.github.rodrigodante.vototech.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity salvarUsuario(@RequestBody Usuario usuario){

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
}
