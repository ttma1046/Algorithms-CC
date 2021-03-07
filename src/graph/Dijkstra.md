    6      5
 A------B------C
 |     /|     /
1|    / |    /
 |  2/ 2|   /5
 |  /   |  /
 | /    | /
 |/     |/
 D------E
    1

keep two lists

visited = []
unvisited = [A, B, C, D, E]

Dijkstra Table

=================================================

Starts from the start vertex "A";

Examine the unvisited neighbours "B", "D";

|Vertex|Shortest distance from A|Previous vertex|
|------|------------------------|---------------|
| A    |           0            |               |           
| B    |           6            |       A       |
| C    |                        |               |
| D    |           1            |       A       |
| E    |                        |               |

visited = [A]
unvisited = [B, C, D, E]

==================================================

visit the unvisited vertex with the smallest known distance from the start vertex 
which is "D"

Examine the unvisited neighbours "B", "E";

if (the distance + the existing shortest distance from A) smaller the existing neigbour shortest distance from A, overwrite the existing neighbour shortest distance 

|Vertex|Shortest distance from A|Previous vertex|
|------|------------------------|---------------|
| A    |           0            |               |           
| B    |     3 (1 + 2)(A-D-B)   |       D       |
| C    |                        |               |
| D    |           1            |       A       |
| E    |     2 (1 + 1)(A-D-E)   |       D       |

visited = [A, D]
unvisited = [B, C, E]
=================================================

visit the unvisited vertex with the smallest known distance from the start vertex 
which is "E"

Examine the unvisited neighbours "B", "C";

if (the distance + the existing shortest distance from A) smaller the existing neigbour shortest distance from A, overwrite the existing neighbour shortest distance 

|Vertex|Shortest distance from A|Previous vertex|
|------|------------------------|---------------|
| A    |           0            |               |           
| B    |     3 (1 + 2)(A-D-B)   |       D       |
| C    |     7 (2 + 5)(A-D-E-C) |       E       |
| D    |           1            |       A       |
| E    |     2 (1 + 1)(A-D-E)   |       D       |

visited = [A, D, E]
unvisited= [B, C]

=================================================

visit the unvisited vertex with the smallest known distance from the start vertex 
which is "B"

Examine the unvisited neighbours "C"

|Vertex|Shortest distance from A|Previous vertex|
|------|------------------------|---------------|
| A    |           0            |               |           
| B    |     3 (1 + 2)(A-D-B)   |       D       |
| C    |     7 (2 + 5)(A-D-E-C) |       E       |
| D    |           1            |       A       |
| E    |     2 (1 + 1)(A-D-E)   |       D       |

visited = [A,D,E,B]
unvisited = [C]

==================================================

visit the unvisited vertex with the smallest known distance from the start vertex 
which is "C"

Examine the unvisited neighbours, no neighbour:

|Vertex|Shortest distance from A|Previous vertex|
|------|------------------------|---------------|
| A    |           0            |               |           
| B    |     3 (1 + 2)(A-D-B)   |       D       |
| C    |     7 (2 + 5)(A-D-E-C) |       E       |
| D    |           1            |       A       |
| E    |     2 (1 + 1)(A-D-E)   |       D       |

visited = [A,D,E,B,C]

Let distance of start vertex from start vertex = 0;
Let distance of all other vertices from start = ∞;

Repeat
	1. Visit the unvisited vertex with the smallest known distance from the start vertex
	2. For the current vertex, examine its unvisited neighbours
	3. For the current vertex, calculate distance of each neighbour from start vertex. (distance from current to neighhbour + distance from start to current)
	4. If the calculated distacne of a vertex is less than the know distance, update the shortest distance.
	5. Update the previous vertex of each of the updated distances.
	6. Add the current vertex to the list of visited vertices.
Until all vertices visited.

It's a Greedy Algo because point 1:
	1. Visit the unvisited vertex with the smallest known distance from the start vertex

Because if select the closest unvisited vertex from the start vertex everytime, we will get to the end more quickly

Greedy Algorithms:
to make locally optimo chooses at each stage in the hope of finding the global optime chooses
if we select the closest one from the start 


Let distance of start vertex from start vertex = 0;
Let distance of all other vertices from start = ∞;

WHILE vertices remain unvisited
	Visit unvisited vertex with smallest known distance from start vertex (call this 'current vertex')
	FOR each unvisited neighbour of the current vertex
		Calculate the distance from start vertex
		If the calculated distance of this vertex is less than the known distance
			Update shortest distacne to this vertex
			Update the pervious vertex with the current vertex
		end if 
	NEXT unvisited neighbour
	Add the current vertex to the list of visited vertices
END WHILE






