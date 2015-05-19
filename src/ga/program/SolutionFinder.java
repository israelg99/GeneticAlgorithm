package ga.program;

public class SolutionFinder {
	
	private static final int DEFAULT_SOLUTION_LENGTH = 64;
	private static final int DEFAULT_POPULATION_LENGTH = 50;

	private static int SOLUTION_LENGTH;
	private static int POPULATION_LENGTH;
	
	private static Solution solution;
	private static Population population;
	
	public static void load(int s_length, int p_length) {
		setSolutionLength(s_length);
		setPopulationLength(p_length);
		
		solution = new Solution(SOLUTION_LENGTH);
		population = new Population(POPULATION_LENGTH, true);
	}
	public static void load(Solution sol, Population pop) {
		load();
		
		solution = sol;
	}
	public static void load() {
		load(DEFAULT_SOLUTION_LENGTH, DEFAULT_POPULATION_LENGTH);
	}
	
	public static int populationLength(int length) {
		return POPULATION_LENGTH;
	}
	public static void setPopulationLength(int length) {
		POPULATION_LENGTH = length;
	}
	
	public static int solutionLength(int length) {
		return SOLUTION_LENGTH;
	}
	public static void setSolutionLength(int length) {
		SOLUTION_LENGTH = length;
	}
	
	public static Solution getSolution() {
		return solution;
	}
	
	public static void findSolution() {
		int generationCount = 0;
        while (population.getFittest().getFitness() < solution.getMaxFitness()) {
            generationCount++;
            
            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness());
            population = GA.evolvePopulation(population);
        }
        
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(population.getFittest());
	}
}
