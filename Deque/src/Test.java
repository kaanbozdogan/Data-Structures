import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Test
{

	public static void main(String[] args)
	{
		GtuDeque<Integer> deq = new GtuDeque<>();

		System.out.println("----- DEQUE METHODS -----");

		System.out.println("___________________________________\n");
		System.out.println("Initializing the list with add()");
		System.out.println("for(i=1 to 9) add(i)");

		for (int i = 1; i <= 9; i++)
			deq.add(i);

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("addFirst(0)");

		deq.addFirst(0);

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("addLast(10)");

		deq.addLast(10);

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("offerFirst(-1)");

		deq.offerFirst(-1);

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("offerLast(11)");

		deq.offerLast(11);

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("removeFirst()");
		System.out.println("removed element: " + deq.removeFirst());


		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("removeLast()");
		System.out.println("removed element: " + deq.removeLast());

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("pollFirst()");
		System.out.println("removed element: " + deq.pollFirst());

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("pollLast()");
		System.out.println("removed element: " + deq.pollLast());

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("getFirst(): " + deq.getFirst());

		System.out.println("___________________________________\n");
		System.out.println("getLast(): " + deq.getLast());

		System.out.println("___________________________________\n");
		System.out.println("peekFirst(): " + deq.peekFirst());

		System.out.println("___________________________________\n");
		System.out.println("peekLast(): " + deq.peekLast());

		System.out.println("___________________________________\n");
		System.out.println("remove()");
		System.out.println("removed element: " + deq.remove());

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("poll()");
		System.out.println("removed element: " + deq.poll());

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("element(): " + deq.element());

		System.out.println("___________________________________\n");
		System.out.println("peek(): " + deq.peek());

		System.out.println("___________________________________\n");
		System.out.println("push(99)");

		deq.push(99);

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("pop()");

		deq.pop();

		System.out.println(deq.toString());

		System.out.println("___________________________________\n");
		System.out.println("size(): " + deq.size());

		System.out.println("___________________________________\n");
		System.out.println("isEmpty(): " + deq.isEmpty());

		System.out.println("___________________________________\n");
		System.out.println("contains(2): " + deq.contains(2));

		System.out.println("___________________________________\n");
		System.out.println("remove(2): " + deq.remove(2));

		System.out.println("___________________________________\n");
		System.out.println("contains(2): " + deq.contains(2));

		System.out.println("___________________________________\n");
		System.out.print("remove(2)");
		System.out.println(": " + deq.remove(2));


		System.out.println("___________________________________\n");
		System.out.println("clear()");

		deq.clear();

		System.out.println("___________________________________\n");
		System.out.println("size(): " + deq.size());

		System.out.println("___________________________________\n");
		System.out.println("isEmpty(): " + deq.isEmpty());

		System.out.println("___________________________________\n");
		System.out.println("Trying all remove and get methods while the deque is empty.\n");


		System.out.print("\nremoveFirst() -> ");
		try{
			deq.removeFirst();
		}
		catch(NoSuchElementException ex){
			System.out.println("NoSuchElementException thrown. Deque is empty.");
		}


		System.out.print("\nremoveLast()() -> ");
		try{
			deq.removeLast();
		}
		catch(NoSuchElementException ex){
			System.out.println("NoSuchElementException thrown. Deque is empty.");
		}


		System.out.println("\npollFirst() -> returns " + deq.pollFirst());


		System.out.println("\npollLast() -> returns " + deq.pollLast());


		System.out.print("\ngetFirst() -> ");
		try{
			deq.getFirst();
		}
		catch(NoSuchElementException ex){
			System.out.println("NoSuchElementException thrown. Deque is empty.");
		}


		System.out.print("\ngetLast() -> ");
		try{
			deq.getLast();
		}
		catch(NoSuchElementException ex){
			System.out.println("NoSuchElementException thrown. Deque is empty.");
		}


		System.out.println("\npeekFirst() -> returns " + deq.peekFirst());


		System.out.println("\npeekLast() -> returns " + deq.peekLast());


		System.out.print("\nremove() -> ");
		try{
			deq.remove();
		}
		catch(NoSuchElementException ex){
			System.out.println("NoSuchElementException thrown. Deque is empty.");
		}


		System.out.print("\npoll() -> ");
		try{
			deq.poll();
		}
		catch(NoSuchElementException ex){
			System.out.println("NoSuchElementException thrown. Deque is empty.");
		}


		System.out.print("\nelement() -> ");
		try{
			deq.element();
		}
		catch(NoSuchElementException ex){
			System.out.println("NoSuchElementException thrown. Deque is empty.");
		}


		System.out.println("\npeek() -> returns " + deq.peek());


		System.out.print("\npop() -> ");
		try{
			deq.pop();
		}
		catch(NoSuchElementException ex){
			System.out.println("NoSuchElementException thrown. Deque is empty.");
		}


		System.out.println("\nremoveFirstOccurrence() -> returns " + deq.removeFirstOccurrence(0));


		System.out.println("\nremoveLastOccurrence() -> returns " + deq.removeLastOccurrence(0));


		System.out.println("___________________________________\n");
		System.out.println("Initializing the deque to test occurrence remove methods");

		deq.add(1);
		deq.add(0);
		deq.add(1);
		deq.add(0);
		deq.add(1);
		deq.add(0);
		deq.add(1);

		System.out.println("Deque elements -> " + deq.toString());


		System.out.println("___________________________________\n");
		System.out.println("removeFirstOccurrence(0)");

		deq.removeFirstOccurrence(0);

		System.out.println(deq.toString());


		System.out.println("___________________________________\n");
		System.out.println("removeLastOccurrence(0)");

		deq.removeLastOccurrence(0);

		System.out.println(deq.toString());



		System.out.println("\n\n\n----- ITERATOR METHODS -----");

		System.out.println("___________________________________\n");
		System.out.println("Initializing the list with add()");
		System.out.println("for(i=1 to 5) add(i)");

		deq.clear();

		for (int i = 1; i <= 5; i++)
			deq.add(i);

		System.out.println(deq.toString());

		GtuDequeIterator<Integer> it1 = (GtuDequeIterator<Integer>) deq.iterator();
		GtuDequeIterator<Integer> it2 = (GtuDequeIterator<Integer>) deq.descendingIterator();

		System.out.println("___________________________________");
		System.out.println("\nIterating through the deque with standard iterator");

		while(it1.hasNext())
			System.out.print(it1.next() + " , ");


		System.out.println("\n___________________________________");
		System.out.println("\nIterating through the deque with descending iterator");

		while(it2.hasNext())
			System.out.print(it2.next() + " , ");

	}
}
