package com.johnk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMidValueNestedLists {

  // Sort but preserve id
  private class listTuple implements Comparable<listTuple> {
    Integer id;
    Integer value;

    // constructor from List
    listTuple(List<Integer> tuple) {
      id = tuple.get(0);
      value = tuple.get(1);
    }
    // constructor just for binarySearch key
    listTuple(Integer value) {
      this.value = value;
      id = null;
    }
    // @return  a negative integer, zero, or a positive integer as this object
    //          is less than, equal to, or greater than the specified object.
    @Override
    public int compareTo(listTuple other) {
      // Bad arguments
      if (other == null) {
        return 1;
      }
      if (other.value == null) {
        return 1;
      }
      //System.out.println(id + " vs " + other.id);
      return value.compareTo(other.value);
    }
  }

  // Find values from each list that sum to match a number.  Return all matches.
  // If no matches are found, return the combination that sums to as close as possible without exceeding.
  // If all combinations are greater than the maximum, return an empty list
  // Return the IDs of each value in a list.
  //
  // The two input lists are formatted: [[id1, value1], [id2, value2], ...]
  // Example:
  //   max: 10, list1: [[2, 5], [1, 7], [3, 2]], list2: [[3, 5], [2, 2], [3, 8]]
  //   answer: [[2, 3], [3, 3]]   (5+5, 2+8)
  // In the test I took these were described as two different travel distances and vehicle mileage limit (fuel)
  // Half the test for me was collection wrangling
  public List<List<Integer>> findMaxValueWithLimit(Integer sumValueToFind, List<List<Integer>>list1, List<List<Integer>>list2) {
    List<List<Integer>> solutionIDs = new ArrayList<List<Integer>>();

    // Bad input
    if (sumValueToFind <= 0 || list1 == null || list2 == null) {
      return solutionIDs;
    }

    // Convert lists to nested class for sorting (so ID's can be preserved)
    // At the same time, strip out elements that are greater than the maximum (or negative = invalid)
    List<listTuple> list1Tuples = new ArrayList<listTuple>(list1.size());
    list1.forEach((listItem) -> {
      if (listItem.size() > 1) {
        Integer listItem1Value = listItem.get(1);
        if (listItem1Value.compareTo(sumValueToFind) <= 0 && listItem1Value.compareTo(0) >= 0) {
          list1Tuples.add(new listTuple(listItem));
        }}});
    List<listTuple> list2Tuples = new ArrayList<listTuple>(list2.size());
    list2.forEach((listItem) -> {
      if (listItem.size() > 1) {
        Integer listItem2Value = listItem.get(1);
        if (listItem2Value.compareTo(sumValueToFind) <= 0 && listItem2Value.compareTo(0) >= 0) {
          list2Tuples.add(new listTuple(listItem));
        }
      }
    });
    if (list1Tuples.size() == 0 || list2Tuples.size() == 0) {
      return solutionIDs;
    }
    Collections.sort(list1Tuples);
    Collections.sort(list2Tuples);

    // Binary-search through list1/list2 sums, retaining IDs of largest combination (beneath maximum)
    // Unless a combination is found matching maximum -- then only look for more maximum combinations
    boolean foundMaximumValue = false;
    Integer largestValueFound = 0;
    List<List<listTuple>> solutions = new ArrayList<List<listTuple>>();
    for (listTuple listItem : list1Tuples) {
      // Search for value that will get us within maximum, when summed
      int list2DesiredValue = sumValueToFind - listItem.value;
      int list2SearchIndex = Collections.binarySearch(list2Tuples, new listTuple(list2DesiredValue));
      // Sum is maximum value -- switch modes
      if (list2SearchIndex > 0) {
        if (!foundMaximumValue) {
          solutions.clear();
        }
        foundMaximumValue = true;

        // Add solution and look for subsequent values that are also solutions
        for (int idx=list2SearchIndex; idx<list2Tuples.size() && list2Tuples.get(idx).value == list2DesiredValue; ++idx) {
          solutions.add(List.of(listItem, list2Tuples.get(idx)));
          listTuple list2Item = list2Tuples.get(idx);
          //System.out.println("exact match: " + listItem.id + "(" + listItem.value + ") " + list2Item.id + "(" + list2Item.value + ")");
        }
      }
      // negated index is "-(insertion_point) - 1", i.e. making it one closer to zero is the index where it
      // would belong.  This will be a larger value (or past the end) than out desired/search value.  For
      // instance: [2, 3, 5] binarySearch(4) = (-3) 2, binarySearch(6) = (-4) 3, binarySearch(1) = (-1) 0
      // So fetch the value one below the insertion point
      else {
        int list2Index = -list2SearchIndex - 2;
        if (list2Index >= 0) {
          listTuple list2Item = list2Tuples.get(list2Index);
          if (listItem.value + list2Item.value > largestValueFound && !foundMaximumValue) {
            largestValueFound = listItem.value + list2Item.value;
            solutions.clear();
            solutions.add(List.of(listItem, list2Item));
            //System.out.println(listItem.id + "(" + listItem.value + ") " + list2Item.id + "(" + list2Item.value + ")");
          }
        }
      }
    }

    // Return only IDs
    //List<List<Integer>> solutionIDs = new ArrayList<List<Integer>>();
    for (List<listTuple> solution : solutions) {
      solutionIDs.add(List.of(solution.get(0).id, solution.get(1).id));
    }
    return solutionIDs;
  }
}
