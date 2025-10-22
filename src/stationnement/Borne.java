package stationnement;

public class Borne {
    private String place;
    private int banque = 0;         // total en Cents
    private Transaction transactionCourante;

    static final int MAX_MINUTES = 120;


    public Borne () {
        transactionCourante = null;
    }

    public boolean validerPlace(String place) {
        if (place.matches("^G\\d{3}$")) {
            // L - V 8h-23h / S 9h-23h / D 13h-18h
            // tarif horaire (60 min) = 4.25 --- 0.25$ = 3.529411 --- minute près?
        }
        else if (place.matches("^SQ\\d{3}$")) {
            //  L - V 9h-21h / S 9h-18h
            // tarif horaire (60 min) = 2.25$ --- 0.25$ = 6.666666 min --- minute près ?
        }
        else {
            return false;
        }
        return false;
    }

    public void ajouterMonaie(Piece monaie) {
        banque += monaie.getValeur();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
