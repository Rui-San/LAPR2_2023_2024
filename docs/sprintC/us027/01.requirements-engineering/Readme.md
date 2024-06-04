# US027 - List green spaces

## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I need to list all green spaces managed by me.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Green spaces for collective use can vary significantly in dimensions and available amenities.
 
**From the client clarifications:**

> **Question:** Which info about Green Spaces do you want the GSM see when listing? only the name ?
>
> **Answer:** Each de team can decide about the aspects related to UX/UI

> **Question:** You only want to list the Green Spaces manage by the GSM. Due to this, the GSM should be registered in the app previously, isn't it? Which attributes should it have? A  GSM is a collaborator?
> 
> **Answer:** Yes; the GSM (you can have many) should be registered in the app. GSM is a role that can be played a registered user with the appropriate privileges.
The app can have multiple GSM registered (for instance that can be done during using the boostrap)

### 1.3. Acceptance Criteria

* **AC1:** The list of green spaces must be sorted by size in descending order. The sorting algorithm to be used by the application
  must be defined through a configuration file. At least two sorting algorithms should be available.
* **AC2:** The green spaces listed are the ones managed by the GSM logged in the system.

### 1.4. Found out Dependencies

* There is a dependency on "US020 - Register a green space" as there must be at least one green space registered in the system to be listed.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * n/a
* Selected data:
    * n/a 

**Output Data:**

* List of green spaces managed by the GSM.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us027-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

* n/a