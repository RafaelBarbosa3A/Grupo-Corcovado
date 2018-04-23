/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado;

import java.util.ArrayList;
import java.util.Iterator;

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
        ArrayList<T> asList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }
}
