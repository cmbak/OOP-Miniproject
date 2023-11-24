package OOP.ec22697.MP;

import java.util.Random;
class Candidate_ec22645 extends Candidate 
{
    
    public String getName() //hello 
    {
        return "John Cena";
    }
    
    public String getSlogan() 
    {
        return "You can't see me";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        Candidate r = new Candidate_ec22645();
        return r;
    }
    public Candidate selectWinner(Candidate[] votes) 
    {
        Candidate r = new Candidate_ec22645(); 
        if (votes.length != 0)
        {
            Random variable = new Random();
            int random = variable.nextInt(votes.length);
            return votes[random];
        }
        return r;
    }
}
//hi
