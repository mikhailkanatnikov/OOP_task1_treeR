package main;

import RTree.RTree;
import Box.Box;
import Point.Point;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //1 создание дерева и точек
        RTree tree = new RTree(4);

        Point p1 = new Point(1, 1, "Магазин");
        Point p2 = new Point(2, 2, "Кафе");
        //Point p3 = new Point(3, 3, "Парк");
        Point p4 = new Point(4,4,"Кинотеатр");

        //2 добавляем точки
        //tree.insert(p1);
        //tree.insert(p2);
        //tree.insert(p3);
        //tree.insert(p4);

        //как выглядит дерево
        //tree.printTree();

        //удалим точку
        //tree.remove(p4);

        //УДАЛИЛАСЬ
        tree.printTree();

        //нахождение точек в области
        List<Point> pointsFromArea = new ArrayList<>();
        pointsFromArea = tree.getAllPointsFromArea(0,3,0,3);

        for (Point p:pointsFromArea){
            System.out.println("Найдена точка "+p.getData()+" с координатами "+p.getX()+";"+p.getY());
        }
        System.out.println("Всего найдено "+pointsFromArea.size()+" точек.");


    }
}
