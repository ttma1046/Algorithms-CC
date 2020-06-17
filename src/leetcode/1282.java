Keep track of people in each group based on the group size. Greedily assign people to the corresponding group.

As soon as the group is filled, add it to the result and reset the list of its members.

We can use a hash map or array (the maximum group size is 501) to track each group.

C++

vector<vector<int>> groupThePeople(vector<int>& gz) {
  vector<vector<int>> res, groups(gz.size() + 1);
  for (auto i = 0; i < gz.size(); ++i) {
    groups[gz[i]].push_back(i);
    if (groups[gz[i]].size() == gz[i]) {
      res.push_back({});
      swap(res.back(), groups[gz[i]]);
    }
  }
  return res;
}
Java

public List<List<Integer>> groupThePeople(int[] gz) {
  List<List<Integer>> res = new ArrayList();
  Map<Integer, List<Integer>> groups = new HashMap<>();
  for (int i = 0; i < gz.length; ++i) {
    List<Integer> list = groups.computeIfAbsent(gz[i], k -> new ArrayList());
    list.add(i);
    if (list.size() == gz[i]) {
      res.add(list);
      groups.put(gz[i], new ArrayList());
    }
  }
  return res;
}