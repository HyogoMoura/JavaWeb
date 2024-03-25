package devcaotics.repository;


import devcaotics.entities.Professor;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diogo
 */
public class ProfessorRepository {
    private static List<Professor> profesores;
    
    static{
        profesores = new ArrayList<>();
    }
    
    public static void create(Professor p){
        ProfessorRepository.profesores.add(p);
    }

    public static void updates(Professor p) {
        for (Professor prof : profesores) {
            if (prof.getCodigo() == p.getCodigo()) {
                prof.setNome(p.getNome());
                prof.setEmail(p.getEmail());
                prof.setSenha(p.getSenha());
            }
        }
    }

    public static Professor read(int codigo) {
        for (Professor prof : profesores) {
            if (prof.getCodigo() == codigo) {
                return prof;
            }
        }
        return null;
    }
    public static void delete(int codigo){
        
        for (int i =0; i < profesores.size();i++){
            if(profesores.get(i).getCodigo()==codigo){
                profesores.remove(i);
                return;
            }
        }
    }
        public static List<Professor> readAll() {
        return profesores;
    }
}
