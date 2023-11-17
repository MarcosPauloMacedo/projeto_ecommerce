<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page 
import="model.response.ProdutoResponse"
%>

<% ProdutoResponse editarProduto = (ProdutoResponse) request.getAttribute("editarProduto"); %>

<%@ include file="../componentes/header.jsp" %>

<div class="d-flex justify-content-center align-items-center mt-5" style="width: 100%; height: auto;">
    <form action="produtos" method="post" enctype="multipart/form-data">
        <h3 class="pb-3">Formulário de Produtos</h3>
        <input type="hidden" name="id" value="${editarProduto.id != null ? editarProduto.id : ''}">
        <div class="mb-3">
            <label class="form-label">Nome do produto</label>
            <input type="text" class="form-control" name="nome" value="${editarProduto.nome != null ? editarProduto.nome : ''}"
            >
        </div>
        <div class="mb-3">
          <label class="form-label">Descrição</label>
          <input type="text" class="form-control" name="descricao" value="${editarProduto.descricao != null ? editarProduto.descricao : ''}"
          >
        </div>
        <div class="mb-3">
          <label class="form-label">Desconto</label>
          <input type="text" class="form-control" name="desconto" value="${editarProduto.desconto != null ? editarProduto.desconto : ''}"
          >
        </div>
        <div class="mb-3">
          <label for="form-label" class="form-label">Preço</label>
          <input type="text" class="form-control" name="preco" value="${editarProduto.preco != null ? editarProduto.preco : ''}"
          >
        </div>
        <div class="mb-3">
          <label class="form-label">Preço final</label>
          <input type="text" class="form-control" name="precoFinal" value="${editarProduto.precoFinal != null ? editarProduto.precoFinal : ''}"
          >
        </div>
        <div class="mb-3">
          <label class="form-label">Parcelas</label>
          <input type="text" class="form-control" name="parcelas" value="${editarProduto.parcelas != null ? editarProduto.parcelas : ''}"
          >
        </div>
        <div class="mb-3">
          <label for="form-label" class="form-label">Quantidade</label>
          <input type="text" class="form-control" name="quantidade" value="${editarProduto.quantidade != null ? editarProduto.quantidade : ''}"
          >
        </div>
        <div class="mb-3">
          <label for="form-label" class="form-label">Imagem</label>
          <input type="file" class="form-control" name="imagem" value="${editarProduto.imagem != null ? editarProduto.imagem : ''}" accept="image/*"
          >
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
</div>
