
public class TestBousole {
	public static void main(String [] args) {
		Bousole B = new Bousole(0);
		System.out.println(B.nouvelAngle(270));
		
		B.orienter(90);
		System.out.println(B.orientation);
	}
}
