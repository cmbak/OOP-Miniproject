package OOP.ec22697.MP;

import java.util.*;

class Candidate_ec22815 extends Candidate 
{
    public static void main(String args[])
    {
        OptionB();
        return;
	}
	
	public static void OptionB()
	{
        Candidate[] allCandidates = A3.getCandidateArray();
        int length = 1;
        Candidate[] specificCandidates = new Candidate[length];
        
		System.out.println("Candidates are ");
		listCandidates(specificCandidates);
		String choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
		
		if (choice.equals("a"))
		{
			String username = inputString("Please enter username.");
            Candidate newCan = A3.getByUsername(username, allCandidates);
            updateList(specificCandidates, length, newCan);
		}
		else if (choice.equals("b"))
		{
            int random = (new Random()).nextInt(allCandidates.length);
            updateList(specificCandidates, length, allCandidates[random]);
		}
		else if (choice.equals("c"))
		{
			String answer = inputString("Who will be counting the votes?");
            int loop = inputNumber("How many times shall the election will be run?");
            Candidate[] listWinners = new Candidate[loop];
            
            for (int i = 0; i < loop; i++)
            {
                Candidate[] votes = new Candidate[allCandidates.length];
                for(int y = 0; y < allCandidates.length; y++)
                {
                    votes[y] = (allCandidates[y]).vote(specificCandidates);
                }
                    
                listWinners[i] = (A3.getByUsername(answer, allCandidates)).selectWinner(votes);
            }
            freqWinners(listWinners);
		}
	}
	
    
	 public String getName() {
	     return "Quoc Dat Nguyen";
	 }
	 
	 public String getSlogan() {
	     return "More pancakes!";
	 }
    
    public Candidate getRandomCandidate(Candidate[] all) 
    {
        Random r = new Random();
        int c = r.nextInt(all.length);
        return all[c];
    }
    
    public static void updateList(Candidate[] specific, int length, Candidate newCan)
    {
        specific[0] = newCan;
        length += 1;
        Candidate[] temporary = new Candidate[length];
        for (int i = 0;i < length-1; i++)
        {
            temporary[i+1] = specific[i]; 
        }
        specific = temporary;
    }
	 
	 public Candidate vote(Candidate[] candidates) {
	     
	     if (candidates.length == 0)
	     {
	         return new Candidate_ec22815();
	     }
	     
	     for (Candidate c : candidates)
	     {
	         if (c.getSlogan().equals("More pancakes!"))
	         return c;
	     }
	     
	     Random r = new Random();
	     int c = r.nextInt(candidates.length);
	     return candidates[c]; 
	 }
	 
	 public static void listCandidates(Candidate[] candidates)
	 {
		 System.out.println("Candidates are");
		 for (int i = 0; i < candidates.length; i++)
		 {
			 int number = i + 1;
			 System.out.println(number + ". " + candidates[i].getName());
		 }
	 }
    
    public static void freqWinners(Candidate[] winners)
    {
        String[] winner_Names = new String[winners.length];
        int[] frequencyTimes = new int[winners.length];
        for(int i = 0; i < winners.length; i++)
        {
            winner_Names[i] = (winners[i]).getName();
            frequencyTimes[i] = 0;
        }

        String[] no_Duplicates = removeDuplicates(winner_Names);

        for(int i = 0; i < winners.length; i++)
        {
            for(int j = 0; j < winners.length; j++)
            {
                if(no_Duplicates != null && no_Duplicates[i].equals(winners[j]))
                {
                    frequencyTimes[i]++;
                }
            }
        }

        mergeSorter(frequencyTimes, no_Duplicates);

        System.out.println("In the descending order, three most frequent winners-");
        for(int i = 1; i < 4; i++)
        {
            System.out.println(i+". " + no_Duplicates[i-1] + " won " + frequencyTimes[i-1] + " times.");
        }

        return;
    }

    public static String[] removeDuplicates(String[] array)
    {
        String[] no_Duplicates = new String[array.length];
        int counter = 0;

        for(int i = 0; i < array.length; i++)
        {
            boolean repeat = false;
            for(int j = 0; j<i; j++)
            {
                if(array[i].equals(no_Duplicates[j]))
                {
                    repeat = true;
                }
            }

            if(!repeat)
            {
                no_Duplicates[counter] = array[i];
                counter++;
            }
        }

        return no_Duplicates;
    }
                                    
    public static void mergeSorter(int[] array, String[] strArray) {
        int n = array.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        String[] leftStr = new String[mid];
        String[] rightStr = new String[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
            leftStr[i] = strArray[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = array[i];
            rightStr[i - mid] = strArray[i];
        }
        mergeSorter(left, leftStr);
        mergeSorter(right, rightStr);
        merge(array, left, right, strArray, leftStr, rightStr);
    }

    public static void merge(int[] array, int[] left, int[] right, String[] strArray, String[] leftStr, String[] rightStr) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] >= right[j]) {
                array[k] = left[i];
                strArray[k] = leftStr[i];
                i++;
            } else {
                array[k] = right[j];
                strArray[k] = rightStr[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            array[k] = left[i];
            strArray[k] = leftStr[i];
            i++;
            k++;
        }
        while (j < right.length) {
            array[k] = right[j];
            strArray[k] = rightStr[j];
            j++;
            k++;
        }
    }
	 
	 public Candidate selectWinner(Candidate[] votes) {
	     
	     if (votes.length == 0) 
	         return new Candidate_ec22815();
	     
	     Candidate currentWinner = votes[0];
	     
	     int highestCount = 0;
	     for (Candidate c : votes) {
	         
	         int count = 0;
	         for (Candidate x : votes)
	             if (x == c)
	                 count++;
	         if (count >= highestCount) {
	             highestCount = count;
	             currentWinner = c;
	         }
	     }
	     
	     return currentWinner;
	 }
	 
	 public static String inputString(String message)
	 	{
	 		String line = "";
	 		Scanner scanner = new Scanner(System.in);
	 		boolean status = true;
	 		while(status == true)
	 		{
	 			try {
	 				System.out.println(message);
	 		 		line = scanner.nextLine();
	 		 		status = false;	
	 			}catch(Exception e) {
	 				System.out.println("Invalid input. Please try again.");
	 				System.out.println("");
	 			}
	 		}
	 		return line;
	 	}
    
    public static int inputNumber(String message)
 	{
 		String strNumber;
 		int number = 0;
 		Scanner scanner = new Scanner(System.in);
 		boolean status = true;
 		while(status == true)
 		{
 			try {
 				System.out.println(message);
 		 		strNumber = scanner.nextLine();
 		 		number = Integer.parseInt(strNumber);
 		 		status = false;
 			}catch(Exception e) {
 				System.out.println("Invalid input. Please try again.");
 				System.out.println("");
 			}
 		}
 		return number;
 	}
 
}
