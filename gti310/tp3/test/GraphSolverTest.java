import gti310.tp3.model.Graph;
import gti310.tp3.model.GraphSolution;
import gti310.tp3.model.Path;
import gti310.tp3.model.Vertex;
import gti310.tp3.solver.GraphSolver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by César Jeanroy on 2016-03-15.
 */
public class GraphSolverTest {

    private GraphSolver solver;
    private Graph graph = mock(Graph.class);
    private Stack<Vertex> stack = mock(Stack.class);
    private GraphSolution solution = mock(GraphSolution.class);
    private Path aSolutionPath = spy(new Path());

    @Before
    public void init(){
        solver = new GraphSolver();
        solver.setCurrentPath(stack);
        solver.setGraph(graph);
        solver.setSolutionFound(solution);

        solver = spy(solver);
    }

    @org.junit.Test
    public void testVisitVertexNewSolutionFound() throws Exception {
        Vertex vertex = mock(Vertex.class);


        when(stack.firstElement()).thenReturn(vertex);
        when(stack.size()).thenReturn(2);
        when(vertex.getVertexNumber()).thenReturn(1);
        when(graph.getNbConnexions()).thenReturn(1);

        solver.visitVertex(vertex);

        verify(vertex, times(1)).setAlreadyVisited(true);
        verify(vertex, times(1)).setAlreadyVisited(false);
        verify(stack,times(1)).pop();

    }

    @Test
    public void testVisitVertexWithNoNeighbours() throws Exception {
        Vertex vertex = mock(Vertex.class);

        when(stack.firstElement()).thenReturn(vertex);
        when(stack.size()).thenReturn(2);
        doReturn("").when(aSolutionPath).toString();
        when(vertex.getVertexNumber()).thenReturn(1);
        when(graph.getNbConnexions()).thenReturn(3);
        when(graph.getNotVisitedNeighbours(vertex)).thenReturn(null);

        solver.visitVertex(vertex);

        verify(vertex, times(1)).setAlreadyVisited(true);

        verify(vertex,times(1)).setAlreadyVisited(false);
        verify(stack,never()).add(any());
        verify(solver,never()).visitVertex(null);
    }

    @Test
    public void testVisitVertexWithNeighbour() throws Exception {
        Vertex vertex = mock(Vertex.class);
        Vertex neighbour = mock(Vertex.class);
        ArrayList<Vertex> neighboursList = new ArrayList<Vertex>();
        neighboursList.add(neighbour);

        when(stack.firstElement()).thenReturn(vertex);
        when(stack.size()).thenReturn(2);
        doReturn("").when(aSolutionPath).toString();
        when(vertex.getVertexNumber()).thenReturn(1);
        when(graph.getNbConnexions()).thenReturn(3);
        when(graph.getNotVisitedNeighbours(vertex)).thenReturn(neighboursList);

        solver.visitVertex(vertex);

        verify(vertex, times(1)).setAlreadyVisited(true);

        verify(vertex,times(1)).setAlreadyVisited(false);
        verify(stack,times(1)).add(any());
        verify(solver,times(1)).visitVertex(neighbour);
    }
}