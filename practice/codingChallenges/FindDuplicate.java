class FindDuplicate {
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int repeated = 0;
        for (int num : nums) {
            set.add(num);
            if (!set.add(num)) {
                repeated = num;
            }
        } return repeated;
    }
}