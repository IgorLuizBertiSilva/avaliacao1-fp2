/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Tarefa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aluno
 */
@Stateless
public class TarefaDAO {
    
    @PersistenceContext(unitName = "aulaPU")
    private EntityManager em;
    
    public void inserir(Tarefa tarefa){
        em.persist(tarefa);
    }

    public List<Tarefa> findAll(){
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class)
        .getResultList();
    }

    public void remove(Tarefa tarefa){
        System.out.println(tarefa);
        
        if(!em.contains(tarefa)){
            tarefa = em.merge(tarefa);
        }
        em.remove(tarefa);
    }  
    
    public void updadete(Tarefa tarefa){
        
        em.merge(tarefa);
        
    }
    
}
