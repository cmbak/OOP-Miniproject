package OOP.ec22697.MP;// File Candidate_ec22668.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22668 extends Candidate {
    
    public static void main(String[] args) {
        

        String seperator = ("---------------------------------------------------------------------------------------------------------------------------------");
        Boolean Finished = false;
        int selectedCandidatesPosition = 0;
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] selectedCandidates = new Candidate[allCandidates.length];

        while (!Finished){
            print(seperator);
            print("What Would You like to Do? ");
            print("a) Add a specific candidate");
            print("b) Add a random candidate");
            print("c) Run the election");
            String Picker = readString("");
            print(seperator);


            if (Picker.equals("a"))
            {
                String chosenUsername = readString("Enter Candidate Username:  ");
                while (A3.getByUsername(chosenUsername, allCandidates)== null)
                {
                    print("Unknown Candidate Please Try again");
                    chosenUsername = readString("Enter Candidate Username:  ");
                }
                Candidate candidateToAdd = A3.getByUsername(chosenUsername, allCandidates);
                selectedCandidates[selectedCandidatesPosition] = candidateToAdd;
                selectedCandidatesPosition ++;
                print("The current candidates held are:  ");
                for (int i=0; i<selectedCandidatesPosition; i++)
                {
                    System.out.println(selectedCandidates[i].getName());

                }
                print(seperator);
            }
            else if (Picker.equals("b"))
            {
                Random random = new Random();
                Candidate candidateToAdd = allCandidates[random.nextInt(allCandidates.length-1)];
                selectedCandidates[selectedCandidatesPosition] = candidateToAdd;
                selectedCandidatesPosition ++;
                print("The current candidates held are:  ");
                for (int i=0; i<selectedCandidatesPosition; i++)
                {
                    System.out.println(selectedCandidates[i].getName());

                }
                print(seperator);

            }
            else if (Picker.equals("c"))
            {
                if (selectedCandidatesPosition > 0)
                {
                    Candidate candidateWhoCounts = A3.getByUsername(readString("Who Do You Want To Count The Votes?  :  "), allCandidates);
                    while (candidateWhoCounts == null)
                    {
                        print("Looks Like that candidate does not exist, or something went wrong, can you try someone else please :  ");
                        candidateWhoCounts = A3.getByUsername(readString("Who Do You Want To Count The Votes?  :  "), allCandidates);
                    }
                    int timesToRepeatElection = readInt("How Many Times Do You Want The Election To Run?  :");
                    Candidate[] Election = new Candidate[timesToRepeatElection*selectedCandidatesPosition-1];
    
                    for (int i=0; i<timesToRepeatElection; i++)
                    {
                        for(int j = 0; j<selectedCandidatesPosition; j++)
                        {
                            try {
                                Election[(selectedCandidatesPosition * i) + j] = selectedCandidates[j].vote(selectedCandidates);
                            } 
                            catch (Exception e) {
                                Election[j] = new Candidate_ec22668();
                            }        
    
                        }
                    }
                    Candidate winner = candidateWhoCounts.selectWinner(Election);
                    if (winner==null){
                        print("The winner candidate seems to have a bug, sorry!");
                        Finished = true;
                    }
                    else{
                        System.out.println("Most common winner is " + winner.getName());
                        Finished = true;
                    }
                    return;

                }
                else
                {
                    print("You Dont have any candidates selected!! Make sure you add candidates before running a election");
                }

            }
            else{
                print("Unknown input Try Again");
            }
        }
    }



    public static String readString(String message) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }

    public static void print(String message)
    {
        System.out.println(message);
    }

    public static int readInt(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    
    public String getName() {
        return "Munaib";
    }
    
    public String getSlogan() {
        return "Make Lemonade";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        for (Candidate c : candidates)
            if (c.getName().equals("Munaib"))
                return c;
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) {
        
        // If array is empty, return instance of this class.
        if (votesCast.length == 0) 
            return new Candidate_ec22668();
        
        // Choose winner at random from votes cast.
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];        
    }
}
