import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Keyed extends Cipher {

    Scanner scan;
    ArrayList<Character> keyedAlphabet = new ArrayList<>();

    public Keyed() {
    }

    public void Encrypt() {

        String splitKey[] = splitKey(super.getKey());
        int shiftKey = Integer.parseInt(splitKey[0]);
        String textualKey = splitKey[1];
        String plainText = super.getPlainText();
        StringBuilder sb = new StringBuilder();

        int offset = shiftKey % 26;

        keyedAlphabet(textualKey);

        for (char c : plainText.toCharArray()) {
            c -= 65;
            c += offset;

            if (c > 25)
                c -= 26;
            if (c < 0)
                c += 26;

            sb.append(keyedAlphabet.get(c));
        }

        super.setCipherText(sb.toString());
    }

    public void Decrypt() {

        String splitKey[] = splitKey(super.getKey());
        int shiftKey = -Integer.parseInt(splitKey[0]);
        String textualKey = splitKey[1];
        String cipherText = super.getCipherText();
        StringBuilder sb = new StringBuilder();

        int offset = shiftKey % 26;

        keyedAlphabet(textualKey);

        for (char c : cipherText.toCharArray()) {
            int keyedIndex = keyedAlphabet.indexOf(c);

            keyedIndex += offset;

            if (keyedIndex > 25)
                keyedIndex -= 26;
            if (keyedIndex < 0)
                keyedIndex += 26;

            keyedIndex += 65;

            sb.append((char) keyedIndex);
        }

        super.setPlainText(sb.toString());
    }

    private void keyedAlphabet(String textualKey) {

        StringBuilder sb = new StringBuilder(); //output cipher text

        //Store textualKey only with the first occurrence of each letter in keyed alphabet
        for (int i = 0; i < textualKey.length(); i++) {
            char c = textualKey.charAt(i);
            if (!keyedAlphabet.contains(c))
                keyedAlphabet.add(c);
        }

        //Fill keyed alphabet with the rest alphabet letters
        for (int i = 65; i < 91; i++) {
            if (!keyedAlphabet.contains((char) i))
                keyedAlphabet.add((char) i);
        }

    }

    public boolean checkKey(String key) {

        String splitKey[];

        splitKey = splitKey(key);

        if (splitKey[0].length() != 0 && splitKey[1].length() != 0 && splitKey[1].matches("[a-zA-Z]+"))
            return true;
        else
            return false;

    }

    private String[] splitKey(String key) {

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String splitKey[] = new String[2];
        int firstLetter = 0;

        for (int i = 0; i < key.length(); i++) {
            if (Character.isLetter(key.charAt(i))) {
                firstLetter = i;
                break;
            }
        }

        for (int i = 0; i < firstLetter; i++) {
            sb1.append(key.charAt(i));
        }

        for (int i = firstLetter; i < key.length(); i++) {
            sb2.append(key.charAt(i));
        }

        splitKey[0] = sb1.toString();
        splitKey[1] = sb2.toString();

        return splitKey;
    }



}
