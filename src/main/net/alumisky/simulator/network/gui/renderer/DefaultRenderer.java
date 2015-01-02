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
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import net.alumisky.simulator.network.gui.PeerUniverseObject;
/**
 *
 * @author Maryan
 */
public class DefaultRenderer implements Renderer<PeerUniverseObject>{
    public void render(Graphics g, PeerUniverseObject object, Attributes view) {
            
            int fontSize=16;
            g.setFont(new Font("TimesRoman", Font.BOLD, fontSize)); 
            g.setColor(Color.WHITE);
            Rectangle r = view.getObjectRectangle();
            
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
            
             FontMetrics fm = g.getFontMetrics();
             String s=object.getName()+"dsdasa";
             
            int totalWidth = fm.stringWidth(s);
  
            if(totalWidth>r.width) {
               int lengthOneSymbol=(totalWidth/s.length());
               
            
               int sizeDots =fm.stringWidth("...");
               int numbSymbol=0;
               
                System.out.println(lengthOneSymbol);
               while(lengthOneSymbol+sizeDots+totalWidth/s.length()<r.width)
               {
                   numbSymbol++;
                   lengthOneSymbol+=totalWidth/s.length();
                }
               
                s=(s.substring(0, numbSymbol))+"...";
                totalWidth = fm.stringWidth(s);
            }
            
            int x = (int) (((r.width - totalWidth) )/4)+3;
            int y=(int) (((r.height - fm.getHeight()) / 2) + fm.getAscent());
            
            g.drawString(s, r.x+x, r.y+y );
            g.drawRoundRect(r.x, r.y, r.width, r.height,30,30);
            
        
            
        }
    
}
