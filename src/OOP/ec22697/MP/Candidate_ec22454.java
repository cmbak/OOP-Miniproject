package OOP.ec22697.MP;// File Candidate_ec22454.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22454 extends Candidate {
     public static void main (String [] a)
    {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag)
        {
            System.out.println( "Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d)exit ");
            String answer = scanner.nextLine();
            if (answer.equals("d"))
            {
                flag = false;
            }
            else
            {
                options(answer);
            }
        }
        
        
    }
    
    public static void options(String answer) //User options
    {
        Candidate[] classContribution = A3.getCandidateArray();
        Candidate[] entry = new Candidate[classContribution.length];
        int pointer = 0;
        if(answer.equals("a"))
        {
            entry = addCandidate(classContribution, entry, pointer);
            pointer++;
        }
        else if (answer.equals("b"))
        {
            entry = addRandomCandidate(classContribution, entry, pointer);
            pointer++;
        }
        else if (answer.equals("c"))
        {
            runElection(classContribution, entry, pointer);
        }
        else
        {
            System.out.println("Invalid");
        }
    }

    //Option a
    public static Candidate[] addCandidate(Candidate[] contributions, Candidate[] entry, int pointer)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the candidate's username: ");
        String name = scanner.nextLine();
        Candidate candidateName = A3.getByUsername(name,entry);
        entry[pointer] = candidateName;
        return entry;
    }

    //Option b
    public static Candidate[] addRandomCandidate(Candidate[] contributions, Candidate[] entry, int pointer)
    {
        Random random = new Random();
        int randomNumber = random.nextInt(contributions.length);
        entry[pointer] = contributions[randomNumber];
        return entry; 
    }
    
    //Option c
    public static void runElection(Candidate[] contributions, Candidate[] entry, int pointer)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter usernmae who should count votes; ");
        String inputUsername = scanner.nextLine();
        Candidate userCountingVotes = A3.getByUsername(inputUsername, entry);

        System.out.println("How many times should we run the election? ");
        int numberOfTimes = scanner.nextInt();

        Candidate[] listVotes= new Candidate[numberOfTimes * entry.length];


        for (int number = 0; number < numberOfTimes; number++) {

            for (int i = 0; i < contributions.length; i++) {
                try {
                    listVotes[(number * contributions.length) + i] = contributions[i].vote(entry);
                } catch (Exception e) {
                    listVotes[(number * contributions.length) + i] = new Candidate_ec22454();
                }
            }
        }
        Candidate winner = userCountingVotes.selectWinner(listVotes);
        System.out.println("The winner is: " + winner.getName());
    }


    
    public String getName() {
        return "Yas";
    }
    
    public String getSlogan() {
        return "Free train travel";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22454();
        
        if (candidates.length != 0){ 
        r = new Candidate_ec22454();
        }
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22454();
        
        if (votes.length != 0)
        {
            r = votes[votes.length-1];
        }
        return r;
    }
    
}
