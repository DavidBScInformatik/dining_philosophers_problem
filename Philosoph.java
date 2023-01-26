package ke6ea3b;

import java.util.Random;

public class Philosoph implements Runnable {

	String name;
	Tisch tisch;
	EStatus status = EStatus.PHILOSOFY;
	boolean leftHand = false;
	boolean rightHand = false;
	int leftChopstick;
	int rightChopStick;
	int counter = 0;
	Random rng = new Random();
	int i = 0;

	public Philosoph(String name, Tisch tisch, int lChop, int rChop) {
		this.name = name;
		this.tisch = tisch;
		this.leftChopstick = lChop;
		this.rightChopStick = rChop;
	}

	@Override
	public void run() {

		boolean waited = false;

		while (this.status != EStatus.DEAD) {
			switch (this.status) {
				case PHILOSOFY:
					System.out.println(this.name + " denkt nach...");
					this.takeABreak(10000);
					this.status = EStatus.HUNGRY;
					break;
				case HUNGRY:
					if (this.leftHand == false) {
						if (this.getLeftChopstick()) {
							this.leftHand = true;
							this.takeABreak(5000);
						} else {
							System.out.println(
									this.name + " hatte keinen Erfolg. Er denkt noch weiter nach und isst später.");
							if(this.counter < 3) {
								this.status = EStatus.PHILOSOFY;
								this.counter++;
							}else {
								System.out.println(this.name + " ist verhunger... x.x");
								this.status = EStatus.DEAD;
							}
							break;
						}
					}

					if (this.leftHand == true && this.rightHand == false) {
						if (this.getRightChopstick()) {
							this.rightHand = true;
							this.takeABreak(5000);
						} else {
							if (waited) {
								// Wenn einmal gewartet wurde soll das Stäbchen abgelebt werden wenn es noch
								// nicht frei ist
								System.out.println(this.name + " denkt weiter nach und isst später.");
								this.leftHand = false;
								this.tisch.staebchen[this.leftChopstick][0] = 1;
								this.status = EStatus.PHILOSOFY;
								break;
							} else {
								System.out.println(this.name + " rechts Stäbchen ist bereits vergriffen. Er wartet...");
								this.takeABreak(5000);
								waited = true;
								break;
							}
						}
					}

					if (this.leftHand == true && this.rightHand == true) {
						this.status = EStatus.EATING;
					}
					break;

				case EATING:
					System.out.println(this.name + " isst.");
					this.takeABreak(20000);
					this.layDownChopsticks();
					this.takeABreak(30000);
					this.status = EStatus.PHILOSOFY;
					this.counter = 0;
					break;
			}

			i++;
		}
	}

	/**
	 * Zwing den Philosophen eine kurze Pause zu machen (Beim denken oder beim
	 * essen)
	 */
	private void takeABreak(int i) {
		try {
			Thread.sleep(rng.nextInt(i));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// TODO
	private synchronized boolean getLeftChopstick() {
		System.out.println(this.name + " greift zum linken Stäbchen");
		if (this.tisch.staebchen[this.leftChopstick][0] == 1) {
			this.tisch.staebchen[this.leftChopstick][0] = 0;
			this.leftHand = true;
			return true;
		} else {
			System.out.println(this.name + " muss warten. Das linke Stäbchen ist vergriffen...");
			this.takeABreak(10000);
			return false;
		}
	}

	// TODO
	private synchronized boolean getRightChopstick() {
		System.out.println(this.name + " greift zum rechten Stäbchen");
		if (this.tisch.staebchen[this.rightChopStick][0] == 1) {
			this.tisch.staebchen[this.rightChopStick][0] = 0;
			this.rightHand = true;
			return true;
		} else {
			System.out.println(this.name + " muss warten. Das rechte Stäbchen ist vergriffen...");
			this.takeABreak(10000);
			return false;
		}
	}

	private synchronized void layDownChopsticks() {
		System.out.println(this.name + " hat gegessen und legt die Stäbchen weg.");
		this.tisch.staebchen[this.leftChopstick][0] = 1;
		this.tisch.staebchen[this.rightChopStick][0] = 1;
		this.leftHand = false;
		this.rightHand = false;
		notifyAll();
		
		System.out.println("BREAK!");
	}
}

