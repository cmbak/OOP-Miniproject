package OOP.ec22697.MP;// File Candidate_ec221085.java
//

import java.util.Random;
import java.util.Scanner; 
import java.util.ArrayList;
class Candidate_ec221085 extends Candidate {

    public String getName() {
        return "Timo";
    }
    
    public String getSlogan() {
        return "I got a 17";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) {
            return new Candidate_ec221088();
        } else {
            Random random = new Random();
            int index = random.nextInt(candidates.length);
            return candidates[index];
        }
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec221088();
        } else {
            Candidate currentWinner = votes[0];
            int highestCount = 1;
            for (int i = 0; i < votes.length; i++) {
                int count = 1;
                for (int j = i + 1; j < votes.length; j++) {
                    if (votes[i] == votes[j]) {
                        count++;
                    }
                }
                if (count > highestCount) {
                    highestCount = count;
                    currentWinner = votes[i];
                }
            }
            return currentWinner;
        }
    }


    public static void main(String[] args)
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
            if (addcandidates[0] == null) 
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
            option = inputString("Would you like to a) Add candidate  b) Run election  c) Add a random candidate  d) Exit");
            if (option.equals("a")) 
            {
                cusername = inputString("Please enter the desired username.");
                Candidate usercheck = A3.getByUsername(cusername, allcandidates);
                if (usercheck != null) //check for the username to ensure it is not null
                {
                    addcandidates[count] = A3.getByUsername(cusername, allcandidates);
                    count = count + 1;
                }
                else
                {
                    print("Username notfound"); 
                }
            }
            else if (option.equals("c")) 
            {
                rcandidate = randomCandidate(allcandidates); 
                addcandidates[count] = allcandidates[rcandidate];
                count = count + 1;
            }
            else if (option.equals("b")) 
            {
                electionrun(allcandidates, addcandidates);
            }
            else if (option.equals("d")) 
            {
                break; 
            }
            else
            {
                print("Try again!");
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
            for (int i = 0; i < count; i ++) 
            {
                try{
                print((i+1)+". "+addcandidates[i].getName());
                }
                catch (Exception e){}
            }
        }
    }

    public static int randomCandidate(Candidate[] allcandidates)
    {
        Random r = new Random();
        int max = allcandidates.length-1; 
        int randomindex = r.nextInt(max);
        return randomindex; 
    }

    public static void electionrun(Candidate[] allcandidates, Candidate[] addcandidates) 
    {
        int repetitions = 0; 
        int currentl = 0; 
        boolean run = false; 
        String votedecider = "";
        Candidate[] votes = new Candidate[allcandidates.length];

        for (int i = 0; i < allcandidates.length; i++) 
        {
            votedecider = inputString("Who's counting the votes? ");
            if (A3.getByUsername(votedecider, allcandidates) != null)
            {
                break;
            }
            else 
            {
                print("Try again!");
            }
        }
        Candidate votecounter = A3.getByUsername(votedecider, allcandidates);
        repetitions = inputInteger("How many times do you want the election to take place? ");
        for (Candidate c : addcandidates) 
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
            for (Citizen v: allcandidates) 
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
        commonWinner(winners); 
    }

    public static void commonWinner(Candidate[] repetitionwinners)
    {
        ArrayList<Candidate> allwins= new ArrayList<>();
        //Inspired by ec22479 with ArrayList
        Candidate common = repetitionwinners[0];
        int highest = 1;
        int current;
        for (int i = 0; i < repetitionwinners.length; i++)
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
            else if (current == highest && !(allwins.contains(repetitionwinners[i]))) 
            {
                allwins.add(repetitionwinners[i]);
            }
        }

        print("The winner(s) are: "); 

        for (Candidate c : allwins) //outputs the winners 
        {
            if (c != null)
            {
                print(c.getName());
            }
        }
    }

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
    
    
}
