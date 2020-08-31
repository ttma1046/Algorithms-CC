1. 
1st question was you need to remove a subarray from an array such that remaining array has equal number of values greater than and less than a value K. Return the total number of digits in the array. Didn't have enough time to go to the another one for me.

i think this problem is similar to https://leetcode.com/problems/subarray-sum-equals-k/
didnt test the code below, but i think main idea is ok

```java
    int n = arr.size();
    vector<int> v(n);
    int bal = 0, fin = n + 1;
    for (int i = n - 1; i >= 0; --i) {
        bal += arr[i] > k;
        bal -= arr[i] < k;
        v[i] = bal;
    }
    bal = 0;
    unordered_map<int, int> mp;
    mp[0] = -1;
    for (int i = 0; i < n; ++i) {
        bal += arr[i] > k;
        bal -= arr[i] < k;
        mp[bal] = i;
        auto t = (i < n - 1) ? -v[i + 1] : 0;
        if (mp.find(t) != mp.end()) {
            fin = min(fin, i - mp[t]);
        }
    }
    if (fin > n) {
        return -1;
    }
    return n - fin;
```
```python
    def removeAmountForEqualSizePartitions(nums, k):
    n = len(nums)

    amountLess, amountGreater = 0,0
    for num in nums:
        if num < k: amountLess += 1
        elif num > k: amountGreater += 1

    if amountLess == 0 or amountLess == n or amountGreater == 0 or amountGreater == n: 
        return n

    removeAmount = abs(amountLess - amountGreater)

    print("Remove:", removeAmount)
    return n-removeAmount
```

```java
/*
sort and binary search?
sort then find approximate index of k (value closest to k) with binary search then use it to keep removing elements on either side till both are equal. ans will always be even. TC = nlogn
or you don't need to use binary search to find the approximate value, just a for loop with track of min diff
*/
//corner cases are if value is greater than max elem or less than min elem, it is easy to track them
//this is useless don't bother with it
sort(arr.begin(),arr.end());
if(k>arr[arr.size()-1]||k<arr[0]) return 0;
int min_diff = INT_MAX;
int temp =0;
for (int i =0;i<arr.size();i++){
     if(abs(arr[i]-k)<min_diff && abs(arr[i]-k)>0){
     min_diff = abs(arr[i]-k);
     temp=i; 
     }
}
//once you found temp, it is quite easy
if(temp>arr.size()/2) return 2*(arr.size()-temp)
else return 2*temp;
```

I think it can be done without sorting, but I'd rather type tried and tested one for now
Edit, without sort and extra space in O(n), O(1)
```java
//use max_elem & min elem for corner cases

int greater_count=0,smaller_count=0;
for (int i=0;i<arr.size();i++){
       if(arr[i]>k)
          greater_count++;
      else if(arr[i]<k) {
          smaller_count++;  
     }
    else continue; 
}
return 2*min(greater_count,smaller_count);
```

2.
my first question was something related to trees, find closest ancestor with co-prime node. second was to process 5 type of queries (rotation/update etc.) where length(array)<=10^5 and length(queries)<=10^5. I was able to solve second one easily but absolutely didn't understand what first question was asking me to do.

3.
From 2 questions one was this:
https://hackcortona.hackerearth.com/pt-br/practice/algorithms/dynamic-programming/bit-masking/practice-problems/algorithm/trophy-of-xorasia-0a2d466a/description/

I didn't find the other question anywhere online.

Here's mine: https://leetcode.com/discuss/interview-question/794988/Google-SWE-Internship-2020-India-Online-Challenge