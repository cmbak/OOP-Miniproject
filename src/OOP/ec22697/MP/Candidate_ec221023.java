package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

// File Candidate_ec221023.java
//
// Machine generated stub for Assignment 2

class Candidate_ec221023 extends Candidate {
    
    public static void main (String[] args){
        boolean exit = false;
        int[] counter = {0};
        
        Candidate[] candidateArray = A3.getCandidateArray(); // get array full of potential candidates
        Candidate[] ourCandidates = new Candidate[candidateArray.length]; 
        
        while (!exit) {
            char choice = getChar("\nChoose election type a) add a specific candidate " + 
                               "b) add candidate at random " +
                               "c) run the election" +
                                "d) exit");
            
            switch (choice) {
                case 'a': addCandidate(counter, candidateArray, ourCandidates); break;
                case 'b': addRandomCandidate(counter, candidateArray, ourCandidates); break;
                case 'c': runElection(counter, candidateArray, ourCandidates);
                case 'd': exit = true; break;
                    
                default: System.out.println("Option '"+ choice +"' not available.");
            }
        }
        
        return;
    }
    
    public static String inpStr(String msg)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(msg);
        
        String input = scanner.nextLine();
        
        return input;
    }
    
    
    public static int inpInt(String msg)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(msg);
        
        String input = scanner.nextLine();
        int intInput;
        
        try 
        {
            intInput = Integer.parseInt(input);
            if (intInput < 1)
            {
                return -1;
            }
        }
        catch (Exception e)
        {
            return -1;
        }
        
        return intInput;
    }
    
    public static char getChar(String msg)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(msg);
        
        String input = scanner.nextLine();
        
        return ((input.length() == 0) ? ' ' : input.charAt(0));
    }
    
    
    
    public static boolean checkDuplicates(Candidate newCandidate, Candidate[] ourList, int[] count)
    {
        boolean duplicate = false;
        
        for (int i = 0; i <= count[0]; i++)
        {
            if (ourList[i] == newCandidate)
            {
                duplicate = true;
                return duplicate;
            }
        }
        
        return duplicate;
    }
    
    public static void addCandidate(int[] count, Candidate[] arrayList, Candidate[] ourList) {
        String usrName = inpStr("Enter the username of the Candidate you would like: ");
        Candidate newCandidate = A3.getByUsername(usrName, arrayList);
        
        if ((newCandidate != null) && !(checkDuplicates(newCandidate, ourList, count))) 
        {
            System.out.println("This Candidate cannot be added into the system (duplicate or non-existent candidate detected!)");
            return;
        }
        
        ourList[count[0]] = newCandidate;
        count[0]++;
        
        return;
    }
    
    public static void addRandomCandidate(int[] count, Candidate[] arrayList, Candidate[] ourList) {
        
        final int candidateLength = arrayList.length;
        Random genInt = new Random();
        Candidate randOption = arrayList[genInt.nextInt(candidateLength)];
        
        while (!(checkDuplicates(randOption, ourList, new int[] {candidateLength}))) 
        {
            randOption = arrayList[genInt.nextInt(candidateLength)];
        }
        
        ourList[count[0]] = randOption;
        count[0]++;
        
        return;
    }
    
    public static void runElection(int[] count, Candidate[] arrayList, Candidate[] ourList)
    {
        String usrName = inpStr("Who should count the votes?");
        int howManyTime = inpInt("How many times shall we run the election?");
        
        Candidate userChoice = A3.getByUsername(usrName, ourList);
        
        if (userChoice == null)
        {
            System.out.println("You have entered a non-existent candidate.");
            return;
        }
        
        if (howManyTime != -1)
        {
            System.out.println("You have entered an erroneous run-time value.");
            return;
        }
        
        Candidate[] votes = new Candidate[count[0] * howManyTime]; // Calculates maximum size possible with our parameters.
    
        for (int i = 0; i < howManyTime; i++)
        {
            for (int j = 0; j < count[0]; j ++)
            {
                votes[(i * count[0]) + j] = ourList[j].vote(ourList);
            }
        }
        
        Candidate winner = userChoice.selectWinner(votes);
        System.out.println("The winner of the election: " + winner.getName());
        
    }
    
    public String getName() {
        return "Miraj";
    }
    
    public String getSlogan() {
        return "e";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec221023();
        
        if (candidates.length != 0) r = new Candidate_ec221023();
 
        for  (Candidate c : candidates) 
        {
            if (c.getName().equals("Mr. Bean") || c.getSlogan().equals("Teddy!")) 
            {
                return c;
            }
        }
        
        return r;
    }
    
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec221023();
        
        if (votes.length != 0) r = votes[0];
 
        r = new Candidate_ec221023();
        
        return r;
    }
    
}
