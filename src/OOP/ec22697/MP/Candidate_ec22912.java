package OOP.ec22697.MP;// File Candidate_ec22912.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22912 extends Candidate {
    
    //Very good name
    public String getName() {
        return "Me";
    }
    
    //Very good slogan
    public String getSlogan(){
        return "You get nothing";
    }

    //Chooses me if there is no one to vote for or selects a random candidate to vote for
    public Candidate vote(Candidate[] candidates) {
        if(candidates.length == 0){
            return new Candidate_ec22912(); 
        }            
        
        Random r = new Random();
        int random_cand = r.nextInt(candidates.length);
        return candidates[random_cand];
    }
    
    
    // Chooses the candidate with the most votes or selects myself if no one else is voted for
    public Candidate selectWinner(Candidate[] votesCast) {
        if(votesCast.length == 0){
            return new Candidate_ec22912(); 
        }  
        
        int winner_count = 0;
        Candidate winner_cad = votesCast[0];
        
        for(int i = 0 ; i < votesCast.length; i++){
            int count = 1;
            for(int j = i + 1; j < votesCast.length; j++){
                if(votesCast[i] == votesCast[j]){
                    count = count + 1;
                }
            }
            if (count >= winner_count){
                winner_count = count;
                winner_cad = votesCast[i];
            }
            
        }      
        return winner_cad;
    }

        public static void main(String[] args){
        boolean continue_election = true;
        Candidate[] all_cand = A3.getCandidateArray();
        Candidate[] election_cand = new Candidate[all_cand.length];
        int tracker = 0;
        
        
        while(continue_election){
            String option = inputMessage("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            
            //Using a method of adams I find person user entered is in all candidate list
            if(option.equals("a")){
                tracker = SpecificCandFind(all_cand,election_cand,tracker);
            }
            
            else if(option.equals("b")){
                tracker = RandomCandAdder(all_cand,election_cand,tracker);
            }
            
            else if(option.equals("c")){
                boolean end = false;
                while(!end){
	            int election_amount = inputInt("how many elections are taking place?");
		    Candidate election_counter =ChooseCounter(all_cand);
                    Candidate election_winner = ElectionRun(all_cand, election_cand,tracker,election_amount,election_counter);
                    String again = inputMessage("Would you like to any button) exit b) run same election many times");
                    if(!again.equals("b")){
                        end = true;
                        continue_election = false;
                    }
		}                
                
            }
            
            else{
                System.out.println("Not a valid input");
            }                
        
        }
        return;
    }


    public static Candidate ElectionRun(Candidate[] all_cand, Candidate[] election_cand, int tracker, int election_amount, Candidate election_counter){
        Candidate[] electives = new Candidate[tracker];
        Candidate[] voters_select = new Candidate[all_cand.length * election_amount];
        int voter_tracker = 0;
        
        for(int i = 0; i < tracker;i++){
                electives[i] = election_cand[i];
        }

        
        for(int i = 0 ; i < election_amount; i++){
            
            for(int j = 0; j <all_cand.length ;j++){
                
                try{	
                    voters_select[voter_tracker] = all_cand[j].vote(electives);
                }
                catch(Exception e){
                    Candidate_ec22912 myself_1 = new Candidate_ec22912();
                    voters_select[voter_tracker] = myself_1.vote(electives);
                }
                voter_tracker = voter_tracker + 1;
            }
            
        }

	Candidate election_winner = null;        
        try{	
            election_winner = election_counter.selectWinner(voters_select);
        }
        catch(Exception e){ 
	    Candidate_ec22912 myself = new Candidate_ec22912();
            election_winner = myself.selectWinner(voters_select);
        }
        
        return election_winner;
    }


    public static Candidate ChooseCounter(Candidate[] all_cand){
        boolean check_elec_counter = false;
	Candidate election_counter = null;        

        while(check_elec_counter==false){
            String election_counter_string = inputMessage("Who should count the votes?");
            election_counter = A3.getByUsername(election_counter_string,all_cand);
            
            if(election_counter == null){
                System.out.println("Not a valid candidate.");
            }
            else{
                check_elec_counter = true;
            }
    
        }
        return election_counter;
    }


    //Checks to see if the specific candidate for option (a) is a real candidate or not
    public static int SpecificCandFind(Candidate[] all_cand, Candidate[] election_cand, int tracker){
        
        String spec_cand_string = inputMessage("What is the username of the specific candidate you would like to add to the election?");
        Candidate spec_cand = A3.getByUsername(spec_cand_string,all_cand);
        
        if(spec_cand == null){
            System.out.println("The candidate entered is not in the list!!");
        }
        else{
            election_cand[tracker] = spec_cand;
            tracker = tracker + 1;
        }
        
        return tracker;
    }

    public static int RandomCandAdder(Candidate[] all_cand, Candidate[] election_cand, int tracker){
        Random rand = new Random();
        int rand_num = rand.nextInt(all_cand.length);
        election_cand[tracker] = all_cand[rand_num];
        tracker = tracker + 1;
        return tracker;
        
    }

    public static String inputMessage(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
    

    //Method used to check if user inputs a positive integer
    public static String intChecker(String message){

        String string_num = inputMessage(message);

        boolean checker = true;
        final int string_num_len = string_num.length() - 1;
        int counter = 0;

        while(counter <= string_num_len && checker == true){

            checker = Character.isDigit(string_num.charAt(counter));
            counter = counter + 1;

            if(checker == false){
                System.out.println("NOT VALID INTEGER");
                string_num = intChecker(message);
            }

        }

        return string_num;
    }

    //Method used to output question and return user input as integer
    public static int inputInt(String message)
    {
        String string;
        int number;
        string = intChecker(message);

        number = Integer.parseInt(string);
        return number;
    }
    
              
    
}

