package OOP.ec22697.MP;

import java.util.*;

class  Candidate_ec221006 extends Candidate {
    public String getName() { 
        return "ArvindKejriwal";
    }
    
    public String getSlogan() {
        return "Free ki Bijli Pani-Paise de Ambani Adani";
    }
    
    public Candidate vote(Candidate[] candidates){
        
        //if array is empty, return own candidate
        if(candidates.length == 0){
            return new Candidate_ec221006();
        }
        else
        {
            return candidates[candidates.length-1];
        }   
    }
    
    public Candidate selectWinner(Candidate[] votes){
        if(votes.length == 0){
            return new Candidate_ec221006();
        }

        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes){
                if (x == c){
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
        
    }
    
    //a method to print a message and take an integer input
    public static int inputInt(String message)
    {
        int input = 0;
        boolean valid = false;
        Scanner scanner = new Scanner(System.in); 

        print(message);
        
        while(!valid)
        {
            if(scanner.hasNextInt())
            {
                input = scanner.nextInt();
               
                if(input<0)
                {
                    print("Invalid input! Please input positive integers only!");
                    scanner.nextLine();
                }
                else
                {
                    valid = true;
                }                

            }
            else
            {
                print("Invalid input! Please input positive integers only!");
                scanner.nextLine();
            }
        }

        return input;
    }
    //end

    //a method to print 
    public static void print(String message)
    {
     System.out.println(message);
     return;
    }//end

    //a method to print a message and take a string input
    public static String inputString(String message)
    {
        String input;
        Scanner scanner = new Scanner(System.in); 

        print(message);
        input = scanner.nextLine();

        return input;
    }//end

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        allCandidates[516] = new Candidate_ec22974();

        Collection<Candidate> myCandidates = new HashSet<Candidate>();

        String response = "";
        while (!response.equals("c")){
            response = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if (response.equals("a")) {

                Candidate toAdd = A3.getByUsername(inputString("Please enter a username."), allCandidates);
                if (toAdd == null) {
                    System.out.println("User not found.");
                } else {
                    myCandidates.add(toAdd);
                }
            } else if (response.equals("b")) {
                Candidate toAdd;
                boolean n = true;
                while(n){
                    toAdd = allCandidates[(new Random()).nextInt(allCandidates.length)];
                    if(toAdd != null){
                        n = false;
                        myCandidates.add(toAdd);
                    }
                }
            }
            Candidate[] temp = myCandidates.toArray(new Candidate[myCandidates.size()]);
            for(Candidate c : temp){
                print(c.getName());
            }
        }

        Candidate voteCounter = null;
        while(voteCounter == null){
            voteCounter = A3.getByUsername(inputString("Who should count the votes?"), allCandidates);
        }

        int loop = inputInt("How many times shall we run the election?");
        Candidate[] winnerList = new Candidate[loop];
        Candidate[] myCandidateArray = myCandidates.toArray(new Candidate[myCandidates.size()]);
            for(int a = 0; a<loop; a++)
            {
                Candidate[] votes = new Candidate[allCandidates.length];
                for(int i = 0; i<allCandidates.length; i++)
                {
                    try{
                        votes[i] = (allCandidates[i]).vote(myCandidateArray);
                        //throw new IllegalArgumentException();
                        throw new IndexOutOfBoundsException();
                    }
                    catch (IndexOutOfBoundsException e) {
                        //System.out.println("out of bound");
                    }
                    catch (IllegalArgumentException e){
                        //System.out.println("illegal arg");
                    }
                }

                winnerList[a] = voteCounter.selectWinner(votes);
            }
            print((voteCounter.selectWinner(winnerList)).getName() + "is the final Winner");

            return;
    }

}
