/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.model.entities;

import java.util.List;

/**
 *
 * @author ALUNO
 */
public class Cobranca {
    
    private int codigo;
    private long dataCadastro;
    private long dataRealizacao;
    private String localCobranca;
    private String descricaoCobranca;
    private String status;
    
    private Agiota agiota;
    private Devedor veaco;
    private Cobrador cobrador;
    private List<Metodo> metodos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public long getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(long dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public long getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(long dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getLocalCobranca() {
        return localCobranca;
    }

    public void setLocalCobranca(String localCobranca) {
        this.localCobranca = localCobranca;
    }

    public String getDescricaoCobranca() {
        return descricaoCobranca;
    }

    public void setDescricaoCobranca(String descricaoCobranca) {
        this.descricaoCobranca = descricaoCobranca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Agiota getAgiota() {
        return agiota;
    }

    public void setAgiota(Agiota agiota) {
        this.agiota = agiota;
    }

    public Devedor getVeaco() {
        return veaco;
    }

    public void setVeaco(Devedor veaco) {
        this.veaco = veaco;
    }

    public Cobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    }

    public List<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<Metodo> metodos) {
        this.metodos = metodos;
    }
    
    
    
    
}
