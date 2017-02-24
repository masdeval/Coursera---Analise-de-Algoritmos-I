/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author admin
 */
public class DirectedGraph
{

  private Graph graph = new Graph();

  public VertexInterface addVertex()
  {
    return graph.addVertex();
  }

  public VertexInterface addVertex(Integer v)
  {
    return graph.addVertex(v);
  }

  public void addEdge(VertexInterface tail, VertexInterface head)
  {
    //Vertex V1 = getReal(new Vertex(v1));
    //Vertex V2 = getReal(new Vertex(v2));

    Vertex V1 = (Vertex) tail;
    Vertex V2 = (Vertex) head;

    //Edge e = this.addEdge();
    //e.v1 = (Vertex) tail;
    //e.v2 = (Vertex) head;

    // ((Vertex) tail).addEdge(e);
    // ((Vertex) v2).addEdge(e);
    V1.addVertex(V2);
  }

  public void invertEdges()
  {
    this.resetVisitedFlag();
    Iterator<Vertex> it = graph.vertices.values().iterator();

    while (it.hasNext())//varre todos os vertices do grafo
    {
      Vertex v = it.next();
      v.isVisited = true;
      
      ListIterator<Vertex> vertices = v.verticesAdjacents.listIterator();

      while (vertices.hasNext())//varre todas as adjacencias de v
      {
        Vertex verticeAdjacente = vertices.next();
        if (verticeAdjacente.getId() == -1)
        {
          vertices.remove();
          break;
        }
        
        if(v == verticeAdjacente)
          continue;

        //inverte a relacao vertice v -> verticeAdjacente
        //significa que nao foi inserido na lista de adjacencias deste vertice algum outro
        if (verticeAdjacente.isVisited == false)
        {
          verticeAdjacente.isVisited = true;   
          //pode ser que insere duplicado na lista de adjacencias mas vai remover depois
          verticeAdjacente.verticesAdjacents.add(new Vertex(-1));//apenas para marcar o inicio da nova lista de adjacencias
        }
        
        //verticeAdjacente -> v
        verticeAdjacente.verticesAdjacents.add(v);//pode ser que insere duplicado na lista de adjacencias mas vai remover depois
        vertices.remove(); //nao pode ter self-loop senao dá pau aqui!!!!!
      }
    }
    this.resetVisitedFlag();
  }

  public void resetVisitedFlag()
  {
    graph.setVertexVisitedFalse();
  }
  
  public List<Vertex> getVerticesSortedAsList()
  {
    List<Vertex> list = new ArrayList<Vertex>(graph.vertices.values());
    Collections.sort(list);
    return list;
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    Iterator<Vertex> it = graph.vertices.values().iterator();
    while (it.hasNext())
    {
      Vertex v = it.next();

      if (v.isActive)
      {
        sb.append("\n Vertice " + v + " esta ligado aos vertices: ");

        Iterator<Vertex> verticesAdjacentes = v.verticesAdjacents.iterator();
        while(verticesAdjacentes.hasNext())
        {
          Vertex vAdjacente = verticesAdjacentes.next();
          if(vAdjacente.isActive)
          {
            sb.append(" => " + vAdjacente + ", ");
          }
        }
      }
    }

    return sb.toString();
  }
}
