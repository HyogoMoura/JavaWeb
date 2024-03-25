package devcaotics.repository;

import devcaotics.entities.Cadeira;
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
public class CadeiraRepository {

    private static List<Cadeira> cadeiras;

    static {
        cadeiras = new ArrayList<>();

    }

    public static void create(Cadeira c) {
        CadeiraRepository.cadeiras.add(c);
    }

    public static void update(Cadeira c) {
        for (Cadeira cAux : cadeiras) {

            if (cAux.getCodigo() == c.getCodigo()) {
                cAux.setNome(c.getNome());
                cAux.setAno(c.getAno());
                cAux.setSemestre(c.getSemestre());

                return;
            }
        }
    }

    public static Cadeira read(int codigo) {

        for (Cadeira aAux : cadeiras) {
            if (aAux.getCodigo() == codigo) {
                return aAux;
            }
        }
        return null;
    }

    public static void delete(int codigo) {

        for (int i = 0; i < cadeiras.size(); i++) {

            if (cadeiras.get(i).getCodigo() == codigo) {

                cadeiras.remove(i);
                //agiotas.remove(agiotas.get(i));
                return;
            }

        }

    }

    public static List<Cadeira> readAll() {
        return cadeiras;
    }

}
