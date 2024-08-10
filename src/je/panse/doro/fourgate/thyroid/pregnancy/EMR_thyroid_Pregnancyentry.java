package je.panse.doro.fourgate.thyroid.pregnancy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import je.panse.doro.GDSEMR_frame;

public class EMR_thyroid_Pregnancyentry {

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final String[] BUTTON_LABELS = {
        "New Patient for Pregnancy with Thyroid disease",
        "F/U Pregnancy with Hyperthyroidism",
        "F/U Pregnancy with Hypothyroidism",
        "F/U Pregnancy with Non thyroidal illness",
        "F/U Pregnancy with TSH elevation",
        "F/U Pregnancy with TSH low",
        "Quit"
    };

    public static void main(String[] args) {
        JFrame frame = createMainFrame();
        addButtonsToFrame(frame);
        positionFrameToBottomRight(frame);
        frame.setVisible(true);
    }

    private static JFrame createMainFrame() {
        JFrame frame = new JFrame("Injections");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(BUTTON_LABELS.length, 1));
        return frame;
    }

    private static void addButtonsToFrame(JFrame frame) {
        ActionListener buttonClickListener = e -> {
            String clickedButtonText = ((JButton) e.getSource()).getText();
            updateDetails(frame, clickedButtonText);
        };

        for (String label : BUTTON_LABELS) {
            JButton button = createGradientButton(label, buttonClickListener);
            frame.add(button);
        }
    }

    private static JButton createGradientButton(String text, ActionListener listener) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                if (g instanceof java.awt.Graphics2D) {
                    java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
                    int h = getHeight();
                    GradientPaint gradient = new GradientPaint(0, 0, new Color(210, 180, 140), 0, h, new Color(180, 150, 110));
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
            }
        };
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(listener);
        return button;
    }

    private static void positionFrameToBottomRight(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPosition = (int) (screenSize.getWidth() - frame.getWidth());
        int yPosition = (int) (screenSize.getHeight() - frame.getHeight());
        frame.setLocation(xPosition, yPosition);
    }

    private static void updateDetails(JFrame frame, String clickedButtonText) {
        if ("Quit".equals(clickedButtonText)) {
            frame.dispose();
            return;
        }

        if ("New Patient for Pregnancy with Thyroid disease".equals(clickedButtonText)) {
            EMR_Preg_CC.main(null);
            return;
        }

        String currentDate = DATE_FORMAT.format(new Date());

        String[] conditions = {
            "F/U Pregnancy with Hyperthyroidism",
            "F/U Pregnancy with Hypothyroidism",
            "F/U Pregnancy with Non thyroidal illness",
            "F/U Pregnancy with TSH elevation",
            "F/U Pregnancy with TSH low"
        };

        for (String condition : conditions) {
            if (condition.equals(clickedButtonText)) {
                String prefix = condition.replace("F/U ", "F/U [   ] weeks    " +  currentDate +"\n\t");
                GDSEMR_frame.setTextAreaText(0, prefix);
                GDSEMR_frame.setTextAreaText(7, "\n  #  " + clickedButtonText + "  [" + currentDate + "]");
                GDSEMR_frame.setTextAreaText(8, "...Plan F/U [   ] weeks\n\t " + condition.replace("F/U ", ""));
                return;
            }
        }
    }
}
