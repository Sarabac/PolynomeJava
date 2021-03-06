package poly;

/**
 * <p>Une classe permettant de manipuler et evaluer des polynomes.</p>
 * <p>(Voir la page wikipedia: {@link <a>https://fr.wikipedia.org/wiki/Polyn%C3%B4me</a>})</p>
 * <p>La liste des coeficients doit etre un double[] allant du coefficient le plus faible
 * au plus fort.</p>
 * @author lboutarfa
 *
 */
public class Poly implements Cloneable{
	/**
	 * La liste des coefficients.
	 * Suffit pour representer un Polynome.
	 * test du reset 
	 */
    private double[] coef;

    public Poly(double[] coef){
    	// si le tableau se termine par des 0, ils sont retires
    	// on utilise i>0 pour garder la possibilitee [0]
    	int taille = coef.length;
    	for(int i = coef.length-1; i>0; i--) {
    		if(coef[i]!=0) {
    			break;
    		}
    		taille = i;
    	}
        this.coef = resizeArray(coef, taille);
    }
    
    public Poly(double coef) {
    	this.coef = new double[]{coef};
    }
    
    public Poly(double coef1, double coef2) {
    	if(coef2==0) {
    		this.coef = new double[]{coef1};
    	}else{
    		this.coef = new double[]{coef1, coef2};
    	}
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
     * Donne le rang du polynome.
     * test autre
     * @return rang en sous forme de int
     */
    public int rang() {
    	return this.coef.length;
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
    	return new Poly(this.getCoef());
    }
    
    /**
     * Donne le polynome derive.
     * @return
     */
    public Poly derivee() {
    	double[] coef = this.getCoef();
    	for(int i=1; i<coef.length; i++) {
    		coef[i-1] = i*coef[i];
    	}
    	coef[coef.length-1] = 0;
    	//Le dernier coeficient null sera retire par le constructeur
    	return new Poly(coef);
    }
    
    /**
     * Donne un polynome integral du polynome.
     * @param constante
     * @return une primitive
     */
    public Poly integrale(double constante) {
    	double[] newCoef = new double[this.coef.length+1];
    	newCoef[0] = constante;
    	for(int i=1; i<newCoef.length; i++) {
    		newCoef[i] = this.coef[i-1]/i;
    	}
    	return new Poly(newCoef);
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