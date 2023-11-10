# Domino Pair Matching
## Problem Definition
A special set of n dominoes are given, each with two integer numbers of dots. The number of dots on each domino is in the range 
[0, n), and there is no specific order to the dots. Each domino can be considered with either number as the 'first' or 'second' 
number. The goal is to design an O(nT) algorithm that determines whether there exist two dominoes, a and b, and a way to order 
each domino (a = {a1, a2}, b = {b1, b2}), such that the condition |a1 − b1| + |a2 − b2| ≤ T holds for a given threshold T.

## Explanation
This program checks if, given n dominoes and a threshold value t, there are any two pairs of dominoes (a and b) that satisfy the 
equation |a1 − b1| + |a2 − b2| ≤ t. Each domino has two integer numbers representing the dots, and there is no fixed order to these 
dots, allowing the domino to be flipped.

## Algorithm
1. Flip Dominoes:
* Flip the dominoes so that the lower value is on the top. (Time Complexity: O(n))

2. Sort Dominoes:
* Sort the dominoes by their top values using counting sort, ensuring the bottom values are not misplaced. (Time Complexity: O(n))

3. Check Threshold:
* For each domino, check within the threshold length (t) for a match.
* If t is 1, check one value to the left and one value to the right. (Time Complexity: O(n.2t) ~ O(nt))
* If a match is found, return "Yes"; otherwise, proceed to the next domino.

4. Return Result:
* If no match is found for any pair of dominoes, return "No."
The threshold value is crucial as it determines the range to check for a match. A higher threshold may increase the complexity (approaching O(n^2)), so the algorithm efficiently adjusts the search range based on the specified threshold.

## Overall Complexity
The overall time complexity of the algorithm is O(nT), where n is the number of dominoes and T is the threshold value. This approach optimizes the search for matching pairs, ensuring efficiency for large sets of dominoes.
