<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page 
import="java.util.List"

import="model.entity.Usuario"
import="model.enuns.Perfil"
%>

<% List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios"); %>

<div class="m-3">
    <% request.setAttribute("create", "/ecommerce/usuarios?create=01"); %>
    <% request.setAttribute("titulo", "Usuarios"); %>
    <%@ include file="../componentes/menuList.jsp" %>

    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Perfil</th>
                <th>Visualizar</th>
                <th>Editar </th>
                <th>Excluir</th>
        </thead>
        <tbody>
            <% for (Usuario usuario : usuarios) { %>
                <tr>
                    <td><%= usuario.getNome() %></td>
                    <td><%= usuario.getEmail() %></td>
                    <td><%= Perfil.acesso(usuario.getAdm()).getDescricao() %></td>
                    <div>
                        <% request.setAttribute("view", "/ecommerce/usuarios?id=" + usuario.getId()); %>
                        <% request.setAttribute("edit", "/ecommerce/usuarios?edit=" + usuario.getId()); %>
                        <% request.setAttribute("delete", "/ecommerce/usuarios?delete=" + usuario.getId()); %>
                                            
                        <%@ include file="../componentes/actions.jsp" %>
                    </div>                    
                </tr>
            <% } %>
        </tbody>
    </table>
</div>
