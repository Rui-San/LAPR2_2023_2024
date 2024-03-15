# US006 - Create a Task 


## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, i want to assign one or more skills to a collaborator

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Set of skills that enable him to perform on certain tasks, for example, driving
vehicles of different types (e.g. light, or heavy), operating machines such as backhoes
or tractors; tree pruning; application of phytopharmaceuticals.

>	 Thus, an employee has a main occupation (job) and a set of skills
that enable him to perform/take on certain tasks/responsibilities 

**From the client clarifications:**

> **Question:** Is there any specific characteristic that the collaborator need to have before assign him any skill?
>
> **Answer:** No

> **Question:** Is there a minimal or maximun number of skills?
>
> **Answer:** No

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The task reference must have at least 5 alphanumeric characters.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US001 - As a HRM, I want to register skills that may
  be appointed to a collaborator." as there must be at least one registed skill to be assigned for a collaborator.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * employee ID 
    * name
    * skill to be assigned
    * date of birth
    * email
    * contact
	
* Selected data:
    * a skill category 

**Output Data:**

* Driving vehicles of different types
* Operating machines
* Tree pruning
* Installation of an irrigation system
* installation of a lighting system
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.