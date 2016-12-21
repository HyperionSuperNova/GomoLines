import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Stryker on 17/11/16.
 */
public class Launcher {

    public Launcher() {
        userExperience();
    }

    // fenêtre pop-up demandant de rentrer une dimension puis à quel type de jeu on veut jouer
    private void userExperience() {
        JFrame frame = new JFrame("Game Choices");
        int dimension = Integer.parseInt(JOptionPane.showInputDialog(frame, "Welcome please enter the dimension of the board"));
        String[] games = {"Gomoku", "ColorLines"};
        String s = (String) JOptionPane.showInputDialog(frame, "What game do you wanna Play", "Games", JOptionPane.QUESTION_MESSAGE, null, games, games[0]);
        if (s.equals("Gomoku")) {
            GomokuLaunch(dimension);
        } else {
            ColorLinesLaunch(dimension);
        }
    }

    // permet de choisir de jouer avec un robot ou non et lance le jeu + interface graphique Gomoku
    public void GomokuLaunch(int dimension) {
        JFrame frame = new JFrame("Game Type Selector");
        String[] choice = {"yes", "no"};
        String res = (String) JOptionPane.showInputDialog(frame, "Do you wanna play with a robot ?", "Choices", JOptionPane.QUESTION_MESSAGE, null, choice, choice[0]);
        JeuGraphique j = null;
        if (res.equals("yes")) {
            res = JOptionPane.showInputDialog("What's your name ?");
            j = new JeuGraphiqueGomoku(new PlateauGomoku(dimension, new JoueurGomoku(res, "blanc", dimension), new JoueurGomoku("Robot", "noir", dimension)));
            j.v.setTitle("1 Player Gomoku");
            j.jouer("robot");
        } else {
            res = JOptionPane.showInputDialog("What's the name of the first player ?");
            String j2 = JOptionPane.showInputDialog("What's the name of the second player ?");
            j = new JeuGraphiqueGomoku(new PlateauGomoku(dimension, new JoueurGomoku(res, "blanc", dimension), new JoueurGomoku(j2, "noir", dimension)));
            j.v.setTitle("2 Player Gomoku");
            j.jouer("humain");
        }
    }

    // permet de choisir si l'on veut jouer au colorLines classique ou à la variante et lance jeu + interface graphique colorLines
    public void ColorLinesLaunch(int dimension) {
        JFrame frame = new JFrame("Game Type Selector");
        String[] choice = {"Classic Game", "Variation"};
        String res = (String) JOptionPane.showInputDialog(frame, "Choose your game type ?", "Choices", JOptionPane.QUESTION_MESSAGE, null, choice, choice[0]);
        if (res.equals("Classic Game")) {
            res = JOptionPane.showInputDialog("What's your name ?");
            JeuGraphique j = new JeuGraphiqueColorLines(new PlateauColorLines(dimension, new JoueurColorLines(res)));
            j.v.setTitle("Color Lines Classique");
            j.jouer("classique");
        } else {
            res = JOptionPane.showInputDialog("What's your name ?");
            JeuGraphique j = new JeuGraphiqueColorLines(new PlateauColorLines(dimension, new JoueurColorLines(res)));
            j.v.setTitle("Color Lines Variante");
            j.jouer("variante");
        }


    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                Launcher test = new Launcher();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
