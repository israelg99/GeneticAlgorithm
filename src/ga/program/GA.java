package ga.program;

public class GA {
	
	/* GA parameters */
    private static double uniformRate;
    private static double mutationRate;
    private static int tournamentSize;
    private static int elitismOffset;
    private static boolean ELITISM;
    
    public static void load(boolean elitism) {
    	uniformRate = 0.7;
        mutationRate = 0.005;
        tournamentSize = 5;
        elitismOffset = 0;
        ELITISM = elitism;
    }
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        // Sorting the population
    	pop.sort();
    	
    	// New population for the next generation
    	Population newPopulation = new Population(pop.size(), false);
        int offest = 0;
        
        // ELITISM
        if (ELITISM) {
        	offest = elitismOffset;
        	for(int i = 0; i < offest; i++) {
            	newPopulation.saveIndividual(i, pop.get(i));
        	}
        }

        // Loop over the population size and create new individuals with crossover
        for (int i = offest; i < pop.size(); i++) {
        	// We use pop in the parameters because the elitism offest maybe <1 or disabled, therefore newPopulation.get(x) is null.
            newPopulation.saveIndividual(i, uniform_crossover(pop.get(0), pop.get(1)));
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Individual uniform_crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        
        // Loop through genes
        for (int i = 0; i < newSol.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        
        return newSol;
    }
    private static Individual point_crossover(Individual indiv1, Individual indiv2) {
    	Individual newSol = new Individual();
    	
    	// Crossover
    	
    	// Offest for the point crossover
    	int offest = (int) (Math.random() * indiv1.size());
    	
    	// Crossover before the offest
    	for (int i = 0; i < offest; i++) {
    		// Crossover
    		newSol.setGene(i, indiv1.getGene(i));
    	}
    	
    	// Crossover after the offest
    	for (int i = offest; i < newSol.size(); i++) {
    		// Crossover
    		newSol.setGene(i, indiv2.getGene(i));
    	}
    	
    	return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                indiv.setGene(i, (byte) Math.round(Math.random()));
            }
        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
