package Homework_00;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Mathematics mathematics = new Mathematics();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long publicPrimeModulus, publicPrimeBase, privateKey1, privateKey2;

        System.out.println("Enter first prime number:\n");
        publicPrimeModulus = Long.parseLong(reader.readLine());
        System.out.println("Enter second prime number:\n");
        publicPrimeBase = Long.parseLong(reader.readLine());
        System.out.println("Enter first secret number:\n");
        privateKey1 = Long.parseLong(reader.readLine());
        System.out.println("Enter second secret number:\n");
        privateKey2 = Long.parseLong(reader.readLine());
        reader.close();


        long publicKey1 = mathematics.getModulus(publicPrimeBase,privateKey1,publicPrimeModulus);
        long publicKey2 = mathematics.getModulus(publicPrimeBase,privateKey2,publicPrimeModulus);
        long sharedSecretKey1 = mathematics.getModulus(publicKey2,privateKey1,publicPrimeModulus);
        long sharedSecretKey2 = mathematics.getModulus(publicKey1,privateKey2,publicPrimeModulus);
        if (sharedSecretKey1 == sharedSecretKey2) {
            System.out.println("Common secret key is: " + sharedSecretKey1);
        } else {
            System.out.println("Some error is going on. First secret key: "+sharedSecretKey1+" Second secret key: "+sharedSecretKey2); // For debugging.
        }
    }
}
