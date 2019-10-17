package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;

import java.util.List;
import java.util.Optional;

public interface PasienService {
    List<PasienModel> getPasienList();
    void addPasien(PasienModel pasien);
    Optional<PasienModel> GetByIdPasien(Long idPasien);
//    void kodePasien(PasienModel pasien);
}
