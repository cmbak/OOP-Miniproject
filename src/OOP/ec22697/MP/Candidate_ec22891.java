package OOP.ec22697.MP;

import java.util.*;

class Candidate_ec22891 extends Candidate {
    
    public String getName() {

	    return "Armin Shahnami";
    }
    
    public String getSlogan() {

	    return "Vote for Armin";
    }
    
    public Candidate vote(Candidate[] candidates) {

        if(candidates==null || candidates.length==0) return new Candidate_ec22891();
 
        for(Candidate c: candidates){
            if(c != null && c.getName().equals("Bob")) return c;
        }

        for(Candidate c: candidates){
            if(c != null && c.getSlogan().equals("Vote for Change")) return c;
        }
	    
        for(Candidate c: candidates){
	    if(c != null) return c;	
	    }

    	return new Candidate_ec22891(); 
    }
    
    public Candidate selectWinner(Candidate[] votes) {

        if(votes==null || votes.length==0) return new Candidate_ec22891();

        Candidate winner = votes[0];
        int maxCount = 0;

        for(Candidate a: votes){
            if(a == null) continue;
            int currCount=0;

            for(Candidate b: votes){
                if(a == b) currCount++;
            }

            if(currCount > maxCount){
                winner = a;
                maxCount = currCount;
            }
        }

        return winner;
    }

    public static void displayCurrentCandidates(Candidate[] currentCandidates, int candidateCount){

        print("Current candidates are:");

        if(candidateCount == 0){
            print("None\n");
            return;
        }

        for(int i=0; i<currentCandidates.length; i++){
            if(currentCandidates[i] != null){
                print(currentCandidates[i].getName());
            }
        }

        print('\n');
    }

    public static int addSpecificCandidate(Candidate[] totalCandidates, Candidate[] currentCandidates, int candidateCount){

        String name = inputString("Please enter a username.");
        Candidate c = A3.getByUsername(name, totalCandidates);

        while(c == null){
            print("That is not a valid username.");
            name = inputString("Please enter a username.");
            c = A3.getByUsername(name, totalCandidates);
        }

        if(candidateExists(currentCandidates, c)){
            print("This candidate already exists.");
            return candidateCount;
        } else {
            currentCandidates[candidateCount] = c;
            return candidateCount+1;
        }
    }

    public static int addRandomCandidate(Candidate[] totalCandidates, Candidate[] currentCandidates, int candidateCount){

        Random rand = new Random();
        int random_int = rand.nextInt(totalCandidates.length);

        while(candidateExists(currentCandidates, totalCandidates[random_int])){
            random_int = rand.nextInt(totalCandidates.length);
        }

        currentCandidates[candidateCount] = totalCandidates[random_int];
        return candidateCount+1;
    }

    public static boolean candidateExists(Candidate[] currentCandidates, Candidate candidate){
        for(Candidate c: currentCandidates){
            if(c == candidate) return true;
        }

        return false;
    }

    public static void mostCommonWinner(Candidate[] winners, int runAmount){

        int maxCount = 0;
        Candidate winner = winners[0];

        for(int i=0; i<runAmount; i++){
            int currCount = 0;
            for(int j=0; j<runAmount; j++){
                if(winners[i] == winners[j]) currCount++;
            }
            if(currCount >= maxCount){
                maxCount = currCount;
                winner = winners[i];
            }
        }

        print("The most common winner is " + winner.getName());
    }    

    public static void runElection(Candidate[] totalCandidates, Candidate[] currentCandidates, int candidateCount){

        String voteCounter = inputString("Who should count the votes?");

        while(A3.getByUsername(voteCounter, totalCandidates) == null){
            voteCounter = inputString("That is not a valid username. Who should count the votes?");
        }

        Candidate countPerson = A3.getByUsername(voteCounter, totalCandidates);

        int runAmount = inputNum("How many times should we run the election?");
        Candidate[] winners = new Candidate[runAmount];

        for(int k=0; k<runAmount; k++){

            Candidate[] votes = new Candidate[totalCandidates.length];
            for(int i=0; i<totalCandidates.length; i++){

                try {
                    votes[i] = totalCandidates[i].vote(currentCandidates);
                } catch(Exception e){
                    print("The following candidate raised an exception " + totalCandidates[i]);
                }
            }
            winners[k] = countPerson.selectWinner(votes);
        }

        mostCommonWinner(winners, runAmount);
    }   

    public static char getChar(String message, char firstOption, char lastOption){

        print(message);
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();

        while(response.length() != 1 || (int)response.charAt(0) < (int)firstOption || (int)response.charAt(0) > (int)lastOption){
            print("Sorry, please select a valid option.");
            print(message);
            response = scanner.nextLine().toLowerCase();
        }

        return response.charAt(0);
    }

    public static String inputString(String message){

        print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int inputNum(String message){

        return Integer.parseInt(inputString(message));
    }

    public static <T> void print(T t){

        System.out.println(t);
    }

    public static void showMenu(Candidate[] totalCandidates, Candidate[] currentCandidates){

        int candidateCount=0;
        final char EXIT_CHAR = 'd';

        displayCurrentCandidates(currentCandidates, candidateCount);
        char choice = getChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?", 'a', 'd');

        while(choice != EXIT_CHAR){
            if(choice == 'a'){
                candidateCount = addSpecificCandidate(totalCandidates, currentCandidates, candidateCount);
            } else if(choice == 'b'){
                candidateCount = addRandomCandidate(totalCandidates, currentCandidates, candidateCount);
            } else {
                runElection(totalCandidates, currentCandidates, candidateCount);
            }
            displayCurrentCandidates(currentCandidates, candidateCount);
            choice = getChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?", 'a', 'd');
        }
    }

    public static void main(String[] args){

        final Candidate[] totalCandidates = A3.getCandidateArray();
        Candidate[] currentCandidates = new Candidate[totalCandidates.length];
        showMenu(totalCandidates, currentCandidates);
    } 
}
