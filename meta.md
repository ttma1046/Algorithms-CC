meta.md

Just finished my onsite today and told myself I would give back to this community that has helped me so much regardless of outcome. I will honour the NDA and not leak exact coding questions but I will share other things like how I prepped and behavioural stuff

I had the following rounds (in this order):
1. Online Assessment: 2 Easy, 2 Medium based on leetcode difficulty
2. Phone Screen: 1 Medium, 1 Hard
3. Onsite
3a: Behavioural
3b: Coding Round 1: 1 Easy, 1 Medium
3c: Coding Round 2: 2 Medium

For all the technical questions to be prepped I here is what I used:

The leetcode top 50 facebook questions: https://leetcode.com/problem-list/top-facebook-questions/
Go on https://leetcode.com/company/facebook/ and sort by frequency and practice as many of those as possible. I can say that out of the 2 phone screen questions, and 4 onsite coding technical questions I had, only 1 of them was not from the top 50 questions and even then it wasnt too bad. I can actually say that for me personally that the phone screen was harder than the actual onsite in my opinion. Obviously, not the case with everyone but for me I think it was.
Here are other resources I used to prep beyond leetcode:
Technical:

Neetcode: https://www.youtube.com/c/NeetCode
Algo.Monster: https://algo.monster/problems/stats
Cracking FAANG: https://www.youtube.com/channel/UCMrgnopgrjjIJn-xyYhZ7nA
Behavioural:

Igotanoffer: https://igotanoffer.com/blogs/product-manager/behavioral-interview-questions-tech-companies#techniques
Cracking the Behavioral Interviews: for Software Engineers: https://www.amazon.ca/Cracking-Behavioral-Interviews-Software-Engineers/dp/1710348615
Also, hopefully to motivate. In university I failed Data Structures Class pretty badly and nearly failed my Algorithms classes. But, I studied like crazy for these interviews. It started in early March I got the reach out from recruiter and that is when I starting prepping until now. Despite my fundamentals being shaky I made sure to practice as much leetcode as possible along with using the other resources mention. Algo.monster I think really helps get your fundamentals strong in the shortest amount of time (I did not use the premium version of Algo.monster. I did however, use leetcode premium. I would say between early March and now I did about 100-115 leetcode questions (All from facebook) most frequently asked questions

While I did mention that I would not share exact questions I dont want to leave everyone high and dry. What I have been doing is all the questions I did solve of those 100-115 I mentioned, I recorded my solutions along with notes for each question to help solve. Im not sure if the notes will be 100% helpful to everyone since it was a mental note to myself initially, hopefully someone, somewhere could use it. https://docs.google.com/spreadsheets/d/1sb7e4Le_D9IWyGtvayw6cR-RlwHlHSJL9LLFD-N_DII/edit?usp=sharing <- All except 1 coding question came from this doc but that does not mean in your case you will have the same situation

Thanks, everyone and Ill update this post when I get my result back





I was asked these two questions with variations in the Meta phone screening for Senior ML engineer-

https://leetcode.com/problems/valid-palindrome/
https://leetcode.com/problems/diameter-of-binary-tree/
For the first question, I thought out loud and was in the process of cross-checking code when the interviewer mentioned an edge case I missed. It was with string input of '#@$', but I was able to correct it when pointed out. I was also asked space and time complexity here, mine was O(1) and O(N) respectively.
For the second question, I passed all edge cases and was asked to code variation for an N-ary tree which I was able to implement successfully. The interviewer asked me to write code for how client would interact with the API and my way to do that was not traditional. I created class object and was storing the answer in instance variable (self.ans) and returning that outside the function definition. I think I should've stuck to returning it in the function itself.
I got rejected. I've read posts where people were able to crack phone screens after answering just one question out of two with brute force and thought the bar for my phone screening was higher than usual as I felt pretty confident about being selected after the interview. Maybe because I was interviewing for a senior position?

Anyway, hard luck. :( The recruiter told me that the interviewer expected me to solve both questions flawlessly without any help from her. In retrospect, I think maybe instead of thinking out loud and working on solution with the interviewer, I should've tested my code rigorously myself silently before. But they mentioned in all webinars that one should talk out loud and explain code while writing, so honestly I think the expectation was unfair. I also practiced Top 50 questions list multiple times, but for phone screening I think I should've focussed on quality over quantity, and made sure I understood all possible test cases aspects of easy/medium problems.


How Meta grades coding questions:

/*
Problem Solving: We’re evaluating how you comprehend and explain complex
ideas. Are you providing the reasoning behind a particular solution? Developing
and comparing multiple solutions? Using appropriate data structures?
Speaking about space and time complexity? Optimizing your solution?

• Coding: Can you convert solutions to executable code? Is the code organized
and does it have the right logical structure?

• Verification: Are you considering a reasonable number of test cases or coming
up with a good argument for why your code is correct? If your solution has
bugs, are you able to walk through your own logic to find them and explain
what the code is doing?

• Communication: Are you asking for requirements and clarity when necessary,
or are you just diving into the code? Your coding interview should be a
conversation, so don’t forget to ask questions.
*/
Possible you failed verification and or problem solving/coding. It is I think expected that the solution you come up with works and points are lost if the interviewer has to help you.

But don't be discouraged, plenty of other places to try your luck.