import java.util.ArrayList;
import java.util.Collections;

public class HuffTree {
    private Node root = null;
    private Node current;
    private int number = 256;
    private ArrayList<Node> leafs = new ArrayList<>();

    private Node swapBlock(Node bottom){
        Node leader = searchBlock(bottom.getWeight());
        char tmp = bottom.getSymbol();
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
        current = root;
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

    private Node searchHuff(char symbol, ArrayList<Character> path) {
        Node current = root;
        int whereTo = 0;

        while (true) {

            if (current.getType() == 0) {
                return current;
            }

            if (current.getChildTwo().getType() == 1) {
                if (current.getChildTwo().getSymbol() == symbol) {
                    path.add('1');
                    return current.getChildTwo();
                }

                whereTo = 1;
            } else {
                if (current.getChildOne().getSymbol() == symbol) {
                    path.add('0');
                    return current.getChildOne();
                }

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

    public String addSymbol(char symbol){
        ArrayList<Character> path = new ArrayList<>();
        Node node = searchHuff(symbol, path);

        if(node.getType() == 0){
            node.setType(2);
            node.setChildOne(new Node(0, number-1, node, 0));
            node.setChildTwo(new Node(1, number, node, 1));
            number -= 2;
            node.getChildTwo().setSymbol(symbol);
            leafs.add(node.getChildTwo());

            char[] code = new char[8+path.size()];
            int idx = 0;
            for(char bin: path) {
                code[idx] = bin;
                idx++;
            }
            char[] binSym = Integer.toBinaryString(symbol).toCharArray();
            for(int i=0; i<8-binSym.length; i++) {
                code[idx] = '0';
                idx++;
            }
            for(char bin: binSym) {
                code[idx] = bin;
                idx++;
            }
            incAndSwap(node);

            return String.valueOf(code);
        }
        else {
            node = swapBlock(node);
            node.incWeight();
            incAndSwap(node);

            char[] code = new char[path.size()];
            int idx = 0;
            for(char bin: path) {
                code[idx] = bin;
                idx++;
            }
            return String.valueOf(code);
        }
    }

    public String walk(char choice){
        if(choice == '0')
            current = current.getChildOne();
        else
            current = current.getChildTwo();

        if(current.getType() == 0){
            current = root;
            return "";
        }
        else if(current.getType() == 1) {
            char symbol = current.getSymbol();
            current = root;
            addSymbol(symbol);
            return "" + symbol;
        } else
            return "aa";
    }

    public float getEntropy(){
        float entropy = 0;
        float omega = 0;

        for(Node node:leafs)
            omega += node.getWeight();

        for(Node node: leafs){
            entropy -= node.getWeight()/omega*(Math.log(node.getWeight()/omega));
        }

        return entropy;
    }

    public double getAverage(){
        Collections.sort(leafs);
        int len = 1;
        int sum = 0;
        int omega = 0;

        for(Node leaf: leafs){
            sum += len * leaf.getWeight();
            omega += leaf.getWeight();
            len++;
        }

        return 1.0*sum/omega;
    }
}
