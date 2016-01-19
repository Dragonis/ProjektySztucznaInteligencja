package org.WojtekSasiela;

import javax.swing.*;

public class Main {



    public static void main(String[] args) {

        System.out.println("Projekt Perceptron");

        double[] w = new double[4]; // pamiec perceptronu (macierz wag dla aktualnie iterowanego wiersza)

        double[][] iSetX = new double[4][2];
        iSetX[0][0] = 0; iSetX[0][0] = 0;
        iSetX[0][0] = 0; iSetX[0][0] = 1;
        iSetX[0][0] = 1; iSetX[0][0] = 0;
        iSetX[0][0] = 1; iSetX[0][0] = 1;

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

        // macierz wag bedzie zapisana w postaci macierzy
//        [ DrawWeight(a, b); DrawWeight(a, b); DrawWeight(a, b);
//        DrawWeight(a, b); DrawWeight(a, b); DrawWeight(a, b);
//        DrawWeight(a, b); DrawWeight(a, b); DrawWeight(a, b);]

        int a = -2, b = 2;
        w[0] = p.DrawWeight(a, b);
        w[1] = p.DrawWeight(a, b);
        w[2] = p.DrawWeight(a, b);
        w[3] = p.DrawWeight(a, b);

        for (int i = 1; i <= w.length; i++) // liczba wierszy do wykonania np. 30
        {
            p.LearningStep();
        }

        p.ResetLearningProcess();

        Perceptron_WojciechSasiela okno = new Perceptron_WojciechSasiela();




    }
}
