package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22786 extends Candidate {

    public String getName() {
        return "Robin";
    }

    public String getSlogan() {
        return "More trees!";
    }
    
    private static Scanner sc() {return new Scanner(System.in);}

    public static void outputNames(int index, Candidate[] CandidateNames){
        System.out.println("Candidates are:");
        if (index == 0){
            System.out.println("None");
        }else{
            for(int i = 0; i< index;i++) {
                System.out.println((i+1)+". "+CandidateNames[i]);
            }
        }
    }
    
    
    public static void main(String[] args) {
        //Candidate array list in A3 file.
        Candidate[] A3Candidate = A3.getCandidateArray();
        //Candidate array list for this class.
        Candidate[] CandidateNames = new Candidate[A3Candidate.length];
        //
        String names = "";
        int index = 0;

        System.out.println("= Running Repeated Elections =");
        outputNames(index,CandidateNames);



        boolean run = true;
        if (index < A3Candidate.length) {
            do {
                
                System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                char option = sc().nextLine().charAt(0);
                outputNames(index, CandidateNames);
                if (option == 'a') {
                    System.out.println("please enter a Username:");
                    addCandidate(A3Candidate, CandidateNames, index);
                    index++;
                } else if (option == 'b') {
                    randomCandidate(A3Candidate, CandidateNames, index);
                    index++;
                } else if (option == 'c') {
                    char exit = election(A3Candidate, CandidateNames, index);
                    if(exit == 'y'){
                        run = false;
                    }
                }


            } while (run);
        }
    }



    public static Candidate[] addCandidate(Candidate[] A3Candidate, Candidate[] CandidateNames, int index){
        String CandidateName = sc().nextLine();
        Candidate CurrentCandidate = A3.getByUsername(CandidateName,A3Candidate);
        if(CurrentCandidate != null){
            CandidateNames[index] = CurrentCandidate;
        }else{
            System.out.println("please enter a correct username");
        }
        return CandidateNames;
    }
    public static Candidate[] randomCandidate(Candidate[] A3Candidate, Candidate[] CandidateNames, int index){
        Random rand = new Random();
        int RandomNum = rand.nextInt(A3Candidate.length);
        Candidate A3Name = A3Candidate[RandomNum];
        CandidateNames[index] = A3Name;
        System.out.println(A3Name+" has been added to your current Candidate list.");
        return CandidateNames;

    }
    public static char election(Candidate[] A3Candidate, Candidate[] CandidateNames, int index){
        System.out.println("Who should count the votes?");
        String username = sc().nextLine();
        Candidate candidate = A3.getByUsername(username, A3Candidate);
        System.out.println("How many times shall we run the election?");
        int times = sc().nextInt();
        //cloning a new array and saved candidates into it without nulls.
        Candidate[] ArrayForThis = new Candidate[index];
        for(int i = 0; i < ArrayForThis.length;i++){
            ArrayForThis[i] = CandidateNames[i];
        }
        Candidate[] arrayForWinner = new Candidate[times];
        for(int i = 0; i < times; i++){
            for(int j = 0; j< index; j++) {
                arrayForWinner[j] = ArrayForThis[j].vote(ArrayForThis);
            }
        }

        Candidate winner = candidate.selectWinner(arrayForWinner);

        System.out.println("Most Common winner is "+winner);

        System.out.println("Would you like to exit? y/n?");
        char exit = sc().nextLine().charAt(0);

        return exit;




    }


    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec22786();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Kim"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ec22786();

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

}
