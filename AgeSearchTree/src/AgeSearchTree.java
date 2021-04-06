public class AgeSearchTree extends BinarySearchTree<AgeData> {
//methods
	@Override
	protected Node<AgeData> add(Node<AgeData> node, AgeData item) {
		// this method overrides the recursive add method in the super class.
		//age with the same name does not exist. create new node.
		if (node == null) {
			addReturn = true;
			return new Node<>(item);
		}
		//age with the same name exists. create add it to the current node.
		else if (item.compareTo(node.data) == 0) {
			node.data.peopleCount ++;
			addReturn = true;
			return node;
		}
		//age is bigger. continue with the left node.
		else if (item.compareTo(node.data) < 0) {
			node.left = add(node.left, item);
			return node;
		}
		//age is smaller. continue with the right node.
		else {
			node.right = add(node.right, item);
			return node;
		}
	}

	@Override
	public boolean remove(AgeData target) {
		root = remove(root, target);
		return deleteReturn != null;
	}

	private Node<AgeData> remove(Node<AgeData> node, AgeData item) {
		// item is not in the tree.
		if (node == null) {
			// should have thrown an exception but just returned null for TA
			deleteReturn = null;
			return null;
		}
		int compResult = item.compareTo(node.data);
		// node is older. item can only be at the left subtree.
		if (compResult < 0) {
			node.left = remove(node.left, item);
			return node;
		}
		// node is younger. item can only be at the right subtree.
		else if (compResult > 0) {
			node.right = remove(node.right, item);
			return node;
		}
		// node with the item found.
		else {
			// node has more than one members. just decrease the count by 1.
			if (node.data.peopleCount > 1) {
				node.data.peopleCount --;
				deleteReturn = node.data;
				return node;
			}
			// node has only one member. need to delete the node
			else {
				deleteReturn = node.data;
				if (node.left == null) {
					return node.right;
				}
				else if (node.right == null) {
					return node.left;
				}
				// deleted node has two children.
				else {
					// if left node does not have a right child then the left node is the oldest node in the left subtree.
					if (node.left.right == null) {
						node.data = node.left.data;
						node.left = node.left.left;
						return node;
					}
					// the oldest node in the left subtree becomes the new local root of the left subree.
					else {
						node.data = findLargestChild(node.left);
						return node;
					}
				}
			}
		}
	}

	/**
	 * Calculates the number of people younger than the given age in the tree.
	 * @param target Given age.
	 * @return The number of people younger than the given age.
	 */
	public int youngerThan(AgeData target) {
		return youngerThan(root,target);
	}

	private int youngerThan(Node<AgeData> node, AgeData target) {
		if (node == null)
			return 0;
		//curr node is older than or equal to the target. Only left node can be younger than the target node.
		if (target.compareTo(node.data) <= 0)
			return youngerThan(node.left,target);
			//curr node is younger than the target. All the child nodes are younger than the target node.
		else {
			int count = 0;
			count += node.data.peopleCount;
			count += youngerThan(node.left,target);
			count += youngerThan(node.right,target);
			return count;
		}
	}

	/**
	 * Calculates the number of people older than the given age in the tree.
	 * @param target Given age.
	 * @return The number of people older than the given age.
	 */
	public int olderThan(AgeData target) {
		return olderThan(root,target);
	}

	private int olderThan(Node<AgeData> node, AgeData target) {
		if (node == null)
			return 0;
		//curr node is younger than or equal to the target. Only right node can be older than the target node.
		if (target.compareTo(node.data) >= 0)
			return olderThan(node.right,target);
			//curr node is older than the target. All the child nodes are older than the target node.
		else {
			int count = 0;
			count += node.data.peopleCount;
			count += olderThan(node.left,target);
			count += olderThan(node.right,target);
			return count;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root,sb);
		return sb.toString();
	}

	private void toString(Node<AgeData> node, StringBuilder sb) {
		if(node == null)
			return ;

		sb.append(node.data.toString());
		toString(node.left,sb);
		toString(node.right,sb);
	}
}
