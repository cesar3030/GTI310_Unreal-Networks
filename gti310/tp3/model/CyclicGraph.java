package gti310.tp3.model;

import sun.rmi.rmic.iiop.ValueType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by César Jeanroy on 2016-03-07.
 */
public class CyclicGraph extends Graph {

    //Adjacency List that contain the graph structure
    private HashMap<Integer,ArrayList<Vertex>> adjacencyList;


    /**
     * Default Constructor
     */
    public CyclicGraph() {
        super();
        this.adjacencyList = new HashMap<Integer,ArrayList<Vertex>>();
    }


    @Override
    public void addVertex(int vertexNumber) {
        adjacencyList.put(vertexNumber,new ArrayList<Vertex>());
    }

    @Override
    public void addNeighbour(int sourceVertexNumber, int neighbourVertexNumber, int weight) {
        //We check that the source vertex is already in the adjacency list
        if(!adjacencyList.containsKey(sourceVertexNumber))
            addVertex(sourceVertexNumber);

        adjacencyList.get(sourceVertexNumber).add(new Vertex(neighbourVertexNumber,weight));
    }

    @Override
    public String toString() {
        return super.toString() + "\nCyclicGraph{" +
                "\nadjacencyList= \n -----------"+printHashMap();

    }

    private String printHashMap(){
        String line ="";
        for (Map.Entry<Integer,ArrayList<Vertex>> entry : adjacencyList.entrySet()) {
            line+="\nVertex "+entry.getKey()+" :";
            for(Vertex v : entry.getValue())
                line+="\n\t\t-> Vertex:"+v.getVertexNumber()+" Weight:"+v.getWeight();
                line+="\n-----------";
        }
        return line;
    }
}
