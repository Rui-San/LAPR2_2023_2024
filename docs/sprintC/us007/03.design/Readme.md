# US007 - Register a vehicle’s check-up

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                            | Question: Which class is responsible for...                           | Answer                    | Justification (with patterns)                                                                    |
|:----------------------------------------------------------|:----------------------------------------------------------------------|:--------------------------|:-------------------------------------------------------------------------------------------------|
| Step 1: Asks to register a new vehicle's check-up         | ... interacting with the actor                                        | RegisterCheckupUI         | Pure Fabrication                                                                                 |
|                                                           | ... coordinating the US                                               | RegisterCheckupController | Controller                                                                                       |
| Step 2: Shows the list of vehicles and asks to select one | ... obtaining the vehicle list?                                       | VehicleRepository         | Information Expert, it knows all the vehicles in the system                                      |  
|                                                           | ... displaying the Vehicle list?                                      | RegisterCheckupUI         | Pure Fabrication                                                                                 |
| Step 3: Selects a vehicle                                 | ... validating selected data?                                         | RegisterCheckupUI         | Pure Fabrication                                                                                 |
|                                                           | ... temporarily keeping the Vehicle?                                  | RegisterCheckupUI         | Pure Fabrication                                                                                 |
| Step 4: Requests check-up data                            | ... displaying the form for the actor to input data?                  | RegisterCheckupUI         | Pure Fabrication                                                                                 |
| Step 5: Types requested data                              | ... validating input data?                                            | RegisterCheckupUI         | Pure Fabrication                                                                                 |
|                                                           | ... temporarily keeping input data?                                   | RegisterCheckupUI         | Pure Fabrication                                                                                 |
| Step 6: Shows all data and requests confirmation          | ... displaying all the information before submitting?                 | RegisterCheckupUI         | Pure Fabrication                                                                                 |
| Step 7: Confirms data                                     | ... creating the Vehicle Checkup object?                              | VehicleCheckupRepository  | Information Expert/Pure Fabrication - VehicleCheckupRepository contains all instances of Vehicle |
|                                                           | ... validating the data locally (mandatory data)?                     | VehicleCheckup            | Information Expert                                                                               |
|                                                           | ... adding to a collection and globally validating duplicate records? | VehicleCheckupRepository  | Information Expert                                                                               | 
| Setp 8: Displays operation success                        | ... informing operation success?                                      | RegisterCheckupUI         | Pure Fabrication                                                                                 |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* VehicleCheckup

Other software classes (i.e. Pure Fabrication) identified:

* VehicleRepository
* RegisterCheckupUI
* VehicleCheckupRepository
* RegisterCheckupController

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us007-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us007-class-diagram.svg)