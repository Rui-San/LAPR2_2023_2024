# US007 - Register a vehicle’s check-up

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                            | Question: Which class is responsible for...                           | Answer                   | Justification (with patterns) |
|:----------------------------------------------------------|:----------------------------------------------------------------------|:-------------------------|:------------------------------|
| Step 1: Asks to register a new vehicle's check-up         | ... instantiating the class that handles the UI?                      | CreateTaskUI             | Pure Fabrication              |
|                                                           | ... obtaining the vehicle checkup list?                               | Organization             | Information Expert            |  
| Step 2: Shows the list of vehicles and asks to select one | ... displaying the Vehicle list?                                      | CreateTaskUI             | Pure Fabrication              |
| Step 3: Selects a vehicle                                 | ... validating selected data?                                         | CreateTaskUI             | Pure Fabrication              |
|                                                           | ... temporarily keeping the Vehicle?                                  | CreateTaskUI             | Pure Fabrication              |
| Step 4: Requests check-up data                            | ... displaying the form for the actor to input data?                  | CreateTaskUI             | Pure Fabrication              |
| Step 5: Types requested data                              | ... validating input data?                                            | CreateTaskUI             | Pure Fabrication              |
|                                                           | ... temporarily keeping input data?                                   | CreateTaskUI             | Pure Fabrication              |
| Step 6: Shows all data and requests confirmation          | ... displaying all the information before submitting?                 | CreateTaskUI             | Pure Fabrication              |
| Step 7: Confirms data                                     | ... creating the Vehicle Checkup object?                              | VehicleCheckupRepository | Pure Fabrication              |
|                                                           | ... validating the data locally (mandatory data)?                     | VehicleCheckupRepository | Pure Fabrication              |
|                                                           | ... adding to a collection and globally validating duplicate records? | VehicleCheckupRepository | Pure Fabrication              | 
| Setp 8: Displays operation success                        | ... informing operation success?                                      | CreateTaskUI             | Pure Fabrication              |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Organization
* Task

Other software classes (i.e. Pure Fabrication) identified:

* CreateTaskUI
* CreateTaskController

## 3.2. Sequence Diagram (SD)

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