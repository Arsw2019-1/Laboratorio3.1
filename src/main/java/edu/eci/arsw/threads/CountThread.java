/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

public class CountThread extends Thread{
   int a;
   int b;
    
    
   public CountThread(int a, int b){
       super("Iniciando el hilo");
       this.a=a;
       this.b=b;

       System.out.println("my thread created" + this);
       start();
       //CountThread ob=new CountThread();
       //ob.run();
       
 
   } 

  public void run()
   {
     try
     {
         for (int i=a;i<b;i++){
        
            System.out.println("El numero va : "+i);
            System.out.println("El hilo es : "+this);
            Thread.sleep(1000);
        } 
     }
     catch(InterruptedException e)
     {
        System.out.println("my thread interrupted");
     }
     System.out.println("My thread run is over" );
   }
    
    
    
    
}
