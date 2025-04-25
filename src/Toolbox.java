import java.util.*;

public class Toolbox {

  public static int length(SingleNode head) {
    if (head == null) {
      throw new IllegalArgumentException("Error: Head cannot be null.");
    }

    int count = 0;
    SingleNode current = head;
    while (current != null) {
      count++;
      current = current.next;
    }

    return count;
  }

  public static SingleNode findTail(SingleNode head) {
    if (head == null) {
      throw new IllegalArgumentException("Error: Head cannot be null.");
    }

    SingleNode current = head;
    while (current.next != null) {
      current = current.next;
    }

    return current;
  }

  public static DoubleNode findHead(DoubleNode tail) {
    if (tail == null) {
      throw new IllegalArgumentException("Error: Tail cannot be null.");
    }

    DoubleNode current = tail;
    while (current.prev != null) {
      current = current.prev;
    }

    return current;
  }

  public static Map<Integer, Integer> countOccurrences(SingleNode head) {
    if (head == null) {
      throw new IllegalArgumentException("Error: Head cannot be null.");
    }

    Map<Integer, Integer> map = new HashMap<>();
    SingleNode current = head;
    while (current != null) {
      map.put(current.data, map.getOrDefault(current.data, 0) + 1);
      current = current.next;
    }

    return map;
  }

  public static void removeNode(DoubleNode node) {
    if (node == null) {
      throw new IllegalArgumentException("Error: Node cannot be null.");
    }

    if (node.prev != null) {
      node.prev.next = node.next;
    }
    if (node.next != null) {
      node.next.prev = node.prev;
    }

    node.prev = null;
    node.next = null;
  }

  public static SingleNode findNthElement(SingleNode head, int n) {
    if (head == null || n < 0) {
      throw new IllegalArgumentException("Error: Head cannot be null and n cannot be negative.");
    }

    SingleNode current = head;
    int index = 0;
    while (current != null && index < n) {
      current = current.next;
      index++;
    }

    return current;
  }

  public static void insertNode(SingleNode node, SingleNode newNode) {
    if (node == null || newNode == null) {
      throw new IllegalArgumentException("Error: Node and newNode cannot be null.");
    }

    newNode.next = node.next;
    node.next = newNode;
  }

  public static void removeGiants(SingleNode head) {
    if (head == null) {
      throw new IllegalArgumentException("Error: Head cannot be null.");
    }

    SingleNode current = head;
    while (current != null && current.next != null && current.next.next != null) {
      if (current.next.data > current.next.next.data) {
        current.next = current.next.next;
      } else {
        current = current.next;
      }
    }
  }

  public static void tripleValues(Queue<Integer> queue) {
    if (queue == null) {
      throw new IllegalArgumentException("Error: Queue cannot be null");
    }

    int size = queue.size();
    for (int i = 0; i < size; i++) {
      queue.add(queue.remove() * 3);
    }
  }

  public static void rotateQueueLeft(Queue<Integer> queue, int k) {
    if (queue == null || k < 0) {
      throw new IllegalArgumentException("Error: Queue cannot be null and k cannot be negative.");
    }

    int size = queue.size();
    k = k % size; // optimize if k > size

    for (int i = 0; i < k; i++) {
      queue.add(queue.remove());
    }
  }

  public static boolean hasBalancedParentheses(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Error: Input string cannot be null.");
    }

    int balance = 0;
    for (char c : input.toCharArray()) {
      if (c == '(') {
        balance++;
      } else if (c == ')') {
        balance--;
        if (balance < 0) return false;
      }
    }

    return balance == 0;
  }

  public static String topScorer(Map<String, Integer> scores) {
    if (scores == null || scores.isEmpty()) {
      throw new IllegalArgumentException("Error: Sxores cannot be null or empty");
    }

    String topName = null;
    int maxScore = Integer.MIN_VALUE;

    for (Map.Entry<String, Integer> entry : scores.entrySet()) {
      String name = entry.getKey();
      int score = entry.getValue();

      if (score > maxScore || (score == maxScore && name.compareTo(topName) < 0)) {
        topName = name;
        maxScore = score;
      }
    }

    return topName;
  }
}
