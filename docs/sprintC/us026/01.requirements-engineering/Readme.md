# US026 - Assign one or more vehicles to an entry in the Agenda


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to assign one or more vehicles to an entry in the Agenda.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Vehicles are needed to carry out the tasks assigned to the teams and to
transport machines and equipment.

> The Agenda is made
up of entries that relate to a task (which was previously in the To-Do List),
the team that will carry out the task, the vehicles/equipment assigned to
the task ...

**From the client clarifications:**

> **Question:** 
>
> **Answer:** 

> **Question:** 
>
> **Answer:** 
 
### 1.3. Acceptance Criteria

* **AC1:**  The entry of Agenda must be selected.
* **AC2:** At least one vehicle must be selected in order to show operation success.


### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register Vehicle" as there must be at least one vehicle in order to be assigned to a task.
* There is a dependency on "US022 - Add new entry in the Agenda" as there need to be at least one entry to assign a vehicle to.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * There is no typed data
	
* Selected data:
    * an entry from the Agenda 
    * one or more vehicles

**Output Data:**

* Shows data for confirmation
* Success of the operation

### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram](svg/us026-system-sequence-diagram.svg)



### 1.7 Other Relevant Remarks

* There is no other relevant remarks.