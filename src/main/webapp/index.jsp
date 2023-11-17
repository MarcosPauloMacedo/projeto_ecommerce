
<head>
  <%@ include file="../componentes/header.jsp" %>
  <link rel="stylesheet" href="css/login.css">
</head>

<body>
  <div id="navbar_home">
    <% request.setAttribute("botao", "Cadastrar"); %>
    <% request.setAttribute("link", "cadastro.jsp"); %>
    <%@ include file="../componentes/navbar_home.jsp" %>
  </div>
  <section class="d-flex justify-content-center align-items-center" style="width: 100%; height: 85vh;">
      <form action="usuarios" method="post" style="width: 30%;">
          <input type="hidden" name="login" value="01">
          <h3 class="pb-3 text-light">Login</h3>
  
          <div class="form-floating mb-3">
            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="email">
            <label for="floatingInput">Email address</label>
          </div>
          <div class="form-floating mb-3">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="senha">
            <label for="floatingPassword">Password</label>
          </div>
  
          <button type="submit" class="btn btn-success">Fazer Login</button>
      </form>
  </section>
</body>