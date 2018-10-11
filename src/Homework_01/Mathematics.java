package Homework_01;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Mathematics {

    public long gcd(long a, long b) {
        while (true) {
            if (a < b) {
                long tempa = a;
                a = b;
                b = tempa;
            }
            long remainder = a % b;
            if (remainder == 0) {
                return b;
            }
            a = b;
            b = remainder;
        }
    }


    public long coPrime(long m) {
        long e = Long.valueOf("2");
        while (gcd(m,e) != 1) {
            e++;
        }
        return e;
    }


    public long demodM(long m, long e) {
        long k = Long.valueOf("0");
        while (true) {
            long t = (1 + k * m);
            if (t % e == 0) {
                return t/e;
            }
            k++;
        }
    }

    public long getModulus(long astendatav, long astendaja, long modulus) {
        long base = astendatav;
        for (long counter = (astendaja-1); counter > 0; counter--) {
            base = ((base % modulus) * (astendatav % modulus)) % modulus;
        }
        return base;
    }

    private ArrayList<Long> getPrimes(long publicKeyPart1) {
        ArrayList<Long> primes = new ArrayList<>();
        for (long counter = Long.valueOf("1"); counter < (publicKeyPart1/2 + Long.valueOf("1")); counter++) {
            if (isPrime(counter)) {
                primes.add(counter);
            }
        }
        return primes;
    }

    private boolean isPrime(long number) {
        if (number <= Long.valueOf("1")) {
            return false;
        } else if (number <= Long.valueOf("3")) {
            return true;
        } else if (number % Long.valueOf("2") == 0 || number % Long.valueOf("3") == 0) {
            return false;
        } else {
            Long counter = Long.valueOf("5");
            while (counter*counter <= number) {
                if (number % counter == 0 || number % (counter + Long.valueOf("2")) == 0) {
                    return false;
                }
                counter += Long.valueOf("6");
            }
            return true;
        }
    }

    public String bruteforce(long encryptedMessage, long publicKeyPart1, long publicKeyPart2) {
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        ArrayList<Long> primes = getPrimes(publicKeyPart1);
        Long firstPrime = null, secondPrime = null, privateKeyPart1, privateKeyPart2;
        for (long counter = Long.valueOf("0"); counter < Long.valueOf(String.valueOf(primes.size())); counter++) {
            if (publicKeyPart1 % primes.get(Integer.valueOf(String.valueOf(counter))) == 0) {
                firstPrime = primes.get(Integer.valueOf(String.valueOf(counter)));
                secondPrime = publicKeyPart1 / primes.get(Integer.valueOf(String.valueOf(counter)));
                break;
            }
        }
        privateKeyPart1 = (firstPrime - Long.valueOf("1")) * (secondPrime - Long.valueOf("1"));
        privateKeyPart2 = demodM(privateKeyPart1, publicKeyPart2);
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        long timerange = timestamp2.getTime()-timestamp1.getTime();
        long hours = timerange/Long.valueOf("3600000");
        long minutes = (timerange - hours * Long.valueOf("3600000"))/Long.valueOf("60000");
        long seconds = (timerange - hours * Long.valueOf("3600000") - minutes * Long.valueOf("60000")) / Long.valueOf("1000");
        long milliseconds = timerange - hours * Long.valueOf("3600000") - minutes * Long.valueOf("60000") - seconds * Long.valueOf("1000");
        return getModulus(encryptedMessage,privateKeyPart2,publicKeyPart1) + " (bruteforcing took: " + hours + " hours, " +
                minutes + " minutes, " + seconds + " seconds, " + milliseconds + " milliseconds)";
    }
}