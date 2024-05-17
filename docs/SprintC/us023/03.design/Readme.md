## US023 - Assign a Team to an Entry in the Agenda

### 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for... | Answer                   | Justification (with patterns)                                                                                 |
|:-------------  |:--------------------- |:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 |	... interacting with the actor? | AssignTeamUI             | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 |	... coordinating the US? | AssignTeamController     | Controller                                                                                                    |
| 			  		 |	... assigning the team to the entry? | Agenda                 | IE: The Agenda knows its entries and is responsible for managing them.                                        |
| Step 2  		 |	... sending assignment notification to team members? | EmailServiceFactory | IE: Responsible for creating an appropriate email service as per configuration.                             |
|                 |                              | EmailService            | IE: Responsible for sending emails to team members.                                                           |
| Step 3  		 |	... informing operation success? | AssignTeamUI            | IE: is responsible for user interactions.                                                                     |

### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

* Agenda
* Team

Other software classes (i.e., Pure Fabrication) identified:

* AssignTeamUI
* AssignTeamController
* EmailServiceFactory
* EmailService


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Get Task Category List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us006-sequence-diagram-partial-get-task-category-list.svg)

**Get Task Category Object**

![Sequence Diagram - Partial - Get Task Category Object](svg/us006-sequence-diagram-partial-get-task-category.svg)

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us006-sequence-diagram-partial-get-employee.svg)

**Create Task**

![Sequence Diagram - Partial - Create Task](svg/us006-sequence-diagram-partial-create-task.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)