package je.panse.doro.soap.pmh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class EMRPMH extends JFrame {
    private JTextArea textArea1, textArea2;
    private JButton clearButton, copyButton, saveButton, quitButton;
    private JCheckBox[] checkBoxes;
    private String[] conditions = {
        "Diabetes Mellitus", "Hypertension", "Dyslipidemia",
        "Cancer", "Operation", "Thyroid Disease",
        "Asthma", "Pneumonia", "Tuberculosis",
        "Chronic Hepatitis B", "GERD", "Gout",
        "Arthritis", "Hearing Loss", "Parkinson's Disease",
        "CVA", "Depression", "Cognitive Disorder",
        "Angina Pectoris", "AMI", "Arrhythmia",
        "Allergy", "...", "Food",
        "Injection", "Medication"
    };

    public EMRPMH() {
        setTitle("EMR PMH");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Text areas initialization
        textArea1 = new JTextArea(5, 20);
        textArea2 = new JTextArea(10, 50);
        setupTextArea(textArea1);
        setupTextArea(textArea2);
        textArea2.setEditable(true);  // textArea2 for display only
        setTextArea2Content();  // Set the predefined text

        // Checkboxes setup
        JPanel checkBoxPanel = new JPanel(new GridLayout(0, 3));  // 3 columns
        checkBoxes = new JCheckBox[conditions.length];
        for (int i = 0; i < conditions.length; i++) {
            checkBoxes[i] = new JCheckBox(conditions[i]);
            checkBoxes[i].addItemListener(new CheckBoxItemListener());
            checkBoxPanel.add(checkBoxes[i]);
        }

        // Buttons setup
        clearButton = new JButton("Clear");
        copyButton = new JButton("Copy");
        saveButton = new JButton("Save");
        quitButton = new JButton("Quit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearButton);
        buttonPanel.add(copyButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        // Main layout setup
        JPanel textAreaPanel = new JPanel(new GridLayout(2, 1));
        textAreaPanel.add(new JScrollPane(textArea1));
        textAreaPanel.add(new JScrollPane(textArea2));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(textAreaPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(checkBoxPanel), BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        configureButtonActions();
    }

    private void setupTextArea(JTextArea textArea) {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    private void setTextArea2Content() {
        textArea2.setText(
            "     □ Diabetes Mellitus     \t□ Hypertension     \t□ Dyslipidemia \n" +
            "     □ Cancer                \t□ Operation        \t□ Thyroid Disease\n" +
            "     □ Asthma                \t□ Pneumonia        \t□ Tuberculosis\n" +
            "     □ Chronic Hepatitis B   \t□ GERD             \t□ Gout\n" +
            "     □ Arthritis             \t□ Hearing Loss     \t□ Parkinson's Disease\n" +
            "     □ CVA                   \t□ Depression       \t□ Cognitive Disorder\n" +
            "     □ Angina Pectoris       \t□ AMI              \t□ Arrhythmia\n" +
            "     □ Allergy               \t□ ...              \n" +
            "     □ Food                  \t□ Injection        \t□ Medication\n"
        );
    }


    private void configureButtonActions() {
        clearButton.addActionListener(this::clearAction);
        copyButton.addActionListener(this::copyAction);
        saveButton.addActionListener(this::saveAction);
        quitButton.addActionListener(e -> System.exit(0));
    }

    private void clearAction(ActionEvent e) {
        textArea1.setText("");
        textArea2.setText("");  // Clear both text areas
        setTextArea2Content();  // Reset predefined text
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
        }
    }

    private void copyAction(ActionEvent e) {
        StringSelection stringSelection = new StringSelection(
            textArea1.getText() + "\n" + textArea2.getText() + "\nSelected Conditions:\n" +
            String.join("\n", getSelectedConditions())
        );
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    private void saveAction(ActionEvent e) {
        System.out.println("Details from TextArea1: " + textArea1.getText());
        System.out.println("Details from TextArea2: " + textArea2.getText());
        System.out.println("Selected Conditions:\n" + String.join("\n", getSelectedConditions()));
    }

    private java.util.List<String> getSelectedConditions() {
        java.util.List<String> selectedConditions = new java.util.ArrayList<>();
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                selectedConditions.add(checkBox.getText());
            }
        }
        return selectedConditions;
    }

    // Listener class for checkboxes
    private class CheckBoxItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox source = (JCheckBox) e.getItem();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                textArea1.append("■ " + source.getText() + "\n");
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                textArea1.setText(textArea1.getText().replace("■ " + source.getText() + "\n", ""));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EMRPMH frame = new EMRPMH();
            frame.setVisible(true);
        });
    }
}
