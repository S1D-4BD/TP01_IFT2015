package DV01_IFT2015;

import java.util.EmptyStackException; //l'exception quon va handle dans 2 cas; top et pop
import java.lang.StackOverflowError; //l'exception quon va handle pour push


public class StackDouble {
    private int[] elements; 
    private int top_1; 
    private int top_2; 

   // je considere la boolean comme une switch
   // pour choisir entre la pile 1 (true) et pile 2 (false)

    /**
     * Constructeur de la double pile
     * vu que ya pas de paramètres c'est un constructeur par défaut
     */
    public StackDouble(){
        this.elements = new int[10]; //100
        this.top_1 = -1;
        this.top_2 = elements.length;
    }

    /**
     * Fonction qui va nous permettre de voir graphiquement ou sont les tops
     */
    public void pointeur(){
        for (int i = 0; i<this.elements.length; i++){
            if (i == top_1){
                System.out.print("x ");
            }
            else if(i == top_2){
                System.out.print("y ");
            }
            else{
                System.out.print("- ");
            }
        }
        System.out.println();
    }

    /**
     * Méthode d'affichage en fct de la longueur
     */
    public void afficherStack(){
        for (int i =0; i<this.elements.length; i++){ //itere tout le long du  tableau qui contien les 2 piles
            System.out.print(this.elements[i]+" ");
        }
        System.out.println();
        pointeur(); //on affiche les poisitions des tops
    }



    /**
     * Méthode pour vérifier si pile est pleine
     * Si les tops sont consécutifs, alors il n'y a plus d'espace pour push dans une pile ou l'autre
     * @return true (pleine) ou false (pas pleine)
     */
    public boolean full(){
        if (top_1+1 == top_2) { //si les pointeurs sont consecutifs -> plein
            return true;
        }
        return false;
    }

    /**
     * Méthode pour vérifier si les 2 tops sont en dehors du tableau
     * On vérifie les tops et non la valeur car l'état vide est défini par un pointeur et pas par une valeur
     * (sinon, un "0" qui a été push va être considéré comme élément vide)
     * @return
     */
    public boolean isEmpty(boolean one) {
        if (one==true) {
            return (this.top_1 == -1); //top pointe a l exterieur de la pile
        }
        else{
            return (this.top_2 == elements.length);//top pointe a l exterieur de la pile
        }
    }

    /**
     * Fonction pour push un int
     * @param one une booleenne permettant de choisir entre la pile 1 (vrai) ou 2 (faux)
     * @param value la valeur a push
     * @throws StackOverflowError l'erreur quon throw si full
     */
    public boolean push(boolean one, int value)throws StackOverflowError {
       if (this.full()==true){
            throw new StackOverflowError(); //si plein, on peut pas push
       }

       if (one == true) {
           top_1+=1; //déplace top pour la nouvelle valeur
           this.elements[top_1] = value; //la ou est le top1 on écrit la valeur
       }
       else {
           top_2-=1; //déplace top pour la nouvelle valeur
           this.elements[top_2] = value; //la ou ets le top2 on écrit la valeur
       }
       return true;
    }

    /**
     * Fonction pour retourner le top d'une des 2 piles
     * @param one une booleenne permettant de choisir entre la pile 1 (vrai) ou 2 (faux)
     * @return la position du top de la pile qu'on choisit
     * @throws EmptyStackException si le top est inferieur a la position 0 ou sup à la limite de taille
     */
    public int top(boolean one) throws EmptyStackException{
        if (one == true) {
            if (top_1 < 0 ) {
                throw new EmptyStackException(); //possible exception,pile vide
            }
            return this.elements[top_1]; //sinon return l'element pointé par le top (meme index)
        }
        else{
            if (top_2 >=this.elements.length) {//possible exception,pile vide
                throw new EmptyStackException();
            }
            return this.elements[top_2];//sinon return l'element pointé par le top (meme index)
        }
    }

    /**
     * Fonction pour enlever et retourner l'élément au top de la pile
     * @param one une booleenne permettant de choisir entre la pile 1 (vrai) ou 2 (faux)
     * @return la valeur pointée par le top1 ou top 2
     * @throws EmptyStackException si on essaie de pop une valeur d'un stack vide
     */
    public int pop(boolean one) throws EmptyStackException{

        if (this.isEmpty(one)) {
            throw new EmptyStackException();
        }

        if (one) { //cas pile 1
            int valeur= this.elements[top_1];
            this.elements[top_1] = 0; //on doit effacer le int pointee par le top initialement
            top_1--;
            return valeur;
        } else { //si pas pile 1 on work avec la pile 2
            int valeur = this.elements[top_2];
            this.elements[top_2] = 0;
            top_2++;
            return valeur;
        }

    }


    /**
     * Fonction pour retourner la taille
     * @param one
     * @return
     */
    public int size(boolean one){
        if (one==true){ //si on choisi pile 1
            return this.top_1 +1; //la taile = top  +1 (car top commence a -1)
        }
        else{
            return this.elements.length - top_2; //pile 2 on enleve du tab "top_2" elements pout avoir le size
        }
    }

    public static void main(String[] args) throws Exception {

       StackDouble stackDouble = new StackDouble();

        stackDouble.afficherStack();

        try{
            stackDouble.push(true,1);
            stackDouble.push(true,5);
            stackDouble.push(true,7);
            stackDouble.push(true,9);
            stackDouble.push(true,7);

            stackDouble.afficherStack();

            stackDouble.push(false,2);
            stackDouble.push(false,4);
            stackDouble.push(false,6);
            stackDouble.afficherStack();

        }catch (StackOverflowError e){
            System.out.println("pleine");
        }
        System.out.println();
        System.out.println();
        try{
        stackDouble.pop(false);
        stackDouble.pop(false);
        stackDouble.afficherStack();
        stackDouble.pop(false);
        //stackDouble.pop(false);
        //stackDouble.pop(false);

        stackDouble.afficherStack();

        }catch (EmptyStackException e){
            stackDouble.afficherStack();
            System.out.println("Vide");
        }



    }



}
