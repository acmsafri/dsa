package avl;

import java.util.*;

public class AVLTree {
    public class AVLTreeNode{
        Integer value;
        AVLTreeNode left;
        AVLTreeNode right;
        Integer hieght;

        public AVLTreeNode(Integer value) {
            this.value = value;
            this.hieght=0;
        }

        @Override
        public String toString() {
            return  value +"";
        }
    }

    private AVLTreeNode root;

    private AVLTreeNode insert(AVLTreeNode root,Integer value){
        if(root==null)root=new AVLTreeNode(value);

        if(Objects.equals(root.value, value))return root;

        if(value<root.value){
            root.left= insert(root.left,value);
        }else {
            root.right= insert(root.right,value);
        }

        root.hieght=Math.max(getHieght(root.left),getHieght(root.right))+1;


        if(!isBallanced(root)){
            if(isLeftHeavy(root)){
                if(getBallanceFactor(root.left) < 0){
                    System.out.println("Left rotate "+root.left);
                    root.left=leftRotate(root.left);
                    //root.left.hieght=Math.max(getHieght(root.left.left),getHieght(root.left.right))+1;
                }
                System.out.println("Right rotate "+root);
                root=rightRotate(root);
                //root.hieght=Math.max(getHieght(root.left),getHieght(root.right))+1;
            } else if (isRightHeavy(root)) {
                if(getBallanceFactor(root.right) > 0){
                    System.out.println("Right rotate "+root.right);
                    root.right=rightRotate(root.right);
                    //root.right.hieght=Math.max(getHieght(root.right.left),getHieght(root.right.right))+1;
                }
                System.out.println("Left rotate "+root);
                root=leftRotate(root);
               // root.hieght=Math.max(getHieght(root.left),getHieght(root.right))+1;
            }

        }




        return root;
    }


    private AVLTreeNode leftRotate(AVLTreeNode root){
        if(root==null)return null;
        AVLTreeNode newRoot=root.right;

        root.right=newRoot.left;
        newRoot.left=root;
        newRoot.hieght=Math.max(getHieght(newRoot.left),getHieght(newRoot.right))+1;
        root.hieght=Math.max(getHieght(root.left),getHieght(root.right))+1;
        return newRoot;
    }

    private AVLTreeNode rightRotate(AVLTreeNode root){
        if(root==null)return null;
        AVLTreeNode newRoot=root.left;
        root.left=newRoot.right;
        newRoot.right=root;
        newRoot.hieght=Math.max(getHieght(newRoot.left),getHieght(newRoot.right))+1;
        root.hieght=Math.max(getHieght(root.left),getHieght(root.right))+1;
        return newRoot;
    }


    private boolean isBallanced(AVLTreeNode root){
        if(root==null){
            return true;
        }

        Integer ballanceFactor = getBallanceFactor(root);

        return ballanceFactor >= -1 && ballanceFactor <=1;

    }

    private Integer getBallanceFactor(AVLTreeNode root) {
        if(root==null)return 0;
        Integer ballanceFactor=getHieght(root.left)-getHieght(root.right);
        return ballanceFactor;
    }

    private boolean isLeftHeavy(AVLTreeNode root){
        if(root==null)return false;

        return getBallanceFactor(root)>1;
    }

    private boolean isRightHeavy(AVLTreeNode root){
        if(root==null)return false;

        return getBallanceFactor(root)<-1;
    }

    private Integer getHieght(AVLTreeNode root){
        if(root==null)return -1;

        return root.hieght;
    }


    public void insert(Integer value){
        root=insert(root, value);
    }

    private static void add(List<AVLTreeNode> integers, AVLTreeNode pop1, Queue<AVLTreeNode> queue) {
        if(pop1==null)return;

        integers.add(pop1);


        if (pop1.left != null) {
            queue.add(pop1.left);
        }

        if (pop1.right != null) {
            queue.add(pop1.right);
        }
    }


    public List<List<AVLTreeNode>> levelOrderTraversal() {
        Queue<AVLTreeNode> queue = new ArrayDeque<>();
        List<List<AVLTreeNode>> list = new ArrayList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            List<AVLTreeNode> integers=new ArrayList<>();

            AVLTreeNode pop1 = queue.poll();
            AVLTreeNode pop2 = queue.poll();
            add(integers, pop1, queue);
            add(integers, pop2, queue);

            list.add(integers);

        }

        return list;
    }

    @Override
    public String toString() {
        List<List<AVLTreeNode>> list=levelOrderTraversal();

        return list.toString();
    }
}
