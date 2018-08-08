package contactsManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsIO {
    String newUser;
    public static int itemID;
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);


        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");

        int userInput = scanner.nextInt();

        do {
               //show whole list
            if (userInput == 1) {
                System.out.println("Viewing Contacts");
                showList();
               //Add new name
            } else if (userInput == 2) {
                createNameList();
               //Search for name
            } else if (userInput == 3) {
                System.out.println("Enter name to search for");
                scanner.nextLine();
                String search = scanner.nextLine();
                findName(directory, fileName, search);
                //Delete contact
            } else if (userInput == 4) {
                System.out.println("deleting contact");
                scanner.nextLine();
                String nameToRemove = scanner.nextLine();
                removeContact(directory,fileName,nameToRemove);
                //Exit
            } else {
                System.out.println("Goodbye");

            }
            System.out.println("Enter an option");
            userInput = scanner.nextInt();
        } while (userInput != 5);
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //Show all contacts
    public static void showList(){
        try {
            List<String> contents = Files.readAllLines(
                    Paths.get(directory, fileName)
            );
        } catch (Exception e) {
            System.out.println("Exception!");
            e.printStackTrace();
        }
        try {
            List<String> contents = Files.readAllLines(Paths.get("contacts/contacts.txt"));
            System.out.printf("Name   |  Phone Number\n" +
                    "------------------------\n");
            for (String item :contents){
                System.out.println("  "+item + "  ");
            }

        } catch (IOException e) {
            System.out.println("Exception!");
            e.printStackTrace();
        }
    }

    //method adding/deleting new contact based on user input
    public static void createNameList() {
        Scanner scanner = new Scanner(System.in);
        Path path = Paths.get("contacts", "contacts.txt");

        //Ask user to enter new contact name
        System.out.println("Enter new user: ");
        String newName = scanner.nextLine();
        List<String> names = new ArrayList<>();
        names.add(newName);

        //Enter new phone number
        System.out.println("Enter a phone number: ");
        String phoneNumber = scanner.nextLine();
        List<String> nums = new ArrayList<>();
        nums.add(phoneNumber);


        //Store new user to variable
//        String newUser = newName  +" | "+ phoneNumber + "\n";
        String newUser = String.format(newName + " | " + phoneNumber + "\n");

        //Write new variable to contacts.txt


        try {
            Files.write(path, Arrays.asList(newUser + "\n"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//method to search by contact name
    public static boolean findName(String directory, String fileName, String search) throws IOException{
        search = search.toLowerCase();
        Path path = Paths.get(directory, fileName);
        List<String> list = Files.readAllLines(path);

        for (String item : list) {
            String lowerCaseItem = item.toLowerCase();
            if(lowerCaseItem.contains(search)) {
                System.out.println(item);
                return true;
            }
        };
        System.out.println("Contact does not exist");
        return false;
    }
    //Re-writing the list
    public static void addContactstoFile(List<String> list, String dir, String filename) throws IOException {
        Path filepath = Paths.get(dir, filename);
        Files.write(filepath, list);
    }
    //Delete Contact
    public static void removeContact(String directory, String fileName, String nameToRemove) throws IOException {
        Path path = Paths.get(directory, fileName);
        List<String> list = Files.readAllLines(path);
        itemID = (-1);
        for (String item : list) {
            if (item.contains(nameToRemove)) {
                itemID = list.indexOf(item);
            }
        }
        if(itemID==(-1)){
            System.out.println("Contact by that name doesn't exist");
        }else{
            list.remove(itemID);
            System.out.println( "Contact was deleted");
        }

        addContactstoFile(list,directory,fileName);
        System.out.println();
    }
}



