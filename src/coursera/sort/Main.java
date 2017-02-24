/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author masdeval
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	// TODO code application logic here
	//Integer array[] = {22, 14, 12, 4, 5, 6, 1, 7, 8, 9, 10, 11,44,556,78,89,654,34,889,234};        
	Integer array[] = {3,9,8,4,6,10,2,5,7,1};
	//Integer array[] = {0,3,1000};

	System.out.println(Arrays.toString(array));

	System.out.println(Arrays.toString(QuickSort.sort(Arrays.asList(array)).toArray()));
        System.out.println("Numero de comparacoes do Quick Sort: " + QuickSort.getNumComparacoes());
        
        

	System.out.println(Arrays.toString(MergeSort.sort(Arrays.asList(array)).toArray()));

	System.out.println("O elemento procurado eh o : " + new RSelect().find(Arrays.asList(array),8));


	String line;

	ArrayList<Comparable> list = new ArrayList<Comparable>();
	try {
	    BufferedReader input = new BufferedReader(new FileReader("C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos I\\QuickSort.txt"));


	    try {

		while ((line = input.readLine()) != null) {
		    list.add(new Integer(Integer.valueOf(line)));
		    line = null;
		}
	    } finally {
		input.close();
	    }


	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	//System.out.println("Numero de inversoes eh:" + CountingInversions.getNumberOfInversions(list));


    Collection aux = QuickSort.sort(list);
    System.out.println(Arrays.toString(aux.toArray(new Comparable[0])));
    System.out.println("Numero de comparacoes do quick Sort: " + QuickSort.getNumComparacoes());

    System.out.println("O elemento medio eh o : " + new RSelect().find(list,list.size()/2));


	try {
	    BufferedWriter input = new BufferedWriter(new FileWriter("c:\\Temp\\IntegerArrayOrdenado.txt",true));

	    try {
			//Tem que criar um array list para ver a colecao retornada pelo quick sort ordenada
			Iterator it = new ArrayList(aux).iterator();
			while (it.hasNext())
			{
				input.append(it.next().toString());
				input.newLine();
			}			

	    } finally {
		input.close();
	    }


	} catch (Exception ex) {
	    ex.printStackTrace();
	}


    }//fim do main
}
