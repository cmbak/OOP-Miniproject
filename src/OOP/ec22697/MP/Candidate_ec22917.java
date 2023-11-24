package OOP.ec22697.MP;// File Candidate_ec22917.java
//

import java.util.*;

class Candidate_ec22917 extends Candidate {
    
    public static void main(String[] args) {

        //testing:
        //Candidate[] testing = A3.getCandidateArray();
        //new Candidate_ec22917().selectWinner(A3.getVotes(testing, testing));
        //System.out.println("stop");
        
        String initialOpt = "";
        while(!initialOpt.equalsIgnoreCase("a")) 
        {
            initialOpt = inputString("Would you like to\n a) exit\n b) run same election many times?");
            
            if(initialOpt.equalsIgnoreCase("b")) 
            {
                String opt = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                while(!opt.equalsIgnoreCase("a") && !opt.equalsIgnoreCase("b") && !opt.equalsIgnoreCase("c"))
                {
                    print("That is not one of the options.");
                    opt = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                }

                Candidate[] newContributions = new Candidate[A3.getCandidateArray().length];
                int pointer = 0; 

                if(opt.equalsIgnoreCase("a"))
                {
                    String username = inputString("Please enter the username you would like to include.");
                    Candidate addCandidate = A3.getByUsername(username, A3.getCandidateArray());


                    if (addCandidate != null) 
                    {
                        newContributions[pointer] = addCandidate; //from lab sheet 4
                        pointer++;
                    }

                    else 
                    {
                        print("User not found.");
                    }
                }

                else if(opt.equalsIgnoreCase("b"))
                {
                    int randFromAll = (new Random()).nextInt(A3.getCandidateArray().length);
                    Candidate addRand = A3.getCandidateArray()[randFromAll];
                    newContributions[pointer] = addRand;
                    pointer++;
                }

                else if(opt.equalsIgnoreCase("c")) // runs election by creating an array of winners, and printing the name of the highest scorer
                {
                    String whoCount = inputString("Who should count the votes?");
                    Candidate whoRuns = A3.getByUsername(whoCount, A3.getCandidateArray());

                    int howMany = inputInt("How many times shall we run the election?");
                    Candidate[] allWinners = new Candidate[howMany];

                    for(int i=0; i<howMany; i++)
                    {
                        Candidate[] votes = new Candidate[pointer];

                        for(int j=0; j<pointer; j++)
                        {
                            votes[j] = newContributions[j].vote(newContributions);
                        }

                        allWinners[i] = whoRuns.selectWinner(votes);
                    }
                    
                    int max = 0;
                    Candidate finalWin = new Candidate_ec22917();

                    for(int i=0; i<allWinners.length; i++)
                    {
                        int track = 0;
                        
                        for(int j=i+1; j<allWinners.length; j++)
                        {
                            if(allWinners[i]==allWinners[j])
                            {
                                track++;
                            }
                        }
                        if(track>max)
                        {
                            max = track;
                            finalWin = allWinners[i];
                        }
                    }

                    print("Most common winner: " + finalWin.getName());
                }
            }
            else
            {
                print("That is not an option.");
            }
        }
    }
    
    public static String print(String message) {
        System.out.println(message);
        return message;
    }
    
    // inputString method
    public static String inputString(String question)
    {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println(question);
        userInput = scanner.nextLine();

        while(userInput.isEmpty()) {
            userInput = inputString(question);
        }

        return userInput;
    }
    
    // inputInt method
    public static int inputInt(String question)
    {
        String userInput = inputString(question);
        while(!isInt(userInput))
        {
            print("Please type an integer.");
            userInput = inputString(question);
        }
        return Integer.parseInt(userInput);
    }

    // checks if each character in the String is an integer
    public static boolean isInt(String userInput)
    {
        boolean check = true;
        int index = 0;

        while(check && index<userInput.length())
        {
            if(!Character.isDigit(userInput.charAt(index))) //built in methods
            {
                check = false;
            }
            index++;
        }

        return check;
    }
    
    public String getName() {
        return "Umaru";
    }
    
    public String getSlogan() {
        return "Game!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Umaru"))
                return c;
        
        // Otherwise, default to random candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        /*
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22917();
        
        // Nullify all candidates that have more than one vote
        for(int i=0; i<votes.length; i++)
            for(int j=i+1; j<votes.length; j++)
                if(votes[j].equals(votes[i]))
                    votes[i] = null;
        
        // Declare new array which will not have any null candidates
        Candidate[] oddList = new Candidate[votes.length];
        
        // Create a new array without the null candidates
        int current=0;
        for(int i=0; i<votes.length; i++)
            if(votes[i]!=null)
                oddList[current]=votes[i];
                current++;
        
        // Choose a random candidate from array where duplicates are removed
        Random r = new Random();
        int c = r.nextInt(oddList.length);
        Candidate chosenOne = oddList[c];

        return chosenOne;
        */

        // FIXED (new) SELECT WINNER METHOD (previous method was only returning null)

        // If array is empty, return instance of this class.
        if (votes.length == 0)
            return new Candidate_ec22917();

        HashMap<Candidate, Integer> singles = new HashMap<>();

        for(int i=0; i<votes.length; i++) {
            singles.put(votes[i], 0);
        }

        for(int i=0; i<votes.length; i++){
            if(singles.containsKey(votes[i])){
                singles.put(votes[i], singles.get(votes[i])+1);
            }
        }

        List<Candidate> newSin = new LinkedList<>();

        int count = 0;
        for(int i=0; i<votes.length; i++){
            if(singles.get(votes[i]) == 1){
                newSin.add(votes[i]);
                count++;
            }
        }

        Candidate[] finfinsin = new Candidate[count];
        newSin.toArray(finfinsin);

        //ArrayList<Candidate> newSin = new ArrayList<>((Collection) singles);



        /*
        Candidate[] finSin = new Candidate[singles.size()];
        for(int i=0; i<finSin.length; i++)
            finSin[i] = singles.getKey();
            */
        //System.out.println("stop");
        //System.out.println("finfin " + finfinsin.length);

        Random r = new Random();
        int c = r.nextInt(finfinsin.length);
        Candidate chosenOne = finfinsin[c];

        return chosenOne;
    }
}

