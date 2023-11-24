package OOP.ec22697.MP;// File Candidate_ex21299.java

import java.util.Random;
import java.util.Scanner;

class Candidate_ex21299 extends Candidate {
    // Used to get numbers from the user
    public static String inputstring(String question) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        String answer = scanner.nextLine();
        return answer;
    }

    //Used to get words from the user
    public static int inputint(String question) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        String answer = scanner.nextLine();
        return Integer.parseInt(answer);
    }


    // Main method
    public static void main (String[] args) 
    {
        Candidate[] all_candidates = A3.getCandidateArray();
        Candidate[] voted_candidates = new Candidate[all_candidates.length];

        int count = 0; 

        String answer = "a";
        //While loop that keeps on giving the user the 4 options
        while(answer != "exit") 
        {
            answer = inputstring("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) Exit\n>");
            //Adds a specific candidate given by the user
            if (answer.equals("a")) {
                String username = inputstring("Please enter a username.");
                //checks if the specified candidate exists, if so adding it to the list of nominated candidates
                if(A3.getByUsername(username, all_candidates) != null ) {
                    voted_candidates[count] = A3.getByUsername(username, all_candidates);
                    count++;
                    // Prints out the current nominated candidates
                    for(int i=0;i<count; i++) {
                        if(voted_candidates[i] != null)
                            System.out.println((i+1) + "." + voted_candidates[i].getName());
                    }
                }
                // Tells user to input a valid candidate
                else System.out.println("Please enter a valid candidate.\n");

            }
            // Adds a random candidate
            else if (answer.equals("b")) {

                // Adds a random candidate to the list of nominated candidates
                int random_num = (new Random()).nextInt(all_candidates.length);
                voted_candidates[count] = all_candidates[random_num]; 
                count++;

                // Prints out the current nominated candidates
                for(int i=0;i<count; i++) {
                    if(voted_candidates[i] != null) 
                        System.out.println((i+1) + "." + voted_candidates[i].getName()); 
                }

            }
            // Runs the election
            else if (answer.equals("c")) {
                String user_counter = inputstring("Who should count the votes?");
                Candidate counter = A3.getByUsername(user_counter, all_candidates);
                int x_times = inputint("How many times shall we run the election?");

                runElection(all_candidates, voted_candidates, counter, x_times, count);
                answer = "exit";
            }
            //Stops the program
            else if(answer.equals("d")) {
                answer = "exit";


            }
            //Default option
            else {
                System.out.println("Not one of the options, try again.");
            }
        }
    }

    // Method that runs the election
    public static void runElection (Candidate[] all_candidates, Candidate[] voted_candidates, Candidate counter, int x_times, int count)
    {
        Candidate[] people_voted = new Candidate[count];
        // Creates a copy of the voted candidates array
        System.arraycopy(voted_candidates, 0, people_voted, 0, count);

        Candidate[] winners = new Candidate[x_times];
        Candidate[] everyones_votes = new Candidate[all_candidates.length];

        //Makes each candidate class vote for the nominated candidates for each run and finds the winner, catches any errors that may thrown by the candidate vote methods
        for (int i=0; i<x_times; i++) {
            for (int j=0; j<all_candidates.length; j++) {
                if(all_candidates[j] != null) {
                    try {
                        everyones_votes[j] = (all_candidates[j]).vote(people_voted);
                    }

                    catch (Exception e) {
                        // This happens when some candidates have bad code inside their class, in this case it is just ignored
                    }
                }
            }
            // Selects the most voted nominee for this run
            winners[i] = counter.selectWinner(everyones_votes);
        }
        // Selects the overall winner from all the runs
        Candidate actual_winner = counter.selectWinner(winners);
        System.out.println("The winner is " + actual_winner.getName());
    }
    // Returns name
    public String getName() {
        return "Yash K";
    }

    // Returns slogan
    public String getSlogan() {
        return "Save the oceans!";
    }

    // Vote method
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ex21299();

        if (candidates.length == 0)
            return new Candidate_ex21423();

        r = candidates[0];

        for (Candidate x : candidates)
            if (x.getSlogan().equals("No more university fees!"))
                return x;
        
        for (Candidate x : candidates)
            if (x.getName().equals("David J"))
                return x;
        
        return r;
    }
    // This Method chooses the winner form the votes array
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ex21299();
        
        if (votes.length != 0) r = votes[0];
        
        int highestCount = 0;
        int count = 0;
        
        if (votes.length == 0) 
            return new Candidate_ex21423();
        
        for (Candidate c : votes) {
            count = 0;
            for (Candidate x : votes) {
                if (x == c)
                    count++;
            }
            if (count > highestCount) {
                highestCount = count;
                r = c;
            }
        }
        
        return r;
    }
    
}
