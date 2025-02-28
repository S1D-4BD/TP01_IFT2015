package DV01_IFT2015;

import java.util.* ;

public class Stack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;   // dans cette exercice la pile est initialiser a partir de -1
                   // indique que la pile est vide
    }

    /** Fonction qui retourne la taille de la pile
     *
     * @return Nombre d'element du stack
     */
    public int size() {
        return top + 1;}
        //return maxSize;}

    /**
     * push est l'une des methode de base d'une pile
     * ajoute un int value au top de la pile
     * @param value
     */
    public void push(int value) {
        // Gestion d'erreur : pile pleine
       if (top == maxSize - 1 ){
           throw new IllegalStateException("Erreur: taille de tableau insuffisante");
       }else {
           top++ ; // incrimenter top de la pile pour permettre l'ajout d'une nouvelle valeur
           stackArray[top] = value; // ajout de la valeur a l'index top courrant
       }
    }

    /**
     * Retire et renvoie le dernier element de la pile.
     * @return value
     */
    public int pop() {
        if (top == -1) {
            throw new NoSuchElementException("la pile est vide");
        } else {
            int value = stackArray[top];
            top--;
            return value;
        }
    }

    /**
     * Renvoie le dernier element sur la pile.
     * @return
     */
    public int top(){
        if (isEmpty()){  // ceci verifie si la pile est vide si oui il envoie le message
            throw new NoSuchElementException("La pile est vide !");
        }else{
            return stackArray[top];   // si la pile n'est pas vide il retourne l'element de
                                      // l'index top de stackArray (tableau)
        }
    }

    /**
     * Verifie si la pile est vide.
     * @return
     */
    public boolean isEmpty(){
        return top == -1;
    }


    ////////////////////////////////////////
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        Stack stack1 = new Stack(10);

        System.out.println(stack.size());// test pour stack size
        System.out.println(stack1.size());// test pour stack size

       try {

             stack.push(10);
           System.out.println(stack.size());// test pour stack size
           System.out.println("Element sommet de la pile est: " + stack.top());
             stack.pop();
             stack.pop();

       } catch (IllegalStateException | NoSuchElementException e) {
           System.out.println("Erreur: " + e.getMessage());
       }

        System.out.println("Est-ce-que la pile est vide? " + stack.isEmpty());
    }
}