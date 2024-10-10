package je.panse.doro.samsara.EMR_OBJ_XrayGFS;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import je.panse.doro.GDSEMR_frame;
import je.panse.doro.samsara.comm.datetime.Date_current;

public class EMR_DEXA extends JFrame implements ActionListener {
    
    // Declare GUI components
    private JLabel ageLabel, genderLabel, totLabel, fractureLabel, menopauseLabel, zscoreLabel;
    private JTextField ageTextField, totTextField;
    private JComboBox<String> genderComboBox;
    private JCheckBox fractureCheckBox, menopauseCheckBox;
    private JButton calculateButton, resetButton;
    private JTextArea resultTextArea;
    
    // Constructor
    public EMR_DEXA() {
        // Set frame properties
        setTitle("EMR DEXA Interface");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize GUI components
        ageLabel = new JLabel("Age:");
        genderLabel = new JLabel("Gender:");
        totLabel = new JLabel("T-score  /  Z-Score:");

        zscoreLabel = new JLabel("<html>    - Pediatric patients:<br>" +
                "    - Premenopausal women:<br>"+
                "    - men under 50 years of age:<br>" +
                "    - Monitoring changes over time:</html>");

        fractureLabel = new JLabel("Fracture History:");
        menopauseLabel = new JLabel("Menopause History:");
        ageTextField = new JTextField();
        totTextField = new JTextField();
        Insets insets = new Insets(0, 15, 0, 0); // 10 pixels left margin
        ageTextField.setMargin(insets);
        totTextField.setMargin(insets);

        genderComboBox = new JComboBox<>(new String[]{"Female", "Male","TransGender"});
        
        
        fractureCheckBox = new JCheckBox();
        menopauseCheckBox = new JCheckBox();

        calculateButton = new JButton("Calculate");
        resetButton = new JButton("Reset");
        
        resultTextArea = new JTextArea();
        resultTextArea.setRows(3);
        resultTextArea.setEditable(false);
        
     // Initialize labels
        String[] labelNames = {"Age:", "Gender:","T-score  /  Z-Score:", "Fracture History:", "Menopause History:","Z-score used in   :   "};
        JLabel[] labels = new JLabel[labelNames.length];

        // Add components to frame
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(labelNames[i]);
            labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
            mainPanel.add(labels[i]);
            
            switch(i) {
                case 0:
                    mainPanel.add(ageTextField);
                    break;
                case 1:
                    mainPanel.add(genderComboBox);
                    break;
                case 2:
                    mainPanel.add(totTextField);
                    break;
                case 3:
                    mainPanel.add(fractureCheckBox);
                    break;
                case 4:
                    mainPanel.add(menopauseCheckBox);
                    break;
                case 5:
                    mainPanel.add(zscoreLabel);
                    break;
                default:
                    break;
            }
        }
        
        ageTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
                manager.focusNextComponent();
            }
        });
     // Set up action listener for genderComboBox
        genderComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Move focus to totTextField
                totTextField.requestFocus();
            }
        });
         // Set up action listener for totTextField
       totTextField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Move focus to fractureCheckBox
                    fractureCheckBox.requestFocus();
                }
        });
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        buttonPanel.add(calculateButton);
        buttonPanel.add(resetButton);
        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(resultTextArea, BorderLayout.NORTH);
        
        // Add action listeners to buttons
        calculateButton.addActionListener(this);
        resetButton.addActionListener(this);
        
        // Show frame
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            handleCalculateAction();
        } else if (e.getSource() == resetButton) {
            handleResetAction();
        }
    }

    private void handleCalculateAction() {
        // Extract input data
        int age = Integer.parseInt(ageTextField.getText());
        String gender = genderComboBox.getSelectedItem().toString();
        double totZScore = Double.parseDouble(totTextField.getText());
        boolean fractureHistory = fractureCheckBox.isSelected();
        boolean menopauseHistory = menopauseCheckBox.isSelected();

        // Calculate diagnosis
        String diagnosis = calculateDEXADiagnosis(age, gender, totZScore, fractureHistory,menopauseHistory);

        // Update result text areas
        updateResultTextAreas(diagnosis, age, gender, fractureHistory, menopauseHistory);

        // Dispose current frame
        dispose();
    }

    private void handleResetAction() {
        ageTextField.setText("");
        genderComboBox.setSelectedIndex(0);
        totTextField.setText("");
        fractureCheckBox.setSelected(false);
        menopauseCheckBox.setSelected(false);
        resultTextArea.setText("");
    }

    private void updateResultTextAreas(String diagnosis, int age, String gender, boolean fractureHistory,boolean menopauseHistory) {
        resultTextArea.setText(diagnosis);
        GDSEMR_frame.setTextAreaText(5, "\n< DEXA >\n\t" + diagnosis);
        String cdate = Date_current.main("d");

        String fractureStatus = fractureHistory ? "[+]" : "none";
        String menopauseStatus = menopauseHistory ? "[+]" : "none";

        
        GDSEMR_frame.setTextAreaText(5, String.format("\n   Age : [%d]  Gender : [%s]  Fracture : %s  Menopause : %s", age, gender, fractureStatus,menopauseStatus));
        
        GDSEMR_frame.setTextAreaText(9, "\n#  " + diagnosis + "   " + cdate);
    }


    private String calculateDEXADiagnosis(int age, String gender, double totZScore, boolean fractureHistory, boolean menopauseHistory) {
        String scoreType;
        if (age > 50 && gender.equalsIgnoreCase("Male") || (gender.equalsIgnoreCase("Female") && menopauseHistory)) {
            scoreType = "T-score";
            return calculateDEXADiagnosisT(totZScore);
            
        } else {
            scoreType = "Z-score";
            return calculateDEXADiagnosisD(totZScore);
        }
    }
        

    private String calculateDEXADiagnosisT(double totZScore) {
        String diagnosis = "";
        if (totZScore <= -2.5) {
            diagnosis = "Osteoporosis";
        } else if (totZScore < -1.0) {
            diagnosis = "Low bone mass or osteopenia";
        } else {
            diagnosis = "Normal bone density";
        }
        return diagnosis  + " T-score [ "+ totZScore +" ]";    }

    private String calculateDEXADiagnosisD(double totZScore) {
        String diagnosis = "";
        if (totZScore <= -2.0) {
            diagnosis = "Osteoporosis";
        } else if (totZScore < 0.0) {
            diagnosis = "Low bone mass or osteopenia";
        } else {
            diagnosis = "Normal bone density";
        }
        return diagnosis  + " Z-score [ "+ totZScore + " ]";
    }

    // Main method to launch interface
    public static void main(String[] args) {
        new EMR_DEXA();
    }
}