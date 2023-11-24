// Change contributions after repo closes!
// ec221026 - commment out uncessary code
// ex20549 - comment out input
// ec22415 - <= should be < in vote method
// ec22716 - changed candidates.length to candidates.length-1
// ec22840 - changed 100 to candidates.length
// ec22888 - changed 40 to 0
// ec22793 - changed candidates[2] to new ec ...
// 221204 - changed /2 to just .length
// 22566 - ^
// 22770 - changed return to self
// 22840 - returned random candidate
// 22883 - got rid of -1 from bound
// 22990 - ^
// 221185 - returns self instead of null!!!
// 22626 - ^

package OOP.ec22697.MP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MP_ec22697 {
    final private Candidate[] CONTRIBUTIONS = A3.getCandidateArray();
    // Prevents duplicates from being added
    private Set<Candidate> electionCandidates = new HashSet<>();
    private int numOfElections = 0;
    private Candidate voteCounter;
    private JPanel mainPanel;
    private JButton quitButton;
    private JButton runElectionBtn;
    private JButton addRandomBtn;
    private JLabel specificLabel;
    private JTextField specificCandField;
    private JButton addSpecificBtn;
    private JLabel elecCandidatesLabel;
    private JTextArea electionCandidatesArea;
    private JLabel timesRunLabel;
    private JTextField timesToRunField;
    private JLabel voteCandLabel;
    private JTextField voteCandField;
    private JButton voteCandBtn;
    private JButton clearFieldsBtn;



    // private or public?
    // my candidate class version of this method has the numErrors var etc.
    private void runActualElection() {
        Candidate[] electionWinners = new Candidate[numOfElections];
        Candidate[] votes = new Candidate[CONTRIBUTIONS.length];
        //Candidate[] elecCandArray = electionCandidates.toArray()

        for (int i = 0; i < numOfElections; i++) {
            for (int j = 0; j < votes.length; j++) {
                try {
                    votes[j] = CONTRIBUTIONS[j].vote(electionCandidates.toArray(new Candidate[electionCandidates.size()]));
                    // System.out.println(candidates[j].un + " has votes for " + votes[j].getName());
                } catch (Exception e) {
                    //System.out.println("Error caused by " + candidates[j].un + ": " + numErrors + "/" + votes.length + "\n\t" + e + "\tVoter num: " + voterCount + "/" + votes.length);
                    System.out.println("Error caused by " + CONTRIBUTIONS[j].un);
                }
            }
            electionWinners[i] = voteCounter.selectWinner(votes);
            votes = new Candidate[CONTRIBUTIONS.length];
        }
        Candidate winner = countMostFrequentW(electionWinners);
        displayBarChart(winner, electionWinners);
    }

    // Displays the bar chart
    // NOTE: Doesn't work well if election ran once
    // OR if write ins win!
    private void displayBarChart(Candidate winner, Candidate[] elecWinners) {
        JFrame f = new JFrame("A minimalist (poor) bar chart of the results");
        JLabel x = new JLabel("<html><div style='text-align: center;'>Num<br>of<br>Elections<br>Won</div></html>");
        JLabel y = new JLabel("Candidates", SwingConstants.CENTER);
        f.add(x, BorderLayout.WEST);
        f.add(y, BorderLayout.SOUTH);
        BarChart bc = new BarChart(winner, electionCandidates.toArray(new Candidate[electionCandidates.size()]), elecWinners);
        bc.setBackground(Color.white);
        f.add(bc, BorderLayout.CENTER);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.pack();
        f.setVisible(true);
    }

    // Counts the most frequent winner from multiple elections and displays this
    // Doesn't work for multiple winners
    private Candidate countMostFrequentW(Candidate[] electionWinners) {
        int largestNumOfVotes = 0;
        Candidate mostPopularCandidate = electionWinners[0];

        for (Candidate winner : electionWinners) {
            int numberOfVotes = 0;
            for (Candidate electionWinner : electionWinners) {
                if (electionWinner == winner) {
                    numberOfVotes += 1;
                }
            }
            if (numberOfVotes > largestNumOfVotes) {
                largestNumOfVotes = numberOfVotes;
                mostPopularCandidate = winner;
            }
        }

        try {
            JOptionPane.showMessageDialog(runElectionBtn, "The most common winner is " + mostPopularCandidate.getName());
            return mostPopularCandidate;

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(runElectionBtn, "Oops, something went wrong " + e);
            return mostPopularCandidate;
        }
    }

    public MP_ec22697() {
        runElectionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // MAKE SURE NOTHING IS NULL OR INVALID
                if (electionCandidates.size() < 1) {
                    JOptionPane.showMessageDialog(runElectionBtn, "Please ensure that there is at least 1 candidate up for election.");
                    return;
                }
                if (voteCounter == null) {
                    JOptionPane.showMessageDialog(runElectionBtn, "Please ensure that you have chosen a candidate to count the votes.");
                    return;
                }
                try {
                    numOfElections = Integer.parseInt(timesToRunField.getText());
                    if (numOfElections < 1) {
                        JOptionPane.showMessageDialog(runElectionBtn, "Sorry, you must run the election at least once.");
                        return;
                    }
                    if (numOfElections > 1000) {
                        JOptionPane.showMessageDialog(runElectionBtn, "Sorry, you can only run the election at most 1000 times.");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(runElectionBtn, "Please enter a valid integer greater than 0.");
                    return;
                }

                // Now if everything's correct, run the election!
                runActualElection();
                // MAKE SURE THAT ONCE ELECTION IS RUN, THE CANDIDATES UP FOR ELECTION AND ELECTION WINNERS ARE NULL
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });
        addSpecificBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String candToFind = specificCandField.getText();

                for (Candidate c : CONTRIBUTIONS) {
                    if (c.getName().equalsIgnoreCase(candToFind)) {
                        if (electionCandidates.contains(c)) {
                            JOptionPane.showMessageDialog(addSpecificBtn, c.getName() + " is already running for election!");
                            return;
                        }
                        electionCandidates.add(c);
                        updateCandidatesText(c);
                        return;
                    }
                }

                JOptionPane.showMessageDialog(addSpecificBtn, "Cannot find a candidate with the name: " + candToFind);
                return;
            }
        });
        addRandomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int index = random.nextInt(CONTRIBUTIONS.length);
                Candidate newCand = CONTRIBUTIONS[index];

                if (electionCandidates.contains(newCand)) {
                    index = random.nextInt(CONTRIBUTIONS.length);
                    newCand = CONTRIBUTIONS[index];
                }

                electionCandidates.add(newCand);
                updateCandidatesText(newCand);
            }
        });
        voteCandBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String candToFind = voteCandField.getText();
                for (Candidate c : CONTRIBUTIONS) {
                    if (c.un.equalsIgnoreCase(candToFind)) {
                        voteCounter = c;
                        JOptionPane.showMessageDialog(voteCandBtn, c.un + " has been chosen to count the votes!");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(voteCandBtn, "No candidate with the name : " + candToFind);
            }
        });
        clearFieldsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                electionCandidatesArea.setText("");
                timesToRunField.setText("");
                voteCandField.setText("");
                specificCandField.setText("");
                electionCandidates.clear();
            }
        });
    }

    // Just in case I change implementation later on
    private void updateCandidatesText(Candidate c) {
        electionCandidatesArea.append(c.getName() + "\n");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MP_ec22697");
        frame.setContentPane(new MP_ec22697().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
