/**
 * Created by Stryker on 04/12/16.
 */
public class PionGomoku extends Pion{
    private boolean blancNoir;          // TRUE pour blanc, FALSE pour noir

    public PionGomoku(boolean blancNoir){
        this.blancNoir = blancNoir;
    }

    public void afficher(){
        if(blancNoir) System.out.print("B"+" ");
        else System.out.print("N"+" ");
    }

    public boolean whichColor(){
        return blancNoir;
    }
}
