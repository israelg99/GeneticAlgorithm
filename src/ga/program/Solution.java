package ga.program;

public class Solution {
	
	private byte[] solution;
	
	// Sets random solution with the given length
	public Solution(int length) {
		solution = new byte[length];

		for (int i = 0; i < getSize(); i++) {
            solution[i] = (byte) Math.round(Math.random());;
        }
	}
	
    // Set a candidate solution as a byte array
    public Solution(byte[] newSolution) {
        solution = newSolution;
    }

    // To make it easier we can use this method to set our candidate solution 
    // with string of 0s and 1s
    public Solution(String newSolution) {
        solution = new byte[newSolution.length()];
        // Loop through each character of our string and save it in our byte 
        // array
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }
    
    /* Getters and Setters */
    // Get solution
    public byte[] getSolution() {
    	return solution;
    }
    // Get size
    public int getSize() {
    	return solution.length;
    }

    // Calculate inidividuals fittness by comparing it to our candidate solution
    public int getFitness(Individual individual) {
        int fitness = 0;
        // Loop through our individuals genes and compare them to our cadidates
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }
    
    // Get optimum fitness
    public int getMaxFitness() {
        return getSize();
    }
}
