package DV01_IFT2015;

/**
 * La classe {CarConfrontation} simule une confrontation entre des voitures
 * représentées par des entiers. Chaque voiture a une direction et un poids :
 * - Les valeurs positives représentent les voitures allant à droite.
 * - Les valeurs négatives représentent les voitures allant à gauche.
 * Lorsqu'elles se rencontrent, la voiture la plus lourde survit,
 * ou les deux sont détruites si leurs poids sont égaux.
 */
public class CarConfrontation {

    /**
     *Methode de confrontation
     * @param a une pile d'entier basée sur le stack quon a déja codé dans Stack.java
     * @return un tableau des voitures restantes (int)
     */
    public int[] carconfrontation(int[] a) {
            Stack st = new Stack(a.length); // utilisant le stack de l'exercice precedent

        for (int i = 0; i < a.length; i++) {
            int car = a[i]; //pour toute voiture dans le tableau a
            boolean carSurvives = true;


            // Quand le stack n'est pas vide empty et il ya potentielle de  collision
            while (!st.isEmpty() && st.top() > 0 && car < 0) {
                // Test de poid de chaque voiture en valeur absolue
                // trichotomie soit a et b d'un ensemble A : a>b ; a = b ; a < b
                if (Math.abs(st.top()) > Math.abs(car)) {
                    carSurvives = false; // Current car is destroyed
                    break;
                } else if (Math.abs(st.top()) < Math.abs(car)) {
                    st.pop(); // Stack top car is destroyed
                } else {
                    st.pop(); // Both cars are destroyed
                    carSurvives = false;
                    break;
                }
            }

            if (carSurvives) {
                st.push(car); // push la voiture presente dans le stack si elle survie

            }
        }

        // conversion dans tableau
        // result array est proportionnel aux nombre d'element restant dans le stack
        int[] result = new int[st.size()];
        int index = result.length - 1;

        while (!st.isEmpty()) {
            // Dépile un élément de la pile et le place dans le tableau à l'index actuel
            result[index] = st.pop();
            index--;
        }

        return result;
    }

    /**
    * Complexite temporelle : O(n)
    * la boucle du for (int car : a) fait 1 seul tour ( -> O(n) ), et la boucle while
    * avec la condition (!st.isEmpty() && st.top() > 0 && car < 0) est executée quand
    * 2 voitures se croisent. Peu importe combien de voiture on a, elles vont soit
    * etre ajoutées 1 fois ou supprimées 1 fois, on va manipuler n fois ces suppressions dans le while
    *
    * On est en O(n)
    **/


    public static void main(String[] args) {
    CarConfrontation cc = new CarConfrontation();

    int[] example0 = {-3,-2,4,-5};
    int[] result0 = cc.carconfrontation(example0);
    System.out.println("Result pour [-3,-2,4,-5]: " + java.util.Arrays.toString(result0));

    int[] example13 = {9,-2,4,-5};
    int[] result13 = cc.carconfrontation(example13);
    System.out.println("Result pour [9,-2,4,-5]: " + java.util.Arrays.toString(result13));

    int[] example1 = {5, 10, -5, -15};
    int[] result1 = cc.carconfrontation(example1);
    System.out.println("Result pour [5, 10, -5, -15]: " + java.util.Arrays.toString(result1));

    int[] example2 = {5,10,-5};
    int[] result2 = cc.carconfrontation(example2);
    System.out.println("Result pour [5,10,-5]: " + java.util.Arrays.toString(result2));

    int[] example3 = {8,-8};
    int[] result3 = cc.carconfrontation(example3);
    System.out.println("Result for [8,-8]: " + java.util.Arrays.toString(result3));
    }
}