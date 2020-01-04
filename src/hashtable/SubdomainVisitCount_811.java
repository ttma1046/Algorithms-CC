package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount_811 {
    public List<String> subdomainVisits(String[] cpdomains) {
        if (cpdomains == null || cpdomains.length <= 0) {
            return null;
        }

        Map<String, Integer> temp = new HashMap<String, Integer>();
        for (String domain: cpdomains) {
            String[] seperate = domain.split("\\s+");
            temp.put(seperate[1], temp.getOrDefault(seperate[1], 0) + Integer.parseInt(seperate[0]));
        }

        String cur = "";
        Map<String, Integer> result = new HashMap<String, Integer>();

        for (String key: temp.keySet()) {
            cur = "";
            String[] frags = key.split("\\.");
            for (int i = frags.length - 1; i >= 0; --i) {
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                result.put(cur, result.getOrDefault(cur, 0) + temp.get(key));
            }
        }

        List<String> ans = new ArrayList();
        for (String dom: result.keySet())
            ans.add("" + result.get(dom) + " " + dom);
        return ans;
    }

    public static void main(String[] args) {
        List<String> result = new SubdomainVisitCount_811().subdomainVisits(new String[] {"9001 discuss.leetcode.com"});
        for (String test: result)
            System.out.println(test);

        result = new SubdomainVisitCount_811().subdomainVisits(new String[] {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"});
        for (String test: result)
            System.out.println(test);
    }

    public List<String> subdomainVisitsIII(String[] cpdomains) {
        Map<String, Integer> map = new HashMap();
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

        List<String> res = new ArrayList();
        for (String d : map.keySet()) res.add(map.get(d) + " " + d);
        return res;
    }

    public List<String> subdomainVisitsII(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<String, Integer>();

        for (String domain: cpdomains) {
            String[] cpinfo = domain.split("\\s+");
            String[] frags = cpinfo[1].split("\\.");
            int count = Integer.valueOf(cpinfo[0]);
            String cur = "";
            for (int i = frags.length - 1; i >= 0; --i) {
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
            }
        }

        List<String> ans = new ArrayList();
        for (String dom: counts.keySet())
            ans.add("" + counts.get(dom) + " " + dom);
        return ans;
    }
}