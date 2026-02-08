package heap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //int[] data={3,10,20,1,0,100};
        int[] data={3,10,2,4,16,8,90};

        MaxHeap maxHeap=new MaxHeap(data.length);

        Arrays.stream(data).forEach(maxHeap::insert);

        System.out.println(maxHeap);


        while (!maxHeap.isEmpty()){
            System.out.println(maxHeap.remove());
        }
    }
}
