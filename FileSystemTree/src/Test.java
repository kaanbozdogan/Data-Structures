public class Test {

	static public void main (String[] args){

		FileSystemTree myFileSystem = new FileSystemTree("root");

		System.out.println("System constructed and files added to the system with below codes\n");

		myFileSystem.addDir("root/first_directory");
		myFileSystem.addDir("root/second_directory");
		myFileSystem.addFile("root/first_directory/new_file.txt");
		myFileSystem.addDir("root/second_directory/new_directory");
		myFileSystem.addFile("root/second_directory/new_directory/new_file.doc");
		
		System.out.println("myFileSystem.addDir(\"root/first_directory\")");
		System.out.println("myFileSystem.addDir(\"root/second_directory\")");
		System.out.println("myFileSystem.addFile(\"root/first_directory/new_file.txt\")");
		System.out.println("myFileSystem.addDir(\"root/second_directory/new_directory\")");
		System.out.println("myFileSystem.addFile(\"root/second_directory/new_directory/new_file.doc\")\n");

		myFileSystem.printSystem();


		System.out.println("-> Trying to add a directory and a file to a file. Files can not have children:");

		System.out.println("\naddDir(\"root/first_directory/new_file.txt/illegal.txt\")");
		myFileSystem.addDir("root/first_directory/new_file.txt/illegal_dir");

		System.out.println("\naddFile(\"root/first_directory/new_file.txt/illegal.txt\")");
		myFileSystem.addFile("root/first_directory/new_file.txt/illegal.txt");


		System.out.println("\n\n-> Trying to add a directory and a file with a path that does not exist:");

		System.out.println("\naddDir(\"root/illegal/illegal_dir\")");
		myFileSystem.addDir("root/illegal/illegal_dir");

		System.out.println("\naddFile(\"root/illegal/illegal.txt\")");
		myFileSystem.addFile("root/illegal/illegal.txt");


		System.out.println("\n\n-> Trying to add a file and directory which has the same name with a file in the current directory:");

		System.out.println("\naddDir(\"root/second_directory/new_directory\")");
		myFileSystem.addDir("root/second_directory/new_directory");

		System.out.println("\naddFile(\"root/first_directory/new_file.txt\")");
		myFileSystem.addFile("root/first_directory/new_file.txt");


		System.out.println("\n\n-> Trying to remove a file that does not exist:");

		System.out.println("\nremove(\"root/illegal_dir\")");
		myFileSystem.remove("root/illegal_dir");


		System.out.println("\n\n-> Trying to remove with a path that does not exist:");

		System.out.println("\nremove(\"root/illegal_dir/illegal\")");
		myFileSystem.remove("root/illegal_dir/illegal");


		System.out.println("\n\n-> Removing a file:");

		System.out.println("\nremove(\"root/first_directory/new_file.txt\")\n");
		myFileSystem.remove("root/first_directory/new_file.txt");

		myFileSystem.printSystem();


		System.out.println("\n-> Removing a directory which includes some other contents. Asks the user:");

		System.out.println("\nremove(\"root/second_directory/new_directory\")");
		myFileSystem.remove("root/second_directory/new_directory");

		myFileSystem.printSystem();


	}
}
