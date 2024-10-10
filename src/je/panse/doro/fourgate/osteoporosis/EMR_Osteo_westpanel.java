package je.panse.doro.fourgate.osteoporosis;

import java.awt.Color;	
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import je.panse.doro.fourgate.diabetes.dmAutonomic.ANPdm;
import je.panse.doro.fourgate.diabetes.dmPeripheral.PNPdm;
import je.panse.doro.soap.subjective.EMR_symptom_list;
import je.panse.doro.soap.subjective.EMR_symptom_retStr;

public class EMR_Osteo_westpanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Select category ...");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocation(0, 610);
        frame.setSize(new Dimension(300, 460));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] buttonNames = {
                "Os Medical History",
                "Os Lifestyle Factors",
                "Os Risk Assessment & Symptoms",
                "Os Diagnosis & Treatment Plan",
                "Osteoporosis",
                "Osteoporosis",
                "Osteoporosis",
                "Quit"
        };

        for (String name : buttonNames) {
            JButton button = createButton(name, frame);
            frame.add(button);
            frame.add(Box.createVerticalStrut(10));
        }

        // Create a Timer to close the frame after 5 minutes (300,000 milliseconds)
        Timer timer = new Timer(500000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the frame
            }
        });
        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start(); // Start the timer

        frame.setVisible(true);
    }

    private static JButton createButton(String name, JFrame frame) {
        JButton button = new JButton(name) {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    Graphics2D g2d = (Graphics2D) g;
                    int width = getWidth();
                    int height = getHeight();

                    // Create a gradient from top to bottom
                    GradientPaint gradient = new GradientPaint(
                            0, 0, Color.decode("#B9D8DE"),
                            0, height, Color.decode("#6FB9C7"));

                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, width, height);
                }
                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Box.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (name.equals("Quit")) {
                    frame.dispose();
                } else if (name.equals("Os Medical History")) {
                	   EMR_osteo_MedicalHistory.main(null);
                } else if (name.equals("Os Lifestyle Factors")) {

                } else if (name.equals("Os Risk Assessment & Symptoms")) {
                    PNPdm.main(null);
                } else if (name.equals("Os Diagnosis & Treatment Plan")) {
                    ANPdm.main(null); 
                } else if (name.equals("Osteoporosis")) {
	            } else if (name.equals("Osteoporosis")) {
	            }
                else {
                    // Replace with your logic to handle button click
                    // String[] args = returnStr(name);
                    // EMR_symptom_list.main(args);
                }
            }
        });

        return button;
    }

    // Placeholder method for returnStr
    private static String[] returnStr(String name) {
        // Add your logic to populate the returnargs array
        return new String[]{};
    }
}
