package OOP.ec22697.MP;

class Candidate_ec221003 extends Candidate {
	public static Candidate Myself = new Candidate_ec221003();

	final String[] myInterests = {
		"future", 
		"trees", 
		"problems", 
		"solving", 
		"environment", 
		"climate", 
		"change", 
		"pollution", 
		"fish", 
		"ocean", 
		"sea", 
		"water", 
		"air", 
		"earth", 
		"space", 
		"universe", 
		"galaxy", 
		"stars", 
		"planets", 
		"moon", 
		"sun", 
		"life", 
		"death", 
		"human", 
		"animals", 
		"plants", 
		"food", 
		"health", 
		"medicine", 
		"science", 
		"technology", 
		"engineering", 
		"art", 
		"music", 
		"poetry", 
		"painting", 
		"writing", 
		"books", 
		"reading", 
		"education", 
		"school"
	};

	public Candidate compareSloganWithMyValues(String[] myValues, Candidate[] candidates) {
		final String[] interesting_topics = myValues;

		java.util.ArrayList<Candidate> candidates_with_interesting_topics = new java.util.ArrayList<Candidate>();

		// this will get all candidates who have a slogan talking about one of the above things
		for (Candidate candidate : candidates) {
			String slogan = candidate.getSlogan().toLowerCase();
			for (String topic : interesting_topics) {
				if (slogan.contains(topic)) {
					candidates_with_interesting_topics.add(candidate);
					break;
				}
			}
		}

		// then create a map of candidates and the number of topics their slogan has
		// in common with the topics we are interested in
		// which will only record candidates who have the highest number of topics in common
		java.util.ArrayList<Candidate> candidates_with_most_interesting_topics = new java.util.ArrayList<Candidate>();
		int max_topics = 0;
		for (Candidate candidate : candidates_with_interesting_topics) {
			String slogan = candidate.getSlogan().toLowerCase();
			int topics = 0;
			for (String topic : interesting_topics) {
				if (slogan.contains(topic)) {
					topics++;
				}
			}
			if (topics > max_topics) {
				max_topics = topics;
				candidates_with_most_interesting_topics.clear();
				candidates_with_most_interesting_topics.add(candidate);
			} else if (topics == max_topics) {
				candidates_with_most_interesting_topics.add(candidate);
			}
		}

		// then if there's only 1 candidate in that list, return them
		if (candidates_with_most_interesting_topics.size() == 1) {
			return candidates_with_most_interesting_topics.get(0);
		}
		// and if there's more than 1, return a random entry
		if (candidates_with_most_interesting_topics.size() > 1) {
			return candidates_with_most_interesting_topics.get((int) (Math.random() * candidates_with_most_interesting_topics.size()));
		}

		return null;
	}

	@Override
	public Candidate vote(Candidate[] candidates) {
		if (candidates.length == 0) return Candidate_ec22617.Myself;
		// ec22617@qmul.ac.uk - Zak

		// search for people who think of similar things
		Candidate most_interesting_candidate = this.compareSloganWithMyValues(this.myInterests, candidates);
		if (most_interesting_candidate == null) {
			// if somehow, the above does not happen, uh... panic!
			// return a random candidate from the candidates parameter array
			most_interesting_candidate = candidates[(int) (Math.random() * candidates.length)];
		}

		return most_interesting_candidate;
	}

	@Override
	public Candidate selectWinner(Candidate[] votes) {
		if (votes.length == 0) return null;

		// create a map, of candidate object against the frequency of their appearance in votes
		java.util.Map<Candidate, Integer> candidate_frequency = new java.util.HashMap<Candidate, Integer>();
		for (Candidate candidate : votes) {
			if (candidate_frequency.containsKey(candidate)) {
				candidate_frequency.put(candidate, candidate_frequency.get(candidate) + 1);
			} else {
				candidate_frequency.put(candidate, 1);
			}
		}

		// iterate over the frequency map, retrieve an array of candidates
		//   where all items have the same highest frequency
		java.util.ArrayList<Candidate> candidates_with_most_votes = new java.util.ArrayList<Candidate>();
		int max_votes = 0;
		for (Candidate candidate : candidate_frequency.keySet()) {
			int votes_for_candidate = candidate_frequency.get(candidate);
			if (votes_for_candidate > max_votes) {
				max_votes = votes_for_candidate;
				candidates_with_most_votes.clear();
				candidates_with_most_votes.add(candidate);
			} else if (votes_for_candidate == max_votes) {
				candidates_with_most_votes.add(candidate);
			}
		}

		// if there's only 1 person in that list, return them
		if (candidates_with_most_votes.size() == 1) {
			return candidates_with_most_votes.get(0);
		}
		// else, if there's more then run them through the values function again
		//   and return the most interesting one
		if (candidates_with_most_votes.size() > 1) {
			Candidate most_interesting_candidate = this.compareSloganWithMyValues(this.myInterests, candidates_with_most_votes.toArray(new Candidate[candidates_with_most_votes.size()]));
			if (most_interesting_candidate == null) {
				// if that was null... somehow, then return a random item from the candidates_with_most_votes list
				most_interesting_candidate = candidates_with_most_votes.get((int) (Math.random() * candidates_with_most_votes.size()));
			}
			return most_interesting_candidate;
		}

		// if somehow, after all of that, it still falls though...
		// idk what to tell you. just return a random item from the candidates_with_most_votes array
		return candidates_with_most_votes.get((int) (Math.random() * candidates_with_most_votes.size()));
	}

	@Override
	public String getName() {
		final String myName = "Chee-Ho Nim"; // 11 chars long
		return myName;
	}

	@Override
	public String getSlogan() {
		final String mySlogan = "Let's work on the Future together! 😘✌"; // 37 chars long
		return mySlogan;
	}
	
}