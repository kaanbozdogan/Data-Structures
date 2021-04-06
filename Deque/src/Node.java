import java.lang.annotation.ElementType;

public class Node<E>
{
//fields
	private E element;
	private Node<E> next;
	private Node<E> prev;

//methods

	/**
	 * Constructor that takes the value of the element. Constructed object does not point to any other Node.
	 * @param newElement The value of the element
	 */
	Node(E newElement)
	{
		element = newElement;
	}

	/**
	 * Constructor that sets the value of the element, next node and previous node.
	 * @param newElement The value of the element.
	 * @param newNext Next node in the list.
	 * @param newPrev Previous node in the list.
	 */
	Node(E newElement, Node<E> newNext, Node<E> newPrev)
	{
		element = newElement;
		next = newNext;
		prev = newPrev;
	}

	/**
	 * Resets the values of an existing node like its created newly.
	 * @param newElement The value of the element.
	 * @param newNext Next node in the list.
	 * @param newPrev Previous node in the list.
	 */
	void reset(E newElement, Node<E> newNext, Node<E> newPrev)
	{
		element = newElement;
		next = newNext;
		prev = newPrev;
	}

	/**
	 * Gets the value of the element in the node.
	 * @return The value of the element.
	 */
	E get(){
		return element;
	}

	/**
	 * Sets the value of the element in the node.
	 * @param newElement The new value of the element.
	 */
	void set(E newElement){
		element = newElement;
	}

	/**
	 * Returns the reference to the next node in the list
	 * @return The next index in the list
	 */
	Node<E> next(){
		return next;
	}

	/**
	 * Returns the reference to the previous node in the list
	 * @return The previous index in the list
	 */
	Node<E> prev(){
		return prev;
	}

	/**
	 * Sets the next node to a new node reference.
	 * @param next The new node reference.
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}

	/**
	 * Sets the previous node to a new node reference.
	 * @param prev The new node reference.
	 */
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}

}
