package union_find;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/*
Given a list accounts, each element accounts[i] is a list of strings,

where the first element accounts[i][0] is a name,
and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [
 ["John", "johnsmith@mail.com", "john00@mail.com"],
 ["John", "johnnybravo@mail.com"],
 ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 ["Mary", "mary@mail.com"]
 ]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
*/

class Accounts_Merge_721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name == "") {
                    name = email;
                    continue;
                }

                graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));

                /*
                    key:johnnybravo@mail.com value:[johnnybravo@mail.com]
                    key:john00@mail.com value:[johnsmith@mail.com]
                    key:johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com]
                    key:mary@mail.com value:[mary@mail.com]
                    key:john_newyork@mail.com value:[johnsmith@mail.com]
                */

                graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);

                /*

                    johnsmith@mail.com value:[johnsmith@mail.com] =>
                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com]

                    key:john00@mail.com value:[johnsmith@mail.com] =>
                    key:john00@mail.com value:[johnsmith@mail.com]

                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com] =>
                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com, john00@mail.com]


                    johnnybravo@mail.com value:[] =>
                    johnnybravo@mail.com value:[johnnybravo@mail.com]


                    johnnybravo@mail.com value:[johnnybravo@mail.com] =>
                    johnnybravo@mail.com value:[johnnybravo@mail.com, johnnybravo@mail.com]


                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com, john00@mail.com] =>
                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com, john00@mail.com, johnsmith@mail.com]


                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com, john00@mail.com, johnsmith@mail.com] =>
                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com, john00@mail.com, johnsmith@mail.com, johnsmith@mail.com]


                    key:john_newyork@mail.com value:[]
                    key:john_newyork@mail.com value:[johnsmith@mail.com]

                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com, john00@mail.com, johnsmith@mail.com, johnsmith@mail.com] =>
                    johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com, john00@mail.com, johnsmith@mail.com, johnsmith@mail.com, john_newyork@mail.com]


                    key:mary@mail.com value:[]
                    key:mary@mail.com  value:[mary@mail.com]
                    key:mary@mail.com value:[mary@mail.com]
                    key:mary@mail.com  value:[mary@mail.com, mary@mail.com]
                */

                emailToName.put(email, name);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : graph.entrySet()) {
            System.out.print("key:" + entry.getKey());
            System.out.print(" value:" + entry.getValue());
            System.out.println();
        }

        System.out.println();

        for (Map.Entry<String, String> entry : emailToName.entrySet()) {
            System.out.print("key:" + entry.getKey());
            System.out.print(" value:" + entry.getValue());
            System.out.println();
        }

        System.out.println();

        /*
        key:johnnybravo@mail.com value:[johnnybravo@mail.com]
        key:john00@mail.com value:[johnsmith@mail.com]
        key:johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com]
        key:mary@mail.com value:[mary@mail.com]
        key:john_newyork@mail.com value:[johnsmith@mail.com]

        key:johnnybravo@mail.com value:[johnnybravo@mail.com, johnnybravo@mail.com]
        key:john00@mail.com value:[johnsmith@mail.com]
        key:johnsmith@mail.com value:[johnsmith@mail.com, johnsmith@mail.com, john00@mail.com, johnsmith@mail.com, johnsmith@mail.com, john_newyork@mail.com]
        key:mary@mail.com value:[mary@mail.com, mary@mail.com]
        key:john_newyork@mail.com value:[johnsmith@mail.com]

        key:johnnybravo@mail.com value:John
        key:johnsmith@mail.com value:John
        key:john00@mail.com value:John
        key:john_newyork@mail.com value:John
        key:mary@mail.com value:Mary
        */

        List<List<String>> ans = new ArrayList<>();

        Set<String> seen = new HashSet<>();
        for (String email : graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);

                Stack<String> stack = new Stack<>();
                stack.push(email);

                List<String> component = new ArrayList<>();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei : graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<List<String>>();

        List<String> account = new ArrayList<String>();

        account.add("John");
        account.add("johnsmith@mail.com");
        account.add("john00@mail.com");
        account.add("johnPPP@mail.com");

        accounts.add(account);

        account = new ArrayList<String>();

        account.add("John");
        account.add("johnnybravo@mail.com");

        accounts.add(account);

        account = new ArrayList<String>();
        account.add("John");
        account.add("johnsmith@mail.com");
        account.add("john_newyork@mail.com");

        accounts.add(account);

        account = new ArrayList<String>();

        account.add("Mary");
        account.add("mary@mail.com");

        accounts.add(account);

        List<List<String>> result = new Accounts_Merge_721().accountsMergeMy(accounts);

        for (List<String> emails : result) {
            for (String email : emails) {
                System.out.print(email + ",");

            }

            System.out.println();
        }
    }

    /*
    Complexity Analysis

    Time Complexity: O(A \log A)O(AlogA), where A = \sum a_iA=∑ai, and a_iai is the length of accounts[i]. If we used union-by-rank, this complexity improves to O(A \alpha(A)) \approx O(A)O(Aα(A))≈O(A), where \alphaα is the Inverse-Ackermann function.

    Space Complexity: O(A)O(A), the space used by our DisjointSetUnion structure.
    */

    public List<List<String>> accountsMergeMy(List<List<String>> accounts) {
        DisjointSetUnion dsu = new DisjointSetUnion();
        Map<String, String> emailToName = new HashMap<String, String>();
        Map<String, Integer> emailToId = new HashMap<String, Integer>();

        int i = 0;
        for (List<String> account : accounts) {
            String name = "";
            for (String str : account) {
                if (name == "") {
                    name = str;
                    continue;
                }

                if (!emailToName.containsKey(str)) emailToName.put(str, name);
                if (!emailToId.containsKey(str)) emailToId.put(str, i++);

                dsu.union(emailToId.get(account.get(1)), emailToId.get(str));
            }
        }

        Map<Integer, List<String>> ans = new HashMap<>();
        for (String email : emailToId.keySet()) {
            int index = dsu.find(emailToId.get(email));
            ans.computeIfAbsent(index, x-> new ArrayList<String>()).add(email);
        }

        for (List<String> component : ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }

        return new ArrayList<List<String>>(ans.values());
    }

    public List<List<String>> accountsMergeII(List<List<String>> accounts) {
        DisjointSetUnion dsu = new DisjointSetUnion();
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name == "") {
                    name = email;
                    continue;
                }

                emailToName.put(email, name);

                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }

                /*
                key:johnnybravo@mail.com value:2
                key:johnsmith@mail.com value:0
                key:john00@mail.com value:1
                key:john_newyork@mail.com value:3
                key:mary@mail.com value:4

                [
                   ["John", "johnsmith@mail.com", "john00@mail.com"],
                   ["John", "johnnybravo@mail.com"],
                   ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                   ["Mary", "mary@mail.com"]
                ]
                */
                dsu.union(emailToID.get(account.get(1)), emailToID.get(email));

                System.out.println(email);
                for (int i = 0; i <= 5; ++i) {
                    System.out.println(i + ":" + dsu.parent[i]);
                }
            }
        }

        for (Map.Entry<String, String> entry : emailToName.entrySet()) {
            System.out.print("key:" + entry.getKey());
            System.out.print(" value:" + entry.getValue());
            System.out.println();
        }


        for (Map.Entry<String, Integer> entry : emailToID.entrySet()) {
            System.out.print("key:" + entry.getKey());
            System.out.print(" value:" + entry.getValue());
            System.out.println();
        }

        Map<Integer, List<String>> ans = new HashMap<>();
        for (String email : emailToName.keySet()) {
            int index = dsu.find(emailToID.get(email));
            ans.computeIfAbsent(index, x-> new ArrayList<>()).add(email);
        }

        for (List<String> component : ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }

        return new ArrayList<List<String>>(ans.values());
    }

    /*
    Time Complexity: O(\sum a_i \log a_i)O(∑ailogai), where a_iai
    ​
    is the length of accounts[i]. Without the log factor, this is the complexity to build the graph and search for each component. The log factor is for sorting each component at the end.

    Space Complexity: O(\sum a_i)O(∑ai), the space used by our graph and our search.
    */
    public Node findParent(Node node) {
        while (node.parent != node.parent.parent)
            node.parent = node.parent.parent;
        return node.parent;
    }

    public List<List<String>> accountsMergeIII(List<List<String>> accounts) {
        Map<String, Node> emailToNodeMap = new HashMap<>();
        List<Node> allNodeList = new ArrayList<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            Node node = new Node(name);
            allNodeList.add(node);

            for (int idx = 1; idx < account.size(); idx++) {
                String email = account.get(idx);
                if (!emailToNodeMap.containsKey(email)) {
                    emailToNodeMap.put(email, node);
                    node.accounts.add(email);
                } else {
                    Node currentNode = findParent(node);
                    Node existsParent = findParent(emailToNodeMap.get(email));
                    currentNode.parent = existsParent.parent;
                }
            }
        }

        for (Node node : allNodeList) {
            if (node.parent != node) {
                Node parent = findParent(node);
                parent.accounts.addAll(node.accounts);
            }
        }

        List<List<String>> resultList = new ArrayList<>();
        for (Node node : allNodeList) {
            if (node.parent == node) {
                List<String> tempList = new ArrayList<>();
                tempList.add(node.name);
                Collections.sort(node.accounts);
                tempList.addAll(node.accounts);
                resultList.add(tempList);
            }
        }

        return resultList;
    }
}

class DisjointSetUnion {
    int[] parent;
    public DisjointSetUnion() {
        parent = new int[10001];
        for (int i = 0; i <= 10000; ++i)
            parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {
        //        int t = find(x);
        //        int s = find(y);
        //        System.out.println("find(x):" + t);
        //        System.out.println("find(y):" + s);

        //        parent[t] = s;

        parent[find(x)] = find(y);
    }
}

class Node {
    Node parent;
    String name;
    List<String> accounts = new ArrayList<>();

    public Node(String name) {
        this.name = name;
        this.parent = this;
    }
}