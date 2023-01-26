package ke6ea3b;

public enum EStatus {
	
PHILOSOFY('p'), HUNGRY('h'), EATING('e'), DEAD('d');
	
	private volatile char status;
	
	private EStatus(char symbol) {
		this.status = symbol;
	}
	
	public char getStatus() {
		return status;
	}
}
