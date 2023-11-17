package model.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import model.entity.Produto;
import model.repository.ProdutoRepository;
import model.response.ProdutoResponse;

public class ProdutoService {

    private ProdutoRepository produtoRepository = new ProdutoRepository();

    public void create(String nome, String descricao, String desconto, String preco, String precoFinal, String parcelas,String quantidade,Part imagem) throws IOException {

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);	
        produto.setDesconto(desconto);
        produto.setPreco(preco);
        produto.setPrecoFinal(precoFinal);
        produto.setParcelas(parcelas);
        produto.setQuantidade(quantidade);
        produto.setImagem(getImagemBytes(imagem));

        if(produtoRepository.create(produto))
        {
            System.out.println("Produto salvo com sucesso!");
        }
    }

    public List<ProdutoResponse> getAll() throws IOException {

        ResultSet produtos = produtoRepository.getAll();
        List<ProdutoResponse> listProdutosResponse = new ArrayList<ProdutoResponse>();

        try {
            while(produtos.next()) {
              
                byte[] imagem = getImagemBytes(produtos);

                ProdutoResponse produtoResponse = new ProdutoResponse(
                    produtos.getLong("id"),
                    produtos.getString("nome"),
                    produtos.getString("descricao"),
                    produtos.getString("quantidade"),
                    produtos.getString("preco"),
                    covertBytesToString(imagem),
                    produtos.getString("desconto"),
                    produtos.getString("precoFinal"),
                    produtos.getString("parcelas")
                );
                
                listProdutosResponse.add(produtoResponse);
            }

            return listProdutosResponse;

        } catch (SQLException e) {   

            throw new RuntimeException(e);
        }
    }

    public ProdutoResponse getById(Long id) throws IOException {
        ResultSet produto = produtoRepository.getById(id);
        try{
            produto.next();
            Blob bytes = produto.getBlob("imagem");
            byte[] imagem = bytes.getBytes(1,(int) bytes.length());

            ProdutoResponse productFound = new ProdutoResponse();
            productFound.setNome(produto.getString("nome"));
            productFound.setDescricao(produto.getString("descricao"));
            productFound.setQuantidade(produto.getString("quantidade"));
            productFound.setPreco(produto.getString("preco"));
            productFound.setImagem(covertBytesToString(imagem));
            productFound.setDesconto(produto.getString("desconto"));
            productFound.setPrecoFinal(produto.getString("precoFinal"));
            productFound.setParcelas(produto.getString("parcelas"));

            return productFound;

        } catch (SQLException e) {   

            throw new RuntimeException(e);
        }
    }

    public Boolean update(Long id,String nome, String descricao, String desconto, String preco, String precoFinal, String parcelas,String quantidade,Part imagem) throws IOException {   
        
        Produto produto = new Produto(id,nome,descricao,quantidade,preco,getImagemBytes(imagem),desconto,precoFinal,parcelas);
        return produtoRepository.update(produto);
    }

    public void delete(Long id) {
        
        produtoRepository.delete(id);
    }

    private byte[] getImagemBytes(Part imagem) throws IOException {
        
        InputStream is = imagem.getInputStream();
        BufferedImage bi = ImageIO.read(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        byte[] bytes = baos.toByteArray();

        return bytes;
    }

    private byte[] getImagemBytes(ResultSet produtos) throws SQLException {
        
        Blob bytes = produtos.getBlob("imagem");
        byte[] img = bytes.getBytes(1,(int) bytes.length());
        return img;
    }

    private String covertBytesToString(byte[] imagem) throws IOException {

        String imageBase64 = Base64.getEncoder().encodeToString(imagem);
        return imageBase64;
    }
}
