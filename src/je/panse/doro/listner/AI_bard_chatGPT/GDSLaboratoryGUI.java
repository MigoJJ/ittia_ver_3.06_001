package je.panse.doro.listner.AI_bard_chatGPT;

import java.awt.*;		
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GDSLaboratoryGUI extends JFrame implements ActionListener {

    private static final JTextArea inputTextArea = new JTextArea(40, 35);
    private static final JTextArea outputTextArea = new JTextArea(40, 35);
    private static String[] centerButtonLabels = {"A>", "Lab>","EMR Summary","Modify ..."};
    private static String[] eastButtonLabels = {"Rescue","Copy to Clipboard", "Clear Input", "Clear Output", "Clear All", "Save and Quit"};
    private JButton[] centerButtons;
    private static final String bardorderlab = """
            make table
            if parameter does not exist -> remove the row;
            Parameter Value Unit 
            using value format 
            """;

    private static final String bardorderlist = """
            modify the data using format like below data sample;
            format sample data starting-------------
            #1  Neurological
                  - Pituitary nonfunctioning tumor (surgery performed) (2016)
                  - Optic nerve deterioration (2021)
                      > CT and MRI: normal
                      > MRI: CVA[+]
            #2  disease category
            #3  disease category
            format sample data finishing----------------
            organize and make summary list using format;
            the list sorted by disease category;
            """;

    private static final String bardorderpro = """
            the dataset finished --------------------------
            make problem list and comment;
            PMH>    -> Past Medical history;
            ▣   ->  The Patient has suffered from
            □   ->  The Patient has  not suffered from
            ▲     -> upper value for reference
            ▼     -> lower value for reference
            if the problem list is "None" -> remove;
            format will be required ;
            indentation and prefix   "    # ."  and  "        -   . ";
            problem sample list is;
            starting------------------------------
            ***  Problem List   ***********************
            #1  Cardiovascular
                  -  ... (2006-02-17 ~ Present)
            #2  Endocrinology
                  -  ...   
                  -  ...   
                  -  ...   
            #2  Hepatic
                 -  ...
            #3  Musculoskeletal
                 -  ... (surgery performed 2019)
            #4  Substance Use
                 -  ...
            #5  Neurological
                 -  ... (2023-10)
            #6  Comment
                  -  ...   
                  -  ...   
                  -  ...   
            finishing-------------------------------
            """;
    
    public GDSLaboratoryGUI() {
        setupFrame();
        setupTextAreas();
        setupButtons();
        arrangeComponents();
    }

    private void setupFrame() {
        setTitle("GDS Bard chatGPT4.0");
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color backgroundColor = new Color(0xffdfba); // convert hex to Color object
        inputTextArea.setBackground(backgroundColor);
        outputTextArea.setBackground(backgroundColor);
        inputTextArea.setBorder(BorderFactory.createRaisedBevelBorder());
        outputTextArea.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void setupTextAreas() {
        outputTextArea.setEditable(true);
    }

    public static void appendTextAreas(String value) {
        outputTextArea.append(value);      
    }

    private void setupButtons() {
        centerButtons = new JButton[centerButtonLabels.length];
        for (int i = 0; i < centerButtonLabels.length; i++) {
            centerButtons[i] = createButton(centerButtonLabels[i]);
        }
    }

    private void arrangeComponents() {
        // Create the panels for the buttons
        JPanel centerButtonPanel = createButtonPanel(centerButtonLabels);
        JPanel eastButtonPanel = createButtonPanelWithStruts(eastButtonLabels);

        // Adjust button dimensions for uniformity
        setUniformButtonDimensions(centerButtonPanel, eastButtonPanel);

        // Main content panel using GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        addComponentsToContentPanel(contentPanel);

        // Set layout and add main components
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(eastButtonPanel, BorderLayout.EAST);
    }

    private JPanel createButtonPanel(String[] labels) {
        JPanel panel = new JPanel(new FlowLayout());
        for (String label : labels) {
            JButton button = createButton(label);
            panel.add(button);
        }
        return panel;
    }

    private JPanel createButtonPanelWithStruts(String[] labels) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < labels.length; i++) {
            JButton button = createButton(labels[i]);
            panel.add(button);
            if (i != labels.length - 1) { // Avoid adding a strut after the last button
                panel.add(Box.createVerticalStrut(5));
            }
        }
        return panel;
    }

    private void setUniformButtonDimensions(JPanel... panels) {
        int maxWidth = 0;
        int fixedHeight = 40; // Fixed height in pixels

        // Determine the maximum width across all buttons
        for (JPanel panel : panels) {
            for (Component component : panel.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    maxWidth = Math.max(maxWidth, button.getPreferredSize().width);
                    button.setBorder(BorderFactory.createLoweredBevelBorder());
                }
            }
        }

        // Set all buttons to the maximum width and fixed height
        Dimension size = new Dimension(maxWidth, fixedHeight);
        for (JPanel panel : panels) {
            for (Component component : panel.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    button.setPreferredSize(size);
                    button.setMaximumSize(size);
                }
            }
        }
    }

    private void addComponentsToContentPanel(JPanel contentPanel) {
        addComponent(contentPanel, new JLabel("Input Data:"), 0, 0, GridBagConstraints.NORTH);
        addComponent(contentPanel, new JScrollPane(inputTextArea), 1, 0, GridBagConstraints.BOTH);
        addComponent(contentPanel, new JLabel("Output Data:"), 2, 0, GridBagConstraints.NORTH);
        addComponent(contentPanel, new JScrollPane(outputTextArea), 3, 0, GridBagConstraints.BOTH);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 4; 
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        contentPanel.add(createButtonPanel(centerButtonLabels), constraints);
    }

    private void addComponent(JPanel contentPanel, JScrollPane jScrollPane, int i, int j, int both) {
		// TODO Auto-generated method stub
		
	}

	private void addComponent(JPanel contentPanel, JLabel jLabel, int i, int j, int north) {
		// TODO Auto-generated method stub
		
	}

	private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        	case "A>" -> modifyActionlist();
            case "Lab>" -> modifyActionlab();
            case "EMR Summary" -> modifyActionpro();
            case "Copy to Clipboard" -> copyToClipboardAction();
            case "Clear Input" -> inputTextArea.setText("");
            case "Clear Output" -> outputTextArea.setText("");
            case "Clear All" -> {
                inputTextArea.setText("");
                outputTextArea.setText("");
            }
            case "Save and Quit" -> {
	            inputTextArea.setText("");
	            outputTextArea.setText("");
	            dispose();
            }
        }
    }

    private void modifyActionlab() {
        String textFromInputArea = inputTextArea.getText();
        outputTextArea.append("\n" + bardorderlab);
        outputTextArea.append(""
        		+ "\nthe below contents are data --------------------------\n" 
        		+ textFromInputArea 
        		+ "\nthe dataset finished --------------------------\n"
        		+ "\nmerge parameters like below;\n"
        		+ "\ndo not calculate between values;\n"
        		+ "\nthe row titles ;----------------------\n");

        GDSLaboratoryDataModify.main(textFromInputArea);
        copyToClipboardAction();
    }
    
    private void modifyActionlist() {
        String textFromInputArea = inputTextArea.getText();
        outputTextArea.append(""
        		+ "\nthe below contents are data --------------------------\n" 
        		+ textFromInputArea 
        		+ "\nthe dataset finished --------------------------\n");
        outputTextArea.append("\n" + bardorderlist);
        copyToClipboardAction();
    }

    private void modifyActionpro() {
        String textFromInputArea = inputTextArea.getText();
        outputTextArea.append(""
        		+ "\nthe below contents are data --------------------------\n" 
        		+ textFromInputArea 
        		+ "\n");
        outputTextArea.append("\n" + bardorderpro);
        copyToClipboardAction();
    }
    
    private void copyToClipboardAction() {
        String textToCopy = outputTextArea.getText();
        StringSelection selection = new StringSelection(textToCopy);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GDSLaboratoryGUI2 gui = new GDSLaboratoryGUI2();
            gui.setVisible(true);
        });
    }
}