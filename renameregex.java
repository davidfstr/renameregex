import java.io.*;
import java.util.*;
import java.util.regex.*;

public class renameregex {
	public static void main(String[] args) {
		if ((args.length != 2) || args[0].length()==0 || args[1].length()==0) {
			//System.out.println(java.util.Arrays.asList(args));
			System.out.println("syntax: renameregex <src-regex> <dst-regex>");
			System.exit(1);
			return;
		}
		
		Pattern srcRegex = Pattern.compile("^" + args[0] + "$");
		String dstRegex = args[1];
		
		// Determine rename operations
		Map<String,String> renameOps = new LinkedHashMap<String,String>();
		Set<String> allDstFilenames = new HashSet<String>();
		boolean dupDstFilenames = false;
		for (String srcFilename : new File(".").list()) {
			Matcher m = srcRegex.matcher(srcFilename);
			if (m.matches()) {
				String dstFilename = m.replaceFirst(dstRegex);
				
				renameOps.put(srcFilename, dstFilename);
				if (!allDstFilenames.add(dstFilename)) {
					dupDstFilenames = true;
				}
			}
		}
		
		// Present proposed rename operations and confirm
		for (Map.Entry<String,String> curEntry : renameOps.entrySet()) {
			String srcFilename = curEntry.getKey();
			String dstFilename = curEntry.getValue();
			System.out.println(srcFilename + " => " + dstFilename);
		}
		System.out.println();
		if (dupDstFilenames) {
			System.out.println("Multiple source files would be renamed to the same destination file. Aborted.");
			return;
		}
		System.out.print("Okay? [y/N] ");
		
		String ans = new Scanner(System.in).nextLine();
		if (ans.equalsIgnoreCase("y")) {
			// Do rename operations
			for (Map.Entry<String,String> curEntry : renameOps.entrySet()) {
				String srcFilename = curEntry.getKey();
				String dstFilename = curEntry.getValue();
				
				new File(srcFilename).renameTo(new File(dstFilename));
			}
		}
	}
}