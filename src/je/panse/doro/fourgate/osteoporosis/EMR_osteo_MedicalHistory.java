package je.panse.doro.fourgate.osteoporosis;

import java.awt.*;
import javax.swing.*;

public class EMR_osteo_MedicalHistory {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Medical History Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1000);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Create a panel for checkboxes and text fields in the center
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        // Create a text area for the SOUTH panel
        JTextArea southTextArea = new JTextArea(5, 40);
        southTextArea.setLineWrap(true);
        southTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(southTextArea);

        // Define sections and checkboxes using arrays
        String[] sectionLabels1 = {
                "Previous Fractures:", "Fracture Type:", "Chronic Conditions:",
                "Medications:", "Surgical History:"
        };
        String[][] checkBoxLabels1 = {
                {},
                {"Stress", "Pathological", "Other:"},
                {"Rheumatoid arthritis", "Hyperthyroidism", "Cushing's syndrome", "Other:"},
                {"Corticosteroids", "Anticonvulsants", "Proton pump inhibitors", "Other:"},
                {"Hormone-related surgeries (e.g., hysterectomy, oophorectomy)", "Other:"}
        };

        // Generate the sections with checkboxes and text areas dynamically
        for (int i = 0; i < sectionLabels1.length; i++) {
            final String sectionLabel = sectionLabels1[i];

            centerPanel.add(new JLabel(sectionLabel));

            if (checkBoxLabels1[i].length > 0) {
                for (String label : checkBoxLabels1[i]) {
                    JCheckBox checkBox = new JCheckBox(label);
                    centerPanel.add(checkBox);
                    addCheckboxActionListener(checkBox, southTextArea, sectionLabel);

                    if (label.equals("Other:")) {
                        JTextField otherField = new JTextField(20);
                        centerPanel.add(otherField);

                        // Add KeyListener for "Other:" text fields
                        otherField.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                                    String text = otherField.getText().trim();
                                    if (!text.isEmpty()) {
                                        southTextArea.append(sectionLabel + " Other: " + text + "\n");
                                        otherField.setText("");
                                    }
                                    evt.consume();
                                }
                            }
                        });
                    }
                }
            } else {
                JTextArea textArea = new JTextArea(2, 20);
                centerPanel.add(textArea);

                textArea.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                            String text = textArea.getText().trim();
                            if (!text.isEmpty()) {
                                southTextArea.append(sectionLabel + " " + text + "\n");
                                textArea.setText("");
                            }
                            evt.consume();
                        }
                    }
                });
            }
        }

        frame.add(centerPanel, BorderLayout.CENTER);

        // Add buttons in the EAST panel
        JPanel eastPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        addButtonsWithGradient(eastPanel, southTextArea, centerPanel); // Pass centerPanel here
        frame.add(eastPanel, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // Modified addButtonsWithGradient method to accept centerPanel:
    private static void addButtonsWithGradient(JPanel eastPanel, JTextArea southTextArea, JPanel centerPanel) {
        String[] buttonLabels = {"OS#1", "OS#2", "All", "Clear", "Save", "Quit"};

        for (String label : buttonLabels) {
            GradientButton button = new GradientButton(label);
            eastPanel.add(button);

            // Add functionality for specific buttons
            if (label.equals("Clear")) {
                button.addActionListener(e -> {
                    southTextArea.setText(""); // Clear text area

                    // Clear all checkboxes in the centerPanel
                    for (Component c : centerPanel.getComponents()) {
                        if (c instanceof JCheckBox) {
                            ((JCheckBox) c).setSelected(false);
                        }
                        if (c instanceof JTextField) {
                            ((JTextField) c).setText("");
                        }
                    }
                });
            } else if (label.equals("Quit")) {
                button.addActionListener(e -> System.exit(0));
            }
        }
    }

    // Method to append checkbox text to the text area with labels
    private static void addCheckboxActionListener(JCheckBox checkBox, JTextArea textArea, String sectionLabel) {
        checkBox.addActionListener(e -> {
            if (checkBox.isSelected()) {
                textArea.append(sectionLabel + " " + checkBox.getText() + "\n");
            }
        });
    }

    // Custom JButton class for gradient background
    public static class GradientButton extends JButton {
        private final Color color1 = new Color(135, 206, 250);
        private final Color color2 = new Color(0, 0, 139);

        public GradientButton(String text) {
            super(text);
            setForeground(Color.BLACK);
            setFocusPainted(false);
            setFont(new Font("Arial", Font.BOLD, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (!isOpaque()) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
            super.paintComponent(g);
        }
    }
}