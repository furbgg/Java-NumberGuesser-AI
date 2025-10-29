import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ZahlenRatenv1 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        boolean laufen = true;
        while (laufen) {
            System.out.println("\n+++Zahlenraten Menu+++");
            System.out.println("Wahle dein Level....");
            System.out.println("1:Level 1 = Grosser/ Kleiner game , 9 versuche");
            System.out.println("2:Level 2 = Abstand Game mit Feedback");
            System.out.println("3:Level 3 = Gegen KI");
            System.out.println("0 ist spiel beendet");
            int wahl = sc.nextInt();

            if (wahl == 1) {
                System.out.println("\n+++Level 1 Gestartet+++");
                int zufallszahl = r.nextInt(101);
                int versuche = 9;
                boolean aktivv1 = true;
                System.out.println("Schätze die Zahl.(0-100)Du hast 9 versuchen");
                while (versuche > 0 && aktivv1) {
                    System.out.printf("Noch %d versuche Übrig.Dein Tipp;", versuche);
                    int tipp = sc.nextInt();
                    if (tipp < zufallszahl) {
                        System.out.println("Denke an eine grössere Zahl.");
                    } else if (tipp > zufallszahl) {
                        System.out.println("Denke an eine kleinere Zahl!");
                    } else {
                        System.out.println("Du bist korrrekt!");
                        aktivv1 = false;
                    }
                    versuche--;
                    if (versuche == 0 && aktivv1) {
                        System.out.println("Du hast keine versuche noch!" + "Die Zahl war " + zufallszahl);
                    }
                }

            } else if (wahl == 2) {
                System.out.println("\n+++Level2 Gestartet+++");
                int zufallszahlv2 = r.nextInt(101);
                ArrayList<Integer> btipp = new ArrayList<>();
                boolean aktivv2 = true;
                System.out.println("Schätze die Zahl.(0-100). (Feedback edition)");
                while (aktivv2) {
                    System.out.println("Dein Tipp...");
                    int tippv2 = sc.nextInt();
                    btipp.add(tippv2);
                    System.out.println("Bisherige Tipp " + btipp);
                    int abstand = Math.abs(tippv2 - zufallszahlv2);
                    if (tippv2 == zufallszahlv2) {
                        System.out.println("Korrekt! Du hast dieZahl  " + zufallszahlv2 + " erwraten!");
                        aktivv2 = false;
                    } else if (abstand <= 3) {
                        System.out.println("Feedback : Fast da!(1-3 daneben");
                    } else if (abstand <= 10) {
                        System.out.println("Feedback : Relativ nahe!(4-10 daneben");
                    } else if (abstand <= 20) {
                        System.out.println("Feedback : Nicht ganz so weit weg (11-20 daneben");
                    } else {
                        System.out.println("Feedback : Weit weg über 20 daneben");
                    }

                }


            } else if (wahl == 3) {
                System.out.println("\n+++Level3 Gestartet+++");
                int zufallszahlv3 = r.nextInt(101);
                boolean aktivv3 = false;
                boolean weristdran = r.nextBoolean();
                int pcmin = 0;
                int pcmax = 100;
                System.out.println("Du spielst gegen KI ; Schätze die Zahl.(0-100)");
                if (weristdran) {
                    System.out.println("KI ´beginn!");
                } else {
                    System.out.println("Du beginnst!");
                }
                while (!aktivv3) {
                    int tipp;

                    if (weristdran) {

                        tipp = r.nextInt(pcmax - pcmin + 1) + pcmin;
                        System.out.println("Ki Tipp = " + tipp);
                        if (tipp == zufallszahlv3) {
                            System.out.println("KI ist gewinnen!");
                            aktivv3 = true;
                        }
                    } else {
                        System.out.print("Dein Tipp?");
                        tipp = sc.nextInt();
                        if (tipp == zufallszahlv3) {
                            System.out.println("Du gewinnst!!");
                            aktivv3 = true;
                        }
                    }

                    if (!aktivv3) {
                        if (tipp < zufallszahlv3) {
                            System.out.println("eine grössere Zahl schätzen!");
                            if (pcmin <= tipp) {
                                pcmin = tipp + 1;
                            }
                        } else { // tipp > zufallszahlv3
                            System.out.println("eine kleinere Zahl schätzen!");
                            if (tipp <= pcmax) {
                                pcmax = tipp - 1;
                            }
                        }
                    }
                    weristdran = !weristdran;
                }

            } else if (wahl == 0) {
                System.out.println("Spiel beendet. Auf Wiedersehen!");
                laufen = false;

            } else {
                System.out.println("Bitte wähle eine Option aus dem Menü.");
            }

        }

    }
}
