package OOP.ec22697.MP;

import java.util.*;

class Candidate_ec22926 extends Candidate {
    
    @Override
    public String getName() {
        return "theUSER";
    }
    
    @Override
    public String getSlogan() {
        return "No slogan for now..()";
    }

    // checks for user, if not returns itself
    @Override
    public Candidate vote(Candidate[] candidates) {
        return candidates.length > 0 ? candidates[candidates.length - 1] : new Candidate_ec221006();
    }
    
    @Override
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec221006();
        }
        Map<Candidate, Integer> counts = new HashMap<>();
        for (Candidate c : votes) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        return Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Candidate[] allCandidates = A3.getCandidateArray();
        List<Candidate> candidates = new ArrayList<>();

        System.out.println("Would you like to a) add a specific candidate ");
        System.out.println("Would you like to b) add a candidate at random ?");
        System.out.println("Would you like to c) run the election?");
        
        String response = scanner.nextLine();

        if (response.equals("a")) {
            System.out.println("Please enter a username.");
            response = scanner.nextLine();
            Candidate specific = A3.getByUsername(response, allCandidates);
            if (specific == null) {
                candidates.add(specific);
            }
        } else if (response.equals("b")) {
            int randomIndex = new Random().nextInt(allCandidates.length);
            candidates.add(allCandidates[randomIndex]);
        } else if (response.equals("c")) {
            Candidate[] votes = Arrays.stream(allCandidates).map(c -> c.vote(candidates.toArray(new Candidate[0]))).toArray(Candidate[]::new);
            Candidate winner = new Candidate_ec22926().selectWinner(votes);
            System.out.println("The winner is: " + winner.getName());
        }
    }
}
