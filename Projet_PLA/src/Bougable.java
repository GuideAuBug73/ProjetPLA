import basic.*;
import basic.Orientation.nsew;

public class Bougable extends Entity{
	 
	

		void Avancer() {
		     
			switch(this.O.yon) {
			  
				case Nord : 
					this.p.y++;
					break ;
				
				case Sud  :	
				     this.p.y--;
				     break ;
				     
				case Est  :	
				     this.p.x++;
				     break ;
				  
				case West  :	
				     this.p.x--;
				     break ;
			}
		   
		} 
		
		void Rotation_droite() {
		    
			 
			
			switch(this.O.yon) {
			  
				case Nord : 
					this.O.yon = nsew.Est;
					break ;
				
				case Sud  :	
					this.O.yon = nsew.West;
				     break ;
				     
				case Est  :	
					this.O.yon = nsew.Sud;
				     break ;
				  
				case West  :	
					this.O.yon = nsew.Nord;
				     break ;
			}
		   
		} 
		
void Rotation_gauche() {
		    
			 
			
			switch(this.O.yon) {
			  
				case Nord : 
					this.O.yon = nsew.West;
					break ;
				
				case Sud  :	
					this.O.yon = nsew.Sud;
				     break ;
				     
				case Est  :	
					this.O.yon = nsew.Nord;
				     break ;
				  
				case West  :	
					this.O.yon = nsew.Est;
				     break ;
			}
		   
		} 
		
		
}
