package Homework_01;

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
}