package je.panse.doro.chartplate;

import java.awt.event.KeyEvent;

import je.panse.doro.fourgate.EMR_FU_diabetesEdit;
import je.panse.doro.fourgate.EMR_FU_hypercholesterolemiaEdit;
import je.panse.doro.fourgate.EMR_FU_hypertensionEdit;
import je.panse.doro.fourgate.thyroid.entry.EMR_thyroid_mainentry;
import je.panse.doro.fourgate.thyroid.pregnancy.EMR_thyroid_Pregnancyentry;

public class GDSEMR_FunctionKey {

    // Static method to handle function key action
    public static void handleFunctionKeyAction(int textAreaIndex, String functionKeyMessage, int keyCode) {
        // Print the function key action to the console
        System.out.println(functionKeyMessage);

        // Check if the key code corresponds to F1, F2, F3, or F4
        if (keyCode == KeyEvent.VK_F1) {
            // Set the specific text in the text area at index 8 if F1, F2, F3, or F4 is pressed
            EMR_FU_diabetesEdit.main(null);
        } else if (keyCode == KeyEvent.VK_F2) {
        	EMR_FU_hypertensionEdit.main(null);
        } else if (keyCode == KeyEvent.VK_F3) {
        	EMR_FU_hypercholesterolemiaEdit.main(null);
        } else if (keyCode == KeyEvent.VK_F4) {
        	EMR_thyroid_mainentry.main(null);
        	EMR_thyroid_Pregnancyentry.main(null);

        } else if (keyCode == KeyEvent.VK_F9) {
            je.panse.doro.GDSEMR_frame.setTextAreaText(8, "\n...follow - up without medications [ :cd ] \n...");
        } else if (keyCode == KeyEvent.VK_F10) {
            je.panse.doro.GDSEMR_frame.setTextAreaText(8, "\n...continuous current prescription [ :cd ] \n...");
        } else if (keyCode == KeyEvent.VK_F11) {
            je.panse.doro.GDSEMR_frame.setTextAreaText(8, "\n...increased dose of current medications [ :cd ] \n...");    
        } else if (keyCode == KeyEvent.VK_F12) {
            je.panse.doro.GDSEMR_frame.setTextAreaText(8 , "\n...change current medications [ :cd ]\n...");    
        } else {
            // Set the text in the specified text area based on the passed index and message
            je.panse.doro.GDSEMR_frame.setTextAreaText(keyCode, functionKeyMessage);
        }
    }
}
