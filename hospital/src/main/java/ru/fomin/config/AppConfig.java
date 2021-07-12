package ru.fomin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.fomin.hospital.api.DoctorOffice;
import ru.fomin.model.enumeration.DiseaseEnum;
import ru.fomin.model.enumeration.SymptomEnum;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("ru.fomin")
@PropertySource("patient.properties")
@RequiredArgsConstructor
public class AppConfig {

    private final DoctorOffice optometristOffice;
    private final DoctorOffice surgeonOffice;
    private final DoctorOffice therapistOffice;

    @Bean
    Map<SymptomEnum, DoctorOffice> symptomToDoctorOfficeMap() {
        Map<SymptomEnum, DoctorOffice> symptomToDoctorOfficeMap = new HashMap<>();
        symptomToDoctorOfficeMap.put(SymptomEnum.EYE_PAIN, optometristOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.INABILITY_TO_SEE, optometristOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.LEG_PAIN, surgeonOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.BIG_PIMPLE, surgeonOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.TEMPERATURE, therapistOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.HIGH_TEMPERATURE, therapistOffice);
        return symptomToDoctorOfficeMap;
    }

    @Bean(name = "optometristMap")
    Map<SymptomEnum, DiseaseEnum> optometristMap() {
        Map<SymptomEnum, DiseaseEnum> map = new HashMap<>();
        map.put(SymptomEnum.EYE_PAIN, DiseaseEnum.CATARACT);
        map.put(SymptomEnum.INABILITY_TO_SEE, DiseaseEnum.BLINDNESS);
        return map;
    }

    @Bean(name = "surgeonMap")
    Map<SymptomEnum, DiseaseEnum> surgeonMap() {
        Map<SymptomEnum, DiseaseEnum> map = new HashMap<>();
        map.put(SymptomEnum.LEG_PAIN, DiseaseEnum.CUT);
        map.put(SymptomEnum.BIG_PIMPLE, DiseaseEnum.BOIL);
        return map;
    }

    @Bean(name = "therapistMap")
    Map<SymptomEnum, DiseaseEnum> therapistMap() {
        Map<SymptomEnum, DiseaseEnum> map = new HashMap<>();
        map.put(SymptomEnum.TEMPERATURE, DiseaseEnum.COLD);
        map.put(SymptomEnum.HIGH_TEMPERATURE, DiseaseEnum.FLU);
        return map;
    }

}
