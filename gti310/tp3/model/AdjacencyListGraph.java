package gti310.tp3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by César Jeanroy on 2016-03-07.
 */
public class AdjacencyListGraph extends Graph {

    //Adjacency List that contain the graph structure
    private HashMap<Integer,ArrayList<Vertex>> adjacencyList;


    /**
     * Default Constructor
     */
    public AdjacencyListGraph() {
        super();
        this.adjacencyList = new HashMap<Integer,ArrayList<Vertex>>();
    }


    @Override
    public void addVertex(int vertexNumber) {
        adjacencyList.put(vertexNumber,new ArrayList<Vertex>());
    }

    @Override
    public ArrayList<Vertex> getNotVisitedNeighbours(Vertex v) {

        ArrayList<Vertex> neighboursList = new ArrayList<Vertex>();

        //If we ask for a node that doesn't exists in the adjacency list we return null
        if(!adjacencyList.containsKey(v.getVertexNumber()))
            return null;

        for( Vertex vertex : adjacencyList.get(v.getVertexNumber())){
            if(!vertex.isAlreadyVisited())
                neighboursList.add(vertex);
        }

        return (neighboursList.size()>0) ? neighboursList:null;
    }

    @Override
    public void addNeighbour(int sourceVertexNumber, int neighbourVertexNumber, int weight) {
        //We check that the source vertex is already in the adjacency list
        if(!adjacencyList.containsKey(sourceVertexNumber))
            addVertex(sourceVertexNumber);

        adjacencyList.get(sourceVertexNumber).add(new Vertex(neighbourVertexNumber,weight));
        super.nbConnexions++;
    }

    @Override
    public int getNbVertices() {
        return adjacencyList.size();
    }

    @Override
    public String toString() {
        return super.toString() + "\nAdjacencyListGraph{" +
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
