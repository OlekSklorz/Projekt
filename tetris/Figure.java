package tetris;

import java.awt.Color;
import java.awt.Component;
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
     * Pobiera położenie figury w poziomie. 
     * @return położenie w poziome. 
     */
    public int getLeftX(){
        return leftX;
    }
    
    /**
     * Pobiera położenie figury w pionie. 
     * @return położenie w pionie. 
     */
    public int getTopX(){
        return topX;
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
    
    public int getActualLeftX(){
        for(int w = 0; w < elements.length; w++){
            for(int k = 0; k < elements[w].length; k++){
                if(elements[w][k] != null)
                    return elements[w][k].getLeftX();
            }
        }
        return -1;
    }
    
    /**
     * Pobiera wysokość figury. 
     * @return wysokość figury. 
     */
    public int getHeightFigure(){
        return x * Element.getHeight();
    }
    
    /**
     * Pobiera szerokość figury. 
     * @return szerokość figury. 
     */
    public int getWidthFigure(){
        return y * Element.getWidth();
    }
    
    /**
     * Usuwa wiersz elementów z danej figury. 
     * @param oldX numer wiersza do usunięcia. 
     */
    public void deleteElement(int oldX){
        for(int i = 0; i < elements[oldX].length; i++){
            if(elements[oldX][i] != null){
                elements[oldX][i] = null;
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
                if(elements[w][k] != null && elements[w][k].getTopX() == line)
                    return w;
                k++;
            }while(k < elements[w].length);
            w++;
        }while(w < elements.length);
        return -1;
    }
    
    /**
     * Wstawia pod podane indeksy, podany element. 
     * @param newX położenie pionowe.
     * @param newY położenie poziome.
     * @param el element do wstawienia. 
     */
    public void setElements(int newX, int newY, Element el){
        elements[newX - 1][newY] = null;
        elements[newX][newY] = el;
        if(elements[newX][newY] != null)
            elements[newX][newY].addLeftTop(0, 20);
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
    
    public void rotate(double angle, GameField c, int limit){
        boolean received = false, obstacle = true;
        int oldX = 0, oldY = 0, newX, newY, tempX = x, tempY = y, horizontal, vertical;
        for(int w = 0; w < elements.length; w++)
            for(int k = 0; k < elements[w].length; k++)
                if(!received && elements[w][k] != null){
                    received = true;
                    oldX = elements[w][k].getLeftX();
                    oldY = elements[w][k].getTopX();
                }
        for(int w = elements.length - 1; w >= 0; w--){
            obstacle = false;
            for(int k = 0; k < elements[w].length; k++){
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
                    if(newX >= 0){
                        elements[w][k].setLeftTop(newX, newY);
                    }else{
                        obstacle = true;
                        break;
                    }
                }
            }
            if(obstacle) break;
        }
        
        boolean is = false;
        for(int w = 0; w < elements.length; w++){
            for(int k = 0; k < elements[w].length; k++){
                if(elements[w][k] != null && c.isComponent(elements[w][k].getTopX(), elements[w][k].getLeftX(), limit)){
                    is = true;
                    break;
                }
            }
            if(is) break;
        }
        if(!obstacle){
            position++;
            position %= 4;
            x = tempY;
            y = tempX;
        }
    }
    
    public int getPosition(){
        return position;
    }
    
    public void setPosition(int position){
        this.position = position;
    }
}
