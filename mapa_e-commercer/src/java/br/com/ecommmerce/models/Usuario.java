/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ecommmerce.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Claudenir
 */
@Entity
@Table(name = "`tb_usuario`", catalog = "`e-commerce`", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
//    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
//    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
//    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
//    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
//    @NamedQuery(name = "User.findByUserType", query = "SELECT u FROM User u WHERE u.userType = :userType")
})

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATACADASTRAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacadastral;
    @Column(name = "TIPO")
    private String tipo = "Usuário";
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DATANASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date datanascimento;
    @Column(name = "IDADE")
    private Integer idade;
    @Column(name = "RUA")
    private String rua;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Column(name = "BAIRRO")
    private String bairro;
    @Column(name = "CIDADE")
    private String cidade;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "RG")
    private String rg;
    @Column(name = "CARTAOTIPO")
    private String cartaotipo;
    @Column(name = "NUMEROCARTAO")
    private String numerocartao;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NOMEUSUARIO")
    private String nomeusuario;
    @Column(name = "SENHAHASH")
    private String senhahash;
    @Column(name = "STATUS")
    private String status = "Aguardando aprovação...";
    
    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId", fetch = FetchType.LAZY)
    //    private List<Pedido> pedidoList;


    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    

    public Usuario(String nome, Date datanascimento, String rua, String numero, String complemento, String bairro, String cidade, String estado, String rg, String cpf, String cartaotipo, String numerocartao, String email, String nomeusuario, String senhahash) {
        this.datacadastral = new Date();
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.rg = rg;
        this.cpf = cpf;
        this.cartaotipo = cartaotipo;
        this.numerocartao = numerocartao;
        this.email = email;
        this.nomeusuario = nomeusuario;
        this.senhahash = senhahash;

    }
    

    //    GETTERS E SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatacadastral() {
        return datacadastral;
    }

    public void setDatacadastral(Date datacadastral) {
        this.datacadastral = datacadastral;
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
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



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + 
                ", datacadastral=" + datacadastral +
                ", tipo=" + tipo + ", nome=" + nome +
                ", datanascimento=" + datanascimento +
                ", idade=" + idade + ", rua=" + rua +
                ", numero=" + numero + ", complemento=" +
                complemento + ", bairro=" + bairro +
                ", cidade=" + cidade + ", estado=" +
                estado + ", cpf=" + cpf + ", rg=" +
                rg + ", cartaotipo=" + cartaotipo +
                ", numerocartao=" + numerocartao +
                ", email=" + email + ", nomeusuario=" +
                nomeusuario + ", senhahash=" + senhahash +
                ", status=" + status +  "\n" + '}';
    }


}
