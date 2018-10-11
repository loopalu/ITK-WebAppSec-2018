package Homework_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Mathematics mathematics = new Mathematics();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long firstPrime, secondPrime, privateKeyPart1, publicKeyPart1, publicKeyPart2, message, privateKeyPart2;

        System.out.println("Enter first prime number:");
        firstPrime = Long.parseLong(reader.readLine()); // firstPrime - p
        System.out.println("Enter second prime number:");
        secondPrime = Long.parseLong(reader.readLine()); // secondPrime - q

        publicKeyPart1 = firstPrime*secondPrime; // publicKeyPart1 - n
        privateKeyPart1 = (firstPrime - Long.valueOf("1")) * (secondPrime - Long.valueOf("1"));

        publicKeyPart2 = mathematics.coPrime(privateKeyPart1); // publicKeyPart2 - coprimeToM

        // System.out.println("Coprime to the "+m+" is "+coprimeToM);

        privateKeyPart2 = mathematics.demodM(privateKeyPart1, publicKeyPart2); // privateKey - d

        System.out.println("Public key is: " + publicKeyPart1 + " " + publicKeyPart2);

        System.out.println("Private key is: " + privateKeyPart1 + " " + privateKeyPart2);

        System.out.println("Enter Message:");
        message = Long.parseLong(reader.readLine());
        long cipher = mathematics.getModulus(message, publicKeyPart2, publicKeyPart1);
        System.out.println("Cipher is: " + cipher +"\n");
        System.out.println("Decrypted message is: " + mathematics.getModulus(cipher,privateKeyPart2,publicKeyPart1));
        reader.close();
    }
}
