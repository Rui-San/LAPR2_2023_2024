# US006 - Create a Task 


## 1. Requirements Engineering

### 1.1. User Story Description

As an FM, I want to list the vehicles needing the check-up.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	As for machines, MS has tractors, backhoe loaders and rotating machines, lawnmowers, among others. 

>	This type of vehicle can be only for passengers or mixed, light or heavy, open box or closed vans or trucks. 

**From the client clarifications:**

> **Question:** Can the vehicles get placed automatically on a list or the one listing has to be the FM?
>
> **Answer:** 

> **Question:** 
>
> **Answer:** 

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The task reference must have at least 5 alphanumeric characters.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US007 - As an FM, I wish to register a vehicle’s check-up" as there must be at least one registed vehicle to check-up for presenting the list of vehicles.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * brand
    * model
    * plate number
    * number of kms
    * frequency of checkup
    * last checkup
	
* Selected data:
    * a task category 

**Output Data:**

* List the vehicles needing the check-up
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us008-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.