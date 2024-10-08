# US020 - Register a greenspace

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID                                                                            | Question: Which class is responsible for...                           | Answer                       | Justification (with patterns)                                                                                 |
|:------------------------------------------------------------------------------------------|:----------------------------------------------------------------------|:-----------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1: Asks to create a new Green Space 		                                               | 	... interacting with the actor?                                      | RegisterGreenSpaceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		                                                                                   | 	... coordinating the US?                                             | RegisterGreenSpaceController | Controller                                                                                                    |
| 			  		                                                                                   | ... knowing the user using the system?                                | UserSession                  | IE: cf. A&A component documentation.                                                                          |
| Step 2: Displays the types of green spaces and asks to select one (garden, medium, large) | ... displaying the list of green space types                          | RegisterGreenSpaceUI         | Pure Fabrication                                                                                              |
| Step 3: Selects type of green space                                                       | ... validating the selected data?                                     | RegisterGreenSpaceUI         | Pure Fabrication                                                                                              |
|                                                                                           | ... temporarily keeping the selected greenSpace?                      | RegisterGreenSpaceUI         | Pure Fabrication                                                                                              |
| Step 4: requests data (name, area, street, streetNumber, postalCode, city, district) 		   | 	... displaying the form for the actor to input data?						           | RegisterGreenSpaceUI         | Pure Fabrication: User will insert data on the User Interface.                                                |
| Step 5: Types requested data  		                                                          | 	... validating input data?                                           | RegisterGreenSpaceUI         | Pure Fabrication                                                                                              |
|                                                                                           | ... temporarily keeping input data?                                   | RegisterGreenSpaceUI         | Pure Fabrication                                                                                              |
| Step 6: Shows all data and requests confirmation  		                                      | ... displaying all the information before confirmation?							        | RegisterGreenSpaceUI         | PureFabrication                                                                                               |              
| Step 7: Confirms data  		                                                                 | 	... creating the Green Space DTO object?                             | RegisterGreenSpaceUI         | Pure Fabrication: All input data will be sent to the Domain in a DTO (transferring data only)                 | 
|                                                                                           | ... mapping a DTO to a domain green space object?                     | GreenSpaceMapper             | Pure Fabrication: GreenSpaceMapper has the responsibility of converting a DTO object into a domain object.    |
| 			  		                                                                                   | 	... validating the data locally (mandatory data)?                    | GreenSpace                   | Information Expert: GreenSpace constructor validates data                                                     |                                                                                    | 
|                                                                                           | ... adding to a collection and globally validating duplicate records? | GreenSpaceRepository         | Information Expert: GreenSpaceRepository aggregates GreenSpace instances and validates duplicate records      |
| Step 8: Displays operation success  		                                                    | 	... informing operation success?                                     | RegisterGreenSpaceUI         | Pure Fabrication                                                                                              | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* GreenSpace

Other software classes (i.e. Pure Fabrication) identified:

* RegisterGreenSpaceUI
* RegisterGreenSpaceController
* GreenSpaceRepository
* GreenSpaceMapper
* GreenSpaceDTO
* UserSession

## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us020-sequence-diagram.svg)

### Ref: SD_toGreenSpaceDomainObject

![Sequence Diagram - SD_toGreenSpaceDomainObject](svg/SD_toGreenSpaceDomainObject.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us020-class-diagram.svg)

### RegisterGreenSpaceUI Class Diagram

![RegisterGreenSpaceUI Class Diagram](svg/us020-class-diagram-register-greenspace-ui.svg)

### Address Class Diagram

![Class Diagram](svg/us020-class-diagram-address.svg)