import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        MyEncoder myEncoder = new MyEncoder();
        MyDecoder myDecoder = new MyDecoder();

        myEncoder.encode();
        myDecoder.decode();

        System.out.println((int) 'ÿ');
        System.out.println(Integer.toBinaryString('ÿ').toCharArray());
        System.out.println((char) 10);
        System.out.println((char) 255);
    }
}
