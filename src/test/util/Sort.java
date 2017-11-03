package util;

/**
 * Created by ${zhao} on 2017/11/2 0002.
 */
public class Sort {
    private static void bubbleSort() {
        int[] nums = {12, -1, 23, 55, 98, -23};
        for (int item:nums
             ) {
            System.out.print(item+",");
        }
    }

    public static void main(String[] strs) {
        bubbleSort();
    }


}
