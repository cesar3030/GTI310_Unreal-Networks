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

	private static String PGR_NAME = null;
	private static String SOURCE_FILE = null;
	private static String SOLUTION_FILE = null;

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

		checkProgramArgumentsValidity(args);

		Parser parser = new GraphParser();
		Graph graph = (Graph) parser.parse(args[1]);
		System.out.println("graph.toString() = " + graph.toString());
		Solver solver = new GraphSolver();
		GraphSolution solution = (GraphSolution) solver.solve(graph);
		System.out.println(solution.toString());
		System.out.println("\nUnreal Networks Solver ! "+solution.getNbPathFound());
		SolutionWriter sr = new SolutionWriter();
		sr.write(SOLUTION_FILE,solution);

	}

	/**
	 * Method that check the validity of the given arguments
	 * @param args
     */
	private static void checkProgramArgumentsValidity(String[] args){

		if(args.length<3){
			System.out.println("Arguments are missing\nexecution args: 0:programmeName, 1:path_Source_File, 2:path_Solution_File");
			System.exit(0);
		}

		PGR_NAME = args[0];
		SOURCE_FILE = args[1];
		SOLUTION_FILE = args[2];

		if(PGR_NAME == null){
			System.out.println("Programme name is missing\nexecution args: 0:programmeName, 1:path_Source_File, 2:path_Solution_File");
			System.exit(0);
		}
		if(SOURCE_FILE == null){
			System.out.println("Source file is missing\nexecution args: 0:programmeName, 1:path_Source_File, 2:path_Solution_File");
			System.exit(0);
		}
		if(SOLUTION_FILE == null){
			System.out.println("Solution file is missing\nexecution args: 0:programmeName, 1:path_Source_File, 2:path_Solution_File");
			System.exit(0);
		}
	}
}
