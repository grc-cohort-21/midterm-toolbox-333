import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

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

    int count = 0; // Set up counter

    
    SingleNode curr = head;
    while (curr != null)  // Go through the list
    {
      count++; // Iterate the count for each node

      if (curr.next == null) // When at the end
      {
        break; // Break out of the loop
      }

      curr = curr.next; // Go to the next node
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
    while (curr.next != null) // Go through the list
    {
      curr = curr.next;
    }

    return curr; // Return tail
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
    while (curr.prev != null) // Go backwards through the list
    {
      curr = curr.prev;
    }

    return curr; // Return the head
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

    Map<Integer, Integer> occurrenceMap = new HashMap<>(); // Set up map for occurrences
    
    SingleNode curr = head;
    while (curr != null) // Go through the list
    {
      if (!occurrenceMap.containsKey(curr.data)) // If the node isn't in the map yet
      {
        occurrenceMap.put(curr.data, 1); // Add to the map at count 1
      }
      else // Otherwise
      {
        occurrenceMap.put(curr.data, occurrenceMap.get(curr.data) + 1); // Iterate the existing data point
      }
      
      if (curr.next == null) // If at the end of the list
      {
        break; // Break out of the loop
      }

      curr = curr.next;

    }

    return occurrenceMap; // Return occurrences
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

    if (node.prev == null & node.next == null) // If a single node
    {
      // Do nothing, due to no connections
    }
    else if (node.prev == null) // If removing the first node
    {
      node = node.next;
      node.prev = null;
    }
    else if (node.next == null) // If removing the last node
    {
      node = node.prev;
      node.next = null;
    }
    else // All other node handling
    {
      // Set before and after node markers
      DoubleNode before = node;
      DoubleNode after = node;

      // Set the nodes to before and after the current node
      before = before.prev;
      after = after.next;

      // Connect the two points together
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
    while (curr.next != null && n != 0) // Go through the list n times
    {
      n--; // Iterate the count down
      curr = curr.next; // Go to the next node
    }

    if (n == 0) // If n fully iterated
    {
      return curr; // Return the node at that point
    }
    else // if the list ended before the count could finish
    {
      return null; // Return null, because the point is out of bounds
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

    if (node.next == null) // If at the end of the list
    {
      node.next = newNode; // Add the node
    }
    else // If anywhere else
    {
      SingleNode save = node; // Set temp
      save = node.next;
      node.next = newNode; // Add the node
      newNode.next = save; // Reattach the rest of the list
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

    // Set markers 
    SingleNode curr = head; 
    SingleNode prevMarker = head;
    if (curr.next != null) // If not a single node
    {
      curr = curr.next; // Set curr to one point ahead
    }

    while (curr.next != null) // Go through the list
    {
      if (curr.data <= curr.next.data) // If the current point is less than or equal to the next one
      {
        // Continue to the next point
        curr = curr.next;
        prevMarker = prevMarker.next;
      }
      else // If the current point is greater than the next one
      {
        if (curr.next.next == null) // If the next node is the last one
        {
          curr.next = null; // Remove it
        }
        else // If not the last one
        {
          prevMarker.next = curr.next; // Set to skip
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
    public static void tripleValues(Queue<Integer> queue) 
    {
      if (queue == null) 
      {
        throw new IllegalArgumentException("Queue cannot be null");
      }

      int length = queue.size(); // Get the size

      while (length != 0) // For each point in the queue
      {
        queue.add(queue.poll() * 3); // Dequeue, multiply it by three, add it back to the queue
        length--; // Iterate down
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
  public static void rotateQueueLeft(Queue<Integer> queue, int k) 
  {
    if (queue == null || k < 0) 
    {
      throw new IllegalArgumentException("Queue cannot be null and k cannot be negative.");
    }

    while (k != 0) // Go through queue
    {
      queue.add(queue.poll()); // Remove from the front, and add to the back of the queue
      k--; // Iterate down
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
  public static boolean hasBalancedParentheses(String input)
  {
    if (input == null) 
    {
      throw new IllegalArgumentException("Input string cannot be null.");
    }

    if (input == "") // If empty
    {
      return true;
    }

    if (input.toCharArray()[0] == ')') // If the first item is a closing parenthesis
    {
      return false;
    }

    Stack<Character> parenPile = new Stack<>(); // Set up stack

    for (Character paren : input.toCharArray())
    {
      if (paren == '(') // If opening
      {
        parenPile.push(paren); //  Add to the stack
      }
      else if (paren == ')') // If closing
      {
        if (parenPile.isEmpty()) // if empty
        {
          return false;
        }
        else // Otherwise
        {
          parenPile.pop();
        }

      }

    }

    if (parenPile.isEmpty()) // If empty at the end
    {
      return true;
    }
    else // If remainders
    {
      return false;
    }

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
  public static String topScorer(Map<String, Integer> scores) 
  {
    if (scores == null || scores.isEmpty()) 
    {
      throw new IllegalArgumentException("Scares cannot be null or empty");
    }

    int highestScore = 0; // Set up high value

    for (int score : scores.values()) // Go through the values
    {
      if (score > highestScore) // If a score is bigger than the current highest
      {
        highestScore = score; // Make that the new highest
      }

    }

    String highScoreName = ""; // Set the name

    for (String name : scores.keySet()) // Go through the names
    {
      if (scores.get(name) == highestScore) // If a name has the highest score
      {
        if (highScoreName == "") // If high score is empty
        {
          highScoreName = name; // Add the name to that
        }
        else if (highScoreName.compareTo(name) > 0) // If the Name is alphabetically higher
        {
          highScoreName = name; // Set the lower name
        }
      }

    }

    return highScoreName;

  }

}