
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedArrayIterator<E> implements ListIterator<E>
{
//fields
	/**
	 * Iterated node
	 */
	private Node<E> currNode;

	/**
	 * When currNode points to the last Node which is "null" the previous node is stored here
	 */
	private Node<E> prevNode;

	/**
	 * Iterated index
	 */
	private int currIndex;

	/**
	 * Previous operation called by the class
	 */
	private Operation lastOperation;

//methods
	/**
	 * Constructs a new iterator starting with the given node
	 * @param newHead New List's starting Node
	 */
	public LinkedArrayIterator(Node<E> newHead)
	{
		currNode = newHead;
		lastOperation = Operation.NONE;
	}

	/**
	 * Constructs a new iterator starting with the given node and starting from given index
	 * @param newHead New List's starting Node
	 * @param index New List's starting Index
	 */
	public LinkedArrayIterator(Node<E> newHead, int index)
	{
		currNode = newHead;
		int remaining = index;

		try
		{
			while (currNode.size() <= remaining)
			{
				remaining = remaining - currNode.size();
				currNode = currNode.next();
			}
		}
		catch(NullPointerException e){
			throw new IndexOutOfBoundsException();
		}

		currIndex = remaining;
		lastOperation = Operation.NONE;
	}

	@Override
	public boolean hasNext() {
		return currNode != null;
	}

	@Override
	public E next()
	{
		//if iterating to the end of the list
		if(currNode == null)
			throw new NoSuchElementException();

		lastOperation = Operation.NEXT;

		//if current node has next element
		if(currIndex + 1 < currNode.size())
		{
			currIndex++;
			return currNode.get(currIndex-1);
		}
		//need to find the next node with an element
		else
		{
			prevNode = currNode;
			int tempIndex = currIndex;

			//finding next node with an element
			currNode = findNextNodeWithElement(currNode.next());

			currIndex = 0;
			return prevNode.get(tempIndex);
		}
	}

	@Override
	public boolean hasPrevious()
	{
		//if current node has a previous element
		if (currIndex > 0) return true;
		//if iterating to the end of the list
		else if(currNode == null) return true;
		//if we reached to the start of the node we need to look at the previous nodes and find a node with element
		else return findPrevNodeWithElement(currNode.prev()) != null;
	}

	@Override
	public E previous() {
		//if iterating to the end of the list
		if (currNode == null)
		{
			currNode = prevNode;
			currIndex = currNode.size() - 1;
		}
		else
		{
			//if current node has previous element
			if (currIndex > 0)
				currIndex--;
				//need to find the previous node with an element
			else {
				//finding previous node with an element
				currNode = findPrevNodeWithElement(currNode.prev());

				//there is no more element left in the list
				if (currNode == null)
					throw new NoSuchElementException();

				//current index points tp the last element of the new node
				currIndex = currNode.size() - 1;
			}

			lastOperation = Operation.PREVIOUS;

		}
		return currNode.get(currIndex);
	}

	@Override
	public int nextIndex()
	{
		Node<E> node = currNode.prev();
		int index = currIndex;

		//count the node sizes until we reached to the current node
		while (node != null)
		{
			index += node.size();
			node = node.prev();
		}

		return index;
	}

	@Override
	public int previousIndex() { return nextIndex() - 1; }

	@Override
	public void remove()
	{
		updateStateAccordingToLastOperation();

		if(currNode == null)
			throw new NoSuchElementException();

		currNode.remove(currIndex);

		//if removed element was the last element of the node get to the next node
		if(currIndex == currNode.size())
		{
			currNode = currNode.next();
			currIndex = 0;
		}

		lastOperation = Operation.REMOVE;
	}

	@Override
	public void add(E o)
	{
		if(currNode == null)
			currNode = prevNode;

		if( currNode.isFull() )
		{
			//next node of current node is saved to a temporary variable
			Node<E> tempNode = currNode.next();

			//linking current node with new node
			currNode.setNext( new Node<>() );
			currNode.next().setPrev( currNode );

			//if next node of current node exists, linking next node of current node with new node
			if (tempNode != null)
			{
				tempNode.setPrev( currNode.next() );
				currNode.next().setNext( tempNode );
			}

			//half of the current exhausted node is transferred to the new node
			for (int i = currNode.size()/2, size = currNode.size(), index = i; i < size; i++)
				currNode.next().add( currNode.remove(index) );

			//finding the index after the data transfer to new node
			if (currNode.size() <= currIndex)
			{
				currIndex = currIndex - currNode.size();
				currNode = currNode.next();
			}
		}

		currNode.add(currIndex,o);

		//current index points to the next element of added element
		if(currIndex < currNode.maxSize())
			currIndex ++;
		//if new element is added to a node's last index, current node advances and current index points to the first element of the new node
		else
		{
			currNode = currNode.next();
			currIndex = 0;
		}

		lastOperation = Operation.ADD;
	}

	@Override
	public void set(E o)
	{
		updateStateAccordingToLastOperation();
		currNode.set(currIndex,o);
	}

//helper methods
	/**
	 * Finds next Node with element
	 * @param node Starting Node of the search
	 * @return Returns found Node
	 */
	private Node<E> findNextNodeWithElement(Node<E> node)
	{
		try
		{   //finding if there is an element after the the current node
			while (node.size() == 0)
				node = node.next();

			return node;
		}
		//if the remaining list has zero elements
		catch(NullPointerException e) {
			return null;
		}
	}

	/**
	 * Finds previous Node with element
	 * @param node Starting Node of the search
	 * @return Returns found Node
	 */
	private Node<E> findPrevNodeWithElement(Node<E> node)
	{
		try
		{   //finding if there is an element before the the current node
			while (node.size() == 0)
				node = node.prev();

			return node;
		}
		//if the remaining list has zero elements
		catch(NullPointerException e) {
			return null;
		}
	}

	/**
	 * Updates the iteration position according to last operation call
	 */
	private void updateStateAccordingToLastOperation()
	{
		switch (lastOperation)
		{
			//go to the element returned by next()
			case NEXT:
				if(currIndex > 0)
					currIndex --;
				else
				{
					currNode = currNode.prev();
					currIndex = currNode.size() + 1;
				}
				break;

			//already iterating to the element which returned by previous()
			case PREVIOUS:
				break;

			case ADD:
			case REMOVE:
			case NONE:
				throw new IllegalStateException();
		}
	}

}
