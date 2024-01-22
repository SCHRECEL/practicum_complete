import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String id, firstName, lastName, title;
    public static int yearOfBirth;

    private static ArrayList<String> personList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static boolean finished = false;

    public static void main(String[] args) {
        do {
            id = SafeInput.getNonZeroLenString(scanner, "Enter ID");
            firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
            title = SafeInput.getNonZeroLenString(scanner, "Enter Title");
            yearOfBirth = SafeInput.getRangedInt(scanner, "Enter Year of Birth", 1900, 2022);

            String personData = id + "," + firstName + "," + lastName + "," + title + "," + yearOfBirth;
            personList.add(personData);

            System.out.println("Person data added.");

            finished = !SafeInput.getYNConfirm(scanner, "Do you want to enter more persons?");
        } while (!finished);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\data.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String person : personList) {
                writer.write(person, 0, person.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Person data file written!");
            System.out.println("Written to " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
