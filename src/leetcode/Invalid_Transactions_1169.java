package array;
/*
A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.

You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name,

time (in minutes), amount, and city of the transaction.

Return a list of transactions that are possibly invalid. You may return the answer in any order.

Example 1:

Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.

Example 2:

Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]

Example 3:

Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]

Constraints:

transactions.length <= 1000
Each transactions[i] takes the form "{name},{time},{amount},{city}"
Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
Each {time} consist of digits, and represent an integer between 0 and 1000.
Each {amount} consist of digits, and represent an integer between 0 and 2000.

First pass, parse transactions to a <Name, <Time, Place>> map
Second pass, check (time - 60) to (time + 60) in map, if different place, add to result.

Reminder: during first pass, if same name and time, different places, just add an unexisted place "XXXX", cause all places are invalid
*/

public List<String> invalidTransactions(String[] transactions) {
    List<String> res = new ArrayList<>();
    String inval = "XXXXXXXXXXXX";
    Map<String, Map<Integer, String>> map = new HashMap<>();
    for (String s : transactions) {
        String[] array = s.split(",");
        String key = array[0];
        if (!map.containsKey(key)) map.put(key, new HashMap<Integer, String>());
        Map<Integer, String> city = map.get(key);
        int temp = Integer.parseInt(array[1]);
        if (city.containsKey(temp)) city.put(temp, inval);
        else city.put(temp, array[3]);

    }

    for (String s : transactions) {
        String[] array = s.split(",");
        if (Integer.parseInt(s.split(",")[2]) > 1000) res.add(s);
        else {
            String key = array[0];
            Map<Integer, String> city = map.get(key);
            int temp = Integer.parseInt(array[1]);
            boolean flag = true;
            for (int i = (temp - 60); i < (temp + 61) && flag; i++) {
                if (i != temp && city.containsKey(i)) {
                    if (!city.get(i).equals(array[3])) {
                        res.add(s);
                        flag = false;
                    }
                }
                if (i == temp && city.get(i).equals(inval)) {
                    res.add(s);
                    flag = false;
                }
            }
        }
    }
    return res;
}