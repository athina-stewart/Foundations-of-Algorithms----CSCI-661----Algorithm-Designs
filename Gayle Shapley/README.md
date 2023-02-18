# **Gayle Shapley matching Algorithm**

## Problem Definition
Given are two disjoint sets, along with preference
lists for each element of each set, ranking all of the elements of the other set. Design an
O(n^2) algorithm that determines the number of elements from the first set that have only
one valid partner. (An element from the first set has only one valid partner if it is paired
with the same element from the second set in every stable matching.)

## Solution Approach
Given two disjoint sets and their respective preference lists, this question asks to determine the number of elements 
from the first set that only has one valid partner. This question also requires that this is solved using an n2 algorithm. 
In order to do this, the Gayle Shapley algorithm was employed. The Gayle Shapley algorithm yields a stable matching between 
the elements of the first and second set in that there is a perfect match and there are no instabilities. This algorithm 
was chosen to solve this problem because the Gayle Shapley algorithm yields the most optimal results for the set of askers 
and pessimal results for the responders. Therefore if the sets representing the askers and responders are switched, we would 
know that any stable matching that is the same in both instances would indicate that for that specific pair, the match is 
both the best and worst possible match the pair can have. Therefore, even if another stable matching algorithm is used, the 
stable matches that were found using this approach (swapping the asking and responding set), would also be found by those algorithms.

In the algorithm submitted that was designed to solve the problem, it was ensured that the algorithm maintained a O(n^2) time 
complexity. The preference lists are stored in a nested array which is done in O(n2) time and the askers are stored in a stack 
(O(1) time). This way retrieving and placing an asker back into the unmatched pool is O(1) time complexity. An inverse preference 
list was used so that when deciding if a responder should upgrade, we do not need to repeatedly search their preference lists 
because there is no guarantee where the asker will be on their list so each scan would be a linear operation. Instead, the 
ranking of the number is placed in the same index. So at index 1, the ranking of asker 1 is stored and that way. we only need 
to retrieve the index in O(1) time. The creation of this inverse preference list is done in O(n^2) time. Using this inverse 
preference list and stack of askers, a while loop is used in which each asker checks the state of every option on their preference 
list. If the option is not matched, then they are instantly matched, if they are matched, then the inverse preference list is 
used to check if the responder wants to upgrade. The indices of the current asker and the matched asker are simply compared. If 
the value of the current asker’s index is smaller, then the responder updates their match. The while loop ends when all of the 
askers are matched. 

Also to note, the System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length) method is used to combine the 
askers' matches and the responders’ matches into one array so that a comparison is made to see how many elements in the first 
half or the array matches the second half of the array. Therefore, it will have to go through the elements of both of the arrays 
holding the matches for the askers and responders so in each instance, the time complexity is O(length) and this does not affect 
the overall (n^2) time. 

_Overall time Complexity: O(n^2)_
