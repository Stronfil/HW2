package ru.fomin.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.fomin.model.enumeration.SymptomEnum;

@Data(staticConstructor = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient {

    final String name;
    final SymptomEnum symptom;
    boolean isSick = true;

    public void printPatientInfo() {
        System.out.printf("Patient %s is %s\n\n", name, isSick() ? "sick" : "healthy");
    }

}
