import java.util.Scanner;

/**
 * Created by Stryker on 17/11/16.
 */
public class Launcher {

    public Launcher() {
        userExperience();
    }

    private void userExperience(){
        System.out.println("Bienvenue!");
        System.out.println("Veuillez entrer les dimensions du plateau:  (de la forme longueur x largeur)");
        userChoiceOfGame();
    }

    private String[] userDimensions(){
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        String []s = res.split("x");
        return s;
    }

    private void userChoiceOfGame(){
        String [] s = userDimensions();
        int z = (Integer.parseInt(s[0])*Integer.parseInt(s[1]))/2;
        Scanner sc = new Scanner (System.in);
        System.out.println("Voulez-vous jouer avec un robot?");
        String res = sc.nextLine();
        if (res.equals("oui")) {
            System.out.print("Pseudo joueur: ");
            res = sc.nextLine();
            PlateauGomoku p = new PlateauGomoku(Integer.parseInt(s[0]), Integer.parseInt(s[1]),new Joueur1Gomoku(res,z),new Joueur2Gomoku("Robot",z));
            p.afficher();
            while(!p.isFull()){
                System.out.print("Coup " + p.blanc.getPseudo() + ": ");
                String s1 = sc.nextLine();
                String[] t1 = s1.split(",");
                p.faire(Integer.parseInt(t1[1]), Integer.parseInt(t1[0]), true);
                int a = (int)(Math.random()*p.getLongueur());
                int b = (int)(Math.random()*p.getLargeur());
                System.out.println("Coup " + p.noir.getPseudo() + ": "+b+","+a);
                while(!p.getCases()[a][b].isEmpty()){
                    a = (int)(Math.random()*p.getLongueur());
                    b = (int)(Math.random()*p.getLongueur());
                    System.out.println("Coup " + p.noir.getPseudo() + ": "+b+","+a);
                }
                p.faire(a,b, false);
                p.afficher();
            }
            p.afficherScore();

        }
        if(res.equals("non")){
            System.out.print("Pseudo premier joueur :");
            res = sc.nextLine();
            Joueur1Gomoku blanc = new Joueur1Gomoku(res,z);
            System.out.print("Pseudo deuxième joueur: ");
            res = sc.nextLine();
            Joueur2Gomoku noir = new Joueur2Gomoku(res,z);
            PlateauGomoku p = new PlateauGomoku(Integer.parseInt(s[0]), Integer.parseInt(s[1]),blanc,noir);
            p.afficher();
            while (!p.isFull()) {
                System.out.print("Coup " + blanc.getPseudo() + ": ");
                String s1 = sc.nextLine();
                String[] t1 = s1.split(",");
                p.faire(Integer.parseInt(t1[1]), Integer.parseInt(t1[0]), true);
                p.afficher();
                System.out.print("Coup " + noir.getPseudo() + ": ");
                String s2 = sc.nextLine();
                String[] t2 = s2.split(",");
                p.faire(Integer.parseInt(t2[1]), Integer.parseInt(t2[0]), false);
                p.afficher();
            }
            p.afficherScore();
        }
    }

    public static void main(String [] args) {
        Launcher test = new Launcher();
    }
}
