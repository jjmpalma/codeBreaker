import java.util.Scanner;

public class Vigenere extends Cipher {

    Scanner scan;

    public Vigenere() {
    }

    public void Encrypt() {

    }

    public void Decrypt() {

    }

    public String editKey() {
        //CHECK: key contains just chars from a-z A-Z
        scan = new Scanner(System.in);
        String key;

        while (true) {
                System.out.println("Enter key value: ");
                key = scan.nextLine();

                if (key.matches("[a-zA-Z]+")) {
                    return super.processText(key);
                } else
                    System.err.println("Enter just letters a-z");
        }
    }





}
