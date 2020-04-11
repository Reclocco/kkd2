import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        MyEncoder myEncoder = new MyEncoder();
        MyDecoder myDecoder = new MyDecoder();

        myEncoder.encode();
        myDecoder.decode();

//        HuffTree huffTree = new HuffTree();
//        huffTree.addSymbol('4');
//        huffTree.addSymbol('a');
//        huffTree.addSymbol('a');
//        huffTree.addSymbol('c');
//        huffTree.addSymbol('c');
//        huffTree.addSymbol('8');
//        huffTree.addSymbol('k');
//        huffTree.addSymbol('c');
//        huffTree.addSymbol('b');
//        huffTree.addSymbol('b');
//        huffTree.addSymbol('4');
//
//        huffTree.print();
    }
}
