package avl;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        /*Integer[] values2={3,1,4,6,8,0,7};

        AVLTree avlTree=new AVLTree();
        Arrays.stream(values2).forEach(integer -> {
            avlTree.insert(integer);
        });

        System.out.println(avlTree);  //[[3], [1, 4], [0, 6], [8], [7]]  --straight insert
*/

      /*  Integer[] values3={10,5,15,2,2,8,12,20}; //Ballanced

        AVLTree avlTree2=new AVLTree();
        Arrays.stream(values3).forEach(integer -> {
            avlTree2.insert(integer);
        });

        System.out.println(avlTree2);*/

        Integer[] values3={10,15,20,25,30,35}; //Uballanced

        AVLTree avlTree2=new AVLTree();
        Arrays.stream(values3).forEach(integer -> {
            avlTree2.insert(integer);
        });

        System.out.println(avlTree2);

    }
}
