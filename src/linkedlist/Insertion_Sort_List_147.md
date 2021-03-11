Given the <code>head</code> of a singly linked list, sort the list using <strong>insertion sort</strong>, and return <em>the sorted list&#39;s head</em>.

The steps of the <strong>insertion sort</strong> algorithm:


* Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
* At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
* It repeats until no input elements remain.


The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.

![](./Insertion_Sort_List_147/Insertion-sort-example-300px.gif)

<strong>Example 1:</strong>
![](./Insertion_Sort_List_147/sort1linked-list.jpg)

<pre>
<strong>Input:</strong> head = [4,2,1,3]
<strong>Output:</strong> [1,2,3,4]
</pre>

<strong>Example 2:</strong>
![](./Insertion_Sort_List_147/sort2linked-list.jpg)

<pre>
<strong>Input:</strong> head = [-1,5,3,4,0]
<strong>Output:</strong> [-1,0,3,4,5]
</pre>

<strong>Constraints:</strong>

<ul>
	The number of nodes in the list is in the range <code>[1, 5000]</code>.
	<code>-5000 &lt;= Node.val &lt;= 5000</code>
</ul>
