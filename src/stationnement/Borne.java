package stationnement;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class Borne {
    private int banque = 0;         // total en Cents
    private Transaction transactionCourante;

    static final int MAX_MINUTES = 120;


    public Borne () {
        transactionCourante = null;
    }

    public boolean validerPlace(String place) {
        return place.matches("^(G|SQ)\\d{3}$");

    }

    public boolean validerHeure(LocalDateTime present, String place) {
        if (place.charAt(0) == 'G'){
            if (present.getDayOfWeek() == DayOfWeek.SATURDAY){
                return present.getHour() >= 9 && present.getHour() < 23;
            } else if (present.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return present.getHour() >= 13 && present.getHour() < 18;
            }
            else return present.getHour() >= 8 && present.getHour() < 23;
        }
        else if (place.charAt(0) == 'S'){
            if (present.getDayOfWeek() == DayOfWeek.SATURDAY){
                return present.getHour() >= 9 && present.getHour() < 18;
            }
            if (present.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return false;
            }
            else return present.getHour() >= 9 && present.getHour() < 21;
        }
        else return false;

    }

    public void ajouterMonaie(Piece monaie) {
        banque += monaie.getValeur();
        transactionCourante.ajouterMontant(monaie.getValeur());
    }

    public boolean verifNumCredit(String numero) {
        return numero.matches("^\\d{4} \\d{4} \\d{4} \\d{4}$");
    }
    public boolean verifExpCredit(String exp) {
        int tempMois = Integer.parseInt(exp.substring(0, 2));
        int tempAnnee = Integer.parseInt(exp.substring(3, 5)) + 2000;
        YearMonth expDate = YearMonth.of(tempAnnee, tempMois);
        return expDate.isAfter(YearMonth.now());
    }

    public int getBanque() {
        return banque;
    }

    public void setBanque(int banque) {
        this.banque = banque;
    }

    public Transaction getTransactionCourante() {
        return transactionCourante;
    }

    public void setTransactionCourante(Transaction transactionCourante) {
        this.transactionCourante = transactionCourante;
    }

    public String afficherRecu() {
        if (transactionCourante != null){
            return transactionCourante.afficherRecu();
        }
        else {
            return "Place pas encore valide";
        }

    }
    public void terminerTransaction() {
        transactionCourante = null;
    }

    public String afficherRapport() {
        DecimalFormat df = new DecimalFormat("0.00$");
        String rapport = "Banque totale : " + (df.format((double) banque / 100));
        banque = 0;
        return rapport;
    }
}
