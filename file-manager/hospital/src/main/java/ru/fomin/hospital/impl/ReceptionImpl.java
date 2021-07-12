package ru.fomin.hospital.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.fomin.hospital.api.DoctorOffice;
import ru.fomin.hospital.api.Reception;
import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.SymptomEnum;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReceptionImpl implements Reception {

    private final Map<SymptomEnum, DoctorOffice> symptomToDoctorOfficeMap;

    @Override
    public void sendForTreatment(Patient patient) {
        DoctorOffice doctorOffice = symptomToDoctorOfficeMap.get(patient.getSymptom());
        doctorOffice.showToDoctor(patient);
    }

}
