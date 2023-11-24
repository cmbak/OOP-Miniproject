package OOP.ec22697.MP;

import java.util.*;

class Candidate_ec22480 extends Candidate 
{
    public static void main(String[] args) 
    {
        Candidate[] candidates = new Candidate[]{};
        Candidate[] allCandidates = A3.getCandidateArray();
        print("= Running Repeated Elections =");
        menu(candidates, allCandidates);
    }

    public static void menu(Candidate[] candidates, Candidate[] allCandidates) 
    {
        String choice = "";
        while (!(choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("b") || choice.equalsIgnoreCase("c")))
        {
            print("Candidates are :");
            if (candidates.length == 0) 
            {
                print("NONE");
            }
            else 
            {
                for (int i = 0; i < candidates.length; i++) 
                {
                    print((i + 1) + ") " + candidates[i].getName());
                }
            }
            choice = input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if (choice.equalsIgnoreCase("a")) 
            {
                String name = input("Please enter a username.");
                Candidate[] newCands = addCandidate(allCandidates, candidates, name);
                menu(newCands, allCandidates);
            }
            else if (choice.equalsIgnoreCase("b")) 
            {
                Candidate[] newCands = getRandomCandidate(allCandidates, candidates);
                menu(newCands, allCandidates);
            }
            else if (choice.equalsIgnoreCase("c")) 
            {
                runElection(allCandidates, candidates);
                menu(candidates, allCandidates);
            }
        }
    }

    public static Candidate[] addCandidate(Candidate[] allCand, Candidate[] currentArr, String name) 
    {
        boolean check = false;
        Candidate newCandidate = null;

        if (!(A3.getByUsername(name, allCand) == null)) 
        {
            newCandidate = A3.getByUsername(name, allCand);
            check = true;
        }
        
        if (check)
        {
            Candidate[] newArr = new Candidate[currentArr.length + 1];
            for (int i = 0; i < currentArr.length; i++) 
            {
                newArr[i] = currentArr[i];
            }
            newArr[newArr.length - 1] = newCandidate;
            print(newCandidate.getName() + " was added.");
            return newArr;
        }
        else 
        {
            print("Candidate not found");
            return currentArr;
        }
    }

    public static Candidate[] getRandomCandidate(Candidate[] allCand, Candidate[] currentArr) 
    {
        Random random = new Random();
        int upperBound = allCand.length;
        int randomNum = random.nextInt(upperBound);
        return addCandidate(allCand, currentArr, allCand[randomNum].un);
    }

    public static void runElection(Candidate[] allCands, Candidate[] candidates) 
    {
        boolean userFound = false;
        Candidate counter = null;
        int numVotes = 0;
        while (!userFound) 
        {
            String counterUn = input("Who should count the votes?");
            counter = A3.getByUsername(counterUn, allCands); 
            if (counter == null) 
            {
                print("User not found");
            }
            else
            {
                userFound = true;
            }
        }

        boolean intCheck = false;
        while (!intCheck) 
        {
            String str = input("How many times shall we run the election?");
            if (isInt(str)) 
            {
                numVotes = Integer.parseInt(str);
                intCheck = true;
            }
        }
        Candidate[] votes = new Candidate[numVotes];
        for (int i = 0; i < numVotes; i++) 
        {
            votes[i] = counter.vote(candidates);
        }
        print("Most common winner is " + counter.selectWinner(votes).getName());
    }

    public String getName() 
    {
        return "Zubair";
    }
    
    public String getSlogan() 
    {
        return "More trees!";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        
        if (candidates.length == 0) 
            return new Candidate_ec22480();
                
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees!"))
                return c;
        
        for (Candidate c : candidates)
            if (c.getName().equals("Kim"))
                return c;
        
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
        {
            return new Candidate_ec22480();
        }
        
        Candidate r = votes[0]; 
        int frequency = 0;
        for (int i = 0; i < votes.length; i++) 
        {
            int count = 0;
            for (int j = 0; j < votes.length; j++) 
            {
                if (votes[i] == votes[j]) 
                {
                    count++;
                }
            }
        
            if (count > frequency) 
            {
                frequency = count;
                r = votes[i];
            }
        }
        return r;  
    }   

    public static void print(String str) 
    {
        System.out.println(str);
    }

    public static String input(String msg) 
    {
        print(msg);
        return new Scanner(System.in).nextLine();
    }

    public static boolean isInt(String str) 
    {
        final String[] numbers = new String[] {"0","1","2","3","4","5","6","7","8","9"};
		int tempNumInt = 0;
		for (int i = 0; i < str.length(); i++) 
		{
			if (Arrays.asList(numbers).contains(String.valueOf(str.charAt(i)))) 
			{
				tempNumInt++;
			}
		}
		
		if (tempNumInt == str.length()) 
		{
			return true;
		}
		else 
		{
			return false;
		}
    }
}
