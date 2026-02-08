package heap;

import java.util.Arrays;

public class MaxHeap {
    int[] arr;
    int size;

    public MaxHeap(int size) {
        arr=new int[size];
        this.size = 0;
    }

    public void insert(int value){
        arr[size]=value;
        size++;


        int currentIndex=size-1;
        //Bubble Up
        while (currentIndex>0 && arr[currentIndex]>getParentValue(currentIndex)){
            //Swap
            swap(currentIndex,getParentIndex(currentIndex));
            //update CurrentIndex
            currentIndex=getParentIndex(currentIndex);
        }

    }

    private int getParentIndex(int index){
        return (index-1)/2;
    }

    private int getParentValue(int index){
        return arr[getParentIndex(index)];
    }

    private void swap(int index1,int index2){
        int temp=arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=temp;
    }

    public boolean isEmpty(){
        return size==0;
    }


    public int remove(){
        int removingValue=arr[0];
        //Make last elem as root
        arr[0]=arr[size-1];
        arr[size-1]=-1;
        size--;


        int currentIndex=0;
        //Bubble Down
        while (currentIndex < size && !isValidParent(currentIndex)){
            int idxOfLargestChild=getIdxOfLargestChild(currentIndex);
            //Swap
            swap(currentIndex,idxOfLargestChild);
            //update CurrentIndex
            currentIndex=idxOfLargestChild;
         //   System.out.println("CURRRR "+currentIndex);
        }

        return removingValue;

    }

    private int getIdxOfLargestChild(int currentIndex) {
        if(!hasLeftChild(currentIndex))return currentIndex;
        if(!hasRightChild(currentIndex))return getLeftChildIdx(currentIndex);

        if(getLeftChild(currentIndex)>getRightChild(currentIndex)){
            return getLeftChildIdx(currentIndex);
        }else {
            return getRightChildIdx(currentIndex);
        }


    }

    private boolean isValidParent(int currentIndex) {
        if(!hasLeftChild(currentIndex) && !hasRightChild(currentIndex))return true;

        if(!hasLeftChild(currentIndex))return false;

        if(!hasRightChild(currentIndex)){
            return arr[currentIndex] > getLeftChild(currentIndex);
        }

        return arr[currentIndex] > getLeftChild(currentIndex) &&  arr[currentIndex] > getRightChild(currentIndex);
    }

    private int getLeftChildIdx(int index){
        return (index*2)+1;
    }

    private int getRightChildIdx(int index){
        return (index*2)+2;
    }

    private int getLeftChild(int index){
        return arr[getLeftChildIdx(index)];
    }

    private int getRightChild(int index){
        return arr[getRightChildIdx(index)];
    }

    private boolean hasLeftChild(int index){
        return getLeftChildIdx(index) <=size;
    }

    private boolean hasRightChild(int index){
        return getRightChildIdx(index) <=size;
    }


    @Override
    public String toString() {
        return "MaxHeap{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}
