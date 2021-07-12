package ru.fomin.hospital.impl.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.fomin.hospital.api.Doctor;
import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.DiseaseEnum;
import ru.fomin.model.enumeration.SymptomEnum;

import java.util.Map;

@Component("optometrist")
public class OptometristDoctor extends Doctor {

    @Override
    public void heal(Patient patient) {
        if (getDisease(patient.getSymptom()) == DiseaseEnum.BLINDNESS) {
            patient.printPatientInfo();
        } else {
            super.heal(patient);
        }
    }

    @Override
    @Autowired
    @Qualifier("optometristMap")
    public void setSymptomToDiseaseMap(@Lazy Map<SymptomEnum, DiseaseEnum> symptomToDiseaseMap) {
        super.setSymptomToDiseaseMap(symptomToDiseaseMap);
    }

}
