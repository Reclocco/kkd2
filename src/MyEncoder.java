import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyEncoder {
    String raw;
    String encoded;
    HuffTree huffTree;

    public MyEncoder() throws IOException {
        huffTree = new HuffTree();

        raw = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\deep.txt";
        encoded = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\encoded.txt";
    }

    private void printStats(){
        File raw = new File(this.raw);
        File encoded = new File(this.encoded);

        System.out.println("Entropia: " + huffTree.getEntropy());
        System.out.println("Kompresja: " + 1.0*raw.length()/encoded.length());
        System.out.println("Srednia długość słowa kodowego: " + huffTree.getAverage());
    }

    public void encode() throws IOException {
        StringBuilder codeBuilder = new StringBuilder();

        byte[] fileContent = Files.readAllBytes(Paths.get(raw));

        for(byte b: fileContent){
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            codeBuilder.append(huffTree.addSymbol((char)Integer.parseInt(s1, 2)));
        }

        int digling = codeBuilder.length()%8;

        for(int i=0; i<(8-Integer.toBinaryString(digling).length())%8; i++){
            codeBuilder.append("0");
        }

        String code = codeBuilder.toString();
        OutputStream os = new FileOutputStream(encoded);
        os.write(Byte.parseByte(Integer.toBinaryString(digling), 2));

        for(int i=0; i<code.length()/8; i++){
            int num = Integer.parseInt(code.substring(i*8, i*8+8), 2)-128;
            byte b = (byte) num;
            os.write(b);
        }

        os.close();
        printStats();
    }
}
