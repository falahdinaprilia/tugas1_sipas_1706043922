package apap.tugas.sipas.service;

import apap.tugas.sipas.model.EmergencyContactModel;

import java.util.List;
import java.util.Optional;

public interface EmergencyContactService {
    Optional<EmergencyContactModel> getPasienByPasienId(Long PasienId);
    Optional<EmergencyContactModel> getEmergencyById(Long idEmergency);
    void addEmergencyContact(EmergencyContactModel emergencyContact);
    List<EmergencyContactModel> getEmergencyList();
    EmergencyContactModel changeEmergency(EmergencyContactModel emergencyContact);
}
