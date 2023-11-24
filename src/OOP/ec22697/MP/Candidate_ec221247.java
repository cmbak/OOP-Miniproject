package OOP.ec22697.MP;

import java.util.ArrayList;
        import java.util.Random;
        import java.util.Scanner;

        public class Candidate_ec221247 extends Candidate {


            public static void main(String[] args) {
                Candidate[] voters = A3.getCandidateArray();
                ArrayList<Candidate> listOfCandidates = new ArrayList<>();
                Candidate_ec221247 me = new Candidate_ec221247();
                Candidate[] ballotBox = new Candidate[voters.length];
                try{
                    int choice;
                    do{
                        choice = me.getChoice("Would you like to \n\t 1) add a candidate"
                                + "\n\t 2) run an election \n\t 3) Exit");
                        if(choice == 1) {me.addCandidate(voters, listOfCandidates);}
                        else if (choice == 2) {
                            me.runElection(voters, listOfCandidates, ballotBox);
                            if(ballotBox[0] != null){
                                int countingMethod;
                                do {
                                    countingMethod = me.getChoice("\nWould you like to \n\t 1) Find a winner" +
                                            "\n\t 2) Find most common winner \n\t 3) exit");
                                    if(countingMethod == 1){ me.findWinner(voters, ballotBox);}
                                    else if (countingMethod == 2) {
                                        me.mostCommonWinner(voters, ballotBox);
                                    }
                                }while (countingMethod < 3);
                            }
                        }
                    }while(choice < 3);
                }
                catch (Exception e){
                    me.pr("Exception caught");
                }
            }


            // name of the candidate or voter
            public String getName() {
                return "Kumaran";
            }


            // return slogan
            public String getSlogan() {
                return "Stronger Together!";
            }


            // vote for an election
            public Candidate vote(Candidate[] candidates) {

                // If array is empty, return instance of friend's class.
                if (candidates.length == 0)
                    return new Candidate_ec22981();

                // Search for a slogan.
                for (Candidate c : candidates) {
                    if (c.getSlogan().equals("Stronger Together!"))
                        return c;
                }

                // Otherwise, search for a friend.
                for (Candidate c : candidates) {
                    if (c.getName().equals("Andrew"))
                        return c;
                }

                // Otherwise, choose at random from list.
                Random r = new Random();
                int i = r.nextInt(candidates.length);
                return candidates[i];
            }


            // return most frequent candidate
            public Candidate selectWinner(Candidate[] ballotBox) {

                // If array is empty, return instance of friend's class
                if (ballotBox.length == 0)
                    return new Candidate_ec22981();

                // assign first candidate as winner
                Candidate winnerCandidate = ballotBox[0];

                // counting votes
                int largestCountSoFar = 0;
                for(Candidate candidate : ballotBox){
                    if(numberOfTimes(candidate, ballotBox) > largestCountSoFar){
                        largestCountSoFar = numberOfTimes(candidate, ballotBox);
                        winnerCandidate = candidate;
                    }
                }

                return winnerCandidate;
            }


            // count number of times an element occurs in an array
            int numberOfTimes(Candidate candidate, Candidate[] ballotBox) {
                int occurrence = 0;
                for(Candidate c : ballotBox) {
                    if(c == candidate) occurrence++;
                }
                return occurrence;
            }


            // print assigned string
            void pr(String s){
                System.out.println(s);
            }


            // read input form keyboard
            String getString(String s){
                pr(s);
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLine();
            }


            // print menu and return first character of the input
            int getChoice(String s){
                String stringInput = getString(s);
                while(!(stringInput.equals("1")  || stringInput.equals("2") || stringInput.equals("3"))){
                    pr("Invalid input try again: ");
                    stringInput = getString(s);
                }
                return Integer.parseInt(stringInput);
            }


            // display add candidate manu call add candidate methods
            void addCandidate(Candidate[] voters, ArrayList<Candidate> listOfCandidates) {
                int choice;
                do {
                    choice = getChoice("How would you like to \n\t 1) add a specific candidate"
                            + "\n\t 2) add random candidate \n\t 3) exit");
                    if(choice == 1){ addSpecificCandidate(voters, listOfCandidates);}
                    else if (choice == 2) { addRandomCandidate(voters, listOfCandidates);}
                }while (choice < 3);
            }


            // add user specified candidate from voters list
            void addSpecificCandidate(Candidate[] voters, ArrayList<Candidate> listOfCandidates){
                String specificName = getString("Which specific user would you like to include?");
                Candidate specificCandidate = A3.getByUsername(specificName, voters);
                if (specificCandidate != null){
                    listOfCandidates.add(specificCandidate);
                    pr("Candidate  " + specificCandidate.getName() + " added to the list");
                }
                else pr("User not found.");

            }


            // add candidates randomly
            void addRandomCandidate(Candidate[] voters, ArrayList<Candidate> listOfCandidates){
                Random random = new Random();
                int randomPosition = random.nextInt(voters.length);
                if(voters[randomPosition]!=null) {
                    listOfCandidates.add(voters[randomPosition]);
                    pr("Candidate  " + voters[randomPosition].getName() + " added to the list");
                }
            }


            // run election
             void  runElection(Candidate[] voters, ArrayList<Candidate> listOfCandidates, Candidate[] ballotBox){
                if(listOfCandidates.isEmpty()) {
                    pr("Candidate list is empty. Choose candidates first to run the Election");
                }
                else {
                    displayCandidates(listOfCandidates);
                    polling(voters, listOfCandidates, ballotBox);
                }
            }


            // display candidates
            void displayCandidates(ArrayList<Candidate> listOfCandidates){
                pr("Candidate list \n -----------------------");
                for(int i = 0; i < listOfCandidates.size(); i++){
                    pr((i+1) + ") " + listOfCandidates.get(i).getName());
                }
            }


            // Ask all voters to vote.
            void polling(Candidate[] voters, ArrayList<Candidate> listOfCandidates, Candidate[] ballotBox){
                pr("\n Polling \n =======================");
                for (int i=0; i < voters.length; i++) {

                    try {
                        Candidate[] candidates = (listOfCandidates.toArray(new Candidate[0]));

                        Candidate v = voters[i].vote(candidates);
                        if(v != null){
                            System.out.println(voters[i].getName() + " voted for " + v.getName() +
                                    ", whose slogan is '" + v.getSlogan() + "'.");
                            ballotBox[i] = v;
                        }
                        else{
                            pr("Invalid vote");
                        }

                    } catch (Exception e) {
                        pr("One Invalid vote cast");
                        i++;
                    }
                }
            }


            //counting vote
            void findWinner(Candidate[] voters, Candidate[] ballotBox){
                int choice;
                do {
                    choice = getChoice("Would you like to \n\t 1) Find winner by default method? " +
                            "\n\t 2) Select winner using an specific voter? " + "\n\t 3) exit? ");
                    if(choice == 1){
                        pr( "My count: " + selectWinner(ballotBox).getName() + " won the election.\n");
                    }
                    else if (choice == 2) { winnerUsingAVoter(voters, ballotBox);}
                }while (choice < 3);
            }

            // Count votes choosing a specified candidate
            void winnerUsingAVoter(Candidate[] voters, Candidate[] ballotBox){
                String userNameOfCountingPerson = getString("Who would you trust to count the vote?");
                Candidate countingPerson = A3.getByUsername(userNameOfCountingPerson, voters);
                if(countingPerson != null){
                    Candidate winner = countingPerson.selectWinner(ballotBox);
                    pr(countingPerson.getName() + " decides " + winner.getName() + " won.");
                }
                else{
                    pr("Invalid username entered. Try again");
                }
            }

            // most common winner
            void mostCommonWinner(Candidate[] voters, Candidate[] ballotBox){

                Candidate[] winners = new Candidate[voters.length];
                int winnerPosition = 0;

                for (int i = 0; i < voters.length; i++) {
                    try {
                        if(voters[i] != null){
                            winners[winnerPosition] = voters[i].selectWinner(ballotBox);
                            pr(voters[i].getName() + " choose " + winners[winnerPosition].getName() + " as a winner.");
                            winnerPosition++;
                        }
                    }
                    catch (Exception e) {
                        pr("Winner is not chosen");
                        i++;
                    }
                }

                pr("The overall winner is " + selectWinner(winners).getName());
            }

        }


