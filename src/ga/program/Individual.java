package ga.program;

public class Individual implements Comparable<Individual> {
    
	private byte[] genes;
    
    private int fitness;
    
    public Individual() {
    	genes = new byte[SolutionFinder.getSolution().getSize()];
    	fitness = 0;
    }

    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    /* Getters and setters */   
    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) { // For performance
            fitness = SolutionFinder.getSolution().getFitness(this);
        }
        return fitness;
    }
    public void setFitness() {
    	fitness = SolutionFinder.getSolution().getFitness(this);
    }

    @Override
    public String toString() {
    	String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }

	@Override
	public int compareTo(Individual individual) {
		return individual.getFitness() - this.getFitness();
	}
}
