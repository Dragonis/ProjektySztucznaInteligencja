package org.WojtekSasiela;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * Created by Dragonis on 17.01.2016.
 */
public class PerceptronTest {

    double[] w = new double[4]; // pamiec perceptronu (macierz wag dla 1 wiersza)
    double[][] iSetX = new double[4][2];
    double[] iSetD = new double[4];
    Perceptron p = null;

    @org.junit.Before
    public void setUp() throws Exception {

        w[0] = 0;
        w[1] = -1;
        w[2] = 1;
        w[3] = 0;

        iSetX[0][0] = -1; iSetX[0][1] = -1;
        iSetX[1][0] = -1; iSetX[1][1] = 1;
        iSetX[2][0] = 1;  iSetX[2][1] = -1;
        iSetX[3][0] = 1;  iSetX[3][1] = 1;

        iSetD[0] = 1;
        iSetD[1] = 1;
        iSetD[2] = 1;
        iSetD[3] = 1;

        p = new Perceptron(
                3, // liczba wejść, przy n+1 wagach
                -1,  // 1 - unipolarna funkcja, -1 - bipolarna funkcja
                w,  // tablica wag, pamięć perceptronu
                iSetX, // tablica tablic (wektro wektorów) wejściowych ze zbioru uczącego (kolumny: x0,x1,...,xn)
                iSetD, // tablica (wektor) wyjść wzorccowych dla wektorów wejściowych ze zbioru ucz. (kolumna: d)
                4,// liczba danych w zbiorze uczącym (liczba par (x,d) )
                1// Ro, współczynnik uczenia
        );
    }

    @org.junit.After
    public void tearDown() throws Exception {
        w = null;
        iSetX = null;
        iSetD = null;
        p = null;
    }

    @org.junit.Test
    public void testComputeS() throws Exception {

        for (int i = 0; i <= 3; i++) { // oblicz sygnal z i-tego wiersza "tabelki" (4 wiersze tablicy zapisujemy liczbą 3)
            double wynikSygnalu = p.ComputeS(iSetX[i]);

            double sprawdzeniePoprawnosciSygnalu =
                                             w[0] * iSetX[i][0] +
                                             w[1] * iSetX[i][0] +
                                             w[2] * iSetX[i][0] +
                                             w[3] * iSetX[i][0] +
                                             w[0] * iSetX[i][1] +
                                             w[1] * iSetX[i][1] +
                                             w[2] * iSetX[i][1] +
                                             w[3] * iSetX[i][1];

            assertEquals(sprawdzeniePoprawnosciSygnalu, wynikSygnalu, 0.001);
            // Odpowiedż: Dla każdego wiersza (w tabelach ISetX i w), sygnał jest prawidłowo obliczany.
        }
    }

    @org.junit.Test
    public void testComputeY() throws Exception
    {

        for(int i=0; i <= 3; i++)
        {// oblicz Y z i-tego wiersza "tabelki" (4 wiersze tablicy zapisujemy liczbą 3))

            double wynik = 0;

            switch (p.f) {
                case 1: // unipolarna
                    if (p.ComputeS(iSetX[i]) > 0) {
                        wynik = 1;
                    } else {
                        wynik = 0;
                    }
                    break;
                case -1: // bipolarna
                    if (p.ComputeS(iSetX[i]) > 0) {
                        wynik = 1;
                    } else {
                        wynik = -1;
                    }
                    break;
                default:
                    System.out.println("Błędna wartosć dla funkcji aktywacji. (Przyjmuje tylko 1 i -1) )");
                    break;
            }

            assertEquals(wynik, p.ComputeY(iSetX[i]), 0.001);
        }
    }



    @org.junit.Test
    public void testLearningStep() throws Exception {

    }

    @org.junit.Test
    public void testResetLearningProcess() throws Exception {
        p.ResetLearningProcess();
        assertEquals(p.w,null);
        assertEquals(p.f,0);
        assertEquals(p.nr_wiersza,0);
        assertEquals(p.iSetD,null);
        assertEquals(p.iSetX,null);
        assertEquals(p.m,0);
        assertEquals(p.n,0);
        assertEquals(p.r,0.0, 0.00);
        assertEquals(p.s,0.0, 0.00);

    }

    @org.junit.Test
    public void testY() throws Exception {

        double wynik =0.0;

        switch (p.f){
            case 1: //unipolarny
                if (p.s > 0) {
                    wynik = 1;
                } else {
                    wynik = 0;
                }
                break;
            case -1: //bipolarny
                if (p.s > 0) {
                    wynik = 1;
                } else {
                    wynik = -1;
                }
                break;
            default:
                System.out.println("Błędna wartosć dla funkcji aktywacji. (Przyjmuje tylko 1 i -1) )");
                break;
        }
        assertEquals(wynik, p.Y(p.s), 0.01);
    }

    @org.junit.Test
    public void testTest() throws Exception {
    for (int i=0;i< 3; i++)
    { // oblicz Y z i-tego wiersza "tabelki" (4 wiersze tablicy zapisujemy liczbą 3))
        double obliczonySygnal = p.ComputeS(iSetX[i]);
        double obliczoneY = p.ComputeY(iSetX[i]);
        int brakPunktuStalego = 0;
        int punktStaly = 0;

        if (obliczoneY == iSetD[i]) {
            punktStaly++;
        } else {
            brakPunktuStalego++;
        }
        assertEquals(brakPunktuStalego, p.Test());
    }
    }

//    @org.junit.Test
//    public void testDrawWeight() throws Exception {
//        int a = -1;
//        int b = 1;
//        double losowaLiczba = ThreadLocalRandom.current().nextDouble(a, b);
//        assertEquals(losowaLiczba, p.DrawWeight(a,b), 0.001);
//    }


//    @org.junit.Test
//    public void testToString() throws Exception {
//
//    }

}