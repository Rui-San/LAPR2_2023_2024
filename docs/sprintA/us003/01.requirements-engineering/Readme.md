# US003 - Register a collaborator


## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to register a collaborator with a job and fundamental characteristics
### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**


MS has a wide range of employees who carry out the most varied tasks in the context
of managing green spaces. Some job examples are designer, estimator, gardener, electrician or bricklayer. Thus, an employee has a main occupation (job) and a set of skills
that enable him to perform/take on certain tasks/responsibilities, for example, driving
vehicles of different types (e.g. light, or heavy), operating machines such as backhoes
or tractors; tree pruning; application of phytopharmaceuticals.
Tasks are carried out on an occasional or regular basis in one or more green spaces,
for example: tree pruning; installation of an irrigation system; installation of a lighting
system.
Teams are temporary associations of employees who will carry out a set of tasks in
one or more green spaces. When creating multipurpose teams, the number of members
and the set of skills that must be covered are crucial.


Human Resources Manager (HRM) - a person who manages human resources
and defines teams based on the needs of ongoing projects and the skills of the
employees.
• Fleet Manager (FM) – a person who manages the fleet park, the machines, equipment and vehicles, ensuring their good condition and assigning them to the tasks
to be carried out.
• Collaborator – a person who is an employee of the organization and carries out
design, construction and/or maintenance tasks for green areas, depending on their
skills.
• Green Spaces Manager (GSM) - the person responsible for managing the green
spaces in charge of the organization.
• Green Spaces User (GSU) - a person who uses the green spaces managed by the
organization and who can through the Portal, make comments or report faults in
parks and gardens on the Portal.


**From the client clarifications:**

> **Question** When creating a collaborator with an existing name ... What does the system do? What characteristics are important to success the register?
> 
> **Answer:** It's not common and most improbable to have different individuals with same name in the same context, however it’s ID documentation number should be unique for sure.
>
> **Question** What are the fundamental characteristics of the employee?
> 
> **Answer** name, date of birth, date of admission, address, contact (telephone and email), identification document and its number
>
> **Question** Does the HRM select the job from a list that we display?
> 
> **Answer** displaying or not, It's a matter of UX, the dev team should decide about it, but the valid jobs are the ones created within the US02.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** ID documentation number must be unique
* **AC3:** ID number must only contain numbers
* **AC4:** Date of birth must be valid
* **AC5:** Date of admission must not be earlier than date of birth
* **AC6:** Name must contain only valid characters

### 1.4. Found out Dependencies

* There is a dependency on "US001 - Register a skill" as if the collaborator has at least one skill, it has to exist before
* There is a dependency on "US002 - Create a job" as the jobs need to exist to be assigned to the collaborator

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * name
    * date of birth
    * date of admission
    * address
    * contact (email and telephone)
    * ID doc type
    * ID number
	
* Selected data:
    * job
    * skill (optional)

**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* If the date of admission exceeds the current date, a warning must appear