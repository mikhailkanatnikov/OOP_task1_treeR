package RTree;
import Box.Box;
import Point.Point;

import java.util.ArrayList;
import java.util.List;

public class RTree {
    private Box rootBox;
    private final int maxBoxSize;
    private int pointCount;

    public RTree(int size){
        if (size<0){throw new IllegalArgumentException("Размер должен быть больше нуля!!");}
        this.maxBoxSize = size;
    }

    public Box getRootBox() {
        return this.rootBox;
    }

    public int getMaxBoxSize(){
        return this.maxBoxSize;
    }

    public int getPointCount(){
        return this.pointCount;
    }

      ///////////////////////// методы ///////////////////////////

    public boolean isTreeEmpty(){
        if(rootBox==null)return true;
        return this.getRootBox().isBoxEmpty();
    }

    private Box findBoxForPoint(Box currentBox, Point point){

        if (!currentBox.isParent()){return currentBox;}

        List<Box> children = currentBox.getBoxes();

        for(Box child: children){
            if (child.isPointContains(point)){
                return findBoxForPoint(child,point);
            }
        }
        //если ничего не подошло, кидаем в первую
        return findBoxForPoint(children.getFirst(),point);

    }


    public void splitBox(Box box){

       Box box1 = new Box(maxBoxSize, box.isParent());
       Box box2 = new Box(maxBoxSize, box.isParent());


       if(!box.isParent()) { //хранит точки

           List<Point> points = box.getPoints();
           int half = points.size() / 2;

           for (int i = 0; i < half; i++) {
               box1.addPoint(points.get(i));
           }

           for (int i = half; i<points.size();i++){
               box2.addPoint(points.get(i));
           }

       } else { // хранит коробки

           List<Box> children = box.getBoxes();
           int half = children.size()/2;

           for (int i = 0; i<half; i++){
               box1.addBox(children.get(i));
           }

           for (int i = half; i<children.size(); i++){
               box2.addBox(children.get(i));
           }
       }

        //проверка на корень
        if(box.getParent()==null){

            Box newRoot = new Box(maxBoxSize, true);
            newRoot.addBox(box1);
            newRoot.addBox(box2);
            rootBox = newRoot;

        }
    }



    public void insert(Point point){
        if(rootBox==null){rootBox = new Box(maxBoxSize,false);}

        Box leaf = findBoxForPoint(rootBox, point);

        if (leaf.getCurrentSize()>=maxBoxSize){
            splitBox(leaf);

            leaf = findBoxForPoint(rootBox, point);
        }

        leaf.addPoint(point);
        pointCount++;
        }


    public void getAllPointsFromBox(Box box, List<Point>pointsFromBox){
        if (box==null)return;

        if(!box.isParent()){ //хранит только точки
            pointsFromBox.addAll(box.getPoints());
        }
            else{
                for (Box child: box.getBoxes()){
                    getAllPointsFromBox(child,pointsFromBox);
                }

        }
    }

    public void getAllPointsFromTree(List<Point>pointsFromTree){
        if(rootBox==null){return;}
        getAllPointsFromBox(rootBox,pointsFromTree);
    }







    public List<Point> getAllPointsFromArea(int minX, int maxX, int minY, int maxY){

        List<Point> pointsInArea = new ArrayList<Point>();

        List<Point> allTreePoints = new ArrayList<Point>();
        getAllPointsFromTree(allTreePoints);

        for (Point point: allTreePoints){
            if( point.isPointInArea(minX,maxX,minY,maxY) ){
                pointsInArea.add(point);
            }
        }

        return pointsInArea;
    }


    }





