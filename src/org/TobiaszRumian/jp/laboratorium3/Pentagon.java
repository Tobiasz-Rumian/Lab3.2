package org.TobiaszRumian.jp.laboratorium3;

import java.awt.*;

/**
 * Created by Tobiasz Rumian on 28.10.2016.
 */
public class Pentagon extends Figure
    {
        private Point[] points= new Point[6];
        private Triangle[] triangles = new Triangle[5];
        Pentagon(){
            points[0] = new Point(13f,12.24f);
            points[1] = new Point(21.90f,12.24f);
            points[2] = new Point(24.84f,20.38f);
            points[3] = new Point(17.5f,25.55f);
            points[4] = new Point(10.06f,20.42f);
            points[5]= new Point(this.getX(),this.getY());
            createPentagon();
            this.scale(Math.abs(random.nextFloat()*10));
            this.move(Math.abs(random.nextFloat()*400),Math.abs(random.nextFloat()*400));
        }

        Pentagon(Point p1, Point p2, Point p3, Point p4, Point p5){
            points[0]=p1; points[1] =p2; points[2] =p3; points[3] =p4; points[4]=p5;points[5] = new Point(this.getX(),this.getY());
            createPentagon();
        }
        private void createPentagon()
            {
                triangles[0]=new Triangle(points[0],points[5],points[1]);
                triangles[1]=new Triangle(points[1],points[5],points[2]);
                triangles[2]=new Triangle(points[2],points[5],points[3]);
                triangles[3]=new Triangle(points[3],points[5],points[4]);
                triangles[4]=new Triangle(points[4],points[5],points[0]);
            }
        @Override
        public boolean isInside(float px, float py)
            {
                for (Triangle t:triangles) if(t.isInside(px,py)) return true;
                return false;
            }

        @Override
        String getName() {
            StringBuilder sb = new StringBuilder();
            for (Point p:points) sb.append(p.toStringXY());
            return "Pentagon{"+sb.toString()+"}";
        }

        @Override
        float getX() {
            float x=0;
            for(int i =0;i<5;i++) x+=points[i].x;
            return x/5;
        }

        @Override
        float getY() {
            float x=0;
            for(int i =0;i<5;i++) x+=points[i].y;
            return x/5;
        }

        @Override
        float computeArea(){
            float x=0;
            for (Triangle t:triangles) x+=t.computeArea();
            return x;
        }

        @Override
        float computePerimeter(){
            float a = (float)Math.sqrt( Math.pow(points[0].x-points[1].x,2)+Math.pow(points[0].y-points[1].y,2));
            float b = (float)Math.sqrt( Math.pow(points[1].x-points[2].x,2)+Math.pow(points[1].y-points[2].y,2));
            float c = (float)Math.sqrt( Math.pow(points[2].x-points[3].x,2)+Math.pow(points[2].y-points[3].y,2));
            float d = (float)Math.sqrt( Math.pow(points[3].x-points[4].x,2)+Math.pow(points[3].y-points[4].y,2));
            float e = (float)Math.sqrt( Math.pow(points[4].x-points[0].x,2)+Math.pow(points[4].y-points[0].y,2));
            return a+b+c+d+e;
        }

        @Override
        void move(float dx, float dy){
            for (Point p:points) p.move(dx,dy);
        }

        @Override
        void scale(float s){
            Point sr1 = new Point(points[5].x,points[5].y);
            for (Point p:points) {p.x*=s;p.y*=s;}
            for (Point p:points) p.move(sr1.x-points[5].x,sr1.y-points[5].y);
        }

        @Override
        void draw(Graphics g){
            for (Triangle t:triangles) {t.setsColor(color);t.draw(g);}
        }

        @Override
        public void setsColor(Color color)
            {
                this.color=color;
            }
    }
