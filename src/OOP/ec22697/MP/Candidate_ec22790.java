package OOP.ec22697.MP;// File Candidate_ec22790.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;
class Candidate_ec22790 extends Candidate {
    
    public String getName() {
        return "Ben Dover";
    }
    
    public String getSlogan() {
        return "Double it";
    }
    
    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22790();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hello World"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Mr WriteIn"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
                
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22790();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
       public static int inputInt(String message)
    {
        String ans_string;
        int ans_int;
        
        Scanner keyboard = new Scanner(System.in); 
        
        System.out.println(message);
        
        ans_string = keyboard.nextLine();
        
        ans_int = Integer.parseInt(ans_string);
        while (ans_int <0)
        {
            System.out.println("Please enter a valid number."); 
            System.out.println(message);
            ans_string = keyboard.nextLine();
            ans_int = Integer.parseInt(ans_string);
        }
        
        return ans_int;
    }


    public static void print(String message)
    {
     System.out.println(message);
     return;
    }


    public static String inputString(String message)
    {
        String input;
        Scanner scanner = new Scanner(System.in); 

        print(message);
        input = scanner.nextLine();

        return input;
    }

    public static void main(String[] args)
    {
        Candidate[] allCandidates = A3.getCandidateArray();
        int listLength = 1;
        Candidate[] candidates = new Candidate[listLength];

        String response = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        
        if(response.equals("a"))
        {        
            response = inputString("Please enter a username.");

            Candidate chosen = A3.getByUsername(response, allCandidates);

            if(chosen != null)
            {
                print("This candidate is not included.");
            }
            else
            {
                candidates[0] = chosen;
                listLength = listLength + 1;
                Candidate[] temp = new Candidate[listLength];
                for(int i = 0; i<listLength-1; i++)
                {
                    temp[i+1] = candidates[i];
                }
                candidates = temp;
            }
        }
        else if(response.equals("b"))
        {
            int random = (new Random()).nextInt(allCandidates.length);
            candidates[0] = allCandidates[random];
            listLength = listLength + 1;
            Candidate[] temp = new Candidate[listLength];
            for(int i = 0; i<listLength-1; i++)
            {
                temp[i+1] = candidates[i];
            }
            candidates = temp;
        } 
        else if(response.equals("c"))
        {
            response = inputString("Who should count the votes?");
            
            int loop = inputInt("How many times shall we run the election?");
            Candidate[] winnerList = new Candidate[loop];
            
            for(int a = 0; a<loop; a++)
            {
                Candidate[] votes = new Candidate[allCandidates.length];
                for(int i = 0; i<allCandidates.length; i++)
                {
                    votes[i] = (allCandidates[i]).vote(candidates);
                }
                
                winnerList[a] = (A3.getByUsername(response, allCandidates)).selectWinner(votes);
            }
            frequentWinner(winnerList);
            
        }

        return;
    }
    
    public static void frequentWinner(Candidate[] winners)
    {
        String[] winnerNames = new String[winners.length];
        int[] frequency = new int[winners.length];
        for(int i = 0; i<winners.length; i++)
        {
            winnerNames[i] = (winners[i]).getName();
            frequency[i] = 0;
        }
    
        String[] noDuplicates = removeDuplicates(winnerNames);
    
    
        for(int i = 0; i<winners.length; i++)
        {
            for(int j = 0; j<winners.length; j++)
            {
                if(noDuplicates != null && noDuplicates[i].equals(winners[j]))
                {
                    frequency[i]++;
                }
            }
        }
    
        mergeSort(frequency, noDuplicates);
    
        print("The 3 most frequent winners in descending orders-");
        for(int i = 1; i<4; i++)
        {
            print(i+". " + noDuplicates[i-1] + " won " + frequency[i-1] + " times.");
        }
    
        return;
    
    }

    public static String[] removeDuplicates(String[] array)
    {
        String[] noDuplicates = new String[array.length];
        int counter = 0;

        for(int i = 0; i<array.length; i++)
        {
            boolean repeat = false;
            for(int j = 0; j<i; j++)
            {
                if(array[i].equals(noDuplicates[j]))
                {
                    repeat = true;
                }
            }

            if(!repeat)
            {
                noDuplicates[counter] = array[i];
                counter++;
            }
        }

        return noDuplicates;
    }
    public static void mergeSort(int[] arr, String[] strArr) {
        int n = arr.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        String[] leftStr = new String[mid];
        String[] rightStr = new String[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
            leftStr[i] = strArr[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = arr[i];
            rightStr[i - mid] = strArr[i];
        }
        mergeSort(left, leftStr);
        mergeSort(right, rightStr);
        merge(arr, left, right, strArr, leftStr, rightStr);
    }

    public static void merge(int[] arr, int[] left, int[] right, String[] strArr, String[] leftStr, String[] rightStr) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] >= right[j]) {
                arr[k] = left[i];
                strArr[k] = leftStr[i];
                i++;
            } else {
                arr[k] = right[j];
                strArr[k] = rightStr[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            arr[k] = left[i];
            strArr[k] = leftStr[i];
            i++;
            k++;
        }
        while (j < right.length) {
            arr[k] = right[j];
            strArr[k] = rightStr[j];
            j++;
            k++;
        }
    }

  
    
}
