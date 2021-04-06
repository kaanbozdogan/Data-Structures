import java.util.*;

/**
 * For some reason writing @override does not copy the javadocs of the overridden functions.
 */
public class GtuDeque<E> implements Deque<E>
{
//fields
	private Node<E> head;
	private Node<E> tail;

	private int size;

	private Node<E> removed;


//overridden methods
	@Override
	public void addFirst(E e)
	{
		//if the deque is empty
		if(head == null)
		{
			//if removed nodes are empty create a new node
			if(removed == null)
				head = new Node<E>(e);
			//else take one of the removed nodes and use it as a new node
			else
			{
				head = removed;
				removed = removed.next();

				head.reset(e,null,null);
			}
			//deque has only one element
			tail = head;
		}
		else
		{
			//if removed nodes are empty create a new node
			if(removed == null)
				head = new Node<>(e,head,null);
			//else take one of the removed nodes and use it as a new node
			else
			{
				Node<E> temp = head;

				head = removed;
				removed = removed.next();

				head.reset(e,temp,null);
			}
			//old head and new head is linked
			head.next().setPrev(head);
		}
		size ++;
	}

	@Override
	public void addLast(E e)
	{
		//if the deque is empty
		if(tail == null)
		{
			//if removed nodes are empty create a new node
			if(removed == null)
				tail = new Node<>(e);
			//else take one of the removed nodes and use it as a new node
			else
			{
				tail = removed;
				removed = removed.next();

				tail.reset(e,null,null);
			}
			//deque has only one element
			head = tail;
		}
		else
		{
			//if removed nodes are empty create a new node
			if(removed == null)
				tail = new Node<>(e,null,tail);
			//else take one of the removed nodes and use it as a new node
			else
			{
				Node<E> temp = tail;

				tail = removed;
				removed = removed.next();

				tail.reset(e,null,temp);
			}
			//old head and new tail is linked
			tail.prev().setNext(tail);
		}

		size ++;
	}

	@Override
	public boolean offerFirst(E e)
	{
		try{
			addFirst(e);
			return true;
		}
		catch (Exception ex){
			return false;
		}
	}

	@Override
	public boolean offerLast(E e)
	{
		try{
			addLast(e);
			return true;
		}
		catch (Exception ex){
			return false;
		}
	}

	@Override
	public E removeFirst()
	{
		if(head == null)
			throw new NoSuchElementException();

		Node<E> temp = head;

		head = head.next();
		size --;

		if(size == 0)
			tail = null;
		else
			head.setPrev(null);

		temp.setNext(removed);
		removed = temp;

		return temp.get();
	}

	@Override
	public E removeLast()
	{
		if(tail == null)
			throw new NoSuchElementException();

		Node<E> temp = tail;

		tail = tail.prev();
		size --;

		if(size == 0)
			head = null;
		else
			tail.setNext(null);

		temp.setNext(removed);
		removed = temp;

		return temp.get();

	}

	@Override
	public E pollFirst()
	{
		try{
			return removeFirst();
		}
		catch (Exception ex){
			return null;
		}
	}

	@Override
	public E pollLast()
	{
		try{
			return removeLast();
		}
		catch (Exception ex){
			return null;
		}
	}

	@Override
	public E getFirst()
	{
		if(head == null)
			throw new NoSuchElementException();

		return head.get();
	}

	@Override
	public E getLast()
	{
		if(tail == null)
			throw new NoSuchElementException();

		return tail.get();
	}

	@Override
	public E peekFirst()
	{
		if(head == null)
			return null;
		else
			return head.get();
	}

	@Override
	public E peekLast()
	{
		if(tail == null)
			return null;
		else
			return tail.get();
	}

	@Override
	public boolean add(E e)
	{
		addLast(e);
		return true;
	}

	@Override
	public boolean offer(E e)
	{
		offerLast(e);
		return true;
	}

	@Override
	public E remove() {
		return removeFirst();
	}

	@Override
	public E poll() {
		return removeLast();
	}

	@Override
	public E element() {
		return getFirst();
	}

	@Override
	public E peek() {
		return peekFirst();
	}

	@Override
	public void clear()
	{
		if(head != null)
		{
			//the whole deque added to the removed list
			tail.setNext(removed);
			removed = head;

			head = null;
			tail = null;

			size = 0;
		}
	}

	@Override
	public void push(E e) {
		addFirst(e);
	}

	@Override
	public E pop() {
		return removeFirst();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new GtuDequeIterator<>(head,false);
	}

	@Override
	public Iterator<E> descendingIterator() {
		return new GtuDequeIterator<>(tail,true);
	}

	@Override
	public boolean removeFirstOccurrence(Object o)
	{
		Node<E> node = head;

		//finding the first occurrence in the list
		try{
			while (node.get() != o)
				node = node.next();
		}
		//given element does not exist in the deque
		catch(NullPointerException ex) {
			return false;
		}

		fixTheLinksOfDeletedNode(node);

		node.setNext(removed);
		removed = node;

		return true;
	}

	@Override
	public boolean removeLastOccurrence(Object o)
	{
		Node<E> node = tail;

		//finding the first occurrence in the list
		try{
			while (node.get() != o)
				node = node.prev();
		}
		//given element does not exist in the deque
		catch(NullPointerException ex) {
			return false;
		}

		fixTheLinksOfDeletedNode(node);

		node.setNext(removed);
		removed = node;

		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		for (E e : c)
			add(e);

		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		Iterator<?> it = c.iterator();

		while(it.hasNext())
			remove(it.next());

		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	  {
		Iterator<?> it = c.iterator();
		Object e;

		while(it.hasNext())
		{
			e = it.next();

			if( !contains(e) )
				it.remove();
		}

		return true;
	}

	@Override
	public boolean remove(Object o) {
		return removeFirstOccurrence(o);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		Iterator<?> it = c.iterator();

		while( it.hasNext() )
			if( !contains(it.next()) )
				return false;

		return true;
	}

	@Override
	public boolean contains(Object o)
	{
		Node<E> node = head;

		try{
			while (node.get() != o)
				node = node.next();
		}
		catch(NullPointerException ex) {
			return false;
		}

		return true;
	}

	@Override
	public Object[] toArray()
	{
		E[] arr = (E[]) new Object[size];

		Node<E> node = head;
		for(int i = 0; node != null; i++, node = node.next())
			arr[i] = node.get();

		return arr;
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		if(a.length > size)
			a = (T[]) new Object[size];

		Node<E> node = head;
		for(int i = 0; node != null; i++, node = node.next())
			a[i] = (T) node.get();

		return a;
	}

//helper methods
	private void fixTheLinksOfDeletedNode(Node<E> node)
	{
		//if the deleted node not tail
		if(node.next() != null)
			node.next().setPrev(node.prev());
		else
			tail = node.prev();

		//if the deleted node is not head
		if(node.prev() != null)
			node.prev().setNext(node.next());
		else
			head = node.next();
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		Node<E> currNode = head;

		while(currNode != null)
		{
			str.append(currNode.get()).append(" , ");
			currNode = currNode.next();
		}

		return str.toString();
	}
}
