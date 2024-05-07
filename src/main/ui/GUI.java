package ui;

import main.Event;
import main.EventLog;
import main.Hospital;
import main.Patient;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


// Represents the Hospital application
public class GUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/test.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Hospital hospital;
    JPanel panel;
    JTextArea patientInfoTextArea;



    // EFFECTS: constructs Hospital and runs graphical User Interface of the application

    public GUI(Hospital hospital) throws FileNotFoundException {
        super("COVID Patient Tracking System");
        this.hospital = hospital;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        panel = new JPanel(new GridLayout(0, 1, 10, 10));
        patientInfoTextArea = new JTextArea();
        ImageIcon icon = new ImageIcon("src/main/images/appbackground2.jpeg");
        JLabel backgroundLabel = new JLabel(icon);
        setContentPane(backgroundLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(90, 90, 100, 300));
        setLayout(new BorderLayout());
        addOtherComponents();
        add(new JScrollPane(patientInfoTextArea), BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        panel.setOpaque(false);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        setVisible(true);
        windowListener();

    }


    private void windowListener() {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
                printEvents();
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    private void printEvents() {
        EventLog eventLog = EventLog.getInstance();
        System.out.println("Event that happened so far include: ");
        for (Event event : eventLog) {
            System.out.println(event);
        }
    }

    //Modifies: this.
    // Effect: adds buttons.
    private void addOtherComponents() {
        addPatientButton();
        addSymptomButton();
        addComorbidityButton();
        addTreatmentButton();
        findPatientButton();
        findSymptomButton();
        viewAllPatientsButton();
        saveButton();
        loadButton();
        exitButton();

        JScrollPane scrollPane = new JScrollPane(patientInfoTextArea);

        JLabel label = new JLabel("Number of clicks: 0");
        patientInfoTextArea.setEditable(false);
        panel.add(scrollPane, BorderLayout.CENTER);


    }


    //Modifies: this.
    //Effect: Adds addpatient button and adds patient to the hospital
    public void addPatientButton() {
        JButton addPatient = new JButton("Add Patient");
        addPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Patient's name: ");
                String ageStr = JOptionPane.showInputDialog("Enter Patient's age: ");
                try {
                    // Parse age as a double
                    double age = Double.parseDouble(ageStr);

                    // Create a new patient and add it to the hospital
                    Patient patient = new Patient(name, age);
                    hospital.addPatient(patient);
                    patientInfoTextArea.append("Added Patient: " + name + ", Age: " + age + "\n");

                    // Display a success message
                    JOptionPane.showMessageDialog(null, "Patient successfully added!");
                } catch (NumberFormatException ex) {
                    // Handle invalid input for age
                    JOptionPane.showMessageDialog(null, "Invalid age. Please enter a valid number."
                            + "", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(addPatient);

    }

    //Modifies: this.
    //Effect: Adds addSymptomButton button and adds symptom to the patient in hospital
    public void addSymptomButton() {
        JButton addSymptom = new JButton("Add Symptom to Patient");
        addSymptom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Patient's name: ");
                if (hospital.findPatientbyName(name) == null) {
                    JOptionPane.showMessageDialog(null, "Patient not found");
                    JOptionPane.showMessageDialog(null, "Symptom successfully added!");
                }
                String symptom = JOptionPane.showInputDialog("Enter Patient's Symptom: ");
                hospital.findPatientbyName(name).addSymptom(symptom);
                JOptionPane.showMessageDialog(null, "Symptom successfully added!");

            }
        });
        panel.add(addSymptom);

    }

    //Modifies: this.
    //Effect: Adds addComorbidity button and adds comorbidity to the patient in hospital
    public void addComorbidityButton() {
        JButton addComorbidity = new JButton("Add Comorbidity");
        addComorbidity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Patient's name: ");
                String comorbidity = JOptionPane.showInputDialog("Enter Patient's Comorbidity: ");
                hospital.findPatientbyName(name).addCormobidities(comorbidity);
            }
        });
        panel.add(addComorbidity);
    }

    //Modifies: this.
    //Effect: Adds findPatient button and finds patient in hospital by name
    public void findPatientButton() {
        JButton findPatient = new JButton("Find Patient by Name");
        findPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Patient's name: ");
                Patient foundPatient = hospital.findPatientbyName(name);
                if (foundPatient != null) {
                    JOptionPane.showMessageDialog(null, foundPatient.getName() + " " + foundPatient.getAge());
                } else {
                    JOptionPane.showMessageDialog(null, "Patient not found");
                }
            }
        });
        panel.add(findPatient);
    }

    //Modifies: this.
    //Effect: Adds addTreatment button and adds treatment to the patient in hospital
    public void addTreatmentButton() {
        JButton addTreatment = new JButton("Add Treatment to Patient");
        addTreatment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Patient's name: ");
                String comorbidity = JOptionPane.showInputDialog("Enter Patient's prescription: ");
                hospital.findPatientbyName(name).addTreatment(comorbidity);
            }
        });
        panel.add(addTreatment);
    }

    //Modifies: this.
    //Effect: Adds findSymptom button and returns patients with symptom in hospital
    public void findSymptomButton() {
        JButton findSymptom = new JButton("Find Patients with Symptom");
        findSymptom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String symptom = JOptionPane.showInputDialog("Enter Symptom: ");
                List<Patient> patientwithSymptom = hospital.getPatientswithSymptom(symptom);
                if (patientwithSymptom != null) {
                    JOptionPane.showMessageDialog(null, "Patients include: ");
                    for (Patient p : patientwithSymptom) {
                        JOptionPane.showMessageDialog(null, "-" + p.getName() + " " + p.getAge());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Patient found");
                }
            }
        });
        panel.add(findSymptom);
    }


    //Modifies: this.
    //Effect: Adds viewAllPatients button and displays patients in hospital
    private void viewAllPatientsButton() {
        JButton viewAllPatients = new JButton("View All Patients");
        viewAllPatients.addActionListener(e -> {
            StringBuilder patientsInfo = new StringBuilder("All patients in the system:\n");
            List<Patient> allPatients = hospital.getAllpatients();

            if (allPatients != null && !allPatients.isEmpty()) {
                for (Patient p : allPatients) {
                    patientsInfo.append("Name: ").append(p.getName()).append(", Age: ").append(p.getAge()).append("\n");
                }
            } else {
                patientsInfo.append("No patients in the system");
            }

            JOptionPane.showMessageDialog(null, patientsInfo.toString());
        });

        panel.add(viewAllPatients);
    }

    //Modifies: this.
    //Effect: Adds save button and saves patients information in hospital
    public void saveButton() {
        JButton save = new JButton("Save to File");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(hospital);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null, "Saved patient to " + JSON_STORE);
                } catch (FileNotFoundException f) {
                    JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
                }
            }
        });
        panel.add(save);

    }

    //Modifies: this.
    //Effect: Adds loadButton button and loads patients from json file
    public void loadButton() {
        JButton load = new JButton("Load from File");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    hospital = jsonReader.read();
                    JOptionPane.showMessageDialog(null, "Loaded patients from " + JSON_STORE);
                } catch (IOException f) {
                    JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
                }
            }
        });
        panel.add(load);

    }

    //Modifies: this.
    //Effect: Adds exit button and exits the system
    public void exitButton() {
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                printEvents();
                System.exit(0);
            }
        });
        panel.add(exit);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
