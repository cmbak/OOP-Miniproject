package OOP.ec22697.MP;// File Candidate_ec221002.java
//

import java.util.*;
class Candidate_ec221002 extends Candidate {
    
    
        public static void main(String[] args) {

            Candidate[] allstuds = A3.getCandidateArray();
            Candidate[] newcan = new Candidate[allstuds.length];
            
            
            System.out.println("Would you like to:");


            String task = input("a) add a specific candidate, b) add a candidate at random, c) run the election?");
            int record = 0;
            
            
            if (task.equals("a")) {
                String name = input("please enter a name...");
                Candidate cans = A3.getByUsername(name, allstuds);
                newcan[record] =  cans;
                record++;
                
                System.out.println("Adding a specific candidate...");
            } else if (task.equals("b")) {
                Random random = new Random();
                int random_Index = (int) (Math.random() * allstuds.length);
                newcan[record] = allstuds[random_Index]; 
                record++;
                System.out.println("Adding a candidate at random...");
            } else if (task.equals("c")) {
                String countvote = input("Who should count the votes?");
                Candidate name_cand = A3.getByUsername(countvote, allstuds);
                int num = inputInt("How many times shall we run the election?");
                loopElection(newcan, record, num, name_cand, allstuds);
                System.out.println("Running the election...");
            } else {
                // Invalid option
                System.out.println("Invalid option.");
            }
        }
    
        
    
    // adapted some code  from ec21582 !



    
   public static int inputInt(String message)
    {
        int num;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        num = Integer.parseInt(scanner.nextLine());

        return num;
    }

    public static String input(String message)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message + " ");
            String answer = scanner.nextLine();
            return answer;
        }
    public String getName() {
        return "Saul Goodman";
    }
    
    public String getSlogan() {
        return "Better Call Saul";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec221002();
        
        if (candidates.length != 0) r = candidates[0];
 
        
        
        return r;
    }
    
    public static void loopElection(Candidate[] votes, int record, int num, Candidate voteCount, Candidate[] names){
        Candidate[] Election_new = new Candidate[record];
        for (int i = 0; i < record; i++) {
            Election_new[i] = votes[record];
        }
    
        
    }
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec221002();
        
        if (votes.length != 0) r = votes[0];
 
        
        // yes
        return r;
    }
    
}
