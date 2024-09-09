package hi.do_an_2.Model;

public class Tu_vung {
    public String tuVung;

    public int getIdTuVung() {
        return idTuVung;
    }

    public void setIdTuVung(int idTuVung) {
        this.idTuVung = idTuVung;
    }

    int idTuVung;

    public Tu_vung(String tuVung, int idTuVung, String nghiaTuVung) {
        this.tuVung = tuVung;
        this.idTuVung = idTuVung;
        this.nghiaTuVung = nghiaTuVung;
    }

    public Tu_vung(String tuVung, String nghiaTuVung) {
        this.tuVung = tuVung;
        this.nghiaTuVung = nghiaTuVung;
    }

    public String getTuVung() {
        return tuVung;
    }

    public void setTuVung(String tuVung) {
        this.tuVung = tuVung;
    }

    public String getNghiaTuVung() {
        return nghiaTuVung;
    }

    public void setNghiaTuVung(String nghiaTuVung) {
        this.nghiaTuVung = nghiaTuVung;
    }

    public String nghiaTuVung;
}
