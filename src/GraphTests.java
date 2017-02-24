/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import coursera.graph.ComputeStronglyConnectedComponents;
import coursera.graph.DFS;
import coursera.graph.DijkstraShortestPath;
import coursera.graph.DirectedGraph;
import coursera.graph.EdgeInterface;
import coursera.graph.Graph;
import coursera.graph.VertexInterface;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


/**
 *
 * @author admin
 */
public class GraphTests
{

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    // TODO code application logic here

    String line;

    
    /*
    //Procura o min-cut
    int mincut = 10000;

    Graph g = new Graph();
    try
    {
      BufferedReader input = new BufferedReader(
              new FileReader("C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos I\\kargerMinCut.txt"));

      try
      {

        while ((line = input.readLine()) != null)
        {
          String[] elements = line.split("\t");

          VertexInterface v1 = g.addVertex(Integer.valueOf(elements[0]));

          for (int i = 1; i < elements.length; i++)
          {
            VertexInterface v2 = g.addVertex(Integer.valueOf(elements[i]));
            g.addEdge(v1, v2);
          }

        }
      } finally
      {
        input.close();
      }
    } catch (Exception ex)
    {
      ex.printStackTrace();
    }
 
    for (int k = 1; k < 10000; k++)
    {
      while (g.getNumberOfVertices() > 2)
      {
        g.pickSomeEdgeForContraction();
      }

      if (g.getNumberOfEdges() < mincut)
      {
        mincut = g.getNumberOfEdges();        
      }

      g.restaureOriginalGraphAfterMinCut();
    }//fim for

    System.out.println("Numero de arestas do min-cut: " + mincut);

    */
    /*
    //Strongly Connected Components
    DirectedGraph dg = new DirectedGraph();
    
    try
    {
      BufferedReader input = new BufferedReader(
              new FileReader("C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos I\\directedGraph.txt"));

      try
      {

        while ((line = input.readLine()) != null)
        {
          String[] elements = line.split(" ");

          VertexInterface v1 = dg.addVertex(Integer.valueOf(elements[0]));

          for (int i = 1; i < elements.length; i++)
          {
            VertexInterface v2 = dg.addVertex(Integer.valueOf(elements[i]));
            dg.addEdge(v1, v2);
          }

        }
      } finally
      {
        input.close();
      }
    } catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
    
   ComputeStronglyConnectedComponents scc = new ComputeStronglyConnectedComponents();
   
   List<VertexInterface> lideres = scc.compute(dg);
   
   System.out.println(lideres.size());
      
   ArrayList<Integer> numberElements = new ArrayList<Integer>();
   
   for(VertexInterface v : lideres)
   {
     numberElements.add(DFS.findAll(dg, v));
   }
   
   System.out.println(numberElements.size());
   
   Collections.sort(numberElements);
   
   for(int i = numberElements.size()-1; i >= (numberElements.size()-5); i--)
   {
     System.out.println(numberElements.get(i));
   }
   
   */

   //Dijkstra Shortest Path 
   /*
    Graph g = new Graph();
    
    try
    {
      BufferedReader input = new BufferedReader(
              new FileReader("C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos I\\dijkstraData.txt"));

      try
      {

        while ((line = input.readLine()) != null)
        {
          String[] elements = line.split("\t");

          VertexInterface v1 = g.addVertex(Integer.valueOf(elements[0]));

          for (int i = 1; i < elements.length; i++)
          {
            String[] pesos = elements[i].split(",");
            VertexInterface v2 = g.addVertex(Integer.valueOf(pesos[0]));
            g.addEdge(v1, v2, Float.valueOf(pesos[1]));
          }

        }
      } finally
      {
        input.close();
      }
    } catch (Exception ex)
   {
      ex.printStackTrace();
   }
   
   System.out.println(g);
   DijkstraShortestPath.compute(g, g.getVertex(1));
   Iterator<VertexInterface> it = g.getVertices().iterator();
   while(it.hasNext())
   {
     VertexInterface v = it.next();
     System.out.println(v.toString() + " score:" + v.getScore());
   }
       
  */     
  }
  
}
