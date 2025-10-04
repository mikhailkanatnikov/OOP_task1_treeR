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
    private int maxSize; //вместимость
    private boolean isParent; //хранит точки или коробки

    public Box(int size, boolean isparent){
        if(size<=0){throw new IllegalArgumentException("Размер коробки должен быть больше нуля!");}

        this.maxSize = size;
        this.isParent = isparent;

        if (isparent){ //если хранит коробки
            this.boxes = new ArrayList<Box>();
            this.points = null;
        } else {
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

    // методы //
    //нужно дописать

    public boolean isBoxEmpty(Box box){
        if (box)
    }
    public void makeBoxBigger(int x, int y){
        if (points.isEmpty()){
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
            points.add(point);
            makeBoxBigger(point.getX(), point.getY());
        }
    }

    public void addBox(Box box){ //добавить детскую в родительскую
        if (isParent){
            boxes.add(box);
            makeBoxBigger(box.getMinX(), box.getMinY());
            makeBoxBigger(box.getMaxX(), box.getMaxY());
        }
    }



}
