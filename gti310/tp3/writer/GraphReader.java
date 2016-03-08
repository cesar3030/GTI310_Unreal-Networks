package gti310.tp3.writer;

import gti310.tp3.model.CyclicGraph;
import gti310.tp3.model.Graph;
import gti310.tp3.model.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by César Jeanroy on 2016-03-07.
 * Class that reader a text file that contain graph information
 */
public class GraphReader {

    //Path to the file that contains graph information
    private String path;

    //The graph generated from the file
    private Graph generatedGraph;

    /**
     * Constructor with parameter
     * @param path Path to the file that contains graph information
     */
    public GraphReader(String path){
        this.path = path;
    }


    /**
     * Method that generate the graph corresponding of file information.
     * It it has already been generated, the method will return it.
     * @return
     */
    public Graph generateGraph(){

        if(generatedGraph!=null)
            return generatedGraph;
        else
            generatedGraph = new CyclicGraph();

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
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
