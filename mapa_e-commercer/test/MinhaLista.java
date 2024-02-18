/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */


import br.com.ecommmerce.models.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;


/**
 *
 * @author Claudenir
 */

// ************************************** lISTA USADA DURANTE AS IMPLEMENTAÇÕES OS TESTES *****************************
// ************************************** NÃO  FAZ MAIS PARTE DO CÓDIGO  *****************************
@ManagedBean(name = "minhaLista")
@ViewScoped
public class MinhaLista implements Serializable {

    private List<Produto> produtos;

    @PostConstruct
    public void init() {
        produtos = new ArrayList<>();
//        produtos.add(new Produto("/resources/images/a.jpg", "TV Smart", "Tela LED 42", 2555.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "Relógio Smart", "Internet e telefone", 199.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "TV Smart", "Tela LED 42", 2555.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "Relógio Smart", "Internet e telefone", 300.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "TV Smart", "Tela LED 42", 1500.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "Relógio Smart", "Internet e telefone", 500.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "TV Smart", "Tela LED 42", 3000.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "Relógio Smart", "Internet e telefone", 59.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "TV Smart", "Tela LED 42", 3250.00));
//        produtos.add(new Produto("/resources/images/a.jpg", "Relógio Smart", "Internet e telefone", 100.00));

  
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

}

// ************************************** lISTA USADA DURANTE AS IMPLEMENTAÇÕES OS TESTES *****************************
// ************************************** NÃO  FAZ MAIS PARTE DO CÓDIGO  *****************************