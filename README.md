# Developer Assessment

##Description
The assessment comes with 3 JSON files. The files contain data for products, orders and payments.

## Assumptions
 - Amount due is negative for few users (where the amount paid is more than ordered amount) considered as normal scneario, so no error thrown for this case.
 - Size in a order doesn't exist in product - Ignore the order and write it in log
 - User in order json not exists in payment json - User yet to made payment and the total ordered amount will be considered as due
 - User in payment json not exists in order json - User made payment but order not gone through, log it and set the amount due in negative

## Rest Api :
- Return User payment details including details :
    - Username
    - amount paid per user
    - Amount that each users still owes.
    
## Technology Stack Used :
  - Java 8
  - Spring Boot
  - Junit 
  - Swagger
  
 
