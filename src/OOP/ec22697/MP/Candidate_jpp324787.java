package OOP.ec22697.MP;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

class Candidate_jpp324787 extends Candidate {

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        ArrayList<Candidate> candidatesList = new ArrayList<Candidate>();
        String answer = "";
        while (!answer.equals("c")) {
            printCandidates(candidatesList);
            answer = inputString("Choose one of the options bellow:\n a)add a specific candidate\nb)add a candidate at random\nc)run the election?");
            if (answer.equals("a")) {
                String username = inputString("Please enter a username");
                Candidate user = A3.getByUsername(username, allCandidates);
                if (user != null) {
                    candidatesList.add(user);
                } else {
                    System.out.println("candidate not found");
                }
            } else if (answer.equals("b")) {
                Random rd = new Random();
                int random = rd.nextInt(allCandidates.length);
                addRandom(random, allCandidates, candidatesList);
            } else {
                System.out.println("choose one of the given options(a,b,c) :");
            }
        }
        String input = inputString("who should count the votes?");
        Candidate cd = A3.getByUsername(input, allCandidates);
        if (cd == null) {
            System.out.println("can't find candidate");
        } else {
            int times = inputInt("how many times should we run the elections?");
            String winner = "";
            for (int i = 0; i < times; i++) {
                int highestCount = 0;
                for (Candidate c : candidatesList) {
                    int count = 0;
                    for (Candidate x : candidatesList) {
                        if (x == c)
                            count++;
                    }
                    if (count > highestCount) {
                        highestCount = count;
                        winner = c.getName();
                    }
                }
            }
            System.out.println("The winner is: " + winner);
        }
    }

    public static int inputInt(String text) {
        System.out.println(text);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void addRandom(int a, Candidate[] b, ArrayList<Candidate> c) {
        c.add(b[a]);
    }

    public static void printCandidates(ArrayList<Candidate> candidates) {
        System.out.println("Candidates are: ");
        for (int i = 0; i < candidates.size(); i++) {
            System.out.println(candidates.get(i).getName());
        }
    }

    public static String inputString(String text) {
        System.out.println(text);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String getName() {
        return "Messi";
   }
    
    public String getSlogan() {
        return "More me!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Me"))
                return c;
        
        // Otherwise, default to ramdom candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) {
        
        // If array is empty, return instance of this class.
        if (votesCast.length == 0) 
            return new Candidate_jpp324787();
        
        // Choose winner at random from votes cast.
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];        
    }
}

