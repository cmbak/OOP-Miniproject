package OOP.ec22697.MP;// File Candidate_ec22960.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;


class Candidate_ec22960 extends Candidate {
    
    public String getName() {
        return "Future";
    }
    
    public String getSlogan() {
        return "I love cheesecake.";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22960();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("I love cheesecake."))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("MrWriteIn"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public int numberOfTimes(Candidate x, Candidate[] a) { //checks how many times the candidate was voted for
        int r = 0;

        for(int i = 0; i < a.length;i++){
            if(a[i] == x){
                r ++;
            }
        }

        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22960();
        
        Candidate currentWinner = votes[0];
        int largestCountSoFar = numberOfTimes(votes[0],votes);

        for(int i = 1; i < votes.length; i++){
            if(numberOfTimes(votes[i],votes) > largestCountSoFar){ //calls on numberOfTimes so see how many votes each candidate got
                currentWinner = votes[i];
                largestCountSoFar = numberOfTimes(votes[i],votes);
            }
        }
    
        
        return currentWinner;
    }
    
    private static void print(String message){
        System.out.println(message);
    }

    private static String inputString(String message){ //detects and returns user inputs using scanner 
        String userInput;
        Scanner scanner = new Scanner(System.in);
        print(message);
        userInput = scanner.nextLine();
        return userInput;
    }

    private static int inputInt(String message){ //detects and returns user inputs using scanner 
        int userInput = 0;
        Scanner scanner = new Scanner(System.in);
        print(message);
        
        if(scanner.hasNextInt()){ //checks if the user has input an integer
            userInput = scanner.nextInt();
            if(userInput < 0){//checks if the input is positive
                print("Please enter an Integer >= 0."); 
                userInput = inputInt(message);
            }
        }else{
            print("Please enter a valid Integer."); //asks for another input 
            userInput = inputInt(message);
        }
            
        return userInput;
    }

    private static Candidate addSpecificCandidate(String userName){
        print("Adding candidate");
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate newCandidate = A3.getByUsername(userName,allCandidates);
        return newCandidate;
    }
    
    private static Candidate addRandomCandidate(){
        print("Adding candidate");
        Candidate[] allCandidates = A3.getCandidateArray();
        int amountOfCandidates = allCandidates.length;
        Random random = new Random();
        int index = random.nextInt(amountOfCandidates);
        Candidate newCandidate = allCandidates[index];
        return newCandidate;
    }

    private static void printCandidates(Candidate[] candidates, int amountOfCandidates){
        if(amountOfCandidates == 0){
            print("None");
        }else{
            for(int i = 0; i < amountOfCandidates; i++){
                if(candidates[i] != null){
                    print(candidates[i].getName());
                }
            }
        }
    }
    
    private static Candidate[] getVotes(Candidate[] candidates, int amountOfCandidates){
        Candidate[] votes = new Candidate[amountOfCandidates];
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate y = A3.getByUsername("ec22960",allCandidates);
        for(int i = 0 ; i < amountOfCandidates; i++){
            try { 
                votes[i] =  candidates[i].vote(candidates);
               
            }
            catch (Exception e) { // 'Spoilt ballot'
                votes[i] = null;
            }   
            
        }
        return votes;
    }

    private static Candidate getWinner(Candidate[] votes, String userName){
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate counter = A3.getByUsername(userName,allCandidates);
        Candidate backup = A3.getByUsername("ec22960",allCandidates);
        Candidate winner;
        try { 
            winner = counter.selectWinner(votes);
           
        }
        catch (Exception e) { 
            winner = backup.selectWinner(votes);
        }   
        return winner;
    }

    private static void runElection(Candidate candidates[], int amountOfCandidates){
        String userName = inputString("Who would you like to count the votes.\n>");
        int repeats = inputInt("How many tmes would you like to run the election.\n>");
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate y = A3.getByUsername("ec22960",allCandidates);
        Candidate[] winners = new Candidate[repeats];
        
        for(int i = 0; i < repeats; i++){
            Candidate[] votes = getVotes(candidates,amountOfCandidates);
            winners[i] = getWinner(votes, userName);
        }

        Candidate currentWinner = y.selectWinner(winners);
       
        if(currentWinner != null){
            print("Most common winner was " + currentWinner.getName());
        }else{
            print("A null candidate won the election");
        }

    }

    private static void election(){
        boolean loop = true;
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] candidates = new Candidate[allCandidates.length];
        System.out.print(candidates.length);
        int amountOfCandidates = 0;

        print("== Running repeated elections ==");

        while(loop == true){
            printCandidates(candidates, amountOfCandidates);
            String userInput = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?\n>");
            
            if(userInput.equals("a")){
                String userName = inputString("Enter username:\n>");
                if(amountOfCandidates == allCandidates.length){
                    print("You have reached the max ammount of candidates");
                }else{
                    candidates[amountOfCandidates] = addSpecificCandidate(userName);
                    amountOfCandidates++;
                }

            }else if(userInput.equals("b")){
                if(amountOfCandidates == allCandidates.length){
                    print("You have reached the max ammount of candidates");
                }else{
                    candidates[amountOfCandidates] = addRandomCandidate();
                    amountOfCandidates++;
                }

            }else if(userInput.equals("c")){
                if(amountOfCandidates == 0){
                    print("There are no candidates in this election");
                }else{
                    runElection(candidates, amountOfCandidates);
                    loop = false;
                }
            }else{
                print("Please enter a valid input");
            }
        }
    }

    private static Candidate[] getAllVotes(Candidate[] candidates){
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate y = A3.getByUsername("ec22960",allCandidates);
        Candidate[] votes = new Candidate[allCandidates.length];
        for(int i = 0; i < allCandidates.length; i++){
            try { 
                votes[i] = allCandidates[i].vote(candidates);
        
               
            }
            catch (Exception e) { // 'Spoilt ballot'
                votes[i] = null;
            }   
        }
        return votes;
    }

    private static void twoCandidateElection(){
        Candidate[] candidates = new Candidate[2];
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate x = A3.getByUsername("ec22960",allCandidates);
        for(int i = 0 ; i < allCandidates.length; i++ ){
            candidates[0] = allCandidates[i];
            print("Candidate 1 :" + candidates[0].getName());
            for(int j = 0 ; j < allCandidates.length; j++ ){
                if(!(i==j)){
                    candidates[1] = allCandidates[j];
                    print("Candidate 2 :" + candidates[1].getName());
                    Candidate[] votes = getAllVotes(candidates);
                    Candidate winner = x.selectWinner(votes);
                    if(winner != null){
                        print("The winner was " + winner.getName());
                    }else{
                        print("A null candidate won");
                    }

                }
            }
        }

    }

    private static boolean  processOption(String userInput){
        boolean loop = true;
        if(userInput.equals("a")){
            loop = false;
        }else if(userInput.equals("b")){
            election();
        }else if(userInput.equals("c")){
            twoCandidateElection();
        }else{
            print("Please enter a valid input.");
        }
        return loop;
    }

    private static void electionSim(){
        boolean loop = true;
        while(loop == true){
            String userInput = inputString("Would you like to \na) exit\nb) run same election many times\nc) run all 2 candidate elections ");
            loop = processOption(userInput);
        }
    }

    public static void main(String[] a ){
        electionSim();
    }




}
