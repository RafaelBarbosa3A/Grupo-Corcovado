/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado;

import br.senac.corcovado.controller.adapter.Auth;
import br.senac.corcovado.model.entity.Pessoa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Diego
 */
public class Utils {
    
    public static <T> ArrayList<T> asList(Iterable<T> iterable) {
        ArrayList<T> asList = new ArrayList<>();
        for (Iterator<T> iterator = iterable.iterator(); iterator.hasNext();) {
            asList.add(iterator.next());
        }
        return asList;
    }
    
    public static <T> ArrayList<T> asList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
    
    public static <T> Set<T> asSet(Iterable<T> iterable) {
        HashSet<T> set = new HashSet<>();
        for (Iterator<T> iterator = iterable.iterator(); iterator.hasNext();) {
            set.add(iterator.next());
        }
        return set;
    }    
}
