# US001 - Register a skill


## 1. Requirements Engineering

### 1.1. User Story Description

As a Human Resources Manager (HRM), I want to register skills that may
be appointed to a collaborator.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	... an employee has a main occupation (job) and a set of skills
that enable him to perform/take on certain tasks/responsibilities, for example, driving
vehicles of different types (e.g. light, or heavy), operating machines such as backhoes
or tractors; tree pruning; application of phytopharmaceuticals. 

>	When creating multipurpose teams, the number of members
and the set of skills that must be covered are crucial. 

**From the client clarifications:**

> **Question:** What criteria are necessary to register a skill?
>
> **Answer:** o nome da skill, por exemplo: podador, condutor de veiculos pesados, aplicador de fito-farmacos

> **Question:** When a skill that already exists is created, what should the system do?
>
> **Answer:** By definition, it's not possible to have duplicate values in a set. Checking for duplicates isn't a business rule; it's at the technological level.


### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** When registering a new skill with an existing reference, the system must inform the user that the reference already exists.

### 1.4. Found out Dependencies

* There is a dependency on "US003 - Create a task category" as there must be at least one task category to classify the task being created.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Skill description
	
* Selected data:
    * a task category 

**Output Data:**

* List of all Skills
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.