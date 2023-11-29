package lab10;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class MyLIFO_App {
	// method stutter that accepts a queue of integers as a parameter and replaces
	// every element of the queue with two copies of that element
	// front [1, 2, 3] back
	// becomes
	// front [1, 1, 2, 2, 3, 3] back
	public static <E> void stutter(Queue<E> input) {
		// TODO
		Queue<E> re = new LinkedList<>();
		while (!input.isEmpty()) {
			re.add(input.peek());
			re.add(input.poll());
		}
		while(!re.isEmpty()) {
			input.add(re.poll());
		}
	}
	// Method mirror that accepts a queue of strings as a parameter and appends the
	// queue's contents to itself in reverse order
	// front [a, b, c] back

	// becomes
	// front [a, b, c, c, b, a] back
	public static <E> void mirror(Queue<E> input) {
		// TODO

		Stack<E> stack = new Stack<>();
		Queue<E> re = new LinkedList<>();
		while (!input.isEmpty()) {
			stack.push(input.peek());
			re.add(input.poll());
		}
		while (!stack.isEmpty()) {
			re.add(stack.pop());
		}
		while(!re.isEmpty()) {
			input.add(re.poll());
		}
	
	}

	public static void main(String[] args) {
		Queue<Integer> obj = new LinkedList<>();
		obj.add(1);
		obj.add(2);
		obj.add(3);
		stutter(obj);
		System.out.println(obj);
		Queue<Integer> obj2 = new LinkedList<>();
		obj2.add(1);
		obj2.add(2);
		obj2.add(3);
		mirror(obj2);
		System.out.println(obj2);
	}

}
















