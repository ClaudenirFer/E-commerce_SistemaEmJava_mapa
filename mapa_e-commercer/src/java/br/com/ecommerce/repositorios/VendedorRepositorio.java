/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ecommerce.repositorios;

import br.com.ecommmerce.models.Vendedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Claudenir
 */
public class VendedorRepositorio {

    private final EntityManager entityManager;

    public VendedorRepositorio(EntityManager em) {
        this.entityManager = em;
    }

    
    //    AUTENTICAÇÃO
    public Vendedor autenticarVendedor(String nomeVendedor) {
        try {
            TypedQuery<Vendedor> query = entityManager.createQuery("SELECT v FROM Vendedor v WHERE v.nomeusuario = :nomeVendedor", Vendedor.class);
            
            query.setParameter("nomeVendedor", nomeVendedor);

            Vendedor vendedor = query.getSingleResult();
            
            // Atualiza o vendedor antes de retorná-lo
            entityManager.refresh(vendedor);

            return query.getSingleResult();

        } catch (NoResultException e) {
            
            return null;
            
        }
        
    }

    // GET
    public List<Vendedor> getAll() {
        
        TypedQuery<Vendedor> query = entityManager.createNamedQuery("Vendedor.findAll", Vendedor.class);
        
        return query.getResultList();
        
    }
    

    // POST
    public boolean create(Vendedor vendedor) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(vendedor);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {

            return false;
        }

    }

    // UPDATE
    public boolean update(Vendedor vendedor) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(vendedor);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {

            return false;
        }
        
    }
    
}
