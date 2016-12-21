import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Stryker on 04/12/16.
 */
public class JoueurGomoku extends Joueur {
    public ArrayList<PionGomoku> pawns;
    public JoueurGomoku(String pseudo,String role, int n){
        this.pseudo = pseudo;
        this.score = 0;
        this.pawns = new ArrayList<PionGomoku>();
        addPawns(n,role);
    }

    private void addPawns(int n, String s){
        while(pawns.size() < n){
            pawns.add(new PionGomoku(s));
        }
    }


    @Override
    public void setScore(int n) {
        this.score += n;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public String getPseudo() {
        return this.pseudo;
    }
}
