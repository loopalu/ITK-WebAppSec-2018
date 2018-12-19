package RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
//    Encryption
//            Cipher = (message)e mod n,  kus e - public key part 2, n=q*p
//    Decryption
//            Message = (cipher)d mod n,  kus d - private key part 2, n=q*p


    public static void main(String[] args) throws IOException {
        //public key part 1 - krüpteerimiseks
        //public key part 2 -  krüpteerimiseks, private key part 2 tegemiseks
        //private key part 1 - public key part 2 tegemiseks, private key part 2 tegemiseks
        //private key part 2 - dekrüpteerimiseks
        Mathematics mathematics = new Mathematics();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long firstPrime, secondPrime, privateKeyPart1, publicKeyPart1, publicKeyPart2, message, privateKeyPart2;

        System.out.println("Enter first prime number:");
        firstPrime = Long.parseLong(reader.readLine()); // firstPrime - p

        System.out.println("Enter second prime number:");
        secondPrime = Long.parseLong(reader.readLine()); // secondPrime - q

        publicKeyPart1 = firstPrime*secondPrime; // publicKeyPart1 - n   ´q*p
        privateKeyPart1 = (firstPrime - Long.valueOf("1")) * (secondPrime - Long.valueOf("1")); // m = (q-1)(p-1)

        publicKeyPart2 = mathematics.coPrime(privateKeyPart1); // publicKeyPart2 - coprimeToM,  m suurim ühine algarv
        privateKeyPart2 = mathematics.demodM(privateKeyPart1, publicKeyPart2); // privateKey - d

        System.out.println("Public key is: n:" + publicKeyPart1 + " e:" + publicKeyPart2);
        System.out.println("Private key is: m:" + privateKeyPart1 + " d:" + privateKeyPart2);

        System.out.println("\nEnter Message:");
        message = Long.parseLong(reader.readLine());
        reader.close();

        long encryptedMessage = mathematics.getModulus(message, publicKeyPart2, publicKeyPart1);
        System.out.println("Cipher is: " + encryptedMessage +"\n");

        long signedMessage = mathematics.getModulus(message,privateKeyPart2,publicKeyPart1);
        System.out.println("Signature is: "+signedMessage + " Message is: "+ message+"\n");
        System.out.println("Signature is true and message is not modified: "+mathematics.getModulus(signedMessage,publicKeyPart2,publicKeyPart1).equals(message));

        System.out.println("Decrypted message is: " + mathematics.getModulus(encryptedMessage,privateKeyPart2,publicKeyPart1));

        System.out.println("Cracked message is: " + mathematics.bruteforce(encryptedMessage,publicKeyPart1,publicKeyPart2));
    }
}
