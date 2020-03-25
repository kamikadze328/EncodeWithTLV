import java.math.BigInteger;
import java.util.Arrays;

public class Encoder {

    public BigInteger encodeLength(BigInteger length) {
        if(length.compareTo(BigInteger.ZERO) < 0 || length.compareTo(new BigInteger("2").pow(1008)) >= 0) throw new IndexOutOfBoundsException();
        if (length.compareTo(new BigInteger("127")) <= 0)
            return length;
        else {
            byte[] bytes = length.toByteArray();
            if(bytes[0]==0 && bytes.length>1)
                bytes = Arrays.copyOfRange(bytes, 1, bytes.length);

            BigInteger twoFiftySix = new BigInteger("256");
            BigInteger binaryLength = BigInteger.ZERO;
            for (int i = bytes.length - 1; i >= 0; i--) {
                int oneByte = ((bytes[i] & 128) != 0) ? (int) bytes[i] + 256 : bytes[i];
                binaryLength = binaryLength.add(twoFiftySix.pow(bytes.length - i - 1)
                                .multiply(BigInteger.valueOf(oneByte)));
            }
            binaryLength = binaryLength.add(twoFiftySix.pow(bytes.length)
                    .multiply(BigInteger.valueOf(Byte.toUnsignedInt((byte) (128 + bytes.length)))));

            return binaryLength;
        }
    }
}
