import sun.jvm.hotspot.runtime.ia64.cInterpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
/*
given a long array A, and a window size w, and a resulting array B
make B[i] = maximum(A[i] ... A[i-w+1])
Assume A.length >> w

As every element of array A can be inserted and removed from the queue at most once.
So the algorithm run time is O(n)
 */
public class SlidingWindowMaximum {

    public static void maxValue(int[] A, int w, int B[]) {
        Deque<Integer> integerDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < w; i++) {
            while (!integerDeque.isEmpty() && A[i] >= A[integerDeque.getLast()])
                 integerDeque.pollLast();
            integerDeque.offer(i);
        }

        for (int i = w; i < A.length; i++) {
            B[i-w] = A[integerDeque.getFirst()];
            while (!integerDeque.isEmpty() && A[i] > A[integerDeque.getLast()])
                integerDeque.pollLast();
            while (!integerDeque.isEmpty() && integerDeque.getFirst() <= i-w)
                integerDeque.pollFirst();
            integerDeque.offer(i);
        }
        B[A.length-w] = A[integerDeque.getFirst()];
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int lena = 30;
        int w = 5;
        int[] A = new int[lena];
        int[] B= new int[lena - w + 1];
        for (int i = 0; i < lena; i++) {
            A[i] = rand.nextInt(1000);
        }

        for (int num : A)
            System.out.print(num + " ");
        System.out.println();

        maxValue(A, w, B);
        for (int num : B)
            System.out.print(num + " ");

    }
}
