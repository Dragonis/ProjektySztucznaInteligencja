package org.WojtekSasiela;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.WojtekSasiela.Perceptron.ANSI_RESET;

/**
 * Created by Dragonis on 17.01.2016.
 */

public class Perceptron {
    // konfiguracja Perceptronu
    public int n;          // liczba wejść, przy n+1 wagach

    public double s;                // sygnał

    public double y;                // wyjście

    public int f;                   // 1 - unipolarna funkcja, -1 - bipolarna funkcja

    public double[] w;              // tablica wag, pamięć perceptronu

// elementy związane z uczeniem:

    public double[][] iSetX;        // tablica tablic (wektro wektorów) wejściowych ze zbioru uczącego (kolumny: x0,x1,...,xn)

    public double[] iSetD;          // tablica (wektor) wyjść wzorccowych dla wektorów wejściowych ze zbioru ucz. (kolumna: d)

    public int m;                   // liczba danych w zbiorze uczącym (liczba par (x,d) )

    public double r;                // Ro, współczynnik uczenia

    public int krok;                   // informacja o numerze kroku uczenia, startowo krok = 0;

    public int nr_wiersza;

    public int iteracjaTabeli;

    public Random random;

// zachowanie Perceptronu:

    public Perceptron(int _n, int _f, double[] _w, double[][] _iSetX, double[] _iSetD, int _m, double _r) {
        // wartościowanie pól:
        this.n = _n;
        this.r = _r;
        this.f = _f; // 1 - unipoler, -1 - bipolar
        this.m = _m;
        this.krok = 0;
        this.s = 0;
        this.y = _f;

        // inicjacja zbioru uczącego
        this.iSetX = _iSetX;
        this.iSetD = _iSetD;
        this.w = _w;



//        // inicjacja zbioru uczącego
//        this.iSetX = new double[m][];
//        this.iSetD = new double[m];
//
//        for (int i = 0; i < this.m; i++) {
//            this.iSetX[i] = new double[n + 1];
//            for (int j = 0; j < this.n + 1; j++) {
//                this.iSetX[i][j] = _iSetX[i][j];
//            }
//            this.iSetD[i] = _iSetD[i];
//        }
//
//        this.w = new double[n + 1];
//        for (int i = 0; i < n + 1; i++) {
//            this.w[i] = _w[i];
//        }
    }

    // metody ComputeS i ComputeY obliczają sygnał i wyjście i zapisują do pól s i y !!!

    public double ComputeS(double[] _x) {
        // ComputeS oblicza sygnal z 1 wiersza
        double sygnal = 0;
        for(int i=0; i < 1; i++) // iteruj 2 kolumny ( wprowadzono 1, bo wtedy jest 2)
        {
            sygnal += _x[i] * w[i]; // wyliczenie sygnalu dla 1 wiersza
        }
        this.s = sygnal;
        return sygnal;
    }

    public double ComputeY(double[] _x) {
        double wynik = 0;
        switch (this.f) {
            case 1: // unipolarna
                if (ComputeS(_x) > 0) {
                    wynik = 1;
                } else {
                    wynik = 0;
                }
                break;
            case -1: // bipolarna
                if (ComputeS(_x) > 0) {
                    wynik = 1;
                } else {
                    wynik = -1;
                }
                break;
            default:
                System.out.println("Błędna wartosć dla funkcji aktywacji. (Przyjmuje tylko 1 i -1) )");
                break;
        }
        this.y = wynik;
        return y;
    }

    // metoda wykonująca jeden krok procesu uczenia
    public void LearningStep() {
        int a = -1, b = 1;

        // macierz wag bedzie zapisana w postaci macierzy
//        [ DrawWeight(a, b); DrawWeight(a, b); DrawWeight(a, b);
//        DrawWeight(a, b); DrawWeight(a, b); DrawWeight(a, b);
//        DrawWeight(a, b); DrawWeight(a, b); DrawWeight(a, b);]

        w[0] = DrawWeight(a, b);
        w[1] = DrawWeight(a, b);
        w[2] = DrawWeight(a, b);
        w[3] = DrawWeight(a, b);

        
            if(krok >= 0 && krok < 1*w.length)
            {
                nr_wiersza = krok;
            }
            if(krok >= 1*w.length && krok <= 2*w.length)
            {
                iteracjaTabeli = 1;
                nr_wiersza = krok % w.length;
            }
            if(krok >= 2*w.length && krok <= 3*w.length)
            {
            iteracjaTabeli = 2;
            nr_wiersza = krok % w.length;
            }

        Test();
        System.out.println(toString());

    }

    // metoda resetuje proces uczenia (reset pola krok oraz losowanie wag?)
    public void ResetLearningProcess() {
        this.w = null;
        this.f = 0;
        this.krok = 0;
        this.iSetD = null;
        this.iSetX = null;
        this.m = 0;
        this.n = 0;
        this.r = 0.0;
        this.s = 0.0;
    }

    // metoda wyznaczająca wartość funkcji aktywacji dla zadanej wartości, metoda nie zmienia stanu klasy (w odróżnieniu od metody ComputeY(double[] _x))
    public double Y(double s) {

        double wynik =0.0;

        switch (f){
            case 1: //unipolarny
                if (s > 0) {
                    wynik = 1;
                } else {
                    wynik = 0;
                }
                 break;
            case -1: //bipolarny
                if (s > 0) {
                    wynik = 1;
                } else {
                    wynik = -1;
                }
                break;
            default:
                System.out.println("Błędna wartosć dla funkcji aktywacji. (Przyjmuje tylko 1 i -1) )");
                break;
        }
        return wynik;
        }


    // metoda testująca Perceptron, zwraca liczbę niezgodnych wyjść dla zboru uczącego
    // 0 na wyjściu oznacza, że neruon nauczył się już funkcji ze zbioru uczącego
    public int Test() {

        double obliczonySygnal = ComputeS(iSetX[nr_wiersza]);
        double obliczoneY = ComputeY(iSetX[nr_wiersza]);
        int brakPunktuStalego = 0;
        int punktStaly = 0;

        if (obliczoneY == iSetD[nr_wiersza]) {
            punktStaly++;
        } else {
            brakPunktuStalego++;
        }
        return brakPunktuStalego;
    }

    // metoda losująca wagi o wartościach z przedziału [a;b]
    public double DrawWeight(double a, double b) {

        //  Random random = new Random();
        //  return ThreadLocalRandom.current().nextDouble(a, b);
        //  return a + (b - a) * random.nextDouble();
        //  return Math.random() * (b - a) + a;

        random = new Random();
        return  a + (b - a) * random.nextDouble();
    }

    // metoda pozwalająca na konwersją obiektu klasy Perceptron na łańcuch znaków (na potrzeby wyświetlania stanu obiektu)

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    @Override
    public String toString() {
        String funkcjaAktywacji = "";
        if (f == 1){ funkcjaAktywacji = "unipolarna"; };
        if (f == -1){ funkcjaAktywacji = "bipolarna"; };
        String napis = "";

        String leftAlignFormat = "| %-4s              | %-4s    | %-4s    | %-4s | "+ANSI_BLUE+"%.2f"+ANSI_RESET+" "+ANSI_RED+"| %.2f"+ANSI_RESET+"| %.2f    | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %n";

        System.out.format("+------------------+---------+---------+------+------+------+---------+------+------+------+------+------+------+------+------+------+%n");
        System.out.format("| iteracja tabeli  |  krok   |  wiersz |  n   |  "+ANSI_BLUE+"s"+ANSI_RESET+"   |  "+ANSI_RED+"y"+ANSI_RESET+"   |  w0     |  w1  |  w2  |  w3  |  d0  |  d1  |  d2  |  d3  |  x0  |  x1  |%n");
        System.out.format("+------------------+---------+---------+------+------+------+---------+------+------+------+------+------+------+------+------+------+%n");
            System.out.format(leftAlignFormat,iteracjaTabeli, krok, nr_wiersza, n, s, y, w[0], w[1], w[2], w[3], iSetD[0], iSetD[1], iSetD[2], iSetD[3], iSetX[nr_wiersza][0], iSetX[nr_wiersza][1]);
        System.out.format("+------------------+---------+------+------+------+--------+------+------+------+------+------+------+------+------+------+%n");

//        napis+="Stan perceptronu \n";
//        napis+= "Funkcja aktywacji: "+ funkcjaAktywacji +", \n";
//        napis+="====== NR WIERSZA: " + nr_wiersza + " ======= \n";
//        napis+= "N= "+ n +", \n";
//        napis+= "s="+ s +", \n";
//        napis+= "y="+ y +", \n";
//        napis+= "Wagi:, \n";
//        napis+= "  w0 | w1 | w2 | w3 \n";
//        napis+= "------------------\n";
//        napis+= " "+w[0]+" |  "+w[1]+" |  "+w[2]+"  | "+w[3]+"\n";
//        napis+= "Macierz d:, \n";
//        napis+= "d0 | d1 | d2 | d4\n";
//        napis+= "-----------------\n";
//        napis+= " "+iSetD[0]+" |  "+iSetD[1]+" |  "+iSetD[2]+" | "+iSetD[3]+" \n";
//        napis+= "Neurony:, \n";
//        napis+= "x0 | x1 | x2 \n";
//        napis+= "-------------\n";
//        napis+= " "+iSetX[krok][0]+" |  "+iSetX[krok][1]+" | \n";
//                            //        napis+= " "+iSetX[1][0]+" |  "+iSetX[1][1]+" | \n";
//                            //        napis+= " "+iSetX[2][0]+" |  "+iSetX[2][1]+" | \n";
//                            //        napis+= " "+iSetX[3][0]+" |  "+iSetX[3][1]+" | \n";
//                            //
//                            //        napis+= " "+iSetX[0][0]+" |  "+iSetX[0][1]+" |  "+iSetX[0][2]+" \n";
//                            //        napis+= " "+iSetX[1][0]+" |  "+iSetX[1][1]+" |  "+iSetX[1][2]+" \n";
//                            //        napis+= " "+iSetX[2][0]+" |  "+iSetX[2][1]+" |  "+iSetX[2][2]+" \n";
//                            //        napis+= " "+iSetX[3][0]+" |  "+iSetX[3][1]+" |  "+iSetX[3][2]+" \n";
//
//        napis+= "Punkt stały: "+ Test() + "\n";

        return napis;
    }
}

