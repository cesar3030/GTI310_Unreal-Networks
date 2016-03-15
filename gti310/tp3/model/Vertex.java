package gti310.tp3.model;

/**
 * Created by César Jeanroy on 2016-03-07.
 * Vertex data structure
 */
public class Vertex {

    //number of the vertex (it's ID)
    private int vertexNumber;
    //Weight to access to this vertex
    private int weight;
    //True if the vertex has been visited
    private boolean alreadyVisited;


    /**
     * Constructor with parameters
     *
     * @param vertexNumber
     * @param weight
     */
    public Vertex(int vertexNumber, int weight) {
        this.vertexNumber = vertexNumber;
        this.weight = weight;
        this.alreadyVisited = false;
    }

    /**
     * Vertex number getter
     * @return
     */
    public int getVertexNumber() {
        return vertexNumber;
    }

    /**
     * Weight getter
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     * return if the vertex has been visited
     * @return
     */
    public boolean isAlreadyVisited() {
        return alreadyVisited;
    }

    /**
     * Set alreadyVisited value
     * @param alreadyVisited
     */
    public void setAlreadyVisited(boolean alreadyVisited) {
        this.alreadyVisited = alreadyVisited;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "vertexNumber=" + vertexNumber +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return vertexNumber == vertex.vertexNumber;
    }

    @Override
    public int hashCode() {
        return vertexNumber;
    }
}
