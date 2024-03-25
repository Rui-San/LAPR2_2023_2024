# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

- The application is to support the management of green spaces in urban contexts.
- 

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

- The software must implement unit tests.
- The application language must be in English.

## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

(fill in here )

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

(fill in here )

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

- The application must support English language.
- Implementation must follow a TDD (Test-Driven Development) approach.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- The application must be developed in Java language.
- The application must be developed using either Intellij or NetBeans IDE.
- The application's graphical interface is to be developed in JavaFX 11.
- During software development, Javadoc must be used to generate useful documentation for Java code.
- The application should use object serialization to ensure data persistence between two runs of the application.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- During the system development, the team must adopt best practices for
  identifying requirements, and for OO software analysis and design;
- During the system development, the team must adopt recognized coding standards (e.g., CamelCase)
- The JaCoCo plugin should be used to generate the coverage report.


### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

- All the images/figures produced during the software development process should
  be recorded in SVG format.

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here )