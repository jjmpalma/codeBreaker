import java.util.Scanner;

public class Keyed extends Cipher {

    Scanner scan;

    public Keyed() {
    }

    public void Encrypt() {

    }

    public void Decrypt() {

    }

    public String editKey() {
        //CHECK: key contains a shift number + something else(string key)
        scan = new Scanner(System.in);
        String key;

        while (true) {

            int firstLetter = 0;
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            System.out.println("Enter key value: ");
            key = scan.nextLine();

            for (int i = 0; i < key.length(); i++) {
                if (Character.isLetter(key.charAt(i))) {
                    firstLetter = i;
                    break;
                }
            }

            for (int i = 0; i < firstLetter; i++) {
                sb1.append(key.charAt(i));
            }

            for (int i = firstLetter; i < key.length(); i++){
                sb2.append(key.charAt(i));
            }

            String number = sb1.toString();
            String textualKey = super.processText(sb2.toString());

            key = number + textualKey;

            if (firstLetter != 0 && key.length() > firstLetter) {
                return key;
            } else {
                System.err.println("Enter a number followed by a textual key");
            }
        }
    }



}
