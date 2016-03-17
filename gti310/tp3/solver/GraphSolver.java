package gti310.tp3.solver;

import gti310.tp3.model.Graph;
import gti310.tp3.model.GraphSolution;
import gti310.tp3.model.Path;
import gti310.tp3.model.Vertex;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by César Jeanroy on 2016-03-10.
 *
 * Class that solve a the problem to find all the different path in a cyclic graph
 */
public class GraphSolver implements Solver<Graph,GraphSolution> {

    //Stack that contain each vertex already visited by the current path
    private Stack<Vertex> currentPath;
    //Graph object that contains the adjacency list
    private Graph graph;
    //The object that store the solution
    GraphSolution solutionFound = null;

    /**
     * Default constructor
     */
    public GraphSolver() {
        currentPath = new Stack<Vertex>();
    }

    /*
     * O(N)
     */
    @Override
    public GraphSolution solve(Graph input) {
        graph = input; //O(1)
        Vertex start = graph.getStartVertex(); //O(1)
        solutionFound = new GraphSolution(); //O(1)
        currentPath.add(start);//O(1)
        visitVertex(start);//O(N)
        return solutionFound;
    }

    /**
     * O(N)
     * Method that visit a vertex and visit the unvisited neighbours vertices
     * while all the paths from the start to the start going through all the connections hasn't all been found.
     * @param vertex the vertex we are gonna visit
     */
    public void visitVertex(Vertex vertex){

        vertex.setAlreadyVisited(true);

        if(currentPath.firstElement().equals(vertex) && currentPath.size() == (graph.getNbConnexions()+1)){

            //printStack();
            solutionFound.addPath(new Path(currentPath));
            vertex.setAlreadyVisited(false);
            currentPath.pop();
        }
        else{
            ArrayList<Vertex> neighbours = graph.getNotVisitedNeighbours(vertex);

            // printNeighbours(neighbours);
            if(neighbours==null){
                vertex.setAlreadyVisited(false);
                currentPath.pop();
            }
            else{
                for(Vertex neighbour : neighbours){
                    currentPath.add(neighbour); //The function is called N times (N = nb neighbours)
                    visitVertex(neighbour);
                }
                vertex.setAlreadyVisited(false);
                currentPath.pop();
            }
        }

    }

    /**
     * Method that prints on the console the current stack of vertices that represent the current path.
     * O(N)
     */
    private void printStack(){
        System.out.print("\n New Path: ");
        for (int i = 0; i < currentPath.size(); i++) {
            System.out.print(currentPath.get(i).getVertexNumber()+" -> ");
        }
    }

    /**
     * Method that print the given arrayList of vertex that represents a list of neighbours
     * O(N)
     * @param a
     */
    private void printNeighbours(ArrayList<Vertex> a){

        System.out.print("\n\t Neighbours: ");

        if(a==null)
            return;

        for (Vertex v:a) {
            System.out.print(v.getVertexNumber()+" , ");
        }
    }

    /*
     * O(1)
     */
    public Stack<Vertex> getCurrentPath() {
        return currentPath;
    }

    /*
     * O(1)
     */
    public void setCurrentPath(Stack<Vertex> currentPath) {
        this.currentPath = currentPath;
    }

    /*
     * O(1)
     */
    public Graph getGraph() {
        return graph;
    }

    /*
     * O(1)
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /*
     * O(1)
     */
    public GraphSolution getSolutionFound() {
        return solutionFound;
    }

    /*
     * O(1)
     */
    public void setSolutionFound(GraphSolution solutionFound) {
        this.solutionFound = solutionFound;
    }
}

