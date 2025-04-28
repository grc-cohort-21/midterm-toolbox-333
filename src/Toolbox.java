import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Toolbox 
{

  /**
   * Finds the length of a singly linked list given the head.
   *
   * @param head the head node of the singly linked list
   * @return the number of nodes in the list
   * @throws IllegalArgumentException if the head is null
   */
  public static int length(SingleNode head) 
  {
    if (head == null) //list cannot be null
    {
      throw new IllegalArgumentException("Head cannot be null.");
    }

   
    int count = 0;    //tracker counter for the nodes
    SingleNode current = head;  // starts from head

    while (current != null)  //current doesnt equal null so loop until reaches null/end
    {
      count++; //increment count for each 
      current = current.next; //iterat to the next node
    }

    return count; //return total count of nodes
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
      //list cannot be null
      throw new IllegalArgumentException("Head cannot be null.");
    }

    SingleNode current = head; //starts from head

    while (current.next != null)  //current doesnt equal null so loop until reaches null/end
    {
      current = current.next; //iterated to the nect node, move right,f oward
    }
  
    return current; //youll end up at the last node 

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
    if (tail == null) //list cannot be null
    {
      throw new IllegalArgumentException("Tail cannot be null.");
    }

    DoubleNode current = tail; //starts from the end/tail

    while(current.prev != null) 
    {
      current = current.prev; //move left, back if not null
    }
    return current; //returns to the start, first node which is the head
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
    if (head == null) //list cannot be null
    {
      throw new IllegalArgumentException("Head cannot be null.");
    }
    Map<Integer, Integer> mapper = new HashMap<>(); //created mapper object

    SingleNode current = head; //starts from the head, current is the list

    //check if something is already in the map if not put it in with the count of 1
    //if it isnt in the map then get the value and increase it by 1
    while(current != null)
    {
      int key = current.data; //key is the data (from above they referenced value) from the linked list 

      if(!mapper.containsKey(key)) //if the data isnt already a key in mapper
      {
      mapper.put(key, 1); //add the key and associated value of 1
      }
      else 
      {
      mapper.put(key, mapper.get(key) + 1); //overwrite(put) the key and getting the value plus 1
      }

      current = current.next; //iterating to the next node.

    }

    return mapper; 
  }



  /**
   * Removes a node from a doubly linked list.
   *
   * @param node the node to remove
   * @throws IllegalArgumentException if the node is null
   */
  public static void removeNode(DoubleNode node) 
  {
    if (node == null) //list cannot be null
    {
      throw new IllegalArgumentException("Node cannot be null.");
    }
    
    //updates previous node and skips current
    if(node.prev !=null)
    {
      node.prev.next = node.next;
    }

    if(node.next !=null) //updates the next node and skips current
    {
      node.next.prev = node.prev;
    }

    //remove current node completly
    node.prev = null;
    node.next = null;
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
    if (head == null || n < 0) //must not be null or less than 0
    {
      throw new IllegalArgumentException("Head cannot be null and n cannot be negative.");
    }

    SingleNode current = head; //starts at the head
    int index = 0; // tracker object

    while(current != null && index < n) //so long as current isnt null and index is less than n
    {
      current = current.next; //iterate right/foward
      index++; //increment 
    }

    return current; 
  }

  /**
   * Inserts a new node into a singly linked list given a pointer to a node in the middle of the list.
   *
   * @param node the node after which the new node is to be inserted
   * @param newNode the new node to insert
   * @throws IllegalArgumentException if either node or newNode is null
   */
  public static void insertNode(SingleNode node, SingleNode newNode) //single node is just a pointer
  {
    if (node == null || newNode == null) //niether cannot be null
    {
      throw new IllegalArgumentException("Node and newNode cannot be null.");
    }

    newNode.next = node.next; //newNode points to the next node after current

    node.next = newNode; //insert newNode after current node 

    //[0, 1, 2, 3]
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
    if (head == null) //cannot work with a null list
    {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    SingleNode current = head; //starts with the head

    //[0, 1, 2, 3, 4, 5]
    while (current != null && current.next != null) //go through the list while this is good
    {
      //check to see if the next node is greater than its neightbor 
      if(current.next.next != null && current.next.data > current.next.next.data)
      {
        current.next = current.next.next; //skips the current.next and points to the nighbor
      }
      else
      {
        current = current.next; //else move to the next node.
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
      if (queue == null) //list must not be null
      {
        throw new IllegalArgumentException("Queue cannot be null");
      }

      int size = queue.size(); //stores the start of the size

      //iterate through queue
      for (int i = 0; i < size; i++)
      {
        int value = queue.remove(); //remove dequeue element from front
        queue.add(value * 3); //triple value and enqueue back
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
    if (queue == null || k < 0) //cannot by null and not k be negative
    {
      throw new IllegalArgumentException("Queue cannot be null and k cannot be negative.");
    }
    
    int size = queue.size(); //get number of elements in teh queue

    if(size == 0 || k% size ==0) //no reottaion is required if queueis empty or mutliple of the size
    {
      return;
    }

    k=k % size; //k is larger than the queu size

    //move the first k element to the back of the queue
    for (int i = 0; i < k; i++) 
    {
      int front = queue.remove(); // Remove from front
      queue.add(front);     
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
  //when using a pop in a list it will remove from the list size
  //use a while loop instead of a for each loop if you need to iterate through a string
  public static boolean hasBalancedParentheses(String input)  
  {
    if (input == null) //no null string for this to operate
    {
      throw new IllegalArgumentException("Input string cannot be null.");
    }
    java.util.Stack<Character> stack = new java.util.Stack<>(); // Stack for open parentheses

  for (char ch : input.toCharArray()) 
  { // Convert string to character array and loop
    if (ch == '(') 
    {
      stack.push(ch); // Push opening parenthesis onto stack
    } else if (ch == ')') 
    {
      if (stack.isEmpty()) 
      {
        // No matching open parenthesis
        return false;
      }
      stack.pop(); // Match found, remove opening from stack
    }
    
  }

  // Return true if all open parentheses were closed
  return stack.isEmpty();

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
    String topName = null;          // Store the name of the top scorer
  int topScore = Integer.MIN_VALUE; // Initialize with the lowest possible score

  //Iterate over each entry in the map
  for (Map.Entry<String, Integer> entry : scores.entrySet()) 
  {
    String name = entry.getKey();  // Player's name
    int score = entry.getValue();  //Player's score

    //Ifscore is higher than current top score, or tied but name is lexicographically smaller
    if (score > topScore || (score == topScore && (topName == null || name.compareTo(topName) < 0))) 
    {
      topScore = score; // Update top score
      topName = name;   //Update top scorer
    }
  }

  return topName; // Return topname
  
  }
}