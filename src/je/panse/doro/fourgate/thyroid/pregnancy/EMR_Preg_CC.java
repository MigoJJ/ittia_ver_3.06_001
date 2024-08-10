package je.panse.doro.fourgate.thyroid.pregnancy;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import je.panse.doro.GDSEMR_frame;

public class EMR_Preg_CC extends JFrame {
    private ArrayList<String> thypreg;
    private JTextField[] textFields;
    private JButton addButton;

    public EMR_Preg_CC() {
        thypreg = new ArrayList<>();
        textFields = new JTextField[5];

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0, 1));

        String[] labels = {"Pregnancy #:", "Weeks:", "Due Date:", "Diagnosis:", "transferred from GY:"};
        for (int i = 0; i < labels.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            JLabel label = new JLabel(labels[i]);
            JTextField textField = new JTextField(10);
            textField.setHorizontalAlignment(SwingConstants.CENTER);
            
            // Add key listener to handle Enter key press
            textField.addKeyListener(new KeyListener() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        int index = -1;
                        for (int j = 0; j < textFields.length; j++) {
                            if (textFields[j] == textField) {
                                index = j;
                                break;
                            }
                        }
                        if (index != -1 && index < textFields.length - 1) {
                            textFields[index + 1].requestFocus();
                        }
                    }
                }

                public void keyTyped(KeyEvent e) {
                }

                public void keyReleased(KeyEvent e) {
                }
            });
            
            textFields[i] = textField;
            panel.add(label);
            panel.add(textField);
            contentPane.add(panel);
        }

        addButton = new JButton("Add Pregnancy");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPregnancyData();
                printPregnancyData();
                dispose();
            }
        });
        contentPane.add(addButton);

        setTitle("Thyroid Pregnancy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }


    private void addPregnancyData() {
        String[] pregnancyData = new String[5];
        for (int i = 0; i < textFields.length; i++) {
            pregnancyData[i] = textFields[i].getText().trim();
        }
        thypreg.add(String.join(",", pregnancyData));
        clearTextFields();
    }

    private void clearTextFields() {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
    }

    private void printPregnancyData() {
        for (String data : thypreg) {
            String[] dataArray = data.split(",");
            if (dataArray.length >= 5) {
                String p0 = dataArray[0];
                String p1 = dataArray[1];
                String p2 = dataArray[2];
                String p3 = dataArray[3];
                String p4 = dataArray[4];
                String pp3 = convertP3ToString(p3);
                String pp4 = convertP4ToString(p4);
                String result = String.format("# %s pregnancy  %s weeks  Due-date %s \n\t%s at %s%n", p0, p1, p2, pp3,pp4);
                System.out.printf(result);
                GDSEMR_frame.setTextAreaText(0, result);
                GDSEMR_frame.setTextAreaText(7, result);
                }
        }
    }
    private String convertP3ToString(String p3) {
		  switch (p3) {
		         case "o":
		         return "Hypothyroidism Diagnosed";
		     case "e":
		         return "Hyperthyroidism diagnosed";
		     case "n":
		         return "TFT abnormality";
		     default:
		         return "존재하지 않는 결과 입니다.";
		     }
		 }

    private String convertP4ToString(String p4) {
			switch (p4) {
			    case "c":
			    	return "청담마리 산부인과";
				case "d":
				    return "도곡함춘 산부인과";
				case "o":
				    return "기타 산부인과";
				default:
				    return "존재하지 않는 산부인과 입니다.";
				}
			}

    public static void main(String[] args) {
        EMR_Preg_CC frame = new EMR_Preg_CC();
        frame.setVisible(true);
    }
}
