package ru.fomin.hospital.api;

import lombok.Setter;
import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.DiseaseEnum;

@Setter
public abstract class DoctorOffice {

    private Doctor doctor;

    public void showToDoctor(Patient patient) {
        DiseaseEnum disease = doctor.getDisease(patient.getSymptom());
        printDiagnosis(patient, disease);
        doctor.heal(patient);

    }

    private void printDiagnosis(Patient patient, DiseaseEnum disease) {
        System.out.printf(
                "Patient %s is sick by %s.\nHim symptom is %s.\n",
                patient.getName(),
                disease.toString().toLowerCase(),
                patient.getSymptom().toString().toLowerCase()
        );
    }

}
