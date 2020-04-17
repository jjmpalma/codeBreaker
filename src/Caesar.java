public class Caesar extends Cipher {

    public Caesar() {
    }

    public void Encrypt() {

        int offset = Integer.parseInt(super.getKey()) % 26;

        String plainText = super.getPlainText().replaceAll("[^A-Za-z]+", "");
        plainText.toUpperCase();


        for(char c: plainText.toCharArray()){
            c += offset;
        }

        super.setPlainText(plainText);
        //super.updatePlainText

    }

    public void Decrypt() {

    }
}
