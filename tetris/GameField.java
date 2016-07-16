package tetris;

import java.awt.*;
import java.util.*;
import javax.swing.JComponent;

/**
 * Obiekt <code>GameField</code> reprezentuje główne pole w którym rozgrywa się gra. 
 * W tym polu rysowane są kolejne elementy gry.
 */
public class GameField extends JComponent {
    private ArrayList<Figure> figures = new ArrayList();
    public void add(Figure figure){
        figures.add(figure);
    }
    /**
     * Rysuje wszystkie elementy które występują w grze.
     * @param g obiekt Graphics służący do operacji na figurach. 
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(Figure figure : figures){
            g2.setPaint(figure.getColor());
            Element[][] elements = figure.getElements();
            for(int i = 0; i < elements.length; i++)
                for(int k = 0; k < elements[i].length; k++){
                    if(elements[i][k] != null)
                        g2.fill(elements[i][k].getElement());
                }
            g2.setPaint(Color.BLACK);
            for(int i = 0; i < elements.length; i++)
                for(int k = 0; k < elements[i].length; k++){
                    if(elements[i][k] != null)
                        g2.draw(elements[i][k].getElement());
                }
        }
    }
    
    /**
     * Sprawdza czy w punkcie o danych współrzędnych x i y znajduje się 
     * jakaś figura która występuje w liście przed indeksem limit. 
     * @param x współrzędna pozioma. 
     * @param y współrzędna pionowa. 
     * @param limit indeks ograniczający do którego miejsca z listy mają być pobrane figury.
     * @return informacja czy w danym punkcie znajduje się jakaś figura. 
     */
    public boolean isComponent(int x, int y, int limit){
        boolean is = false;
        int i = 0; 
        Figure figure;
        while(i < limit && !is){
            figure = figures.get(i);
            int w = 0;
            Element[][] elements = figure.getElements();
            do{
                int k = 0;
                do{
                    if(elements[w][k] != null && elements[w][k].getTopX() == x && elements[w][k].getLeftX() == y){
                        is = true;
                    }
                    k++;
                }while(k < elements[w].length && !is);
                w++;
            }while(w < elements.length && !is);
            i++;
        }
        return is;
    }
    
    /**
     * Pobiera informacje o numerze zapełnionego przez elementy wiersza. 
     * Wiersze numerowane od góry w dół. 
     * @param limit określa do którego indeksu mają być pobierane figury z listy.
     * @return numer zapełnionego wiersza. 
     */
    public Integer checkLine(int limit){
        ArrayList<Integer> coordinates = getCoordinates(limit);
        int counter;
        for(Integer c : coordinates){
            counter = 1;
            for(int i = coordinates.indexOf(c) + 1; i < coordinates.size(); i++){
                if(c.equals(coordinates.get(i))){
                    counter++;
                }
                if(counter == 10){
                    return c/Element.getHeight();
                }
                    
            }
        }
        return -1;
    }
    
    /**
     * Usuwa zapełnioną linię. 
     * @param limit określa do którego indeksu mają być pobierane figury z listy.
     * @param line podaje numer zapełnionej linii (linii do usuniecia). 
     */
    public void deleteLine(int limit, int line){
        int x = line * Element.getHeight();
        int i = 0;
        int counter = 0;
        Figure figure;
        Element[][] elements;
        boolean deleted;
        int w, k;
        while(i < limit){
            figure = figures.get(i);
            elements = figure.getElements();
            deleted = false;
            w = 0;
            do{
                k = 0;
                do{
                    if(elements[w][k] != null && elements[w][k].getTopX() == x){
                        figure.deleteElement(w);
                        deleted = true;
                    }
                    k++;
                }while(k < elements[w].length && !deleted);
                w++;
            }while(w < elements.length && !deleted);
            i++;
        }
    }
    
    private ArrayList<Integer> getCoordinates(int limit){
        ArrayList<Integer> coordinates = new ArrayList();
        int i = 0;
        Element[][] elements;
        while(i < limit){
            elements = figures.get(i).getElements();
            for(int w = 0; w < elements.length; w++){
                for(int k = 0; k < elements[w].length; k++){
                    if(elements[w][k] != null){
                        coordinates.add(elements[w][k].getTopX());
                    }
                }     
            }
            i++;
        }
        return coordinates;
    }
    
    /**
     * Pobiera preferowany rozmiar pola do gry. 
     * @return preferowany rozmiar pola do gry. 
     */
    public Dimension getPreferredSize(){
        return new Dimension(200, 561);
    }
}

