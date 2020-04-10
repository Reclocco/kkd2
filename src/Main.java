public class Main {

    public static void main(String[] args) {
        HuffTree huffTree = new HuffTree();
        huffTree.print();

        huffTree.addSymbol(4);
        huffTree.print();

        huffTree.addSymbol(2);
        huffTree.print();

        huffTree.addSymbol(2);
        huffTree.print();

        huffTree.addSymbol(1);
        huffTree.print();

        huffTree.addSymbol(1);
        huffTree.print();
    }
}
