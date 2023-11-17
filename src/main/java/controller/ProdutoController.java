package controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.response.ProdutoResponse;
import model.service.ProdutoService;

@WebServlet("/produtos")
@MultipartConfig()
public class ProdutoController extends HttpServlet implements Controller {

    private ProdutoService produtoService = new ProdutoService();
    private HttpServletRequest req;
    private HttpServletResponse resp;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.req = req;
        this.resp = resp;

        Long profile = verifyProfile();
        boolean accessAllowed = profile != null;
        
        if(req.getParameter("id") != null && accessAllowed) view();
        else if(req.getParameter("delete") != null && accessAllowed) delete();
        else if(req.getParameter("edit") != null && accessAllowed) formEdit();
        else if(req.getParameter("create") != null && accessAllowed) formCreate(); 
        else if(req.getParameter("loja") != null && accessAllowed) loja();
        else if(accessAllowed) index();
        else {
            resp.sendRedirect("/ecommerce");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {

        this.req = req;
        this.resp = resp;

        try{
            if(req.getParameter("id").isEmpty()) create();
            else update();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void index() throws ServletException, IOException {

        List<ProdutoResponse> produtos = produtoService.getAll();
        req.setAttribute("produtos", produtos);
        req.getRequestDispatcher("/produtos/index.jsp").forward(req, resp);
    }

    private void view() throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        ProdutoResponse produto = produtoService.getById(id);
        req.setAttribute("produto", produto);
        req.getRequestDispatcher("/produtos/view.jsp").forward(req, resp);
    }

    private void create() throws ServletException, IOException{
            
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String desconto = req.getParameter("desconto");
        String preco = req.getParameter("preco");
        String precoFinal = req.getParameter("precoFinal");
        String parcelas = req.getParameter("parcelas");
        String quantidade = req.getParameter("quantidade");
        Part imagem = req.getPart("imagem");

        produtoService.create(nome,descricao,desconto,preco,precoFinal,parcelas,quantidade,imagem);
        resp.sendRedirect("/ecommerce/produtos");
    }

    private void update() throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String desconto = req.getParameter("desconto");
        String preco = req.getParameter("preco");
        String precoFinal = req.getParameter("precoFinal");
        String parcelas = req.getParameter("parcelas");
        String quantidade = req.getParameter("quantidade");
        Part imagem = req.getPart("imagem");

        produtoService.update(id, nome,descricao,desconto,preco,precoFinal,parcelas,quantidade,imagem);
        resp.sendRedirect("/ecommerce/produtos");
    }

    private void delete() throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("delete"));
        produtoService.delete(id);
        resp.sendRedirect("/ecommerce/produtos");
    }

    private void loja() throws ServletException, IOException {

        List<ProdutoResponse> produtos = produtoService.getAll();
        req.setAttribute("produtos", produtos);
        req.getRequestDispatcher("/produtos/loja.jsp").forward(req, resp);
    }

    private void formEdit() throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("edit"));
        ProdutoResponse editarProduto = produtoService.getById(id);
        req.setAttribute("editarProduto", editarProduto);
        req.getRequestDispatcher("/produtos/form.jsp").forward(req, resp);
    }

    private void formCreate() throws ServletException, IOException {

        req.getRequestDispatcher("/produtos/form.jsp").forward(req, resp);
    }

    @Override
    public Long verifyProfile() {

        if(req.getSession().getAttribute("adm") != null) {
            return 1L;
        }
        else{
            return null;
        }
    }
}
