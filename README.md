# COVITRACK
COVID Symptoms Tracker
## Project Overview
The COVID Symptoms Tracker is a Java-based application designed to help healthcare providers monitor and record the 
COVID-19 symptoms of patients. this project aims to simplify the process of tracking symptoms for healthcare 
professionals.

### *Key Features*:
- Patient Registration: Healthcare providers can register patients, recording their basic information, and assigning a 
unique patient ID.
- Symptom Tracking: For each registered patient, healthcare providers can log daily symptoms, including fever,
cough, shortness of breath, fatigue, and other symptoms.
- Symptom History: Access a patient's complete symptom history to track their progress and response to treatment.

### Target Users
This application is intended for **healthcare providers**, including doctors and nurses, who need a streamlined tool to
record and track COVID-19 symptoms in their patients.
An example of text with **bold** and *italic* fonts.  

### Project Motivation
During the pandemic, healthcare professionals faced an unprecedented surge in patient volumes, making it increasingly 
challenging to monitor the progression of symptoms, from mild to severe, in their patients. Tragically, many lives, 
including those of healthcare workers, were lost during this challenging period. This project is dedicated to 
streamlining the workload of healthcare providers by systematically organizing and monitoring changes in patients' 
symptoms and their severity. By doing so, it aims to improve patient care and outcomes while also easing the burden
on healthcare workers

### User Story;
- As a user, I want to be able to add a patient 
- As a user, I want to be able to retrieve  list of patients
- As a user, I want to be able to search patients according to symptoms 
- As a user, I want to be able to find a patient by name from the list using patient names
- As a user, I want to be able to update patient symptoms and comorbodities list.
- As a user, I want to be able to add a Treatment to a patient
- As a user, I want to be able to save Patient information
- As a user, i want to be able to continue from where I stopped.

### Instructions for Grader
- After running main, you add new Patients to the hospital by pressing the button addPatients
- You can view the patients in the hospital by pressing the view all patients.
- You can add symptoms, comorbidities, treatments and find patients by name by clicking the corresponding buttons
- You can save the state of my application by pressing the save button
- You can reload patients in the file by pressing load buttons.

### Phase 4: Task 2
- Patient added: Joe Doe 35 years
- Symptoms added to Joe Doe, Symptom Headache.
- Comorbidity added to joe Doe comorbidity: Hypertension
- prescription added to patient:  Joe Doe  prescription: tyenol.

### Phase 4: Task 3
In the GUI, the methods that add buttons like addSymptom, addPatient etc have similar structure, we can restructure so that
a generic method is used to reduce duplication.
Hospital class is managing so much information, that can be broken down into patient management, event logging 