# US006 - Register a Vehicle

## 6. Design - User Story Realization

### 6.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                                                                                                                         | Question: Which class is responsible for...           | Answer                    | Justification (with patterns) |
|:-------------------------------------------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------|:--------------------------|:------------------------------|
| Step 1: Asks to create a new vehicle	                                                                                                                  | 	... interacting with the actor?                      | RegisterVehicleUI         | Pure Fabrication              |
| 			  		                                                                                                                                                | 	... coordinating the US?                             | RegisterVehicleController | Controller                    |
| Step 2: Requests vehicle data (plate id, brand, model, type,\ntare, gross weight, current km, register date,\nacquisition date, checkup frequency kms) | 	... displaying the form for the actor to input data? | RegisterVehicleUI         | Pure Fabrication              |
| Step 3: Types requested data	                                                                                                                          | 	...validating the inputted data?                     | RegisterVehicleUI         | Pure Fabrication              |
|                                                                                                                                                        | ...saving the inputted data?                          | RegisterVehicleUI         | Pure Fabrication              |
| Stop 4: Shows all data and request confirmation                                                                                                        | ..show the user all the data                          | RegisterVehicleUI         | Pure Fiction                  
| Step 5: Confirms data and submits    	                                                                                                                 | 	... creating vehicle object                          | VehicleRepository         | Information Expert/Pure Fabrication - VehicleRepository contains all instances of Vehicle                       | 
| 			  		                                                                                                                                                | 	... validating all data (mandatory data)?            | Vehicle                   | Information Expert            | 
| 	  		                                                                                                                                                  | 	... adding to a collection and globally validating duplicate records?                      | VehicleRepository         | Pure Creation/Information Expert: VehicleRepository aggregates Vehicle instances and validates duplicate records            | 
| Step 6: Displays operation success  		                                                                                                                 | 	... informing operation success?                     | RegisterVehicleUI         | Information Expert            |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Vehicle

Other software classes (i.e. Pure Fabrication) identified:

* RegisterVehicleUI
* RegisterVehicleController
* VehicleRepository

## 6.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![System Sequence Diagram](svg/us006-sequence-diagram.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)