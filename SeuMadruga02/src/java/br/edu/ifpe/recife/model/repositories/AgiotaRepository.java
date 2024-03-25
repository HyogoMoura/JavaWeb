/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.model.repositories;

import br.edu.ifpe.recife.model.entities.Agiota;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALUNO
 */
public class AgiotaRepository {
    
    private static List<Agiota> agiotas;
    
    static{
        agiotas = new ArrayList<>();
        
        Agiota a = new Agiota();
        a.setCodigo(1);
        a.setCodNome("Sandro da baxada da egua");
        a.setEmail("sandromatador@gmail.com");
        a.setSenha("1234");
        a.setDescricao("Perigoso, mata de rir por diversao");
        
        agiotas.add(a);
        
        a = new Agiota();
        a.setCodigo(2);
        a.setCodNome("Paulo da Metranca");
        a.setEmail("paulo2005@gmail.com");
        a.setSenha("1234");
        a.setDescricao("gosta de armas e municoes");
        
        agiotas.add(a);
    }
    
    public static void create(Agiota a){
        AgiotaRepository.agiotas.add(a);
    }
    
    public static void update(Agiota a){
        
        for(Agiota aAux: agiotas){
            
            if(aAux.getCodigo() == a.getCodigo()){
                aAux.setCodNome(a.getCodNome());
                aAux.setDescricao(a.getDescricao());
                aAux.setEmail(a.getEmail());
                aAux.setSenha(a.getSenha());
                
                return;
            }
            
        }
        
    }
    
    public static Agiota read(int codigo){
        
        for(Agiota aAux: agiotas){
            if(aAux.getCodigo()==codigo){
                return aAux;
            }
        }
        return null;
    }
    
    public static void delete(int codigo){
        
        for(int i = 0; i < agiotas.size();i++){
            
            if(agiotas.get(i).getCodigo()==codigo){
                
                agiotas.remove(i);
                //agiotas.remove(agiotas.get(i));
                return;
            }
            
        }
        
    }
    
    public static List<Agiota> readAll(){
        return agiotas;
    }    
}
