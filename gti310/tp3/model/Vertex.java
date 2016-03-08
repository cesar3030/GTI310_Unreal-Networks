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

    /**
     * Constructor with parameters
     *
     * @param vertexNumber
     * @param weight
     */
    public Vertex(int vertexNumber, int weight) {
        this.vertexNumber = vertexNumber;
        this.weight = weight;
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

    @Override
    public String toString() {
        return "Vertex{" +
                "vertexNumber=" + vertexNumber +
                ", weight=" + weight +
                '}';
    }
}
