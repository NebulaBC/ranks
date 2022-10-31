# Ranks for BTA!  
commands:  

| Command                       | Description                     |
|-------------------------------|---------------------------------|
| `/rank give <rank> <player>`  | Gives a rank to a player        |
| `/rank prefix <rank> <prefix>` | Sets the chat prefix for a rank |
| `/rank create <rank>` | Creates a new rank              |
| `/rank delete <rank>` | Deletes an existing rank        |



examples:  
*the default rank is "default"  
to modify it just create a rank called "default" and then apply your actions on it.*  

To create a rank called "owner" run:
```
/rank create owner
```
now to give this newly created rank to a player called "Steve" run:
```
/rank give owner Steve
```
and to set the prefix for the owner rank to "&6[Owner]" run:
```
/rank prefix owner &6[Owner]
```