package apap.tugas.sipas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Entity
@Table(name="pasien")
public class PasienModel implements Serializable, Comparable<PasienModel> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="kode", nullable = false, unique = true)
    private String kode;

    @NotNull
    @Column(name="nik", nullable = false, unique = true)
    private String nik;

    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Column(name="tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Column(name="tempat_lahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name="jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @ManyToMany
    @JoinTable(name = "pasien_asuransi",
            joinColumns = @JoinColumn(name = "id_pasien"),
            inverseJoinColumns = @JoinColumn(name = "id_asuransi")
    )
    private List<AsuransiModel> listAsuransi;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_emergency_contact", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EmergencyContactModel emergencyContact;

    @OneToMany(mappedBy= "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public List<AsuransiModel> getListAsuransi() {
        return listAsuransi;
    }

    public void setListAsuransi(List<AsuransiModel> listAsuransi) {
        this.listAsuransi = listAsuransi;
    }

    public EmergencyContactModel getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContactModel emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public List<PasienDiagnosisPenyakitModel> getListPasienDiagnosisPenyakit() {
        return listPasienDiagnosisPenyakit;
    }

    public void setListPasienDiagnosisPenyakit(List<PasienDiagnosisPenyakitModel> listPasienDiagnosisPenyakit) {
        this.listPasienDiagnosisPenyakit = listPasienDiagnosisPenyakit;
    }

    @Override
    public int compareTo(PasienModel pasienLain) {
        return this.nama.compareTo(pasienLain.nama);
    }

}
