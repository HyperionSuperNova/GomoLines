/**
 * Created by merat on 17/11/16.
 */
public class CaseGomoku extends Case{


    public CaseGomoku(int x, int y){
        super(x,y);
    }

    public CaseGomoku(int x, int y, PionGomoku pionGomoku){
        super(x,y,pionGomoku);
    }

    public Pion getPionGomoku(){
        return pion;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setPion(String s){
        this.pion = new PionGomoku(s);
    }


    public void afficher(){
        if(!isEmpty()) pion.afficher();
        else System.out.print("- ");
    }

    public boolean isItEquals(Pion a){
        return pion.toString().equals(pion.toString());
    }
}
