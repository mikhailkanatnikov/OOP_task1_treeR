package RTree;
import Box.Box;
import Point.Point;

import java.util.List;

public class RTree {
    private Box rootBox;
    private final int maxSize;
    private int pointCount;

    public RTree(int size){
        if (size<0){throw new IllegalArgumentException("Размер должен быть больше нуля!!");}
        this.maxSize = size;
    }

    public Box getRootBox() {
        return this.rootBox;
    }

    public int getMaxSize(){
        return this.maxSize;
    }

    public int getPointCount(){
        return this.pointCount;
    }

                    // методы // (нужно дописать)

    public boolean isTreeEmpty(RTree tree){
        if (tree.getRootBox())
    }

    private Box findBoxForPoint(){
        return rootBox;
    }

   /* private void splitBox(Box box){
        List<Point> points = box.getPoints();

        int half = points.size()/2;
        Box box1 = new Box(half, false);
        Box box2 = new Box(half, false);

        for (int i = 0; i<half; i++ ){
            box1.addPoint(box.); //дописать
        }

        for (int i = half; i<)

    } */

}
