/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.ecommerce.beans;

import br.com.ecommerce.repositorios.UsuarioRepositorio;
import br.com.ecommerce.util.JPAUtil;
import br.com.ecommmerce.models.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Claudenir
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private UsuarioRepositorio usuarioRepositorio;
    private List<Usuario> usuariosCadastrados;


    private long id = 0;
    private Usuario usuario;
    private String tipo;
    private String nome;
    private Date datanascimento;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cpf;
    private String rg;
    private String cartaotipo;
    private String numerocartao;
    private String email;
    private String nomeusuario;
    private String senhahash;
    private String status;

    public void limparFormulario() {
        this.setNome("");
        this.setDatanascimento(null);
        this.setRua("");
        this.setRua("");
        this.setNumero(null);
        this.setComplemento("");
        this.setBairro("");
        this.setCidade("");
        this.setEstado("");
        this.setRg("");
        this.setCpf("");
        this.setCartaotipo("");
        this.setNumerocartao("");
        this.setEmail("");
        this.setNomeusuario("");
        this.setSenhahash("");
    }
    
    
    //  CADASTRAR USUÁRIO  -  POST
    public String cadastrar() {

        String hashedPassword = BCrypt.hashpw(senhahash, BCrypt.gensalt());

        usuario = new Usuario(nome, datanascimento, rua, numero, complemento, bairro, cidade, estado, rg, cpf, cartaotipo, numerocartao, email, nomeusuario, hashedPassword);

        EntityManager entityManager = JPAUtil.getEntityManager();

        usuarioRepositorio = new UsuarioRepositorio(entityManager);

        usuarioRepositorio.create(usuario);

        this.limparFormulario();

        return "index?faces-redirect=true";

    }

    
    // EDITA USUÁRIO - UPDATE
    public String editarUsuario(Usuario usuario) {

        this.usuario = usuario;

        if (usuarioRepositorio.update(usuario)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário atualizado com sucesso!"));

            return "index";

        } else {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar usuário.", "Verifique os dados informados e tente novamente"));

            return "editarUsuario?faces-redirect=true"; // Redireciona para a página de edição de usuário

        }

    }
    

    // EXCLUIR USUÁRIO - DELETE
    public String excluirUsuario(Usuario usuario) {

        if (usuarioRepositorio.delete(usuario)) {
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário excluído com sucesso!"));

            return recarrega();

        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir usuário.", "Verifique os dados informados e tente novamente"));

            return null;

        }

    }
    

    // RECARREGA A PÁGINA - (Obs: pode vir a ser static)
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

    
    //   IMPRIMIR LISTA NO CONSOLE
    public void imprimirLista() {

        for (Usuario u : usuariosCadastrados) {

            System.out.println(u);

        }

    }
    
    

    public String confirmacao() {
        
        if (usuarioRepositorio.create(usuario)) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário registrado com sucesso!"));
            
            return "index";
            
        } else {
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar usuário.", "Verifique os dados informados e tente novamente"));
            
            return "register";
            
        }
        
    }
    
    

    @Override
    public String toString() {
        return "NavigationBean{" + "usuarioRepositorio=" + usuarioRepositorio + ", usuario=" + usuario + '}';
    }

    // GETTERS E SETTERS
    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepositorio.getAll();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCartaotipo() {
        return cartaotipo;
    }

    public void setCartaotipo(String cartaotipo) {
        this.cartaotipo = cartaotipo;
    }

    public String getNumerocartao() {
        return numerocartao;
    }

    public void setNumerocartao(String numerocartao) {
        this.numerocartao = numerocartao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeusuario() {
        return nomeusuario;
    }

    public void setNomeusuario(String nomeusuario) {
        this.nomeusuario = nomeusuario;
    }

    public String getSenhahash() {
        return senhahash;
    }

    public void setSenhahash(String senhahash) {
        this.senhahash = senhahash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Usuario> getUsuariosCadastrados() {
            EntityManager entityManager = JPAUtil.getEntityManager();
            usuarioRepositorio = new UsuarioRepositorio(entityManager);
            usuariosCadastrados = usuarioRepositorio.getAll();
        return usuariosCadastrados;
    }

    public void setUsuariosCadastrados(List<Usuario> usuariosCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioRepositorio getUsuarioRepositorio() {
        return usuarioRepositorio;
    }

    public void setUsuarioRepositorio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

}
