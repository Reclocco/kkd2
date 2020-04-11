import java.io.*;

public class Decoder {
    public void decode() throws IOException {
        HuffTree huffTree = new HuffTree();

        StringBuilder codeBuilder = new StringBuilder();

        String encoded = "C:\\Users\\micha\\Desktop\\kkd2\\src\\endoded.txt";
        BufferedReader reader = new BufferedReader(new FileReader(encoded));
        String line = reader.readLine();

        String decoded = "C:\\Users\\micha\\Desktop\\kkd2\\src\\encoded.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(encoded));

        while(line != null) {
            for (char e : line.toCharArray()) {
                codeBuilder.append(Integer.toBinaryString(e));
            }
            line = reader.readLine();
        }
        reader.close();

        String code = codeBuilder.toString();
        int idx = 0;
        String symbol;

        while(idx < code.length()){
            symbol = huffTree.walk(code.charAt(idx));
            if(symbol.length()==0){
                String sub = code.substring(idx, idx+8);
                writer.write((char) Integer.parseInt(sub, 2));
                idx += 8;
            }
            else if(symbol.length()==1){
                writer.write(symbol.charAt(0));
                idx++;
            }
            else {
                idx++;
            }
        }

        writer.close();
    }
}
