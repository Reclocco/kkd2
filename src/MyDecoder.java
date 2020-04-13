import java.io.*;

public class MyDecoder {
    public void decode() throws IOException {
        HuffTree huffTree = new HuffTree();

        StringBuilder codeBuilder = new StringBuilder();

        String encoded = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\encoded.txt";
        BufferedReader reader = new BufferedReader(new FileReader(encoded));
        String line = reader.readLine();

        String decoded = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\decoded.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(decoded));

        int dingling;
        try{
            dingling = line.charAt(0);
        } catch (NullPointerException e){
            dingling = 10;
        }


        while(line != null) {
            for (char e : line.toCharArray()) {
                String appended = Integer.toBinaryString(e);
                for(int i=0; i<16-appended.length(); i++){
                    codeBuilder.append("0");
                }
                codeBuilder.append(appended);
            }
            line = reader.readLine();
        }
        reader.close();

        String code = codeBuilder.toString();
        int idx = 16;

        String sub = code.substring(idx, idx+16);
        huffTree.addSymbol((char) Integer.parseInt(sub, 2));
        writer.write((char) Integer.parseInt(sub, 2));
        idx += 8;

        while(idx < code.length()-16){
            idx = walk(huffTree, writer, code, idx);
        }

//        System.out.println(dingling);
        if(idx<code.length()-1) {
            for (int i = 0; i < dingling; i++) {
                idx = walk(huffTree, writer, code, idx);
            }
        }
        writer.close();
    }

    private int walk(HuffTree huffTree, BufferedWriter writer, String code, int idx) throws IOException {
        String symbol;
        symbol = huffTree.walk(code.charAt(idx));
        if(symbol.length()==0){
            idx++;
            String sub = code.substring(idx, idx+8);

            huffTree.addSymbol((char) Integer.parseInt(sub, 2));
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
        return idx;
    }
}
