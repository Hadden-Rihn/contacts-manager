package contactsManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.ExportException;
import java.util.*;

public class ContactsIO {


    public static void main(String[] args) throws IOException{

    Scanner scanner = new Scanner(System.in);


        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");

        int userInput = scanner.nextInt();

        do {
            if(userInput == 1) {
                System.out.println("Viewing Contacts");
                try{
                    List<String>contents= Files.readAllLines(
                    Paths.get(directory,fileName)
                    );
                }catch(Exception e){
                    System.out.println("Exception!");
                    e.printStackTrace();
                }
                try{
                    List<String> contents = Files.readAllLines(Paths.get("contacts/contacts.txt"));
                    System.out.println(contents.toString());

                }catch(IOException e){
                    System.out.println("Exception!");
                    e.printStackTrace();
                }

            } else if (userInput == 2){
                System.out.println("Enter new contact");

            } else if(userInput == 3) {
                System.out.println("searching contacts");

            } else if(userInput == 4) {
                System.out.println("deleting contact");

            } else {
                System.out.println("Goodbye");

            }
            System.out.println("Enter an option");
            userInput = scanner.nextInt();

        }while(userInput != 5);
    }

    //method that returns a list of all contacts

    public static String directory = "contacts";
    public static String fileName = "contacts.txt";


    public static void createFileDirectory(String directory, String fileName) {
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, fileName);

        try {
            if (Files.notExists(dataDirectory) && Files.notExists(dataFile)) {
                Files.createDirectories(dataDirectory);
                Files.createFile(dataFile);
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

    //method adding/deleting new contact based on user input

    //method to search by contact name

    //Exit

