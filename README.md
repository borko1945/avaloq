# Dice rolling simulator
Dice rolling simulator service provides dice rolling simulation. Rolling results are stored and later could be analyzed.

When performing simulation following parameters could be provided:
- dices - the number of the dices used. Default is 3. Valid value is > 0
- rolls - number of throws with the dices. Default is 100. Valid value is > 0
- diceSides - the type of dices used. Default is 6-sided dices. Valid value > 3

Result will contain a list of objects containing:
- dices sum
- times it was rolled during the request

Following query operations on stored data could be performed:
- total number of simulations and total rolls made, grouped by all existing dice number–dice side
  combinations
  
- for a given dice number–dice side combination, return the relative distribution, compared to the total rolls, for all
  the simulations

###Architecture and Decisions
- Gradle based SpringBoot2/Java11 with embedded Tomcat
- Rest communication
- Java validation used for validation
- Lombok is used to limit the boilerplate code
- Embedded in-memory DB(H2) is used for configuration simplicity, no DB specific queries are used inside this project
- Modification and query operations are separated into different packages/controllers/services/repositories
- Dtos and Models are separated
- A JPQL query is used for performance purposes
- Tests for critical scenarios and components are provided

###Assumptions
- assuming results(possible dices sum) per simulation are few but not a lot. If a lot, more performant hibernate relations and technics could be used
- distribution value is formatted and rounded like in the assessment(Ex: 3.666612 -> 3.66)
- when performing simulation default values will be used if not provided - check above in the document 