package je.panse.doro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;

import je.panse.doro.chartplate.GDSEMR_ButtonPanel;
import je.panse.doro.chartplate.GDSEMR_DocumentListner;
import je.panse.doro.chartplate.GDSEMR_FunctionKey;
import je.panse.doro.chartplate.GDSEMR_fourgate;
import je.panse.doro.fourgate.influenza.InjectionApp;
import je.panse.doro.listner.buttons.EMR_BlendColors;
import je.panse.doro.samsara.EMR_east_buttons_obj;
import je.panse.doro.samsara.EMR_OBJ_Vitalsign.Vitalsign;
import je.panse.doro.samsara.EMR_OBJ_excute.EMR_BMI_calculator;
import je.panse.doro.samsara.EMR_OBJ_excute.EMR_HbA1c;
import je.panse.doro.samsara.EMR_OBJ_excute.EMR_TFT;
import je.panse.doro.soap.subjective.EMR_symptom_main;

public class GDSEMR_frame {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 1020;
    public static int PatientID = 0;

    public static JFrame frame;
    public static JTextArea[] textAreas;
    public static JTextArea tempOutputArea;
    public static String[] titles;

    public GDSEMR_frame() {
        frame = new JFrame("GDS EMR Interface for Physician");
        textAreas = new JTextArea[10];
        tempOutputArea = new JTextArea();
        titles = new String[]{"CC>", "PI>", "ROS>", "PMH>", "S>", "O>", "Physical Exam>", "A>", "P>", "Comment>"};
    }

    public void createAndShowGUI() throws Exception {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocation(360, 60);
        frame.setUndecorated(true);

        JPanel centerPanel = new JPanel(new GridLayout(5, 2));
        centerPanel.setPreferredSize(new Dimension(900, 1000));

        tempOutputArea.setEditable(true);

        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(500, FRAME_HEIGHT));
        JScrollPane outputScrollPane = new JScrollPane(tempOutputArea);
        outputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        westPanel.add(outputScrollPane, BorderLayout.CENTER);

        JPanel northPanel = new GDSEMR_ButtonPanel("north");
        JPanel southPanel = new GDSEMR_ButtonPanel("south");

        // Initialize and setup JTextAreas and their JScrollPane containers
        for (int i = 0; i < textAreas.length; i++) {
            textAreas[i] = new JTextArea();
            String inputData = (titles[i] + "\t" + " ");
            textAreas[i].setLineWrap(true);
            textAreas[i].setText(inputData);
            textAreas[i].setCaretPosition(0);
            
            EMR_BlendColors.blendColors(textAreas[i], tempOutputArea, i);

            JScrollPane scrollPane = new JScrollPane(textAreas[i]);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            centerPanel.add(scrollPane);

            textAreas[i].getDocument().addDocumentListener((DocumentListener) new GDSEMR_DocumentListner(textAreas, tempOutputArea));
            textAreas[i].addMouseListener(new DoubleClickMouseListener());
            textAreas[i].addKeyListener(new FunctionKeyPress());
        }

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GDSEMR_frame emrFrame = new GDSEMR_frame();
            try {
                emrFrame.createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Additional feature executions, likely these are separate threads or events
        EMR_east_buttons_obj.main(null);
        Vitalsign.main(args);
        EMR_HbA1c.main(null);
        EMR_symptom_main.main(null);
        EMR_BMI_calculator.main(null);
        EMR_TFT.main(null);
        InjectionApp.main(null);
    }

    public static void setTextAreaText(int index, String text) {
        if (textAreas != null && index >= 0 && index < textAreas.length) {
            textAreas[index].append(text);
        } else {
            System.err.println("Invalid text area index or text areas not initialized.");
        }
    }

    private static class FunctionKeyPress extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            // Check if the key code is within the range of function keys from F1 to F12
            if (keyCode >= KeyEvent.VK_F1 && keyCode <= KeyEvent.VK_F12) {
                // Construct the function key message dynamically
                String functionKeyMessage = "F" + (keyCode - KeyEvent.VK_F1 + 1) + " key pressed - Action executed.";

                // Handle the function key action using the utility class, passing the key code
                GDSEMR_FunctionKey.handleFunctionKeyAction(1, functionKeyMessage, keyCode);
            }
        }
    }

    private static class DoubleClickMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                JTextArea source = (JTextArea) e.getSource();
                String text = source.getText();
                System.out.println("Double-clicked on: " + text);
                try {
                    GDSEMR_fourgate.main(text);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void updateTempOutputArea(String text) {
        tempOutputArea.setText(text);
    }
}
