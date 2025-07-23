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
 //Floyd's Cycle Detection Algorithm
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode rabbit=head,turtle=head;
        while(rabbit!=null &&rabbit.next!=null){
            turtle = turtle.next;
            rabbit = rabbit.next.next;
            if(turtle == rabbit)
                return true;
        }
        return false;
    }
}