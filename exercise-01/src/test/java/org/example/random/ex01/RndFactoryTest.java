package org.example.random.ex01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RndFactoryTest {

    private RndFactory underTest;

    @BeforeEach
    void setUp() {
        underTest = new RndFactory();
    }

    @Test
    void listAllSorted() {
        List<RndFactory.Rnd> expected = List.of(
                new RndFactory.Rnd("L128X1024MixRandom",new BigInteger("61172327492847069472032393719205726809135813743440799050195397570919697796091958321786863938157971792315844506873509046544459008355036150650333616890210625686064472971480622053109783197015954399612052812141827922088117778074833698589048132156300022844899841969874763871624802603515651998113045708569927237462205950801913604801214743801985278935040")),
                new RndFactory.Rnd("L64X1024MixRandom",new BigInteger("3316158518186977171087283760642741158699936149735704467159471849921418683482035763477878926564345847729145083728966646356210626353328840324989147544629059746554141479347263264595425816446455256534872353644097455203319930608430165174159005378955830171087831965898486080345430665055936553487340789901656166618015036886002108989440")),
                new RndFactory.Rnd("L128X256MixRandom",new BigInteger("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047345316421452266199196222095360")),
                new RndFactory.Rnd("L64X256MixRandom",new BigInteger("2135987035920910082395021706169552114602704522356652769947041607822219725780622103278888377384960")),
                new RndFactory.Rnd("Xoshiro256PlusPlus",new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935")),
                new RndFactory.Rnd("L128X128MixRandom",new BigInteger("115792089237316195423570985008687907852929702298719625575994209400481361428480")),
                new RndFactory.Rnd("L64X128StarStarRandom",new BigInteger("6277101735386680763835789423207666416083908700390324961280")),
                new RndFactory.Rnd("L64X128MixRandom",new BigInteger("6277101735386680763835789423207666416083908700390324961280")),
                new RndFactory.Rnd("Xoroshiro128PlusPlus",new BigInteger("340282366920938463463374607431768211455")),
                new RndFactory.Rnd("L32X64MixRandom",new BigInteger("79228162514264337589248983040")),
                new RndFactory.Rnd("SplittableRandom",new BigInteger("18446744073709551616")),
                new RndFactory.Rnd("Random",new BigInteger("281474976710656")),
                new RndFactory.Rnd("SecureRandom",new BigInteger("0"))
                );
        List<RndFactory.Rnd> actual = underTest.listAllSorted().toList();
        actual.forEach((rnd) -> {
            String fmt = "   new RndFactory.Rnd(\"%s\",new BigInteger(\"%s\")),";
            String msg = String.format(fmt,
                    rnd.name(), rnd.period().toString());
            System.out.println(msg);
        });
        Assertions.assertEquals(expected.size(), actual.size());
    }
}