package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;
class  Candidate_ec22804 extends Candidate {
    public String getName() { 
        return "Kj";
    }
    
    public String getSlogan() {
        return "i love adam ";
    }
    
    public Candidate vote(Candidate[] candidates){
        
        //if array is empty, return own candidate
        if(candidates.length == 0){
            return new Candidate_ec22804();
        }
        else
        {
            return candidates[candidates.length-1];
        }   
    }
    
    public Candidate selectWinner(Candidate[] votes){
        if(votes.length == 0){
            return new Candidate_ec22804();
        }

        Candidate Winner = votes[0];
        
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
                Winner = c;
            }
        }
        
        return Winner;
        
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

    public static void main(String[] args)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        int Length = 1;
        Candidate[] candidates = new Candidate[Length];

        String inputOptions = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        
        if(inputOptions.equals("a"))
        {        
            inputOptions = inputString("Please enter a username.");

            Candidate name = A3.getByUsername(inputOptions, allCandidates);

            if(name != null)
            {
                print("user not found");
            }
            else
            {
                candidates[0] = name;
                Length = Length + 1;
                Candidate[] temp = new Candidate[Length];
                for(int i = 0; i<Length-1; i++)
                {
                    temp[i+1] = candidates[i];
                }
                candidates = temp;
            }
        }
        else if(inputOptions.equals("b"))
        {
            int randNum = (new Random()).nextInt(allCandidates.length);
            candidates[0] = allCandidates[randNum];
            Length = Length + 1;
            Candidate[] temp = new Candidate[Length];
            for(int i = 0; i<Length-1; i++)
            {
                temp[i+1] = candidates[i];
            }
            candidates = temp;
        } 
        else if(inputOptions.equals("c"))
        {
            inputOptions = inputString("Who should count the votes?");
            
            int rounds = inputInt("How many times shall we run the election?");
            Candidate[] FinalCadidates = new Candidate[rounds];
            
            for(int a = 0; a<rounds; a++)
            {
                Candidate[] votes = new Candidate[allCandidates.length];
                for(int i = 0; i<allCandidates.length; i++)
                {
                    votes[i] = (allCandidates[i]).vote(candidates);
                }
                
                FinalCadidates[a] = (A3.getByUsername(inputOptions, allCandidates)).selectWinner(votes);
            }
            Winner(FinalCadidates);
            
        }

        return;
    }
    
    public static void Winner(Candidate[] winnersList)
{
    String[] finalNames = new String[winnersList.length];
    int[] count = new int[winnersList.length];
    for(int i = 0; i<winnersList.length; i++)
    {
        finalNames[i] = (winnersList[i]).getName();
       count[i] = 0;
    }
    
    String[] noRepeat = removeDuplicates(finalNames);
    
    
    for(int i = 0; i<winnersList.length; i++)
    {
        for(int j = 0; j<winnersList.length; j++)
        {
            if(noRepeat != null && noRepeat[i].equals(winnersList[j]))
            {
                count[i]++;
            }
        }
    }
    
    Sort(count, noRepeat);
    
    print("The 3 most frequent winners in descending orders-");
    for(int i = 1; i<4; i++)
    {
        print(i+". " + noRepeat[i-1] + " won " + count[i-1] + " times.");
    }
    
    return;
    
}

public static String[] removeDuplicates(String[] array)
{
    String[] noRepeat = new String[array.length];
    int counter = 0;
    
    for(int i = 0; i<array.length; i++)
    {
        boolean duplicate = false;
        for(int j = 0; j<i; j++)
        {
            if(array[i].equals(noRepeat[j]))
            {
                duplicate = true;
            }
        }
        
        if(!duplicate)
        {
            noRepeat[counter] = array[i];
            counter++;
        }
    }
    
    return noRepeat;
}
    public static void Sort(int[] list, String[] strArr) {
        int n = list.length;
        if (n < 2) {
            return;
        }
        int middle = n / 2;
        int[] L = new int[middle];
        int[] R= new int[n - middle];
        String[] LStr = new String[middle];
        String[] RStr = new String[n - middle];
        for (int i = 0; i < middle; i++) {
            L[i] = list[i];
            LStr[i] = strArr[i];
        }
        for (int i = middle; i < n; i++) {
            R[i -middle] = list[i];
           RStr[i - middle] = strArr[i];
        }
        Sort(L, LStr);
        Sort(R, RStr);
        merge(list, L, R, strArr, LStr, RStr);
    }

    public static void merge(int[] list, int[] L, int[] R, String[] strArr, String[] LStr, String[] RStr) {
        int a= 0, b = 0, c = 0;
        while (a < L.length && b < R.length) {
            if (L[a] >= R[b]) {
                list[c] = L[a];
                strArr[c] = LStr[a];
                a++;
            } else {
                list[c] = R[b];
                strArr[c] = RStr[b];
                b++;
            }
            c++;
        }
        while (a < L.length) {
            list[c] = L[a];
            strArr[c] = LStr[a];
            a++;
            c++;
        }
        while (b < R.length) {
            list[c] = R[b];
            strArr[c] = RStr[b];
            b++;
            c++;
        }
    }


    
}
