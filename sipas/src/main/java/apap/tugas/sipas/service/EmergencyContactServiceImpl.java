package apap.tugas.sipas.service;

import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.repository.EmergencyContactDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService {
    @Autowired
    EmergencyContactDb emergencyContactDb;

    @Override
    public Optional<EmergencyContactModel> getPasienByPasienId(Long pasienId) {
        return emergencyContactDb.findByPasienId(pasienId);
    }

    @Override
    public Optional<EmergencyContactModel> getEmergencyById(Long idEmergency) {
        return emergencyContactDb.findById(idEmergency);
    }

    @Override
    public void addEmergencyContact(EmergencyContactModel emergencyContact) {
        emergencyContactDb.save(emergencyContact);
    }

    @Override
    public List<EmergencyContactModel> getEmergencyList() {
        return emergencyContactDb.findAll();
    }

}
