/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.ecommerce.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import br.com.ecommerce.repositorios.ProdutoRepositorio;
import br.com.ecommerce.util.JPAUtil;
import br.com.ecommmerce.models.Produto;
import br.com.ecommmerce.models.Vendedor;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Claudenir
 */

@ManagedBean(name = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

    private ProdutoRepositorio produtoRepositorio;
    private List<Produto> produtos;
    private List<Produto> produtosDoVendedor;

    private String nome;
    private String descricao;
    private Double valor;
    private String imagem;
    private Vendedor vendedor;
    private long idVendedor;

    @ManagedProperty("#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

    
    public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
        this.autenticacaoBean = autenticacaoBean;
    }
    

    public ProdutoBean() {
    }
    

    public ProdutoBean(String nome, String descricao, Double valor, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.imagem = imagem;
    }
    

    // *******   MÉTODO INACABADO POR FALTA DE QI.... Seria para editar as céluas separadamente e enviá-las para o UPDATE dos produtos
    public void onCellEdit(CellEditEvent event) {
        
        Object oldValue = event.getOldValue();
        
        Object newValue = event.getNewValue();
    

        if (newValue != null && !newValue.equals(oldValue)) {
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
                
        PrimeFaces.current().executeScript("document.getElementById('editarUsuarioBtn').click();");
        
    }

    
    // RECARREGA A URI ATUAL
    public String recarrega() {

        String url = "";

        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            url = request.getRequestURI();

            FacesContext.getCurrentInstance().getExternalContext().redirect(url);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return url;

    }
    

    // MÉTODO CADASTRA PRODUTO - POST
    public String cadastrar() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        Produto produto = new Produto(imagem, nome, descricao, valor, vendedor);

        produto.setVendedor_Id(vendedor);

        EntityManager entityManager = JPAUtil.getEntityManager();

        produtoRepositorio = new ProdutoRepositorio(entityManager);

        produtoRepositorio.create(produto);

        this.limparFormulario();

        return recarrega();

    }
    

    //    EDITAR PRODUTO - UPDATE
    public String editarProduto(Produto produto) {


        if (produtoRepositorio.update(produto)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto atualizado com sucesso!"));

            return "painelProdutos";

        } else {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar o produto.", "Verifique os dados informados e tente novamente"));

            return "editarUsuario?faces-redirect=true";

        }

    }

    
    // EXCLUIR PRODUTO - DELETE
    public String excluirProduto(Produto produto) {

        if (produtoRepositorio.delete(produto)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário excluído com sucesso!"));

            return recarrega();

        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir usuário.", "Verifique os dados informados e tente novamente"));

            return null;

        }

    }

    // VERIFICA SE ESTÁ LOGADO E DIRECIONA PARA PÁGINA COMPRAR
    public String comprar(Produto produto) {
        if (autenticacaoBean.verivicaUsuarioLogado()) {
            return "comprar";
        }
        return null;
    }

    
    public void limparFormulario() {
        setNome("");
        setDescricao("");
        setValor(null);
        setImagem("");
    }

    
    //    GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public ProdutoRepositorio getProdutoRepositorio() {
        return produtoRepositorio;
    }

    public List<Produto> getProdutos() {

        if (produtos == null) {

            EntityManager entityManager = JPAUtil.getEntityManager();

            produtoRepositorio = new ProdutoRepositorio(entityManager);

            produtos = produtoRepositorio.getAll();

        }

        return produtos;

    }

    public void setProdutoRepositorio(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public List<Produto> getProdutosDoVendedor() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        EntityManager entityManager = JPAUtil.getEntityManager();

        produtoRepositorio = new ProdutoRepositorio(entityManager);

        vendedor = new Vendedor((Long) session.getAttribute("idVendedor"));

        produtosDoVendedor = produtoRepositorio.getById(vendedor);

        return produtosDoVendedor;

    }

    public void setProdutosDoVendedor(List<Produto> produtosDoVendedor) {
        this.produtosDoVendedor = produtosDoVendedor;
    }

    public long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(long idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

}
