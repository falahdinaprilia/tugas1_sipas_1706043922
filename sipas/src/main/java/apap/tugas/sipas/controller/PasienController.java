package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.AsuransiService;
import apap.tugas.sipas.service.EmergencyContactService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Controller
public class PasienController {
    @Autowired
    private EmergencyContactService emergencyContactService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Autowired
    private AsuransiService asuransiService;


    @RequestMapping(value= "/", method = RequestMethod.GET)
    public String beranda(Model model) {
        List<PasienModel> listPasien = pasienService.getPasienList();
        for (PasienModel pasien : listPasien) {
            pasien.getEmergencyContact().getNoHp();
        }
//        List<EmergencyContactModel> listEmergency = emergencyContactService.getEmergencyList();
//        String noHp;
//        if (pasien.getIdEmergency().equals(emergency.getId())) {
//            noHp = emergency.getNoHp();
//        }
        Collections.sort(listPasien);
        model.addAttribute("pasienList", listPasien);

//        String noEmergency = emergencyContactService.GetPasienByIdPasien(
        return "beranda";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String addPasienFormPage(Model model) {

        PasienModel pasien = new PasienModel();
        AsuransiModel asuransi = new AsuransiModel();
        EmergencyContactModel emergencyContact = new EmergencyContactModel();
        List<AsuransiModel> listAsuransi = new ArrayList<AsuransiModel>();
        listAsuransi.add(asuransi);
        pasien.setEmergencyContact(emergencyContact);
        pasien.setListAsuransi(listAsuransi);

        List<AsuransiModel> listAllAsuransi = asuransiService.getAsuransiList();
        model.addAttribute("allAsuransiList", listAllAsuransi);
        model.addAttribute("pasien", pasien);

        return "form-tambah-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST)
    public String addPasienSubmit(@RequestParam (value = "asuransi") Long id, @ModelAttribute PasienModel pasien, Model model){
        pasien.kodePasien(pasien);
        AsuransiModel asuransiPasien = asuransiService.getAsuransiById(id).get();
        List<AsuransiModel> listAsuransiPasien = new ArrayList<AsuransiModel>();
        listAsuransiPasien.add(asuransiPasien);
        pasien.setListAsuransi(listAsuransiPasien);
        emergencyContactService.addEmergencyContact(pasien.getEmergencyContact());
        pasienService.addPasien(pasien);
        model.addAttribute("kodePasien", pasien.getKode());
        model.addAttribute("namaPasien", pasien.getNama());

        return "tambah-pasien";
    }
}