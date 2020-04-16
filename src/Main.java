import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        MyEncoder myEncoder = new MyEncoder();
        MyDecoder myDecoder = new MyDecoder();

        myEncoder.encode();
        myDecoder.decode();
    }
}
