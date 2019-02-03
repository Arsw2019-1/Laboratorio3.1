/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.Block;

/**
 *
 * @author hcadavid
 */
public class HostBlackListsValidator {
    private LinkedList<Block> segmentacion=new LinkedList<Block>();
    private static final int BLACK_LIST_ALARM_COUNT=5;
    private int hilos=0;
    private static int rango;
    
    public void gen(int i, Boolean cod, int N,String ipaddress ) throws InterruptedException{
            LinkedList<Bllock> segmentacion=new LinkedList<Bllock>();
              int ocurrencesCount=0;
            int contador=0;
            int ServerRegistration=0;
            int checkedListsCount=0;
            while(i<N & cod){
                Bllock t=new Bllock(contador, rango*(i+1), ipaddress, N);
                t.start();
                t.join();
                segmentacion.add(t);
                //System.out.println("que se añado :"+t);
                contador+=rango;
                if(ocurrencesCount>=5){
                    checkedListsCount=t.getCheckedListCount();
                    ServerRegistration=t.GetRegisteredServersCount();
                    cod=false;
                }
                i++;            
            }        
    }
    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     * @param ipaddress suspicious host's IP address.
     * @return  Blacklists numbers where the given host's IP address was found.
     */
    public List<Integer> checkHost(String ipaddress, int N) throws InterruptedException{
        
        LinkedList<Integer> blackListOcurrences=new LinkedList<>();
        
        int ocurrencesCount=0;
        //
        LinkedList<Bllock> segmentacion=new LinkedList<Bllock>();
        
        int ServerRegistration=0;
        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
        
        int numServ=skds.getRegisteredServersCount();
        int checkedListsCount=0;
        int rango =0;
        int contador=0;
        int i=0;
        Boolean cod=true;        
        //Codigo para hacerlo concurrente
        rango=numServ/N;
        System.out.println("es :" + N%2);
        if(N%2==0){
            System.out.println("contador"+contador);
            while(i<N & cod){
                Bllock t=new Bllock(contador, rango*(i+1), ipaddress, N);
                t.start();
                t.join();
                segmentacion.add(t);
                //System.out.println("que se añado :"+t);
                contador+=rango;
                if(ocurrencesCount>=5){
                    checkedListsCount=t.getCheckedListCount();
                    ServerRegistration=t.GetRegisteredServersCount();
                    cod=false;
                }
                i++;            
            }                
        }else{                       
            int temp=numServ-rango;
            while(i<N & cod &temp>0){
                if(temp<rango){
                    rango=temp;
                }
                Bllock t=new Bllock(contador, rango*(i+1), ipaddress, N);
                t.start();
                t.join();
                segmentacion.add(t);
                contador+=rango;
                temp-=rango;
                if(ocurrencesCount>=5){
                    checkedListsCount=t.getCheckedListCount();
                    ServerRegistration=t.GetRegisteredServersCount();
                    cod=false;
                }
                i++;
            
            }
        }
        //Fin codigo recurrencia
        if (ocurrencesCount>=BLACK_LIST_ALARM_COUNT){
            skds.reportAsNotTrustworthy(ipaddress);
        }
        else{
            skds.reportAsTrustworthy(ipaddress);
        }                
        
        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});
        
        return blackListOcurrences;
    }
    
    
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());
    
    
    
}
