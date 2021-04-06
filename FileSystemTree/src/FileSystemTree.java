import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.Scanner;

/** Manages a file system with a tree structure. */
public class FileSystemTree {

//enum
	/** Type of files in the file system. */
	private enum Type {
		ROOT, DIR, FILE
	}

//nested class
	/** Holds the information about a file in the system. */
	private static class FileNode {
		/** Name of the file. */
		String name;
		/** Children of the file. A file can only have children if it is a directory. */
		ArrayList<FileNode> children;
		/** Type of the file. */
		Type type;

		/**
		 * Constructs a file node with given name and type.
		 * @param newName Name of the constructed file node.
		 * @param newType Type of the constructed file node.
		 */
		FileNode (String newName, Type newType) {
			name = newName;
			type = newType;

			if (type != Type.FILE)
				children = new ArrayList<>();
		}

		/**
		 * Finds the position of the child node with given name.
		 * @param name Name of the searched node.
		 * @return The index of the child node with given name. -1 if it does not exist.
		 * @throws UnsupportedOperationException If the file is not a directory it throws this exception.
		 */
		public int hasChildWithSameName (String name) throws UnsupportedOperationException {
			//Files can not have children
			if (type == Type.FILE)
				throw new UnsupportedOperationException();
			//Search children with the given name
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i).name.equals(name))
					return i;
			}
			return -1;
		}
	}

//fields
	/** Root directory of the system */
	private FileNode root;

	/**
	 * Constructs a file system with a root directory with the given mame
	 * @param name Name of the root directory of the system.
	 */
	FileSystemTree (String name) {
		root = new FileNode(name, Type.ROOT);
	}

//methods

	/**
	 * Adds a directory to the file system in a given path.
	 * @param path Path of the new directory starting from root.
	 */
	public void addDir (String path) {
		addChild(path,Type.DIR);
	}

	/**
	 * Adds a file to the file system in a given path.
	 * @param path Path of the new file starting from root.
	 */
	public void addFile (String path) {
		addChild(path,Type.FILE);
	}

	/**
	 * Adds a file or directory to the file system in a given path.
	 * @param path Path of the new file or directory starting from root.
	 * @param fileType Defines the added node if it is a file or directory.
	 */
	private void addChild (String path, Type fileType) {

		BiFunction<FileNode,String,FileNode> f = (node, fileName) -> {
			if (node.hasChildWithSameName(fileName) != -1)
				System.out.println("Directory already contains a file with the same name.");
			else
				node.children.add(new FileNode(fileName, fileType));
			return node;
		};

		root = modifierPreOrderTraversal(root,path,f);
	}

	/**
	 * Removes a node from the file system in a given path.
	 * @param path Path of the node starting from root.
	 */
	public void remove (String path) {
		BiFunction<FileNode,String,FileNode> f = (node,fileName) -> {
			boolean control = true;
			int childIndex = node.hasChildWithSameName(fileName);

			if (childIndex == -1)
				System.out.println("File does not exist! Request denied.");
			else {
				if (node.children.get(childIndex).type != Type.FILE) {
					if (node.children.get(childIndex).children.size() != 0) {
						System.out.println("The file you wanted to delete includes some other directories:\n");
						printStartingFromNode(node.children.get(childIndex));
						System.out.println("\nDo you want to proceed? (y/n)");

						Scanner scan = new Scanner(System.in);
						if (scan.nextLine().equals("n"))
							control = false;
					}
				}
				if (control)
					node.children.remove(childIndex);
			}

			return node;
		};

		root = modifierPreOrderTraversal(root,path,f);
	}

	/**
	 * Searches the whole file system and prints the nodes with given search characters in their name.
	 * @param searchChars Searched characters.
	 */
	public void search (String searchChars) {
		System.out.println("Not implemented.");
		/*
		BiFunction<FileNode,String,StringBuilder> f1 = node, name -> {
			StringBuilder str = new StringBuilder();
			return str;
		};

		BiFunction<FileNode,Function<FileNode,StringBuilder>,StringBuilder> f2 = (node, f) -> {
			StringBuilder str = new StringBuilder();
			StringBuilder path = observerPreOrderTraversal(root,new StringBuilder(),0,f);

			if (node.name.contains(searchChars))
				str.append(node.type).append(" - ").append(path).append("/").append(node.name).append("\n");

			return str;
		};
		 */
	}

	/**
	 * Prints the whole file system with their hierarchy starting from root file.
	 */
	public void printSystem () {
		System.out.println("___File System___");
		printStartingFromNode(root);
	}

	/**
	 * Prints the children nodes of the given node.
	 * @param startingNode Parent of the nodes that will be printed.
	 */
	private void printStartingFromNode (FileNode startingNode) {
		BiFunction<FileNode, Integer, StringBuilder> f = (node,height) -> {
			StringBuilder str = new StringBuilder();

			for (int i = 0; i < height; i++)
				str.append("  ");
			str.append(node.name).append("\n");
			return str;
		};

		System.out.println( observerPreOrderTraversal(startingNode,new StringBuilder(),0,f) );
	}

	/**
	 * Recursive function that traverses whole file system in pre order traversal.
	 * @param node Starting node of the traverse.
	 * @param str String that holds the names of files traversed.
	 * @param height Depth of the recursion.
	 * @param f Function that is called at every node.
	 * @return result of the traversing with given function parameter.
	 */
	private StringBuilder observerPreOrderTraversal (FileNode node, StringBuilder str, int height, BiFunction<FileNode, Integer, StringBuilder> f) {

		str.append( f.apply(node,height) );

		if (node.type != Type.FILE) {
			if (node.children.size() > 0) {
				for (int i = 0; i < node.children.size(); i++) {
					str = observerPreOrderTraversal(node.children.get(i), str, height + 1, f);
				}
			}
		}
		return str;
	}

	/**
	 * Recursive function that traverses file system with the given file path in pre order traversal.
	 * @param node Node we are currently at.
	 * @param path Remaining file path that will be traversed.
	 * @param f Function that is called when we reached the target node in the file path.
	 * @return The node that function currently traversed.
	 */
	private FileNode modifierPreOrderTraversal (FileNode node, String path, BiFunction<FileNode,String,FileNode> f) {
		String[] separated;

		//If the current node is root remove root from the file path
		if (node.type == Type.ROOT) {
			separated = path.split("/", 2);
			//Root name is different
			if ( !node.name.equals(separated[0]) ) {
				System.out.println("Illegal file path! Request denied.");
				return node;
			}
			path = separated[1];
		}

		//If file path have only one file left in it, recursive function end is reached
		if ( !path.contains("/") )
			if(node.type != Type.FILE)
				return f.apply(node, path);
			else
				//If given file is a file, wanted functionality is illegal
				System.out.println("File path includes a file as a directory! Request denied.");
		else {
			separated = path.split("/", 2);
			path = separated[1];

			//Trying to find the next file in the file path as this node's child
			boolean control = false;
			int nodeIndex = -1;
			try{
				for (int i = 0; (i < node.children.size() && !control); i++) {
					//find the node with given name
					if (node.children.get(i).name.equals(separated[0])) {
						control = true;
						nodeIndex = i;
					}
				}
			} //Only directories can have children
			catch (NullPointerException ex) {
				System.out.println("File path includes a file as a directory! Request denied.");
			}

			//If everything went ok continue with recursive call
			if( control )
				node.children.set(nodeIndex, modifierPreOrderTraversal(node.children.get(nodeIndex), path, f) );
			else
				System.out.println("Illegal file path! Request denied.");
		}
		//If this method is not returned or a recursive call is not called previously, recursion of this method ends
		return node;
	}
}