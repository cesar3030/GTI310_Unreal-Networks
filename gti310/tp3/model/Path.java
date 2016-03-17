package gti310.tp3.model;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by César Jeanroy on 2016-03-15.
 * Class that store a solution path of the graph
 */
public class Path {

    //List of vertex that represents the path found
    private ArrayList<Vertex> verticesList;

    /**
     * Default constructor
     */
    public Path() {
    }

    /**
     * Constructor that uses the current stack of the GraphSolver to create a SolutionPath
     */
    public Path(Stack<Vertex> pathStack) {
        verticesList = new ArrayList<Vertex>(pathStack.size());
        for (int i = 0; i < pathStack.size() ; i++) {
            verticesList.add(pathStack.elementAt(i));
        }
    }

    /**
     * Getter
     * @return
     */
    public ArrayList<Vertex> getVerticesList() {
        return verticesList;
    }

    /**
     * Setter
     * @param verticesList
     */
    public void setVerticesList(ArrayList<Vertex> verticesList) {
        this.verticesList = verticesList;
    }

    @Override
    public String toString(){
        String result ="";
        for(Vertex v:verticesList)
            result+=v.getVertexNumber()+" ";
        return result.substring(0, result.length()-1);
    }
}
