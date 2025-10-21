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
            //  et si le temps match return True
        }
        else if (place.matches("^SQ\\d{3}$")) {
            //  et si temps match return True
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
