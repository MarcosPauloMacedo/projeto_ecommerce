package model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String quantidade;
    private String preco;
    private String imagem;
    private String desconto;
    private String precoFinal;
    private String parcelas;
}
