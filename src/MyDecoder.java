import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class MyDecoder {
    public void decode() throws IOException {
        HuffTree huffTree = new HuffTree();

        StringBuilder codeBuilder = new StringBuilder();

        String encoded = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\encoded.txt";

        String decoded = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\decoded.txt";

        byte[] fileContent = Files.readAllBytes(Paths.get(encoded));

        int dingling = fileContent[0];
        fileContent = Arrays.copyOfRange(fileContent, 1, fileContent.length);

        for(byte b: fileContent){
            int num = b + 128;
            StringBuilder bin = new StringBuilder(Integer.toBinaryString(num));

            int how_many_0 = 8-bin.length();
            for(int i=0; i<how_many_0; i++){
                bin.insert(0, "0");
            }

            codeBuilder.append(bin);
        }

        String code = codeBuilder.toString();

        if(dingling == 0)
            dingling = 8;

        OutputStream os = new FileOutputStream(decoded);

        try {
            String sub = code.substring(0, 8);
            huffTree.addSymbol((char) Integer.parseInt(sub, 2));
            os.write((char) Integer.parseInt(sub, 2));
        } catch (ArrayIndexOutOfBoundsException ignored) { }

        int idx = 8;

        while(idx < code.length()+dingling-8){
            idx = walk(huffTree, os, code, idx);
        }

        os.close();
    }

    private int walk(HuffTree huffTree, OutputStream outputStream, String code, int idx) throws IOException {
        String symbol;
        symbol = huffTree.walk(code.charAt(idx));
        if(symbol.length()==0){
            idx++;
            String sub = code.substring(idx, idx+8);

            huffTree.addSymbol((char) Integer.parseInt(sub, 2));
            outputStream.write((char) Integer.parseInt(sub, 2));
            idx += 8;
        }
        else if(symbol.length()==1){
            outputStream.write(symbol.charAt(0));
            idx++;
        }
        else {
            idx++;
        }
        return idx;
    }
}
