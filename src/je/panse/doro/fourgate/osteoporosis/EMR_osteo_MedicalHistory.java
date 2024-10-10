package je.panse.doro.fourgate.osteoporosis;

import java.awt.*;
import javax.swing.*;
import je.panse.doro.GDSEMR_frame;

public class EMR_osteo_MedicalHistory {

    private static JFrame frame;
    private static JTextArea southTextArea;

    public static void main(String[] args) {
        // 1. Create and Set Up the Frame
        frame = new JFrame("Medical History Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1000);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);  // Center the frame

        // 2. Create Components
        createMainComponents();

        // 3. Set Frame Visibility
        frame.setVisible(true);
    }

    // Create and organize the main components of the frame
    private static void createMainComponents() {
        // Create the SOUTH TextArea with ScrollPane
        southTextArea = new JTextArea(5, 40);
        southTextArea.setLineWrap(true);
        southTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(southTextArea);

        // Create the CENTER panel with dynamic content
        JPanel centerPanel = createCenterPanel();

        // Create and add buttons to the EAST panel
        JPanel eastPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        addButtonsWithGradient(eastPanel, southTextArea, centerPanel); // Pass centerPanel for "Clear" functionality

        // Add components to the frame
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(scrollPane, BorderLayout.SOUTH);
    }

    // Create the CENTER panel with labels, checkboxes, and text areas
    private static JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        // Define section labels and checkboxes
        String[] sectionLabels = {"Previous Fractures:", "Fracture Type:", "Chronic Conditions:", "Medications:", "Surgical History:"};
        String[][] checkBoxLabels = {
                {}, // No checkboxes for "Previous Fractures"
                {"Stress", "Pathological", "Other:"},
                {"Rheumatoid arthritis", "Hyperthyroidism", "Cushing's syndrome", "Other:"},
                {"Corticosteroids", "Anticonvulsants", "Proton pump inhibitors", "Other:"},
                {"Hormone-related surgeries (e.g., hysterectomy, oophorectomy)", "Other:"}
        };

        // Dynamically generate checkboxes and text fields based on the sections
        for (int i = 0; i < sectionLabels.length; i++) {
            final String sectionLabel = sectionLabels[i];
            centerPanel.add(new JLabel(sectionLabel));

            // If the section has checkboxes
            if (checkBoxLabels[i].length > 0) {
                for (String label : checkBoxLabels[i]) {
                    JCheckBox checkBox = new JCheckBox(label);
                    centerPanel.add(checkBox);
                    addCheckboxActionListener(checkBox, southTextArea, sectionLabel); // Add checkbox action

                    if (label.equals("Other:")) {
                        JTextField otherField = new JTextField(20);
                        centerPanel.add(otherField);
                        addOtherFieldKeyListener(otherField, sectionLabel);
                    }
                }
            } else {
                // For sections with only text input (like "Previous Fractures")
                JTextArea textArea = new JTextArea(2, 20);
                centerPanel.add(textArea);
                addTextAreaKeyListener(textArea, sectionLabel);
            }
        }

        return centerPanel;
    }

    // Add a KeyListener to a JTextField for "Other" fields to append text on Enter key
    private static void addOtherFieldKeyListener(JTextField otherField, String sectionLabel) {
        otherField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    String text = otherField.getText().trim();
                    if (!text.isEmpty()) {
                        southTextArea.append(sectionLabel + " Other: " + text + "\n");
                        otherField.setText("");
                    }
                    evt.consume(); // Prevent default Enter key behavior
                }
            }
        });
    }

    // Add a KeyListener to a JTextArea to append text on Enter key
    private static void addTextAreaKeyListener(JTextArea textArea, String sectionLabel) {
        textArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    String text = textArea.getText().trim();
                    if (!text.isEmpty()) {
                        southTextArea.append(sectionLabel + " " + text + "\n");
                        textArea.setText("");
                    }
                    evt.consume(); // Prevent default Enter key behavior
                }
            }
        });
    }

    // Add buttons with gradient colors to the EAST panel
    private static void addButtonsWithGradient(JPanel eastPanel, JTextArea southTextArea, JPanel centerPanel) {
        String[] buttonLabels = {"OS#1", "OS#2", "All", "Clear", "Save", "Quit"};

        for (String label : buttonLabels) {
            GradientButton button = new GradientButton(label);
            eastPanel.add(button);

            // Button functionalities
            if (label.equals("Clear")) {
                button.addActionListener(e -> {
                    // Clear text area
                    southTextArea.setText("");
                    // Clear all checkboxes and text fields in the centerPanel
                    for (Component c : centerPanel.getComponents()) {
                        if (c instanceof JCheckBox) {
                            ((JCheckBox) c).setSelected(false);
                        } else if (c instanceof JTextField) {
                            ((JTextField) c).setText("");
                        }
                    }
                });
            } else if (label.equals("Save")) {
                button.addActionListener(e -> {
                    String text = southTextArea.getText();
                    // Save to GDSEMR_frame (assuming it has setTextAreaText method)
                    GDSEMR_frame.setTextAreaText(1, "<DEXA Checklist>  \n" + text);
                    frame.dispose();
                });
            } else if (label.equals("Quit")) {
                button.addActionListener(e -> frame.dispose());
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
