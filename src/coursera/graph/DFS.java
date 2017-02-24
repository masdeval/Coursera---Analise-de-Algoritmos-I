/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera.graph;

import java.util.Iterator;

/**
 *
 * @author admin
 */
public class DFS
{

  public static Integer findAll(DirectedGraph g, VertexInterface v)
  {
    int numberOfReachableElelements = 0;    
    ((Vertex)v).isVisited = true;
    Iterator<Vertex> it = ((Vertex)v).verticesAdjacents.iterator();
    while (it.hasNext())
    {
      Vertex aux = it.next();
      if (!aux.isVisited)
      {
        numberOfReachableElelements = numberOfReachableElelements + findAll(g, aux);        
      }
    }
    return ++numberOfReachableElelements;
  }
}
