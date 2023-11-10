# **Chef In The Kitchen**

## Problem Sepcification
#### Input specification: 
The first line contains a positive integer n, representing the number of food items that are donated. Following that are n lines. Each line contains 2 values, separated by a space, indicating the time unit the food item arrives and its shelf life. The shelf life is the number of time units the donated food item remains viable. If not used within that timeframe, the item goes bad. For example, a shelf life of 1 means the item must be used on the same time unit it arrived.

#### Constraints:

* 1 ≤ n ≤ 1,000,000
* Each input value fits in a 32-bit integer.
* The input file lists food items in non-decreasing order of arrival time.

#### Output specification: 
The output contains a single line with a single string: "YES" (if it is possible to use all food items without any going to waste) or "NO" (otherwise), including the end-of-line character.

## Solution Design
### Overview
To solve this problem, a min-heap was implemented using the built-in priority queue data structure in Java. Food items were added to the heap with their expiration time calculated as the sum of arrival time and shelf life. The priority queue ensures efficient addition and removal of the minimum element, both done in O(logn) time.

### Implementation Details
1. Min-Heap Implementation:

* Food items were added to the min-heap based on their expiration time.
* Operations of addition and removal of the minimum element were optimized for O(logn) time complexity.

2. Chef's Strategy:

* The chef can use any item per day, but the min-heap helps determine if it's possible to use all items.
* The items in the heap are represented as time units to expiry plus the time unit they were added. Therefore, an element as [3, 5] is added to the heap on day 3, to be expired on day 8.
* This approach avoids repeated O(n) operations, making the solution more efficient.

3. Validation Loop:

* The expiration date of the first food item is compared with the current day.
* A secondary loop checks if the remaining items can be used before they expire, removing the minimum element while the heap is not empty.

## Overall Complexity
The overall time complexity of the algorithm is O(nlogn). This efficiency is achieved by leveraging the min-heap for optimal addition, removal, and validation of food items, ensuring the chef can utilize all items without waste.
