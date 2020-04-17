import java.awt.event.KeyEvent;
import java.io.*;
import java.security.Key;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherApp {

    private Scanner scan;
    private Cipher cipher;

    private int cipherType = 1; //1 = caesar, 2 = keyed and 3 = vigenere

    private String keyFile = "key.txt";
    private String plainTextFile;
    private String cipherTextFile;

    public static void main(String[] args) {

        CipherApp app = new CipherApp();
        app.initialise();

    }

    private void initialise() {
        cipher = new Caesar();
        menu();
    }

    private void menu() {

        int response;
        scan = new Scanner(System.in);

        do {
            cipherCheck(cipher);
            printMenu();

            while (true) {
                try {
                    System.out.println("Choose an option: ");
                    response = scan.nextInt();
                    if (response > 0 && response < 15)
                        break;
                } catch (InputMismatchException e) {
                    System.err.println("Please enter a number");
                    scan.next();
                }
            }

            switch (response) {
                case 1:
                    cipher = new Caesar();
                    break;
                case 2:
                    cipher = new Keyed();
                    break;
                case 3:
                    cipher = new Vigenere();
                    break;
                case 4:
                    editKey();
                    break;
                case 5:
                    showKey();
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:

                    break;
                case 12:

                    break;
                case 13:

                    break;
                case 14:
                    System.out.println("EXECUTION FINISHED");
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (response != 14);
    }

    private void printMenu() {
        System.out.println("----CIPHER SELECTOR----");
        System.out.println("1 - Use Caesar cipher");
        System.out.println("2 - Use Keyed Caesar cipher");
        System.out.println("3 - Use Vigenere Cipher\n");
        System.out.println("----MODIFY INPUT VALUES----");
        System.out.println("4 - Edit key");
        System.out.println("5 - Display key");
        System.out.println("6 - Input plain text file");
        System.out.println("7 - Input cipher text file\n");
        System.out.println("----OPERATIONS----");
        System.out.println("8 - Encrypt");
        System.out.println("9 - Display cipher text file");
        System.out.println("10 - Save cipher text");
        System.out.println("11 - Decrypt");
        System.out.println("12 - Display plain text");
        System.out.println("13 - Save plain text");
        System.out.println("14 - Quit");
    }


    private void cipherCheck(Cipher obj) {

        String cipher;

        if (obj instanceof Caesar) {
            cipherType = 1;
            cipher = "Caesar";
        } else if (obj instanceof Keyed) {
            cipherType = 2;
            cipher = "Keyed";
        } else {
            cipherType = 3;
            cipher = "Vigenere";
        }

        System.out.println("Current cipher: " + cipher);
    }

    private void editKey() {

        scan = new Scanner(System.in);
        int exitControl = 0;
        String key;

        do {
            System.out.println("Enter key value: ");
            key = scan.nextLine();

            //checks depending on cipher type
            switch (cipherType) {
                case 1:
                    //check the key is just a number
                    if (key.matches("[0-9]+")) {
                        exitControl = 1;
                        break;
                    } else {
                        System.err.println("Enter just a number");
                        break;
                    }
                case 2:
                    //check the key contains a shift number + something else(string key)
                    int firstLetter = 0;
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < key.length(); i++){
                        if(!Character.isDigit(key.charAt(i))) {
                            firstLetter = i;
                            break;
                        }
                    }
                    for (int i = 0; i < firstLetter; i++){
                        sb.append(key.charAt(i));
                    }

                    String shift = sb.toString();
                    if (firstLetter != 0 && key.length() > shift.length()) {
                        exitControl = 1;
                        break;
                    } else {
                        System.err.println("Enter a number followed by a textual key");
                        break;
                    }
                case 3:
                    //check the key contains just chars from a-z A-Z
                    if(key.matches("[a-zA-Z]+")){
                        exitControl = 1;
                        break;
                    } else{
                        System.err.println("Enter just letters a-z");
                        break;
                    }
            }
        } while (exitControl == 0);

        //EDIT IN FILE
        try {
            File file = new File(keyFile);
            PrintWriter outfile = new PrintWriter(file);
            outfile.println(key);
            outfile.close();

        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + keyFile);
            System.err.println(e.getMessage());
        }

        //EDIT CIPHER CLASS KEY
        cipher.setKey(key);

        //------> change to: 1-cipher.setKey(key) 2-cipher.updateKey
    }

    private void showKey() {
        try {
            File file = new File(keyFile);
            Scanner infile = new Scanner(file);

            String read = infile.nextLine();
            System.out.println("Key: " + read);
            infile.close();

        } catch (FileNotFoundException e) {
            System.err.println("The file: " + keyFile + " does not exist");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + keyFile);
            System.err.println(e.getMessage());
        }
    }






}
