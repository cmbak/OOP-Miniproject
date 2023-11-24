package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22723 extends Candidate {
    
    public String getName() {
        return "Rohaan";
    }
    
    public String getSlogan() {
        return "More video games";
    }
    public static void main(String[]args){
        
        Candidate[] all_candidates = A3.getCandidateArray();
        election_menu(all_candidates);
        return;
    }
    
    public static void election_menu(Candidate[] all_candidates){
        
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        Candidate[] candidate_list = new Candidate[all_candidates.length];
        int option;
        
        while (true){
            
            System.out.println("= Running Repeated Elections =");
            if (candidate_list[0] == null){
                System.out.println("None");
            }
            else{
                for (int i=0; i < count; i++){
                    System.out.println(i+1 + ". " + candidate_list[i]);
                }
            }
            
            
            System.out.println("Would you like to  1) add a specific candidate 2) add a candidate at random 3) run the election?");
            option = scanner.nextInt();
            if (option == 1){
                candidate_list[count] = add_specificCandidate(all_candidates);
                count = count + 1;
            }
            else if (option == 2){
                int random_candidate = new Random().nextInt(all_candidates.length);
                candidate_list[count] = all_candidates[random_candidate];
                count = count + 1;
            }
            else if (option == 3){
                run_election(count, all_candidates, candidate_list);
                break;
            }
   
        }
    }
    
    
    public static Candidate add_specificCandidate(Candidate[] all_candidates){
        
        Scanner scanner = new Scanner(System.in);
        String username;
        Candidate new_candidate = null;
        
        while (true){
            
            System.out.println("Please enter a username.");
            username = scanner.nextLine();
            
            new_candidate = A3.getByUsername(username, all_candidates);
            
            if (new_candidate == null){
                System.out.println("Invalid username!");
                System.out.println("Please enter valid username");
            }
            else {
                break;
            }
            
        }
        return new_candidate;
    
    }
    
    public Candidate vote(Candidate[] candidate_list) {
        
        if (candidate_list.length == 0) {
            return new Candidate_ec22723();
        }

        for (Candidate c : candidate_list)
        {
            if (c.getSlogan().equals("More video games"))
                return c;
        }
        
  
        for (Candidate c : candidate_list){
            if (c.getName().equals("Rohaan"))
                return c;
        }
        
        return candidate_list[candidate_list.length-1];
    }
    
    public Candidate selectWinner (Candidate[] candidate_list) {
        Candidate winner = null;
        int highestVotes = 1;
        
        if (candidate_list.length == 0){
            winner = new Candidate_ec22977();
        }
        else{
            
            for (int i = 0; i < candidate_list.length; i++){
                int currentVotes = 1;

                for (int j = i+1; j < candidate_list.length; j++){

                    if (candidate_list[i] == candidate_list[j]){
                        currentVotes = currentVotes + 1;
                    }
                }

                if (currentVotes > highestVotes){
                    highestVotes = currentVotes;
                    winner = candidate_list[i];
                }
            }   
        }
        
        return winner;      
    }
    
    public static void run_election(int count, Candidate[] all_candidates, Candidate[] candidate_list){
        
        Scanner scanner = new Scanner(System.in);
        Candidate vote_counter = null;
        String input_voteCounter;
        int n;
        Candidate WinnerName = null;
        
        while (true){
            
            System.out.println("Who should count the votes?");
            input_voteCounter = scanner.nextLine();
            vote_counter = A3.getByUsername(input_voteCounter, all_candidates);
            
            if (vote_counter == null){
                System.out.println("Invalid username!");
                System.out.println("Please enter valid username");
            }
            else{
                break;
            }

        }
        
        System.out.println("How many times shall we run the election?");
        n = scanner.nextInt();
        
        

        Candidate[] winner = new Candidate[n * (count -1)];

        for (int i = 0; i <= n; i++){
            for (int j = 0; j < count; j++){
                winner[(count*i) + j] = candidate_list[j].vote(candidate_list);
            }
        }

        WinnerName = vote_counter.selectWinner(winner);
        System.out.println("Most common winner is: " + WinnerName.getName());

    
    }

     

}

