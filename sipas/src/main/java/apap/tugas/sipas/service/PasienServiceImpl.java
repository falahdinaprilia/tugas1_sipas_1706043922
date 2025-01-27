package apap.tugas.sipas.service;

import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PasienServiceImpl implements PasienService {
    @Autowired
    private PasienDb pasienDb;
    @Override
    public List<PasienModel> getPasienList() {
        return pasienDb.findAll();
    }

    @Override
    public void addPasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    @Override
    public Optional<PasienModel> getByIdPasien(Long idPasien) {
        return pasienDb.findById(idPasien);
    }

    @Override
    public Optional<PasienModel> getByNikPasien(String nikPasien) {
        return pasienDb.findByNik(nikPasien);
    }

    @Override
    public PasienModel changePasien(PasienModel pasien) {
    PasienModel targetPasien = pasienDb.findById(pasien.getId()).get();
    targetPasien.setNama(pasien.getNama());
    targetPasien.setNik(pasien.getNik());
    targetPasien.setJenisKelamin(pasien.getJenisKelamin());
    targetPasien.setTempatLahir(pasien.getTempatLahir());
    if(pasien.getTanggalLahir() != targetPasien.getTanggalLahir()) {
        targetPasien.setTanggalLahir(pasien.getTanggalLahir());
        kodePasien(targetPasien);
    }
        pasienDb.save(targetPasien);
        return targetPasien;
    }

    public void kodePasien(PasienModel pasien) {
        Date tanggalLahir = pasien.getTanggalLahir();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        String strDate = dateFormat.format(tanggalLahir).replaceAll("-", "");
        String kode = (LocalDateTime.now().getYear() + 5) + strDate + pasien.getJenisKelamin() +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(new Random().nextInt(26)) +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(new Random().nextInt(26));
        pasien.setKode(kode);
        }
    }
