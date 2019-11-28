public class Bousole {
	int orientation;
	
	public Bousole (int orientation) {
		this.orientation=orientation;
	}
	
	public Bousole () {
		orientation=0;
	}
	
	public void orienter (int angle) { // additionne les 2 angles 
		if(angle<0) {
			orientation=((360+orientation)+angle)%360;
		}
		else{
			orientation=(orientation+angle)%360;
		}
	}
	
	public int nouvelAngle (int angle) { // met la valeur de l'ancien angle à la valeur du nouvel angle
		int orien2=orientation;
		orientation=angle;
		int res = orien2-angle;
		return -res;
	}
}
