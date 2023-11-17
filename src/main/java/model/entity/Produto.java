package model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    
    private Long id;
    private String nome;
    private String descricao;
    private String quantidade;
    private String preco;
    private byte[] imagem;
    private String desconto;
    private String precoFinal;
    private String parcelas;  
}