import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
     /*  Integer[] values={3,1,4,6,8,0,7};
       Integer[] values2={3,1,4,6,8,0,7};

        *//**
         *                  3
         *      1                   4
         * 0                                 6
         *                                         8
         *                                  7
         *//*
        Tree tree=new Tree();
        Arrays.stream(values).toList().forEach(tree::insert);

       Tree tree2=new Tree();
       Arrays.stream(values2).toList().forEach(tree2::insert);

        tree.preOrderTraversal(); //3,1,0,4,6,8,7
        System.out.println();
        tree.inOrderTraversal();  //0,1,3,4,6,7,8
        System.out.println();
        tree.postOrderTraversal();//0,1,7,8,6,4,3
        System.out.println();

        System.out.println(tree.depth(7));
        System.out.println(tree.depth(0));
        System.out.println(tree.depth(8));
        System.out.println();
        System.out.println(tree.height(7)); //0
        System.out.println(tree.height(0)); //0
        System.out.println(tree.height(8)); //1
        System.out.println(tree.height(3)); //4

        System.out.println(tree.getMin());//0

        System.out.println(tree.isEqual(tree2));

        System.out.println(tree.kthDistance(3));


        System.out.println("****************");

        tree.levelOrderTraversalInBadWay();


        System.out.println("****************");

        System.out.println(tree.levelOrderTraversal());*/


        Integer[] values={3,1,4,6,8,0,7};
        Tree tree=new Tree();
        Arrays.stream(values).toList().forEach(tree::insert);
        tree.inOrderTraversal();  //0,1,3,4,6,7,8
        System.out.println();
        tree.morrisTraversal(); //0,1,3,4,6,7,8

    }
}