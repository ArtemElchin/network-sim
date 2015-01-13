/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.alumisky.simulator.network.gui.renderer;

import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Renderer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import net.alumisky.simulator.network.gui.PeerUniverseObject;
import javax.swing.event.ListSelectionEvent;
/**
 *
 * @author Maryan
 */
public class DefaultRenderer implements Renderer<PeerUniverseObject>{
    public void render(Graphics g, PeerUniverseObject object, Attributes view) {
            Graphics2D g1 = (Graphics2D) g;
            
            int fontSize=16;
            
           
            g.setFont(new Font(null, Font.BOLD, fontSize)); 
            g.setColor(Color.WHITE);
            
            Rectangle r = view.getObjectRectangle();
            
            RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            g1.setRenderingHints(rh);
        
             String s=object.getName()+"dd gfdgfs fsfcsds";
             
           
            if(view.multilineSupport){
                
                g.fillRect(r.x, r.y, r.width, r.height);
                g.setColor(Color.BLACK);
                renderMultiline(g,s,r);
                 
            }
            else{
                g.fillRect(r.x, r.y, r.width, r.height);
                g.setColor(Color.BLACK);
                renderOneLine(g,s,r);
            
            }
            
        }
    
    public void renderOneLine(Graphics g, String name, Rectangle r)
    {
        
          FontMetrics fm = g.getFontMetrics();
        
        int totalWidth = fm.stringWidth(name);
        
          if(totalWidth>r.width) {
               int lengthOneSymbol=(totalWidth/name.length());
               
            
               int sizeDots =fm.stringWidth("...");
               int numbSymbol=0;
               
               
               while((lengthOneSymbol+sizeDots+(totalWidth/name.length())*2)<r.width)
               {
                   numbSymbol++;
                   lengthOneSymbol+=totalWidth/name.length();
                }
               
                name=(name.substring(0, numbSymbol))+"...";
                totalWidth = fm.stringWidth(name);
            }
            
            int x = (int) (((r.width - totalWidth) )/4)+3;
            int y=(int) (((r.height - fm.getHeight()) / 2) + fm.getAscent());
            
            g.drawString(name, r.x+x, r.y+y );
           
            g.drawRoundRect(r.x, r.y, r.width, r.height,15,15);
          
            
        
    }
    
    public void renderMultiline(Graphics g, String name, Rectangle r)
    {
        final int arcWidth=25, arcHeight=25;
        FontMetrics fm = g.getFontMetrics();
       
        int totalWidth = fm.stringWidth(name);
        
        
        
        String firstLine="", secondLine="", tmpName=name;
        
        if(totalWidth>r.width){
            int gap=0;
            while(totalWidth>r.width)
            {
                gap=tmpName.lastIndexOf(' ');
                firstLine=tmpName.substring(0, gap);
               
                totalWidth=fm.stringWidth(firstLine);
                tmpName=firstLine;
                
            }
            secondLine=name.substring(gap+1, name.length());
            
            
            
            int x = (int) (((r.width - totalWidth) )/4)+3;
            int y=(r.height-(fm.getHeight()*2))/2+ fm.getAscent()-2;
          
            g.drawString(firstLine, r.x+x, r.y+y );
            
           
            
            
            
           // System.out.println(firstLine+"  "+secondLine);
            
            
             totalWidth = fm.stringWidth(secondLine);
        
          if(totalWidth>r.width) {
               int lengthOneSymbol=(totalWidth/secondLine.length());
               
            
               int sizeDots =fm.stringWidth("...");
               int numbSymbol=0;
               
               // System.out.println(lengthOneSymbol);
               while(lengthOneSymbol+sizeDots+(totalWidth/secondLine.length())*2<r.width)
               {
                   numbSymbol++;
                   lengthOneSymbol+=totalWidth/secondLine.length();
                }
               
                secondLine=(secondLine.substring(0, numbSymbol))+"...";
                totalWidth = fm.stringWidth(secondLine);
            }
            
            x = (int) (((r.width - totalWidth) )/4)+3;
            y=((r.height-((fm.getHeight())*2))/2+ fm.getAscent()*2)+2;
            
            g.drawString(secondLine, r.x+x, r.y+y );
            
            g.drawRoundRect(r.x, r.y, r.width, r.height,arcWidth,arcHeight);
            
            //g.setColor(Color.LIGHT_GRAY);
            //g.drawRoundRect(r.x-2, r.y-2, r.width+4, r.height+4,30,30);
            
        }
    }
    
}
