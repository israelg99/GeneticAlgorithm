package ga.program;

public class Main {

	public static void main(String[] args) {
		GA.load(true);
		SolutionFinder.load(600,50); /* SOLUTION SIZE, POPULATION SIZE */
		SolutionFinder.findSolution();
	}

}
