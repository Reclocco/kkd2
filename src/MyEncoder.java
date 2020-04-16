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

        raw = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\raw.txt";
        encoded = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\encoded.txt";
    }

    private void printStats(){
        File file1 = new File(raw);
        File file2 = new File(encoded);

        System.out.println("Entropia: " + huffTree.getEntropy());
        System.out.println("Kompresja: " + 1.0*file2.length()/file1.length());
        System.out.println("Srednia długość słowa kodowego: " + huffTree.getAverage());
    }

    public void encode() throws IOException {
        StringBuilder codeBuilder = new StringBuilder();

//        BufferedReader reader = new BufferedReader(new FileReader(raw));
//        String line = reader.readLine();
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter(encoded));
//
//        boolean first = false;
//        while(line != null) {
//            if(first) {
//                codeBuilder.append(huffTree.addSymbol((char) 10));
//            }
//            first = true;
//
//            for (char e : line.toCharArray()) {
//                codeBuilder.append(huffTree.addSymbol(e));
//            }
//            line = reader.readLine();
//        }
//        reader.close();

//        StringBuilder codebuilder = new StringBuilder();

        byte[] fileContent = Files.readAllBytes(Paths.get(raw));

        for(byte b: fileContent){
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
//            System.out.println("bin: " + s1);
            codeBuilder.append(huffTree.addSymbol((char)Integer.parseInt(s1, 2)));
//            os.write(Byte.parseByte(s1, 2));
            System.out.println("char: " + (char)Integer.parseInt(s1, 2));
        }

        System.out.println("encoded: " + codeBuilder + ", " + codeBuilder.length());

        int digling = codeBuilder.length()%8;

        for(int i=0; i<(8-Integer.toBinaryString(digling).length())%8; i++){
            codeBuilder.append("0");
        }

        String code = codeBuilder.toString();
        OutputStream os = new FileOutputStream(encoded);
        os.write(Byte.parseByte(Integer.toBinaryString(digling), 2));

//        writer.write((char) digling);

        for(int i=0; i<code.length()/8; i++){
            int num = Integer.parseInt(code.substring(i*8, i*8+8), 2)-128;
//            num = Integer.toBinaryString(Integer.parseInt(code.substring(i*8, i*8+8), 2)-128);
//            writer.write((char) Integer.parseInt(code.substring(i*16, i*16+16), 2));
            System.out.println("ENCODING bin: " + Integer.toBinaryString(num) + " dec: " + num);
            byte b = (byte) num;
            os.write(b);
        }

//        writer.close();
        os.close();
        printStats();
    }
}
