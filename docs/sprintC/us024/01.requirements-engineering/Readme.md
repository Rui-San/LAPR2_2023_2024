# US006 - Postpone task 


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to Postpone an entry in the Agenda to a specific future date.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The Agenda is made up of entries that relate to a task (which was previously in the To-Do List), 
> the team that will carry out the task, the vehicles/equipment assigned to the task, 
> expected duration, and the status (Planned, Postponed, Canceled, Done).

**From the client clarifications:**

> **Question:** 
>
> **Answer:** 

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.

### 1.4. Found out Dependencies

* There is a dependency on "US021 - Add new entry in the To-Do List" as the task must have first been created in the To-Do List before it can be added to the agenda.
* There is a dependency on "US022 - Add new entry in the Agenda" as the task must have already been added to the agenda before it can be assigned to a team.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * n/a
	
* Selected data:
    * a task from the Agenda

**Output Data:**

* Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us024-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

* n/a