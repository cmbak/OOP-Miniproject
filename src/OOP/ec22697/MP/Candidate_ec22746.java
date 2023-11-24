package OOP.ec22697.MP;// File Candidate_ec22746.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

class Candidate_ec22746 extends Candidate {
    
    public static void main (String[] a){
        Scanner reply = new Scanner(System.in);
        final Candidate[] allCandidates = A3.getCandidateArray();
        
        System.out.println("Please enter how many candidates you would like to add to the election");
        String totalText = reply.nextLine();
        while (checkForCharacters(totalText) == true){
            System.out.println("Please enter a number");
            totalText = reply.nextLine();
        }
        int total = Integer.parseInt(totalText);

        Candidate[] electionCandidates = new Candidate[total];

        for (int i=0; i<total; i++){
            System.out.println("Enter a candidate username to add to the election: ");
            String username = reply.nextLine();
            electionCandidates[i] = checkCandidate(username, allCandidates, reply);
        }

        System.out.println("Who will run the election?");
        String username = reply.nextLine();
        Candidate electedCandidate = checkCandidate(username, allCandidates, reply);
        System.out.println("How many times will the election be run?");
        String numberOfTimesText = reply.nextLine();
        while (checkForCharacters(numberOfTimesText) == true){
            System.out.println("Please enter a number");
            numberOfTimesText = reply.nextLine();
        }
        int numberOfTimes = Integer.parseInt(numberOfTimesText);
        
        Candidate[] candidateWinners = new Candidate[numberOfTimes];
        
        for (int j=0; j<numberOfTimes; j++){
            Candidate winner = electedCandidate.selectWinner(electionCandidates);
            candidateWinners[j] = winner;
        }
        Candidate winner = mostVoted(candidateWinners);
        System.out.println("The most common winner is " + winner.un);
    }
                

    public static boolean checkForCharacters(String word)
    {
        char currentCharacter;
        int count = 0;
        boolean condition = false;
        boolean characterFound = false;

        while (condition == false) {
            currentCharacter = word.charAt(count);
            if (!(Character.isDigit(currentCharacter))) {
                characterFound = true;
                condition = true;
            } else if (count == word.length() - 1) {
                condition = true;
            } else {
                count++;
            }
        }

        return characterFound;
    }

    public static Candidate mostVoted(Candidate[] candidateWinners){
        int [] mostVoted = new int[candidateWinners.length];
        for (int x=0; x<candidateWinners.length; x++){
            int count = 0;
            for (int y=0; y<candidateWinners.length; y++){
                if (candidateWinners[x].equals(candidateWinners[y])){
                    count++;
                }
                mostVoted[x] = count;
            }
        }
        
        int highest = mostVoted[0];
        int index = 0;
        
        for (int z=1; z<mostVoted.length; z++){
            if (highest < mostVoted[z]){
                highest = mostVoted[z];
                index = z;
            }
        }
        
        return candidateWinners[index];
    }
              
    
    public static Candidate checkCandidate(String username, Candidate[] allCandidates, Scanner reply){
        while (A3.getByUsername(username, allCandidates) == null){
            System.out.println("There is no candidate with that username. Please try again");
            username = reply.nextLine();
        }
        return A3.getByUsername(username, allCandidates);
    }
        
      
    public String getName(){
        return "Samuel";
    }
    
    public String getSlogan(){
        return "More recycling!";
    }
    
    public Candidate vote(Candidate[] candidates){
        
        if (candidates.length == 0){
            return new Candidate_ec22777();
        }
        
        else{
            for (Candidate c : candidates){
                if (c.getSlogan() == "More fields!"){
                    return c;
                }
            }
        }
        
        return candidates[0];
        
    }
    
    public Candidate selectWinner(Candidate[] votes){
        
        if (votes.length == 0){
            return new Candidate_ec22777();
        }
        
        else{
            
            Candidate winner = votes[0];
            int highestVotes = 0;
            int current = 0;
        
            for (Candidate c : votes){
                for (Candidate d : votes){
                    if (c.equals(d)){
                        current++;
                    }
                }

                if (current>highestVotes){
                    highestVotes = current;
                    winner = c;
                }
            }
            
            return winner;
        }

        
    }
}
