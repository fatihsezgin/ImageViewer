package edu.aydin.finalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Scanner;

public class ImageViewer extends JFrame {
    private DrawingPanel dp;
    private JFileChooser fileChooser;
    private JButton jOpenFile;
    private Scanner scanner;
    private FileInputStream fis;
    private String magicNumber = "";
    private int w,h;
    private int[] image;
    private int[] bitArray;
    private  Color imageColor[];
    private File selectedFile;
    private Timer timer;
    private int ws,a,count =1;



    ImageViewer(){

        jOpenFile = new JButton("Open File");
        dp = new DrawingPanel();
        timer = new Timer(500,new TimerListener());
        fileChooser = new JFileChooser("C:\\Users\\Fatih Sezgin\\Desktop\\Adem Hoca Java\\AdemHocaFinalProject\\FinalProject\\Images");
        this.add(dp,BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        this.setVisible(true);
        this.setTitle("Image Viewer");
        this.add(jOpenFile,BorderLayout.SOUTH);

        jOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(ImageViewer.this);
                selectedFile = fileChooser.getSelectedFile();
                try {
                    fis = new FileInputStream(selectedFile);
                    magicNumber= getMagicNumber();
                    /*scanner = new Scanner(selectedFile);
                    magicNumber = scanner.next();
                    System.out.println(magicNumber);*/

                    switch (magicNumber){
                        case "P1":
                            System.out.println("P1 selected");
                            openP1();
                            break;
                        case "P2":
                            System.out.println("P2 selected");
                            openP2();
                            break;
                        case "P3":
                            System.out.println("P3 selected");
                            openP3();
                            break;
                        case "P4":
                            System.out.println("P4 selected");
                            openP4();
                            break;
                        case "P5":
                            System.out.println("P5 selected");
                            openP5();
                            break;
                        case "P6":
                            System.out.println("P6 selected");
                            openP6();
                            break;
                    }

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

    }
    private void openP1() {
        try {
            scanner = new Scanner(selectedFile);

            String mn = scanner.next();
            System.out.println(mn);//P1
            w = scanner.nextInt();
            a=count *(w/5);
            h = scanner.nextInt();
            int numOfPixels = w*h;

            image = new int[numOfPixels];

            for (int i =0;i<numOfPixels;i++){
                image[i] = (scanner.nextInt())*255;
            }

            timer.start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void openP2() throws FileNotFoundException {
        scanner = new Scanner(selectedFile);
        magicNumber = scanner.next();
        System.out.println(magicNumber);

        w = scanner.nextInt();
        a=count *(w/5);
        System.out.println("w :"+w);
        h= scanner.nextInt();
        System.out.println("h :"+h);
        int colorNumber = scanner.nextInt();
        System.out.println(colorNumber);

        int numOfPixels = w*h ;

        image = new int[numOfPixels];
        for (int i = 0; i<numOfPixels;i++){
            image[i] = scanner.nextInt();
        }
        imageColor = new Color[numOfPixels];
        for (int i = 0; i<numOfPixels;i++){
            imageColor[i] = new Color(image[i],image[i],image[i]);
        }

        timer.start();
    }


    private void openP3() throws FileNotFoundException {
        scanner = new Scanner(selectedFile);
        magicNumber= scanner.next();

        w= scanner.nextInt();
        a=count *(w/5);
        h=scanner.nextInt();
        System.out.println( "w :"+w+"h :"+h);
        int colorNumber = scanner.nextInt();
        System.out.println(colorNumber);
        int numOfPixels = w*h;
        image = new int[numOfPixels*3];
        for (int i = 0;i<image.length;i++){
            image[i] = scanner.nextInt();
        }
        int a = 0;
        imageColor = new Color[numOfPixels];
        for (int i = 0;i<numOfPixels*3;i=i+3){
            imageColor[a]=new Color(image[i],image[i+1],image[i+2]);
            a++;
        }
        timer.start();
    }
     private void openP4() throws IOException {
        fis = new FileInputStream(selectedFile);
        String mn = getMagicNumber();
        System.out.println(mn);
        skipWhitespace();
        w = readNumber();
        a=count *(w/5);
        skipWhitespace();
        h = readNumber();
        System.out.println("w: " +w +"h:"+h);

        image = new int[fis.available()];
        System.out.println(image.length);
        bitArray= new int[fis.available()*8];
        int a = fis.available();

        for (int i = 0; i< image.length;i++){
            //System.out.println(fis.available());
            image[i]= fis.read();
            //System.out.println("image"+image[i]);
        }


        int iter =0;
        int index =0;
        /*for (int i = 0; i<a;i++){
            for (int j =7;j>=0;j--){
                if (index%520<514){
                    bitArray[iter] = (image[i] >>> j) & 1;
                    //System.out.println(bitArray[iter]);
                    iter++;
                }
                index++;
            }
        }*/
        int w1 = w%8;
         System.out.println(w1);
        if (w1 ==1){
            w1= w+7;
        }else if (w1==2){
            w1=w+6;
        }else if (w1==3){
            w1=w+5;
        }else if (w1==4){
            w1=w+4;
        }else if (w1==5){
            w1=w+3;
        }else if (w1==6){
            w1=w+2;
        }else if (w1==7){
            w1=w+1;
        }else if (w1==0){
            w1=w;
        }

        for(int i = 0; i< a ; i++){
            for (int j = 7;j>=0;j--){
                if (index % w1<w){
                    bitArray[iter]=(image[i]>>>j)&1;
                    iter++;
                }
                index++;
            }
        }
        w1=0;



        System.out.println("*****************************INDEXXXXXXXXXX*************************"+ index);
        System.out.println("*****************************ITERR*************************"+ iter);


        timer.start();

     }
     public void openP5 () throws IOException {
        fis = new FileInputStream(selectedFile);
        String mn = getMagicNumber();
        System.out.println(mn);
        skipWhitespace();
        w= readNumber();
        a=count *(w/5);
        skipWhitespace();
        h=readNumber();
        skipWhitespace();
        int maxNum = readNumber();

        image = new int[w*h];
        for (int i = 0;i<image.length;i++){
            image[i]= fis.read();
            //System.out.println(image[i]);
        }

        imageColor = new Color[w*h];
        for (int i = 0; i<imageColor.length;i++){
            imageColor[i] = new Color(image[i],image[i],image[i]);
        }
        timer.start();

     }

     private void openP6() throws IOException {
        fis = new FileInputStream(selectedFile);
        String mn = getMagicNumber();
        System.out.println(mn);
        skipWhitespace();
        w = readNumber();
        a=count *(w/5);
        skipWhitespace();
        h=readNumber();
        skipWhitespace();
        int max = readNumber();
        image = new int[w*h*3];
        for (int i = 0;i<image.length;i++){
            image[i]= fis.read();
            //System.out.println(image[i]);
        }
         imageColor = new Color[w*h];
        int a =0;
         for (int i = 0; i<imageColor.length*3;i=i+3){
             imageColor[a] = new Color(image[i],image[i+1],image[i+2]);
             a++;
         }
         timer.start();

     }


     private String getMagicNumber() {
        byte [] magicNum = new byte[2];
        try {
            fis.read(magicNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return new String(magicNum);
    }
    private void skipWhitespace() {
        try {
            ws = fis.read();
            while(Character.isWhitespace(ws))
                ws = fis.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private int readNumber() {
        String wstr = "";
            try {
                while(!Character.isWhitespace(ws)) {
                //while(Character.isDigit(ws))
                wstr = wstr + (ws-'0'/*48*/);
                ws = fis.read();
            }
        }catch(IOException e2) {}

        System.out.println(wstr);
        return Integer.parseInt(wstr);
    }


   class DrawingPanel extends JPanel{
       @Override
       protected void paintComponent(Graphics g) {
           super.paintComponent(g);
           System.out.println("in drawing w " + w + "h" + h);
           if (magicNumber.equals("P1")) {
               for (int y = 0; y < h; y++) {
                   for (int x = 0; x < a; x++) {
                       g.setColor(image[y * w + x] == 0 ? Color.BLACK : Color.WHITE);
                       g.fillRect(x, y, 1, 1);
                   }
               }
               if (count == 5) {
                   timer.stop();
                   count=0;
               } else {
                   count++;
               }
               a = count * (w / 5);

           }else if (magicNumber.equals("P2")){
               for (int y = 0;y <h ; y++){
                   for (int x = 0 ; x <a ;x++){
                        g.setColor(imageColor[y*w+x]);
                        g.fillRect(x,y,1,1);
                   }
               }
               if (count == 5) {
                   timer.stop();
                   count=0;
               } else {
                   count++;
               }
               a = count * (w / 5);
           }else if (magicNumber.equals("P3")){
               for (int y = 0;y <h ; y++){
                   for (int x = 0 ; x <a ;x++){
                       g.setColor(imageColor[y*w+x]);
                       g.fillRect(x,y,1,1);
                   }
               }
               if (count == 5) {
                   timer.stop();
                   count=0;
               } else {
                   count++;
               }
               a = count * (w / 5);
           }else if(magicNumber.equals("P4")){

               for (int y = 0 ;y<h;y++){
                   for (int x = 0 ; x<a;x++){
                       g.setColor(bitArray[y*w+x]==0 ? Color.WHITE : Color.BLACK);
                       g.fillRect(x,y,1,1);
                   }
               }
               if (count == 5) {
                   timer.stop();
                   count=0;
               } else {
                   count++;
               }
               a = count * (w / 5);

           }else if(magicNumber.equals("P5")){
               for (int y = 0;y <h ; y++){
                   for (int x = 0 ; x <a ;x++){
                       g.setColor(imageColor[y*w+x]);
                       g.fillRect(x,y,1,1);
                   }
               }
               if (count == 5) {
                   timer.stop();
                   count=0;
               } else {
                   count++;
               }
               a = count * (w / 5);
           }else if(magicNumber.equals("P6")){
               for (int y = 0;y <h ; y++){
                   for (int x = 0 ; x <a ;x++){
                       g.setColor(imageColor[y*w+x]);
                       g.fillRect(x,y,1,1);
                   }
               }
               if (count == 5) {
                   timer.stop();
                   count=0;
               } else {
                   count++;
               }
               a = count * (w / 5);
           }
       }
   }

    public static void main(String[] args) {
        new ImageViewer();
    }


    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dp.repaint();
        }
    }
}
