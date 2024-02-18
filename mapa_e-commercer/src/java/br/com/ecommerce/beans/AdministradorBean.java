/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.ecommerce.beans;

import br.com.ecommerce.repositorios.AdministradorRepositorio;
import br.com.ecommerce.repositorios.UsuarioRepositorio;
import br.com.ecommerce.repositorios.VendedorRepositorio;
import br.com.ecommerce.util.JPAUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.ecommmerce.models.Usuario;
import br.com.ecommmerce.models.Vendedor;
import br.com.ecommmerce.models.Administrador;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Claudenir
 */

@ManagedBean(name = "administradorBean")
@SessionScoped
public class AdministradorBean implements Serializable {

    private String sessionId = "";
    private UsuarioRepositorio usuarioRepositorio;
    private VendedorRepositorio vendedorRepositorio;
    private AdministradorRepositorio administradorRepositorio;

    private long id;
    private Administrador administrador;
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
    private String email;
    private String nomeusuario;
    private String senhahash;
    private String status;
    private String tipo;


    public AdministradorBean(String nome, Date datanascimento, String rua, String numero, String complemento, String bairro, String cidade, String estado, String rg, String email, String nomeusuario, String senhahash) {
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.rg = rg;
        this.email = email;
        this.nomeusuario = nomeusuario;
        this.senhahash = senhahash;
    }

        public AdministradorBean() {

        }


    
    
    //    CADASTRAR ADMINISTRADOR
    public String cadastrar() {

        String hashedPassword = BCrypt.hashpw(senhahash, BCrypt.gensalt());
        
        administrador = new Administrador(nome, datanascimento, rua, numero, complemento, bairro, cidade, estado, cpf, rg, email, nomeusuario, hashedPassword);

        EntityManager entityManager = JPAUtil.getEntityManager();
        
        administradorRepositorio = new AdministradorRepositorio(entityManager);
        
        System.out.println("Objeto Administrador:   " + administrador);
        
        administradorRepositorio.create(administrador);
        
        this.limparFormulario();
        
        return "index?faces-redirect=true";
        
    }
    
    
    //    EDITAR ADMINISTRADOR  -  UPDATE 
    public String editarUsuario(Administrador administrador) {
        
        this.administrador = administrador;
        
        if (administradorRepositorio.update(administrador)) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Adminisrador atualizado com sucesso!"));
            
            return "index";
            
        } else {
            
            FacesContext.getCurrentInstance().addMessage(null,
                    
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar usuário.", "Verifique os dados informados e tente novamente"));
            
            return "editarAdministrador?faces-redirect=true"; // Redireciona para a página de edição de usuário
            
        }
        
    }
    

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
        this.setEmail("");
        this.setNomeusuario("");
        this.setSenhahash("");
    }
    

    // **** **** GERENCIAMENTO USUÁIRO **** **** ***** ***** 
    // Aprova usuário
    public String aprovarUsuario(Usuario usuario) {
        usuario.setStatus("APROVADO");
        EntityManager entityManager = JPAUtil.getEntityManager();
        usuarioRepositorio = new UsuarioRepositorio(entityManager);
        usuarioRepositorio.update(usuario);
        return "painelUsuarios?faces-redirect=true";
    }

    // Bloquea usuário
    public String bloquearUsuario(Usuario usuario) {
        usuario.setStatus("BLOQUEADO");
        EntityManager entityManager = JPAUtil.getEntityManager();
        usuarioRepositorio = new UsuarioRepositorio(entityManager);
        usuarioRepositorio.update(usuario);
        encerrarUsuario(usuario);
        return "painelUsuarios?faces-redirect=true";
    }

    // Encerra usuário, se logado
    public void encerrarUsuario(Usuario usuario) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (usuario.getNomeusuario().equals(session.getAttribute("nomeUsuario"))) {
            session.removeAttribute("nomeUsuario");
            // session.invalidate();
        }
    }

    // reseta senha usuario
    public String resetarSenhaUsuario(Usuario usuario) {
        usuario.setSenhahash("");
        EntityManager entityManager = JPAUtil.getEntityManager();
        usuarioRepositorio = new UsuarioRepositorio(entityManager);
        usuarioRepositorio.update(usuario);
        return "painelUsuarios?faces-redirect=true";
    }


    // **** **** GERENCIAMENTO VENDEDOR **** **** ***** *****
    // Aporva vendedor
    public String aprovarVendedor(Vendedor vendedor) {
        vendedor.setStatus("APROVADO");
        EntityManager entityManager = JPAUtil.getEntityManager();
        vendedorRepositorio = new VendedorRepositorio(entityManager);
        vendedorRepositorio.update(vendedor);
        return "painelUsuarios?faces-redirect=true";
    }

    // Bloquea vendedor
    public String bloquearVendedor(Vendedor vendedor) {
        vendedor.setStatus("BLOQUEADO");
        EntityManager entityManager = JPAUtil.getEntityManager();
        vendedorRepositorio = new VendedorRepositorio(entityManager);
        vendedorRepositorio.update(vendedor);
        encerrarVendedor(vendedor);
        return "painelUsuarios?faces-redirect=true";
    }

    // Encerra vendedor, se logado
    public void encerrarVendedor(Vendedor vendedor) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (vendedor.getNomeusuario().equals(session.getAttribute("nomeVendedor"))) {
            session.removeAttribute("nomeVendedor");
            // session.invalidate();
        }
    }

    // reseta senha do vendedor
    public String resetarSenhaVendedor(Vendedor vendedor) {
        vendedor.setSenhahash("");
        EntityManager entityManager = JPAUtil.getEntityManager();
        vendedorRepositorio = new VendedorRepositorio(entityManager);
        vendedorRepositorio.update(vendedor);
        return "painelUsuarios?faces-redirect=true";
    }
    
    
    // GETTERS E SETTERS
    public UsuarioRepositorio getUsuarioRepositorio() {
        return usuarioRepositorio;
    }

    public void setUsuarioRepositorio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public VendedorRepositorio getVendedorRepositorio() {
        return vendedorRepositorio;
    }

    public void setVendedorRepositorio(VendedorRepositorio vendedorRepositorio) {
        this.vendedorRepositorio = vendedorRepositorio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public AdministradorRepositorio getAdministradorRepositorio() {
        return administradorRepositorio;
    }

    public void setAdministradorRepositorio(AdministradorRepositorio administradorRepositorio) {
        this.administradorRepositorio = administradorRepositorio;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
