package OOP.ec22697.MP;// File Candidate_ec22507.java

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22507 extends Candidate {
    public static void main (String [] args){
        A3 a3 = new A3();
        Candidate[] candidates = a3.getCandidateArray();
        String[] a = new String[0];
      
        System.out.println("= Running Repeated Elections =");
        System.out.println("Candidates are:");
        if(a.length == 0){
            System.out.println("none");
        }
        else {
            for(int i=0;i<a.length; i++) {
                System.out.println(candidates[i]);
            }
        }
        while(true) {
            String answer = inputstring("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

            if(answer.equals("a")){
                a = addCandidate(a);
            }
            else if (answer.equals("b")){
                a = addRandomCandidate(a);
            }
            else {
                RunElection(a);
                break;
            }
        }

        
    }
    public static String[] addCandidate (String[] a){
        String[] addA = new String[a.length + 1];
        for(int i = 0; i<a.length; i++){
            addA[i] = a[i];
        }
        String input = inputstring("Please enter a username.");
        addA[addA.length-1] = input;
        return addA;
    }
    public static String[] addRandomCandidate (String[] a ) {
        String[] addA = new String[a.length + 1];
        for(int i = 0; i<a.length; i++){
            addA[i] = a[i];
        }
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomString = "";
        int length = 6;
        Random rand = new Random();
        char[] text = new char[length];
        for(int i = 0; i<length; i++){
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for (int i = 0; i <text.length; i++) {
            randomString += text[i];
        }
        addA[addA.length-1] = randomString;
        return addA;
    }
    public static void RunElection (String[] a) {
        int counter = inputint("How many times shall we run the election?");
        for (int i=0; i<counter; i++) {
         String winner = a[0];
        }
        String winner = a[0];
        System.out.println("Most common winner is " + winner + ".");
        System.out.println("There are no other winners");

    }

    public static String inputstring(String message){
        Scanner input = new Scanner(System.in); 
            System.out.println(message);
            String answer = input.nextLine();
            return answer;
        

    }
    public static int inputint(String message) {
        int answer = Integer.parseInt(inputstring(message));
        return answer;

    }
    
    public String getName() {
        return "Edward Kenway";
    }
    
    public String getSlogan() {
        return "Everything is permitted";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22507();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Nothing is true"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Ezio Auditore"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec221028();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        int highestCount = 0;
        int count = 0;
        for (Candidate c : votes) {
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
