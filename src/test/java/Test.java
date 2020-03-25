import org.junit.Assert;
import java.math.BigInteger;

public class Test {
    @org.junit.Test
    public void decodeTest() {
        Decoder decoder = new Decoder();
        Assert.assertEquals(BigInteger.valueOf(2), decoder.decodeLength(BigInteger.valueOf(512)));
        Assert.assertEquals(BigInteger.ZERO, decoder.decodeLength(BigInteger.ZERO));
        Assert.assertEquals(BigInteger.ONE, decoder.decodeLength(BigInteger.valueOf(33025)));
        Assert.assertEquals(BigInteger.valueOf(255), decoder.decodeLength(BigInteger.valueOf(33279)));

        BigInteger length = new BigInteger("2").pow(1016).subtract(BigInteger.ONE).subtract(new BigInteger("2").pow(1008));
        Assert.assertEquals((new BigInteger("2").pow(1008).subtract(BigInteger.ONE)), decoder.decodeLength(length));
        try {
            decoder.decodeLength(BigInteger.valueOf(-1));
            Assert.fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
        try {
            decoder.decodeLength(length.add(BigInteger.ONE));
            Assert.fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }


    }
    @org.junit.Test
    public  void encodeTest() {
        Encoder encoder = new Encoder();
        Assert.assertEquals(BigInteger.valueOf(127), encoder.encodeLength(BigInteger.valueOf(127)));
        for(int i = 128; i < 256; i++){
            Assert.assertEquals(BigInteger.valueOf(i + 129*256), encoder.encodeLength(BigInteger.valueOf(i)));
        }

        try {
            encoder.encodeLength(BigInteger.valueOf(-1));
            Assert.fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
        try {
            encoder.encodeLength(BigInteger.valueOf(2).pow(1008));
            Assert.fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }

    @org.junit.Test
    public void decodeEncodeTest(){
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        Assert.assertEquals(BigInteger.valueOf(128), decoder.decodeLength(encoder.encodeLength(BigInteger.valueOf(128))));
        Assert.assertEquals(BigInteger.valueOf(2), decoder.decodeLength(encoder.encodeLength(BigInteger.valueOf(2))));
        Assert.assertEquals(BigInteger.ZERO, decoder.decodeLength(encoder.encodeLength(BigInteger.ZERO)));
        Assert.assertEquals(BigInteger.ONE, decoder.decodeLength(encoder.encodeLength(BigInteger.ONE)));
        Assert.assertEquals(BigInteger.valueOf(255), decoder.decodeLength(encoder.encodeLength(BigInteger.valueOf(255))));

        BigInteger number = new BigInteger("2").pow(1008).subtract(BigInteger.ONE);
        Assert.assertEquals(number, decoder.decodeLength(encoder.encodeLength(number)));
    }
}
