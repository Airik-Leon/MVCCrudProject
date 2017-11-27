# MVC CRUD Project
## Author: Airik Leon
### Date: November 2017
![alt tag](https://i.imgur.com/sI5Tx6a.png "Blog photo")

This project is a blog that is (still in development) and is intended to show off Spring MVC skills & CRUD application.

technologies used:
- Spring MVC
- Java
- Object Oriented Programming
- Jstl  & Expression Language
- Gradle
- JDBC
- MySQL DB queries & joins

For viewers the way to use this application is in two ways. You can browse posts
by category which is a retrieval function from the DB and if you add an account
and sign in you can do a create function by posting on the current posts in the blog.
For the update and delete functionality you must sign as the guest admin account
to retrieve users on the get Users page. It is on the Get User page where you can
update information (excluding the id) of the user or delete a user.
The guest admin account is below:

![alt tag](https://i.imgur.com/Iuhoc84.png "Guest Admin info")

if you can't see the information then the info is below:

username = Guest_Admin
password = admin1234

Admins have ability to create other admins under create a user tab and are
also allowed to create posts.

For Database Schema see below:
![alt tag](https://i.imgur.com/onHSzxG.png "Guest Admin info")

Future things I would like to add are the following:
 - Users make changes to their accounts.
 - Post liking by users
 - Allow custom Post
 - Enable direct messaging to other users
 - Add more admin stats on users
 - customize feeds for users.

My biggest problem were the following:
- test cases I only had a few days and I had many features to add so I decided to skip test cases to get the feaures working. And that is a regret that I will carry with me. :(
- Handling multiple users
