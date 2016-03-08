package gti310.tp3.model;

/**
 * Graph Interface
 */
public abstract class Graph {

    //Number of vertex that the graph has
    protected int nbVertices;
    //Infinite value used by the depth-first/breath-first algorithms
    protected double infiniteValue;
    //The Vertex where the graph starts
    protected int startVertex;

    /**
     * Default constructor
     */
    public Graph() {
        this.nbVertices = 0;
        this.infiniteValue = Double.MAX_VALUE;
        this.startVertex = 0;
    }

    /**
     * Methode that adds a new vertex in the adjacency list.
     * @param vertexNumber Number of the vertex (it's ID)
     */
    public abstract void addVertex(int vertexNumber);

    /**
     * Method that add a new neigbour relation between two vertex in the adjacency list including the weight.
     * @param sourceVertexNumber
     * @param neighbourVertexNumber
     * @param weight
     */
    public abstract void addNeighbour(int sourceVertexNumber, int neighbourVertexNumber, int weight);

    /**
     * setter
     * @param nbVertices
     */
    public void setNbVertices(int nbVertices){
        this.nbVertices=nbVertices;
    }

    /**
     * Setter
     * @param infiniteValue
     */
    public void setInfiniteValue(double infiniteValue) {
        this.infiniteValue = infiniteValue;
    }

    /**
     * Setter
     * @param startVertex
     */
    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    /**
     * Getter
     * @return number of Vertices
     */
    public int getNbVertices() {
        return nbVertices;
    }

    /**
     * Getter
     * @return infinite value to use
     */
    public double getInfiniteValue() {
        return infiniteValue;
    }

    /**
     * Getter
     * @return start vertex value
     */
    public int getStartVertex() {
        return startVertex;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nbVertices=" + nbVertices +
                ", infiniteValue=" + infiniteValue +
                ", startVertex=" + startVertex +
                '}';
    }
}
