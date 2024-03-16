/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.model.repositories;

import br.edu.ifpe.recife.model.entities.Cobrador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diogo
 */
public class CobradorRepository {
    private static List<Cobrador> cobrador;
    
    static{
        cobrador = new ArrayList<>();
    }
    
    public static void create(Cobrador c){
        CobradorRepository.cobrador.add(c);
    }
    public static void update(Cobrador c){
        for(Cobrador cAux: cobrador){
            if (cAux.getCodigo()==c.getCodigo()){
                cAux.setCodNome(c.getCodNome());
                cAux.setEmail(c.getEmail());
                cAux.setFerramentas(c.getFerramentas());
                cAux.setHabilidades(c.getHabilidades());
                cAux.setSenha(c.getSenha());
                cAux.setBreveDescricao(c.getBreveDescricao());
                cAux.setValor(c.getValor());
            }
        }
    }
    //nao entendi o porque
    public static Cobrador read(int codigo){
        for(Cobrador cAux: cobrador){
            if(cAux.getCodigo()==codigo){
                return cAux;
            }
        }
        return null;
    }
    
    public static void delete(int codigo){
        for(int i = 0; i < cobrador.size();i++){
            if(cobrador.get(i).getCodigo()==codigo){
                cobrador.remove(i);
                return;
            }
        }
    }
    
    public static List<Cobrador> readAll(){
        return cobrador;
    }

}