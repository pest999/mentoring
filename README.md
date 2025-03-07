# Product Pricing Service

In the company’s e-commerce database, there is an **OFFER** table that reflects the final price (retail price) and the rate applied to a product from a brand between certain dates.

#### Below is an example of a table with the most relevant fields:

| OFFER_ID | BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PARTNUMBER | PRIORITY | PRICE | CURR |
|----------|----------|---------------------|---------------------|------------|------------|----------|-------|------|
| 234      | 1        | '2020-06-14T00:00.00Z' | '2020-12-31T23:59.59Z' | 1          | '0001002'  | 0        | 35.50 | 'EUR' |
| 123      | 1        | '2020-06-14T15:00.00Z' | '2020-06-14T18:30.00Z' | 2          | '0001002'  | 1        | 25.45 | 'EUR' |
| 678      | 1        | '2020-06-15T00:00.00Z' | '2020-06-15T11:00.00Z' | 3          | '0001002'  | 1        | 30.50 | 'EUR' |
| 987      | 1        | '2020-06-15T16:00.00Z' | '2020-12-31T23:59.59Z' | 4          | '0001002'  | 4        | 38.95 | 'EUR' |

### **Fields:**
- **BRAND_ID**: Foreign key of the brand group (e.g., 1 = Zara).
- **START_DATE, END_DATE**: Date range in which the indicated price rate applies. On the **END_DATE**, the price NO LONGER APPLIES (i.e., it would apply until 1 second before the **END_DATE**). The dates are expressed in UTC date time ISO8601.
- **PRICE_LIST**: Identifier of the applicable price rate.
- **PARTNUMBER**: Product code identifier.
- **PRIORITY**: Disambiguation of price application. If two tariffs coincide in a date range, the one with the highest priority (highest numeric value) is applied.
- **PRICE**: Final sale price.
- **CURR**: Currency ISO code.

---

## Task Description

Complete the proposed **SpringBoot** service so that all endpoints of the **OfferController** class are implemented:

- **(POST) /offer** — Creates a new offer.
- **(DELETE) /offer** — Deletes all the offers from the database.
- **(DELETE) /offer/{offerId}** — Deletes a concrete offer from the database by **offerId**.
- **(GET) /offer** — Get all the offers from the database.
- **(GET) /offer/{offerId}** — Get a concrete offer by **offerId**.
- **(GET) /brand/{brandId}/partNumber/{partNumber}** — Get the timetable for a concrete product by **brandId** and **partNumber**.

### Special attention to the "timetable" operation for a specific product:

This operation must "flatten" the intervals so that start-end tuples are not overlapped and the correct prices are applied in each one.

- According to the task data, the result should be similar to the one indicated below.
- Use the POJO **OfferByPartnumber** to return the result.

### Example of timetable result for a specific product:
From "2020-06-14T00:00.00Z" until "2020-06-14T18:28.59Z" price: 35.50
From "2020-06-14T18:30.00Z" until "2020-12-31T23:59.59Z" price: 25.45
From "2020-06-15T00:00.00Z" until "2020-06-15T11:59.59Z" price: 30.50
From "2020-06-15T16:00.00Z" until "2020-12-31T23:59.59Z" price: 38.95


---

### Additional Conditions:

- If **START_DATE** and **END_DATE** overlap for several tariffs, the one with the highest **PRIORITY** (the higher numeric value) should be applied.
- The logic ensures that the date ranges do not overlap, and each price has the correct duration.
---

## Java Concepts & Practices to Apply

### 1. **Optional, Null Safety, and Exception Handling:**
    - **Optional**: You should use `Optional` to handle missing offers or other data without introducing `NullPointerException`. For example:
        - Use `Optional.ofNullable()` when retrieving an offer by ID.
        - Use `Optional.orElseThrow()` when the offer is not found, to throw a custom exception (like `OfferNotFoundException`).
    - **Exception Handling**: Implement custom exceptions for scenarios such as:
        - `OfferNotFoundException`: Thrown when the requested offer is not found.
        - `InvalidDateRangeException`: Thrown when **START_DATE** and **END_DATE** are invalid or overlapping improperly.
    - Use **try-catch-finally** to ensure smooth error handling in your service methods.

### 2. **Stream API and Functional Style:**
    - Use **Stream API** to filter, map, and process offers. For instance:
        - `offers.stream().filter(o -> o.getStartDate().isBefore(LocalDate.now()))` to find offers that started before the current date.
        - `offers.stream().sorted(Comparator.comparing(Offer::getPriority))` to sort offers based on **PRIORITY**.
    - Use methods like **map()**, **collect()**, **filter()**, **flatMap()**, etc., for efficient data manipulation.

### 3. **Lambda Expressions and Functional Interfaces:**
    - Use **Lambda Expressions** for concise and readable code, especially for operations like sorting, filtering, and applying transformations to offer data.
    - Work with **Functional Interfaces** like `Predicate`, `Consumer`, and `Function` for flexible handling of collections and offer data.

### 4. **OOP Concepts (Abstraction, Inheritance, Polymorphism):**
    - Use **Abstraction** to isolate business logic (e.g., separating offer validation and offer creation into different classes).
    - **Inheritance** can be used if you need to extend certain behavior across different types of offers.
    - **Polymorphism** will help in implementing different pricing strategies, depending on **PRICE_LIST** or **BRAND_ID**.

### 5. **SOLID Principles:**
    - Apply the **SOLID** principles to the OfferService and OfferController to keep your code maintainable:
        - **Single Responsibility Principle**: Split the offer processing logic from the controller logic.
        - **Open/Closed Principle**: Ensure your classes are open for extension (e.g., by adding new offer types) but closed for modification.
        - **Liskov Substitution Principle**: Ensure any derived classes or interfaces can be substituted for the base ones without altering functionality.
        - **Interface Segregation**: Separate the operations for creating and retrieving offers into smaller, more specific interfaces.
        - **Dependency Inversion**: Use Dependency Injection for services and repositories to keep the classes loosely coupled.

### 6. **Interfaces and Their Application in Tests:**
    - Use **Interfaces** for defining the offer processing operations. For instance, `OfferRepository` interface for accessing offer data from the database.
    - This helps in creating mock services for testing.

### 7. **Collections API:**
    - Understand the differences between `List`, `Set`, `Map`, and `Queue` collections, and use them appropriately for managing offers and their properties.
    - For example, you can use `Map<String, Offer>` for fast lookups by offer ID.

### 8. **Unit Testing and Mocking:**
    - Use **JUnit** and **Mockito** to write unit tests for the methods handling offer creation, deletion, retrieval, and timetable generation.
    - Mock external dependencies like the database or other services to isolate the offer logic for testing.

### 9. **SQL and Integration with Java:**
    - Use **JDBC** or **ORM (e.g., Hibernate)** to interact with the **OFFER** database.
    - Write SQL queries to retrieve offers, filter by date range, and apply the correct pricing logic.

---



### Objective of the Task:

This task focuses on implementing a **Product Pricing Service** in **Spring Boot** that handles multiple price intervals, prioritization, and correct price assignment based on time ranges. The key challenge is to manage overlapping price rates, apply the highest priority, and return the correct pricing information based on the specified period.
---
This task involves building a Spring Boot service that manages pricing data, including handling price ranges and applying the correct prices for products based on the given date ranges. The service should provide an API to create, read, update, and delete pricing data, as well as return the timetable for a given product considering the price priority and correct application of the date ranges.