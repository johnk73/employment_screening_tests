Find values from each list that sum to match a number.  Return all matches.
If no matches are found, return the combination that sums to as close as possible without exceeding.
Return the IDs of each value in a list.
If all combinations are greater than the maximum, return an empty list

The two input lists are formatted: [[id1, value1], [id2, value2], ...]
Example:
max: 10, list1: [[2, 5], [1, 7], [3, 2]], list2: [[3, 5], [2, 2], [3, 8]]
answer: [[2, 3], [3, 3]]   (5+5, 2+8)
In the test I took these were described as two different travel distances and vehicle mileage limit (fuel)


Half the test for me was collection wrangling.  My solution under testing time pressure was incomplete but would have been O(n^2).  I got stuck trying to get this kind of thing to compile:
  public List<List<Integer>> blahblah() {
    ArrayList<ArrayList<Integer>> rtnvalue = new ArrayList<ArrayList<Integer>>();

This works better.  Stay abstract until you are actually allocating (next to "new").
   List<List<Integer>> rtnvalue = new ArrayList<List<Integer>>();
