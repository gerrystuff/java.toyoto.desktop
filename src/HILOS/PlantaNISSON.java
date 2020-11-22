package HILOS;
import model.Semaforo;

import java.util.*;
class Linea extends Thread {
	static Semaforo se1=null;
	static Semaforo se2,se2t,se3,se4,se5,se6,s;
	static int contProduccion;
	public Linea() {
		if(se1==null) {
			se1=new Semaforo(5);
			se2=new Semaforo(4);
			se2t=new Semaforo(2);
			se3=new Semaforo(3);
			se4=new Semaforo(3);
			se5=new Semaforo(2);
			se6=new Semaforo(5);
			s=new Semaforo(1);
			contProduccion=0;
		}
		
	}


	public void run() {

		System.out.println(getName()+" Inicia la linea de produccion " );
		while (true) {
			s.Espera();
            if(contProduccion>=1000) {
            	s.Libera();
            	return;
            }
			contProduccion++;
			System.out.println(getName()+" FABRICANDO EL CARRO #  "+contProduccion);
			s.Libera();
			se1.Espera();
			System.out.println(this.getName()+": Linea  esta instalando chasis del carro #" + contProduccion);
			se1.Libera();
			se2.Espera();
			System.out.println(this.getName()+": Linea  esta instalando el motor del carro #" + contProduccion);
			se2t.Espera();
			se2.Libera();
			System.out.println(this.getName()+": Linea  esta instalando la transmision del carro #"+ contProduccion);
			se2t.Libera();

			se3.Espera();
			System.out.println(this.getName()+": Linea esta instalando la carroceria del carro #"+ contProduccion);
			se3.Libera();	   

			se4.Espera();
			System.out.println(this.getName()+": Linea esta instalando los interiores del carro #" + contProduccion);
			se4.Libera();	   

			se5.Espera();
			System.out.println(this.getName()+": Linea esta instalando llantas del carro #"+ contProduccion);
			se5.Libera();

			se6.Espera();
			System.out.println(this.getName()+": Linea  esta instalando las pruebas del carro #"+ contProduccion);
			se6.Libera();
		}


	}


}
public class PlantaNISSON {
	
	public static void main(String [] a) {
		Linea [] l= new Linea[5];
		for(int i=0 ;  i<l.length ; i++)
			l[i]=new Linea();
		for(int i=0 ;  i<l.length ; i++)
			l[i].start();
		
		
	}

}
