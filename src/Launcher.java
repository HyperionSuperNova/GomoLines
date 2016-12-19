import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Stryker on 17/11/16.
 */
public class Launcher {

    public Launcher() {
        userExperience();
    }

    private void userExperience(){
        int dimension = Integer.parseInt(JOptionPane.showInputDialog("Welcome please enter the dimension of the board"));
        JFrame frame = new JFrame("Game Selector");
        String[] games = {"Gomoku","ColorLines"};
        String s = (String) JOptionPane.showInputDialog(frame,"What game do you wanna Play","Games",JOptionPane.QUESTION_MESSAGE,null,games,games[0]);
        if(s.equals("Gomoku")){
            GomokuLaunch(dimension);
        }else{
            ColorLinesLaunch(dimension);
        }
    }

    public void GomokuLaunch(int dimension){
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous jouer avec un robot?");
        String res = sc.nextLine().toLowerCase();
        //Plateau p = null;
        JeuGraphique j = null;
        if (res.equals("oui")) {
            System.out.print("Pseudo joueur: ");
            res = sc.nextLine();
            j = new JeuGraphiqueGomoku(new PlateauGomoku(dimension, new JoueurGomoku(res, "blanc", dimension), new JoueurGomoku("Robot", "noir", dimension)));
            j.jouer("robot");
            //p = new PlateauGomoku(dimension, new JoueurGomoku(res, "blanc", dimension), new JoueurGomoku("Robot", "noir", dimension));
            //f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //f.setLocationByPlatform(true);

            // ensures the frame is the minimum size it needs to be
            // in order display the components within it
            //f.pack();
            // ensures the minimum size is enforced.
            //f.setMinimumSize(f.getSize());
            //f.setVisible(true);
            /*j.getPlateau().initializeThePlate();
            j.getPlateau().afficher();
            j.getPlateau().jouer("robot");*/
        }else if (res.equals("non")) {
            System.out.print("Pseudo premier joueur :");
            res = sc.nextLine();
            JoueurGomoku a = new JoueurGomoku(res,"blanc",dimension);
            System.out.print("Pseudo deuxième joueur: ");
            res = sc.nextLine();
            JoueurGomoku b = new JoueurGomoku(res,"noir",dimension);
           // p = new PlateauGomoku(dimension,a,b);
            j = new JeuGraphiqueGomoku(new PlateauGomoku(dimension, a, b));
            j.getPlateau().initializeThePlate();
            //j.getPlateau().jouer("humain");
        }
    }

    public void ColorLinesLaunch(int dimension){
        System.out.println("lel");
        //Plateau p = new PlateauColorLines(dimension, new JoueurColorLines("Nabil"));
        JeuGraphique j = new JeuGraphiqueColorLines(new PlateauColorLines(dimension, new JoueurColorLines("Nabil")));
        j.getPlateau().initializeThePlate();
        //while(!j.getPlateau().isFull()) j.getPlateau().jouer("classique");
    }

    public static void main(String [] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                Launcher test = new Launcher();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
