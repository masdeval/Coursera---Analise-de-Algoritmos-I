/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera.graph;

import coursera.sort.RSelect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Graph
{

  LinkedHashMap<Integer, Vertex> vertices = new LinkedHashMap<Integer, Vertex>();
  LinkedHashMap<Integer, Edge> edges = new LinkedHashMap<Integer, Edge>();

  private Integer getMaxVertexId()
  {
    if (vertices.isEmpty())
    {
      return 0;
    }

    return Integer.valueOf(new RSelect().find(vertices.keySet(), vertices.size() - 1).toString());
  }

  private Integer getMaxEdgeId()
  {
    if (edges.isEmpty())
    {
      return 0;
    }

    return Integer.valueOf(new RSelect().find(edges.keySet(), edges.size() - 1).toString());
  }

  public Integer getNumberOfVertices()
  {
    int count = 0;

    Iterator<Vertex> it = vertices.values().iterator();
    while (it.hasNext())
    {
      Vertex v = it.next();
      if (v.isActive)
      {
        count++;
      }
    }

    return count;
  }

  public Integer getNumberOfEdges()
  {
    int count = 0;

    Iterator<Edge> it = edges.values().iterator();
    while (it.hasNext())
    {
      Edge e = it.next();
      if (e.isActive)
      {
        count++;
      }
    }

    return count;
  }

  public Vertex addVertex(Integer id)
  {
    Vertex v = this.getVertex(id);

    if (v != null)
    {
      return v;
    }

    v = new Vertex(id);
    vertices.put(id, v);

    return v;
  }

  public Vertex addVertex()
  {
    Vertex v = new Vertex(this.getMaxVertexId() + 1);
    vertices.put(v.getId(), v);

    return v;
  }

  public Vertex getVertex(Integer id)
  {
    return vertices.get(id);
  }

  private Edge addEdge(Integer id)
  {
    Edge e = this.getEdge(id);

    if (e != null)
    {
      return e;
    }

    e = new Edge(id);
    edges.put(id, e);

    return e;
  }

  private Edge addEdge(Integer id, Float weight)
  {
    Edge e = this.getEdge(id);
    
    if (e != null)
    {
      e.weight = weight;
      return e;
    }

    e = new Edge(id);
    e.weight = weight;
    edges.put(id, e);

    return e;
  }
  
  private Edge addEdge()
  {
    Edge e = new Edge(this.getMaxEdgeId() + 1);
    edges.put(e.getId(), e);

    return e;
  }

  public Edge getEdge(Integer id)
  {
    return edges.get(id);
  }

  public void addEdge(VertexInterface v1, VertexInterface v2)
  {
    //Vertex V1 = getReal(new Vertex(v1));
    //Vertex V2 = getReal(new Vertex(v2));

    Vertex V1 = (Vertex) v1;
    Vertex V2 = (Vertex) v2;

    //Previne arestas paralelas
    if (V1.verticesAdjacents.contains(V2))
    {
      return;
    }
    if (V2.verticesAdjacents.contains(V1))
    {
      return;
    }

    Edge e = this.addEdge();
    e.v1 = (Vertex) v1;
    e.v2 = (Vertex) v2;

    ((Vertex) v1).addEdge(e);
    ((Vertex) v2).addEdge(e);
    V1.addVertex(V2);
    V2.addVertex(V1);
  }

  public void addEdge(VertexInterface v1, VertexInterface v2, Edge e)
  {
    //Vertex V1 = getReal(new Vertex(v1));
    //Vertex V2 = getReal(new Vertex(v2));

    Vertex V1 = (Vertex) v1;
    Vertex V2 = (Vertex) v2;

    //Previne arestas paralelas
    if (V1.verticesAdjacents.contains(V2))
    {
      return;
    }
    if (V2.verticesAdjacents.contains(V1))
    {
      return;
    }

    
    e.v1 = (Vertex) v1;
    e.v2 = (Vertex) v2;

    ((Vertex) v1).addEdge(e);
    ((Vertex) v2).addEdge(e);
    V1.addVertex(V2);
    V2.addVertex(V1);
  }

  public void addEdge(VertexInterface v1, VertexInterface v2, Float weight)
  {
    //Vertex V1 = getReal(new Vertex(v1));
    //Vertex V2 = getReal(new Vertex(v2));

    Vertex V1 = (Vertex) v1;
    Vertex V2 = (Vertex) v2;

    //Previne arestas paralelas
    if (V1.verticesAdjacents.contains(V2))
    {
      return;
    }
    if (V2.verticesAdjacents.contains(V1))
    {
      return;
    }

    Edge e = this.addEdge();
    e.weight = weight;
    e.v1 = (Vertex) v1;
    e.v2 = (Vertex) v2;

    ((Vertex) v1).addEdge(e);
    ((Vertex) v2).addEdge(e);
    V1.addVertex(V2);
    V2.addVertex(V1);
  }

  protected Vertex getReal(Vertex v)
  {
    if (!exist(v))
    {
      throw new RuntimeException("Vertice " + v + " não existe.");
    }

    return this.vertices.get(v.getId());
  }

  private Edge getReal(Edge e)
  {
    if (!exist(e))
    {
      throw new RuntimeException("Aresta " + e + " não existe.");
    }

    return this.edges.get(e.getId());
  }

  public boolean exist(Vertex v)
  {

    return this.vertices.containsKey(v.getId());

  }

  public boolean exist(Edge e)
  {
    return this.edges.containsKey(e.getId());
  }

  public void pickSomeEdgeForContraction()
  {
    Random x = new Random();

    while (true)
    {
      int indice = x.nextInt(edges.size()); //nextInt irá retornar um inteiro entre 0 e edges.size()

      Edge edge = (Edge) edges.values().toArray()[indice];

      if (!edge.isActive)
      {
        continue;
      } else
      {

        edge.isActive = false;
        //cria o supervertice e redireciona as arestas 
        Vertex sv = new SuperVertex(getMaxVertexId() + 1);


        //essa aresta esta ligada em um supervertice
        if (edge.v1.sv != null)
        {
          SuperVertex svV1 = edge.v1.sv;
          ((SuperVertex) sv).edgesAdjacents.addAll(svV1.edgesAdjacents);
          //((SuperVertex) sv).verticesAdjacents.addAll(svV1.verticesAdjacents);
          ((SuperVertex) sv).verticesEnglobados.addAll(svV1.verticesEnglobados);
          Iterator<Vertex> it = svV1.verticesEnglobados.iterator();
          //acerta o supervertice 
          while (it.hasNext())
          {
            Vertex v = it.next();
            v.sv = (SuperVertex) sv;
          }
          vertices.remove(svV1.getId());
        }

        //essa aresta esta ligada em um supervertice
        if (edge.v2.sv != null)
        {
          SuperVertex svV2 = edge.v2.sv;
          ((SuperVertex) sv).edgesAdjacents.addAll(svV2.edgesAdjacents);
          //((SuperVertex) sv).verticesAdjacents.addAll(svV2.verticesAdjacents);
          ((SuperVertex) sv).verticesEnglobados.addAll(svV2.verticesEnglobados);
          Iterator<Vertex> it = svV2.verticesEnglobados.iterator();
          while (it.hasNext())
          {
            Vertex v = it.next();
            v.sv = (SuperVertex) sv;
          }
          vertices.remove(svV2.getId());
        }

        edge.v1.isActive = false;
        edge.v2.isActive = false;

        ((SuperVertex) sv).addVertexEnglobado(edge.v1);
        ((SuperVertex) sv).addVertexEnglobado(edge.v2);


        edge.v1.sv = (SuperVertex) sv;
        edge.v2.sv = (SuperVertex) sv;

        /*Iterator<Edge> it = edge.v1.edgesAdjacents.iterator();
         while (it.hasNext())
         {
         sv.addEdge(it.next());
         }*/

        sv.edgesAdjacents.addAll(edge.v1.edgesAdjacents);
        //sv.verticesAdjacents.addAll(edge.v1.verticesAdjacents);

        /*it = edge.v2.edgesAdjacents.iterator();
         while (it.hasNext())
         {
         sv.addEdge(it.next());
         }*/

        sv.edgesAdjacents.addAll(edge.v2.edgesAdjacents);
        //sv.verticesAdjacents.addAll(edge.v2.verticesAdjacents);

        this.vertices.put(sv.getId(), sv);
        this.removeSelfLoops();
        return;
      }
    }

  }

  private void removeSelfLoops()
  {
    Iterator<Edge> it = edges.values().iterator();
    while (it.hasNext())
    {
      Edge edge = it.next();

      if (edge.isActive)
      {
        if (edge.v1 == edge.v2)//self loop
        {
          edge.isActive = false;
          continue;
        }

        //Trata o caso onde havia dois vertices e duas (ou mais) arestas paralelas
        //Uma delas foi escolhida para contracao, tornando os dois vertices inativos
        //A(s) outra(s) viraram sel-loops no super vertice que foi criado
        /*if (edge.v1.isActive == false && edge.v2.isActive == false)
         {
         edge.isActive = false;
         continue;
         }*/

        if (edge.v1.sv != null && edge.v2.sv != null && edge.v1.sv == edge.v2.sv)
        {
          edge.isActive = false;
          continue;
        }
      }

    }
  }
  
  public List<VertexInterface> getVertices()
  {
    
    List aux =  new ArrayList<VertexInterface>(vertices.values()); 
    Collections.sort(aux);
    
    return aux;
  }
  
  public List<EdgeInterface> getEdges()
  {
    List aux =  new ArrayList<EdgeInterface>(edges.values()); 
    Collections.sort(aux);
    
    return aux;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    Iterator<Vertex> it = vertices.values().iterator();
    while (it.hasNext())
    {
      Vertex v = it.next();

      if (v.isActive)
      {
        sb.append("\n Vertice " + v + " esta ligado aos vertices: ");

        LinkedHashSet dd = new LinkedHashSet(v.edgesAdjacents);//para remover duplicatas
        Iterator<Edge> arestasDeV = dd.iterator();
        while (arestasDeV.hasNext())
        {
          Edge e = arestasDeV.next();
          if (e.isActive)//verifica se aresta esta ativa
          {
            //aresta conecta dois supervertices e v tem que ser um supervertice
            if (!e.v1.isActive && !e.v2.isActive)//vertices v1 e v2 nao estao ativos
            {
              if (!(v instanceof SuperVertex))
              {
                throw new RuntimeException("Foi detectado que " + v + " deveria ser supervertice mas nao eh.");
              }
              //v esta conectado ao supervertice e.v2.sv
              if (v == e.v1.sv)
              {
                sb.append(e.v2.sv + " pela aresta " + e + ", ");
              } else if (v == e.v2.sv)
              {
                sb.append(e.v1.sv + " pela aresta " + e + ", ");
              } else
              {
                throw new RuntimeException("Foi encontrada uma aresta ligada ao supervertice " + v + " que nao o tinha! ");
              }
            } //v eh supervertex e esta ligado em e.v2
            else if (!e.v1.isActive && v instanceof SuperVertex)
            {
              if (e.v1.sv != v)
              {
                throw new RuntimeException("O supervertex " + v + " não está em uma das pontas da aresta " + e);
              }

              sb.append(e.v2 + " pela aresta " + e + ", ");
            } //v nao eh SuperVertex, entao esta ligado ao supervertex que esta em e.v1.sv
            else if (!e.v1.isActive && !(v instanceof SuperVertex))
            {
              sb.append(e.v1.sv + " pela aresta " + e + ", ");
            } //v eh supervertex e esta ligado a e.v1
            else if (!e.v2.isActive && v instanceof SuperVertex)
            {
              if (e.v2.sv != v)
              {
                throw new RuntimeException("O supervertex " + v + " não está em uma das pontas da aresta " + e);
              }

              sb.append(e.v1 + " pela aresta " + e + ", ");
            } else if (!e.v2.isActive && !(v instanceof SuperVertex))
            {
              sb.append(e.v2.sv + " pela aresta " + e + ", ");
            } //tanto e.v1 como e.v2 estao ativos entao ambos sao Vertex e v tambem tem que ser
            else
            {
              if (e.v1 == v)
              {
                sb.append(e.v2 + " pela aresta " + e + ", ");
              } else
              {
                sb.append(e.v1 + " pela aresta " + e + ", ");
              }
            }
          }
        }
      }
    }

    return sb.toString();
  }

  public void setVertexActiveTrue()
  {
    Iterator<Vertex> it = this.vertices.values().iterator();
    while (it.hasNext())
    {
      Vertex v = it.next();
      v.isActive = true;
    }
  }

  public void setVertexActiveFalse()
  {
    Iterator<Vertex> it = this.vertices.values().iterator();
    while (it.hasNext())
    {
      Vertex v = it.next();
      v.isActive = false;
    }
  }

  public void setVertexVisitedTrue()
  {
    Iterator<Vertex> it = this.vertices.values().iterator();
    while (it.hasNext())
    {
      Vertex v = it.next();
      v.isVisited = true;
    }
  }

  public void setVertexVisitedFalse()
  {
    Iterator<Vertex> it = this.vertices.values().iterator();
    while (it.hasNext())
    {
      Vertex v = it.next();
      v.isVisited = false;
    }
  }

  
  public void setEdgeActiveTrue()
  {
    Iterator<Edge> arestas = this.edges.values().iterator();
    while (arestas.hasNext())
    {
      Edge e = arestas.next();
      e.isActive = true;
    }
  }

  public void setEdgeActiveFalse()
  {
    Iterator<Edge> arestas = this.edges.values().iterator();
    while (arestas.hasNext())
    {
      Edge e = arestas.next();
      e.isActive = false;
    }
  }

  public void restaureOriginalGraphAfterMinCut()
  {
    //Remove todos os supervertex
    //Ativa os vertices
    //Passa para nulo as referencias ao supervertex dentro dos vertex
    Iterator<Vertex> it = vertices.values().iterator();
    while (it.hasNext())
    {
      Vertex v = it.next();
      if (v instanceof SuperVertex)
      {
        it.remove();
        continue;
      }

      v.isActive = true;
      v.sv = null;
    }

    //Passa as arestas para ativa
    Iterator<Edge> arestas = edges.values().iterator();
    while (arestas.hasNext())
    {
      Edge e = arestas.next();
      e.isActive = true;
    }
  }

  @Override
  public Graph clone()
  {
    Graph g = new Graph();

    g.edges = new LinkedHashMap<Integer, Edge>();
    Iterator<Edge> arestas = this.edges.values().iterator();
    while (arestas.hasNext())
    {
      Edge e = arestas.next();
      g.edges.put(e.getId(), e.clone());
    }
    g.vertices = new LinkedHashMap<Integer, Vertex>();
    Iterator<Vertex> vertices = this.vertices.values().iterator();
    while (vertices.hasNext())
    {
      Vertex v = vertices.next();
      g.vertices.put(v.getId(), v.clone());
    }

    return g;
  }
}

class GraphStructure
{

  Integer id;

  public GraphStructure(Integer id)
  {
    this.id = id;
  }

  public Integer getId()
  {
    return this.id;
  }

  @Override
  public int hashCode()
  {

    return 31 * 17 + this.id;
  }

  @Override
  public boolean equals(Object o)
  {

    if (!(o instanceof GraphStructure))
    {
      return false;
    }

    GraphStructure v = (GraphStructure) o;
    return this.id == v.id;
  }
}


class Vertex extends GraphStructure implements VertexInterface, Comparable
{
  boolean isActive = true;
  boolean isVisited = false;
  float score = 0f;
  
  SuperVertex sv = null;

  Vertex(Integer x)
  {
    super(x);
  }
  ArrayList<Edge> edgesAdjacents = new ArrayList<Edge>();
  ArrayList<Vertex> verticesAdjacents = new ArrayList<Vertex>();

  public Float getScore()
  {
    return score;
  }
  
  void addEdge(Edge e)
  {
    if (!edgesAdjacents.contains(e))
    {
      edgesAdjacents.add(e);
    }
  }

  void addVertex(Vertex v)
  {
    if (!verticesAdjacents.contains(v))
    {
      verticesAdjacents.add(v);
    }
  }

  @Override
  public String toString()
  {
    return " vertex = " + this.getId();
  }

  @Override
  public Vertex clone()
  {
    Vertex v = new Vertex(this.getId());
    v.isActive = this.isActive;
    if (this.sv != null)
    {
      v.sv = this.sv.clone();
    }

    v.edgesAdjacents = new ArrayList<Edge>();
    Iterator<Edge> arestas = this.edgesAdjacents.iterator();
    while (arestas.hasNext())
    {
      v.edgesAdjacents.add(arestas.next().clone());
    }

    v.verticesAdjacents = new ArrayList<Vertex>();
    Iterator<Vertex> vertices = this.verticesAdjacents.iterator();
    while (vertices.hasNext())
    {
      v.verticesAdjacents.add(vertices.next().clone());
    }

    return v;
  }

  public int compareTo(Object t)
  {
     if(!(t instanceof Vertex))
     {
       throw new RuntimeException("Impossível comparar " + t + " com " + this);
     }
     
     Vertex v = (Vertex) t;
     
     if(this.getId() > v.getId())
       return 1;
     else if(this.getId() == v.getId())
       return 0;
     else
       return -1;
  }
}

class SuperVertex extends Vertex
{

  ArrayList<Vertex> verticesEnglobados = new ArrayList<Vertex>();

  void addVertexEnglobado(Vertex v)
  {
    if (!verticesEnglobados.contains(v))
    {
      verticesEnglobados.add(v);
    }
  }

  SuperVertex(Integer id)
  {
    super(id);
  }

  @Override
  public SuperVertex clone()
  {
    SuperVertex sv = new SuperVertex(this.getId());
    sv.isActive = this.isActive;
    sv.verticesEnglobados = new ArrayList<Vertex>();
    Iterator<Vertex> it = this.verticesEnglobados.iterator();
    while (it.hasNext())
    {
      sv.verticesEnglobados.add(it.next().clone());
    }

    return sv;
  }
}

class Edge extends GraphStructure implements EdgeInterface
{
  boolean isActive = true;
  boolean isInverted = false;
  Vertex v1 = null;
  Vertex v2 = null;
  Float weight = 0f;

  Edge(Integer x)
  {
    super(x);
  }

  @Override
  public String toString()
  {
    return " edge = " + this.getId();
  }

  @Override
  public Edge clone()
  {
    Edge e = new Edge(this.getId());
    e.v1 = this.v1.clone();
    e.v2 = this.v2.clone();

    return e;
  }
}