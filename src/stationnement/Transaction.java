package stationnement;

import java.time.LocalDateTime;

public class Transaction {
    private int cout;
    private String paiement;
    private String stationnement;
    private double temps;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;

    private static int  TARIF_HEURE_G = 425;
    private static int TARIF_HEURE_SQ = 225;

    public Transaction(String stationnement){
        this.stationnement = stationnement;
    }

    public int getCout() {
        return cout;
    }

    public void ajouterMontant(int montant) {
        if (montant > 0) {
            cout += montant;
        }
    }

    public void ajouterTemps(int montant){
        if (montant > 0){
            if (stationnement.charAt(0) == 'G'){
                temps += (double) (montant * 60) / TARIF_HEURE_G;
            }
            else if (stationnement.charAt(0) == 'S'){
                temps += (double) (montant * 60) / TARIF_HEURE_SQ;
            }
        }
    }

    public String getPaiement() {
        return paiement;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }

    public String getStationnement() {
        return stationnement;
    }

    public void setStationnement(String stationnement) {
        this.stationnement = stationnement;
    }

    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut() {
        heureDebut = LocalDateTime.now();
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalDateTime heureFin) {
        this.heureFin = heureFin;
    }
}
