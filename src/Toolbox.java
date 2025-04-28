import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Toolbox {

  /**
   * Finds the length of a singly linked list given the head.
   *
   * @param head the head node of the singly linked list
   * @return the number of nodes in the list
   * @throws IllegalArgumentException if the head is null
   */
  public static int length(SingleNode head) 
  {
    if (head == null) 
    {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    int count = 0;

    SingleNode curr = head;
    while (curr != null) 
    {
      count++;

      if (curr.next == null)
      {
        break;
      }

      curr = curr.next;
    }

    return count; 
  }


  /**
   * Finds the tail of a singly linked list given the head.
   *
   * @param head the head node of the singly linked list
   * @return the tail node of the list, or null if the list is empty
   * @throws IllegalArgumentException if the head is null
   */
  public static SingleNode findTail(SingleNode head) 
  {
    if (head == null) 
    {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    SingleNode curr = head;
    while (curr.next != null)
    {
      curr = curr.next;
    }

    return curr; 
  }


  /**
   * Finds the head of a doubly linked list given the tail.
   *
   * @param tail the tail node of the doubly linked list
   * @return the head node of the list, or null if the list is empty
   * @throws IllegalArgumentException if the tail is null
   */
  public static DoubleNode findHead(DoubleNode tail) 
  {
    if (tail == null) 
    {
      throw new IllegalArgumentException("Tail cannot be null.");
    }

    DoubleNode curr = tail;
    while (curr.prev != null)
    {
      curr = curr.prev;
    }

    return curr; 
  }


  /**
   * Counts the occurrences of values in a linked list.
   *
   * @param head the head node of the linked list
   * @return a map where the keys are the values in the list, and the values are the counts of occurrences
   * @throws IllegalArgumentException if the head is null
   */
  public static Map<Integer, Integer> countOccurrences(SingleNode head) 
  {
    if (head == null) 
    {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    Map<Integer, Integer> occurrenceMap = new HashMap<>();
    
    SingleNode curr = head;
    while (curr != null)
    {
      if (!occurrenceMap.containsKey(curr.data))
      {
        occurrenceMap.put(curr.data, 1);
      }
      else
      {
        occurrenceMap.put(curr.data, occurrenceMap.get(curr.data) + 1);
      }
      
      if (curr.next == null)
      {
        break;
      }

      curr = curr.next;

    }

    return occurrenceMap; 
  }


  /**
   * Removes a node from a doubly linked list.
   *
   * @param node the node to remove
   * @throws IllegalArgumentException if the node is null
   */
  public static void removeNode(DoubleNode node) 
  {
    if (node == null) 
    {
      throw new IllegalArgumentException("Node cannot be null.");
    }

    if (node.prev == null & node.next == null)
    {
      // No connections
    }
    else if (node.prev == null)
    {
      node = node.next;
      node.prev = null;
    }
    else if (node.next == null)
    {
      node = node.prev;
      node.next = null;
    }
    else
    {
      DoubleNode before = node;
      DoubleNode after = node;

      before = before.prev;
      after = after.next;

      before.next = before.next.next;
      after.prev = after.prev.prev;
    }

  }


  /**
   * Finds the nth element in a singly linked list.
   *
   * @param head the head node of the singly linked list
   * @param n the index of the element to find (0-based)
   * @return the nth node, or null if the index is out of bounds
   * @throws IllegalArgumentException if the head is null or n is negative
   */
  public static SingleNode findNthElement(SingleNode head, int n) 
  {
    if (head == null || n < 0) 
    {
      throw new IllegalArgumentException("Head cannot be null and n cannot be negative.");
    }

    SingleNode curr  = head;
    while (curr.next != null && n != 0) 
    {
      n--;
      curr = curr.next;
    }

    if (n == 0)
    {
      return curr;
    }
    else
    {
      return null;
    }

  }


  /**
   * Inserts a new node into a singly linked list given a pointer to a node in the middle of the list.
   *
   * @param node the node after which the new node is to be inserted
   * @param newNode the new node to insert
   * @throws IllegalArgumentException if either node or newNode is null
   */
  public static void insertNode(SingleNode node, SingleNode newNode) 
  {
    if (node == null || newNode == null) 
    {
      throw new IllegalArgumentException("Node and newNode cannot be null.");
    }

    if (node.next == null)
    {
      node.next = newNode;
    }
    else
    {
      SingleNode save = node;
      save = node.next;
      node.next = newNode;
      newNode.next = save;
    }

  }

  /**
   * Removes all nodes that are strictly larger than their next neighbor in the original list, except for the head.
   * The head is never removed.
   * 
   * The removals are done in-place.
   * 
   * Example:
   * Input: 5 -> 7 -> 6 -> 20 -> 4 -> 4
   * Output: 5 -> 6 -> 4 -> 4
   * 
   * Explanation: 7 is greater than 6 and 20 is greater than 4, so these nodes are removed.
   *
   * @param head the head of the list
   * @throws IllegalArgumentException if the head is null
   */
  public static void removeGiants(SingleNode head) 
  {
    if (head == null) 
    {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    SingleNode curr = head;
    SingleNode prevMarker = head;
    if (curr.next != null)
    {
      curr = curr.next;
    }

    while (curr.next != null) 
    {
      if (curr.data <= curr.next.data)
      {
        curr = curr.next;
        prevMarker = prevMarker.next;
      }
      else
      {
        if (curr.next.next == null)
        {
          curr.next = null;
        }
        else
        {
          prevMarker.next = curr.next;
          curr = curr.next;
        }
        
      }

    }
    
  }


    /**
     * Triples the value of every element in a queue in-place.
     * 
     * Only O(1) space should be used.
     * 
     * You can assume the queue will have first-in-first-out behavior.
     *
     * Example:
     * Input: [5, 3, 2, 7] 
     * Result: [15, 9, 6, 21]
     *
     * @param queue the queue to modify
     * @throws IllegalArgumentException if the queue is null
     */
    public static void tripleValues(Queue<Integer> queue) {
      if (queue == null) {
        throw new IllegalArgumentException("Queue cannot be null");
      }
      
    }


  /**
   * Rotates a queue to the left by the specified number of positions in-place.
   * 
   * The first k elements of the queue are moved to the end, preserving the order
   * of all elements.
   * 
   * Only O(1) space should be used.
   * 
   * You can assume the queue will have first-in-first-out behavior.
   *
   * Example:
   * Given a queue [1, 2, 3, 4, 5] and k = 2, the result will be [3, 4, 5, 1, 2].
   *
   * @param queue the queue to rotate
   * @param k the number of positions to rotate to the left
   * @throws IllegalArgumentException if the queue is null or k is negative
   */
  public static void rotateQueueLeft(Queue<Integer> queue, int k) {
    if (queue == null || k < 0) {
      throw new IllegalArgumentException("Queue cannot be null and k cannot be negative.");
    }
    
  }

  /**
   * Checks if a string has balanced parentheses using a stack.
   * 
   * A string is considered to have balanced parentheses if each opening parenthesis
   * '(' has a corresponding closing parenthesis ')', and the parentheses are correctly nested.
   *
   * Example:
   * - Input: "(()())" -> Returns true
   * - Input: "(()" -> Returns false
   * - Input: ")" -> Returns false
   *
   * @param input the string to check
   * @return true if the string has balanced parentheses, false otherwise
   * @throws IllegalArgumentException if the input string is null
   */
  public static boolean hasBalancedParentheses(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input string cannot be null.");
    }
    return false;
  }

  /**
   * Returns the name of the person who has the highest score associated with them in a map.
   * 
   * The keys hold the names of the players and the values hold the scores. 
   * 
   * For example: 
   * {
   *  "Lewis": 20,
   *  "Yuki": 23,
   *  "Kimi": 16
   * }
   * 
   * Yuki has the highest score.
   * 
   * In the event of a tie, the person whose name comes first lexicographically (alphabetically) should
   * be returned.
   * 
   * @param scores
   * @return the person with the highest score, or the first person lexicographically if there is a tie
   * @throws IllegalArgumentException if the scores are null or empty
   */
  public static String topScorer(Map<String, Integer> scores) {
    if (scores == null || scores.isEmpty()) {
      throw new IllegalArgumentException("Scares cannot be null or empty");
    }
    return null;
  }
}