/**
 * Created by stryker on 06/12/16.
 */
public class JoueurColorLines extends Joueur{

    public JoueurColorLines(String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
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
        return pseudo;
    }

    @Override
    public String[] jouer() {
        return new String[0];
    }
}
