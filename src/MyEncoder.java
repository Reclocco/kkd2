import java.io.*;
import java.io.File;

public class MyEncoder {
    String raw;
    String encoded;
    HuffTree huffTree;

    public MyEncoder() throws IOException {
        huffTree = new HuffTree();

        raw = "C:\\Users\\micha\\Desktop\\kkd2\\src\\raw.txt";
        encoded = "C:\\Users\\micha\\Desktop\\kkd2\\src\\encoded.txt";
    }

    private void printStats(){
        File file1 = new File(raw);
        File file2 = new File(encoded);

        System.out.println("Entropia: " + huffTree.getEntropy());
        System.out.println("Kompresja: " + file2.length()/file1.length());
        System.out.println("Srednia długość słowa kodowego: " + huffTree.getAverage());
    }

    public void encode() throws IOException {
        StringBuilder codeBuilder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(raw));
        String line = reader.readLine();
        System.out.println(line);

        BufferedWriter writer = new BufferedWriter(new FileWriter(encoded));

        while(line != null) {
            for (char e : line.toCharArray()) {
                System.out.println(e);
                codeBuilder.append(huffTree.addSymbol(e));
            }
            codeBuilder.append(huffTree.addSymbol((char) 10));
            line = reader.readLine();
        }
        reader.close();

        int digling = codeBuilder.length()%8;
        System.out.println(codeBuilder.length());

        for(int i=0; i<(8-digling)%8; i++){
            codeBuilder.append("0");
        }

        String code = codeBuilder.toString();

        writer.write((char) digling);

        for(int i=0; i<code.length()/8; i++){
            writer.write((char) Integer.parseInt(code.substring(i*8, i*8+8), 2));
        }

        writer.close();
        printStats();
    }
}
