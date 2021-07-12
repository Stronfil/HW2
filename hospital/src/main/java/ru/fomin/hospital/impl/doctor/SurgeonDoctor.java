package ru.fomin.hospital.impl.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.fomin.hospital.api.Doctor;
import ru.fomin.model.enumeration.DiseaseEnum;
import ru.fomin.model.enumeration.SymptomEnum;

import java.util.Map;

@Component("surgeon")
public class SurgeonDoctor extends Doctor {

    @Override
    @Autowired
    @Qualifier("surgeonMap")
    public void setSymptomToDiseaseMap(@Lazy Map<SymptomEnum, DiseaseEnum> symptomToDiseaseMap) {
        super.setSymptomToDiseaseMap(symptomToDiseaseMap);
    }

}
