/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.model;

import java.time.Instant;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author aluno
 */

@Entity
@Table(name = "tarefa")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    
    @Column(name = "descricao", length = 120, nullable = false)
    private String descricao;
    
    @Column(name = "data_de_cadastro", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataDeCadastro;
    
    
    
    @Column(name = "data_de_modificacao", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataDeModificacao;
    
    @Column(name = "status", length = 30, nullable = false)
    private String status;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public Date getDataDeModificacao() {
        return dataDeModificacao;
    }

    public void setDataDeModificacao(Date dataDeModificacao) {
        this.dataDeModificacao = dataDeModificacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarefa other = (Tarefa) obj;
        return this.codigo == other.codigo;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "codigo=" + codigo + ", descricao=" + descricao + ", dataDeCadastro=" + dataDeCadastro + ", dataDeModificacao=" + dataDeModificacao + ", status=" + status + '}';
    }
    
    
    
    

    public Tarefa() {
        
        this.dataDeCadastro = new Date(System.currentTimeMillis());
        this.status = "Cadastrada";
        
    }

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.dataDeCadastro = new Date(System.currentTimeMillis());
        
        this.status = "Cadastrada";
        
    }
    
    
    
}
