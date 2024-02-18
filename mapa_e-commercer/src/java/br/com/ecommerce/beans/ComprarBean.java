/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.ecommerce.beans;

import br.com.ecommmerce.models.Produto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//  ************************* ****************   CARRINHO EM DESENVOLVIMENTO ************************

/**
 *
 * @author Claudenir
 */
@ManagedBean(name = "comprarBean")
@SessionScoped
public class ComprarBean implements Serializable {

    private List<Produto> carrinho;

    public ComprarBean() {
        carrinho = new ArrayList<>();
    }

    public String adicionarAoCarrinho(String imagem, String nome, String descricao, Double valor, int quantidade) {
        System.out.println("Adiciona ao carrinho");


        Produto produto = new Produto(imagem, nome, descricao, valor, quantidade);
        
        carrinho.add(produto);

        //  limpar a quantidade
        quantidade = 0;

        return "index";
    }
    
    
//  ************************* **************** CARRINHO EM DESENVOLVIMENTO ************************
    public String inicio() {
        return "index";
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }
    
}

