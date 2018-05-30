import basic.*;
import basic.Orientation.nsew;

public class Bougable extends Entity{
	 
	

		void Avancer(Entity uraz ) {
		     
			switch(uraz.O.yon) {
			  
				case Nord : 
					uraz.p.y++;
					break ;
				
				case Sud  :	
				     uraz.p.y--;
				     break ;
				     
				case Est  :	
				     uraz.p.x++;
				     break ;
				  
				case West  :	
				     uraz.p.x--;
				     break ;
			}
		   
		} 
		
		void Rotation_droite(Entity uraz ) {
		    
			 
			
			switch(uraz.O.yon) {
			  
				case Nord : 
					uraz.O.yon = nsew.Est;
					break ;
				
				case Sud  :	
					uraz.O.yon = nsew.West;
				     break ;
				     
				case Est  :	
					uraz.O.yon = nsew.Sud;
				     break ;
				  
				case West  :	
					uraz.O.yon = nsew.Nord;
				     break ;
			}
		   
		} 
		
void Rotation_gauche(Entity uraz ) {
		    
			 
			
			switch(uraz.O.yon) {
			  
				case Nord : 
					uraz.O.yon = nsew.West;
					break ;
				
				case Sud  :	
					uraz.O.yon = nsew.Sud;
				     break ;
				     
				case Est  :	
					uraz.O.yon = nsew.Nord;
				     break ;
				  
				case West  :	
					uraz.O.yon = nsew.Est;
				     break ;
			}
		   
		} 
		
		
}
