/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ecommmerce.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Claudenir
 */

@Entity
@Table(name = "`tb_produto`", catalog = "`e-commerce`", schema = "")
@XmlRootElement
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "IMAGEM")
    private String imagem;
    @Column(name = "VALOR")
    private Double valor;
    @JoinColumn(name = "CATEGORIA_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoriaId;
    @JoinColumn(name = "VENDEDOR_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vendedor vendedor_Id;
    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoId", fetch = FetchType.LAZY)
    //    private Integer quantidade;
    //    private List<Pedido> pedidoList;
    

    //    CONSTRUTORES
    public Produto() {
    }

    public Produto(String imagem, String nome, String descricao, Double valor, Vendedor vendedor_id) {
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        // this.quantidade = quantidade;
    }

    public Produto(String imagem, String nome, String descricao, Double valor, Integer quantidade) {
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        // this.quantidade = quantidade;
    }
    

    // GETTERS AND SETTERS 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

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

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ecommmerce.models.Produto[ Nome=" + nome + " ]";
    }

    public Vendedor getVendedor_Id() {
        return vendedor_Id;
    }

    public void setVendedor_Id(Vendedor vendedor_Id) {
        this.vendedor_Id = vendedor_Id;
    }

}
