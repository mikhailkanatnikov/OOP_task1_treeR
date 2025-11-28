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

        if (children.isEmpty()) {
            return currentBox;}

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

        } else {
            Box parent = box.getParent();
            // УДАЛИТЬ старую коробку из родителя
            parent.getBoxes().remove(box);


            parent.addBox(box1);
            parent.addBox(box2);

            // не переполнился ли родитель
            if (parent.getCurrentSize() > maxBoxSize) {
                splitBox(parent); // рекурсивно разделить родителя
            }

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

    public void remove(Point target) {
        if (rootBox == null) return;

        Box leaf = findBoxForPoint(rootBox, target);
        List<Point> points = leaf.getPoints();

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.getX() == target.getX() && p.getY() == target.getY() &&
                    p.getData().equals(target.getData())) {
                points.remove(i);
                pointCount--;
                return;
            }
        }
    }


    public void printTree() {
        if (rootBox == null) {
            System.out.println("Дерево пустое");
            return;
        }


        List<Box> stack = new ArrayList<>();
        stack.add(rootBox);

        while (!stack.isEmpty()) {
            Box box = stack.remove(0);

            if (box.isParent()) {
                System.out.println("Родитель [" + box.getCurrentSize() + " детей]");
                if (box.getBoxes() != null) {
                    stack.addAll(0, box.getBoxes()); // добавляем детей в начало
                }
            } else {
                System.out.println("Лист [" + box.getCurrentSize() + " точек]");
                if (box.getPoints() != null) {
                    for (Point p : box.getPoints()) {
                        System.out.println("  (" + p.getX() + "," + p.getY() + ") - " + p.getData());
                    }
                }
            }
        }
    }

}





