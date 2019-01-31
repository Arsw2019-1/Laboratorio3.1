/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

/**
 *
 * @author 2098325
 */
public class Block extends  Thread {

    
    
    private int ocurrencesCount=0;

    HostBlacklistsDataSourceFacade skds;
    
    public Block(String ip,int rango, HostBlacklistsDataSourceFacade skds){
    
    
    
    }
    
    
    
    @Override
    public void run(){
    
    for (int i=0;i<skds.getRegisteredServersCount() && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            
            if (skds.isInBlackListServer(i, ipaddress)){
                
                blackListOcurrences.add(i);
                
                ocurrencesCount++;
            }
        }
    
    
    
    }


    
}
