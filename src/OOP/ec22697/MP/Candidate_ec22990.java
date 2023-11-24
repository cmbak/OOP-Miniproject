package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22990 extends Candidate {
//213    
    public String getName() {
        return "Arsen4ik";
    }
    
    public String getSlogan() {
        return "Hi, kids!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) 
            return new Candidate_ec22990();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hi, kids!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Bo"))
                return c;
        
        // Otherwise, default to random on list.
        Random n = new Random();
        int randomised=n.nextInt(candidates.length);
        return candidates[randomised];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) 
            return new Candidate_ec22990();
        
        Random n = new Random();
        Candidate currentWinner = votes[n.nextInt(votes.length-1)];

        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
    public static void main (String [] args) {
        int input_number = inputInt("How many candidates?");
        Candidate[] allCandidates = A3.getCandidateArray();
        int number = 0;
        Candidate[] list = new Candidate[10000];
        boolean valid= true; 
        while(valid) {
            
            String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d)exit?").toLowerCase();
            
            if (choice.equals("a")){
                String name = inputString("Please enter a new username:");
                Candidate specificCandidate = A3.getByUsername(name, allCandidates);

                try {
                    if (specificCandidate != null) {
                        list[number] = specificCandidate;
                    }
                } catch(Exception e) {
                    System.out.println("Couldn't get candidate.");
                }
                
            } else if (choice.equals("b")) {
                
                if (number == allCandidates.length) {
                    System.out.println("Candidates are full.");
                }
                try {
                    int randIndex = new Random().nextInt(allCandidates.length);
                    list[number] = allCandidates[randIndex];
                    number++;
                } catch (Exception e) {
                    System.out.println("error");
                }
            } else if (choice.equals("c")) {
                String inputName = inputString("Enter the candidate to vote: ");
                Candidate selected = A3.getByUsername(inputName, allCandidates);
                
                Candidate[] finalList = new Candidate[number];
                    for (int i = 0 ; i < number; i ++)
                    {
                        finalList[i] = list[i];
                    }
                int noOfTimes = inputInt("How many times shall we run the election?");

                Candidate[] votes = new Candidate[noOfTimes*allCandidates.length];
                for (int electionNo = 0; electionNo<noOfTimes ; electionNo++){

                    for (int i = 0; i< allCandidates.length; i++ ){
                        try{
                            votes[(electionNo*allCandidates.length) + i] = allCandidates[i].vote(finalList);
                        }
                        catch(Exception e){
                            votes[(electionNo*allCandidates.length) + i] = new Candidate_ec22990();

                        }
                    }
                }
                Candidate winner = selected.selectWinner(votes);
                System.out.println("Most common winner is: " + winner.getName());
                
            } else if(choice.equals("d")){
                valid = false;
            }
        }
    } 
    
    public static String inputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String answer;

        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    } 
    
    public static int inputInt(String message)
    {
        Scanner scanner = new Scanner(System.in);
        int answer;

        System.out.println(message);

        try {
            answer = scanner.nextInt();
        } catch (Exception e) {
            answer=inputInt("Invalid input, an integer is required");
        }

        return answer;
    }
    
    public static void showAllCandidates(Candidate[] list, int length){
        System.out.println("Candidates are");
        if (length !=0){
            for (int i =0; i < length; i++){
                System.out.println(list[i].getName()+ ": " + list[i].getSlogan());
            }
        }
        else{
            System.out.println("None");
        }
        return;
    }
}


