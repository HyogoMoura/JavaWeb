/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devcaotics.repository;

import devcaotics.entities.Estudante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diogo
 */
public class EstudanteRepository {
    
    private static List<Estudante> estudantes;
    
    static{
        estudantes = new ArrayList<>();
    }
    
    public static void create(Estudante e){
        EstudanteRepository.estudantes.add(e);
    }

    public static void update(Estudante e) {
        for (Estudante est : estudantes) {
            if (est.getCodigo() == e.getCodigo()) {
                est.setNome(e.getNome());
                est.setEmail(e.getEmail());
                est.setAnoEntrada(e.getAnoEntrada());
                est.setSenha(e.getSenha());
            }
        }
    }

    public static Estudante read(int codigo) {
        for (Estudante est : estudantes) {
            if (est.getCodigo() == codigo) {
                return est;
            }
        }
        return null;
    }
    public static void delete(int codigo){
        
        for (int i =0;i< estudantes.size();i++){
            if(estudantes.get(i).getCodigo()==codigo){
                estudantes.remove(i);
                return;
            }
        }
    }
        public static List<Estudante> readAll() {
        return estudantes;
    }
}
