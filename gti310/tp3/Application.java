package gti310.tp3;

import gti310.tp3.model.Graph;
import gti310.tp3.model.GraphSolution;
import gti310.tp3.parser.Parser;
import gti310.tp3.solver.GraphSolver;
import gti310.tp3.solver.Solver;
import gti310.tp3.parser.GraphParser;
import gti310.tp3.writer.SolutionWriter;

/**
 * The Application class defines a template method to call the elements to
 * solve the problem Unreal-Networks is fa�ing.
 * 
 * @author Fran�ois Caron <francois.caron.7@ens.etsmtl.ca>
 */
public class Application {

	/**
	 * The Application's entry point.
	 * 
	 * The main method makes a series of calls to find a solution to the
	 * problem. The program awaits two arguments, the complete path to the
	 * input file, and the complete path to the output file.
	 * 
	 * @param args The array containing the arguments to the files.
	 */
	public static void main(String args[]) {
		Parser parser = new GraphParser();
		Graph graph = (Graph) parser.parse(args[1]);
		System.out.println("graph.toString() = " + graph.toString());
		Solver solver = new GraphSolver();
		GraphSolution solution = (GraphSolution) solver.solve(graph);
		System.out.println(solution.toString());
		System.out.println("\nUnreal Networks Solver ! "+solution.getNbPathFound());
		SolutionWriter sr = new SolutionWriter();
		sr.write("media/cesar_solution.txt",solution);

	}
}
