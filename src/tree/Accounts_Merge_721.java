package tree;
/*
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
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
    public class Node {
        Node parent;
        String name;
        List<String> accounts = new ArrayList<>();
        
        public Node(String name) {
            this.name = name;
            this.parent = this;
        }
    }
    
    public Node findParent(Node node) {
        while (node.parent != node.parent.parent)
            node.parent = node.parent.parent;
        return node.parent;
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
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
                }
                else {
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