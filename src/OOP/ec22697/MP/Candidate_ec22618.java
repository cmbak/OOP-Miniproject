package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

public class Candidate_ec22618 extends Candidate {

    public String getName() {
        return "Shabanaba";
    }

    public String getSlogan() {
        return "Nagachaka Chaignishu";
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) {
            return new Candidate_ec22618();
        }
        else{
            Random rand = new Random();

            int ran = rand.nextInt(candidates.length);
            return candidates[ran];
        }

    }

    public Candidate selectWinner(Candidate[] votesCast) {

        if (votesCast.length == 0)
        {
            return new Candidate_ec22618();
        }

        else
        {
            Random r = new Random();
            int c = r.nextInt(votesCast.length);
            return votesCast[c];
        }
    }


    public static void main(String [] a ){
        Candidate[] AllCandi = A3.getCandidateArray(); //use to get the array with all the candidate
        int i = 0; //used to store the index of the Candidate type array
        Candidate[] array = new Candidate[AllCandi.length]; //creates an Candidate type array


        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        String InputChoice = Input().toLowerCase();

        while (!InputChoice.equals("c") || i==0){
            if (InputChoice.equals("a")){
                System.out.println("Please enter a username.");
                String name = Input();
                Candidate placeHolder = A3.getByUsername(name, AllCandi);
                array[i] = placeHolder; //stores the matching username into the array
                i++;
                System.out.println("Candidates are");

                for (int integer=0;integer<i; integer++){
                    System.out.println(integer+1 + "." + array[integer].getName()); //prints out the name that the specific user chosen
                }
            }

            else if (InputChoice.equals("b")){
                Random rand = new Random();
                int n = rand.nextInt(AllCandi.length);
                array[i] = AllCandi[n];

                i++;
                System.out.println("Candidates are");

                for (int integer=0;integer<i; integer++){
                    System.out.println(integer+1 + "." + array[integer].getName()); //prints out the name that the specific user chosen
                }
            }

            else if (InputChoice.equals("c")&&(i==0)){
                    System.out.println("You got to add someone into the list");
            }
            else {
                System.out.println("Invalid choice,try again.");
            }

            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            // InputChoice = Input().toLowerCase();
            InputChoice = Input().toLowerCase();
        }

        boolean Temp_x = false;

        Candidate The_counter = new Candidate_ec22618();

        while (Temp_x==false){          //used to check if the person chosen is in the list.
            System.out.println("Who should count the votes?");
            String A_Person = Input();

            The_counter = A3.getByUsername(A_Person, AllCandi); //checks if user(A_Person) is in the array(AllCandi)

            if (The_counter == null){
                System.out.println("Not on the list,try again.");
            }
            else {
                Temp_x = true;
            }


        }

        System.out.println("How many time would you like to run.");

        int num_of_run = Integer.parseInt(Input());     //checks if number of run is a valid number
        while (num_of_run<=0){
            System.out.println("Value inputted is not a valid value, try again.");
            num_of_run = Integer.parseInt(Input());
        }


        String [] won_Candi = new String[num_of_run];

        Random rand = new Random();
        System.out.println("num: "+i);
        int n = rand.nextInt(i);
        for (int values = 0; values < num_of_run; values++) {
            won_Candi[values] = array[n].getName();
        }


        int maxcount = 0;
        String element_having_max_freq = "";

        for (int counting = 0; counting < n; counting++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (won_Candi[counting] == won_Candi[j]) {
                    count++;
                }
            }

            if (count > maxcount) {
                maxcount = count;
                element_having_max_freq = won_Candi[counting];
            }
        }

        System.out.println("The most common winner was " + won_Candi[n]);


    }

    public static String Input() {
        Scanner scanner = new Scanner(System.in);
        String ans = scanner.nextLine();
        return ans;
    }

}
 
