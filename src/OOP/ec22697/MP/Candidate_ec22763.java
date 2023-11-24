package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22763 extends Candidate {
    
    public String getName() {
        return "No name (ec22763)";
    }
    
    public String getSlogan() {
        return "No slogan (ec22763)";
    }
   
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22763();
        
        if (candidates.length != 0) r = candidates[0];
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22763();
        
        if (votes.length != 0) r = votes[0];
        
        return r;
    }
    
    public static void pr(String message){
        System.out.println(message);
        
        return;
    }
    
    public static String inputStrings(String message){
        Scanner scanner = new Scanner(System.in);

        pr(message);
        String answer = scanner.nextLine();

        return answer;
    }
    
    public static int inputInteger(String message){
        Scanner scanner = new Scanner(System.in);

        pr(message);
        int answer = Integer.parseInt(scanner.nextLine());

        return answer;
    }
    
    public static void printCandidates(Candidate[] candidates, int counter){
        pr("Candidates are:");

        for (int i = 0; i < counter; i++){
            System.out.println(candidates[i].getName());
        }

        return;
    }
    
    public static void runElection(Candidate[] newCandidate, int counter, int repeat, Candidate candidateCounter){
        Candidate[] newElec = new Candidate[counter];

        for(int i = 0; i < counter; i++){
        newElec[i] = newCandidate[counter];
        }

        Candidate[] winner = new Candidate[repeat * (counter -1)];

        for (int i = 0; i <= repeat; i++){
            for (int j = 0; j < counter; j++){
                winner[(counter*i) + j] = newElec[j].vote(newElec);
            }
        }

        Candidate actualWinner = candidateCounter.selectWinner(winner);
        System.out.println("Most common winner is: " + actualWinner.getName());
    }
    
    public static void main(String[] args){
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] newCandidates = new Candidate[allCandidates.length];

        int counter = 0;

        String option = inputStrings("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        
        if(option.equals("a")){
            String username = inputStrings("Enter a username");
            
            Candidate newCandidate1 = A3.getByUsername(username, allCandidates);
            newCandidates[counter] = newCandidate1;

            counter++;
            printCandidates(newCandidates, counter);
        }
        
        else if(option.equals("b")){
            Random random = new Random();
            int randomInt = random.nextInt(allCandidates.length);

            newCandidates[counter] = allCandidates[randomInt];
            counter++;

            printCandidates(newCandidates, counter);
        }
        
        else if(option.equals("c")){
            String vote = inputStrings("Who should count the votes?");
            Candidate candidateCounter = A3.getByUsername(vote, allCandidates);
            int repeat = inputInteger("How many times shall we run the election?");

            runElection(newCandidates, counter, repeat, candidateCounter);
        }

        else{
            pr("Invalid option");
        }
   }
}
