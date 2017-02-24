
import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class MedianMaintenanceHeap
{

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    // TODO code application logic here
    PriorityQueue<Long> heap = new PriorityQueue<Long>();
    String line;
    long result = 0;
    
    try
    {
      BufferedReader input = new BufferedReader(
              new FileReader("C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos I\\Median.txt"));

      try
      {
        ArrayList<Long> aux = new ArrayList<Long>();  
        while ((line = input.readLine()) != null)
        {
            aux.add(Long.valueOf(line));
            heap = new PriorityQueue<Long>(aux);
            long mediam = 0;
            int i;
            
            if(heap.size()%2 == 0)
              i = heap.size()/2;
            else
              i = (heap.size() + 1) / 2;
            
            for(; i > 0 ; i--)
            {
              mediam = heap.poll();
            }
            result += mediam;
        }
      } finally
      {
        input.close();
      }
    } catch (Exception ex)
    {
      ex.printStackTrace();
    }

    System.out.println(result%10000);
    
  }
}
