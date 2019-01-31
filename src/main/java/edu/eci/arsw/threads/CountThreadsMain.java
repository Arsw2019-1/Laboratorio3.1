/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    static int aa=99;
    static int b=0;
    
    public void run(){
        System.out.println("Hola mundo");
    
    }
    
    
    public static void main(String a[]){
        //for (int i=0;i<3;i++){           
        CountThread temp1=new CountThread(0, 99);
        CountThread temp2=new CountThread(99, 199);
        CountThread temp3=new CountThread(200, 299);
        temp1.run();
        temp2.run();
        temp3.run();        
        //temp1.start();
        //temp2.start();
        //temp3.start();        
        //temp1.start();
        //temp2.start();
        //temp3.start();
        
        //}
        
    }
    
}
