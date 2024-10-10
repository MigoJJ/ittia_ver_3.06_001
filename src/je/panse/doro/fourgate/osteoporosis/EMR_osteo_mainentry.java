package je.panse.doro.fourgate.osteoporosis;

// Import the necessary classes from other packages
import je.panse.doro.samsara.EMR_OBJ_XrayGFS.EMR_DEXA;

public class EMR_osteo_mainentry {
    
    // Main method to execute the application
    public static void main(String[] args) {
        // Calling the main method of the EMR_DEXA class
        EMR_DEXA.main(null);
        
        // Calling the main method of the EMR_Osteo_westpanel class
        EMR_Osteo_westpanel.main(null);
    }
}
