/**
 * Created by Stryker on 04/12/2016.
 */
public class CaseColorLines extends Case {

    public CaseColorLines(int x, int y) {
        super(x, y);
    }

    public CaseColorLines(int x, int y, PionColorLines pcl){
        super(x,y,pcl);
    }

    @Override
    public boolean isEmpty() {
        return pion == null;
    }

    @Override
    public boolean isItEquals(Pion a) {
        if(this.toString().equals(a.toString())){
            return true;
        }
        return false;
    }

    @Override
    public void afficher() {
        if(!isEmpty()) pion.afficher();
        else System.out.print("- ");
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setPion(Pion a){
        this.pion = a;
    }

}
