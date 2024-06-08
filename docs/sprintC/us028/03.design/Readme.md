# US028 - Create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for... | Answer               | Justification (with patterns)                                                                                 |
|:-------------  |:--------------------- |:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 |	... interacting with the actor? | ListCollaboratorTasksUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 |	... coordinating the US? | ListCollaboratorTasksController | Controller: Responsible for handling the input from the UI and coordinating the tasks.                       |
| 			  		 |	... instantiating a new Task? | Task         | Creator: Task is the domain object that needs to be instantiated and manipulated.                            |
| 			  		 | ... knowing the user using the system?  | UserSession          | Information Expert: Maintains details about the current user session.                                         |
| Step 2  		 |	... retrieving the collaborator-specific agenda? | AgendaRepository                 | Information Expert: Responsible for fetching data related to the agenda from the repository.                  |
| Step 3  		 |	... getting the current application session? | ApplicationSession    | Information Expert: Manages the current session information.                                                  |
| Step 4  		 |	... getting the current user session? | UserSession           | Information Expert: Provides the details of the current logged-in user.                                        |
| Step 5  		 |	... retrieving the user ID? | Email                  | Information Expert: Responsible for user identification through email.                                         |
| Step 6  		 |	... converting the tasks to DTOs? | AgendaMapper          | Information Expert: Converts domain objects to Data Transfer Objects.                                          |
| Step 7  		 |	... knowing the details of each task? | Task                 | Information Expert: Owns its data and operations related to it.                                                |
| Step 8  		 |	... knowing the work periods of tasks? | Workperiod           | Information Expert: Manages the work period details for tasks.                                                 |
| Step 9  		 |	... checking for overlapping work periods? | Workperiod           | Information Expert: Has the information necessary to determine overlaps.                                       |
| Step 10  		 |	... adding tasks to the filtered list? | ListCollaboratorTasksController | Controller: Manages the flow and coordination of task filtering.                                              |
| Step 11  		 |	... displaying the collaborator's specific agenda? | ListCollaboratorTasksUI          | Pure Fabrication: Manages UI rendering and user interactions.                                                 |

### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

- **Organization**
- **Task**
- **UserSession**
- **ApplicationSession**
- **Workperiod**
- **Email**

Other software classes (i.e., Pure Fabrication) identified:

- **ListCollaboratorTasksUI**
- **ListCollaboratorTasksController**
- **AgendaRepository**
- **AgendaMapper**

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