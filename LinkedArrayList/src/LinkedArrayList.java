
import java.util.AbstractList;
import java.util.List;


public class LinkedArrayList<E> extends AbstractList<E> implements List<E> {
//Fields
	/**
	 * Starting Node of the list
	 */
	private Node<E> head;

	/**
	 * Ending Node of the list
	 */
	private Node<E> tail;

	/**
	 * Element size of the list
	 */
	private int size;

//Methods
	/**
	 * Default parameter constructor
	 */
	public LinkedArrayList()
	{
		head = new Node<E>();
		tail = head;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int index) {
		int remaining = index;
		Node<E> currNode = head;

		if (0 <= index && index <= size) {
			while (currNode.size() <= remaining) {
				remaining = remaining - currNode.size();
				currNode = currNode.next();
			}

			return currNode.get(remaining);
		} else
			throw new IndexOutOfBoundsException();
	}

	@Override
	public boolean add(Object o)
	{
		if (tail.isFull())
		{
			tail.setNext(new Node<>());
			tail.next().setPrev(tail);

			tail = tail.next();
		}

		tail.add((E) o);
		size++;

		return true;
	}

	@Override
	public void add(int index, Object o)
	{
		int remaining = index;
		Node<E> currNode = head;

		if (index < 0 || size < index)
			throw new IndexOutOfBoundsException();

		//finding the node which wanted index resides in
		while (currNode.size() < remaining)
		{
			remaining = remaining - currNode.size();
			currNode = currNode.next();
		}

		if (currNode.isFull())
		{
			//next node of current node is saved to a temporary variable
			Node<E> tempNode = currNode.next();

			//linking current node with new node
			currNode.setNext(new Node<>());
			currNode.next().setPrev(currNode);

			//if next node of current node exists linking next node of current node with new node
			if (tempNode != null)
			{
				tempNode.setPrev(currNode.next());
				currNode.next().setNext(tempNode);
			}
			//if next node of current node is null it means current node is the tail and now next node becomes the tail
			else
				tail = currNode.next();

			//half of the current exhausted node is transferred to the new node
			for (int i = currNode.size() / 2, size = currNode.size(), ind = i; i < size; i++)
				currNode.next().add(currNode.remove(ind));

			//finding the index after the data transfer to new node
			if (currNode.size() <= remaining)
			{
				remaining = remaining - currNode.size();
				currNode = currNode.next();
			}
		}

		currNode.add(remaining, (E) o);
		size++;
	}

	@Override
	public E remove(int index) {
		int remaining = index;
		Node<E> currNode = head;

		if (index < 0 || size < index)
			throw new IndexOutOfBoundsException();

		while (currNode.size() <= remaining) {
			remaining = remaining - currNode.size();
			currNode = currNode.next();
		}

		size--;
		return currNode.remove(remaining);
	}

//Iterator methods
	@Override
	public LinkedArrayIterator<E> listIterator() {
		return new LinkedArrayIterator<>(head);
	}

	@Override
	public LinkedArrayIterator<E> listIterator(int index) {
		return new LinkedArrayIterator<>(head, index);
	}

//Test methods
	/**
	 * Takes a 2d array and saves it as a Linked Array List
	 *
	 * @param arr 1st dimension stands for Node, second dimension stands for the data of the Node
	 */
	public void setList(E[][] arr)
	{
		Node<E> currNode = head;

		for (E[] eArr : arr) {
			for (E e : eArr) {
				currNode.add(e);
				size++;
			}

			currNode.setNext(new Node<>());
			currNode.next().setPrev(currNode);

			currNode = currNode.next();
		}

		tail = currNode.prev();
		tail.setNext(null);
	}

	/**
	 * Transforms the List to a string
	 *
	 * @return Printable string of the List
	 */
	public String getList()
	{
		StringBuilder str = new StringBuilder();
		Node<E> currNode = head;

		for(int j = 1; currNode != null; j++)
		{
			str.append(j).append(". Node:   ");

			for (int i = 0; i < currNode.size(); i++)
				str.append(currNode.get(i)).append(" - ");

			str.append(" size:").append(currNode.size()).append("\n");
			currNode = currNode.next();
		}

		return str.toString();
	}

}
