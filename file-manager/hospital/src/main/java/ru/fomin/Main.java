package ru.fomin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.fomin.config.AppConfig;
import ru.fomin.hospital.api.Reception;
import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.SymptomEnum;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Main {

    private List<Patient> patientList;

    private final Reception reception;

    @Value("#{'${patient.name}'.split(',')}")
    private List<String> patientNameList;

    @PostConstruct
    public void init() {
        patientList = new ArrayList<>(patientNameList.size());
        SymptomEnum[] symptomArr = SymptomEnum.values();
        for (int i = 0; i < patientNameList.size(); i++) {
            String patientName = patientNameList.get(i);
            SymptomEnum symptom = symptomArr.length > i ? symptomArr[i] : symptomArr[i % symptomArr.length];
            patientList.add(Patient.of(patientName, symptom));
        }
        System.out.printf("%d patients have come to reception\n\n", patientList.size());
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = context.getBean("main", Main.class);
        main.patientList.forEach(main.reception::sendForTreatment);
    }

}
