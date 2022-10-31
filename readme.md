# Ranks for BTA!  
commands:  
```mermaid
graph TD;
    /rank-->give;
    give-->(rank1);
    (rank1)-->(user);
    /rank-->prefix;
    prefix-->(rank2);
    (rank2)-->(prefix)
```


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