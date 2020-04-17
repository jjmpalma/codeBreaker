public abstract class Cipher {

        private String key;
        private String plainText;
        private String cipherText;

        public String getKey() {
                return key;
        }

        public void setKey(String key) {
                this.key = key;
        }

        public String getPlainText() {
                return plainText;
        }

        public void setPlainText(String plainText) {
                this.plainText = plainText;
        }

        public String getCipherText() {
                return cipherText;
        }

        public void setCipherText(String cipherText) {
                this.cipherText = cipherText;
        }

        abstract void Encrypt();
        abstract void Decrypt();

        //method update key in file
        //method update plain text in file
        //method update cipher text in file

}
