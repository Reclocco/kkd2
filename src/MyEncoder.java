import java.io.*;

public class MyEncoder {
    public void encode() throws IOException {
        HuffTree huffTree = new HuffTree();

        StringBuilder codeBuilder = new StringBuilder();

        String raw = "C:\\Users\\micha\\Desktop\\kkd2\\src\\raw.txt";
        BufferedReader reader = new BufferedReader(new FileReader(raw));
        String line = reader.readLine();

        String encoded = "C:\\Users\\micha\\Desktop\\kkd2\\src\\encoded.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(encoded));

        while(line != null) {
            for (char e : line.toCharArray()) {
                codeBuilder.append(huffTree.addSymbol(e));
            }
            line = reader.readLine();
        }
        reader.close();

        int digling = codeBuilder.length()%8;

        for(int i=0; i<(8-(codeBuilder.length()%8))%8; i++){
            codeBuilder.append("0");
        }

        String code = codeBuilder.toString();
        System.out.println(code.length());

        writer.write((char) digling);

        int counter = 1;
        for(int i=0; i<code.length()/8; i++){
            writer.write((char) Integer.parseInt(code.substring(i*8, i*8+8), 2));
            counter++;
        }

        writer.close();
    }
}