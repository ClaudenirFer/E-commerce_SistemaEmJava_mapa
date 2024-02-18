/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.ecommerce.beans;

import br.com.ecommerce.repositorios.VendedorRepositorio;
import br.com.ecommerce.util.JPAUtil;
import br.com.ecommmerce.models.Vendedor;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Claudenir
 */
@ManagedBean(name = "vendedorBean")
@SessionScoped
public class VendedorBean implements Serializable {

    private VendedorRepositorio vendedorRepositorio;
    private List<Vendedor> vendedoresCadastrados;

    private long id;
    private Vendedor vendedor;
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
    private String email;
    private String nomeusuario;
    private String senhahash;
    private String status;

    public VendedorBean() {
    }
    

    // MÉTODO CADASTRA VENDEDOR - POST
    public String cadastrar() {

        String hashedPassword = BCrypt.hashpw(senhahash, BCrypt.gensalt());

        vendedor = new Vendedor(nome, datanascimento, rua, numero, complemento, bairro, cidade, estado, cpf, rg, email, nomeusuario, hashedPassword);

        EntityManager entityManager = JPAUtil.getEntityManager();

        vendedorRepositorio = new VendedorRepositorio(entityManager);

        vendedorRepositorio.create(vendedor);

        this.limparFormulario();

        return "index?faces-redirect=true";

    }
    

    // MÉTODO EDITA VENDEDOR - UPDATE
    public String editarVendedor(Vendedor vendedor) {

        this.vendedor = vendedor;

        if (vendedorRepositorio.update(vendedor)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vendedor atualizado com sucesso!"));

            return "index";

        } else {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar vendedor.", "Verifique os dados informados e tente novamente"));

            return "editarUsuario?faces-redirect=true"; // Redireciona para a página de edição de usuário

        }

    }

    
    // CONFIRMAÇÃO: VENDEDOR CRIADO - POST
    public String confirmacao() {

        if (vendedorRepositorio.create(vendedor)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vendedor registrado com sucesso!"));

            return "index";

        } else {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar o vendedor.", "Verifique os dados informados e tente novamente"));

            return "register";

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
        this.setStatus("");
    }

    
    
    @Override
    public String toString() {
        return "NavigationBean{" + "vendedorRepositorio=" + vendedorRepositorio + ", vendedor=" + vendedor + '}';
    }

    //    IMPRIMIR LISTA NO CONSOLE (testes)
    public void imprimirLista() {
        for (Vendedor v : vendedoresCadastrados) {
            System.out.println(v);
        }
    }

    
    //    GETTERS E SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VendedorRepositorio getVendedorRepositorio() {
        return vendedorRepositorio;
    }

    public void setVendedorRepositorio(VendedorRepositorio vendedorRepositorio) {
        this.vendedorRepositorio = vendedorRepositorio;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public List<Vendedor> getVendedoresCadastrados() {
        if (vendedoresCadastrados == null) {
            EntityManager entityManager = JPAUtil.getEntityManager();
            vendedorRepositorio = new VendedorRepositorio(entityManager);
            vendedoresCadastrados = vendedorRepositorio.getAll();
        }
        return vendedoresCadastrados;
    }

    public void setVendedoresCadastrados(List<Vendedor> vendedoresCadastrados) {
        this.vendedoresCadastrados = vendedoresCadastrados;
    }

}
