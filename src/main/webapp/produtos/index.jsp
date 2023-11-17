<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page 
import="model.response.ProdutoResponse"
import="java.util.List"
%>

<% List<ProdutoResponse> produtos = (List<ProdutoResponse>) request.getAttribute("produtos"); %>

<div class="m-3">
    <% request.setAttribute("create", "/ecommerce/produtos?create=01"); %>
    <% request.setAttribute("titulo", "Produtos"); %>
    <%@ include file="../componentes/menuList.jsp" %>
    
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Preço</th>
                <th>Quantidade</th>
                <th>View</th>
                <th>Edit </th>
                <th>Excluir</th>
            </tr>
        </thead>
        <tbody>
            <% for (ProdutoResponse produto : produtos) { %>
                <tr>
                    <td><%= produto.getNome() %></td>
                    <td><%= produto.getPreco() %></td>
                    <td><%= produto.getQuantidade() %></td>
                    <div>
                        <% request.setAttribute("view", "/ecommerce/produtos?id=" + produto.getId()); %>
                        <% request.setAttribute("edit", "/ecommerce/produtos?edit=" + produto.getId()); %>
                        <% request.setAttribute("delete", "/ecommerce/produtos?delete=" + produto.getId()); %>
                        
                        <%@ include file="../componentes/actions.jsp" %>
                    </div>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>