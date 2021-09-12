package com.example.random;

public class PeriodCalculator {
    /*
    unsigned lfsr_xorshift(void)
    {
        uint16_t start_state = 0xACE1u;  // Any nonzero start state will work.
        uint16_t lfsr = start_state;
        unsigned period = 0;

        do
        {
            lfsr ^= lfsr >> 7;
            lfsr ^= lfsr << 9;
            lfsr ^= lfsr >> 13;
            ++period;
        }
        while (lfsr != start_state);

        return period;
    }
     */
    public int calc16Bit(int startState) {
        int lfsr = startState;
        int period = 0;
        int maxPeriod = 357913941;
        do {
            lfsr = getLfsr16(lfsr, period);
            ++period;
        } while (lfsr != startState);
        return period;
    }

    private int getLfsr16(int lfsr, int period) {
//        BinString binStr = null;
//        boolean verbose = false; //(period == 1);
//        if(verbose) {
//            int bitsWide = 16;
//            binStr = BinString.of(bitsWide);
//            binStr.add("lfsr : ", lfsr);
//        }
        int lfsr1 = lfsr >> 7;
//        if(verbose) {
//            binStr.add("lfsr1: ", lfsr1);
//        }
        lfsr ^= lfsr1;
//        if(verbose) {
//            binStr.add("lfsr : ", lfsr );
//        }
        int lfsr2 = lfsr << 9;
//        if(verbose) {
//            binStr.add("lfsr2: ", lfsr2);
//        }
        lfsr ^= lfsr2;
//        if(verbose) {
//            binStr.add("lfsr : ", lfsr );
//        }
        int lfsr3 = lfsr >> 13;
//        if(verbose) {
//            binStr.add("lfsr3: ", lfsr3);
//        }
        lfsr ^= lfsr3;
//        if(verbose) {
//            binStr.add("lfsr : ", lfsr);
//            String display = binStr.toList().stream()
//                    .reduce("", (a, b) -> a + "\n" + b);
//            log.info("\n{}", display);
//        }
        return lfsr;
    }
}
