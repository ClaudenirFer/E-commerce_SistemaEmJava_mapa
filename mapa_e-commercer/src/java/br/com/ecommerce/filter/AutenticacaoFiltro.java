/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import br.com.ecommmerce.models.Produto;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/faces/painelUsuarios.xhtml", "/faces/painelProdutos.xhtml", "/faces/comprar.xhtml"})
public class AutenticacaoFiltro implements Filter {

    public AutenticacaoFiltro() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Verificar se o administrador está autenticado para acessar o painelUsuarios
        if (req.getRequestURI().endsWith("/faces/painelUsuarios.xhtml")
                && req.getSession().getAttribute("adminLogado") != null) {

            chain.doFilter(request, response);
            return;
        }

        // Verificar se o vendedor está autenticado para acessar o painelProdutos
        if (req.getRequestURI().endsWith("/faces/painelProdutos.xhtml")
                && req.getSession().getAttribute("nomeVendedor") != null) {
           
            chain.doFilter(request, response);
            return;
        }

        // Verificar se o usuário está logado para acessar a página comprar.xhtml
        if (req.getRequestURI().endsWith("/faces/comprar.xhtml")) {
            // Verificar se existe um produto ao tentar acessar a página
            Produto produto = (Produto) req.getAttribute("produto");
            if (produto != null) {

                chain.doFilter(request, response);
                return;
            } else {
                // Rdireciona, se não houver produto
                res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
                return;
            }
        }

        // Redireciona para a página de origem, caso os ifs acima não seja verdadeiro
        res.sendRedirect(req.getContextPath());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
