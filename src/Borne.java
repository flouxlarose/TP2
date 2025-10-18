public class Borne {
    private String place = "NONE";
    private int banque;     // total en Cents
    private Transaction transactionCourante;

    void Borne (String place) {
        setPlace(place);
        banque = 0;
        transactionCourante = null;
    }

    private void setPlace(String place) {   // besoin d'etre private?
        if (place.matches("^(G|SQ)\\d{3}$")) {
            this.place = place;
        }

    }
}
