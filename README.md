Build and run
=============
* Build `mvn clean package`
* Look for the jar with dependencies in /target and run `java -jar game-1.0-SNAPSHOT-jar-with-dependencies.jar`




Game Menu
=========
* s: welcome! 1) play, 2) load saved game
* p: 1
* s: wanna create a character? Y/N


Game Playing
============
* S: Explore! N,S,E,W
* P: E
* S: good!
* P: E
* s: wall!
* p: N
* s: good!
* p: N
* s: geek found! Fight (F) or infoq (Q)?
* p: F
* s: engage! Kick (k), Punch (P)
* p: K
* p: K
* p: P
* s: geek defeated! Eat brain (B) or keep exploring (X)?
* p: B
* s: yummy! Geek knowledge aquired: Java applets. New status: junior zombie dev. Keep Exploring! N,S,E,w
* p: N
* s: level completed! Next level: nom project manager. Play (P) ?

Game Char creation
==================
* s: create a character is fun! Pick a name for ur player
* p: xxx
* s: that's a great name... U will be able to customize your player in the next (and buyable) version :D

Save Game
=========
* s: wanna save ur game (Y) ?
* p: Y
* s: ur game was saved!

Load saved Game
===============
* s: please pick which one u want to load
** 1) bar date/time
** 2) foo date/time
* p: 1
* s: game loaded! 
* Status: [stats]

TODO:
=====
* exploration hints
* replace if's in systems with strategy pattern (SRP)
* list and load saved games in new system
* add properties file with different data for each geek in the level
* store in zombie all the knowledge from the brains eaten
* verbose mode ;)
