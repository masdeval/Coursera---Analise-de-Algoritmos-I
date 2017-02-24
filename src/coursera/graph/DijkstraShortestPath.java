/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera.graph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author admin
 */
public class DijkstraShortestPath
{
  
  public static void compute (Graph g,Vertex initialVertex)
  {
    
    ArrayList<Vertex> alreadySeen = new ArrayList<Vertex>();
    alreadySeen.add(initialVertex);
    
    while(alreadySeen.size() < g.vertices.size())
    {
      Iterator<Vertex> it = alreadySeen.iterator();
      VertexInterface winner = null;
      float score = Float.MAX_VALUE;              
   
      while(it.hasNext())
      {
        Vertex v = it.next();        
        //verifica a lista de adjacencias de v
        Iterator<Edge> adjacencias = v.edgesAdjacents.iterator();
        while(adjacencias.hasNext())
        {
          Edge e = adjacencias.next();
          if(alreadySeen.contains(e.v1) && alreadySeen.contains(e.v2))//caso a aresta leve a um vertice que já esta em alreadySeen
            continue;
          if(e.weight + v.score < score)
          {
            score = e.weight + v.score;
            if(e.v1 != v)
              winner = e.v1;
            else
              winner = e.v2;
          }         
        }        
      }
      ((Vertex)winner).score = score;
      alreadySeen.add((Vertex)winner);
    }
    
  }
  
}
