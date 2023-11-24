package OOP.ec22697.MP;// File Candidate_ec221185.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec221185 extends Candidate {

    public static void main (String [] args){
        // object to get array of candidates
        // array of all candidates
        String[] elec = new String[0];    // array of all election candidates

        System.out.println("Running Repeated Elections");
        System.out.println("Candidates are:");
        // if statements for how many election candidates
        System.out.println("none");
        while(true) {
            String answer = inputstring("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

            if(answer.equals("a")){   // if statement for if you want to add a candidate
                elec = addCandidate(elec);
            }
            else if (answer.equals("b")){   // if statement if you want to add a random candidate
                elec = addRandomCandidate(elec);
            }
            else {
                RunElection(elec);   // method to run the election
                break;
            }
        }


    }
    public static String[] addCandidate (String[] a){   // passes an array argument
        String[] AddElec = new String[a.length + 1];   // add array is equal to the passed array size + 1
        // for loop to make all passed array elements copy into the add element
        System.arraycopy(a, 0, AddElec, 0, a.length);
        String input = inputstring("Please enter a username.");
        AddElec[AddElec.length-1] = input;  // adds username to empty space made when the length was added and the index of this space will be length - 1
        return AddElec;  // return array to passed argument
    }
    public static String[] addRandomCandidate (String[] a ) {  // passes array argument
        String[] AddElec = new String[a.length + 1]; // Add elec array is initialized with size of passed array
        // AddElec is given all the values of passed array
        System.arraycopy(a, 0, AddElec, 0, a.length);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  // gets random
        StringBuilder randomString = new StringBuilder();
        int rounds = 6;
        Random random = new Random();
        char[] text = new char[rounds];
        for(int i = 0; i<rounds; i++){  // cho
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        for (char c : text) {
            randomString.append(c);
        }
        AddElec[AddElec.length-1] = randomString.toString();
        return AddElec;
    }
    public static void RunElection (String[] a) {

        String winner = a[0];
        System.out.println("Most common winner is " + winner + ".");
        System.out.println("There are no other winners");

    }

    public static String inputstring(String message){
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        return input.nextLine();


    }
    public static int inputint(String message) {
        return Integer.parseInt(inputstring(message));

    }

    public String getName() {
        return "Bruce Wayne";


        }

    @Override
    public Candidate vote(Candidate[] candidates) {
        return new Candidate_ec221185();
    }

    @Override
    public Candidate selectWinner(Candidate[] votes) {
        return null;
    }

    @Override
    public String getSlogan() {
        return "Make Gotham safe again";
    }
}
