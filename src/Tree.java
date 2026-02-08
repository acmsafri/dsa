import java.util.*;

public class Tree {
        private class TreeNode{
                private Integer value;
                private TreeNode left;
                private TreeNode right;

                public TreeNode(Integer value) {
                        this.value = value;
                }

                @Override
                public String toString() {
                        return value.toString();
                }
        }

        private TreeNode root;


        private TreeNode insert(TreeNode root,Integer value){
                if(root==null){
                        root= new TreeNode(value);
                        return root;
                }
                if(value <= root.value){
                       root.left=insert(root.left,value);
                }else {
                      root.right=insert(root.right,value);
                }

                return root;
        }

        public void insert(Integer value){
                root=insert(root,value);
        }

        private void preOrder(TreeNode treeNode){
                if(treeNode==null){
                        return;
                }

                System.out.print(treeNode.value+",");
                preOrder(treeNode.left);
                preOrder(treeNode.right);

        }

        private void postOrder(TreeNode treeNode){
                if(treeNode==null){
                        return;
                }

                postOrder(treeNode.left);
                postOrder(treeNode.right);
                System.out.print(treeNode.value+",");

        }

        private void inOrder(TreeNode treeNode){
                if(treeNode==null){
                        return;
                }

                inOrder(treeNode.left);
                System.out.print(treeNode.value+",");
                inOrder(treeNode.right);

        }

        public void preOrderTraversal(){
                preOrder(root);
        }

        public void inOrderTraversal(){
                inOrder(root);
        }

        public void postOrderTraversal(){
                postOrder(root);
        }

        private int depth(TreeNode treeNode,int value,int depth){
                if(treeNode==null){
                        return -1;
                }

                if(treeNode.value==value){
                        return depth;
                }

                if(value<=treeNode.value){
                        return depth(treeNode.left,value,depth+1);
                }

                return depth(treeNode.right,value,depth+1);
        }

        public int depth(int value){
                return depth(root,value,0);
        }

        private int heightOfTree(TreeNode treeNode){
                if(treeNode==null){
                        return -1;
                }

                if(treeNode.left==null && treeNode.right==null){
                        return 0;
                }

                return Math.max(heightOfTree(treeNode.left), heightOfTree(treeNode.right)) + 1;


        }

        private int heightOfTree(){
                return heightOfTree(root);
        }

        private int height(TreeNode treeNode,int value){
                if(treeNode==null){
                        return -1;
                }

                if(treeNode.value==value){
                        return heightOfTree(treeNode);
                }

                if(value<=treeNode.value){
                        return height(treeNode.left,value);
                }

                return height(treeNode.right,value);
        }

        public int height(int value){
                return height(root,value);
        }

        private TreeNode getMin(TreeNode treeNode){
                if(treeNode==null)return null;

                if(treeNode.left==null)return treeNode;

                return getMin(treeNode.left);

        }

        public TreeNode getMin(){
                return getMin(root);

        }

        private boolean isEqual(TreeNode t1,TreeNode t2){
                if(t1==null && t2==null)return true;

                if(t1==null || t2==null) return false;

                return Objects.equals(t1.value, t2.value)
                        && isEqual(t1.left,t2.left)
                        && isEqual(t1.right,t2.right);
        }

        public boolean isEqual(Tree tree){
                return isEqual(root,tree.root);
        }

        private void kthDistance(TreeNode treeNode,int k,List<TreeNode> treeNodes){
                if(treeNode==null)return;

                if(k==0){
                        treeNodes.add(treeNode);
                }

                k--;
                kthDistance(treeNode.left,k,treeNodes);
                kthDistance(treeNode.right,k,treeNodes);
        }

        public List<TreeNode> kthDistance(int k){
              List<TreeNode> list=new ArrayList<>();

              kthDistance(root,k,list);
              return list;
        }

        public void levelOrderTraversalInBadWay(){
                int hieght=heightOfTree();
                for(int i=0;i<=hieght;i++){
                        System.out.println(kthDistance(i));
                }

        }

        public List<List<TreeNode>> levelOrderTraversal() {
                Queue<TreeNode> queue = new ArrayDeque<>();
                List<List<TreeNode>> list = new ArrayList<>();

                queue.add(root);

                while (!queue.isEmpty()) {
                        List<TreeNode> integers=new ArrayList<>();

                        TreeNode pop1 = queue.poll();
                        TreeNode pop2 = queue.poll();
                        add(integers, pop1, queue);
                        add(integers, pop2, queue);

                        list.add(integers);

                }

                return list;
        }

        private static void add(List<TreeNode> integers, TreeNode pop1, Queue<TreeNode> queue) {
                if(pop1==null)return;

                integers.add(pop1);


                if (pop1.left != null) {
                        queue.add(pop1.left);
                }

                if (pop1.right != null) {
                        queue.add(pop1.right);
                }
        }

        public void morrisTraversal(){
            TreeNode current=root;

            while (current!=null){
                if(current.left==null){
                    System.out.print(current.value+",");
                    current=current.right;
                }else{
                    TreeNode pred=current.left;
                    while (pred.right!=null && pred.right!=current){
                        pred=pred.right;
                    }

                    if(pred.right==null){
                        pred.right=current;
                        current=current.left;
                    }else{
                        pred.right=null;
                        System.out.print(current.value+",");
                        current=current.right;
                    }
                }
            }
        }

}
