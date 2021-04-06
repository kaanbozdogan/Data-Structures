public class AgeData implements Comparable<AgeData> {
//fields
	protected int age;
	protected int peopleCount;

//Constructors
	/**
	 * Constructs an age data with the given age and one member.
	 * @param age The age of the age data object.
	 */
	AgeData (int age) {
		this.age = age;
		peopleCount = 1;
	}

//methods
	@Override
	public int compareTo(AgeData o) {
		return age - o.age;
	}

	@Override
	public String toString() {
		return age + "\t-\t" + peopleCount + "\n";
	}
}