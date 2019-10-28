package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDb;
import apap.tugas.sipas.repository.PasienDiagnosisPenyakitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasienDiagnosisPenyakitServiceImpl implements PasienDiagnosisPenyakitService {
    @Autowired
    private PasienDiagnosisPenyakitDb pasienDiagnosisPenyakitDb;

    @Autowired
    private PasienDb pasienDb;

    @Override
    public List<PasienModel> getAllPasienByIdPenyakit(Long idPenyakit) {
        List<PasienDiagnosisPenyakitModel> listPasienDiagnosis = pasienDiagnosisPenyakitDb.findByDiagnosisPenyakitId(idPenyakit);
        List<PasienModel> pasienTmp = new ArrayList<PasienModel>();
        for (PasienDiagnosisPenyakitModel i: listPasienDiagnosis) {
            if(i.getDiagnosisPenyakit().getId() == idPenyakit) {
                pasienTmp.add(i.getPasien());
            }
        }
        List<PasienModel> listPasien = pasienTmp.stream().distinct().collect(Collectors.toList());
        return listPasien;
    }

    @Override
    public void addDiagnosisPasien(PasienDiagnosisPenyakitModel diagnosisPasien, String nikPasien) {
        diagnosisPasien.setTanggalDiagnosis(java.sql.Date.valueOf(LocalDate.now()));
        diagnosisPasien.setPasien(pasienDb.findByNik(nikPasien).get());
        pasienDiagnosisPenyakitDb.save(diagnosisPasien);
    }
}
