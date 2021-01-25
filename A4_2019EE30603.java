import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class A4_2019EE30603{
    //public long V=0;
    static class node{
        String data;
        boolean Visited=false;
        int weightsum=0;
        public  ArrayList<edge> outedge=new ArrayList<>();   //stores adjacent destination nodes
        node(String s){
            this.data=s;   
        }
        public void addedge(edge d){
            this.outedge.add(d);
        }
    }
    static class edge{
        int weight;
        //node source;
        node destination;
        public edge(node d,String w){
            this.destination=d;
            //this.source=s;
            this.weight=Integer.parseInt(w);
        }  
    }
    public static void average(HashMap<String, node> h){
        double avg=0;
        for(node n:h.values()){
            avg+=n.outedge.size();
        }
        if(h.size()!=0)
        avg/=h.size();
        System.out.printf("%.02f\n",avg);
    }
    public static void rank(HashMap<String, node> h){
        int i=0,l=h.size();
        node[] Rank=new node[l];
        for(node n:h.values()){
            Rank[i]=n;
            for(int k=0;k<n.outedge.size();k++){
            Rank[i].weightsum+=n.outedge.get(k).weight;
            }
            i++;
        }
        Qsort(Rank,0,l-1);
        i=0;
        if(i<l){
        while(i<l-1){
            System.out.print(Rank[i].data+",");
            i++;  
        }
        System.out.println(Rank[i].data);
        }               
    }
    public static int partition(node Rank[], int l, int r)
    {
        node pivot =Rank[r];
        int i = l-1, j = l;
        while (j<r) {
            if(Rank[j].weightsum>pivot.weightsum){
                node temp=Rank[++i];
                Rank[i]=Rank[j];
                Rank[j]=temp;
            }
            else if(Rank[j].weightsum==pivot.weightsum&&Rank[j].data.compareTo(pivot.data)>=0){
                node temp=Rank[++i];
                Rank[i]=Rank[j];
                Rank[j]=temp;
            }
            j++;
        }
        node temp=Rank[++i];
        Rank[i]=Rank[r];
        Rank[r]=temp;
        return i;
    }
    static void Qsort(node Rank[],int l, int r){
        if(l<r)
        {
            int p=partition(Rank,l,r);
            Qsort(Rank,l,p-1);
            Qsort(Rank,p+1,r);
        }
    }
    static void DFS(node n,HashMap<String, node> h,ArrayList<String> A ){
        h.get(n.data).Visited=true;
        A.add(n.data);
        ArrayList<edge> edges=h.get(n.data).outedge;
        for(int i=0;i<edges.size();i++){
            edge e=edges.get(i);
            node adjnode=h.get(e.destination.data);
            if(!adjnode.Visited){
                DFS(adjnode,h,A);
            }
        }
    }
    static int partitionList(ArrayList<String> list, int low, int high) 
    { 
        String pivot = list.get(high);  
        int i = (low-1);
        for (int j=low; j<high; j++) 
        { 
           if (list.get(j).compareTo(pivot)>=0) 
            { 
                i++; 
                String temp = list.get(i); 
                list.set(i,list.get(j)); 
                list.set(j,temp); 
            } 
        } 
        String temp = list.get(i+1); 
        list.set(i+1,list.get(high)); 
        list.set(high,temp);
  
        return i+1; 
    } 
    static void qsortlist(ArrayList<String> list, int low, int high) 
    { 
        if (low < high) 
        {            
            int p = partitionList(list, low, high); 
            qsortlist(list, low, p-1); 
            qsortlist(list, p+1, high); 
        } 
    }
    static void independentstorylines(HashMap<String, node> h){
        ArrayList<ArrayList<String>> storylines = new ArrayList<>();
        //Iterator nodeiter=h.entrySet().iterator();
        for(node n:h.values()){
            if(n.Visited==false){
                ArrayList<String> s=new ArrayList<>();
                DFS(n,h,s);
                qsortlist(s,0,s.size()-1);
                if(storylines.isEmpty()){
                    storylines.add(s);
                }
                else{
                    int i=0;
                    while(i<storylines.size()&&(storylines.get(i).size()>s.size()||(storylines.get(i).size()==s.size()&&storylines.get(i).get(0).compareTo(s.get(0))>0))){
                        i++;
                    }
                    storylines.add(i,s);
                }
            }   
        }
        for(int i=0;i<storylines.size();i++){
            int l=storylines.get(i).size();
            int j=0;
            if(j<l){
            while(j<l-1){
            System.out.print(storylines.get(i).get(j)+",");
            j++;  
            }
            System.out.println(storylines.get(i).get(j));
            } 
        }
    }   
    public static void main(String args[]) throws java.io.FileNotFoundException
    {
        String npath=args[0];
        String epath=args[1];
        String line;
        HashMap<String,node> h=new HashMap<String, node>();
        try{
            BufferedReader a= new BufferedReader(new FileReader(npath));
            line=a.readLine();
            while((line=a.readLine())!=null){
                String[] val=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                val[1]=val[1].replaceAll("(\")","");
                node n= new node(val[1]);
                h.put(val[1],n);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            BufferedReader b= new BufferedReader(new FileReader(epath));
            line=b.readLine();
            while((line=b.readLine())!=null){
                String[] val=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                val[0]=val[0].replaceAll("(\")","");
                val[1]=val[1].replaceAll("(\")","");
                
                node s=h.get(val[0]);
                node d=h.get(val[1]);
                edge E1= new edge(d,val[2]);
                edge E2= new edge(s,val[2]);
                s.addedge(E1);
                d.addedge(E2);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        String ch=args[2];
        switch(ch){
            case "average":
                average(h);
                break;
            case "rank":
                rank(h);
                break;
            case "independent_storylines_dfs":
                independentstorylines(h);
                break;
            default:
                break;
        }
    }
}