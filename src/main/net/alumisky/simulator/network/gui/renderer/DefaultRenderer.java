/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.alumisky.simulator.network.gui.renderer;

import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Renderer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import net.alumisky.simulator.network.gui.PeerUniverseObject;

/**
 *
 * @author Maryan
 */
public class DefaultRenderer implements Renderer<PeerUniverseObject>{
    public void render(Graphics g, PeerUniverseObject object, Attributes view) {
            Graphics2D g1 = (Graphics2D) g;
            final int fontSize=16;
            
           
            g.setFont(new Font(null, Font.BOLD, fontSize)); 
            g.setColor(Color.WHITE);
            
            RoundRectangle2D r = view.getObjectRoundRectangle2D();
            
            RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            g1.setRenderingHints(rh);
             
           
            //String s=object.getName()+"dd gfdgfs fsfcsds";
             
             String s="fdd aaaa";
             
             FontMetrics fm = g.getFontMetrics();
             int totalWidth = fm.stringWidth(s);
             
            if(view.multilineSupport && s.contains(" ") && totalWidth>r.getWidth()){
                
                g.fillRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),(int)r.getArcWidth(),(int)r.getArcHeight());
              
                
                g.setColor(Color.BLACK);
                
                renderMultiline(g,s,r,object);
                 
            }
            else{
                g.fillRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),(int)r.getArcWidth(),(int)r.getArcHeight());
                
                g.setColor(Color.BLACK);
                renderOneLine(g,s,r,object);
            
            }
              
            
        }
    
    public void renderOneLine(Graphics g, String name, RoundRectangle2D r, PeerUniverseObject object)
    {
        
        FontMetrics fm = g.getFontMetrics();
        Graphics2D g1 = (Graphics2D) g;
        int totalWidth = fm.stringWidth(name);
        
          if(totalWidth>r.getWidth()) {
               int lengthOneSymbol=(totalWidth/name.length());
               
            
               int sizeDots =fm.stringWidth("...");
               int numbSymbol=0;
               
               
               while((lengthOneSymbol+sizeDots+(totalWidth/name.length())*2)<r.getWidth())
               {
                   numbSymbol++;
                   lengthOneSymbol+=totalWidth/name.length();
                }
               
                name=(name.substring(0, numbSymbol))+"...";
                totalWidth = fm.stringWidth(name);
            }
            
            int x = 5;
            int y=(int) (((r.getHeight()- fm.getHeight()) / 2) + fm.getAscent());
            
            if(object.errorMode){
                
                g.setColor(Color.red);
                g1.setStroke(new BasicStroke(2));
                g.drawString(name, (int)r.getX()+x, (int)r.getY()+y );
                g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),(int)r.getArcWidth(),(int)r.getArcHeight());
                g.setColor(Color.PINK);
                
               
                g1.setStroke(new BasicStroke(3));
                g.drawRoundRect((int)r.getX()-2,(int) r.getY()-2, (int)r.getWidth()+4, (int)r.getHeight()+4,(int)r.getArcWidth()+2,(int)r.getArcHeight()+2);
            
            }
            else if(object.disableMode){
                g.setColor(Color.LIGHT_GRAY);
                g.drawString(name, (int)r.getX()+x, (int)r.getY()+y );
                float dash[] = { 4};
                BasicStroke dashedStroke=new BasicStroke(1, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10, dash, 0);
                g1.fill(dashedStroke.createStrokedShape(r));
               
                
              
            }
            else{
            
        
               g.drawString(name, (int)r.getX()+x, (int)r.getY()+y );
               g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),(int)r.getArcWidth(),(int)r.getArcHeight());
              
            }
        
    }
    
    public void renderMultiline(Graphics g, String name, RoundRectangle2D r, PeerUniverseObject object)
    {
        
        FontMetrics fm = g.getFontMetrics();
        Graphics2D g1 = (Graphics2D) g;
        int totalWidth = fm.stringWidth(name);
        int totalWidthFirstStr, totalWidthSecondStr;
        
        
        String firstLine="", secondLine="", tmpName=name;
        
        if(totalWidth>r.getWidth()){
            int gap=0;
            while(totalWidth>r.getWidth())
            {
                gap=tmpName.lastIndexOf(' ');
                firstLine=tmpName.substring(0, gap);
               
                totalWidth=fm.stringWidth(firstLine);
                tmpName=firstLine;
               
                
            }
            secondLine=name.substring(gap+1, name.length());
            System.out.println("sdf"+secondLine);
            int x,y;
            
       
             totalWidthFirstStr = fm.stringWidth(firstLine);
             totalWidthSecondStr = fm.stringWidth(secondLine);
        
            if(totalWidthSecondStr>r.getWidth()) {
               int lengthOneSymbol=(totalWidthSecondStr/secondLine.length());
               int sizeDots =fm.stringWidth("...");
               int numbSymbol=0;
               
               
               while(lengthOneSymbol+sizeDots+(totalWidthSecondStr/secondLine.length())*2<r.getWidth())
               {
                   numbSymbol++;
                   lengthOneSymbol+=totalWidthSecondStr/secondLine.length();
                }
               
                secondLine=(secondLine.substring(0, numbSymbol))+"...";
                totalWidthSecondStr = fm.stringWidth(secondLine);
            }
            
            
              if(object.errorMode){
              
                g.setColor(Color.red);
                g1.setStroke(new BasicStroke(2));
                
                x = (int) 5;
                y=(int) ((r.getHeight()-(fm.getHeight()*2))/2+ fm.getAscent()-2);
          
                g.drawString(firstLine, (int)r.getX()+x, (int)r.getY()+y );
                
                x = (int) 5;
                y=(int) (((r.getHeight()-((fm.getHeight())*2))/2+ fm.getAscent()*2)+2);
                
                g.drawString(secondLine, (int)r.getX()+x, (int)r.getY()+y );
                
                g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),(int)r.getArcWidth(),(int)r.getArcHeight());
                g.setColor(Color.PINK);
                
               
                g1.setStroke(new BasicStroke(3));
                g.drawRoundRect((int)r.getX()-2,(int) r.getY()-2, (int)r.getWidth()+4, (int)r.getHeight()+4,(int)r.getArcWidth()+2,(int)r.getArcHeight()+2);
            
            }
            else if(object.disableMode){
                
                g.setColor(Color.LIGHT_GRAY);
                
                x = 5;
                y=(int) ((r.getHeight()-(fm.getHeight()*2))/2+ fm.getAscent()-2);
          
                g.drawString(firstLine, (int)r.getX()+x, (int)r.getY()+y );
                
                x = 5;
                y=(int) (((r.getHeight()-((fm.getHeight())*2))/2+ fm.getAscent()*2)+2);
                
                g.drawString(secondLine, (int)r.getX()+x, (int)r.getY()+y );
                
                float dash[] = { 4};
                BasicStroke dashedStroke=new BasicStroke(1, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10, dash, 0);
                g1.fill(dashedStroke.createStrokedShape(r));
                
                }
            else{
                
                x = 5;
                y=(int) ((r.getHeight()-(fm.getHeight()*2))/2+ fm.getAscent()-2);
          
                g.drawString(firstLine, (int)r.getX()+x, (int)r.getY()+y );
                
                x = 5;
                y=(int) (((r.getHeight()-((fm.getHeight())*2))/2+ fm.getAscent()*2)+2);
        
                g.drawString(secondLine, (int)r.getX()+x, (int)r.getY()+y );
                g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),(int)r.getArcWidth(),(int)r.getArcHeight());
              
            }
            
            
          
            
        }
    }
    
}
