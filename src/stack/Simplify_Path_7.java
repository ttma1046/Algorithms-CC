package stack;
import java.util.Stack;
/*

Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

 

Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"

*/

class Simplify_Path_7 {
    public String simplifyPath(String path) {
        if (path.isEmpty() || path == null) {
            return path;
        }

        String[] temp = path.split("/");
        String[] resultArray = new String[temp.length];
        int j = 0;
        for(String item: temp) {
            if (item.equals("..")) {
                if (j > 0) {
                    resultArray[--j] = "";
                }
                
                continue;
            }  
            
            if (item.equals(".") || item.isEmpty() || item == null) {
                continue;
            }

            resultArray[j++] = item;
        }
       
		StringBuilder result = new StringBuilder();
        for(int t = 0; t < resultArray.length; t++) {
            if (resultArray[t] != null && !resultArray[t].isEmpty()) {
                result.append("/");
                result.append(resultArray[t]);
            }
        }
        
        return result.length() > 0 ? result.toString() : "/";
    }

    public String simplifyPathII(String path) {
        
        // Handle empty string
        if (path.isEmpty()) {
            return path;
        }
        
        // Initialize a stack
        Stack<String> stack = new Stack<String>();
        String[] components = path.split("/");
        
        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String directory : components) {
            
            // A no-op for a "." or an empty string
            if (directory.equals(".") || directory.isEmpty()) {
                continue;
            } else if (directory.equals("..")) {
                
                // If the current component is a "..", then
                // we pop an entry from the stack if it's non-empty
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                
                // Finally, a legitimate directory name, so we add it
                // to our stack
                stack.add(directory);
            }
        }
        
        // Stich together all the directory names together    
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }
        
        return result.length() > 0 ? result.toString() : "/" ;
    }

    public static void main(String[] args) {
    	System.out.println(new Simplify_Path_7().simplifyPath("/home/"));
    	System.out.println(new Simplify_Path_7().simplifyPath("/../"));
    	System.out.println(new Simplify_Path_7().simplifyPath("/home//foo/"));
    	System.out.println(new Simplify_Path_7().simplifyPath("/a/./b/../../c/"));
    	System.out.println(new Simplify_Path_7().simplifyPath("/a/../../b/../c//.//"));
    	System.out.println(new Simplify_Path_7().simplifyPath("/a//b////c/d//././/.."));
    }

    public String simplifyPathIII(String path) {
        if (path.isEmpty() || path == null) {
            return path;
        }

        Stack<String> myStack = new Stack<String>();
        String[] components = path.split("/");

        for (String item: components) {
            if (item.equals(".") || item.isEmpty()) {
                continue;
            } else if (item.equals("..")) {
                if (!myStack.isEmpty()) {
                    myStack.pop();
                }
            } else {
                myStack.push(item);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String item: myStack) {
            result.append("/");
            result.append(item);
        }

        return result.length() > 0 ? result.toString() : "/";
    }
}