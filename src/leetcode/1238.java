Find more explanation here about the used formula
Time O(2^N) Space O(2^N)

Java:

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i)
            res.add(start ^ i ^ i >> 1);
        return res;
    }
C++:

    vector<int> circularPermutation(int n, int start) {
        vector<int> res;
        for (int i = 0; i < 1 << n; ++i)
            res.push_back(start ^ i ^ i >> 1);
        return res;
    }
1-line Python:

    def circularPermutation(self, n, start):
        return [start ^ i ^ i >> 1 for i in range(1 << n)]