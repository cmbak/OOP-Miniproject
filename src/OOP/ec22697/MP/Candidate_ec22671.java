package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22671 extends Candidate {

    // a method that is going to hold the name of the candidate
   public String getName() 
   {
       return "Hiphopologist";
   }


   // a method that is going to hold the slogan of the candidate
   public String getSlogan() 
   {
       return "Doctore Rap Fa";
   }


   // a method that is going to hold the votes in array
   public Candidate vote(Candidate[] candidates) 
   {
       // make sure that there is an array of candidates to be evaluated
       if (candidates.length == 0)
          return new Candidate_ec22671();
      
      // search for Hiphopologist and if it is in the list, vote for him
      for (Candidate c : candidates)
          if (c.getName().equals("Hiphopologist"))
              return c;
      
      //if the array does not have the name of the candidate, vote for a random person
      Random random = new Random();
      int x = random.nextInt(candidates.length);
      return candidates[x];
  }



   // a method that is going to count the number of votes and decide the winner
   public Candidate selectWinner(Candidate[] votes) 
   {
        // make sure there is an array to be evaluated
       if (votes.length == 0)
          return new Candidate_ec22671();
      
      // make sure there is a winner.
      Candidate a = votes[0];
      
      
      int count = 0;
      
      for (Candidate c : votes)
      {
          // setting a seccong counter variable to compare with the first counter variable for the nmber of the votes
          int count2 = 0;
          
          // a second loop to compare the number of the vtes between two elements of the array
          for (Candidate d : votes)
              if (d == c)
                  count2 = count2 + 1;
          
          
          if (count2 >= count)
          {
              count = count2;
              a = c;
          }
      }
      
       return a;
   }


    // a method that is going to return the input value as a string
    public static String inputS(String text)
    {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println(text);
        input = scanner.nextLine();

        return input;
    }

  
    // a method that is going to return the inptu value as an integer
    public static int inputI(String text)
    {
        String input = inputS(text);

        return Integer.parseInt(input);
    }


  
    // a method that is going to return the input answer for the options
    public static String option()
    {
        String a = inputS("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

        return a;
    }


    // a method to expand the array of the candidates and add a candidate
    public static Candidate[] EA(Candidate[] all, Candidate add)
    {
        Candidate[] c = new Candidate[all.length + 1];

        for (int i=0; i<all.length; i++)
        {
            c[i] = all[i];
        }

        c[c.length - 1] = add;

        return c;
    }


  
    // a method that is going to generate a random number
    public static int generate(int limit)
    {
        Random random = new Random();

        int r = random.nextInt(limit);

        return r;
    }


    // a method that is going t o use the random number to return the related candidate
    public static Candidate random(int num, Candidate[] all)
    {
        Candidate c = all [num];

        return c;
    }


    // the main method 
    public static void main(String [] args)
    {
        Candidate[] all =  A3.getCandidateArray();
        

        System.out.println("= Running Repeated Elections =");

        String answer = option();

        while(! answer.equals("c"))
        {

            // this part is for when the answer to the option is a 
            if (answer.equals("a"))
            {
                String username = inputS("Please enter a username.");
                Candidate a = A3.getByUsername(username, all);

                if (a == null)
                {
                    System.out.println("there is no such username");
                }

                else
                {
                    all = EA(all, a);

                    System.out.println("the new list of candidates is as follow:");

                    for (int i=0; i<all.length; i++)
                    {
                        System.out.println(all[i].getName());
                        System.out.println(all[i].getSlogan());
                    }

                }

                answer = option();
            }




            // this is for when the answer to the option is b
            else if (answer.equals("b"))
            {

                int random = generate(all.length);
                Candidate f = random(random, all);

                all = EA(all, f);

                System.out.println("the new list of candidates is as follow:");

                    for (int t=0; t<all.length; t++)
                    {
                        System.out.println(all[t].getName());
                        System.out.println(all[t].getSlogan());
                    }


                answer = option();

            }
        }



        // this is for when the answer to the options is c
        if (answer.equals("c"))
        {
            String answer2 = inputS("Who should count the votes?");

            Candidate counter = A3.getByUsername(answer2, all);

            while (counter == null)
            {
                System.out.println("there is no such username.");

                answer2 = inputS("Who should count the votes?");

                counter = A3.getByUsername(answer2, all);
            }


            int number = inputI("How many times shall we run the election?");

            while (number <= 0)
            {
                System.out.println("the number can not be zero or negative.");
                number = inputI("How many times shall we run the election?");
            }

            Candidate[] top = new Candidate[number];
            Candidate[] votes = new Candidate[all.length];

            for (int j=0; j<number; j++)
            {
                for (int h=0; h<all.length; h++)
                {
                    votes[h] = all[h].vote(all);
                }


                top[j] = counter.selectWinner(votes);
            }


            int highest = 0;
            Candidate winner = top[0];

            for (int k=0; k<top.length; k++)
            {
                int hold = 1;

                for (int e=0; e<top.length; e++)
                {
                    if (top[k] == top[e])
                    {
                        hold = hold + 1;
                    }
                }


                if (hold > highest)
                {
                    highest = hold;
                    winner = top[k];
                }
            }

            System.out.println("Most common winner is " + winner.getName());
        }

        return;
    }
}
