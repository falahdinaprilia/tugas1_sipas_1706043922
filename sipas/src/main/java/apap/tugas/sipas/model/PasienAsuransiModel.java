//package apap.tugas.sipas.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//
//@Entity
//@Table(name="pasien_asuransi")
//public class PasienAsuransiModel implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idPasienAsuransi;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "pasienId", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private PasienModel pasien;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "asuransiId", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private AsuransiModel asuransi;
//
//    public Long getIdPasienAsuransi() {
//        return idPasienAsuransi;
//    }
//
//    public void setIdPasienAsuransi(Long idPasienAsuransi) {
//        this.idPasienAsuransi = idPasienAsuransi;
//    }
//
//    public PasienModel getPasien() {
//        return pasien;
//    }
//
//    public void setPasien(PasienModel pasien) {
//        this.pasien = pasien;
//    }
//
//    public AsuransiModel getAsuransi() {
//        return asuransi;
//    }
//
//    public void setAsuransi(AsuransiModel asuransi) {
//        this.asuransi = asuransi;
//    }
//}
