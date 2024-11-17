package com.aanya.HMS;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Create the SessionFactory from hibernate.cfg.xml
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Doctor.class)
                .addAnnotatedClass(Appointment.class)
                .addAnnotatedClass(Bill.class)
                .buildSessionFactory();

        // Create DAO instances
        PatientDAO patientDAO = new PatientDAO(factory);
        DoctorDAO doctorDAO = new DoctorDAO(factory);
        AppointmentDAO appointmentDAO = new AppointmentDAO(factory);
        BillDAO billDAO = new BillDAO(factory);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nHospital Management System");
            System.out.println("1. Add Patient");
            System.out.println("2. View Particular Patient");
            System.out.println("3. View All Patients");
            System.out.println("4. Add Doctor");
            System.out.println("5. View Particular Doctor");
            System.out.println("6. View All Doctors");
            System.out.println("7. Schedule Appointment");
            System.out.println("8. View Appointment");
            System.out.println("9. Create Bill");
            System.out.println("10. View Bill");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add a new patient
//                	System.out.println("Enter PatientId:");
//                    int id = scanner.nextInt();
                    
                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter date of birth (yyyy-mm-dd):");
                    String dobInput = scanner.nextLine();
                    Date dob = java.sql.Date.valueOf(dobInput);
                    System.out.println("Enter contact number:");
                    String contactNumber = scanner.nextLine();
                    System.out.println("Enter address:");
                    String address = scanner.nextLine();
                    System.out.println("Enter gender (MALE, FEMALE, OTHER):");
                    Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

                    Patient newPatient = new Patient();
                    //newPatient.setId(id);
                    newPatient.setFirstName(firstName);
                    newPatient.setLastName(lastName);
                    newPatient.setDateOfBirth(dob);
                    newPatient.setContactNumber(contactNumber);
                    newPatient.setAddress(address);
                    newPatient.setGender(gender);

                    patientDAO.savePatient(newPatient);
                    System.out.println("Patient added successfully.");
                    break;

                case 2:
                    // View a patient by ID
                    System.out.println("Enter patient ID:");
                    int patientId = scanner.nextInt();
                    Patient patient = patientDAO.getPatient(patientId);
                    if (patient != null) {
                        System.out.println(patient);
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                    
                case 3:
                	//View all the patients
				List<Patient> patients;
				
					patients = patientDAO.listPatients();

    				// Print or use the records as needed
    				for (Patient it : patients) {
    					System.out.println(it);
    				}
    				break;
               
                case 4:
                    // Add a new doctor
//                	System.out.println("Enter DoctorId:");
//                    int did = scanner.nextInt();
                    System.out.println("Enter doctor name:");
                    String doctorName = scanner.nextLine();
                    System.out.println("Enter specialization:");
                    String specialization = scanner.nextLine();
                    System.out.println("Enter experience in years:");
                    int experience = scanner.nextInt();
                    System.out.println("Enter contact number:");
                    String doctorContactNumber = scanner.nextLine();

                    Doctor newDoctor = new Doctor();
                    //newDoctor.setId(did);
                    newDoctor.setName(doctorName);
                    newDoctor.setSpecialization(specialization);
                    newDoctor.setExperience(experience);
                    newDoctor.setContactNumber(doctorContactNumber);

                    doctorDAO.saveDoctor(newDoctor);
                    System.out.println("Doctor added successfully.");
                    break;

                case 5:
                    // View a doctor by ID
                    System.out.println("Enter doctor ID:");
                    int doctorId = scanner.nextInt();
                    Doctor doctor = doctorDAO.getDoctor(doctorId);
                    if (doctor != null) {
                        System.out.println(doctor);
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                    
                case 6:
                	//View all the doctors
				List<Doctor> doctors;
				
					doctors = doctorDAO.listDoctors();

    				// Print or use the records as needed
    				for (Doctor it : doctors) {
    					System.out.println(it);
    				}
    				break;

                case 7:
                    // Schedule an appointment
                    System.out.println("Enter patient ID:");
                    int appointmentPatientId = scanner.nextInt();
                    System.out.println("Enter doctor ID:");
                    int appointmentDoctorId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter appointment date and time (yyyy-mm-dd hh:mm:ss):");
                    String appointmentDateTime = scanner.nextLine();
                    Date appointmentDate = java.sql.Timestamp.valueOf(appointmentDateTime);

                    Appointment newAppointment = new Appointment();
                    newAppointment.setPatient(patientDAO.getPatient(appointmentPatientId));
                    newAppointment.setDoctor(doctorDAO.getDoctor(appointmentDoctorId));
                    newAppointment.setAppointmentDate(appointmentDate);
                    newAppointment.setStatus("Scheduled");

                    appointmentDAO.saveAppointment(newAppointment);
                    System.out.println("Appointment scheduled successfully.");
                    break;

                case 8:
                    // View an appointment by ID
                    System.out.println("Enter appointment ID:");
                    int appointmentId = scanner.nextInt();
                    Appointment appointment = appointmentDAO.getAppointment(appointmentId);
                    if (appointment != null) {
                        System.out.println(appointment);
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;

                case 9:
                    // Create a bill
                    System.out.println("Enter patient ID:");
                    int billPatientId = scanner.nextInt();
                    System.out.println("Enter bill amount:");
                    double amount = scanner.nextDouble();

                    Bill newBill = new Bill();
                    newBill.setPatient(patientDAO.getPatient(billPatientId));
                    newBill.setAmount(amount);
                    newBill.setBillingDate(new Date());

                    billDAO.saveBill(newBill);
                    System.out.println("Bill created successfully.");
                    break;

                case 10:
                    // View a bill by ID
                    System.out.println("Enter bill ID:");
                    int billId = scanner.nextInt();
                    Bill bill = billDAO.getBill(billId);
                    if (bill != null) {
                        System.out.println(bill);
                    } else {
                        System.out.println("Bill not found.");
                    }
                    break;

                
                case 11:
                    // Exit
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Close the SessionFactory
        factory.close();
        scanner.close();
        System.out.println("Goodbye!");
    }
}

