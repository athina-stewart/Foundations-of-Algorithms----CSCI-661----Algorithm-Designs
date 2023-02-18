# **Chef In The Kitchen**

## Problem Sepcification

Input specification: the first line contains a positive integer n, representing the number of food items that are donated. 
Following that are n lines. Each line contains 2 values, separated by a space, that specify a particular donated food item. 
The first such value indicates the time unit that the food item arrives. This value is guaranteed to be a non-negative integer. 
The second such value indicates the shelf life associated with this food item. This value is guaranteed to be a positive integer. 
It represents the number of time units that the donated food item is viable. If the item is not used within that many time units 
(including the unit it arrived), it goes bad. So for example, a value of 1 indicates that the food item must be used on the same 
time unit that it arrived.

You as chef can utilize only 1 food item per time unit, and can choose to use a food item in the same time unit that it arrives, 
or delay it for a later time unit. Note that there may be multiple food items that arrive at the same time unit. (The problem is 
trivial if this doesn't occur.)

You may assume that n is not bigger than 1,000,000 and that each input value fits in a 32-bit integer. You may also assume that the 
input file lists the food items in non-decreasing order of arrival time.

Output specification: the output contains a single line with a single string: "YES" (if it is possible to use all food items without 
any going to waste) or "NO" (otherwise), including the end of line character.


## Solution Design
In order to solve this problem, the items were added in a min-heap using the built in priority queue data structure in Java. It is known that the operations of addition of an element and removal of the minimum element are done in O(logn) time. This is because when an element is added it “bubbles” up on one side of the tree (involving half of the data). Bubbling up involves comparing with the parent of the element and if the value is smaller then it is swapped. The addition of n elements means that the overall time complexity is O(nlogn). Similarly for removal of the minimum element (in this case, the highest priority element), happens in O(logn) time and the removal of n elements would be O(nlogn). Accessing the minimum element (highest priority element) happens in O(1) time because the min heap is stored in an array and we simply need to show the index of 0. 

The chef can randomly use any item to cook with per day. However, a min heap was chosen as the approach to solve this problem because we want to know if it is possible to use all the items. If the chef uses the item that will expire first each day, and if there is an item left that still expires, then it is sure that there was no way to use all the items before they went bad. Important to note is that the elements are stored in the min heap as the time units to expiry plus the time unit it was added. So an element with [3, 5] is added to the heap to be expired on day 8. This way, the heap does not need to be traversed after every day and every item decremented. This avoids the addition of repeated O(n) operations. 

Finally, to check if the chef can use all the food items, the expiration date of the first food item is compared with the day. Also, even if all the food items have been added and the minimum element doesn’t expire right away, it still needs to be checked that the remaining items can be used before they expire. To do this, there is one more loop in which the min element is removed while the heap is not empty and compared with the current day that increases by one for each loop. This operation could be at most O(n), if all the items were added to the heap on day 1. 

_Overall Complexity: O(nlogn)_
