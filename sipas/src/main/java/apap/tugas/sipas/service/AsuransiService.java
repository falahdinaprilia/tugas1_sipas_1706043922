package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;

import java.util.List;
import java.util.Optional;

public interface AsuransiService {
    List<AsuransiModel> getAsuransiList();
    Optional<AsuransiModel> getAsuransiById(Long idAsuransi);
}
