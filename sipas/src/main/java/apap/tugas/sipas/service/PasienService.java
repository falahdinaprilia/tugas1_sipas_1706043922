package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;

import java.util.List;
import java.util.Optional;

public interface PasienService {
    List<PasienModel> getPasienList();
    void addPasien(PasienModel pasien);
    Optional<PasienModel> getByIdPasien(Long idPasien);
    Optional<PasienModel> getByNikPasien(String nikPasien);
    PasienModel changePasien(PasienModel pasien);
    void kodePasien(PasienModel pasien);
}
