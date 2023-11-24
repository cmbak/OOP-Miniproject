package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Candidate_ec22411 extends Candidate {
    public static void main(String[] s){
        Candidate[] allCandidates = A3.getCandidateArray();
        runSameElec(allCandidates);
        return;
    }
    
    public static void pr(String msg){
        System.out.println(msg);
        return;
    }
    
    public static String inputString(String message)
    {
    String keyboard_input;
    Scanner keyboard = new Scanner(System.in);
    
    System.out.println(message);
    keyboard_input = keyboard.nextLine();
    
    return keyboard_input;
    }
    
    public static void runSameElec(Candidate[] allCandidates){
        ArrayList <Candidate> chosenCand = new ArrayList<Candidate>();
        pr("# Running Repeated Elections #");
        prArrayList(chosenCand);
        String ans = "";
        String menumsg = ("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        ans = inputString(menumsg);
        Random random = new Random();
        while (!ans.equals("c")){
            int size = 0;
            if (ans.equals("a"))
            {
                String name = inputString("Please enter a username");
                Candidate add = A3.getByUsername(name,allCandidates);
                if (add!=null){
                    chosenCand.add(add);
                    size++;
                }
                else{
                    pr("User not found.");
                }
                prArrayList(chosenCand);
            }
            else if (ans.equals("b")){
                int num = random.nextInt(allCandidates.length);
                chosenCand.add(allCandidates[num]);
                size++;
                prArrayList(chosenCand);
            }
            ans = inputString(menumsg);
        }
        if (ans.equals("c")){
            String name = inputString("Who should count the votes?");
            Candidate personCounting = A3.getByUsername(name, allCandidates);
            
            while (personCounting == null){
                pr("User not found.");
                name = inputString("Who should count the votes?");
                personCounting = A3.getByUsername(name, allCandidates);
            }
            int times = Integer.parseInt(inputString("How many times shall we run the election?"));

            //running the election in for loop
            int[] numVotes = new int[chosenCand.size()];
            Candidate[] winners = new Candidate[chosenCand.size()];
            Candidate currentwinner;
            Candidate[] listOfWinners = new Candidate[times];
            Candidate mostCommonWinner;
            int winnerPosition = 0;
            Candidate[] chosenCandArray = chosenCand.toArray(new Candidate[chosenCand.size()]);
            
            for (int e = 0; e < times; e++) //loop to repeat specified num of times
            {
                Candidate[] votes = new Candidate[allCandidates.length];
                int position = 0;
                for (Candidate voter : allCandidates){
                    votes[position] = voter.vote(chosenCandArray);
                    position++;
                }
                currentwinner = personCounting.selectWinner(votes);
                winners[winnerPosition] = currentwinner;  //listOfWinners.add(currentwinner);
                winnerPosition++;
            }
            Candidate mostCommon = oneMostFreq(winners);
            pr("Most common winner is " + mostCommon.getName() + ".");
        }
        return;
    }
    public static int numOfTimes(Candidate[] array, Candidate target){
        int count = 0;
        for(int i = 0 ; i < array.length; i++){
            if (array[i]==target){
                count++;
            }
        }
        return count;
    }
    public static Candidate oneMostFreq(Candidate[] winners){
        int highestFreq = 1;
        Candidate mostCommonWinner = winners[0]; // default to first on the list
        for (int i =0; i<winners.length; i++){
            if(numOfTimes(winners, winners[i])>highestFreq)
                mostCommonWinner = winners[i];
                highestFreq = numOfTimes(winners, winners[i]);
        }
        return mostCommonWinner;
    }
    public static void prArrayList(ArrayList<Candidate> list){
        pr("Candidates are: ");
        if (list.size()==0){
            pr("None");
        }
        else{
            int i=1;
            String nm = "";
            Candidate current;
            for(Candidate c : list)
            {
                current = list.get(i-1);
                nm = current.getName();
                pr(i + ". " + nm);
                i++;
            }
        }
        return;
    }
    public String getName() {
        return "Natalie";
    }
    public String getSlogan() {
        return "Read Ajin";
    }
    public Candidate vote(Candidate[] candidates) {
        //if array empty return friend
        if (candidates.length == 0) 
            return new Candidate_ec22467();
        //look for slogan
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Read Ajin."))
                return c;
        // look for friend
        for (Candidate c : candidates)
            if (c.getName().equals("Link"))
                return c;
        // default to voting for myself :D
        return new Candidate_ec22411();
    }
    public Candidate selectWinner(Candidate[] votes) {
        // if empty array return friend
        Candidate winner = new Candidate_ec22411();
        if (votes.length == 0) 
            return winner;
        // default first position
        winner = votes[0];
        
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes){
                if (x == c)
                    count++;
            }
            if (count > highestCount) {
                highestCount = count;
                winner = c;
            }
        }
        return winner;
    }
}
