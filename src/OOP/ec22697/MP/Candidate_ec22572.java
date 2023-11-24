package OOP.ec22697.MP;
// File Candidate_ec22572.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22572 extends Candidate {

    public static void main(String[] args) throws Exception {
        Candidate[] total = A3.getCandidateArray();
        System.out.println(
                "Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) exit");
        char userInput = getChar();

        if (userInput == 'a') {
            optionA(total);

        } else if (userInput == 'b') {
            optionB(total);

        } else if (userInput == 'c') {
            optionC(total);
        } else {
            System.out.println("invalid option");
        }

    }

    public static char getChar() {
        Scanner sc = new Scanner(System.in);
        return sc.next().charAt(0);
    }

    public static Candidate getCandidate(String candidateName, Candidate[] arrayOfCandidates) {
        for (int i = 0; i < arrayOfCandidates.length; i++) {
            if (candidateName.equals(arrayOfCandidates[i].un)) {
                return arrayOfCandidates[i];
            }
        }

        return null;
    }

    public static void showCandidates(Candidate[] candidateArray){
        int counter = 1;
        for(int i = 0;i<candidateArray.length;i++){
            if(candidateArray[i] != null && candidateArray[i].un != null){
                System.out.println(counter +" "+candidateArray[i].getName());
                counter++;
            }
        }
        if(counter == 1){
            System.out.println("There were no candidates");
        }
    }

    public static void runElection(Candidate[] total, Candidate candidate, int runtime){
        for(int i = 0;i<runtime;i++){
            candidate.selectWinner(total);
        }
        System.out.println("The most common winner is "+candidate.selectWinner(total));
        System.out.println("There were no more winner");
    }



    public static void optionA(Candidate[] total){
        Candidate[] allCandidates = new Candidate[546];
        System.out.println("Please enter a candidate");
        Scanner sc = new Scanner(System.in);
        String givenCandidateString = sc.nextLine();
        allCandidates[0] = getCandidate(givenCandidateString,total);
        showCandidates(allCandidates);

    }

    public static void optionB(Candidate[] total){
        Random r = new Random();
        int random = r.nextInt(546);
        System.out.println("A random candidate is "+total[random]);
    }

    public static void optionC(Candidate[] total){
        System.out.println("Who should run the election?");
        Scanner sc = new Scanner(System.in);
        String givenCandidateString = sc.nextLine();
        Candidate givenCandidate = getCandidate(givenCandidateString, total);
        System.out.println("How many times should the election be ran?");
        int runtime = sc.nextInt();
        runElection(total,givenCandidate,runtime);
    }

    public String getName() {
        return "Jett";
    }

    public String getSlogan() {
        return "I am a jett one trick";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0) {
            return new Candidate_ec22572();
        }

        for (Candidate c : candidates) {
            if (c.getSlogan().equals("I am a jett one trick")) {
                return c;
            }
        }

        for (Candidate c : candidates) {
            if (c.getName().equals("Harry Potter")) {
                return c;
            }

        }

        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];

    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22572();

        if (votes.length == 0) {
            return new Candidate_ec22572();
        }

        Candidate currentWinner = votes[0];

        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }

}
