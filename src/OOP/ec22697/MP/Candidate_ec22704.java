package OOP.ec22697.MP;

import java.util.Scanner;


class Candidate_ec22704 extends Candidate {

    public String getName() {
        return "MASH";
    }

    public String getSlogan() {
        return "MASH HAS A BAAASH";
    }

    public Candidate vote(Candidate[] candidates) {


        if (candidates.length == 0)
            return new Candidate_ec22704();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("MASH HAS A BAAASH"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {


        if (votes.length == 0)
            return new Candidate_ec22704();

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

    public static int printInt(String message)
    {

        int answer = 0;
        boolean Check = false;


        Scanner look = new Scanner(System.in);


        while (!Check)
        {
            System.out.println(message);
            if (look.hasNextInt())
            {
                answer = look.nextInt();

                Check = true;


            }
            else
            {
                System.out.println("ERROR-INPUT A INTEGER, IN THE NEXT ATTEMPT");

                look.next();
            }


        }


        return answer;


    }


    //input validation for string
    public static String printString(String insertMe)
    {

        final String holdVariable;

        Scanner look = new Scanner(System.in);
        System.out.println(insertMe);
        holdVariable = look.nextLine();

        return holdVariable;


    }





    public static void main(String[] args) {
        final Candidate[] EveryCandiate = A3.getCandidateArray();
        Candidate[] votes = new Candidate[EveryCandiate.length];


        int enterNumCandidates = printInt("Number Of candidates do you want to enter?: ");


        Candidate[] candidatesUser = new Candidate[enterNumCandidates];

        for (int i = 0; i < enterNumCandidates; i++) {
            String candidateUsername = printString("Enter Candidate Username: ");

            Candidate individual = A3.getByUsername(candidateUsername, EveryCandiate);

            if (individual == null) {
                System.out.println("Not found. Try Again.");

                i--;

            } else {
                candidatesUser[i] = individual;
            }


        }

        System.out.println("List Of Your Candidates: ");

        for (int x = 0; x < candidatesUser.length; x++) {
            System.out.println((x + 1) + "-------" + candidatesUser[x].getName());
        }


        Candidate check = new Candidate_ec22704();

        String usernameCounter;

        boolean rational = false;

        while (!rational) {
            usernameCounter = printString("Which Username would you like to count the votes? ");


            check = A3.getByUsername(usernameCounter, candidatesUser);

            if (check == null) {
                System.out.println("NOT FOUND. TRY AGAIN");

            } else {
                rational = true;

            }


        }
        boolean runs = false;

        int numbersOfRun = 0;

        while (!runs) {
            numbersOfRun = printInt("Numbers Of times you want to run the election: ");

            if (numbersOfRun > 0) {
                runs = true;
            } else {
                System.out.println("Number is Positive. Try Again");
            }

        }

        Candidate[] Winners = new Candidate[numbersOfRun];

        for (int y = 0; y < numbersOfRun; y++) {
            for (int x = 0; x < EveryCandiate.length; x++) {
                try {
                    votes[x] = EveryCandiate[x].vote(candidatesUser);
                } catch (Exception e) {
                    System.out.println("ERROR WITH THE CANDIDATE USERNAME " + EveryCandiate[x]);
                    x++;
                }


            }

            Winners[y] = check.selectWinner(votes);
        }


        int highestAmount = 0;


        int normalAmount = 0;


        Candidate largestWinner = Winners[0];
        for (int i = 0; i < Winners.length; i++) {
            normalAmount = 1;
            for (int j = 0; j < Winners.length; j++) {
                if (Winners[i] == Winners[j]) normalAmount++;
            }


            if (normalAmount > highestAmount) {
                highestAmount = normalAmount;
                largestWinner = votes[i];
            }

        }
        System.out.println("The most common winner was " + largestWinner.getName());


    }


}














