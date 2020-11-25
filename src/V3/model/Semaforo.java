package V3.model;

public class Semaforo {
	private int Recursos;
	
	public Semaforo(int Recursos){
		this.Recursos=Recursos;
	}
	
	public synchronized void Espera(){
		while (Recursos<1) {
			try {
				System.out.println("No hay robots disponibles.");
				wait();
				System.out.println("Checando si hay robots disponibles...");
			} catch (InterruptedException e) {
			}
		}
		System.out.println("Si hay robots disponibles.");
		Recursos--;
	
	}
	
	public synchronized void Libera(){
		System.out.println("No necisto mas el robot, quien lo quiere?");
		Recursos++;
		notifyAll();
	}
}
