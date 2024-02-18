package br.com.ecommerce.repositorios;

import br.com.ecommmerce.models.Produto;
import br.com.ecommmerce.models.Vendedor;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProdutoRepositorio {

    private final EntityManager entityManager;

    public ProdutoRepositorio(EntityManager em) {
        this.entityManager = em;
    }

    public List<Produto> getAll() {
        TypedQuery<Produto> query = entityManager.createQuery("SELECT p FROM Produto p", Produto.class);
        return query.getResultList();
    }

    public List<Produto> getById(Vendedor vendedor) {
        TypedQuery<Produto> query = entityManager.createQuery("SELECT p FROM Produto p WHERE p.vendedor_Id.id = :vendedorId", Produto.class);
        query.setParameter("vendedorId", vendedor.getId());
        return query.getResultList();
    }

    public boolean create(Produto produto) {
        System.out.println(produto);
        System.out.println("Método Create");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(produto);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {

            return false;
        }

    }    
    
    
    // UPDATE
    public boolean update(Produto produto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(produto);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {

            return false;
        }
    }
    
    
    // DELETE
    public boolean delete(Produto produto) {

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(produto) ? produto : entityManager.merge(produto));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            //log e
            return false;
        }
    }
    
}
