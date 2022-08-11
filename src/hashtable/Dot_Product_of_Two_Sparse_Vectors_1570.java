package hashtable;
/*
Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums

dotProduct(vec) Compute the dot product between the instance of SparseVector and vec

A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8

Example 2:
Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0

Example 3:
Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6

Constraints:

n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
*/

class Dot_Product_of_Two_Sparse_Vectors_1570 {
    public static void main(String[] args) {
        int[] nums1 = new int[] {1, 0, 0, 2, 3};
        int[] nums2 = new int[] {0, 3, 0, 4, 0};
        SparseVector v1 = new SparseVector(nums1);
        SparseVector v2 = new SparseVector(nums2);
        int ans = v1.dotProduct(v2);
        System.out.println(ans);

        nums1 = new int[] {0, 1, 0, 0, 0};
        nums2 = new int[] {0, 0, 0, 0, 2};
        v1 = new SparseVector(nums1);
        v2 = new SparseVector(nums2);
        ans = v1.dotProduct(v2);
        System.out.println(ans);


        nums1 = new int[] {0, 1, 0, 0, 2, 0, 0};
        nums2 = new int[] {1, 0, 0, 0, 3, 0, 4};
        v1 = new SparseVector(nums1);
        v2 = new SparseVector(nums2);
        ans = v1.dotProduct(v2);
        System.out.println(ans);

    }
}

class SparseVectorI {
    public int[] origin;

    SparseVector(int[] nums) {
        origin = nums;
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;

        for (int i = 0; i < origin.length; i++)
            result += vec.origin[i] * origin[i];

        return result;
    }
}

class SparseVectorII {

    // Map the index to value for all non-zero values in the vector
    Map<Integer, Integer> mapping;

    SparseVector(int[] nums) {
        mapping = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (nums[i] != 0)
                mapping.put(i, nums[i]);
    }

    /*
    	0:1
    	3:2
    	4:3
    */

    /*
    	1:3
    	3:4
    */


    public int dotProduct(SparseVector vec) {
        int result = 0;

        // iterate through each non-zero element in this sparse vector
        // update the dot product if the corresponding index has a non-zero value in the other vector
        for (Integer i : this.mapping.keySet())
            if (vec.mapping.containsKey(i))
                result += this.mapping.get(i) * vec.mapping.get(i);

        return result;
    }
}


class SparseVectorIII {
    public int[] origin;

    SparseVector(int[] nums) {
        origin = nums;
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;

        for (int i = 0; i < origin.length; i++) {
            if (vec.origin[i] != 0 && origin[i] != 0)
                result += vec.origin[i] * origin[i];
        }

        return result;
    }
}

class SparseVectorIV {
    Map<Integer, Integer> map = new HashMap<>();

    SparseVector(int[] nums) {
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != 0)
                this.map.put(i, nums[i]);
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        if (this.map.size() == 0 || vec.map.size() == 0)
            return 0;

        if (this.map.size() > vec.map.size())
            vec.dotProduct(this);

        int productSum = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int index = entry.getKey();
            if (vec.map.containsKey(index)) {
                int vecValue = vec.map.get(index);
                productSum += entry.getValue() * vecValue;
            }
        }

        return productSum;
    }
}

class SparseVector {
    List<int[]> pairs;

    SparseVector(int[] nums) {
        pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != 0)
                pairs.add(new int[] {i, nums[i]});
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0, p = 0, q = 0;
        while (p < pairs.size() && q < vec.pairs.size()) {
            if (pairs.get(p)[0] == vec.pairs.get(q)[0]) {
                result += pairs.get(p)[1] * vec.pairs.get(q)[1];
                p++;
                q++;
            } else if (pairs.get(p)[0] > vec.pairs.get(q)[0])
                q++;
            else
                p++;

        }
        return result;
    }
}