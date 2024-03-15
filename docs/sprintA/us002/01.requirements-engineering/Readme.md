# US002 - Register a Job 


## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to register a job.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> MS has a wide range of employees who carry out the most varied tasks in the context
of managing green spaces. Some job examples are designer, estimator, gardener, electrician 
or bricklayer. Thus, an employee has a main occupation (job) and a set of skills
that enable him to perform/take on certain tasks/responsibilities, for example, driving
vehicles of different types (e.g. light, or heavy), operating machines such as backhoes
or tractors; tree pruning; application of phytopharmaceuticals.

**From the client clarifications:**

> **Question:** What are the required fields for registering a job?
>
> **Answer:** ---

> **Question:** When the user registers a job does he select a job from a list or does he type out a custom job name?
>
> **Answer:** ---

> **Question:** What data is introduced by the user when creating a job?
>
> **Answer:** ---

> **Question:** What happens when the user tries to create a job with an existing reference?
> 
> **Answer:** ---

> **Question:** What are the valid inputs for each field? For example, are there any restrictions on the format or length of the job reference, designation, or descriptions?
> 
> **Answer:** --- 

> **Question:** Are there any default values for any of the fields?
> 
> **Answer:** ---

> **Question:** Is there a limit to the number of jobs that can be registered by a user or within the organization?
> 
> **Answer:** ---

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** When creating a job with an existing reference, the system must inform the user that the reference already exists.

### 1.4. Found out Dependencies

* There are no dependencies for this user story.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * job reference
    * job name
    * informal description
    * technical description

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us002-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* There are no other relevant remarks for this user story.