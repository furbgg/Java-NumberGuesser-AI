import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Zahlenratenv2 {
    private static final int min_Zahl = 0;
    private static final int max_Zahl = 100;

    public static void main(String[] args) {

        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int geheime_Zahl = r.nextInt(max_Zahl + 1);
        // System.out.println("(Für Test) Geheime Zahl ist ====" + geheime_Zahl);
        ArrayList<Integer> mooegliche_Zahlen = new ArrayList<>();
        for (int i = min_Zahl; i <= max_Zahl; i++) {
            mooegliche_Zahlen.add(i);
        }

        ArrayList<Integer> bisherige_Tipps = new ArrayList<>();
        boolean werIstDran = r.nextBoolean();

        System.out.println("Das spiel beginnt!");
        if (werIstDran) {
            System.out.println("Du fangst an!");
        } else {
            System.out.println("KI fangst an.");
        }
        while (true) {
            System.out.println("\n---------------------------------------------");
            System.out.println("Bisherige Tipps: " + bisherige_Tipps);
            System.out.println("Verbleibende Möglichkeiten (" + mooegliche_Zahlen.size() + " Stück):");
            print(mooegliche_Zahlen);

            int aktuellerTipp;

            if (werIstDran) {
                System.out.print("Dein Tipp: ");

                while (!sc.hasNextInt()) {
                    System.out.println("Ungültige Eingabe! Bitte eine Zahl eingeben.");
                    sc.next();
                }
                aktuellerTipp = sc.nextInt();
            } else {
                aktuellerTipp = kiTipp(mooegliche_Zahlen);
                System.out.println("Computer Tipp ist = " + aktuellerTipp);
            }

            bisherige_Tipps.add(aktuellerTipp);
            String feedbacktext = feedback(geheime_Zahl, aktuellerTipp);
            System.out.println(feedbacktext);
            uptade_List(mooegliche_Zahlen, aktuellerTipp, feedbacktext);

            if (aktuellerTipp == geheime_Zahl) {
                System.out.println("---------------------------------------------");
                if (werIstDran) {
                    System.out.println("Glückwunsch! Du hast gewonnen!");
                } else {
                    System.out.println("Die KI hat gewonnen.");
                }
                break;
            }
            werIstDran = !werIstDran;
        }
        sc.close();
    }

    static void uptade_List(ArrayList<Integer> a1, int tipp, String feedback) {
        int[] grenze = parseFeedback(feedback);
        int lowerdist = grenze[0];
        int upperdist = grenze[1];
        if (feedback.equals("Feedback : Weit weg über 20 daneben")) {
            removeRange(a1, tipp - (lowerdist - 1), tipp + (lowerdist - 1));
            return;
        }

        int lowerbound1 = tipp - upperdist;
        int upperbound1 = tipp - lowerdist;
        int lowerbound2 = tipp + lowerdist;
        int upperbound2 = tipp + upperdist;
        removeRange(a1, upperbound2 + 1, max_Zahl);
        removeRange(a1, upperbound1 + 1, lowerbound2 - 1);
        removeRange(a1, min_Zahl, lowerbound1 - 1);


    }

    static int[] parseFeedback(String feedback) {
        if (feedback.equals("treffer")) {
            return new int[]{0, 0};
        }
        if (feedback.equals("Feedback : Fast da!(1-3 daneben)")) {
            return new int[]{1, 3};
        }
        if (feedback.equals("Feedback : Relativ nahe!(4-10 daneben)")) {
            return new int[]{4, 10};
        }
        if (feedback.equals("Feedback : Nicht ganz so weit weg (11-20 daneben)")) {
            return new int[]{11, 20};
        }
        if (feedback.equals("Feedback : Weit weg über 20 daneben")) {
            return new int[]{21, max_Zahl};
        }

        return new int[]{-1, -1};
    }

    static String feedback(int geheim, int tipp) {
        int distanz = Math.abs(geheim - tipp);
        if (distanz == 0) {
            return "treffer";
        } else if (distanz <= 3) {
            return "Feedback : Fast da!(1-3 daneben)";
        } else if (distanz <= 10) {
            return "Feedback : Relativ nahe!(4-10 daneben)";
        } else if (distanz <= 20) {
            return "Feedback : Nicht ganz so weit weg (11-20 daneben)";
        } else {
            return "Feedback : Weit weg über 20 daneben";
        }
    }

    static void removeRange(ArrayList<Integer> a1, int lower, int upper) {
        if (lower > upper) {
            return;
        }
        lower = Math.max(min_Zahl, lower);
        upper = Math.min(max_Zahl, upper);
        for (int i = lower; i <= upper; i++) {
            a1.remove(Integer.valueOf(i));
        }

    }


    static void print(ArrayList<Integer> a1) {
        for (int i = min_Zahl; i <= max_Zahl; i++) {
            if (a1.contains(i)) {
                System.out.print("*");
            } else {
                System.out.print(".");
            }
        }
        System.out.println();
    }

    static int kiTipp(ArrayList<Integer> mooegliche_Zahlen) {
        if (mooegliche_Zahlen.isEmpty()) {
            return -1;
        } else {
            return mooegliche_Zahlen.get(mooegliche_Zahlen.size() / 2);
        }
    }


}

