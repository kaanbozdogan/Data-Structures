
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GtuDequeIterator<E> implements Iterator<E>
{
//fields
	Node<E> currNode;
	boolean descending;

//methods

	/**
	 * Constructor for the class.
	 * @param head Starting point of the iterator
	 * @param isDescending True if iterator continues in descending order.
	 */
	GtuDequeIterator(Node<E> head, boolean isDescending)
	{
		currNode = head;
		descending = isDescending;
	}

	@Override
	public boolean hasNext() {
		return currNode != null;
	}

	@Override
	public E next()
	{
		try{
			E temp = currNode.get();

			if ( descending )
				currNode = currNode.prev();
			else
				currNode = currNode.next();

			return temp;
		}
		catch(NullPointerException ex){
			throw new NoSuchElementException();
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
