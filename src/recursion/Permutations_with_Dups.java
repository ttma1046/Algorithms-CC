package recursion;

class Permutations_with_Dups {
    public static void getPermutations(ArrayList<Integer> array, ArrayList<Integer> currentPermutation, ArrayList<ArrayList<Integer>> permutations) {
        if (array.size() == 0 && currentPermutation.size() > 0) {
            permutations.add(currentPermutation);
        } else {
            for (int i = 0; i < array.size(); i++) {
                ArrayList<Integer> newArray = new ArrayList<Integer>(array);
                newArray.remove(i);
                ArrayList<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);
                newPermutation.add(array.get(i));
                getPermutations(newArray, newPermutation, permutations);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums)     {
        List<List<Integer>> permutations = new ArrayList<List<Integer>>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int num: nums) {
            array.add(num);
        }
        getPermutations(array, new ArrayList<Integer>(), permutations);
        return permutations;
    }
    
    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations().permute(new int [] {1, 1, 3});

        for (List<Integer> item: result) {
            for(Integer num: item) {
                System.out.println(num);
            }
        }
    }
}