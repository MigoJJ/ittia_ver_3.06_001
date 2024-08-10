package je.panse.doro.support.sqlite3_manager.abbreviation.setfindedit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import je.panse.doro.entry.EntryDir;

public class DatabaseExtractStrings {

  private static String dbURL = "jdbc:sqlite:" + EntryDir.homeDir + "/support/sqlite3_manager/abbreviation/AbbFullDis.db";

  public static void main(String[] args) throws IOException {
    // Extract data from the database
    String[][] data = extractData();

    // Save extracted data to a file
    saveToFile(data, "extracteddata.txt");

    // Print confirmation message (optional)
    System.out.println("Data saved to extracteddata.txt");
  }

  private static void saveToFile(String[][] data, String filename) throws IOException {
       String filePath = EntryDir.homeDir + "/support/sqlite3_manager/abbreviation/" + "extracteddata.txt"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {      for (String[] entry : data) {
        writer.write("replacements.put( \"" + entry[0] + " \" , \"" + entry[1] + " \");\n");
      }
    }
  }

  private static String[][] extractData() {
    List<String[]> dataList = new ArrayList<>();
    String sql = "SELECT abbreviation, full_text FROM abbreviations";

    try (Connection conn = DriverManager.getConnection(dbURL);
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        String abbreviation = rs.getString("abbreviation");
        String fullText = rs.getString("full_text");
        dataList.add(new String[]{abbreviation, fullText});
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    // Convert List to String[][]
    String[][] dataArray = new String[dataList.size()][2];
    return dataList.toArray(dataArray);
  }
}
