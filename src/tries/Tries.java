package tries;

import java.util.HashMap;

public class Tries {
    class Node {
        HashMap<Character, Node> children;
        boolean isCompleteWord;

    }
}

/*
Don't look up each prefix from the root
-> Build on past calls
By
 * keeping state in the tries
 * Return Node reference
*/
