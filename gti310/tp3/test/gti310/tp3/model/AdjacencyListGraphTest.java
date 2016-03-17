package gti310.tp3.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by César Jeanroy on 2016-03-16.
 */
public class AdjacencyListGraphTest {

    private AdjacencyListGraph target;

    @Before
    public void setUp() throws Exception {
        target = new AdjacencyListGraph();
    }

    /**
     * Test with all the neighbours never visited yet
     * @throws Exception
     */
    @Test
    public void testGetNotVisitedNeighboursNeverVisited() throws Exception {
        target.addNeighbour(1,2,12);
        target.addNeighbour(1,3,14);
        target.addNeighbour(1,4,14);

        ArrayList<Vertex> neighbours = target.getNotVisitedNeighbours(new Vertex(1,23));

        assertEquals(2,neighbours.get(0).getVertexNumber());
        assertEquals(12,neighbours.get(0).getWeight());
        assertEquals(3,neighbours.get(1).getVertexNumber());
        assertEquals(14,neighbours.get(1).getWeight());
        assertEquals(4,neighbours.get(2).getVertexNumber());
        assertEquals(14,neighbours.get(2).getWeight());
    }

    /**
     * Test with one the neighbours already visited
     * @throws Exception
     */
    @Test
    public void testGetNotVisitedNeighboursAlreadyVisited() throws Exception {
        target.addNeighbour(1,2,12);
        target.addNeighbour(1,3,14);
        target.addNeighbour(1,4,14);


        ArrayList<Vertex> neighbours = target.getNotVisitedNeighbours(new Vertex(1,23));
        neighbours.get(2).setAlreadyVisited(true);

        neighbours = target.getNotVisitedNeighbours(new Vertex(1,23));

        assertEquals(2,neighbours.get(0).getVertexNumber());
        assertEquals(12,neighbours.get(0).getWeight());
        assertEquals(3,neighbours.get(1).getVertexNumber());
        assertEquals(14,neighbours.get(1).getWeight());
    }

    @Test
    public void testAddNeighbour() throws Exception {
        assertEquals(0,target.getNbVertices());
        target.addVertex(1);
        assertEquals(1,target.getNbVertices());
    }
}