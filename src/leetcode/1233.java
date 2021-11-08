Method 1: sort by length then put only parent into HashSet

Sort folder by length;
Check if the floder's parent fold in HashSet before adding it into the HashSet.
Note: the part before any / is a parent.
Java

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, Comparator.comparing(s -> s.length()));
        Set<String> seen = new HashSet<>();
        outer:
        for (String f : folder) {
            for (int i = 2; i < f.length(); ++i)
                if (f.charAt(i) == '/' && seen.contains(f.substring(0, i))) 
                    continue outer;
            seen.add(f);
        }
        return new ArrayList<>(seen);
    }
Analysis

Time: O(n * (logn + m ^ 2)), space: (n * m), where n = folder.length, m = average size of the strings in folder.

Method 2: sort folders

Sort the folders;
For each folder check if the followings are child folders; if yes, ignore; otherwise, count it in.
    public List<String> removeSubfolders(String[] folder) {
        LinkedList<String> ans = new LinkedList<>();
        Arrays.sort(folder);
        for (String f : folder)
            if (ans.isEmpty() || !f.startsWith(ans.peekLast() + '/')) //  need '/' to ensure a parent.
                ans.offer(f);
        return ans;
    }
    def removeSubfolders(self, folder: List[str]) -> List[str]:
        folder.sort()
        ans = []
        for f in folder:
            if not ans or f[: 1 + len(ans[-1])] != ans[-1] + '/': 	# need '/' to ensure a parent.
                ans.append(f)
        return ans
Analysis

credit to @alanwaked for his excellent comment:

I think the time complexity for method 2 is actually O(n * m * logn).
Because the sort is based on merge sort for Object and time complexity of merge sort is O(n * logn). That means n * logn times comparing happened.
For this question, it just makes the comparing time be O(m). Thus it won't increase the number of "layers" of merge sort to log(n * m).

Time: O(n * m * log(n)), space: O(1)(excluding space cost of sorting part), where n = folder.length, m = average size of the strings in folder.

Method 3: Trie

Credit to @Phil_IV-XIII for correction.

Use index to save each folder index in a trie node; when search the trie, if we find a folder (index >= 0) and the next char is /, then we get all parent folders on the current trie branch.

    class Trie {
        Trie[] sub = new Trie[27];
        int index = -1;
    }
    public List<String> removeSubfolders(String[] folder) {
        Trie root = new Trie();
        for (int i = 0; i < folder.length; ++i) {
            Trie t = root;
            for (char c : folder[i].toCharArray()) {
                int idx = c == '/' ? 26 : c - 'a'; // correspond '/' to index 26.
                if (t.sub[idx] == null)
                    t.sub[idx] = new Trie();
                t = t.sub[idx];
            }
            t.index = i;
        }
        return bfs(root, folder);
    }
    private List<String> bfs(Trie t, String[] folder) {
        List<String> ans = new ArrayList<>();
        Queue<Trie> q = new LinkedList<>();
        q.offer(t);
        while (!q.isEmpty()) { // BFS search.
            t = q.poll();
            if (t.index >= 0) { // found a parent folder, but there might be more.
                ans.add(folder[t.index]);
            }
            for (int i = 0; i < 27; ++i)
                if (t.sub[i] != null && !(i == 26 && t.index >= 0)) // not yet found all parent folders in current trie branch.
                    q.offer(t.sub[i]);
        }
        return ans;
    }
Analysis:

Time & space: O(n * m), where n = folder.length, m = average size of the strings in folder.

Compare the first two methods

Generally speaking, m > logn,

For method 2:
O(n * m * logn)

For method 1:
O(n * (logn + m ^ 2)) = O(n * m ^ 2) > O(n * m * logn) - time complexity of method 1.

Conclusion:

Method 2 is more space efficent and is, generally speaking, faster than method 1;
Method 1 is faster if folders' average size m < logn.