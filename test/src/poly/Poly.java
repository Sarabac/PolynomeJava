package poly;

/**
 * <p>Une classe permettant de manipuler et evaluer des polynomes.</p>
 * <p>(Voir la page wikipedia: {@link <a>https://fr.wikipedia.org/wiki/Polyn%C3%B4me</a>})</p>
 * <p>La liste des coeficients doit etre un double[] allant du coefficient le plus faible
 * au plus fort.</p>
 * @author lboutarfa
 *
 */
public class Poly{
	/**
	 * La liste des coefficients.
	 */
    private double[] coef;

    public Poly(double[] coef){
        this.coef = coef;
    }
    /**
     * Evalue le polynome.
     * @param x
     * @return P(x)
     */
    public double eval(double x) {
    	double retour = 0;
    	for(int i=0; i<this.coef.length; i++) {
    		//on evalue chaque terme un par un
    		retour = retour + this.coef[i]*Math.pow(x, i);
    	}
    	return retour;
    }
    
    /**
     * Copie le double[] des coefficient. Peut etre utilise pour creer un nouveau polynome.
     * @return
     */
    public double[] getCoef(){
        return this.coef.clone();
    }
    
    /**
     * Cree une copie du Polynome.
     */
    @Override
    public Poly clone() {
    	return new Poly(this.coef.clone());
    }
    
    /**
     * Additionne deux polynomes.
     * @param autre
     * @return
     */
    public Poly add(Poly autre){
    	// coefAutre sera redefini avec les nouveaux coeficients
        double[] coefAutre = autre.getCoef();
        double[] coefThis = this.coef;
        
        if (coefAutre.length > coefThis.length){
        	//on ajoute des zeros a la fin de coefThis
            coefThis = resizeArray(coefThis, coefAutre.length);
        }
        else if(coefAutre.length < coefThis.length){
        	//on ajoute des zeros a la fin de coefAutre
        	coefAutre = resizeArray(coefAutre, coefThis.length);
        }
        //Maintenant coefAutre et coefThis font la meme taille
        for(int i=0; i<coefAutre.length; i++) {
        	coefAutre[i] = coefAutre[i] + coefThis[i];
        }
        return new Poly(coefAutre);
    }
    /**
     * Retourne le polynome oppose.
     * @return
     */
    public Poly inv() {
    	double[] newCoef = this.getCoef();
    	for(int i=0; i<newCoef.length; i++) {
    		newCoef[i] = -newCoef[i];
    	}
    	return new Poly(newCoef);
    }
    
    /**
     * Soustrait le polynome par le polynome autre.
     * @param autre
     * @return
     */
    public Poly substr(Poly autre) {
    	return this.add(autre.inv());
    }
    
    /**
     * Representqtion d'un polynome sous forme de chaine de caracteres.
     */
    @Override
    public String toString(){
        return "Poly " + reprCoef(this.coef);
    }
    
    /**
     * Creer la chaine de caractere representant le tableau coef.
     * @param coef
     * @return La representation de coef.
     */
    private static String reprCoef(double[] coef){
        String repr = "[";
        for(double nb: coef){
            repr += nb + ", ";
        }
        repr = repr.substring(0,repr.length()-2) + "]";
        return repr;
    }

    /**
     * Tronque le tableau ou ajoute des zeros a la fin pour le faire corespondre a la taille indiquee
     * @param array
     * @param taille
     * @return
     */
    private static double[] resizeArray(double[] array, int taille){
        double[] retour = new double[taille];
        for(int i=0; i<taille && i<array.length; i++){
            retour[i] = array[i];
        }
        return retour;
    }


}