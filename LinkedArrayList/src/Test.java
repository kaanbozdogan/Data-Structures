
import java.util.NoSuchElementException;

public class Test
{

	public static void main(String[] args)
	{
		LinkedArrayList<Integer> list = new LinkedArrayList<>();

		System.out.println("\n\n------- LIST METHODS -------");
		System.out.println("----------------------------");

		//initializing the list
		System.out.println("\nInitializing the List with add(element)");
		System.out.println("for ( 1 -> 9 ) list.add(i)\n");

		for (int i = 1; i < 10; i++)
			list.add(i);

		System.out.print(list.getList());


		System.out.println("_______________________________");
		System.out.println("\nsize(): " + list.size());


		System.out.println("_______________________________");
		System.out.println("\nprinting with get(index)");
		System.out.println("for ( 0 -> size() ) list.get(i)\n");

		for (int i = 0; i < list.size(); i++)
			System.out.print(list.get(i) + " - ");


		System.out.println("\n_______________________________");
		System.out.println("Adding to Tail Node with space");
		System.out.print("add(10)");

		list.add(10);

		System.out.print("\n" + list.getList());


		System.out.println("\n_______________________________");
		System.out.println("Adding to Tail Node with maximum size");
		System.out.println("\nadd(11)");

		list.add(11);

		System.out.print("\n" + list.getList());


		System.out.println("_______________________________");
		System.out.println("Adding to a Node with enough space for a new element");
		System.out.println("\nadd(11,12)");

		list.add(11,12);

		System.out.print("\n" + list.getList());


		System.out.println("_______________________________");
		System.out.println("Adding to a Node with maximum size");
		System.out.println("\nadd(7,99)");

		list.add(7,99);

		System.out.print("\n" + list.getList());


		System.out.println("_______________________________");
		System.out.println("Adding to an out of bound index");
		System.out.println("\nadd(20,-1)");

		try{
			list.add(20,-1);
		}
		catch(IndexOutOfBoundsException e){
			System.out.print("\nIndexOutOfBoundsException thrown");
		}


		System.out.println("\n_______________________________");
		System.out.println("Removing from list using index");
		System.out.println("\nremove(6)");

		list.remove(6);

		System.out.print("\n" + list.getList());


		System.out.println("_______________________________");
		System.out.println("Removing from list using an out of bounds index");
		System.out.println("\nremove(20)");

		try{
			list.remove(20);
		}
		catch(IndexOutOfBoundsException e){
			System.out.print("\nIndexOutOfBoundsException thrown");
		}


		System.out.println("\n_______________________________");
		System.out.println("Removing from list using element");
		System.out.print("\nprint( remove(99) ) = ");

		System.out.println(list.remove((Integer)99));

		System.out.print("\n" + list.getList());


		System.out.println("_______________________________");
		System.out.println("Removing from list using an element which does not exist int he list");
		System.out.print ("\nremove(20) = ");

		System.out.println(list.remove((Integer)20) );

		System.out.print("\n" + list.getList());

		System.out.println("_______________________________\n\n\n\n");




		System.out.println("------- ITERATOR METHODS -------");
		System.out.println("--------------------------------");

		LinkedArrayIterator<Integer> it;
		LinkedArrayIterator<Integer> it2;

		System.out.println("\nInitializing iterator with listIterator()");
		System.out.println("\niterator = listIterator()");

		it = list.listIterator();


		System.out.println("_______________________________");
		System.out.println("Printing the list using hasNext() and next()\n");
		System.out.println("while(it.hasNext()) print(it.next())\n");

		while(it.hasNext())
			System.out.print(it.next() + " - ");


		System.out.println("\n_______________________________");
		System.out.println("Trying to use next() while there is no next element\n");

		try{
			it.next();
		}
		catch(NoSuchElementException e){
			System.out.print("NoSuchElementException thrown");
		}


		System.out.println("\n_______________________________");
		System.out.println("Printing the list using hasPrevious() and previous()\n");
		System.out.println("while(it.hasPrevious()) print(it.previous())\n");

		while(it.hasPrevious())
			System.out.print(it.previous() + " - ");


		System.out.println("\n_______________________________");
		System.out.println("Trying to use previous() while there is no previous element\n");

		try{
			it.previous();
		}
		catch(NoSuchElementException e){
			System.out.print("NoSuchElementException thrown");
		}


		System.out.println("\n_______________________________");
		System.out.println("Initializing iterator with listIterator(index)");
		System.out.println("\niterator = listIterator(6)");

		it2 = list.listIterator(6);


		System.out.println("\n_______________________________");
		System.out.println("Adding to where iterator iterates");
		System.out.println("add(99)");

		it2.add(99);

		System.out.println("\n" + list.getList());


		System.out.println("_______________________________");
		System.out.println("Removing the element that returned by a previously called next()");
		System.out.println("next() -> remove()");

		it2.next();
		it2.remove();

		System.out.println("\n" + list.getList());


		System.out.println("_______________________________");
		System.out.println("Trying to remove element right after a call to remove(), not next() or previous()");
		System.out.println("remove()\n");

		try{
			it2.remove();
		}
		catch(IllegalStateException e){
			System.out.println("IllegalStateException thrown");
		}


		System.out.println("_______________________________");
		System.out.println("Setting the element returned from previously called previous()");
		System.out.println("previous() -> set(0)");

		it2.previous();
		it2.set(0);

		System.out.println("\n" + list.getList());


		System.out.println("_______________________________");
		System.out.println("Trying to set right after a call to add(), not next() or previous()");
		System.out.println("add(-1) -> set(0)\n");

		try{
			it2.add(-1);
			it2.set(0);
		}
		catch(IllegalStateException e){
			System.out.println("IllegalStateException thrown");
		}

	}
}