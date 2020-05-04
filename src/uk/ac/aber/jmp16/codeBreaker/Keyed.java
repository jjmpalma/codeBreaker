package uk.ac.aber.jmp16.codeBreaker;

import java.util.ArrayList;

public class Keyed extends Cipher {

    private ArrayList<Character> keyedAlphabet = new ArrayList<>();

    /**
     * Empty constructor
     */
    public Keyed() {
    }

    /**
     * Encryption from plain text to cipher text. Parameters given by the Cipher class
     * Keyed Caesar Cipher algorithm
     */
    public void Encrypt() {

        // splitKey[1] = numeric shift. splitKey[2] = textual key
        String splitKey[] = splitKey(super.getKey());
        int shiftKey = Integer.parseInt(splitKey[0]);
        String textualKey = splitKey[1];
        String plainText = super.getPlainText();
        StringBuilder sb = new StringBuilder();

        int offset = shiftKey % 26;

        //Builds keyed alphabet arrayList
        keyedAlphabet(textualKey);

        /*
         * 1- Subtract 65 to each plain text char to get it according to the keyedAlphabet index
         * 2- Add numeric shift to find the correct one
         */
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

    /**
     * Decryption from cipher text to plain text. Parameters given by the Cipher class
     * Keyed Caesar Cipher algorithm
     */
    public void Decrypt() {

        // splitKey[1] = numeric shift. splitKey[2] = textual key (negative)
        String splitKey[] = splitKey(super.getKey());
        int shiftKey = -Integer.parseInt(splitKey[0]);
        String textualKey = splitKey[1];

        String cipherText = super.getCipherText();
        StringBuilder sb = new StringBuilder();

        int offset = shiftKey % 26;

        //Builds keyed alphabet arrayList
        keyedAlphabet(textualKey);

        /*
         * 1- Find each cipher text char in the keyedAlphabet index
         * 2- Add numeric shift to find the correct one (negative number shift)
         */
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

    private String[] splitKey(String key) {

        StringBuilder numericShift = new StringBuilder();
        StringBuilder textualKey = new StringBuilder();
        String splitKey[] = new String[2];
        int firstLetter = 0;

        for (int i = 0; i < key.length(); i++) {
            if (Character.isLetter(key.charAt(i))) {
                firstLetter = i;
                break;
            }
        }

        //Gets numeric shift from key
        for (int i = 0; i < firstLetter; i++) {
            numericShift.append(key.charAt(i));
        }

        //Gets textual key from key
        for (int i = firstLetter; i < key.length(); i++) {
            textualKey.append(key.charAt(i));
        }

        splitKey[0] = numericShift.toString();
        splitKey[1] = textualKey.toString();

        return splitKey;
    }

    /**
     * Check key format: Number followed by a textual key from A-Z
     * @param key
     * @return true when format is correct and false otherwise
     */
    public boolean checkKey(String key) {

        String splitKey[];

        splitKey = splitKey(key);

        if (splitKey[0].length() != 0 && splitKey[1].length() != 0 && splitKey[1].matches("[a-zA-Z]+"))
            return true;
        else
            return false;
    }

}
