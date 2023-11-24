package OOP.ec22697.MP;//ec22895 contribution for Candidate_ec22895
//A3

import java.util.*;

class Candidate_ec22895 extends Candidate {
    

    public static void main(String[] args) {
        Candidate[] candidates = A3.getCandidateArray();
        List<Candidate> userDefinedCandidates = new ArrayList<Candidate>();
        boolean exitMenu = false;
        System.out.println("= Running Repeated Elections =");
        displayAllCandidates(userDefinedCandidates);
        while(!exitMenu) {
            String menuOption = getStringInput("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) exit");
            switch(menuOption) {
                case "a":
                        String username = getStringInput("Please enter a username.");
                        Candidate userCandidate = A3.getByUsername(username, candidates);
                        if(userCandidate == null) {
                            System.out.println("Candidate does not exist with that username.");
                        }
                        else {
                             userDefinedCandidates.add(userCandidate);
                             displayAllCandidates(userDefinedCandidates);
                        }
                        break;
                case "b":
                         Candidate randomCandidate = getRandomCandidate(candidates);
                         userDefinedCandidates.add(randomCandidate);
                         displayAllCandidates(userDefinedCandidates);
                         break;
                case "c":
                         String countingUsername = getStringInput("Who should count the votes?");
                         Candidate countingCandidate = A3.getByUsername(countingUsername, candidates);
                         if(countingCandidate == null) {
                            System.out.println("Candidate does not exist with that username.");
                         }
                         else {
                             int numberOfTimes = getIntegerInput("How many times shall we run the election?");
                             if(numberOfTimes < 0) {
                                System.out.println("You must enter an integer greater than 0.");
                             }
                             else {
                                runElection(candidates, userDefinedCandidates, numberOfTimes, countingCandidate);
                             }
                         }
                         break;

                case "d":
                         System.out.println("Goodbye!");
                         exitMenu = true;
                         break;
                default:
                        System.out.println("Please a choose valid option out of either a, b or c.");
                        break;
            }
        }
        
    }
    public String getName() {
        return "Tim Ford";
    }

    public static void displayAllCandidates(List<Candidate> candidates) {
        System.out.println("Candidates are");
        if(candidates.size() == 0) {
            System.out.println("None");
            return;
        }
        int position = 1;
        for(Candidate candidate: candidates) {
            System.out.println(position + ". " + " " + candidate.getName());
            position++;
        }
    }

    public static void runElection(Candidate[] candidates, List<Candidate> userDefinedCandidates, int numberOfTimes, Candidate countingCandidate) {
        try {
            Candidate[] castedCandidates = userDefinedCandidates.toArray(new Candidate[0]);
            List<Candidate> votes = new ArrayList<Candidate>();
            for(int i = 1; i <= numberOfTimes; i++) {
                for(int j = 0; j < candidates.length; j++) {
                    try {
                        votes.add(candidates[j].vote(castedCandidates));
                    } 
                    //if another persons contribution has an error and causes a vote to fail, then that vote will be replaced with a new instance of my class
                    catch(Exception exception) {
                        votes.add(new Candidate_ec22895());
                    }
                    
                }
            }
            Candidate winner = countingCandidate.selectWinner(votes.toArray(new Candidate[0]));
            System.out.println("Most common winner is " + winner.getName() + " .");
        }
        catch(Exception e) {
            System.out.println("Error running election, here is exception message: " + e.getMessage());
        }
        
    }
    
    public String getSlogan() {
        return "Vote for Tim!";
    }

    public static Candidate getRandomCandidate(Candidate[] candidates) {
        Random random = new Random();
        return candidates[random.nextInt(candidates.length)];
    }
    
    
    public static int getIntegerInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        if(!scanner.hasNextInt()) {
            System.out.println("You have not entered an integer!");
            return -1;
        }
        return scanner.nextInt();
    }

    public static String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }
    public Candidate vote(Candidate[] candidates) {
        
        if(candidates.length == 0) {
            return new Candidate_ec22895();
        }
        
        //return candidate object from Candidate_ec22419
        for(Candidate c : candidates) {
            if(c.getSlogan().equals("End Global Warming!")) {
                return c;
            }
        }
        return candidates[0];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if(votes.length == 0) {
            return new Candidate_ec22895();
        }
        
        Candidate currentWinner = votes[0];
        
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
    
}
