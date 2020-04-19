import java.util.Scanner;

public class Caesar extends Cipher {

    Scanner scan;

    public Caesar() {
    }

    public void Encrypt() {

        String plainText = super.getPlainText();
        int key = Integer.parseInt(super.getKey());

        super.setCipherText(shiftChar(plainText, key));
    }

    public void Decrypt() {

        String cipherText = super.getCipherText();
        int key = -Integer.parseInt(super.getKey());

        super.setPlainText(shiftChar(cipherText, key));
    }

    private String shiftChar(String text, int key){

        StringBuilder sb = new StringBuilder();

        int offset = key % 26;

        for (char c : text.toCharArray()) {
            c += offset;
            if (c > 90)
                c -= 26;
            if (c < 65)
                c += 26;

            sb.append(c);
        }

        return sb.toString();
    }

    public String editKey() {
        //CHECK: key contains only digits
        scan = new Scanner(System.in);
        String key;

        while (true) {
            System.out.println("Enter key value: ");
            key = scan.nextLine();

            if (key.matches("-?[0-9]+")) {
                return key;
            } else
                System.err.println("Enter just numeric digits");
        }
    }


}

