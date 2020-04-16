import org.jetbrains.annotations.NotNull;

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

    public int getNumber() {
        return number;
    }

    public int getWeight() {
        return weight;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void incNumber(){
        number++;
    }

    public void decNumber(){
        number--;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void incWeight(){
        weight++;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setChildren(Node child1, Node child2){
        children[0] = child1;
        children[1] = child2;
    }

    public void setChildOne(Node child){
        children[0] = child;
    }

    public void setChildTwo(Node child){
        children[1] = child;
    }

    public Node[] getChildren(){
        return children;
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

//    @Override
//    public int compareTo(@NotNull Node o) {
//        if(this.getWeight() > (o.getWeight()))
//            return 1;
//        else
//            return 0;
//    }
}
