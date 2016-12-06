import java.util.ArrayList;

/**
 * Created by Stryker on 04/12/16.
 */
public class Joueur2Gomoku extends Joueur {
    public ArrayList<PionGomoku> pawns;
    public Joueur2Gomoku(String pseudo, int n) {
        this.pseudo = pseudo;
        this.score = 0;
        this.pawns = new ArrayList<PionGomoku>();
        addPawns(n);
    }

    private void addPawns(int n){
        while(pawns.size() < n){
            pawns.add(new PionGomoku("noir"));
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
