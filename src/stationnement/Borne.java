package stationnement;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

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
        return numero.matches("\\d*");
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
}
