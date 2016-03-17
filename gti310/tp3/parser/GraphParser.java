package gti310.tp3.parser;

import gti310.tp3.exceptions.FileFormatException;
import gti310.tp3.model.AdjacencyListGraph;
import gti310.tp3.model.Graph;

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
public class GraphParser implements Parser<Graph>{

    //The graph generated from the file
    private Graph generatedGraph;

    // Patterns to check file's line validity
    private Pattern patternOnlyANumber;
    private Pattern patternGraphInformationLine;

    private final static String REGEX_ONLY_A_NUMBER = "^[0-9]*$";
    private final static String REGEX_GRAPH_INFO_LINE = "^\\d*\\t\\d*\\t\\d*$";

    //Attributes used to know if the files contains all the information needed
    private boolean isNbVerticesRead = false;
    private boolean isInfiniteValueRead = false;
    private boolean isStartVertexRead = false;
    private boolean hasOneVertexAdded = false;
    private boolean endingCharFound = false;



    /**
     * Default Constructor
     */
    public GraphParser(){
        patternOnlyANumber  = Pattern.compile(REGEX_ONLY_A_NUMBER);
        patternGraphInformationLine = Pattern.compile(REGEX_GRAPH_INFO_LINE);
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
                if(line.split("")[0].equals("$")){
                    endingCharFound=true;
                    break;
                }

                parseLine(line,lineNum);
                lineNum++;
            }
            br.close();

            if(!isFileValid())
                throw new FileFormatException("Empty file");

        }catch (IOException e) {
            generatedGraph = null;
            System.out.println("An error occurred during the manipulation of the graph file ");
            e.printStackTrace();
            System.exit(0);
        }
        catch (FileFormatException e) {
            generatedGraph = null;
            e.printStackTrace();
            System.out.println("Execution has been stopped due to a format problem of the file that contains graph information.");
            System.exit(0);
        }

        return generatedGraph;
    }

    /**
     * Method that parse a line of the file and execute different action depending on the line information.
     * @param line  A String line from the file
     * @param lineNum
     */
    private void parseLine(String line, int lineNum) throws FileFormatException {

            if(lineNum==1)
                parseNbVerticesLine(line);

            else if(lineNum==2)
                parseInfiniteValueLine(line);

            else if(lineNum==3)
                parseNumberOrEmptyLine(line);

            else if(lineNum > 3)
                parseGraphInformationLine(line);
    }

    /**
     * Method that parse a line of the file if it matches with format requirements and that contains the number of vertices in the graph
     * Line must only contains a number
     * @param line  A String corresponding of a line from the file
     */
    private void parseNbVerticesLine(String line) throws FileFormatException{

        Matcher matcher = patternOnlyANumber.matcher(line);

        if(!matcher.matches()|| line.isEmpty()){
            throw new FileFormatException("The given data line does not match format requirement (only number)");
        }
        //checker si c'est un chiffre
        generatedGraph.setNbVertices(Integer.parseInt(line));
        isNbVerticesRead = true;
    }

    /**
     * Method that parse a line of the file if it matches with format requirements and that contains the infinite value
     * Line must only contains a number
     * @param line  A String corresponding of a line from the file
     */
    private void parseInfiniteValueLine(String line) throws FileFormatException{

        Matcher matcher = patternOnlyANumber.matcher(line);

        if(!matcher.matches()|| line.isEmpty()){
            throw new FileFormatException("The given data line does not match format requirement (only number)");
        }
        //checker si c'est un chiffre
        generatedGraph.setInfiniteValue(Integer.parseInt(line));
        isInfiniteValueRead = true;
    }

    /**
     * Method that parse a line of the file if it matches with format requirements
     * Line must only contains a number or being empty
     * @param line  A String corresponding of a line from the file
     */
    private void parseNumberOrEmptyLine(String line) throws FileFormatException{

        if(line.isEmpty()){
            generatedGraph.setStartVertex(0);
            isStartVertexRead = true;
            return;
        }

        Matcher matcher = patternOnlyANumber.matcher(line);

        if(!matcher.matches()){
            throw new FileFormatException("The given data line does not match format requirement (number or empty)");
        }

        generatedGraph.setStartVertex(Integer.parseInt(line));
        isStartVertexRead = true;
    }

    /**
     * Method that parse a line of the file that contains information
     * about vertices and connections to build the graph
     *
     * The given line must follow this format:
     *  <source><tabulation><destination><tabulation><weight>
     *
     * @param line  A String corresponding of a line from the file
     */
    private void parseGraphInformationLine(String line) throws FileFormatException{

        Matcher matcher = patternGraphInformationLine.matcher(line);

        //checker si c'est int tab int tab int
        if((!matcher.matches())|| line.isEmpty()){
            throw new FileFormatException("The given data line does not match format requirement (<source><tabulation><destination><tabulation><weight>)");
        }

        String[] values= line.split("\t");
        int source = Integer.parseInt(values[0]);
        int neighbour = Integer.parseInt(values[1]);
        int weight = Integer.parseInt(values[2]);
        generatedGraph.addNeighbour(source,neighbour,weight);
        hasOneVertexAdded = true;
    }

    /**
     * Method that returns True if the file read contains all the needed information otherwise returns false
     * @return True if it's a correct file, False if the files doesn't have all the information
     */
    private boolean isFileValid(){
        return isInfiniteValueRead & isNbVerticesRead & isStartVertexRead & hasOneVertexAdded & endingCharFound;
    }


}
