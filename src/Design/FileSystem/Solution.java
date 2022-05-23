/**
 * 
 */
package Design.FileSystem;

import java.util.List;

/**
 * @author rahul
 *
 */
public class Solution {

	public static void main(String[] args) {
		 FileSystem fileSystem = new FileSystem();
	   
		fileSystem.addFileToCollection("file1.txt", 100, "");
		fileSystem.addFileToCollection("file2.txt", 100, "collection1");
		fileSystem.addFileToCollection("file3.txt", 200, "collection1");
		fileSystem.addFileToCollection("file4.txt", 300, "collection3");
		fileSystem.addFileToCollection("file5.txt", 400, "collection4");
	    fileSystem.addFileToCollection("file2.txt", 100, "collection1");
	    fileSystem.addFileToCollection("file3.txt", 400, "collection3");	

		System.out.println(fileSystem.totalProcessedFile());
	        
		// List<Collection> collections = fileSystem.topNCollection(2);
		 fileSystem.topNCollections(2);
	 }

}
