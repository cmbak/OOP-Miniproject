package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner; 
import java.util.ArrayList; 


// File Candidate_ec22887.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22887 extends Candidate {
    //print method for any type
    public static <T> void print(T message)
    {
        System.out.println(message);
    }
    
    //method to input a string
    public static String inputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String answer;
        print(message);
        answer = scanner.nextLine();
        return answer; 
    }
    
    //method to input an integer 
    public static int inputInteger(String message)
    {
        Scanner scanner = new Scanner(System.in);
        int answerint;
        print(message);
        answerint = Integer.parseInt(scanner.nextLine());
        return answerint; 
    }
    
    //method to carry out the election spec
    public static void main(String[] a)
    {
        boolean check = false; 
        int count = 0;  
        int outputcount = 0;
        String cusername = "";
        int rcandidate = 0;
        String option = ""; 
        //Inspired by ec221006
        Candidate[] allcandidates = A3.getCandidateArray();
        Candidate[] addcandidates = new Candidate[allcandidates.length]; 
        print("= Running Repeated Elections =");
        while(!check)
        {
            if (addcandidates[0] == null) //output of the candidates
            {
                print("None");
            }
            else
            {
                for (int i =0; i < addcandidates.length; i++)
                {
                    if (addcandidates[i] != null)
                    {
                        print((i+1)+". "+addcandidates[i].getName());                        
                    }
                }
            }
            option = inputString("Would you like to a) Add a specific candidate, b) Add a candidate at random, c) Run the election d) Exit");
            if (option.equals("a")) 
            {
                cusername = inputString("Please enter a username.");
                Candidate usercheck = A3.getByUsername(cusername, allcandidates);
                if (usercheck != null) //check for the username to ensure it is not null
                {
                    addcandidates[count] = A3.getByUsername(cusername, allcandidates);
                    count = count + 1;
                }
                else
                {
                    print("No username found"); 
                }
            }
            else if (option.equals("b")) //make use of randomCandidate method
            {
                rcandidate = randomCandidate(allcandidates); 
                addcandidates[count] = allcandidates[rcandidate];
                count = count + 1;
            }
            else if (option.equals("c")) //electionrun method
            {
                electionrun(allcandidates, addcandidates);
            }
            else if (option.equals("d")) //Exit
            {
                break; 
            }
            else
            {
                print("Invalid input, try again");
                check = false; 
            }
        }
    }
    
    public static void displayC(Candidate[] addcandidates, int count)
    {
        if (count == 0) 
        {
                print("None");
        }
        else
        {
            for (int i = 0; i < count; i ++) //displays names of candidates added
            {
                try{
                print((i+1)+". "+addcandidates[i].getName());
                }
                catch (Exception e){}
            }
        }
    }
    
    public static int randomCandidate(Candidate[] allcandidates) //Random index returned by method using Random()
    {
        Random r = new Random();
        int max = allcandidates.length-1; 
        int randomindex = r.nextInt(max);
        return randomindex; 
    }
    
    public static void electionrun(Candidate[] allcandidates, Candidate[] addcandidates) //electionrun method 
    {
        int repetitions = 0; 
        int currentl = 0; 
        boolean run = false; 
        String votedecider = "";
        Candidate[] votes = new Candidate[allcandidates.length];
        
        for (int i = 0; i < allcandidates.length; i++) //check for valid votecounter username
        {
            votedecider = inputString("Who should count the votes? ");
            if (A3.getByUsername(votedecider, allcandidates) != null)
            {
                break;
            }
            else 
            {
                print("Not found try again");
            }
        }
        Candidate votecounter = A3.getByUsername(votedecider, allcandidates);
        repetitions = inputInteger("How many times shall we run the election? ");
        for (Candidate c : addcandidates) //makes a seperate array for real Candidate objects
        {
            if (c!= null)
            {
                currentl = currentl + 1;
            }
        }
        Candidate[] condensed = new Candidate[currentl];
        for (int i = 0; i < currentl; i++)
        {
            condensed[i] = addcandidates[i];
        }
        
        Candidate[] winners = new Candidate[repetitions];
        for (int i = 0; i < repetitions; i++)
        {
            int index = 0;
            for (Citizen v: allcandidates) //Allows for every Candidate object to vote 
            {
                try{
                    votes[index] = v.vote(condensed);
                    index = index + 1; 
                }
                catch (Exception e){
                }
                
            }
            winners[i] = votecounter.selectWinner(votes);
        }
        commonWinner(winners); //Calls the commonWinner method 
    }
    
    public static void commonWinner(Candidate[] repetitionwinners)
    {
        ArrayList<Candidate> allwins= new ArrayList<>();
        //Inspired by ec22479 with ArrayList
        Candidate common = repetitionwinners[0];
        int highest = 1;
        int current;
        for (int i = 0; i < repetitionwinners.length; i++) //checks for the highest winner 
        {
            current = 1;
            for (int j = 0; j < repetitionwinners.length; j++)
            {
                if (repetitionwinners[i] == repetitionwinners[j])
                {
                    current = current + 1; 
                }
            }
            if (current > highest)
            {
                common = repetitionwinners[i];
                highest = current;
            }
            else if (current == highest && !(allwins.contains(repetitionwinners[i]))) //ensures that the winners are not repeated
            {
                allwins.add(repetitionwinners[i]);
            }
        }
        
        print("Most common winner(s) are: "); 
        
        for (Candidate c : allwins) //outputs the winners 
        {
            if (c != null)
            {
                print(c.getName());
            }
        }
        print("There were no other winners");
        print("");
    }

    public String getName() {
        return "Devin";
    }

    public String getSlogan() {
        return "Time for change!";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0)
        {
            return new Candidate_ec22593();
        }
        int middlevote = candidates.length / 2;
        return candidates[middlevote];
    }

    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0)
        {
            return new Candidate_ec22593();
        }

        Candidate winner = votes[0];
        int highest = 0; 
        for (Candidate current : votes){
            int count = 0;
            for (Candidate check : votes){
                if(check == current){
                    count++;
                }
            }
            if (count > highest){
                highest = count; 
                winner = current; 
            }
        }
        return winner; 
    }

}
