/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ecommerce.repositorios;

import br.com.ecommmerce.models.Administrador;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.NoResultException;


/**
 *
 * @author Claudenir
 */
public class AdministradorRepositorio {

    private final EntityManager entityManager;

    public AdministradorRepositorio(EntityManager em) {
        this.entityManager = em;
    }
    
    
    //    AUTENTICAÇÃO
    public Administrador autenticarAdministrador(String nomeAdminsitrador) {
        
        try {
            
            TypedQuery<Administrador> query = entityManager.createQuery("SELECT u FROM Administrador u WHERE u.nomeusuario = :nomeAdministrador", Administrador.class);
            
            query.setParameter("nomeAdministrador", nomeAdminsitrador);
            
            return query.getSingleResult();
            
        } catch (NoResultException e) {
            
            return null; // Usuário não encontrado
            
        }
        
    }
    
    
    //    GETALL
    public List<Administrador> getAll() {
        TypedQuery<Administrador> query = entityManager.createNamedQuery("Administrador.findAll", Administrador.class);
        return query.getResultList();
    }

    //    CREATE
    public boolean create(Administrador administrador) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(administrador);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            //log e
            return false;
        }

    }
    
    //    UPDATE
    public boolean update(Administrador administrador) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(administrador);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            //log e
            return false;
        }
        
    }
}
