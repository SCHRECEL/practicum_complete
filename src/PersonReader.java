import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Person file");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                displayFileContent(selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File selection cancelled by the user.");
        }
    }

    private static void displayFileContent(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        System.out.println(String.format("%-10s%-15s%-15s%-10s%-4s", "ID#", "Firstname", "Lastname", "Title", "YOB"));
        System.out.println("=====================================");

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String formattedLine = String.format("%06d%15s%15s%-20s%4s",
                    Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]);
            System.out.println(formattedLine);
        }

        reader.close();
    }
}
