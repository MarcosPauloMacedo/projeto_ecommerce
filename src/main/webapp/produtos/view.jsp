<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page 
import="model.entity.Produto"
import="model.response.ProdutoResponse"
%>

<% ProdutoResponse produto = (ProdutoResponse) request.getAttribute("produto"); %>

<div class="m-3">
    <div>
        <% request.setAttribute("titulo", "Dados do produto"); %>
        <%@ include file="../componentes/menu.jsp" %>
    </div>

    <div class="accordion accordion-flush m-4" id="accordionFlushExample">
        <div class="accordion-item">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                Informações do produto
                </button>
            </h2>
            <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                    <div>
                        <h5>Nome</h5>
                        <p>${produto.getNome()}</p>
                    </div>
                    <div>
                        <h5>Preco</h5>
                        <p>${produto.getPreco()}</p>
                    </div>
                    <div>
                        <h5>Quantidade</h5>
                        <p>${produto.getQuantidade()}</p>
                    </div>
                    <div>
                        <h5>Imagem</h5>
                        <img style="height: 200px; width: 200px;" src="data:image/png;base64, ${produto.getImagem()}" alt="imagem">
                    </div>
                    
                </div>
            </div>
            <div>
                <% request.setAttribute("titulo","voltar"); %>
                <% request.setAttribute("link", "/ecommerce/produtos"); %>
                <%@ include file="../componentes/button.jsp" %>
            </div>
        </div>
    </div>
</div>
