package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="asuransi")
public class AsuransiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="jenis", nullable = false)
    private String jenis;

    @ManyToMany(mappedBy = "listAsuransi", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PasienModel> listPasien;

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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public List<PasienModel> getListPasien() {
        return listPasien;
    }

    public void setListPasien(List<PasienModel> listPasien) {
        this.listPasien = listPasien;
    }
}
