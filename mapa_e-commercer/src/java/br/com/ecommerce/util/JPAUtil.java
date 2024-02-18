/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.ecommerce.util;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Claudenir
 */
public class JPAUtil {
    
    private static final String PERSISTENCE_UNIT = "E-CommercePU";
    
    private static EntityManagerFactory emf;
    
    
    public static EntityManager getEntityManager() {
        
        if (emf == null) {
            
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            
        }
        
        return emf.createEntityManager();
        
    }    
    
}
