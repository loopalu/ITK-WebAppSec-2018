package Homework_00;

public class Mathematics {
    public long getModulus(long astendatav, long astendaja, long modulus) {
        long base = astendatav;
        for (long counter = (astendaja-1); counter > 0; counter--) {
            base = ((base % modulus) * (astendatav % modulus)) % modulus;
        }
        return base;
    }
}