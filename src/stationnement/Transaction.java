package stationnement;

import java.time.LocalDateTime;

public class Transaction {
    private int cout;
    private String paiement;
    private int stationnement;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;

    public Transaction(int stationnement){
        this.stationnement = stationnement;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public String getPaiement() {
        return paiement;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }

    public int getStationnement() {
        return stationnement;
    }

    public void setStationnement(int stationnement) {
        this.stationnement = stationnement;
    }

    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalDateTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalDateTime heureFin) {
        this.heureFin = heureFin;
    }
}
