package gti310.tp3.parser;

import gti310.tp3.model.AdjacencyListGraph;
import gti310.tp3.model.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by César Jeanroy on 2016-03-07.
 * Class that reader a text file that contain graph information
 */
public class GraphParser implements Parser<Graph>{

    //The graph generated from the file
    private Graph generatedGraph;

    /**
     * Default Constructor
     */
    public GraphParser(){

    }


    /**
     * Method that generate the graph corresponding of file information.
     * It it has already been generated, the method will return it.
     * @return
     */
    @Override
    public Graph parse(String filename) {

        if(generatedGraph!=null)
            return generatedGraph;
        else
            generatedGraph = new AdjacencyListGraph();

        try {

            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            int lineNum = 1;

            while ((line = br.readLine()) != null) {
                //if it's the end of file
                if(line.split("")[0].equals("$"))
                    break;

                parseLine(line,lineNum);
                lineNum++;
            }
            br.close();

        }catch (IOException e) {
            generatedGraph = null;
            System.out.println("An error occurred during the manipulation of the graph file ");
            e.printStackTrace();
        }

        return generatedGraph;
    }

    /**
     * Method that parse a line of the file and execute different action depending on the line information
     * @param line  A String line from the file
     * @param lineNum
     */
    private void parseLine(String line, int lineNum) {

        if(lineNum==1){
            generatedGraph.setNbVertices(Integer.parseInt(line));
        }
        else if(lineNum==2){
            generatedGraph.setInfiniteValue(Integer.parseInt(line));
        }
        else if(lineNum==3){
            generatedGraph.setStartVertex(Integer.parseInt(line));
        }
        else if(line.contains("\t")){
            String[] values= line.split("\t");
            int source = Integer.parseInt(values[0]);
            int neighbour = Integer.parseInt(values[1]);
            int weight = Integer.parseInt(values[2]);
            generatedGraph.addNeighbour(source,neighbour,weight);
        }
    }


}
