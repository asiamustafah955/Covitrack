package ui;

import main.Hospital;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
//            new HospitalApp();
            Hospital hospital = new Hospital();
            new GUI(hospital);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
