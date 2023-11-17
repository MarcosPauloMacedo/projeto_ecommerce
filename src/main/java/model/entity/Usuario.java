package model.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Long adm;
}
