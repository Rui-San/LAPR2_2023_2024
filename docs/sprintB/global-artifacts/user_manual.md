# User Manual

# Green Spaces Management App

### Instituto Superior de Engenharia do Porto

#### Laboratory/Project II - LEI
#### 1DCDD - GROUP 034 – CODEFLOW

- PEDRO COSTA (1221790)
- RUI SANTIAGO (1221402)
- DIÓGENES DE JESUS (1210879)
- ALEXANDRE MOURA (1231193)
- FRANCISCO TROCADO (1230608)

#### **Date:** April 9th, 2024

---

## Table of Contents

- [Glossary](#glossary)
- [Introduction](#introduction)
- [System Overview](#system-overview)
- [System Features](#features)
- [Troubleshooting](#troubleshooting)
- [Frequently Asked Questions](#frequently-asked-questions)
- [References](#references)

---

## Glossary

| **_Terms, Expressions and Acronyms_** | **_Description_**                                                                                                                                      |
|:--------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Agenda**                            | Crucial mechanism for planning the week's work.                                                                                                        |
| **Automatically**                     | Something that is done without human intervention.                                                                                                     |
| **Backlog**                           | A list of tasks or items awaiting completion or further action.                                                                                        |
| **CamelCase**                         | CamelCase is a coding convention where compound words are written without spaces, and each word begins with a capital letter except for the first one. |
| **Check-up**                          | The action of subjecting company vehicles to a periodic inspection.                                                                                    |
| **Collaborator**                      | Employee of the organization with specific skills for executing tasks related to green space management.                                               |
| **Doc**                               | Refers to the identification document.                                                                                                                 |
| **Employee**                          | An employee is an individual who works for the company MusgoSublime.                                                                                   |
| **FM**                                | Fleet Manager.                                                                                                                                         |
| **Green Space**                       | Referes to green spaces in public places (e.g. parks and gardens).                                                                                     |
| **Gross Weight**                      | The total weight of a vehicle, including passengers, cargo, and any other load.                                                                        |
| **GSM**                               | Green Spaces Manager.                                                                                                                                  |
| **GSU**                               | Green Spaces User.                                                                                                                                     |
| **HRM**                               | Human Resources Manager.                                                                                                                               |
| **ID Doc**                            | Number of identification document.                                                                                                                     |
| **IDE**                               | Integrated development environment.                                                                                                                    |
| **IntelliJ IDE**                      | Integrated development environment by JetBrains.                                                                                                       |
| **Item**                              | Items colocados por ordem alfabética                                                                                                                   |
| **JaCoCo**                            | Java library that offers information about the code coverage of Java.                                                                                  |
| **Java**                              | Object-oriented programming language.                                                                                                                  |
| **Javadoc**                           | Automatic documentation generator for Java code, using specially formatted comments.                                                                   |
| **JavaFX 11**                         | The framework used to develop the graphical interface of the application.                                                                              |
| **JUnit 5**                           | Testing framework for Java.                                                                                                                            |
| **MS**                                | MS is an acronym for MusgoSublime.                                                                                                                     |
| **MusgoSublime**                      | MusgoSublime is the organization dedicated to the planning, construction, and maintenance of green spaces for collective use.                          |
| **NetBeans**                          | Integrated development environment by Oracle Corporation.                                                                                              |
| **OO**                                | Refers to Object-Oriented.                                                                                                                             |
| **PVM**                               | Minimal Viable Product.                                                                                                                                |
| **SCRUM**                             | Framework for managing and organizing work.                                                                                                            |
| **Skill**                             | A characteristic of an employee and/or a specific ability to perform a task.                                                                           |
| **SPRINT**                            | A short and fixed period of time (usually 2-4 weeks) in which a team works on a set of tasks to deliver a usable product increment.                    |
| **SVG**                               | Scalable Vector Graphics is an image format used for two-dimensional.                                                                                  |
| **Tare**                              | The weight of a vehicle when empty, excluding any passengers or cargo.                                                                                 |
| **Task**                              | Tasks performed by collaborators.                                                                                                                      |
| **TDD**                               | Test-Driven Development.                                                                                                                               |
| **Team**                              | Group of workers who collaborate as a team to accomplish a task.                                                                                       |
| **User**                              | Individual who can use the application.                                                                                                                |
| **User Portal**                       | Platform where users can post comments, report faults and malfunctions of equipment.                                                                   |
| **VFM**                               | Vehicle and Equipment Fleet Manager.                                                                                                                   |

---

## Introduction

Green spaces are really important for enhancing the quality of life for residents and even visitors. Effectively
managing these areas requires an efficient application that focuses on each of the topics inherent in their management.

This user manual was designed as a guide to help users navigate and comprehend the functionalities of our application
effectively. Its purpose is to provide step-by-step instructions for users to operate the application in the easiest way
and efficiently.

The application is still in its implementation phase, however, it aims to provide a set of functionalities which are
intuitive and easy-to-use for managing public parks and gardens in urban spaces.

The User Manual is divided into several parts, each one addressing a specific aspect of the application. The sections
for this sprint are as listed:

- **Glossary**, which contains a list of terms, expressions and acronyms used throughout the project followed by its
  description.

- **System Overview**, which describes the main functionalities of the application. It includes an overall description of
  the product, including the objectives of the application, its structure, the reference to the main features and the
  representation of the diagram.

- **System Features**, listing the main features of the application. Each feature in this user manual corresponds to a user
  story in software development terminology, it means one “action” the user may perform on the application.

---

## System Overview

The main objective of this Java-built application is to provide a solution that supports the activities of an
organization responsible for managing green spaces, predominantly in urban contexts.

While using this application, users have at their disposal a set of functionalities that streamline the company's
activities, making management more efficient, facilitating planning and coordination of the activities.

Through a user-friendly interface, the system enables managers to register collaborators, jobs, and skills, which allows
the manager to automatically create a team that will execute tasks of an agenda, ensuring greater efficiency and
coordination in the work. Furthermore, the application also facilitates the fleet management, including vehicles,
machinery, and equipment through functionalities that allow the registration of the organization's vehicles, as well as
the management of vehicle check-ups.

The application will also provide a portal where green space users can access information about the green spaces, report
problems and malfunctions or suggest improvements.

To demonstrate more easily how the application works, we have developed a diagram that shows the system's
functionalities and the dependencies that exist between them.


![Domain Model](02.analysis/svg/project-domain-model.svg)

---

## Features

### 1. Register a Skill | Human Resources Manager

Refers to the feature that allows the Human Resources Manager to register a skill that a collaborator might have.

Instructions:

In order to register a skill, the Human Resources Manager must select the option "Register a Skill" from the menu. After that,
the system will ask the user to fill in the skill's information. These fields are:
- Skill name: refers to the skill's name.

![Register a Skill](user_manual_rersources/us001/01.png)

### 2. Register a Job | Human Resources Manager

Refers to the feature that allows the Human Resources Manager to register a job that a collaborator might have.

Instructions:

In order to register a job, the Human Resources Manager must select the option "Register a Job" from the menu. After that,
the system will ask the user to fill in the job's information. These fields are:
- Job name: refers to the job's name.

![Register a Job](user_manual_rersources/us002/01.png)

### 3. Register a Collaborator | Human Resources Manager

Refers to the feature that allows the Human Resources Manager to register a collaborator.

Instructions:

In order to register a collaborator, the Human Resources Manager must select the option "Register a Collaborator" from
the menu. After that, the system will ask the user to fill in the collaborator's information. These fields are:
- Name: refers to the collaborator's name.
- Birthdate: refers to the collaborator's birthdate.
- Admission Date: refers to the collaborator's admission date.
- Address: refers to the collaborator's address.
- Email: refers to the collaborator's email.
- Mobile Number: refers to the collaborator's mobile number.
- ID Doc Type: refers to the collaborator's identification document type.
- ID Doc: refers to the collaborator's identification document.

![Register a Collaborator](user_manual_rersources/us003/01.png)

### 4. Assign skills to a Collaborator | Human Resources Manager

Refers to the feature that allows the Human Resources Manager to assign skills to a collaborator.

Instructions:

In order to assign skills to a collaborator, the Human Resources Manager must select the option "Assign skills to a
Collaborator" from the menu. After that, the system will ask the user to select the collaborator to whom the skills will
be assigned. The system will then display a list of skills that can be assigned to the collaborator. The user must then select
the skills that will be assigned to the collaborator.

![Assign skills to a Collaborator](user_manual_rersources/us004/01.png)

### 5. Generate a team proposal | Human Resources Manager

Refers to the feature that allows the Human Resources Manager to generate a team proposal.

Instructions:

In order to generate a team proposal, the Human Resources Manager must select the option "Generate a team proposal" from
the menu.
After that, the system will ask the user the minimum and maximum team size.
The system will then prompt the user for the jobs that the team members should have and how many of each job the team must have.
Finally, the user will be able to accept or reject randomly generated teams based on the criteria previously defined.

![Generate a team proposal](user_manual_rersources/us005/01.png)

### 6. Register a Vehicle | Fleet Manager

Refers to the feature that allows the Fleet Manager to register a vehicle.

Instructions:

In order to add a vehicle, the Fleet Manager must select the option "Register a Vehicle's Check-up" from the menu,
after that he will be prompted to fill the camps with the vehicle's information.
These camps are:
- PlateID: refers to the vehicle's plate identification.
- Brand: refers to the vehicle's brand.
- Model: refers to the vehicle's model.
- Type: refers to the vehicle's type.
- Tare: refers to the vehicle's tare.
- Gross Weight: refers to the vehicle's gross weight.
- Current Km: refers to the vehicle's current kilometers.
- Register Date: refers to the vehicle's registration date.
- Acquisition Date: refers to the vehicle's acquisition date.
- Check-up Frequency Km: refers to the vehicle's check-up frequency in kilometers.

![Register a Vehicle](user_manual_rersources/us006/01.png)

### 7. Register a Vehicle's Check-up | Fleet Manager

Refers to the feature that allows the Fleet Manager to register a vehicle's check-up.

Instructions:

After selecting the option, the system will prompt the Fleet Manager to select the vehicle that will be checked-up.
After selecting the vehicle, the system will ask the user to type the kilometers at checkup and the date of checkup.

![Register a Vehicle's Check-up](user_manual_rersources/us007/01.png)

### 8. List Vehicles needing check-up | Fleet Manager

Refers to the feature that allows the Fleet Manager to list vehicles needing check-up.

Instructions:

After selecting the option, the system will display a list of vehicles that need a check-up.
There's no need for any user input as the system will automatically calculate the vehicles that need a check-up and display them.

![List Vehicles needing check-up](user_manual_rersources/us008/01.png)


## Troubleshooting

## Frequently Asked Questions

## References