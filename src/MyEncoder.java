import java.io.*;
import java.io.File;

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

        BufferedReader reader = new BufferedReader(new FileReader(raw));
        String line = reader.readLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter(encoded));

        boolean first = false;
        while(line != null) {
            if(first) {
                codeBuilder.append(huffTree.addSymbol((char) 10));
            }
            first = true;

            for (char e : line.toCharArray()) {
                codeBuilder.append(huffTree.addSymbol(e));
            }
            line = reader.readLine();
        }
        reader.close();

        System.out.println("encoded: " + codeBuilder + ", " + codeBuilder.length());

        int digling = codeBuilder.length()%16;

        for(int i=0; i<(16-Integer.toBinaryString(digling).length())%16; i++){
            codeBuilder.append("0");
        }

        String code = codeBuilder.toString();

        writer.write((char) digling);

        for(int i=0; i<code.length()/16; i++){
            writer.write((char) Integer.parseInt(code.substring(i*16, i*16+16), 2));
        }

        writer.close();
        printStats();
    }
}
