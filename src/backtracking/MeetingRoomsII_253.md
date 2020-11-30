## meeting rooms 

The solution is proposed by @pinkfloyda at "Super Easy Java Solution Beats 98.8%" , which is amazing.

Here I would like to explain why it works a little bit.

The code from @pinkfloyda:
```java
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr]) {
                rooms++;
            } else {
                endsItr++;
            }
        }
        return rooms;
    }
}
```
To understand why it works, first let’s define two events:

* Meeting Starts
* Meeting Ends

Next, we acknowledge three facts:
1. The numbers of the intervals give chronological orders.

2. When an ending event occurs, there must be a starting event has happened before that, where “happen before” is defined by the chronological orders given by the intervals.

3. Meetings that started which haven’t ended yet have to be put into different meeting rooms, and the number of rooms needed is the number of such meetings

So, what this algorithm works as follows:

for example, we have meetings that span along time as follows:

|_____|
      |______|
|________|
        |_______|
Then, the start time array and end time array after sorting appear like follows:

||    ||
     |   |   |  |
Initially, endsItr points to the first end event, and we move i which is the start event pointer. As we examine the start events, we’ll find the first two start events happen before the end event that endsItr points to, so we need two rooms (we magically created two rooms), as shown by the variable rooms. Then, as i points to the third start event, we’ll find that this event happens after the end event pointed by endsItr, then we increment endsItr so that it points to the next end event. What happens here can be thought of as one of the two previous meetings ended, and we moved the newly started meeting into that vacant room, thus we don’t need to increment rooms at this time and move both of the pointers forward.
Next, because endsItr moves to the next end event, we’ll find that the start event pointed by i happens before the end event pointed by endsItr. Thus, now we have 4 meetings started but only one ended, so we need one more room. And it goes on as this.



Hint:

Think about how we would approach this problem in a very simplistic way. We will allocate rooms to meetings that occur earlier in the day v/s the ones that occur later on, right?

If you've figured out that we have to sort the meetings by their start time, the next thing to think about is how do we do the allocation?

There are two scenarios possible here for any meeting. Either there is no meeting room available and a new one has to be allocated, or a meeting room has freed up and this meeting can take place there.

An important thing to note is that we don't really care which room gets freed up while allocating a room for the current meeting. As long as a room is free, our job is done.

We already know the rooms we have allocated till now and we also know when are they due to get free because of the end times of the meetings going on in those rooms. We can simply check the room which is due to get vacated the earliest amongst all the allocated rooms.

Following up on the previous hint, we can make use of a min-heap to store the end times of the meetings in various rooms.

So, every time we want to check if any room is free or not, simply check the topmost element of the min heap as that would be the room that would get free the earliest out of all the other rooms currently occupied.

If the room we extracted from the top of the min heap isn't free, then no other room is. So, we can save time here and simply allocate a new room.