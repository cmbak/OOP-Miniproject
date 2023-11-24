package OOP.ec22697.MP;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Candidate_ec22911 extends Candidate {
    public String getName() {
        return "Roger Federer";
    }

    public String getSlogan() {
        return "Federer is the GOAT!!!";
    }
    public static void main(String[] args) {
        //DEFINITIONS
        Candidate[] allContributions = A3.getCandidateArray();
        Candidate[] contributions = new Candidate[]{};
        int option = 0;
        int runCounter = 0;
        boolean loop = true;

        System.out.println("= Running Repeated Elections =");

        while (loop) {
            if ((contributions.length == 0) && (runCounter == 0))  {
                System.out.println("None");
            }
            else if (option==1) {
                for (int i=0; i< contributions.length;i++) {
                    System.out.println((i+1)+". "+contributions[i].getName());
                }
            }

            option = inputInt("Select an option\n1) New Candidate\n2) Most Common Winner (from specified vote counter)\n3) Most Common Winner (from random vote counter)\n4) Exit");

            //New Candidate
            if (option == 1){
                runCounter++;

                try {
                    String username = inputString("Please enter a username:");
                    contributions = addArray(contributions, A3.getByUsername(username, A3.getCandidateArray()));
                }
                catch (Exception e){
                    String username = inputString("Try another username:");
                    contributions =addArray(contributions, A3.getByUsername(username, A3.getCandidateArray()));
                }
            }

            //Most Common Winner (from specified vote counter)
            else if (option == 2) {
                runCounter++;
                if(allContributions.length == 0){
                    System.out.println("NO CANDIDATES");
                }else{
                    String voteCounter = inputString("Who should count the votes?");
                    Candidate[] totalCandidates = joinArray(contributions,allContributions);
                    mostCommonWinner(totalCandidates, voteCounter);
                }
            }

            //Most Common Winner (from random vote counter)
            else if (option == 3){
                Candidate[] totalCandidates = joinArray(contributions,allContributions);
                Random rand = new Random();
                int voteCount = rand.nextInt(totalCandidates.length);
                mostCommonWinner(totalCandidates, totalCandidates[voteCount].un);
                runCounter++;
            }

            //Exit
            else if (option == 4) {
                loop = false;
            }

            //Wrong Input
            else {
                System.out.println("Only select an option from the list (1,2 or 3)");
            }

        }
    }

    //Joins two arrays together that are passed in as arguments and returns the joint array
    public static Candidate[] joinArray(Candidate[] contributions, Candidate[] allContributions) {
        Candidate[] totalCandidates = new Candidate[allContributions.length + contributions.length];
        System.arraycopy(contributions, 0 , totalCandidates,0, contributions.length);
        System.arraycopy(allContributions, 0 , totalCandidates,contributions.length, allContributions.length);
        return totalCandidates;
    }

    //Finds the most common winner from the voteCounter argument and returns the most common winner using the vote counter's selectWinner method
    public static void mostCommonWinner(Candidate[] contributions, String voteCounter){
        int runs = inputInt("How many times shall we run the election?");
        Candidate[] commonWinner = new Candidate[runs];
        Candidate currentWinner = commonWinner[0];
        for(int i=0; i<runs;i++){
            try {

                commonWinner[i] = Objects.requireNonNull(A3.getByUsername(voteCounter, A3.getCandidateArray())).selectWinner(contributions);
            }
            catch (Exception e){
                voteCounter = inputString("Try another user:");
                commonWinner[i] = Objects.requireNonNull(A3.getByUsername(voteCounter, A3.getCandidateArray())).selectWinner(contributions);
            }
        }
        int maxCount = 0;
        for(int i=0;i< commonWinner.length;i++){
            int count = 0;
            for(int j=0;j<commonWinner.length;j++){
                if((commonWinner[i].equals(commonWinner[j]))&&(i!=j)){
                    count++;
                    if(count>maxCount){
                        maxCount = count;
                        currentWinner = commonWinner[i];
                    }
                }
            }
        }
        System.out.println("Most common winner is "+currentWinner.getName());
    }

    //Adds a new candidate object to the array and returns the new array
    public static Candidate[] addArray(Candidate[] preArray, Candidate newCandidate){
        Candidate[] newArray = new Candidate[preArray.length+1];
        System.arraycopy(preArray, 0, newArray, 0, preArray.length);
        newArray[newArray.length-1] = newCandidate;
        return newArray;
    }
    public static String inputString(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();}

    public static int inputInt(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (!scanner.hasNextInt()) {//Checks whether the input is actually an integer
            System.out.println("Invalid input. Please only enter a number: ");
            scanner.next();
        }

        return scanner.nextInt();}

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec22431();
        for (Candidate c : candidates)
            if (c.getName().equals("Kermit"))
                return c;
        for (Candidate c : candidates)
            if (c.getName().equals("Abdullah"))
                return c;
        Random rand = new Random();
        int candidate = rand.nextInt(candidates.length);
        return candidates[candidate];
    }

    public Candidate selectWinner(Candidate[] votes) {
        int start = 0;
        int end = votes.length - 1;

        Random rand = new Random();
        while (start < end) {
            int pivot = rand.nextInt(end - start + 1) + start;

            Candidate temp = votes[end];
            votes[end] = votes[pivot];
            votes[pivot] = temp;

            int i = start;
            for (int j = start; j <= end - 1; j++) {
                //Compare the person at position j of the array to the person at position "end" (divide and conquer algo)
                // and if they are the same the values at position i and j are swapped
                if (votes[j].getName().compareTo(votes[end].getName()) <= 0) {
                    temp = votes[i];
                    votes[i] = votes[j];
                    votes[j] = temp;
                    i++;
                }
            }

            temp = votes[i];
            votes[i] = votes[end];
            votes[end] = temp;

            int mid = (start + end) / 2;


            if (i == mid) {
                return votes[i];
            } else if (i < mid) {
                start = i + 1;
            } else {
                end = i - 1;
            }
        }
        return votes[start];
    }

}
