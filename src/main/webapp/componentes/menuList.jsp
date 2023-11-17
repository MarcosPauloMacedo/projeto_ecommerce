<div class="d-flex justify-content-between mb-3 ms-2 me-2">
    <% request.setAttribute("titulo", request.getAttribute("titulo")); %>
    <%@ include file="../componentes/menu.jsp" %>

    <% request.setAttribute("create", request.getAttribute("create")); %>
    <div class="d-flex">
        <%@ include file="../componentes/reserch.jsp" %>
        <%@ include file="../componentes/logout.jsp" %>
    </div>
</div>