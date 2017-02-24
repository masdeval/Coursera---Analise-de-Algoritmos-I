/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author admin
 */
public class ComputeStronglyConnectedComponents
{

  List<VertexInterface> lideres;
  Integer t = 0;

  public List<VertexInterface> compute(DirectedGraph g)
  {
    lideres = new ArrayList<VertexInterface>();
    //inverte o grafo
    g.invertEdges();
    //calcula numeros magicos
     List<Vertex> vertices = g.getVerticesSortedAsList();
    for (int i = vertices.size() - 1; i > 0; i--)//varre a lista do maior para o menor
    {
      Vertex v = vertices.get(i);

      if (v.isVisited)
      {
        continue;
      }
      depthFirstSearchFirstFase(g, v);
    }
    
    //calcula componentes
    g.invertEdges();
    vertices = g.getVerticesSortedAsList();
    for (int i = vertices.size() - 1; i > 0; i--)//varre a lista do maior para o menor
    {
      Vertex v = vertices.get(i);

      if (v.isVisited)
      {
        continue;
      }
      lideres.add(v);
      depthFirstSearchSecondFase(g, v);
    }
    g.resetVisitedFlag();
    return lideres;
  }

  private void depthFirstSearchFirstFase(DirectedGraph g, Vertex v)
  {
    v.isVisited = true;
    Iterator<Vertex> it = v.verticesAdjacents.iterator();
    while (it.hasNext())
    {
      Vertex aux = it.next();
      if (!aux.isVisited)
      {
        depthFirstSearchFirstFase(g, aux);
      }
    }
    t++;
    v.id = t;
  }
  
  private void depthFirstSearchSecondFase(DirectedGraph g, Vertex v)
  {
    v.isVisited = true;
    Iterator<Vertex> it = v.verticesAdjacents.iterator();
    while (it.hasNext())
    {
      Vertex aux = it.next();
      if (!aux.isVisited)
      {
        depthFirstSearchSecondFase(g, aux);
      }
    }

  }

  
}
