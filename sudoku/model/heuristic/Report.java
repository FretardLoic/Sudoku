package sudoku.model.heuristic;


import java.awt.Color;
import java.util.Map;
import java.util.Set;

import sudoku.model.history.cmd.Command;
import sudoku.util.ICoord;

public interface Report {

	public enum CellSetName {
		DECISIVE_CELLS(new Color(80, 80, 255)),
		DECISIVE_UNITS(new Color(150, 150, 255)),
		DELETION_CELLS(new Color(255, 80, 80)),
		DELETION_UNITS(new Color(255, 150, 150));
		
		private Color color;
		
		CellSetName(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return color;
		}
	}
	
	
	//REQUETES
	/**
	 * Renvoie une description d'une modification possible du sudoku.
	 */
	String describe();
	
	/**
	 * Renvoie les ensembles de cellules importants pour la règle utilisée.
	 */
	Map<CellSetName, Set<ICoord>> importantSets();
	
	//COMMANDES
	/**
	 * Renvoie la commande à executer pour faire les changements indiquer par la 
	 * regle.
	 */
	Command generateCommand();
}