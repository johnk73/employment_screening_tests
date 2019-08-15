package com.johnk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FindMidValueNestedListsTest {

  List<Integer> inputMax = new ArrayList<Integer>();
  List<List<List<Integer>>> inputList1 = new ArrayList<List<List<Integer>>>();
  List<List<List<Integer>>> inputList2 = new ArrayList<List<List<Integer>>>();
  List<List<List<Integer>>> benchmark = new ArrayList<List<List<Integer>>>();
  FindMidValueNestedLists testClass = new FindMidValueNestedLists();
  @BeforeEach
  void setUp() {
    //   max: 10, list1: [[4, 5], [2, 5], [1, 7], [3, 2]], list2: [[3, 5], [2, 2], [3, 8]]
    //   answer: [[2, 3], [3, 3]]   (5+5, 2+8)
    inputMax.add(10);
    inputList1.add(List.of(List.of(4,5), List.of(2,5), List.of(1,7), List.of(3,2)));
    inputList2.add(List.of(List.of(3,5), List.of(2,2), List.of(3,8)));
    benchmark.add(List.of(List.of(2,3), List.of(3,3), List.of(4,3)));

    //   max: 10, list1: [[4, 1], [2, 4], [1, 7], [3, 2]], list2: [[3, 5], [2, 2], [3, 11]]
    //   answer: [[2, 3]]   (4+5)
    inputMax.add(10);
    inputList1.add(List.of(List.of(4,1), List.of(2,4), List.of(1,7), List.of(3,2)));
    inputList2.add(List.of(List.of(3,5), List.of(2,2), List.of(3,11)));
    benchmark.add(List.of(List.of(2,3)));
  }

  // Lists may be ordered differently but it's still the right answer
  // e.g. [[2,2], [1,5]] == [[1,5], [2,2]]
  private boolean listsEqual(List<List<Integer>> list1, List<List<Integer>> list2) {
    Comparator<List<Integer>> IDValueComparator = new Comparator<List<Integer>>() {
      @Override
      public int compare(List<Integer> list1, List<Integer> list2) {
        int IDCompare = Integer.compare(list1.get(0), list2.get(0));
        if (IDCompare == 0) {
          int valueCompare = Integer.compare(list1.get(1), list2.get(1));
          return valueCompare;
        }
        return IDCompare;
      }
    };

    List<List<Integer>> list1Mutable = new ArrayList<List<Integer>>((List)list1);
    List<List<Integer>> list2Mutable = new ArrayList<List<Integer>>((List)list2);
    list1Mutable.sort(IDValueComparator);
    list2Mutable.sort(IDValueComparator);
    return list1Mutable.equals(list2Mutable);
  }

  @Test
  void TestFindMaxValueWithLimit() {
    for (int idx=0; idx <inputMax.size(); ++idx) {
      Integer max = inputMax.get(idx);
      List<List<Integer>> list1 = inputList1.get(idx);
      List<List<Integer>> list2 = inputList2.get(idx);
      List<List<Integer>> output = testClass.findMaxValueWithLimit(max, list1, list2);
      List<List<Integer>> benchmarkValue = benchmark.get(idx);
      assertTrue(listsEqual(benchmarkValue, output), "list1:" + list1.toString() + " list2:" + list2.toString()
          + " expected:" + benchmarkValue + " actual:" + output);
    }
  }
}