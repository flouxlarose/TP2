package stationnement;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class Transaction {
    private int cout;
    private String paiement = "aucun";
    private String stationnement;
    private double temps;
    private LocalDateTime heureDebut;
    private CarteCredit carteCredit;

    private static int  TARIF_HEURE_G = 425;
    private static int TARIF_HEURE_SQ = 225;
    private static int MAX_MINUTES = 120;

    public Transaction(String stationnement){
        this.stationnement = stationnement;
        setHeureDebut();
    }

    public int getCout() {
        return cout;
    }

    public void ajouterMontant(int montant) {
        if (montant > 0) {
            cout += montant;
            calculerTemp();
        }
    }

    public void retirerMontant(int montant) {
        if (montant > 0 && montant <= cout) {
            cout -= montant;
            calculerTemp();
        }
    }

    public void calculerTemp(){
        if (cout >= 0 && temps <= MAX_MINUTES){
            if (stationnement.charAt(0) == 'G'){
                temps = (double) (cout * 60) / TARIF_HEURE_G;
            }
            else if (stationnement.charAt(0) == 'S'){
                temps = (double) (cout * 60) / TARIF_HEURE_SQ;
            }
            if (temps > MAX_MINUTES) {
                temps = MAX_MINUTES;
            }
        }
    }

    public void ajouterTemps(double min) {
        if (min > 0){
            temps += min;
            temps = temps > MAX_MINUTES ? 120.0 : temps;
            setCout();
        }
    }

    private void setCout(){
        if (temps > 0 && temps <= MAX_MINUTES){
            if (stationnement.charAt(0) == 'G'){
                cout = (int) Math.round((temps * TARIF_HEURE_G) / 60);
            } else if (stationnement.charAt(0) == 'S') {
                cout = (int) Math.round((temps * TARIF_HEURE_SQ) / 60);
            }
        } else if (temps == 0 && cout != 0) {
            cout = 0;

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

    public double getTemps() {
        return temps;
    }

    public CarteCredit getCarteCredit() {
        return carteCredit;
    }

    public void setCarteCredit(String numero, String exp) {
        int tempMois = Integer.parseInt(exp.substring(0, 2));
        int tempAnnee = Integer.parseInt(exp.substring(3, 5)) + 2000;
        YearMonth expDate = YearMonth.of(tempAnnee, tempMois);
        carteCredit = new CarteCredit(numero, expDate);
    }

    public String afficherRecu() {
        DecimalFormat df = new DecimalFormat("0.00$");
        String recu = "------------------" + "\n";
        recu += "type de paiement : " + paiement + "\n";
        recu += "cout de la transaction : " + (df.format((double) cout / 100)) + "\n";
        recu += "heure de debut : " + heureDebut + "\n";
        recu += "heure de fin : " + heureDebut.plusMinutes((long) temps) + "\n";
        recu += "place : " + stationnement;
        return recu;
    }
}
