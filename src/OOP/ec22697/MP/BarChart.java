package OOP.ec22697.MP;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class BarChart extends JPanel {
    private int rectWidth;
    private int rectHeightMult;
    private final int DISPLACEMENT;
    private final int FRAME_HEIGHT_PADDING = 15;
    private final int BAR_DISPLACEMENT_TOP = 5;
    private final int TEXT_BAR_GAP = 30;
    private int rect_x = 15;
    private Candidate[] upForElection;
    private Candidate[] electWinners;
    private int tallestRectHeight;
    private boolean halfHeight = false;

    BarChart(Candidate winner, Candidate[] c, Candidate[] ew) {
        upForElection = c;
        electWinners = ew;

        if (electWinners.length < 25) {
            rectHeightMult = 24;
        } else if (electWinners.length < 50) {
            rectHeightMult = 12;
        } else if (electWinners.length < 100) {
            rectHeightMult = 7;
        } else if (electWinners.length < 300){
            rectHeightMult = 5;
        } else {
            halfHeight = true;
        }

        if (upForElection.length < 50) {
            rectWidth = 35;
        } else if (upForElection.length >= 51 & upForElection.length < 100) {
            rectWidth = 25;
        } else {
            rectWidth = 15;
        }

        DISPLACEMENT = rectWidth /2 + 10;

//        System.out.println("up for elec length: " + upForElection.length);
//        System.out.println("rect width: " + RECT_WIDTH + "\ndisplacemnt " + DISPLACEMENT);
        int width = (upForElection.length * rectWidth) + (DISPLACEMENT * upForElection.length);

        if (halfHeight) {
            tallestRectHeight = countInArray(electWinners, winner) / 2;
        } else {
            tallestRectHeight = countInArray(electWinners, winner) * rectHeightMult;
        }

        // There's probably a formula to work out the scale?
        // height of bar chart should be height of tallest rect + length of longest name + some padding or something along these lines
        int frameHeight = tallestRectHeight + BAR_DISPLACEMENT_TOP + TEXT_BAR_GAP + longestCandName()*2 + FRAME_HEIGHT_PADDING*5;
        setPreferredSize(new Dimension(width+rect_x, frameHeight+20)); // 20 is just a bit more padding since the No Name (...) gets cut off - not a very elegant solution
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawBars(g2);
    }

    // would this be an example of a anonymous thingy? is there even a point?
    private void drawBars(Graphics2D g2) {
        int height;
        for (Candidate c : upForElection) {
            //System.out.println("Number of times " + c.getName() + " has won: " + countInArray(electWinners, c));
            if (halfHeight) {
                height = countInArray(electWinners, c) / 2;
            } else {
                height = countInArray(electWinners, c) * rectHeightMult;
            }

            //Rectangle2D.Double r = new Rectangle2D.Double(rect_x, frameHeight+longestCandName()-TEXT_BAR_GAP-height, RECT_WIDTH, height);
            //Rectangle2D.Double r = new Rectangle2D.Double(rect_x, (BAR_DISPLACEMENT_TOP + tallestRectHeight)-TEXT_BAR_GAP-height, RECT_WIDTH, height);
            Rectangle2D.Double r = new Rectangle2D.Double(rect_x, (BAR_DISPLACEMENT_TOP + tallestRectHeight+TEXT_BAR_GAP)-height, rectWidth, height);

            g2.setColor(randomColour());
            g2.fill(r);

            AffineTransform originalPos = g2.getTransform(); // Used to restore rotation after text drawn
            g2.rotate(Math.PI/2);

            // when rotating 90 deg
            // - y moves right
            // + x moves down
            // so we want to swap x with y, and make y negative

            //g2.drawString(c.getName(), frameHeight-TEXT_BAR_GAP+5, -rect_x - RECT_WIDTH/2);
            //g2.drawString(c.getName(), frameHeight+longestCandName()-TEXT_BAR_GAP+5, -rect_x - RECT_WIDTH/2);
            g2.drawString(c.getName(), (BAR_DISPLACEMENT_TOP + tallestRectHeight+TEXT_BAR_GAP)+5, -rect_x - rectWidth /2);
            g2.setTransform(originalPos);
            rect_x += DISPLACEMENT + rectWidth;
        }

    }

    // Returns a random colour
    private Color randomColour() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }

    // Counts how many times a given candidate is in an array
    private int countInArray(Candidate[] candidates, Candidate target) {
        int count = 0;
        for (Candidate c : candidates) {
            if (c == target) {
                count += 1;
            }
        }
        return count;
    }

    // Returns the length of the longest name
    private int longestCandName() {
        int longestName = -1;
        for (Candidate c : upForElection) {
            if (c.getName().length() > longestName) {
                longestName = c.getName().length();
            }
        }
    return longestName;
    }
}
