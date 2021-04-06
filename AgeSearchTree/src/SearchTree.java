public interface SearchTree<E extends Comparable<E>> {
	public boolean add(E item);
	public boolean contains(E target);
	public E find(E target);
	public E delete(E target);
	public boolean remove(E target);
}
