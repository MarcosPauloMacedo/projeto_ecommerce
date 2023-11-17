<head>
  <%@ include file="../componentes/header.jsp" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
</head>


<body>
  <div id="navbar_home">
    <% request.setAttribute("botao", "Login"); %>
    <% request.setAttribute("link", "index.jsp"); %>
    <%@ include file="../componentes/navbar_home.jsp" %>
  </div>
  <div class="d-flex justify-content-center align-items-center" style="width: 100%; height: 85vh;">
    <form class="p-4 rounded" action="usuarios" method="post" style="background: #0DFDA1;">
        <input type="hidden" name="register" value="01">
        <input type="hidden" name="adm" value="0">
        <h3 class="pb-3 text-light">Cadastrar</h3>

        <div class="mb-3">
          <label for="exampleInputPassword1" class="form-label">Nome</label>
          <input type="text" class="form-control"
            name="nome" 
          >
        </div>
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Endereço de email</label>
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"name="email"
          >
          <div id="emailHelp" class="form-text">Não compartilharemos seu e-mail com mais ninguém.</div>
        </div>
        <div class="mb-3">
          <label for="exampleInputPassword1" class="form-label">Senha</label>
          <input type="password" class="form-control" id="exampleInputPassword1"
            name="senha" 
          >
        </div>
        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="exampleCheck1">
          <label class="form-check-label" for="exampleCheck1">Check me out</label>
        </div>
        <button type="submit" class="btn btn-success">Cadastrar</button>
    </form>
  </div>
</body>