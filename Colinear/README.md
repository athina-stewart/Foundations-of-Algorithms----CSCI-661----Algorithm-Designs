# Collinear Points Detection

## Problem Definition
Given n distinct points in 2-dimensional space, the task is to design an O(n^2logn) algorithm that determines 
whether any three of these points are evenly spaced, forming co-linear points. A Θ(n^3) algorithm can earn at 
most 8 out of 18 marks.

## Explanation
The goal of this problem is to identify, among a set of points, whether there exist any three points that are 
evenly spaced and collinear. The solution employs sorting and slope calculations to efficiently identify such points.

## Algorithm Steps
1. Sort Points:
* Sort the given points first by their x value and then by their y value using the Arrays.sort() method. (Time Complexity: O(nlogn))

2. Calculate Slopes:
* Use a double for loop to calculate the slopes between every pair of points. Store the slope and distance of each pair in an array. (Time Complexity: O(n))

3. Sort Slopes:
* Sort the array of slopes by their slope values. (Time Complexity: O(nlogn))

4. Identify Collinear Points:
* Iterate through the sorted slopes array, checking for pairs of adjacent points with the same slope.
* If such pairs are found, further check if they are evenly spaced by comparing their distances.
* Return "YES" if evenly spaced, indicating the presence of collinear points.

## Overall Time Complexity
The overall time complexity of this algorithm is dominated by the outer for-loop and the sorting of slopes, resulting in O(n^2logn). The sorting operation contributes O(nlogn) complexity, making the algorithm efficient for large datasets.

#### Alternative Solution
An alternative solution using a HashMap is also discussed. Points, slopes, and distances are stored in the HashMap, and the algorithm checks for collinearity and even spacing. The HashMap implementation performed better for larger datasets compared to the O(n^2logn) solution, demonstrating the efficiency of the O(n^2) solution.

## Conclusion
This algorithm efficiently determines evenly spaced, collinear points, providing an O(n^2logn) solution that outperforms the alternative implementation for larger datasets. The sorting and slope calculation approach contributes to the algorithm's effectiveness.
