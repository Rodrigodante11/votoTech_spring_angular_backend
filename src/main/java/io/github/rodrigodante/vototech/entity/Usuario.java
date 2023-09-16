package io.github.rodrigodante.vototech.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table( name="usuario")
public class Usuario {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(unique = true)
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "is_admin", columnDefinition = "boolean default false")
    private boolean isAdmin;

    @PrePersist
    public void prePersisist() {
        setDataCadastro(LocalDate.now());
    }
}