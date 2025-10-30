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
    private static int MAX_MINUTES = 120;

    public Transaction(String stationnement){
        this.stationnement = stationnement;
        setPaiement("aucun");
    }

    public int getCout() {
        return cout;
    }

    public void ajouterMontant(int montant) {
        if (montant > 0) {
            cout += montant;
            ajouterTemps(montant);
        }
    }

    public void ajouterTemps(int montant){
        if (montant > 0 && temps <= MAX_MINUTES){
            if (stationnement.charAt(0) == 'G'){
                temps += (double) (montant * 60) / TARIF_HEURE_G;

            }
            else if (stationnement.charAt(0) == 'S'){
                temps += (double) (montant * 60) / TARIF_HEURE_SQ;
            }
            if (temps > MAX_MINUTES) {
                temps = MAX_MINUTES;
            }
        }
    }

    public String getPaiement() {
        return paiement;
    }

    public void setPaiement(String paiement) {
        if (paiement.equals("comptant") || paiement.equals("carte") || paiement.equals("aucun")) {
            this.paiement = paiement;
        }
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

    public double getTemps() {
        return temps;
    }
}
