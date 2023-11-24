package OOP.ec22697.MP;// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22742 extends Candidate {

  public String getName() {
      return "Mikhail";
  }

  public String getSlogan() {
      return "Gustavo Frank";
  }

  public Candidate vote(Candidate[] a) {

      if (a.length == 0){
          return new Candidate_ec22742();
        }
      
      for(Candidate b : a){
          if(b.getSlogan().equals("Gustavo Frank")){
             return b;
           }
         }
      
      return a[a.length-1];

  }

  public Candidate selectWinner(Candidate[] votes){


      if (votes.length == 0){
          return new Candidate_ec22742();
        }

      Candidate currentWinner = votes[0];

      int highest = 
          0;
      for (Candidate c : votes) {

          int count = 0;
          for (Candidate x : votes){
              if (x==c){
                  count++;
                }
              }

          if (count>highest) {
              highest = count;
              currentWinner = c;
          }

      }

      return currentWinner;

  }

// user inputs character 

    public static char inputChar() {

        Scanner scanner = new Scanner(System.in);

        String a = scanner.nextLine();

        char b = a.charAt(0);

        return b;

    }


    // user inputs string

    public static String inputString() {

        Scanner scanner = new Scanner(System.in);

        String c = scanner.nextLine();

        return c;

    }

    // user inputs int

    public static int inputInt() {

        Scanner scanner = new Scanner(System.in);

        int e = scanner.nextInt();

        return e;

    }




    // find a candidate in an array

    public static int findCandidate(Candidate[] candidate) {

        int index = 0; // username index

        System.out.println("Please enter a username.");

        String username = inputString();


        for (int i=0; i<candidate.length; i++){

            if (candidate[i].un.equals(username)) {
                index = i;
            }

        } // if not found, default to 0.



        return index;

    }

    // Add candidate to array

    public static Candidate[] addCandidate(Candidate[] candidate, Candidate[] myCandidates, int index) {

        if (index == -1) index = findCandidate(candidate); // Find index of the username in the array

        // Into next null index

        for (int i=0; i<candidate.length; i++){

            if (myCandidates[i] == null) {

                myCandidates[i] = candidate[index];

                break;

            }

        }

        return myCandidates;

    }


    // output array

    public static void outputArray(Candidate[] candidate) {
        for (int i=0; i<candidate.length; i++){
            if (candidate[i] != null) {

                System.out.println((i+1) + ". " + candidate[i].un);

            }

        }

    }

    // Run the election

    public static void runElection(Candidate[] candidate, Candidate[] myCandidates) {

        System.out.println("Who should count the votes?");

        int index = findCandidate(candidate); // Find index of username in array c

        
        System.out.println("How many times should we run the election?");

        int n = inputInt();




        for (int i=0; i<n; i++){

            Candidate elected = candidate[index].selectWinner(myCandidates);

            System.out.println("The most common winner is: " + elected.un);

        }

    }




    // main

    public static void main(String[] args) {

        Candidate[] candidate = A3.getCandidateArray();

        Candidate[] myCandidates = new Candidate[546];


        char choice = 'b'; 

        Random rand;

        int index;



        while (choice != 'c') {

            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

            choice = inputChar();




            // option type a

            if (choice == 'a') {

                myCandidates = addCandidate(candidate, myCandidates, -1); // -1 to find index of username in array c

                outputArray(myCandidates);

            }



            // Add random candidate

            else if (choice == 'a') {

                // generate random number

                rand = new Random();

                index = rand.nextInt(candidate.length);



                myCandidates = addCandidate(candidate, myCandidates, index); // index of random candidate in array c

            }

        }

        runElection(candidate, myCandidates);

    } 

}// end class Candidate_ec22742

