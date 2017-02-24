/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera.sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 *
 * @author masdeval
 */
public class QuickSort {

    private static Comparable[] aux;

    private static Long numComparacoes = 0L;
            
    public static Comparable[] getSortedArray() {
        return aux;
    }

    public static Long getNumComparacoes()
    {
        return numComparacoes;
    }
    public static Collection<? extends Comparable> sort(Collection<? extends Comparable> array) {
        if (array.size() <= 1) {
            return array;
        }
        numComparacoes = 0L;
        Comparable[] aux = array.toArray(new Comparable[0]);
        return Arrays.asList(sort(aux, 0, aux.length - 1));
    }
    
  
    /*	private static Comparable[] sort(Comparable[] array, int i, int j)
     {
     int indiceFinal = j;
     int indiceInicial = i;

     if (i == j || j < i)
     {
     return array;
     } else
     {
     Random x = new Random();
     int pivot = x.nextInt(j - i) + i; //pivot aleatorio => nextInt irá retornar um inteiro entre 0 e o tamanho do array.
     //Ocorre que em algumas chamadas o indice inical do array não é 0, por isso o + i
     //faz o pivot ser o primeiro elemento
     Comparable saved = array[i];
     array[i] = array[pivot];
     array[pivot] = saved;

     //particiona in-place em torno do pivot
     while (i < j)
     {
     if (array[i].compareTo(array[i + 1]) > 0)
     {
     Comparable aux = array[i];
     array[i] = array[i + 1];
     array[i + 1] = aux;
     i++;
     } else if (array[i].compareTo(array[i + 1]) < 0)
     {
     Comparable aux = array[i + 1];
     array[i + 1] = array[j];
     array[j] = aux;
     j--;
     } else
     {
     i++;
     }
     }
     //faz chamadas recursivas
     sort(array, indiceInicial, i - 1);
     sort(array, j + 1, indiceFinal);
     return array;
     }

     }
     */
    private static Comparable[] sort(Comparable[] array, int indiceInicial, int indiceFinal) {
        int i = indiceInicial + 1;
        int j = indiceInicial + 1;
        
        if (indiceInicial == indiceFinal || indiceFinal < indiceInicial)
        {
            return array;
        } 
         
        Random x = new Random();
        int pivot = x.nextInt(indiceFinal - indiceInicial) + indiceInicial; //pivot aleatorio => nextInt irá retornar um inteiro entre 0 e o tamanho do array.
        //Ocorre que em algumas chamadas o indice inical do array não é 0, por isso o + indiceInicial
        //faz o pivot ser o primeiro elemento
        
        //Pivot aleatorio
        //Comparable saved = array[indiceInicial];
        //array[indiceInicial] = array[pivot];
        //array[pivot] = saved;
        
        //Ultimo elemento como pivot
        //Comparable saved = array[indiceInicial];
        //array[indiceInicial] = array[indiceFinal];
        //array[indiceFinal] = saved;

        //Escolher o elemento medio entre o primeiro, meio e ultimo elemento
        int meio = ((indiceFinal - indiceInicial)/2) + indiceInicial;
        //decide qual é o elemento mediano
        int resultado;
        //testa se primeiro menor que ultimo
        if (array[indiceInicial].compareTo(array[indiceFinal]) < 0) 
        {
            //testa se primeiro menor que meio
            if (array[indiceInicial].compareTo(array[meio]) < 0)
            {
                //procura o segundo menor
                if (array[meio].compareTo(array[indiceFinal]) < 0)
                {
                    resultado = meio;
                }
                else
                    resultado = indiceFinal;
            }
            //o primeiro é menor que o ultimo e maior que o do meio entao ele é o mediano
            else{
                
                resultado = indiceInicial;
            }
        }    
        else
        //o primeiro elemento é maior que o ultimo
        {
            //se for menor que o meio, ele eh o mediano
            if (array[indiceInicial].compareTo(array[meio]) < 0) 
            {
                resultado = indiceInicial;
            }
            else
            //se o primeiro eh maior que o ultimo e maior que o meio
            {
                //procura o maior entre meio e ultimo
                if (array[meio].compareTo(array[indiceFinal]) > 0) 
                {
                    resultado = meio;                    
                }
                else
                {
                    resultado = indiceFinal;
                }
            }           
               
        }
        
        //Troca o primeiro elemento pelo mediano
        //Comparable saved = array[indiceInicial];
        //array[indiceInicial] = array[resultado];
        //array[resultado] = saved;

        
        //particiona in-place em torno do pivot
        for (; j <= indiceFinal; j++) {
            numComparacoes ++;
            if (array[j].compareTo(array[indiceInicial]) < 0) {                
                Comparable aux = array[i];
                array[i] = array[j];
                array[j] = aux;
                i++;
            }
        }
        //coloca o pivo na posicao correta
        Comparable aux = array[indiceInicial];
        array[indiceInicial] = array[i - 1];
        array[i - 1] = aux;

        sort(array, indiceInicial, i - 2);    
        sort(array, i, indiceFinal);
        
        return array;
        

    }
}
