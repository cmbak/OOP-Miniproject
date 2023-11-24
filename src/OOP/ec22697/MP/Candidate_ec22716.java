package OOP.ec22697.MP;// File Candidate_ec22716.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

class Candidate_ec22716 extends Candidate {
    
    static Candidate[] contributions = A3.getCandidateArray();
    static Candidate[] candidates = new Candidate[0];
    static Candidate[] votes = new Candidate[0];

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String option = "";
        while (!option.equals("c")) {
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            option = scanner.nextLine().toLowerCase();
            if (option.equals("a")) {
                String specificName = input("Enter the username of the candidate: ");
                addspecCandidate(specificName, contributions);
            } else if (option.equals("b")) {
                addRandomCandidate(contributions,candidates);
                break;
            } else if (option.equals("c")) {
                runElection(contributions,candidates,votes);
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
 
    }
    
        public String getName() {
        return "Joe";
    }
    
    public String getSlogan() {
        return "More votes pls";
    }
    
    
    
        public static Candidate addspecCandidate(String specificName, Candidate allContributions[]) {
            String name=input("Enter the username of the candidate");
    Candidate specificCandidate = A3.getByUsername(name,contributions);
    if (specificCandidate != null) {
        // allContributions[0] = specificCandidate;
        Candidate[] newCandidates = new Candidate[candidates.length + 1];
        System.arraycopy(candidates, 0, newCandidates, 0, candidates.length);
        newCandidates[candidates.length] = specificCandidate;
        candidates = newCandidates;
        System.out.println("Candidate added: " + specificCandidate.getName());
    } else {
        System.out.println("User not found.");
    }
    return specificCandidate; 
}

        public static Candidate addRandomCandidate(Candidate allContributions[], Candidate candidates[]) {
    // Get a random index within the allContributions array
    int randomIndex = (int) (Math.random() * allContributions.length);
    Candidate randomCandidate = allContributions[randomIndex];
    Candidate[] newCandidates = new Candidate[candidates.length + 1];
    System.arraycopy(candidates, 0, newCandidates, 0, candidates.length);
    newCandidates[candidates.length] = randomCandidate;
    candidates=newCandidates;
    
    System.out.println("Added random candidate: " + randomCandidate.getName());
            
    return randomCandidate;
}
    
  
public static String input(String text){
    Scanner scanner=new Scanner(System.in);
    String input=scanner.nextLine();
    System.out.println(text);
    return input;
    }
     
     public static Candidate runElection(Candidate allContributions[], Candidate candidates[], Candidate[] votes) {
    // Allow each candidate to vote once
    for (Candidate c : candidates) {
        c.vote(candidates);
    }
         

    // Ask the user who should count the votes
    System.out.println("Who should count the votes?");
    for (int i = 0; i < allContributions.length; i++) {
        System.out.println((i + 1) + ": " + allContributions[i].getName());
    }

    // Ask the user how many times to run the election
    System.out.println("How many times would you like to run the election?");
    int numElections = 0; // initialize to 0
    boolean validInput = false;
    while (!validInput) {
        try {
            numElections = Integer.parseInt(System.console().readLine());
            if (numElections > 0) {
                validInput = true;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again.");
        }
    }

    Candidate currentWinner = null;

    // Run the election the specified number of times
    for (int i = 0; i < numElections; i++) {
        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        System.out.println(currentWinner);
    }

    return currentWinner;
}



    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length==0){
            return new Candidate_ec22716();
        }
        
        // First search for self on the list of candidates.
        for (Candidate c : candidates){
            if (c.getName().equals("Joe") | c.getSlogan().equals("More votes pls")){
                return c;
        }
        
    }
    
    return candidates[candidates.length-1];
}   

     
     public Candidate selectWinner(Candidate[] votes) {
    // If array is empty
    if (votes.length == 0) {
        return new Candidate_ec22716();
    }

    Candidate currentWinner = votes[0];
    int highestCount = 1;

    // Count the votes for each candidate in the array,
    // selecting one with the most.
    for (int i = 0; i < votes.length; i++) {
        int count = 1;
        for (int j = i + 1; j < votes.length; j++) {
            if (votes[i] == votes[j]) {
                count++;
            }
        }
        if (count > highestCount) {
            highestCount = count;
            currentWinner = votes[i];
        }
    }

    return currentWinner;
}
    
}


