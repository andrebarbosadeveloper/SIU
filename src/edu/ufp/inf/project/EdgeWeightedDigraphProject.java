/******************************************************************************
 *  Compilation:  javac EdgeWeightedDigraph.java
 *  Execution:    java EdgeWeightedDigraph digraph.txt
 *  Dependencies: Bag.java DirectedEdge.java
 *  Data files:   https://algs4.cs.princeton.edu/44st/tinyEWD.txt
 *                https://algs4.cs.princeton.edu/44st/mediumEWD.txt
 *                https://algs4.cs.princeton.edu/44st/largeEWD.txt
 *
 *  An edge-weighted digraph, implemented using adjacency lists.
 *
 ******************************************************************************/

package edu.ufp.inf.project;

import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *  The {@code EdgeWeightedDigraph} class represents a edge-weighted
 *  digraph of vertices named 0 through <em>V</em> - 1, where each
 *  directed edge is of type {@link DirectedEdge} and has a real-valued weight.
 *  It supports the following two primary operations: add a directed edge
 *  to the digraph and iterate over all of edges incident from a given vertex.
 *  It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which 
 *  is a vertex-indexed array of {@link Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the edges incident from a given vertex, which takes
 *  time proportional to the number of such edges.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class EdgeWeightedDigraphProject implements Serializable {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;                // number of vertices in this digraph
    private int E;                      // number of edges in this digraph
    private Bag<DirectedEdgeProject>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;             // indegree[v] = indegree of vertex v
    LinkedList<Integer> adjList[];

    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedDigraphProject(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdgeProject>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdgeProject>();

        adjList = new LinkedList[V];
        for (int i = 0; i <V ; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /*
     * Initializes a random edge-weighted digraph with {@code V} vertices and <em>E</em> edges.
     *
     * @param  V the number of vertices
     * @param  E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}

    public EdgeWeightedDigraphProject(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdgeProject e = new DirectedEdgeProject(v, w, weight);
            addEdge(e);
        }
    }*/

    /*
     * Initializes an edge-weighted digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative

    public EdgeWeightedDigraphProject(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            addEdge(new DirectedEdgeProject(v, w, weight));
        }
    }*/


    /**
     * Returns the number of vertices in this edge-weighted digraph.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the directed edge {@code e} to this edge-weighted digraph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless endpoints of edge are between {@code 0}
     *         and {@code V-1}
     */
    public void addEdge(DirectedEdgeProject e) {
        int v = e.from();
        int w = e.to();
        //validateVertex(v);
        //validateVertex(w);
        //forward edge
        adjList[e.from()].addFirst(e.to());
        //backward edge in undirected graph
        adjList[e.to()].addFirst(e.from());
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    public void removeEdge(DirectedEdgeProject directedEdgeProject) {
        int v = directedEdgeProject.from();
        int w = directedEdgeProject.to();

        for (DirectedEdgeProject directedEdge : adj[v]) {
            if (directedEdge.from() == v && directedEdge.to() == w) {
                adj[v].remove(directedEdge);
                indegree[w]--;
                E--;
                return;
            }
        }
    }


    /**
     * Returns the directed edges incident from vertex {@code v}.
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdgeProject> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns all directed edges in this edge-weighted digraph.
     * To iterate over the edges in this edge-weighted digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<DirectedEdgeProject> edges() {
        Bag<DirectedEdgeProject> list = new Bag<DirectedEdgeProject>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdgeProject e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    } 

    /**
     * Returns a string representation of this edge-weighted digraph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdgeProject e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public void isConnected(){

        int vertices = this.V;
        LinkedList<Integer> adjList [] =this.adjList;

        //created visited array
        boolean[] visited = new boolean[vertices];

        //start the DFS from vertex 0
        DFS(1, adjList, visited);

        //check if all the vertices are visited, if yes then graph is connected
        int count = 0;
        for (int i = 0; i <visited.length ; i++) {
            if(visited[i])
                count++;
        }
        if(vertices==count){
            System.out.println("Given graph is connected");
        }else{
            System.out.println("Given graph is not connected");
        }
    }

    public void DFS(int source, LinkedList<Integer> adjList [], boolean[] visited){

        //mark the vertex visited
        visited[source] = true;

        //travel the neighbors
        for (int i = 0; i <adjList[source].size() ; i++) {
            int neighbor = adjList[source].get(i);
            if(visited[neighbor]==false){
                //make recursive call from neighbor
                DFS(neighbor, adjList, visited);
            }
        }
    }


    public EdgeWeightedDigraphProject createSubGraph(EdgeWeightedDigraphProject edg, ArrayList<Integer> verticesSubGraph) {
        EdgeWeightedDigraphProject subGraph = new EdgeWeightedDigraphProject(edg.V());
        for(int j=0;j<edg.V();j++){
            for (Integer integer : verticesSubGraph) {
                if (j == integer) {
                    Iterable<DirectedEdgeProject> adj_aux = edg.adj(j); //Para desenhar subgraph if(aux!=null)
                    for (DirectedEdgeProject de : adj_aux) {
                        subGraph.addEdge(de);
                    }
                }
            }
        }
        return subGraph;
    }
    
    
    
    /**
     * Unit tests the {@code EdgeWeightedDigraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

    }

}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
