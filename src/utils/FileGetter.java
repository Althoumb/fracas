package utils;

import java.io.File;
import java.util.ArrayList;

public class FileGetter {
	public static ArrayList<File> filesInFolder(final File folder) {
		ArrayList<File> files = new ArrayList<File>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            filesInFolder(fileEntry);
	        } else {
	            files.add(fileEntry);
	        }
	    }
	    return files;
	}
}
