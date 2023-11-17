<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page 
import="model.response.ProdutoResponse"
import="java.util.List"
%>

<% List<ProdutoResponse> produtos = (List<ProdutoResponse>) request.getAttribute("produtos"); %>

<header>
    <%@ include file="../componentes/header.jsp" %>
    <link rel="stylesheet" href="/ecommerce/css/loja.css">
</header>

<body>
    <div class="ms-4 me-4 p-3 styleLoja d-flex justify-content-between">
        <% request.setAttribute("titulo", ""); %>
        <%@ include file="../componentes/menu.jsp" %>
    
        <div class="d-flex">
            <button type="button" class="btn btn-primary position-relative">
                <a><i class="bi bi-bag-fill fs-4"></i></a>
                <span id="notificacao" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                  0
                  <span class="visually-hidden">unread messages</span>
                </span>
              </button>
            <%@ include file="../componentes/logout.jsp" %>
        </div>
    </div>
    
    <div class="ms-4 me-4 styleLoja">
        <section class="d-flex justify-content-around">
            <div class="col-6 d-flex flex-column justify-content-center ms-5 ps-5">
                <p class="text-light styleBackTitle">MERCADO PAGO POINT</p>
                <h1 class="text-light fs-2 fw-bold mb-4">ESCOLHA A MELHOR MAQUININHA DE CARTÃO PARA O SEU NEGOCIO</h1>
                <p class="text-light">consulte as regras de taxas 0%</p>
            </div>
            <div class="col-6 d-flex justify-content-center">
                <img style="width: 70%;" src="/ecommerce/img/multiproduto.png" alt="">
            </div>
        </section>
        
        <section class="d-flex justify-content-center width-100-vh">
            <div class="position-absolute" id="liveAlertPlaceholder"></div>
        </section>

        <section id="mercadoPago-maquininhas" class="d-flex mt-4 pb-5 flex-wrap">

            <% for (ProdutoResponse produto : produtos) { %>

            <section id="mini-point" class="col-3 backWhite maquininha">
                <div class="mp-imagemMaquininha">
                    <img class="img-maquininha-mp imagem-point" src="data:image/png;base64, <%=produto.getImagem() %>" alt="">               
                </div>                      
                    <div class="textAlignCenter">
                        <h2 class="fs-4 mt-3"><%= produto.getNome() %></h2>
                        <div class="tipo-maq-mp">
                            <p><%= produto.getDescricao() %></p>
                        </div>
                    </div>
                    
                    <div class="preco">
                        <div class="desconto d-flex justify-content-around ps-5 pe-5">
                            <p class="preco-valor"><%= produto.getPreco() %></p>
                            <div class="desconto-caixa">
                                <p class="desconto-tamanho"><%= produto.getDesconto() %></p>
                            </div>
                        </div>
                        <div class="textAlignCenter">
                            <h3 class="fw-bold"><%= produto.getPrecoFinal() %></h3>
                            <P> <%= produto.getParcelas() %></P>
                        </div>
                    </div>
    
                    <button id="liveAlertBtn" value="mini-point" class="botao-comprar mb-3 mt-4">Comprar</button>
            </section>   
            
            <% } %>
            
        </section>
    </div>

    <script src="../js/loja.js"></script>
</body>