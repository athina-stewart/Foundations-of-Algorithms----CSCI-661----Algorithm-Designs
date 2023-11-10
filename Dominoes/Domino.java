public class Domino {
    int top;
    int bottom;

    public Domino(int one, int two){
        if (one < two){
            top = one;
            bottom = two;
        } else {
            top = two;
            bottom = one;
        }
    }

    @Override
    public String toString(){
        return "top: " + this.top + ", bottom: " + this.bottom + ".";
    }
}
