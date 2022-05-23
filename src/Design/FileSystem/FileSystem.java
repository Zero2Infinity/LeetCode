/**
 * 
 */
package Design.FileSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author rahul
 *
 */
interface FileAttributes {};

class File implements FileAttributes {
	String fileName;
	int fileSize;
	// Collection collection;
	
	File(String name, int size) { fileName = name; fileSize = size; }
}

class Collection implements FileAttributes {
	String collectionName;
	List<File> files;
	int collectionSize;
	
	Collection(String name) { collectionName = name; files = new ArrayList<>(); collectionSize = 0; }
}

public class FileSystem {
	// Note: Structure was crucial
	Map<String, FileAttributes> fileTables;
	int fileSystemSize;


	// Top K Freq. Problem
	Set<Collection> collectionInHeap = new HashSet<Collection>();
	PriorityQueue<Collection> pq;
	
	public FileSystem() {
		fileTables = new HashMap<>();
	 	fileSystemSize = 0;
	 	
	 	pq = new PriorityQueue<Collection>((a,b) -> {
			 if (a.files.size() != b.files.size()) {
				return b.files.size() - a.files.size();
			} else {
				return b.collectionSize - a.collectionSize;
			}
	 	});
	}

	public int totalProcessedFile() {
		return fileSystemSize;
	}

	public void topNCollections(int k) {
		// topNCollections_Sorting(k);
		topNCollections_PQ(k);
	}

	private void topNCollections_Sorting(int k) {
		List<Collection> onlyCollections = new ArrayList<>();

		// Time: O(FileTables)
		for (Map.Entry<String, FileAttributes> entry : fileTables.entrySet()) {
			if (entry.getValue().getClass().equals(Collection.class)) {
				onlyCollections.add((Collection) entry.getValue());
			}
		}
		
		Comparator<Collection> sortByCollectionCount = new Comparator<>() {
			@Override
			public int compare(Collection a, Collection b) {

				if (a.files.size() != b.files.size()) {
					return b.files.size() - a.files.size();
				} else {
					return b.collectionSize - a.collectionSize;
				}
			}

		};
		
		// Time: O(N LogN)
		Collections.sort(onlyCollections, sortByCollectionCount);
		
		// Time: O(k)
		for (int i = 0; i < k; i++) {
			System.out.println("Collection: " + onlyCollections.get(i).collectionName + 
								" size: " + onlyCollections.get(i).collectionSize);
		}
		
	}

	private void topNCollections_PQ(int k) {
		for (int i = 0; i < k; i++) {
			Collection c = pq.poll();
			System.out.println("Collection name: " + c.collectionName + " size: " + c.collectionSize);
		}
	}

	public void addFileToCollection(String name, int s, String coll) {
		if (coll == "") {
			fileTables.put(name, new File(name, s));
		} else {
			Collection c = (Collection) fileTables.getOrDefault(coll, new Collection(coll));
			c.files.add(new File(name, s));
			c.collectionSize += s;
			
			// Note: What about duplicate situation?
			// Time: O(Log N) - Height of the tree
			if (!collectionInHeap.contains(c)) {
				pq.add(c);
				collectionInHeap.add(c);
			}

			fileTables.put(coll, c);
		}
		
		fileSystemSize += s;
	}

}
