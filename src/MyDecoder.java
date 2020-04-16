import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class MyDecoder {
    public void decode() throws IOException {
        HuffTree huffTree = new HuffTree();

        StringBuilder codeBuilder = new StringBuilder();

        String encoded = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\encoded.txt";
//        BufferedReader reader = new BufferedReader(new FileReader(encoded));
//        String line = reader.readLine();

        String decoded = "C:\\Users\\micha\\Desktop\\Projekty\\4sem\\kkd2\\src\\decoded.txt";
//        BufferedWriter writer = new BufferedWriter(new FileWriter(decoded));

        byte[] fileContent = Files.readAllBytes(Paths.get(encoded));

        int dingling = fileContent[0];
        fileContent = Arrays.copyOfRange(fileContent, 1, fileContent.length);

        for(byte b: fileContent){
//            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
//            System.out.println("bin: " + s1);
            int num = b + 128;
            StringBuilder bin = new StringBuilder(Integer.toBinaryString(num));

//            System.out.println("how many 0s: " + (8-bin.length()));
            int how_many_0 = 8-bin.length();
            for(int i=0; i<how_many_0; i++){
//                System.out.println("appending 0");
                bin.insert(0, "0");
            }

            System.out.println("DECODING byte: " + b + ", int: " + num + ", bin: " + bin + ", bin len: " + bin.length() + ", char: " + (char) num);
            codeBuilder.append(bin);

//            codeBuilder.append(Integer.toBinaryString(b & 0xFF).replace(' ', '0'));

//            os.write(Byte.parseByte(s1, 2));
//            System.out.println("char: " + (char)Integer.parseInt(s1, 2));
        }

//        int dingling;
//        try{
//            dingling = line.charAt(0);
//            line = line.substring(1);
//        } catch (StringIndexOutOfBoundsException e){
//            dingling = 10;
//            line = reader.readLine();
//        }

//        while(line != null) {
//            for (char e : line.toCharArray()) {
//                String appended = Integer.toBinaryString(e);
////                System.out.println("char: " + e + "bin: " + appended);
//                for(int i=0; i<16-appended.length(); i++){
//                    codeBuilder.append("0");
//                }
//                codeBuilder.append(appended);
//            }
//            line = reader.readLine();
//        }
//        reader.close();

        String code = codeBuilder.toString();
        System.out.println("decoded: " + code);
//        int dingling = Integer.parseInt(code.substring(0, 8), 2)-128;

//        String sub = code.substring(idx, idx+8);
//        huffTree.addSymbol((char) Integer.parseInt(sub, 2));
//        writer.write((char) Integer.parseInt(sub, 2));
//        idx += 8;

        if(dingling == 0)
            dingling = 8;

        OutputStream os = new FileOutputStream(decoded);

        try {
            String sub = code.substring(0, 8);
            huffTree.addSymbol((char) Integer.parseInt(sub, 2));
            os.write((char) Integer.parseInt(sub, 2));
        } catch (ArrayIndexOutOfBoundsException ignored) { }

        int idx = 8;

        System.out.println("code len: " + code.length() + ", ding: " + dingling);
        while(idx < code.length()+dingling-8){
            idx = walk(huffTree, os, code, idx);
        }

//        System.out.println("dongle: " + dingling);
//        if(idx<code.length()-1) {
//            for (int i = 0; i < dingling; i++) {
//                idx = walk(huffTree, writer, code, idx);
//            }
//        }
//        writer.close();
        os.close();
    }

    private int walk(HuffTree huffTree, OutputStream outputStream, String code, int idx) throws IOException {
        String symbol;
        System.out.println("choice: " + code.charAt(idx));
        symbol = huffTree.walk(code.charAt(idx));
        if(symbol.length()==0){
            idx++;
            String sub = code.substring(idx, idx+8);
//            sub = Integer.toBinaryString(Integer.parseInt(sub, 2)+128);

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
