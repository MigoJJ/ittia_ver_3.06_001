package je.panse.doro.listner.buttons;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JOptionPane;

import je.panse.doro.GDSEMR_frame;
import je.panse.doro.chartplate.EMR_Write_To_Chartplate;
import je.panse.doro.fourgate.EMR_FU_diabetesEdit;
import je.panse.doro.fourgate.EMR_FU_hypercholesterolemiaEdit;
import je.panse.doro.fourgate.EMR_FU_hypertensionEdit;
import je.panse.doro.fourgate.EMR_FU_mainEdit;
import je.panse.doro.fourgate.EMR_FU_uriEdit;
import je.panse.doro.fourgate.diabetes.EMR_dm_mainentry;
import je.panse.doro.fourgate.influenza.InjectionApp;
import je.panse.doro.fourgate.routinecheck.RoutineCheck;
import je.panse.doro.fourgate.thyroid.entry.EMR_thyroid_mainentry;
import je.panse.doro.fourgate.thyroid.pregnancy.EMR_thyroid_Pregnancyentry;
import je.panse.doro.listner.ai.Mprompt;
import je.panse.doro.listner.buttons.EMR_Backup_Excute.EMR_B_CopyBackup;
import je.panse.doro.listner.buttons.EMR_Backup_Excute.EMR_B_FileListFrame;
import je.panse.doro.listner.buttons.EMR_Backup_Excute.EMR_InputFrame;
import je.panse.doro.samsara.comm.FileGeditToCilpboard;
import je.panse.doro.samsara.comm.OpenOneNotePage;
import je.panse.doro.support.EMR_ittia_support;
import je.panse.doro.support.sqlite3_manager.abbreviation.MainScreen;

public class EMR_ButtonEntry extends GDSEMR_frame {

    public EMR_ButtonEntry() throws Exception {
        super();
    }

    public static void EMR_B_1entryentry(String noButton, String panelLocation) throws Exception {
        if (panelLocation.equals("north")) {
            switch (noButton) {
                case "Rescue":
                    EMR_Write_To_Chartplate.copyToClipboard(tempOutputArea);
                    FileGeditToCilpboard.FileGeditToCilpboard();
                    EMR_B_FileListFrame.main(null);
                    break;
                case "Backup":
                    EMR_InputFrame.main(null);
                	EMR_B_CopyBackup backupHelper = new EMR_B_CopyBackup();
                	backupHelper.saveTextToFile(tempOutputArea.getText());  // Replace 12345 with the actual serial number
                    EMR_B_FileListFrame.main(null);
                    break;

                case "Copy":
                    try {
                        String textToCopy = tempOutputArea.getText();
                        StringSelection stringSelection = new StringSelection(textToCopy);
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(stringSelection, null);
                        JOptionPane.showMessageDialog(null, "Text copied to clipboard!"); // Assuming you use Swing for GUI
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Failed to copy text: " + e.getMessage());
                    }
                    break;
            
                case "CE":
                    for (int index = 1; index <= 7; index++) {
                    textAreas[index].setText("");
                    String inputData = titles[index] + "\t";
                    textAreas[index].setText(inputData);
                }
                break;
                case "Clear":
                    for (int i = 0; i < textAreas.length; i++) {
                        textAreas[i].setText("");
                        String inputData = titles[i] + "\t";
                        textAreas[i].setText(inputData);
                    }
                    break;
                case "Exit":
                	 System.exit(0);
                	  break;


                case "Abbreviation":
                    MainScreen.main(null);
                    break;
                
                case "ittia_support":
                	EMR_ittia_support.main(null);
                    break;

                case "Button 6":
                    break;
                case "Button 7":
                    break;
                case "Button 8":
                    break;
                case "Button 9":
                    break;
                case "ittia_EMR_AI":
                	Mprompt.main(null);
                    break;
                default:
                    System.out.println("Invalid Button title");
                    break;
            }
        }
        if (panelLocation.equals("south")) {
            switch (noButton) {
                case "F/U DM":
                	  EMR_FU_diabetesEdit.main(null);
               	  EMR_dm_mainentry.main(null);                	  
                    break;
                    
                case "F/U HTN":
                	   EMR_FU_hypertensionEdit.main(null);
                    break;

                case "F/U Chol":
                    EMR_FU_hypercholesterolemiaEdit.main(null);
                    
                    break;
                case "F/U Thyroid":
                    EMR_thyroid_mainentry.main(null);
                    EMR_thyroid_Pregnancyentry.main(null);
                    
                    break;
                case "URI":
                    EMR_FU_uriEdit.main(null);
                    break;
                case "Injections":
                	InjectionApp.main(null);
                    break;
                case "GDS RC":
                	RoutineCheck.performGDSRoutineCheck();
                    break;
                case "공단검진":
                	RoutineCheck.performHCRoutineCheck();
                    break;
                case "DM retinopathy":
                    OpenOneNotePage.main(null);
                    break;
                case "F/U Edit":
                    System.out.println("southsouthsouth 1 1 1 ~~!!");
                    EMR_FU_mainEdit.main(null);
                    break;
                default:
                    System.out.println("I do not recognize this button");
            }
        }
    }
}
