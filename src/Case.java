/**
 * Created by Stryker on 04/12/2016.
 */
public abstract class Case {
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

    public boolean isEmpty(){
        return (pion == null);
    }
    public abstract boolean isItEquals(Pion a);
    public abstract void afficher();
    public abstract int getX();
    public abstract int getY();
}
