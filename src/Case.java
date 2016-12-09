/**
 * Created by Stryker on 04/12/2016.
 */
public class Case {
    protected int x;
    protected int y;
    protected Pion pion;

    public Case(int x, int y){
        this.x = x;
        this.y = y;
        this.pion = null;
    }

    public Case(int x, int y, Pion p){
        this(x,y);
        this.pion = p;
    }

    public void afficher(){
        if(!isEmpty()) pion.afficher();
        else System.out.print("- ");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pion getPion() {
        return pion;
    }

    public void fabrique(String s) {
        this.pion = Pion.factory(s);
    }

    public void setPion(Pion a){
        this.pion = a;
    }

    public boolean isItEquals(Pion a){
        return pion.toString().equals(pion.toString());
    }

    public boolean isEmpty(){
        return pion == null;
    }
}
