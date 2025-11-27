package Box;
import Point.Point;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private List<Point> points;
    private List<Box> boxes;
    private final int maxSize; //вместимость
    private final boolean isParent; //хранит точки или коробки

    public Box(int size, boolean isparent){
        if(size<=0){throw new IllegalArgumentException("Размер коробки должен быть больше нуля!");}

        this.maxSize = size;
        this.isParent = isparent;

        if (isparent){ //если хранит коробки
            this.boxes = new ArrayList<Box>();
            this.points = null;
        } else { //хранит точки
            this.points = new ArrayList<Point>();
            this.boxes = null;
        }
    }

    public int getMinX(){
        return this.minX;
    }

    public int getMaxX(){
        return this.maxX;
    }

    public int getMinY(){
        return this.minY;
    }

    public int getMaxY(){
        return this.maxY;
    }

    public int getMaxSize(){
        return this.maxSize;
    }

    public boolean isParent(){
        return this.isParent;
    }

    public List<Box> getBoxes() {
        return this.boxes;
    }

    public List<Point> getPoints() {
        return this.points;
    }

    /////////////////////////////////////////////////////////// методы ///////////////////////////////


    public boolean isBoxEmpty(){
        if (this.isParent){
            if (boxes==null){return true;}
            if (boxes.isEmpty()){return true;}
            return false;
            }

        else {
            if (points==null){return true;}
            if (points.isEmpty()){return true;}
            return false;
        }
    }


    public int getCurrentSize() {
        if (this.isParent) {
            if (boxes == null) return 0;
            return boxes.size();

        } else {
            if (points == null) return 0;
            return points.size();
        }
    }


    public void makeBoxBigger(int x, int y){
        if (isBoxEmpty()){
            minX = x;
            maxX = x;
            minY = y;
            maxY = y;
        } else {
            if (x<minX){minX=x;}
            if (x>maxX){maxX=x;}
            if (y<minY){minY=y;}
            if (y>maxY){maxY=y;}
        }
    }

    public void addPoint(Point point){
        if (!isParent){
            if (points==null) points = new ArrayList<>();
            points.add(point);

            if (point.getX()<minX || point.getX()>maxX
                    || point.getY()<minY || point.getY()>maxY){

                    makeBoxBigger(point.getX(), point.getY()); }
        }
    }

    public void addBox(Box box){ //добавить детскую в родительскую
        if (isParent){
            if(boxes==null)boxes = new ArrayList<>();
            boxes.add(box);

            if(box.getMinX() < minX || box.getMaxX() > maxX
                || box.getMinY() < minY || box.getMaxY() > maxY){

            makeBoxBigger(box.getMinX(), box.getMinY());
            makeBoxBigger(box.getMaxX(), box.getMaxY());
            }
        }
    }

    public boolean isPointContains(Point point){
        return (point.getX() >= minX && point.getX() <= maxX
                && point.getY() >= minY && point.getY() <= maxY);
    }



}
