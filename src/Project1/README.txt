/***                             Minecraft Memory                             ***\
 *                                                                              *
 *                           Student Name:  John Call                           *
 *                           Student A#:    A01283897                           *
 *                                                                              *
\***                               CS2410 ~ USU                               ***/

+---------------------------------------------------------------------------------+
|                                  CITED SOURCES                                  |
+---------------------------------------------------------------------------------+

All images and sounds are Copyrighted by MojangAB. They can be found on: http://minecraft.gamepedia.com

Peer Collaboration Acknowledgements
  --> Showed Jennifer Balling (A00890700) how to use org.json


This project depends on two open-source projects:
Org.json for parsing JSON formatted data: https://code.google.com/p/org-json-java/
Easy-ogg for processing ogg formatted sound files: http://www.cokeandcode.com/main/code/
  --> This depends on com.jcraft.jogg.SyncState: http://www.java2s.com/Code/Jar/j/Downloadjorbisjar.htm

These sources are also included in the assignment submission for your convenience but you may need to configure your IDE to import them.

+---------------------------------------------------------------------------------+
|                         PROJECT REQUIREMENTS FULFILMENT                         |
+---------------------------------------------------------------------------------+

Extra rule about point system
  - Creepers are -3 points
  - Bosses (Enderdragon and Wither) are worth 5 points

Optional Feature: Special Cards
  - Herobrine
    -> When Herobrine is revealed all cards are shuffled
    -> If a matching set of Herobrine is revealed the player loses 9,999 points (instant loss)
  - Creeper
    -> If a matching set of Creeper is revealed the player loses 3 points
  - Bosses
    -> If a matching set of mob bosses (Enderdragon or Wither) the player gains 5 points
    
  (Yes there's some layover between the special cards and the point system, hopefully that there's an extra mitigates this)

Original Feature: Sounds
  - Almost every card has three sound clips associated with it.
    -> A sound that is played if it's the first card flipped over
    -> A sound that is played if it matches the first card flipped over
    -> A sound that is played if it doesn't match the first card that is flipped over
  - When a new game is started, Minecraft's melodic morning time music plays
  - When a player wins the game, the sound of the player's hunger bars replenishing is played

Some sounds do not play successfully in Java (Tested only in Windows 7).
I'm not sure what is different between the sound files that work and the ones that don't.
You can see the status of the sound library by opening the Sound Tester Window:
  - Red buttons indicate that type of card doesn't have an associated sound
  - Yellow buttons indicate that particular sound clip doesn't play in Java

Additionally there is an occasionally occurring bug where if the game is open long enough a sound will get stuck in a short loop.
This usually results in nothing more than a clicking sound that occurs every second. Up to now I have no idea what causes it.

+---------------------------------------------------------------------------------+
|                                   HOW TO PLAY!                                  |
+---------------------------------------------------------------------------------+

This is a simple memory game with a Minecraft theme to it.
 -> Click on two cards to flip them over.
 -> If they are a match you will be awarded points and get to go again.
 -> If they are not match it will switch to the other player's turn.
 -> You can see who's turn it is by looking at Steve.

Every matching pair is worth a different number of points.
 -> Be ware of creepers! They will make you lose three points if you match them!
 -> Bats are worth zero points, but you get go again and have a shot at getting a consecutive match bonus!
 -> The following pairs are worth 1 point
   - Villager
   - Chicken
   - Cow
   - Mooshroom
   - Horse
   - Ocelot
   - Pig
   - Sheep
   - Squid
   - Snow Golem
   - Wolf
   - Spider
   - Magma Cube
   - Slime
   - Skeleton
   - Zombie
 -> The following pairs are worth 2 points
   - Iron Golem
   - Cave Spider
   - Zombie Pigman
   - Blaze
   - Ghast
   - Silverfish
   - Witch
   - Spider Jockey
 -> The following pairs are worth 3 points
   - Chicken Jockey
   - Wither Skeleton
   - Enderman
 -> Mob bosses are worth 5 points
   - Wither
   - Enderdragon
 -> Additionally, there is the infamous griefer Herobrine! 
   - He only appears once in a 7x7 game, or twice in an 8x8 game!
   - After he is revealed, all the cards are shuffled!
   - If you are playing an 8x8 game and reveal a matching pair of Herobrine, you will lose 9,999 points!
   - The game is over if the only cards remaining are Herobrine cards. So unlike the creeper, the last person is not forced to take the point loss.
   - Because of the nature of Herobrine, as you play an 8x8 game, the board will shuffle more and more frequently!
      -> (an 8x8 game is not for the faint of heart!)
 
 At the end of the game the player with the most points wins!
 You can start a new game with the menu "Game -> New Game".
 You can change the game size with the menu "Game -> Options".
   - If a game is active when changing the options you will be prompted whether or not to quit the current game and start a new one.


+---------------------------------------------------------------------------------+
|                       OTHER INFORMATION ABOUT THIS PROJECT                      |
+---------------------------------------------------------------------------------+

Some features are not built in, but code is in place that would make it easy to implement.
Perhaps I might implement some of them in the future just because it would be simple at this stage, and make things nice.
  - Showing the rules and how the game is played in a dialog accessible from the help menu
  - Keeping track of players scores/turns/wins by name in a data file
  - Changing which players are engaged in the next game from list of known players
  - Collusion reports
