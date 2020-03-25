import java.math.BigInteger;
import java.util.Arrays;


public class Decoder {

    public BigInteger decodeLength(BigInteger binaryLength) {
        if(binaryLength.compareTo(new BigInteger("2").pow(1016).subtract(BigInteger.ONE).subtract(new BigInteger("2").pow(1008))) > 0
        || binaryLength.compareTo(BigInteger.ZERO) < 0) throw new IndexOutOfBoundsException();
        byte[] bytes = binaryLength.toByteArray();
        if (bytes[0] == 0 && bytes.length > 1)
            bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
        int[] intArrayLength = new int[bytes.length];
        for (int i = bytes.length - 1; i >= 0; i--)
            intArrayLength[i] = ((bytes[i] & 128) != 0) ? (int) bytes[i] + 256 : bytes[i];
        int firstByte = intArrayLength[0];
        if ((firstByte & 128) == 0)
            return BigInteger.valueOf(firstByte);
        else {
            int nextByteCount = firstByte - 128;
            BigInteger length = BigInteger.ZERO;
            for (int i = 1; i <= nextByteCount; i++) {
                length = length.add(new BigInteger("256")
                        .pow(intArrayLength.length - i - 1)
                        .multiply(BigInteger.valueOf(intArrayLength[i])));
            }
            return length;
        }
    }
}
