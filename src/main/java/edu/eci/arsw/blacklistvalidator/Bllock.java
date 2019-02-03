/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;


import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;

/**
 *
 * @author 2098325
 */
public class Bllock extends  Thread {

    LinkedList<Integer> blackListOcurrences=new LinkedList<>();
    private static final int BLACK_LIST_ALARM_COUNT=5;    
    private int checkedListsCount=0;

    private String ipaddress;
    private int ocurrencesCount=0;
    private int inicio;
    private int fin;
    private int N;
    private int registeredServersCount;
    
    HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    
    public Bllock(int inicio,int fin, String ipaddress, int N){
        this.inicio=inicio;
        this.fin=fin;
        this.ipaddress=ipaddress;
        this.N=N;

    }
    
    public int GetRegisteredServersCount(){
        return registeredServersCount;
    }
    
    public int getCheckedListCount(){
        return checkedListsCount;
    }
    public int getOcurrencesCount(){
    
        return ocurrencesCount;
    }

    @Override
    public void run(){
    
    for (int i=0;i<skds.getRegisteredServersCount() && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            
            if (skds.isInBlackListServer(i, ipaddress)){
                //System.out.println("Entamos y : "+i);
                blackListOcurrences.add(i);
                
                ocurrencesCount++;
            }
        }

    registeredServersCount=skds.getRegisteredServersCount();       
    
    }

}
