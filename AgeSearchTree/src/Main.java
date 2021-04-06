public class Main {

	public static void main(String[] args) {
		AgeSearchTree at = new AgeSearchTree();

		System.out.println("________________________________");
		System.out.println("-> Initializing the three using add() with inputs in order \"10,10,5,6,6,6,4,15,13,13\"");

		at.add(new AgeData(10));
		at.add(new AgeData(10));
		at.add(new AgeData(5));
		at.add(new AgeData(6));
		at.add(new AgeData(6));
		at.add(new AgeData(6));
		at.add(new AgeData(4));
		at.add(new AgeData(15));
		at.add(new AgeData(13));
		at.add(new AgeData(13));


		System.out.println("________________________________");
		System.out.println("-> Printing the tree using toString()");

		System.out.println(at.toString());


		System.out.println("________________________________");
		System.out.println("-> Finding an age data which exist in the tree");

		System.out.println(at.find(new AgeData(6)));


		System.out.println("________________________________");
		System.out.println("-> Finding an age data which does not exist in the tree");

		System.out.println(at.find(new AgeData(99)));


		System.out.println("________________________________");
		System.out.println("-> youngerThan(10): " + at.youngerThan(new AgeData(10)));


		System.out.println("________________________________");
		System.out.println("-> olderThan(10): " + at.olderThan(new AgeData(10)));


		System.out.println("________________________________");
		System.out.println("-> Removing an age with multiple members -> remove(6)");

		System.out.println("Item Removed: " + at.remove(new AgeData(6)));

		System.out.println(at.toString());


		System.out.println("________________________________");
		System.out.println("-> Removing an age with one members -> remove(5)");

		System.out.println("Item Removed: " + at.remove(new AgeData(5)));

		System.out.println(at.toString());


		System.out.println("________________________________");
		System.out.println("-> Removing an age which does not exist -> remove(99)");

		System.out.println("Item Removed: " + at.remove(new AgeData(99)));

		System.out.println(at.toString());
	}

}
