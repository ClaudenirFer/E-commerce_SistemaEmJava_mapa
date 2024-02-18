/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ecommmerce.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



// +++++++++++++++++++    CATEGORIA NÃO CHEGOU A SER RELACIONADA NA IMPLEMENTAÇÃO.  ++++++++++++++
// ******************   FALTOU UM TEMPO PARA ISSO ****************************


/**
 *
 * @author Claudenir
 */
@Entity
//@MappedSuperclass
@Table(name = "`tb_categoria`", catalog = "`e-commerce`", schema = "")
@XmlRootElement
public class Categoria implements Serializable {

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
    @OneToMany(mappedBy = "categoriaId", fetch = FetchType.LAZY)
    private List<Produto> produtoList;

    public Categoria() {
    }

    public Categoria(Long id) {
        this.id = id;
    }

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    
    // +++++++++++++++++++    CATEGORIA AINDA NÃO RELACIONADA NA IMPLEMENTAÇÃO.  ++++++++++++++


    
    //    GETTERS E STTERS    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @XmlTransient
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ecommmerce.models.Categoria[ id=" + id + " ]";
    }
    
}
