# SlappingPlayers-Spigot

SlappingPlayers-Spigot is a silly/fun plugin. It gives a player with proper permissions the ability to "slap" a player, either causing no damage or some damage. You can slap a player from anywhere, and it does not get picked up by NoCheatPlus. The code is full of comments for easy following.

## Commands

- /slap
    - <player> [damage]

## How To Use

To slap a player, ensure that you have the "slappingplayers.slap" permission. Use the command '/slap <player>', replacing <player> with the player's name. This will then cause the targeted player to recieve 0 damage, while having the 'hurt' effect.

To slap a player for damage, ensure that you have the "slappingplayers.slap.damage" permission. At the end of your slap command, add an integer for the amount of damage. This will cause the targeted player to recieve the amount of damage specified.

## Dependencies

SlappingPlayers-Spigot was built with SpigotAPI 1.8.8 R0.1. Newer versions of the SpigotAPI could possibly cause compilation to fail.

To get the SpigotAPI, you have to use Spigot's BuildTools, which can be found [here] [SpigBT]

## Building

* Fetch the GitHub repo and open in your preferred IDE.
* Using BuildTools, download the SpigotAPI.
* Add SpigotAPI as an external JAR.
* Build all files to a JAR file.
* Put the JAR file in a Spigot server's 'plugins' folder
* Start/Restart Spigot or use a plugin to manually load the plugin.


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [SpigBT]: <https://www.spigotmc.org/wiki/buildtools/>

