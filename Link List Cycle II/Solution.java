/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode rabbit =head,turtle = head;
        int i=0;
        while(rabbit!=null &&rabbit.next!=null){
            turtle = turtle.next;
            rabbit = rabbit.next.next;
            if(turtle == rabbit)
                {
                    turtle = head;
                    while(turtle != rabbit){
                        turtle = turtle.next;
                        rabbit =rabbit.next;
                        i++;
                    }
                    System.out.println("tail connects to node index " + i);
                    return turtle;

                }
        }
        System.out.println("There is no cycle in the linked list.");
        return null;
        
    }
}