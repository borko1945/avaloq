# Dice rolling simulator

###Decisions and assumptions
- assuming results(possible dices sum) per simulation are few but not a lot. If a lot, more performant hibernate relations and technics could be used
- embedded DB(H2) is used for configuration simplicity, no DB specific queries are used inside this project
- distribution value is formatted and rounded like in the assessment(Ex: 3.666612 -> 3.66)