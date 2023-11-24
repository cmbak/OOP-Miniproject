package OOP.ec22697.MP;// File Candidate_jpp335448.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;

public class Candidate_jpp335448 extends Candidate {

    // scan string and return
    public static String getStr(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);
        
        return scanner.nextLine();
    }
    // scan int and return
    public static int getInt(String q) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(q);
        
        return scanner.nextInt();
    }
    // main 
    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        String candidateName;
        int candidateCount;

        candidateCount = getInt("Enter a number of people for candidates: ");
        Candidate[] candidatesList = new Candidate[candidateCount];

        for (int i = 0; i < candidateCount; i++) {
            System.out.print("Add more candidates: ");
            candidateName = getStr("Enter the name of the candidate: ");
            candidatesList[i] = A3.getByUsername(candidateName, allCandidates);

        }
        // counter choosing
        String counterName = getStr("Name the counter");
        Candidate counter = A3.getByUsername(counterName, candidatesList);
        //get number of re-elction
        int electionsNum = getInt("How long is the election?");
        Candidate[] v = new Candidate[electionsNum * candidateCount];

        for (int j = 0; j < electionsNum; j++) {
            for (int z = 0; z < electionsNum; z++) {
                v[z + (j * candidateCount)] = candidatesList[z].vote(candidatesList);
            }
        }
        // printout the winner
        Candidate winnerC = counter.selectWinner(v);
        System.out.println(
                "Out final winner is " + winnerC.getName() + ".");
    }
    
    public String getName() {
        return "Park";
    }

    public String getSlogan() {
        return "More Trees!";
    }
    // voting method
    public Candidate vote(Candidate[] candidates) {
        Random r = new Random();
        // add myself when empty
        if (candidates.length == 0)
            return new Candidate_jpp335448();
        int k = r.nextInt(candidates.length);
        return candidates[k];

    }
    // selectwinner
    public Candidate selectWinner(Candidate[] candidates) {
        // top vote
        int topV = 0;
        Candidate winner = new Candidate_jpp335448();

        if (candidates.length <= 0) {
            return new Candidate_jpp335448();
        }
        for (Candidate x : candidates) {
            int count = 0;
            for (Candidate y : candidates) {
                if (x == y) {
                    count = count + 1;
                }
            }
            if (count > topV) {
                topV = count;
                winner = x;
            }
        }
        return winner;
    }
}
