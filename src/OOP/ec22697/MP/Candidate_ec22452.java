package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;



public class Candidate_ec22452 extends Candidate {
    public String getName() {
        return "derek";
    }

    public String getSlogan() {
        return "1000 milligrams tren? like no way dude.";
    }

    public Candidate vote(Candidate[] candidates) {
        for (int i=0; i<= candidates.length; i++) {
            if (candidates[i].getSlogan() == "We need to cook") {
                return candidates[i];
            }
            break;
        }
        return new Candidate_ec22452();
    }

    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec22452();
        }
        Random r = new Random();
        int c = r.nextInt(votes.length);
        return votes[c]; 
    }
    
    public static Candidate enter_name() {
        System.out.println("Please enter a username");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        return A3.getByUsername(username, A3.getCandidateArray());
        
    }
    

    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        boolean terminate = false;
        Candidate[] candidates = new Candidate[1000];
        String choice = "f";
        String vote_counter;
        Candidate vote_counter_object;
        int i = 0;
        Candidate[] votes = new Candidate[1000];
        while (!terminate){
            
            int iterations = 0;
            Candidate[] winners = new Candidate[1000];
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            choice = scanner.nextLine();
            if (choice.equals("a")) {
                candidates[i] = enter_name();
                i = i + 1;
                for (int j=0; j<=candidates.length-1; j++) {
                    if (!(candidates[j] == null)) {
                        System.out.print(j+1);
                        System.out.print(" ");
                        if (!(candidates[j] == null)){
                            System.out.println(candidates[j].getName());
                        }
                    }
                
                }
            } else if (choice.equals("b")) {
                  System.out.println("placeholder");
            } else if (choice.equals("c")){
                System.out.println("Who should count the votes?");
                vote_counter = scanner.nextLine();
                vote_counter_object = A3.getByUsername(vote_counter, candidates);
                System.out.println("How many times shall we run the election?");
                iterations = scanner.nextInt();
                
                for (int z = 0; z <= iterations-1; z++) {
                    votes[z] = vote_counter_object.vote(candidates);
                    
                    
                }
                for (int z=0; z<= iterations-1; z++) {
                    winners[z] = vote_counter_object.selectWinner(votes);
                }
                int candidate_count = 0;
                int winning_candidate_count = 0;
                Candidate current_winner = winners[0];
                for (int z=0; z <= winners.length - 1; z++) {
                    for (int j=1; j<=winners.length - 1; j++) {
                        if (current_winner == winners[j]) {
                            candidate_count++;
                        }
                    }
                  if (candidate_count > winning_candidate_count) {
                      winning_candidate_count = candidate_count;
                      current_winner = winners[i];
                  }
                }
                System.out.println("The most common winner is ");
                System.out.print(current_winner);
            } else {
                System.out.println("Invalid option!");
            }
            String option = scanner.nextLine();
            if (option.equals("a")) {
                terminate = true;
            }
            
        }
    }
}
