package stationnement;

import java.time.YearMonth;
import java.util.Random;

public class CarteCredit {      // c'est une carte de debit lol
    private String numero;
    private YearMonth exp;
    private double solde;  // argent qui reste sur la carte

    public CarteCredit (String numero, YearMonth exp){
        this.numero = numero;
        this.exp = exp;
        setSolde();
    }

    private void setSolde() {
        Random r = new Random();
        solde = (r.nextDouble(500.0) + 100.0);   // entre 100 et 500 ?
    }

    public void retirerMontant(double montant) {
        if (montant <= solde && montant > 0) {
            solde -= montant;
        }
    }

    public String getNumero() {
        return numero;
    }

    public YearMonth getExp() {
        return exp;
    }

    public double getSolde() {
        return solde;
    }
}


