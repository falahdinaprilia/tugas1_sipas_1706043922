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
    targetPasien.setTanggalLahir(pasien.getTanggalLahir());
    if(pasien.getTanggalLahir() != targetPasien.getTanggalLahir()) {
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
//    @Override
//    public void kodePasien(PasienModel pasien) {
//        if ()
//        int tahunLahir = Calendar.getInstance().get(Calendar.YEAR);
//        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
//        String tanggalLahir = dateFormat.format(pasien.getTanggalLahir());
//        String jenisKelamin = Integer.toString(pasien.getJenisKelamin());
//        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        StringBuilder hurufTemp = new StringBuilder(2);
//        for(int i = 0; i<2; i++) {
//            int idx = (int)(AlphaNumericString.length() * Math.random());
//            hurufTemp.append(AlphaNumericString.charAt(idx));
//        }
//        String hurufRandom = hurufTemp.toString();
//        String kode = Integer.toString(tahunLahir+5) + tanggalLahir + jenisKelamin + hurufRandom;
//        pasien.setKode(kode);
//    }
