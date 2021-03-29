package hashtable;

/*
A website domain like "discuss.leetcode.com" consists of various subdomains.

At the top level, we have "com", at the next level, we have "leetcode.com",

and at the lowest level, "discuss.leetcode.com".

When we visit a domain like "discuss.leetcode.com",

we will also visit the parent domains "leetcode.com" and "com" implicitly.

Now, call a "count-paired domain" to be a count (representing the number of visits this domains received),

followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".

We are given a list cpdomains of count-paired domains.

We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.

Example 1:
Input:
["9001 discuss.leetcode.com"]
Output:
["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
Explanation:
We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.

Example 2:
Input:
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output:
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]


Explanation:
We will visit

"google.mail.com" 900 times,

 "yahoo.com" 50 times,

 "intel.mail.com" once and

 "wiki.org" 5 times.

 For the subdomains, we will visit "mail.com" 900 + 1 = 901 times,

 "com" 900 + 50 + 1 = 951 times,

and "org" 5 times.

Notes:

The length of cpdomains will not exceed 100.
The length of each domain name will not exceed 100.
Each address will have either 1 or 2 "." characters.
The input count in any count-paired domain will not exceed 10000.
The answer output can be returned in any order.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount_811 {
    public List<String> subdomainVisitsMy(String[] cpdomains) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (String cpdomain : cpdomains) {
            int spaceIndex = cpdomain.indexOf(' ');
            int count = Integer.valueOf(cpdomain.substring(0, spaceIndex));

            String domain = cpdomain.substring(spaceIndex + 1);

            String[] frags = domain.split("\\.");

            String curr = "";

            for (int i = frags.length - 1; i >= 0; --i) {
                curr = frags[i] + (i < frags.length - 1 ? "." : "") + curr;
                hashMap.put(curr, hashMap.getOrDefault(curr, 0) + count);
            }
        }

        List<String> ans = new ArrayList<String>();

        for (String littleKey : hashMap.keySet())
            ans.add("" + hashMap.get(littleKey) + " " + littleKey);

        return ans;
    }

    public List<String> subdomainVisitsMyII(String[] cpdomains) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (String cpdomain : cpdomains) {
            int spaceIndex = cpdomain.indexOf(' ');
            int count = Integer.valueOf(cpdomain.substring(0, spaceIndex));

            String domain = cpdomain.substring(spaceIndex + 1);

            for (int i = 0; i < domain.length(); ++i) {
                if (domain.charAt(i) == '.') {
                    String curr = domain.substring(i + 1);
                    hashMap.put(curr, hashMap.getOrDefault(curr, 0) + count);
                }
            }

            hashMap.put(domain, hashMap.getOrDefault(domain, 0) + count);
        }

        List<String> ans = new ArrayList<String>();

        for (String littleKey : hashMap.keySet())
            ans.add("" + hashMap.get(littleKey) + " " + littleKey);

        return ans;
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        if (cpdomains == null || cpdomains.length <= 0) {
            return null;
        }

        Map<String, Integer> temp = new HashMap<String, Integer>();
        for (String domain : cpdomains) {
            String[] seperate = domain.split("\\s+");
            temp.put(seperate[1], temp.getOrDefault(seperate[1], 0) + Integer.parseInt(seperate[0]));
        }

        String cur = "";
        Map<String, Integer> result = new HashMap<String, Integer>();

        for (String key : temp.keySet()) {
            cur = "";
            String[] frags = key.split("\\.");
            for (int i = frags.length - 1; i >= 0; --i) {
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                result.put(cur, result.getOrDefault(cur, 0) + temp.get(key));
            }
        }

        List<String> ans = new ArrayList<>();
        for (String dom : result.keySet())
            ans.add("" + result.get(dom) + " " + dom);
        return ans;
    }

    public static void main(String[] args) {
        List<String> result = new SubdomainVisitCount_811().subdomainVisits(new String[] {"9001 discuss.leetcode.com"});
        for (String test : result)
            System.out.println(test);

        result = new SubdomainVisitCount_811().subdomainVisits(new String[] {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"});
        for (String test : result)
            System.out.println(test);
    }

    public List<String> subdomainVisitsIII(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (String cd : cpdomains) {
            int i = cd.indexOf(' ');
            int n = Integer.valueOf(cd.substring(0, i));
            String s = cd.substring(i + 1);
            for (i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '.') {
                    String d = s.substring(i + 1);
                    map.put(d, map.getOrDefault(d, 0) + n);
                }
            }
            map.put(s, map.getOrDefault(s, 0) + n);
        }

        List<String> res = new ArrayList<>();
        for (String d : map.keySet()) res.add(map.get(d) + " " + d);
        return res;
    }

    public List<String> subdomainVisitsII(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<String, Integer>();

        for (String domain : cpdomains) {
            String[] cpinfo = domain.split("\\s+");
            String[] frags = cpinfo[1].split("\\.");
            int count = Integer.valueOf(cpinfo[0]);
            String cur = "";
            for (int i = frags.length - 1; i >= 0; --i) {
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
            }
        }

        List<String> ans = new ArrayList<>();
        for (String key : counts.keySet())
            ans.add("" + counts.get(key) + " " + key);
        return ans;
    }
}