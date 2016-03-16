package gti310.tp3.model;

import java.util.ArrayList;

/**
 * Created by César Jeanroy on 2016-03-15.
 */
public class GraphSolution {

    private ArrayList<Path> pathsFound;

    /**
     * Constructor with parameter
     * @param pathsFound The list of solution path found
     */
    public GraphSolution(ArrayList<Path> pathsFound) {
        this.pathsFound = pathsFound;
    }

    /**
     * Default Constructor
     */
    public GraphSolution() {
        this.pathsFound = new ArrayList<Path>();
    }

    /**
     * Method that add a new path in the list of solution path
     * @param p
     */
    public void addPath(Path p){
        pathsFound.add(p);
    }

    /**
     * Getter
     * @return the list of solution path found in the graph
     */
    public ArrayList<Path> getPathsFound() {
        return pathsFound;
    }

    /**
     * Setter
     * @param pathsFound
     */
    public void setPathsFound(ArrayList<Path> pathsFound) {
        this.pathsFound = pathsFound;
    }

    /**
     * Method that return the number of paths found
     */
    public int getNbPathFound(){
        return pathsFound.size();
    }

    @Override
    public String toString(){
        String result = "";
        for(Path p: pathsFound)
            result+="\n"+p.toString();
        return result;
    }
}
