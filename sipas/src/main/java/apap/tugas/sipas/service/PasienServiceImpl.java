package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
    public Optional<PasienModel> GetByIdPasien(Long idPasien) {
        return pasienDb.findById(idPasien);
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
}
