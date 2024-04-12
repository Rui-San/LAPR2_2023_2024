# US006 - Register a vehicle

## 6. Design - User Story Realization

### 6.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                                                     | Question: Which class is responsible for...                  | Answer                | Justification (with patterns)                                                                                 |
|:-----------------------------------------------------------------------------------|:-------------------------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1: Asks to assign one or more skills to a collaborator  		                    | 	... interacting with the actor?                             | AssignSkillUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		                                                                            | 	... coordinating the US?                                    | AssignSkillController | Controller                                                                                                    |
| 			  		                                                                            | 	... obtaining the list of collaborators?                    | Organization          | Information Expert: knows its own collaborators.                                                              |
|                                                                                    | ... obtaining the list of skills?                            | Organization          | Information Expert: Organization knows/has its own skills.                                                    |
| 			  		                                                                            | ... knowing the user using the system?                       | UserSession           | IE: cf. A&A component documentation.                                                                          |
| Step 2: Shows a list of Collaborators and asks to select one  		                   | ... displaying the list of collaborators?							             | AssignSkillUI         | Pure Fabrication                                                                                              |
| Step 3: Selects a collaborator  		                                                 | 	... validating selected data?                               | AssignSkillUI         | Pure Fabrication                                                                                              |
|                                                                                    | ... temporarily keeping the selected collaborator?           | AssignSkillUI         | Pure Fabrication                                                                                              |
| Step 4: Displays a list of available skills and asks to select one.  		            | 	... displaying the list of skills?                          | AssignSkillUI         | Pure Fabrication                                                                                              |
| Step 5: Selects a skill  		                                                        | 	... validating selected data?                               | AssignSkillUI         | Pure Fabrication                                                                                              |
|                                                                                    | ... temporarily keeping the selected skill?                  | AssignSkillUI         | Pure Fabrication                                                                                              |                                                                                              
| Step 6: Shows all data (collaborator and his skills) and requests confirmation  		 | 	... displaying all the information before submitting?						 | AssignSkillUI         | Pure Fabrication                                                                                              |              
| Step 7: Confirms the operation  		                                                 | 	... saving the selected skills (local validation)?          | "a resolver"          | "a resolver"                                                                                                  |
| Step 8: Displays operation success  		                                             | 	... informing operation success?                            | AssignSkillUI         | Pure Fabrication                                                                                              | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Organization


Other software classes (i.e. Pure Fabrication) identified:

* AssignSkillUI
* AssignSkillController

## 6.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this
user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

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