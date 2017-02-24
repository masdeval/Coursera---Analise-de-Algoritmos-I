/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package coursera.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author masdeval
 */
public class MergeSort {


    public static Collection<? extends Comparable> sort(Collection<? extends Comparable> elements)
    {	
	int num = elements.size();
	if(num <= 1) return elements;
	Collection<? extends Comparable> a = sort(Arrays.asList(Arrays.copyOfRange(elements.toArray(new Comparable[0]),0,num/2)));
	Collection<? extends Comparable> b = sort(Arrays.asList(Arrays.copyOfRange(elements.toArray(new Comparable[0]),num/2,num)));
	//join A and B
	return join(a,b);

    }

private static Collection<Comparable> join(Collection<? extends Comparable> a, Collection<? extends Comparable> b)
    {
    	Comparable[] arrayA = a.toArray(new Comparable[0]);
	Comparable[] arrayB = b.toArray(new Comparable[0]);
	Collection<Comparable> result = new ArrayList<Comparable>();

	int i = 0, j = 0;
	for(; i < arrayA.length && j < arrayB.length; )
	{
	   if(arrayA[i].compareTo(arrayB[j]) <= 0)
	   {
	       result.add(arrayA[i]);
	       i++;
	   }
	   else {
	       result.add(arrayB[j]);
	       j++;
	   }
	}

	if(i < arrayA.length)
	{
	   while( i < arrayA.length)
	   {
	       result.add(arrayA[i]);
	       i++;
	   }

	}

	if(j < arrayB.length)
	{
	   while( j < arrayB.length)
	   {
	       result.add(arrayB[j]);
	       j++;
	   }

	}

	return result;
	/*Iterator<? extends Comparable> itA = a.iterator();
	Iterator<? extends Comparable> itA = a.iterator();
	Iterator<? extends Comparable> itB = b.iterator();
	Collection<Comparable> result = new ArrayList<Comparable>();
	while(itA.hasNext())
	{
	    Comparable i = itA.next();
	    if (itB.hasNext())
	    {
		Comparable j = itB.next();
		if(i.compareTo(j) <= 1)
		    result.add(i);
		else
		    result.add(j);
	    }
	}*/

}

}
