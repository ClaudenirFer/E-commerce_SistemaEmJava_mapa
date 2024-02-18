/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.ecommerce.repositorios;

import br.com.ecommmerce.models.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Claudenir
 */
public class UsuarioRepositorio implements Serializable {

    private final EntityManager entityManager;

    public UsuarioRepositorio(EntityManager em) {
        this.entityManager = em;
    }

    
    //    AUTENTICAÇÃO
    public Usuario autenticarUsuario(String nomeUsuario) {

        try {

            TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nomeusuario = :nomeUsuario", Usuario.class);
            
            query.setParameter("nomeUsuario", nomeUsuario);

            Usuario usuario = query.getSingleResult();

            // Atualiza o usuário antes de retorná-lo
            entityManager.refresh(usuario);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;
        }
    }

    
    // GET
    public List<Usuario> getAll() {
        TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findAll", Usuario.class);
        return query.getResultList();
    }
    

    // POST
    public boolean create(Usuario usuario) {
        //        System.out.println(usuario);
        //        System.out.println("Método Create");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            //log e
            return false;
        }
    }
    

    // UPDATE
    public boolean update(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(usuario);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    
    // DELETE
    public boolean delete(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(usuario) ? usuario : entityManager.merge(usuario));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

}
