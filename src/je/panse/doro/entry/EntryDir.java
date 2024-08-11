package je.panse.doro.entry;

import java.io.File;

public class EntryDir {
    public static final String currentDir = System.getProperty("user.dir");
    public static final String homeDir;
    public static final String backupDir;

    static {
        // Check if the application is in production or development environment
        String env = System.getProperty("app.env", "dev"); // Default to development environment
        if ("prod".equals(env)) {
            homeDir = currentDir + File.separator + "je" + File.separator + "panse" + File.separator + "doro";
        } else {
//            homeDir = currentDir + File.separator + "src" + File.separator + "je" + File.separator + "panse" + File.separator + "doro";
            homeDir = currentDir + File.separator + "je" + File.separator + "panse" + File.separator + "doro";

        }
        backupDir = homeDir + File.separator + "tripikata" + File.separator + "rescue";
    }

    public static void main(String[] args) {
        // Ensure that the home directory exists
        createDirectoryIfNotExists(homeDir);
        // Ensure that the backup directory exists
        createDirectoryIfNotExists(backupDir);

        // Output the directories for verification
        System.out.println("Current user's directory: " + currentDir);
        System.out.println("Home Directory: " + homeDir);
        System.out.println("Backup Directory: " + backupDir);
    }

    // Utility method to create a directory if it does not exist
    private static void createDirectoryIfNotExists(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            boolean wasSuccessful = dir.mkdirs();
            if (wasSuccessful) {
                System.out.println("Successfully created directory: " + directoryPath);
            } else {
                System.out.println("Failed to create directory: " + directoryPath);
            }
        } else {
            System.out.println("Directory already exists: " + directoryPath);
        }
    }
}
