package OOP.ec22697.MP;// File Candidate_ec22837.java
//
// Machine generated stub for Assignment 2
     

import java.util.*;
class Candidate_ec22837 extends Candidate {
    public static void main(String[] args){
        Candidate[] ca = getCandidateArray();
        Candidate[] candidates = new Candidate[ca.length];
        boolean choose = false;
        int remaining = ca.length;
        int num_added = 0;
        while(!choose){
            println("candidates are ");
            if(num_added > 0){
                for(int i = 0; i < num_added; i++){
                    println((i+1) + ". " + candidates[i].getName());
                }
            }
            else{
                println("none");
            }

            String option = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election ?");
            if(option.equals("a")){
                String username = inputString("enter the candidate username");
                for (int i = 0; i < ca.length; i++){
                    if(username.equals(ca[i].un)){
                        candidates[num_added] = ca[i];
                    }
                }
                if(!candidates[num_added].un.equals(username)){
                    println("that username is not in the list of candidates");
                    num_added--;
                }
                num_added++;
            }
            else if(option.equals("b")){
                Random r = new Random();
                int random = r.nextInt(remaining);
                if(ca[random] != null && remaining > 0){
                    candidates[num_added] = ca[random];
                    ca = shiftArray(ca, random);
                    remaining--;
                    num_added++;
                }
            }
            else if(option.equals("c")) {
                String voteCounter = inputString("who should count the votes? (enter username)");
                int runs = Integer.parseInt(inputString("how many times shall we run the election?"));
                Candidate counter = candidates[0];
                String[] winners = new String[runs];
                for (int i = 0; i < num_added; i++) {
                    if (voteCounter.equals(candidates[i].un)) {
                        counter = candidates[i];
                    }
                }
                for (int j = 0; j < runs; j++) {
                    winners[j] = counter.selectWinner(candidates).getName();
                }
                mostCommon(winners);
                choose = true;
            }
        }

    }
    public static void mostCommon(String[] array){
        int highest = 0;
        String winner = array[0];
        for(int i = 0; i < array.length; i++){
            int count = 0;
            for(int j = 0; j < array.length; j++){
                if(array[j].equals(array[i])){
                    count++;
                }
            }
            if(count>highest){
                highest = count;
                winner = array[i];
            }
        }
        println("Most common winner is " + winner);
    }
    public static <T> T[] shiftArray(T[] array, int x){
        for (int i = 0; i < array.length; i++){
            for (int k = i + 1; k < array.length; k++){
                if (array[x] == array[i]){
                    T temp = array[i];
                    array[i] = array[k];
                    array[k] = temp;
                    x++;
                }
            }
        }
        return array;
    }
    public static void println(String message){
        System.out.println(message);
        return;
    }
    public static void print(String message){
        System.out.print(message);
    }
    public static String inputString(String message){
        Scanner scanner = new Scanner(System.in);
        print(message);
        String ans = scanner.nextLine();
        return ans;
    }

    public String getName() {
        return "Naveed";
    }

    public String getSlogan() {
        return "vote for me";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec22626();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("vote for me"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("HH"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ec22626();

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
    public static Candidate[] getCandidateArray() { // Adapted from A3
        //This is just a small sample that was available to me
        Candidate[] ca = new Candidate[3];
        ca[0] = new Candidate_ec22837(); ca[0].un = "ec22837";
        ca[1] = new Candidate_ec22532(); ca[3].un = "ec22532";
        ca[2] = new Candidate_ec22626(); ca[4].un = "ec22626";
        return ca;
    }

}

