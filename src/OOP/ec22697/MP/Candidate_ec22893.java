package OOP.ec22697.MP;// File Candidate_ec22893.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22893 extends Candidate {

    public String getName() {
        return "Peter";
    }
    
    public String getSlogan() {
        return "More holiday!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0){
            return new Candidate_ec22893();
        }
        
        for (Candidate r : candidates)
           if (r.getSlogan().equals("More holiday!"))
               return r;
        
        // If can't find self, return first candidate
        return candidates[0];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate currentWinner = new Candidate_ec22893();
        
        if (votes.length != 0) currentWinner = votes[0];
        
        int largestCount = 0;
        for (int i=0;i<votes.length;i++) {
            int count = 0;
            for (int j=0;j<votes.length;j++)
                if (votes[i] == votes[j])
                    count++;
            if (count > largestCount) {
                largestCount = count;
                currentWinner = votes[i];
            }
        }
        return currentWinner;

    }

    // Allow a single integer to be input, returning the integer typed as the data1
    //
    public static Integer inputInteger(String message)
    {
        Integer data1;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        data1 = Integer.parseInt(scanner.nextLine());

        return data1;
    }

    //Allow a single string to be input, returning the string typed as the data2
    //
    public static String inputString(String message)
    {
        String data2;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        data2 = scanner.nextLine();

        return data2;
    }
    
    private static Candidate[] addCandidate(Candidate[] candidates, Candidate[] allCandidates, int count)
    {
        String name = inputString("Please enter a username:");
        Candidate candidate = A3.getByUsername(name, allCandidates);
        if (candidate != null)
        {
            candidates[count] = candidate;
            count++;
            return candidates;
        }
        else
        {
            System.out.println("Cannot find the candidate");
            return candidates;
        }
    }
    
    public static void addRandomCandidate(Candidate[] candidates, Candidate[] allCandidates, int count){
        int random = (new Random()).nextInt(count);
        Candidate candidate = allCandidates[random];
        candidates[count] = candidate;
        count++;
    }
    
    public static void runElection(Candidate[] candidates, Candidate[] allCandidates, int count){
        String name = inputString("Who should count the votes?");
        int loopNum = inputInteger("How many times shall we run the election?");
        Candidate[] winnerList = new Candidate[loopNum];
        for(int i = 0; i<loopNum; i++)
        {
            Candidate[] votes = new Candidate[count];
            for(int y = 0; y<count; y++)
            {
                votes[y] = (allCandidates[y]).vote(candidates);
            }

            winnerList[i] = (A3.getByUsername(name, allCandidates)).selectWinner(votes);
        }
        
        System.out.println("Most common winner is:");
        for(int x = 0; x<loopNum; x++)
        {
            System.out.println((winnerList[x]).getName());
        }
    
    }
    
    
    public static void main(String[] args) {
        String option = "";
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] candidates = new Candidate[allCandidates.length];
        int count= allCandidates.length;

        System.out.println("= Running Repeated Elections =");

        while (true) {
            if (count == 0) {
                    System.out.println("Candidates are None");
                } 
                else{
                    for (int i = 0; i < count; i++) {
                        System.out.println((i+1)+". "+allCandidates[i].getName());
                    }
                }
            
            option = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            
            if (option.equals("a")){
                candidates = addCandidate(candidates, allCandidates,count);
            }

            else if (option.equals("b")){
                addRandomCandidate(candidates, allCandidates,count);
            }
                     
            else if (option.equals("c")) {
                runElection(candidates, allCandidates,count);
            }

            else if (option.equals("d")) {
                break;
            }

            else {
                System.out.println("Invalid. Please select a , b , c or d:");
            }
        }
    }
}
