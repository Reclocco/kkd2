import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HuffTree {
    private Node root = null;
    private int number = 256;

    private Node swapBlock(Node bottom){
        Node leader = searchBlock(bottom.getWeight());
        int tmp = bottom.getSymbol();
        bottom.setSymbol(leader.getSymbol());
        leader.setSymbol(tmp);

        return leader;
    }

    private void incAndSwap(Node node){
        while(node.getParent() != null){
            node.incWeight();
            if (node.compare()){
                node.getParent().setChildOne(node.getParent().getChildTwo());
                node.getParent().setChildTwo(node);

                node.incNumber();
                node.getParent().getChildOne().decNumber();
            }

            node = node.getParent();
        }
        node.incWeight();
    }

    public HuffTree() {
        root = new Node(0, number, null, 0);
        number--;
    }

    public void print(){
        Node current = root;
        System.out.println("(Num: " + current.getNumber() + ", sym: " + current.getSymbol() + ", weight: " + current.getWeight() + ", type: " + current.getType() + ")");
        while(current.getType() != 0){
            System.out.print("(Num: " + current.getChildOne().getNumber() + ", sym: " + current.getChildOne().getSymbol() + ", weight: " + current.getChildOne().getWeight() + ", type: " + current.getChildOne().getType() + ")   ");
            System.out.print("(Num: " + current.getChildTwo().getNumber() + ", sym: " + current.getChildTwo().getSymbol() + ", weight: " + current.getChildTwo().getWeight() + ", type: " + current.getChildTwo().getType() + ")\n");


            if (current.getChildTwo().getType() == 1)
                current = current.getChildOne();
            else
                current = current.getChildTwo();
        }
        System.out.println("\n");
    }

    private Node searchBlock(int weight) {
        Node current = root;
        int whereTo = 0;

        while (true) {
            if (current.getChildTwo().getType() == 1) {
                if (current.getChildTwo().getWeight() == weight)
                    return current.getChildTwo();

                whereTo = 1;
            } else {
                if (current.getChildOne().getWeight() == weight)
                    return current.getChildOne();
            }

            if (whereTo == 1) {
                current = current.getChildOne();
                whereTo = 0;
            } else
                current = current.getChildTwo();
        }
    }

    private Node searchHuff(int symbol, ArrayList<Character> path) {
        Node current = root;
        int whereTo = 0;

        while (true) {

            if (current.getType() == 0) {
                if(path.size() != 0)
                    path.remove(0);
                return current;
            }

            if (current.getChildTwo().getType() == 1) {
                if (current.getChildTwo().getSymbol() == symbol)
                    return current.getChildTwo();

                whereTo = 1;
            } else {
                if (current.getChildOne().getSymbol() == symbol)
                    return current.getChildOne();

            }

            if (whereTo == 1) {
                current = current.getChildOne();
                whereTo = 0;
                path.add('0');
            } else {
                current = current.getChildTwo();
                path.add('1');
            }
        }
    }

    public char[] addSymbol(int symbol){
        ArrayList<Character> path = new ArrayList<>();
        Node node = searchHuff(symbol, path);

        if(node.getType() == 0){
            node.setType(2);
            node.setChildOne(new Node(0, number-1, node, 0));
            node.setChildTwo(new Node(1, number, node, 1));
            number -= 2;
            node.getChildTwo().setSymbol(symbol);
            System.out.println(symbol);
            System.out.println(node.getChildTwo().getSymbol());

            char[] code = new char[8+path.size()];
            int idx = 0;
            for(char bin: path) {
                code[idx] = bin;
                idx++;
            }

            for(char bin: Integer.toBinaryString(symbol).toCharArray()) {
                code[idx] = bin;
                idx++;
            }
            incAndSwap(node);

            return code;
        } else {
            node = swapBlock(node);

            char[] code = new char[path.size()];
            int idx = 0;
            for(char bin: path) {
                code[idx] = bin;
                idx++;
            }
            incAndSwap(node);

            return code;
        }
    }
}
