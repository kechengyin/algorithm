import java.util.ArrayDeque;
import java.util.Deque;
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
            while (!integerDeque.isEmpty() && A[i] >= integerDeque.getLast())
                 integerDeque.pollLast();
            integerDeque.offer(A[i]);
        }

        for (int i = w; i < A.length; i++) {
            B[i-w] = integerDeque.getFirst();
            while (!integerDeque.isEmpty() && A[i] > integerDeque.getLast())
                integerDeque.pollLast();
            while (!integerDeque.isEmpty() && integerDeque.getFirst() <= i-w)
                integerDeque.pollFirst();
            integerDeque.offer(A[i]);
        }
        B[A.length-w] = integerDeque.getFirst();
    }
}
