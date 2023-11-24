package OOP.ec22697.MP;// File Candidate_ec22632.java

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22632 extends Candidate {
    
    public String getName() {
        return "Szymon";
    }
    
    public String getSlogan() {
        return "What.";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22632();

        if (candidates.length == 0){
            return r;
        }

        for (Candidate i : candidates){
            if (i != null){
                if (i.getName().equals("Walter Hartwell White")){
                    return i;
                }
            }
        }

        for (Candidate i : candidates){
            if (i != null){
                if (i.getSlogan().equals("Hello")){
                    return i;
                 }
            }
        }
        
        Random rand = new Random();
        int c = rand.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22632();
        
        if (votes.length == 0) return r;
 
        int highestCount = 0;

        for (Candidate i: votes){
            int count = 0;
            
                for (Candidate j: votes){
                    if (i==j){
                        count++;
                    }
                    if (count > highestCount){
                        highestCount = count;
                        r = i;
                    }
                }
            
        }
        return r;
    }
    
    public static Candidate[] addSpecificCandidate(String name, Candidate[] arr, Candidate[] bigArr){
        int counter = 0;
        for (Candidate i: arr){
            if (i != null){
                counter++;
            }
        }
        arr[counter] = A3.getByUsername(name, bigArr);
        return arr;
    }

    public static Candidate[] addRandomCandidate(Candidate[] arr, Candidate[] bigArr){
        int counter = 0;
        for (Candidate i: arr){
            if (i != null){
                counter++;
            }
        }
        Random rand = new Random();
        int r = rand.nextInt(bigArr.length);
        arr[counter] = bigArr[r];
        return arr;
    }
    
    public static void printArray(Candidate[] arr){
        System.out.println("Candidates are");
        int nameCount = 1;
        for (Candidate i: arr){
            if (i != null){
                System.out.println(nameCount + ". " + i.getName());
                nameCount++;
            }
        }
    }

    public static void runElection(Candidate electionHost, Candidate[] voters, Candidate[] candidates, int repetition){
        Candidate[] winners = new Candidate[repetition];
        Candidate[] votes = new Candidate[voters.length];
        int candidateCount = 0;
        for (Candidate i: candidates){
            if (i != null){
                candidateCount++;
            }
        }
        System.out.println(candidateCount);
        Candidate[] candidateArray = new Candidate[candidateCount];
        for (int i = 0; i < candidateCount; i++){
            candidateArray[i] = candidates[i];
        }

        int votecount = 0;
        for (int i = 0; i < repetition; i++){
            for (Candidate j: voters){
                try{
                    votes[votecount] = j.vote(candidateArray);
                    votecount++;
                }
                catch(Exception e){
                    
                    votecount++;
                }
            }
            winners[i] = electionHost.selectWinner(votes);
        }
        Candidate overallWinner = electionHost.selectWinner(winners);
        System.out.println("Most common winner is " + overallWinner.getName());
    }
    
    public static void main(String[] args) {
        final Candidate[] array = A3.getCandidateArray();
        Candidate[] newArray = new Candidate[array.length];
        Scanner scanner = new Scanner(System.in);
        String response = "";

        while (!response.equals("c")){
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            response = scanner.nextLine().toLowerCase();
            if (response.equals("a")){
                System.out.println("Please enter a username.");
                newArray = addSpecificCandidate(scanner.nextLine(), newArray, array);
                printArray(newArray);
            }
            else if (response.equals("b")){
                newArray = addRandomCandidate(newArray, array);
                printArray(newArray);
            }
        }
        System.out.println("Who should count the votes?");
        response = scanner.nextLine();
        Candidate electionHost = A3.getByUsername(response, array);
        System.out.println("How many times shall we run the election?");
        response = scanner.nextLine();
        int repetition = Integer.parseInt(response);
        runElection(electionHost, array, newArray, repetition);
        scanner.close();
    }
}
