//описывает точку которая входит в коробку
package Point;

public class Point {
    private int x;
    private int y;
    private Object data;

    public Point(int x, int y, Object data){
        this.x = x;
        this.y = y;
        this.data = data;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Object getData(){
        return this.data;
    }

    public boolean isPointInArea(int minX, int maxX, int minY, int maxY){
        return (this.x >= minX && this.x <= maxX
                && this.y >= minY && this.y <= maxY);
    }

}
