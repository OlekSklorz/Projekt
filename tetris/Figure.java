package tetris;

import java.awt.Color;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>Figure</code> reprezentuje dowolną figurę o podanych wymiarach.
 * Jej kolor jest dobierany losowo. 
 */
public abstract class Figure extends JComponent{
    protected int leftX, topX;
    protected Element[][] elements;
    protected Color color;
    private int x, y;
    private boolean stopMovement = false;
    private int position;
    public Figure(int leftX, int topX, int x, int y){
        this.leftX = leftX;
        this.topX = topX;
        this.x = x;
        this.y = y;
        this.elements = new Element[x][y];
        color = MyColors.getColor(new Random().nextInt(12));
        position = 0;
    }
    
    /**
     * Przemieszcza figurę poziomo i pionowo. 
     * @param x przemieszczanie poziomo. 
     * @param y przemieszczenie pionowo. 
     */
    public void move(int x, int y){
        for(int i = 0; i < elements.length; i++)
            for(int k = 0; k < elements[i].length; k++){
                if(elements[i][k] != null)
                    elements[i][k].addLeftTop(x, y);
            }
    }
    
    /**
     * Pobiera wszystkie elementy (kwadraciki) z których złożona jest figura. 
     * @return tablica składowych figury. 
     */
    public Element[][] getElements(){
        return elements;
    }
    
    /**
     * Pobiera kolor figury.
     * @return kolor figury.
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * Pobiera aktualne położenie figury w pionie. 
     * @return aktualne położenie figury w pionie. 
     */
    public int getActualTopX(){
        //return elements[0][0].getTopX();
        for(int w = 0; w < elements.length; w++){
            for(int k = 0; k < elements[w].length; k++){
                if(elements[w][k] != null)
                    return elements[w][k].getTopX();
            }
        }
        return -1;
    }
    
    /**
     * Usuwa wiersz elementów z danej figury. 
     * @param oldX numer wiersza do usunięcia. 
     */
    public void deleteElement(int oldX){
        if(position == 0 || position == 2)
        for(int i = 0; i < elements[oldX].length; i++){
            if(elements[oldX][i] != null){
                elements[oldX][i] = null;
            }
        }
        if(position == 1 || position == 3)
            for(int i = 0; i < elements.length; i++){
            if(elements[i][oldX] != null){
                elements[i][oldX] = null;
            }
        }
    }
    
    /**
     * Pobiera indeks wiersza o podanych współrzędnych. Będzie to wiersz do usunięcia.
     * @param line współrzędna pionowa usuwanego wiersza. 
     * @return indeks usuwanego wiersza. 
     */
    public int getNumberDeletedLine(int line){
        int w = 0, k;
        do{
            k = 0;
            do{
                if(elements[w][k] != null && elements[w][k].getTopX() == line && (position == 0 || position == 2))
                    return w;
                if(elements[w][k] != null && elements[w][k].getTopX() == line && (position == 1 || position == 3))
                    return k;
                k++;
            }while(k < elements[w].length);
            w++;
        }while(w < elements.length);
        return -1;
    }
    
    /**
     * Ustawia stan poruszania się. Jeśli true - figura nie porusza się, 
     * jeśli false - figura porusza się. 
     * @param stopMovement stan poruszania się. 
     */
    public void setStopMovement(boolean stopMovement){
        this.stopMovement = stopMovement;
    }
    
    /**
     * Pobiera stan poruszania się. Jeśli true - figura nie porusza się, 
     * jeśli false - figura porusza się. 
     * @return stan poruszania się. 
     */
    public boolean getStopMovement(){
        return stopMovement;
    }
    
    /**
     * Obrócenie figury względem lewego górnego rogu, pierwszego kwadracika, 
     * zgodnie z ruchem zegara, jeśli kąt większy od zera, natomiast
     * przeciwnie do ruchu zegara jeśli kąt mniejszy od zera. 
     * @param angle kąt obrotu figury.
     */
    public void rotate(double angle){
        boolean received = false;
        int oldX = 0, oldY = 0, newX, newY, tempX = x, tempY = y, horizontal, vertical;
        int w = 0, k;
        do{
            k = 0;
            do{
                if(elements[w][k] != null){
                    received = true;
                    oldX = elements[w][k].getLeftX();
                    oldY = elements[w][k].getTopX();
                }
                k++;
            }while(k < elements[w].length && !received);
        }while(w < elements.length && !received);
        for(w = elements.length - 1; w >= 0; w--){
            for(k = 0; k < elements[w].length; k++){
                if(elements[w][k] != null){
                    horizontal = elements[w][k].getLeftX();
                    vertical = elements[w][k].getTopX();
                    newX = (int)((horizontal - oldX) * Math.cos(angle) + (-vertical + oldY) * Math.sin(angle) + oldX);
                    if(newX % 20 != 0){
                        newX++;
                        if(newX % 20 != 0) newX -= 2;  
                    }
                    x = tempY;
                    newY = (int)((horizontal - oldX) * Math.sin(angle) - (-vertical + oldY) * Math.cos(angle) + oldY);
                    if(newY % 20 != 0){
                        newY++;
                        if(newY % 20 != 0) newY -= 2;   
                    }
                    elements[w][k].setLeft(newX);
                    elements[w][k].setTop(newY);
                }
            }
        } 
        if(this instanceof FigureZ){
            FigureZ tempFigure = (FigureZ)this;
            if(tempFigure.getStart()){
                tempFigure.setStart(false);
            }else{
                if((position == 1 || position == 3) && angle >= 0){
                    elements[0][0].addLeftTop(-20, 0);
                    elements[1][0].addLeftTop(-20, 0);
                    elements[1][1].addLeftTop(20, 0);
                    elements[2][1].addLeftTop(20,0);
                }else{
                    if((position == 2 || position == 4) && angle < 0 ){
                        elements[0][0].addLeftTop(20, 0);
                        elements[1][0].addLeftTop(20, 0);
                        elements[1][1].addLeftTop(-20, 0);
                        elements[2][1].addLeftTop(-20,0);
                    }
                }
            }
        }
        if(angle >= 0){
            position++;
            position %= 4;
        }else{
            position--;
            if(position < 0)
                position = 3;
        }
        x = tempY;
        y = tempX;
    }
    
    /**
     * Pobiera pozycję figury. Pozycje numerowane od 0 do 3 (0 - początek, 3 - koniec)
     * @return numer pozycji figury. 
     */
    public int getPosition(){
        return position;
    }
}

