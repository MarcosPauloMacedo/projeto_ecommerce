<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page 
import="model.entity.Usuario"
%>

<% Usuario editarUsuario = (Usuario) request.getAttribute("editarUsuario"); %>

<%@ include file="../componentes/header.jsp" %>
<div class="d-flex justify-content-center align-items-center" style="width: 100%; height: 100vh;">
    <form action="usuarios" method="post">
        <h3 class="pb-3">Formulário de Usuário</h3>
        <input type="hidden" name="id" value="${editarUsuario.id != null ? editarUsuario.id : ''}">
        <div class="mb-3">
            <label class="form-label">Nome completo</label>
            <input type="text" class="form-control" name="nome" 
                value="${editarUsuario.nome != null ? editarUsuario.nome : ''}"
            >
            <div id="emailHelp" class="form-text">Não compartilharemos seu e-mail com mais ninguém.</div>
        </div>
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Endereço de email</label>
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
            name="email" value="${editarUsuario.email != null ? editarUsuario.email : ''}"
          >
          <div id="emailHelp" class="form-text">Não compartilharemos seu e-mail com mais ninguém.</div>
        </div>
        <div class="mb-3">
          <label  class="form-label">Perfil de acesso</label>
          <select class="form-select" name="adm" aria-label="Default select example">
            <option value="0">Cliente</option>
            <option value="1">Administrador</option>
          </select>
      </div>
        <div class="mb-3">
          <label for="exampleInputPassword1" class="form-label">Senha</label>
          <input type="password" class="form-control" id="exampleInputPassword1"
            name="senha" value="${editarUsuario.senha != null ? editarUsuario.senha : ''}"
          >
        </div>
        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="exampleCheck1">
          <label class="form-check-label" for="exampleCheck1">Check me out</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
</div>