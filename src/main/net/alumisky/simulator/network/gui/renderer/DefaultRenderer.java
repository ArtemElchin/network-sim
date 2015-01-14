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
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import net.alumisky.simulator.network.gui.PeerUniverseObject;
import javax.swing.event.ListSelectionEvent;
/**
 *
 * @author Maryan
 */
public class DefaultRenderer implements Renderer<PeerUniverseObject>{
    public void render(Graphics g, PeerUniverseObject object, Attributes view) {
            Graphics2D g1 = (Graphics2D) g;
            final int arcWidth=25, arcHeight=25;
            final int fontSize=16;
            
           
            g.setFont(new Font(null, Font.BOLD, fontSize)); 
            g.setColor(Color.WHITE);
            
            RoundRectangle2D r = view.getObjectRoundRectangle2D();
            
            RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            g1.setRenderingHints(rh);
             
           
            String s=object.getName()+"dd gfdgfs fsfcsds";
             
           
            if(view.multilineSupport){
                
                g.fillRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),arcWidth,arcWidth);
              
                
                g.setColor(Color.BLACK);
                
                renderMultiline(g,s,r,object);
                 
            }
            else{
                g.fillRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),arcWidth,arcWidth);
                
                g.setColor(Color.BLACK);
                renderOneLine(g,s,r,object);
            
            }
              
             // g1.setStroke(new BasicStroke(3));
             
            // g.fillRect(r.x-2, r.y-2, r.width+4, r.height+4);
            //  g.setColor(Color.BLUE);
             // g1.setStroke(new BasicStroke(3));
            //g.drawRoundRect((int)r.getX()-2,(int) r.getY()-2, (int)r.getWidth()+4, (int)r.getHeight()+4,arcWidth+2,arcHeight+2);
            
        }
    
    public void renderOneLine(Graphics g, String name, RoundRectangle2D r, PeerUniverseObject object)
    {
        final int arcWidth=25, arcHeight=25;
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
            
            int x = (int) (((r.getWidth() - totalWidth) )/4)+3;
            int y=(int) (((r.getHeight()- fm.getHeight()) / 2) + fm.getAscent());
            
            if(!object.errorMode){
                
                g.setColor(Color.red);
                g1.setStroke(new BasicStroke(2));
                g.drawString(name, (int)r.getX()+x, (int)r.getY()+y );
                g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),arcWidth,arcWidth);
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
               
                
               // g.drawRoundRect((int)r.getX()-2,(int) r.getY()-2, (int)r.getWidth()+4, (int)r.getHeight()+4,(int)r.getArcWidth()+2,(int)r.getArcHeight()+2);
            }
            else{
            
        
               g.drawString(name, (int)r.getX()+x, (int)r.getY()+y );
               g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),arcWidth,arcWidth);
              
            }
        
    }
    
    public void renderMultiline(Graphics g, String name, RoundRectangle2D r, PeerUniverseObject object)
    {
        final int arcWidth=25, arcHeight=25;
        FontMetrics fm = g.getFontMetrics();
        Graphics2D g1 = (Graphics2D) g;
        int totalWidth = fm.stringWidth(name);
        
        
        
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
            
            
            
            int x = (int) (((r.getWidth() - totalWidth) )/4)+3;
            int y=(int) ((r.getHeight()-(fm.getHeight()*2))/2+ fm.getAscent()-2);
          
            g.drawString(firstLine, (int)r.getX()+x, (int)r.getY()+y );
            
           
            
            
            
           // System.out.println(firstLine+"  "+secondLine);
            
            
             totalWidth = fm.stringWidth(secondLine);
        
          if(totalWidth>r.getWidth()) {
               int lengthOneSymbol=(totalWidth/secondLine.length());
               
            
               int sizeDots =fm.stringWidth("...");
               int numbSymbol=0;
               
               // System.out.println(lengthOneSymbol);
               while(lengthOneSymbol+sizeDots+(totalWidth/secondLine.length())*2<r.getWidth())
               {
                   numbSymbol++;
                   lengthOneSymbol+=totalWidth/secondLine.length();
                }
               
                secondLine=(secondLine.substring(0, numbSymbol))+"...";
                totalWidth = fm.stringWidth(secondLine);
            }
            
            x = (int) (((r.getWidth() - totalWidth) )/4)+3;
            y=(int) (((r.getHeight()-((fm.getHeight())*2))/2+ fm.getAscent()*2)+2);
              if(object.errorMode){
                
                g.setColor(Color.red);
                g1.setStroke(new BasicStroke(2));
                g.drawString(name, (int)r.getX()+x, (int)r.getY()+y );
                g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),arcWidth,arcWidth);
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
               g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),arcWidth,arcWidth);
              
            }
            //g.drawString(secondLine, (int)r.getX()+x, (int)r.getY()+y );
            
           // g.drawRoundRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),arcWidth,arcWidth);
            
          
            
        }
    }
    
}
