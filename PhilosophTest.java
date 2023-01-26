package ke6ea3b;

public class PhilosophTest {
	
	public static void main(String[] args) {

		Tisch table = new Tisch();
		
		//Thread der Philosopheen
		Thread marx = new Thread(new Philosoph("Karl Marx", table, 0, 1));
		Thread engels = new Thread(new Philosoph("Friedich Engels", table, 1, 2));
		Thread kant = new Thread(new Philosoph("Immanuel Kant", table, 2, 3));
		Thread bakunin = new Thread(new Philosoph("Michail Bakunin", table, 3, 4));
		Thread freud = new Thread(new Philosoph("Siegmund Freud", table, 4, 0));
		
		System.out.println("Die Philosophen setzen sich an den Tisch");
		
		marx.start();
		engels.start();
		kant.start();
		bakunin.start();
		freud.start();

		System.out.println("BREAKPOINT!");
	}

}
