/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.PessoaDAO;
import br.edu.ifsp.pep.dao.TarefaDAO;
import br.edu.ifsp.pep.model.Pessoa;
import br.edu.ifsp.pep.model.Tarefa;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aluno
 */
@Named
//@RequestScoped
@ViewScoped
public class ExemploController implements Serializable {

    @Inject
    private PessoaDAO pessoaDAO;

    private Pessoa pessoa;
    private List<Pessoa> pessoas;
    private Pessoa pessoaSelecionada;

    @Inject
    private TarefaDAO tarefaDAO;

    private Tarefa tarefa;
    private List<Tarefa> tarefas;
    private Tarefa tarefaSelecionada;

    private Date data;

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public List<Tarefa> getTarefas() {
        if (this.tarefas == null) {
            System.out.println("Carregando...");
            this.tarefas = tarefaDAO.findAll();
        }

        return tarefas;
    }

    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
//        return pessoas;
        if (this.pessoas == null) {
            System.out.println("Carregando...");
            this.pessoas = pessoaDAO.findAll();
        }

        return pessoaDAO.findAll();

    }
//
//    public void setPessoas(List<Pessoa> pessoas) {
//        this.pessoas = pessoas;
//    }

    public void excluir() {

        if (tarefaSelecionada != null) {
            if (tarefaSelecionada.getStatus().equals("Cancelada")) {
                System.out.println("Metodo Excluir");
                tarefaDAO.remove(tarefaSelecionada);
                addMessage(FacesMessage.SEVERITY_INFO, "Informação", "Tarefa excluida");
                this.tarefas = null;
            }

        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Tarefa Não selecionada");
            return;
        }

    }

    public void cancelar() {
        
        if (tarefaSelecionada != null && tarefaSelecionada.getStatus().equals("Cadastrada")) {
        
            System.out.println("Cancelando tarefa");
            tarefaSelecionada.setStatus("Cancelada");
            Date date = new Date(System.currentTimeMillis());
            tarefaSelecionada.setDataDeModificacao(date);
            
            addMessage(FacesMessage.SEVERITY_WARN, "Informação", "Tarefa Cancelada");
            
            tarefaDAO.updadete(tarefaSelecionada);

            this.tarefas = null;
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Tarefa Não selecionada");
            return;
        }
    }

    public void finalizar() {
        
        if (tarefaSelecionada != null && tarefaSelecionada.getStatus().equals("Cadastrada")) {
            
            System.out.println("Cancelando tarefa");
            Date date = new Date(System.currentTimeMillis());
            tarefaSelecionada.setStatus("Finalizada");
            tarefaSelecionada.setDataDeModificacao(date);
            
            tarefaDAO.updadete(tarefaSelecionada);

            addMessage(FacesMessage.SEVERITY_WARN, "Informação", "Tarefa Finalizada");
            
            this.tarefas = null;
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Tarefa Não selecionada");

        }
    }

    public void adicionar() {

        System.out.println("Executou o método adicionar");
        System.out.println("Tarefa");
        System.out.println(tarefa);

        tarefaDAO.inserir(tarefa);
        //this.tarefas.add(tarefa);
        this.tarefa = new Tarefa();

        addMessage(FacesMessage.SEVERITY_INFO, "Informação", "Cadastro Realizado");

    }

    public void exibir() {
        System.out.println("Executou o método exibir");
//        for (Pessoa p : pessoas) {
//            System.out.println("Nome na lista: " + p.getNome());
//        }
    }

    public ExemploController() {

        System.out.println("Construido");
        this.pessoa = new Pessoa();
        this.tarefa = new Tarefa();
        //this.tarefas = new List<Tarefa>();

        //System.out.println(System.currentTimeMillis());
        //System.out.println(data.toGMTString());
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
