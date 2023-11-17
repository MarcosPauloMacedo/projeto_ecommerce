<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page 
import="model.entity.Usuario"
%>

<% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>

<div class="m-3">
    <div>
        <% request.setAttribute("titulo", "Dados pessoais"); %>
        <%@ include file="../componentes/menu.jsp" %>
    </div>

    <div class="accordion accordion-flush m-4" id="accordionFlushExample">
        <div class="accordion-item">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                Informações do usuário
                </button>
            </h2>
            <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                    <div>
                        <h5>Nome</h5>
                        <p>${usuario.getNome()}</p>
                    </div>
                    <div>
                        <h5>Email</h5>
                        <p>${usuario.getEmail()}</p>
                    </div>
                    <div>
                        <h5>Perfil</h5>
                        <p>administrador</p>
                    </div>
                </div>
            </div>
            <div>
                <% request.setAttribute("titulo", "voltar"); %>
                <% request.setAttribute("link", "/ecommerce/usuarios"); %>
                <%@ include file="../componentes/button.jsp" %>
            </div>
        </div>
    </div>

</div>
