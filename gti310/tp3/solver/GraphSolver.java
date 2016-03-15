package gti310.tp3.solver;

import gti310.tp3.model.Graph;
import gti310.tp3.model.Vertex;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by César Jeanroy on 2016-03-10.
 */
public class GraphSolver implements Solver<Graph,String> {

    Stack<Vertex> currentPath;
    Graph graph;
    int nbPathFound=0;
    /**
     * Default constructor
     */
    public GraphSolver() {
        currentPath = new Stack<Vertex>();
    }

    @Override
    public String solve(Graph input) {
        graph = input;
        Vertex start = graph.getStartVertex();
        currentPath.add(start);
        visitVertex(start);
        return "Done ! "+nbPathFound+" Paths found";
    }

    private void visitVertex(Vertex vertex){

        //printStack();
        vertex.setAlreadyVisited(true);

        if(currentPath.firstElement().equals(vertex) && currentPath.size() == (graph.getNbConnexions()+1)){
            printStack();
            vertex.setAlreadyVisited(false);
            currentPath.pop();
            nbPathFound++;
            return;
        }
        else{
            ArrayList<Vertex> neighbours = graph.getNotVisitedNeighbours(vertex);

            // printNeighbours(neighbours);
            //System.out.print(" -> " + vertex.getVertexNumber());
            if(neighbours==null){
                vertex.setAlreadyVisited(false);
                currentPath.pop();
                return;
            }
            else{
                for(Vertex neighbour : neighbours){
                    currentPath.add(neighbour);
                    visitVertex(neighbour);
                }
                vertex.setAlreadyVisited(false);
                currentPath.pop();
            }

        }

    }

    private void printStack(){
        System.out.print("\n nouvelle visite: ");
        for (int i = 0; i < currentPath.size(); i++) {
            System.out.print(currentPath.get(i).getVertexNumber()+" -> ");
        }
    }

    private void printNeighbours(ArrayList<Vertex> a){
        System.out.print("\n\t Voisins: ");
        if(a==null)
            return;

        for (Vertex v:a) {
            System.out.print(v.getVertexNumber()+" , ");
        }
    }
}

