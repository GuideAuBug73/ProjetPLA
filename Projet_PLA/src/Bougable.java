import basic.*;
import basic.Orientation.orientation;

public class Bougable extends Entity{
	 
	

		void Avancer() {
		     
			switch(this.O.orient) {
			  
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
		    
			 
			
			switch(this.O.orient) {
			  
				case Nord : 
					this.O.orient = orientation.Est;
					break ;
				
				case Sud  :	
					this.O.orient = orientation.West;
				     break ;
				     
				case Est  :	
					this.O.orient = orientation.Sud;
				     break ;
				  
				case West  :	
					this.O.orient = orientation.Nord;
				     break ;
			}
		   
		} 
		
void Rotation_gauche() {
		    
			 
			
			switch(this.O.orient) {
			  
				case Nord : 
					this.O.orient = orientation.West;
					break ;
				
				case Sud  :	
					this.O.orient = orientation.Sud;
				     break ;
				     
				case Est  :	
					this.O.orient = orientation.Nord;
				     break ;
				  
				case West  :	
					this.O.orient = orientation.Est;
				     break ;
			}
		   
		} 
		
		
}
