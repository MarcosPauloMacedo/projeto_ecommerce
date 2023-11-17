<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../componentes/header.jsp" %>
<% Long perfil = (Long) request.getSession().getAttribute("adm"); %>
<%@ page import="model.enuns.Perfil" %>

<div class="d-flex">
    <div class="align-self-baseline me-3">
        <a class="btn btn-primary" data-bs-toggle="offcanvas" href="#offcanvasExample" role="button" aria-controls="offcanvasExample">
            <i class="bi bi-list"></i>
        </a>
        <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasExampleLabel">Menu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <% if (Perfil.acesso(perfil) == Perfil.ADMINISTRADOR) { %>
                <div>
                    <ul class="list-group">
                        <button type="button" class="btn btn-primary btn-sm mb-3">
                            <a class="btn" href="/ecommerce/usuarios" >Usuarios</a>
                        </button>
                        <button type="button" class="btn btn-primary btn-sm mb-3">
                            <a class="btn" href="/ecommerce/usuarios" >Vendas</a>
                        </button>
                        <button type="button" class="btn btn-primary btn-sm mb-3">
                            <a class="btn" href="/ecommerce/produtos" >Produtos</a>
                        </button>
                        <button type="button" class="btn btn-primary btn-sm mb-3">
                            <a class="btn" href="/ecommerce/produtos?loja=true" >Loja</a>
                        </button>
                    </ul>
                </div>
                <% } else { %>
                    <div>
                        <ul class="list-group">
                            <button type="button" class="btn btn-primary btn-sm mb-3">
                                <a class="btn" href="/ecommerce/produtos/loja" >Loja</a>
                            </button>
                            <button type="button" class="btn btn-primary btn-sm mb-3">
                                <a class="btn" href="/ecommerce/usuarios" >Vendas</a>
                            </button>
                        </ul>
                    </div>
                <% } %>
            </div>
        </div>
    </div>
    <h3><%= request.getAttribute("titulo") %></h3>
</div>
