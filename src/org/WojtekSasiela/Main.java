package org.WojtekSasiela;

public class Main {

    public static void main(String[] args) {
        System.out.println("Projekt Perceptron");

        // wartosci zostana wygenerowane przez metode
        double[] w = new double[4]; // pamiec perceptronu (macierz wag dla 1 wiersza)

        double[][] iSetX = new double[4][2];
        iSetX[0][0] = -1; iSetX[0][1] = -1;
        iSetX[1][0] = -1; iSetX[1][1] = 1;
        iSetX[2][0] = 1;  iSetX[2][1] = -1;
        iSetX[3][0] = 1;  iSetX[3][1] = 1;

        double[] iSetD = new double[4];
        iSetD[0] = 1;
        iSetD[1] = 1;
        iSetD[2] = 1;
        iSetD[3] = 1;

        Perceptron p = new Perceptron(
                2, // liczba wejść, przy n+1 wagach
                -1,  // 1 - unipolarna funkcja, -1 - bipolarna funkcja
                w,  // tablica wag, pamięć perceptronu
                iSetX, // tablica tablic (wektro wektorów) wejściowych ze zbioru uczącego (kolumny: x0,x1,...,xn)
                iSetD, // tablica (wektor) wyjść wzorccowych dla wektorów wejściowych ze zbioru ucz. (kolumna: d)
                4,// liczba danych w zbiorze uczącym (liczba par (x,d) )
                1// Ro, współczynnik uczenia
        );

        int a = -2, b = 2;

        w[0] = p.DrawWeight(a, b);
        w[1] = p.DrawWeight(a, b);
        w[2] = p.DrawWeight(a, b);
        w[3] = p.DrawWeight(a, b);

        for (int i = 0; i < 2; i++) // liczba krokow do wykonania np. 30
        {
            p.LearningStep();
        }

        p.ResetLearningProcess();
    }
}
