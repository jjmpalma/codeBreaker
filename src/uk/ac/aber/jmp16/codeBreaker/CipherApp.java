package uk.ac.aber.jmp16.codeBreaker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherApp {

    private Scanner scan;
    private Cipher cipher;
    private int cipherType = 0; //0 = caesar, 1 = keyed and 2 = vigenere
    private String[] keyFiles = {"keys/caesarKey.txt", "keys/keyedKey.txt", "keys/vigenereKey.txt"};

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

            //Set up key from file. When format is not correct ask for a new key
            String key = readFromFile(keyFiles[cipherType]);
            if (cipher.checkKey(key))
                cipher.setKey(key);
            else {
                System.err.println("The key file in the following route: " + keyFiles[cipherType] + " contains an incorrect key format");
                editKey();
            }
            printMenu();

            while (true) {
                try {
                    System.out.println("Choose an option: ");
                    response = scan.nextInt();
                    if (response > 0 && response < 13)
                        break;
                } catch (InputMismatchException e) {
                    System.err.println("Please enter a number");
                    scan.next();
                }
            }

            switch (response) {
                case 1:
                    selectCipher();
                    break;
                case 2:
                    editKey();
                    break;
                case 3:
                    inputPlainText();
                    break;
                case 4:
                    inputCipherText();
                    break;
                case 5:
                    displayKey();
                    break;
                case 6:
                    displayPlainText();
                    break;
                case 7:
                    displayCipherText();
                    break;
                case 8:
                    savePlainText();
                    break;
                case 9:
                    saveCipherText();
                    break;
                case 10:
                    cipher.Encrypt();
                    System.out.println("ENCRYPTION DONE!");
                    break;
                case 11:
                    cipher.Decrypt();
                    System.out.println("DECRYPTION DONE!");
                    break;
                case 12:
                    System.out.println("EXECUTION FINISHED");
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (response != 12);
    }

    private void printMenu() {

        System.out.println("-->OPTIONS------------------------");
        System.out.println("1 - Select cipher type");
        System.out.println("-->INPUT VALUES-------------------");
        System.out.println("2 - Edit key");
        System.out.println("3 - Input plain text file");
        System.out.println("4 - Input cipher text file");
        System.out.println("-->DISPLAY VALUES-----------------");
        System.out.println("5 - Display key");
        System.out.println("6 - Display plain text");
        System.out.println("7 - Display cipher text");
        System.out.println("-->SAVE---------------------------");
        System.out.println("8 - Save plain text to file");
        System.out.println("9 - Save cipher text to file");
        System.out.println("-->ENCRYPTION---------------------");
        System.out.println("10 - Encrypt");
        System.out.println("11 - Decrypt");
        System.out.println("----------------------------------");
        System.out.println("12 - Quit");
    }

    private void cipherCheck(Cipher obj) {
        String cipher;

        if (obj instanceof Caesar) {
            cipherType = 0;
            cipher = "Caesar";
        } else if (obj instanceof Keyed) {
            cipherType = 1;
            cipher = "Keyed";
        } else {
            cipherType = 2;
            cipher = "Vigenere";
        }

        System.out.println("Current cipher: " + cipher);
    }

    private void selectCipher() {

        int option;
        scan = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Select one of the next options:");
                System.out.println("1 - Use Caesar cipher \n2 - Use Keyed Caesar cipher \n3 - Use Vigenere Cipher ");
                option = scan.nextInt();
                scan.nextLine();
                if (option > 0 && option < 4)
                    break;
            } catch (InputMismatchException ie) {
                System.err.println("Please enter only numbers");
                scan.next();
            }
        }

        switch (option) {
            case 1:
                cipher = new Caesar();
                break;
            case 2:
                cipher = new Keyed();
                break;
            case 3:
                cipher = new Vigenere();
                break;
        }
    }

    private void editKey() {

        scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter key value: ");
            String key = scan.nextLine();

            if (cipher.checkKey(key)) {
                key = key.toUpperCase();
                cipher.setKey(key);
                saveToFile(key, keyFiles[cipherType]);
                break;
            } else {
                switch (cipherType) {
                    case 0:
                        System.err.println("Enter just numeric digits");
                        break;
                    case 1:
                        System.err.println("Enter a number followed by a textual key from A-Z");
                        break;
                    case 2:
                        System.err.println("Enter just letters a-z");
                        break;
                }
            }
        }
    }

    private void inputPlainText() {

        scan = new Scanner(System.in);
        String plainText;

        System.out.println("Enter the Plain text file name:");
        String fileName = scan.nextLine();

        plainText = readFromFile(fileName);
        plainText = processText(plainText);

        cipher.setPlainText(plainText);
    }

    private void inputCipherText() {

        scan = new Scanner(System.in);
        String cipherText;

        System.out.println("Enter the Cipher text file name:");
        String fileName = scan.nextLine();

        cipherText = readFromFile(fileName);
        cipherText = processText(cipherText);

        cipher.setCipherText(cipherText);
    }

    private void displayKey() {


        String key = readFromFile(keyFiles[cipherType]);
        System.out.println("Key: " + key);
    }

    private void displayPlainText() {

        if (cipher.getPlainText() == null)
            System.err.println("Plain text has not been input by the user");
        else
            System.out.println(cipher.getPlainText());
    }

    private void displayCipherText() {

        if (cipher.getCipherText() == null)
            System.err.println("Cipher text has not been input by the user");
        else
            System.out.println(cipher.getCipherText());
    }

    private void savePlainText() {

        scan = new Scanner(System.in);

        if (cipher.getPlainText() == null)
            System.err.println("Plain text has not been input by the user");
        else {
            System.out.println("Enter the text file name:");
            String filename = scan.nextLine();

            saveToFile(cipher.getPlainText(), filename);
        }
    }

    private void saveCipherText() {

        scan = new Scanner(System.in);

        if (cipher.getCipherText() == null)
            System.err.println("Cipher text has not been input by the user");
        else {
            System.out.println("Enter the text file name:");
            String filename = scan.nextLine();

            saveToFile(cipher.getCipherText(), filename);
        }
    }

    private String readFromFile(String fileName) {

        String reading;

        try {
            reading = new String(Files.readAllBytes(Paths.get(fileName)));
            return reading;

        } catch (IOException e) {
            System.err.println("Error reading from file");
        }
        return "";
    }

    private void saveToFile(String outText, String fileName) {
        try {
            Files.write(Paths.get(fileName), outText.getBytes());
        } catch (IOException e) {
            System.err.println("Error writing to file");
        }
    }

    private String processText(String text) {
        text = text.replaceAll("[^A-Za-z]+", "");
        text = text.toUpperCase();

        return text;
    }

}