# US002 - Register a new job

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                         | Question: Which class is responsible for...                            | Answer                | Justification (with patterns)                                                                                 |
|:-------------------------------------------------------|:-----------------------------------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1: Asks to register a new job		                   | ... interacting with the actor?                                        | RegisterJobUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		                                                | ... coordinating the US?                                               | RegisterJobController | Controller                                                                                                    |
| Step 2: Request data (job name)  		                    | 		                                                                     | RegisterJobUI         | Pure Fabrication: User will insert data on the User Interface.                                                |
| Step 3: Types requested data  		                       | 	...validating input data?                                             | RegisterJobUI         | IE: object created in step 1 has its own data.                                                                |
|                                                        | ...temporarily keeping input data?                                     | RegisterJobUI         | Pure Fabrication                                                                                              |
| Step 4: Shows typed data and requests confirmation  		 | ... displaying all the information before confirmation?						          | RegisterJobUI         | Pure Fabrication                                                                                              |              
| Step 5: Confirms data and submits  		                  | 	... creating the Skill object                                         | JobRepository         | Creator (Rule 1): Repository manage the created items                                                         | 
| 			  		                                                | 	... validating the data locally (mandatory data)?                     | Job                   | Information Expert: Skill constructor validates the data                                                      | 
| 			  		                                                | 	... adding to a collection and globally validating duplicate records? | JobRepositoy          | Information Expert: Skill repository validates data                                                           | 
| Step 6: Displays operation success  		                 | 	... informing operation success?                                      | RegisterJobUI         | Pure Fabrication                                                                                              | 

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

![Sequence Diagram - Full](svg/us002-sequence-diagram.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us002-class-diagram.svg)