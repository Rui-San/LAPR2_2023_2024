# US004 - Assign skills to a collaborator

## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to assign one or more skills to a collaborator.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> Set of skills that enable him to perform on certain tasks, for example, driving vehicles of different types (e.g.
> light, or heavy), operating machines such as backhoes or tractors; tree pruning; application of phytopharmaceuticals.

> Thus, an employee has a main occupation (job) and a set of skills that enable him to perform/take on certain
> tasks/responsibilities.

**From the client clarifications:**

> **Question:** Is there any special characteristics that the collaborator must possess in order to have these skills
> added to them?
>
> **Answer:** No.

> **Question:** Is there a minimum or maximum number of skills?
>
> **Answer:** No.

> **Question:** Can any skill be registered to any collaborator/job? Or should they be associated in categories in
> association with a specific job?
>
> **Answer:** There is no association, it totally depends on the CV of the collaborator.

> **Question:** Should it be possible to add the same skill to a collaborator multiple times?
>
> **Answer:** That does not make sense.

> **Question:** Is there any certification/proof needed to register a skill to a colaborator?
>
> **Answer:** No.

### 1.3. Acceptance Criteria

* **AC1:** One collaborator must be selected before select the skills.
* **AC2:** At least one skill must be selected from the skills list.
* **AC3:** The same skill cannot be added multiple times.

### 1.4. Found out Dependencies

* There is a dependency on "US001 - Register a skill" as there must be at least one registed skill to be assigned to a
  collaborator.
* *There is a dependency on "US003 - Register a collaborator" as there must be at least one registered collaborator
  before assigning skills to him.

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
    * A collaborator
    * Skills

**Output Data:**

* List with the information about the selected collaborator and his skills.
* Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us004-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks


