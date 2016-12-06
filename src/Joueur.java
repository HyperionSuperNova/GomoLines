/**
 * Created by merat on 17/11/16.
 */

public abstract class Joueur {
    protected String pseudo;
    protected int score;

    public abstract void setScore(int n);

    public abstract int getScore();

    public abstract String getPseudo();

    public abstract String[] jouer();
}
