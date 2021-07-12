package ru.fomin.hospital.api;

import ru.fomin.model.Patient;

public interface Reception {

    void sendForTreatment(Patient patient);

}
