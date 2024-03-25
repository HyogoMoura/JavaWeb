/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devcaotics.repository;


import devcaotics.entities.MetodoFila;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diogo
 */
public class MetodoFilaRepository {
        private static List<MetodoFila> metodos;

    static {
        metodos = new ArrayList<>();

    }

    public static void create(MetodoFila m) {
        MetodoFilaRepository.metodos.add(m);
    }

    public static void update(MetodoFila m) {
        for (MetodoFila mAux : metodos) {

            if (mAux.getCodigo() == m.getCodigo()) {
                mAux.setDescricaoCurta(m.getDescricaoCurta());
                mAux.setDescricaoLonga(m.getDescricaoLonga());
                return;
            }
        }
    }

    public static MetodoFila read(int codigo) {

        for (MetodoFila mAux : metodos) {
            if (mAux.getCodigo() == codigo) {
                return mAux;
            }
        }
        return null;
    }

    public static void delete(int codigo) {

        for (int i = 0; i < metodos.size(); i++) {

            if (metodos.get(i).getCodigo() == codigo) {

                metodos.remove(i);
                //agiotas.remove(agiotas.get(i));
                return;
            }

        }

    }

    public static List<MetodoFila> readAll() {
        return metodos;
    }
    
}
