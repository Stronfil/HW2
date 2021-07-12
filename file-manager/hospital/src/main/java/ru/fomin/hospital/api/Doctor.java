package ru.fomin.hospital.api;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.DiseaseEnum;
import ru.fomin.model.enumeration.SymptomEnum;

import java.util.Map;

@Setter
public abstract class Doctor {

    private Map<SymptomEnum, DiseaseEnum> symptomToDiseaseMap;

    public void heal(Patient patient) {
        patient.setSick(false);
        patient.printPatientInfo();
    }

    public DiseaseEnum getDisease(SymptomEnum symptom) {
        return symptomToDiseaseMap.get(symptom);
    }

}
