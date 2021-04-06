
public class Node<E>
{
//fields
	/** The node comes after this node in a list structure */
	private Node<E> next;

	/** The node comes before this node in a list structure */
	private Node<E> prev;

	/** Data of the node */
	private E[] data;

	/** Data count of the node */
	private int size;

	/** Maximum element count that a node can have */
	private static final int CAPACITY = 10;

//methods
	/** Constructs a Node with zero elements */
	public Node() {
		data = (E[]) new Object[CAPACITY];
	}

	/**
	 * Controls if the node has more capacity to add elements
	 * @return True if there is no more space left to add elemenets
	 */
	public boolean isFull() {
		return size == CAPACITY;
	}

	/**
	 * Returns the element count
	 * @return Element count
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the maximum element count that a node can have
	 * @return Maximum element count that a node can have
	 */
	public int maxSize(){
		return CAPACITY;
	}

	/**
	 * Returns the next node
	 * @return Next node
	 */
	public Node<E> next() {
		return next;
	}

		/**
	 * Returns the previous node
	 * @return Previous node
	 */
	public Node<E> prev() {
		return prev;
	}

	/**
	 * Sets a new next Node
	 * @param newNext The new next node
	 */
	public void setNext(Node<E> newNext) {
		next = newNext;
	}

	/**
	 * Sets a new previous Node
	 * @param newPrev The new previous node
	 */
	public void setPrev(Node<E> newPrev) {
		prev = newPrev;
	}

	/**
	 * Returns the element at the given index
	 * @param index Index of the element returned
	 * @return Element at the given index
	 */
	public E get(int index)
	{
		if(0<=index && index <= size)
			return data[index];
		else
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Sets the element at the given index with a new element
	 * @param index Index of the element changed
	 * @param element The new element
	 */
	public void set(int index, E element)
	{
		if(0<=index && index <= size)
			data[index] = element;
		else
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Adds the new element at the end of the node
	 * @param element New element added
	 */
	public void add(E element)
	{
		data[size] = element;
		size ++;
	}

	/**
	 * Adds the new element at the given index
	 * @param index New element's index
	 * @param element New element added
	 */
	public void add(int index, E element)
	{
		if(index<0 || size < index)
			throw new IndexOutOfBoundsException();
		else
			size ++;

			//creating space for new element with shifting
			for (int i = size - 2; i >= index; i--)
				data[i + 1] = data[i];

			//adding the new element to the position we just created
			data[index] = element;
	}

	/**
	 * Removes the element at the given index
	 * @param index Removed element's index
	 * @return Removed element
	 */
	public E remove(int index)
	{
		E returning = data[index];

		for (int i = index; i < size - 1; i++)
			data[i] = data[i + 1];

		size --;

		return returning;
	}

}
