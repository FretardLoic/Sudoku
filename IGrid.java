package sudoku.model;

import java.io.Serializable;
import java.util.Set;

import sudoku.util.ICoord;

/**
 * @inv <pre>
 *	    getNumberSectorByWidth() > 0  && getNumberSectorByHeight() > 0 && size() ==  getNumberSectorByWidth() * getNumberSectorByHeight()
 * 		isFull() <==> forall ICell cell in cells() : cell.hasValue()
 * 		forall ICoord c : getCell(c) <==> cells[c.getCol()][c.getRow()]
 * 		size() == getNumberSectorByHeight() * getNumberSectorByWidth()
 * 		cells().size() == size() 
 * 		numberPossibility() == size()
 * 		getWidthSector() == getNumberSectorByHeight()
 * 		getHeightSector() == getNumberSectorByWidth()
 * 		cells() != null
 * 		getUnitCells(c) = getCol(c) U  getRow(c) U getSector(c)
 *      </pre>
 * 
 * @cons <pre>
 *     $DESC$ Une grille de taille width * height
 *     
 *     $ARGS$ int width, int height
 *     
 *     $PRE$ 
 *         width > 0  && height > 0
 *         
 *     $POST$ 
 *         getNumberSectorByWidth() == width
 *         getNumberSectorByHeight() == height
 *         forall int i,j : cells[i][j].getValue() == 0 &&
 *         				    cells[i][j].isModifiable()
 *    </pre>
 *    
 */
public interface IGrid extends Serializable, Cloneable {
	
	//REQUÊTES
	/**
	 * Retourne la taille de la grille
	 */
	int size();
	
	/**
	 * Retourne le nombre de régions par largeur  
	 */
	int getNumberSectorByWidth();
	
	/**
	 * Retourne le nombre de régions par hauteur
	 */
	int getNumberSectorByHeight();
	
	/**
	 * Retourne le nombre de possibilité de la grille
	 */
	int numberPossibility();
	
	/**
	 * Retourne la largeur d'une région
	 */
	int getWidthSector();
	
	/**
	 * Retourne la hauteur d'une région
	 */
	int getHeightSector();
	
	/**
	 * Retourne le tableau de cellules
	 */
	ICell[][] cells();
	
	/**
	 * Retourne la cellule à coordonnée c.
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * </pre>
	 */
	ICell getCell(ICoord coord);
	
	/**
	 * Retourne si la grille est complète
	 */
	boolean isFull();
	
	/**
	 * Retourne l'ensemble des cellules de la ligne d'où se trouve la 
	 * coordonnée coord
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * </pre>
	 */
	Set<ICell> getRow(ICoord coord);
	
	/**
	 * Retourne l'ensemble des cellules de la colonne d'où se trouve la 
	 * coordonnée coord
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * </pre>
	 */ 
	Set<ICell> getCol(ICoord coord);
	
	/**
	 * Retourne l'ensemble des cellules de la région d'où se trouve la 
	 * coordonnée coord
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * </pre>
	 */
	Set<ICell> getSector(ICoord coord);
	
	/**
	 * Retourne si les composantes de la coordonnée coord sont valides entre 0 et size().
	 * @pre : <pre>
	 * 		coord != null
	 * </pre>
	 */
	boolean isValidCoord(ICoord coord);
	
	/**
	 * Retourne l'ensemble des cellules  présentes sur la ligne, colonne et région de la coordonnée.
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * </pre>
	 */
	Set<ICell> getUnitCells(ICoord coord);
	
	//COMMANDES
	
	/**
	 * Efface toutes les valeurs de la grille qui ne sont pas celle de
	 * départ
	 * @post
	 * 		forall int i,j : cells[i][j].isModifiable() => cells[i][j].getValue() == 0
	 */
	void reset();
	
	/**
	 * Efface toutes les valeurs de la grille.
	 * @post :<pre>
	 * 		forall int i,j : cells[i][j].getValue() == 0 &&
	 *         				 cells[i][j].isModifiable()
	 * </pre>
	 */
	void clear();
	
	/**
	 * Change la valeur de la cellule de coord par value.
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * 		1 <= value <= numberPossibility()
	 * </pre>
	 * @post <pre>
	 * 		getCell(coord).getValue() == value
	 * </pre>
	 */
	void changeValue(ICoord coord, int value);
	
	/**
	 * Réinitialise la valeur de la cellule de coord par 0.
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * </pre>
	 * @post <pre>
	 * 		getCell(coord).getValue() == 0
	 * </pre>
	 */
	void resetValue(ICoord coord);
	
	/**
	 * Ajoute la valeur value dans les possibilités la cellule de coord.
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * 		1 <= value <= numberPossibility()
	 * </pre>
	 * @post <pre>
	 * 		getCell(coord).possibility()[value - 1]
	 * </pre>
	 */
	void addPossibility(ICoord coord, int value);
	
	/**
	 * Supprime la valeur value dans les possibilités la cellule de coord.
	 * @pre : <pre>
	 * 		coord != null
	 * 		isValidCoord(coord)
	 * 		1 <= value <= numberPossibility()
	 * </pre>
	 * @post <pre>
	 * 		! getCell(coord).possibility()[value - 1]
	 * </pre>
	 */
	void removePossibility(ICoord coord, int value);	
	
	/**
	 * Change le tableau de cellules par tabCells.
	 * @pre <pre>
	 * 		tabCells != null
	 *		forall  cell : tabCells, cell != null
	 *		tabCells.size() == size()
	 * </pre>
	 * @post <pre>
	 * 		cells() == tabCells
	 * </pre>
	 */
	void changeCells(ICell[][] tabCells);
}
