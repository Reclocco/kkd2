
public class Node {
    private Node[] children = {null, null};
    private Node parent = null;

    private int weight = 0;
    private int number = 0;
    private char symbol;
    private int type = 0;
    /*
        0-NYT
        1-Leaf
        2-Inner
     */

    public Node(int weight, int number, Node parent, int type) {
        this.weight = weight;
        this.number = number;
        this.parent = parent;
        this.type = type;
    }

    public boolean compare(){
        return this.getWeight() > this.getParent().getChildTwo().getWeight();
    }

    public int getWeight() {
        return weight;
    }

    public void incNumber(){
        number++;
    }

    public void decNumber(){
        number--;
    }

    public void incWeight(){
        weight++;
    }

    public Node getParent() {
        return parent;
    }

    public void setChildOne(Node child){
        children[0] = child;
    }

    public void setChildTwo(Node child){
        children[1] = child;
    }

    public Node getChildOne(){
        return children[0];
    }

    public Node getChildTwo(){
        return children[1];
    }

    public char getSymbol(){
        return symbol;
    }

    public void setSymbol(char symbol){
        this.symbol = symbol;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type=type;
    }

}
