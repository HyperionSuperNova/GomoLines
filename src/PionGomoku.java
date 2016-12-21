import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Stryker on 04/12/16.
 */
public class PionGomoku extends Pion{
    Couleur cool;          // TRUE pour blanc, FALSE pour noir

    public PionGomoku(String s){
        switch(s){
            case "noir":
                cool = Couleur.NOIR;break;
            case "blanc":
                cool = Couleur.BLANC;break;
        }
    }

    enum Couleur{BLANC, NOIR;}
    public String toString(){
        return this.cool.name();
    }
}
