package gti310.tp3.writer;

import gti310.tp3.model.GraphSolution;
import gti310.tp3.model.Path;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by César Jeanroy on 2016-03-15.
 */
public class SolutionWriter implements Writer<GraphSolution> {

    @Override
    public void write(String filename, GraphSolution output) {

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(filename, "UTF-8");

            //We print all the path found
            for(Path p : output.getPathsFound())
                writer.println(p.toString());

            writer.close();
            System.out.println("Solution has been written in the solution file: "+filename);

        } catch (FileNotFoundException e) {
            System.out.println("file: "+filename+" not found !");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
