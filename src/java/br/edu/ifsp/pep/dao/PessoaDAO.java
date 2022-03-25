/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aluno
 */
@Stateless
public class PessoaDAO {
    
    @PersistenceContext(unitName = "aulaPU")
    private EntityManager em;

    public void inserir(Pessoa pessoa){
        em.persist(pessoa);
    }

    public List<Pessoa> findAll(){
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
        .getResultList();
    }

    public void remove(Pessoa p){
        System.out.println(p);
        
        if(!em.contains(p)){
            p = em.merge(p);
        }
        em.remove(p);
    }  

}
