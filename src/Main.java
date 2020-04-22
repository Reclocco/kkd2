import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        MyEncoder myEncoder = new MyEncoder(args[0], args[1]);
        MyDecoder myDecoder = new MyDecoder(args[1], args[2]);

        myEncoder.encode();
        myDecoder.decode();
    }
}
