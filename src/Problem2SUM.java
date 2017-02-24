
import coursera.graph.VertexInterface;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ListIterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class Problem2SUM
{

  public static void main(String[] arg)
  {

    String line;
    HashMap<Long,Long> hash;    
    hash = new HashMap<Long,Long>();
    
    try
    {
      BufferedReader input = new BufferedReader(
              new FileReader("C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos I\\algo1_programming_prob_2sum.txt"));

      try
      {

        while ((line = input.readLine()) != null)
        {
            hash.put(Long.valueOf(line),Long.valueOf(line));
        }
      } finally
      {
        input.close();
      }
    } catch (Exception ex)
    {
      ex.printStackTrace();
    }

   int count = 0;
   
   for(int t = -100; t <= 100; t++)
   {     
     Iterator<Long> it = hash.values().iterator();
     while(it.hasNext())
     {
       Long y = it.next();
       if(hash.get(t-y) != null && hash.get(t-y) != y)
       {            
         count++;
         break;
       }
     }
   }
  
   
    System.out.println(count);
    
  }
}
