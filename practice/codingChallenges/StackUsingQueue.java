import java.util.ArrayDeque;
import java.util.Queue;

class StackUsingQueue {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    int top;

    public StackUsingQueue() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    public void push(int n) {
        queue1.add(n);
        top = n;
    }

    public int pop() {
        while (queue1.size() > 1) {
            top = queue1.poll();
            queue2.add(top);
        }
        // swap(inputQueue, outputQueue)
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return queue2.poll();
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}