/*
 * Copyright 2009-2015 TauNova (http://taunova.com). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
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

/**
 *
 * @author Maryan
 */
public class DefaultRenderer implements Renderer<PeerUniverseObject> {

    final int arc = 25;
    protected int width = 80;
    protected int height = 30;

    @Override
    public RoundRectangle2D getObjectShape(PeerUniverseObject object, Attributes attr) {
        Rectangle r = attr.getObjectRectangle();
        RoundRectangle2D rect = new RoundRectangle2D.Double(r.x, r.y, r.width, r.height, arc, arc);

        return rect;
    }

    @Override
    public void render(Graphics g, PeerUniverseObject object, Attributes attr) {
        Graphics2D g1 = (Graphics2D) g;
        final int fontSize = 16;

        g.setFont(new Font(null, Font.BOLD, fontSize));
        g.setColor(Color.WHITE);

        RoundRectangle2D r = getObjectShape(object, attr);

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setRenderingHints(rh);

        String text = object.getName();

        FontMetrics fm = g.getFontMetrics();
        int totalWidth = fm.stringWidth(text);

      
             String tmp;
             
             int tmpWidth=totalWidth;
             int gap=text.indexOf(' ');
             
             if(gap!=-1){
             tmp=text.substring(0, text.indexOf(' '));
             tmpWidth=fm.stringWidth(tmp);
             }

        if (attr.isMultilineEnabled()
                && text.contains(" ")
                && totalWidth > r.getWidth()
                && tmpWidth<r.getWidth()) {

            g.fillRoundRect((int) r.getX(),
                    (int) r.getY(),
                    (int) r.getWidth(),
                    (int) r.getHeight(),
                    (int) r.getArcWidth(),
                    (int) r.getArcHeight());

            g.setColor(Color.BLACK);

            renderMultiline(g, text, r, object);
          
        } else {
            g.fillRoundRect((int) r.getX(),
                    (int) r.getY(),
                    (int) r.getWidth(),
                    (int) r.getHeight(),
                    (int) r.getArcWidth(),
                    (int) r.getArcHeight());

            g.setColor(Color.BLACK);
            renderOneLine(g, text, r, object);
        }
        
       if(attr.isSelected())
        {
           
            g.setColor(Color.BLUE);
            g1.setStroke(new BasicStroke(2));
            
            g.drawRoundRect((int)r.getX(),
                    (int)r.getY(),
                    (int) r.getWidth(),
                    (int)r.getHeight(),
                    (int)r.getArcWidth(),
                    (int)r.getArcHeight());
            
          g.setColor(Color.CYAN);
           g1.setStroke(new BasicStroke(3));
            
            g.drawRoundRect((int)r.getX()-2,
                    (int) r.getY()-2,
                    (int)r.getWidth()+4, 
                    (int)r.getHeight()+4,
                    (int)r.getArcWidth()+2,
                    (int)r.getArcHeight()+2);
        }
    }

    public void renderOneLine(Graphics g, String text, RoundRectangle2D r, PeerUniverseObject object) {
        FontMetrics fm = g.getFontMetrics();
        Graphics2D g1 = (Graphics2D) g;
        int totalWidth = fm.stringWidth(text);
        String strTmp="";
          if(totalWidth>r.getWidth()) {
               
               
            
               int sizeDots =fm.stringWidth("...");
               int i=0;
               
               
               while(sizeDots+(totalWidth)+7>r.getWidth())
               {
                  strTmp=text.substring(0, (text.length())-i);
                   i++;
                   totalWidth=fm.stringWidth(strTmp);
                }
               
                text=(strTmp)+"...";
                totalWidth = fm.stringWidth(text);
            }
            
            int x = 5;
            int y=(int) (((r.getHeight()- fm.getHeight()) / 2) + fm.getAscent());
            
            if(object.errorMode){
                
                g.setColor(Color.red);
                g1.setStroke(new BasicStroke(2));
                g.drawString(text, (int)r.getX()+x, (int)r.getY()+y );
                g.drawRoundRect((int)r.getX(), 
                        (int)r.getY(), 
                        (int)r.getWidth(), 
                        (int)r.getHeight(),
                        (int)r.getArcWidth(),
                        (int)r.getArcHeight());
                g.setColor(Color.PINK);
                
               
                g1.setStroke(new BasicStroke(3));
                g.drawRoundRect((int)r.getX()-2,
                        (int) r.getY()-2, 
                        (int)r.getWidth()+4, 
                        (int)r.getHeight()+4,
                        (int)r.getArcWidth()+2,
                        (int)r.getArcHeight()+2);
            
            }
            else if(object.disableMode){
                g.setColor(Color.LIGHT_GRAY);
                g.drawString(text, (int)r.getX()+x, (int)r.getY()+y );
                float dash[] = { 4};
                BasicStroke dashedStroke=new BasicStroke(1, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10, dash, 0);
                g1.fill(dashedStroke.createStrokedShape(r));
               
                
              
            }
            else{
            
        
               g.drawString(text, (int)r.getX()+x, (int)r.getY()+y );
               g.drawRoundRect((int)r.getX(), 
                       (int)r.getY(), 
                       (int)r.getWidth(), 
                       (int)r.getHeight(),
                       (int)r.getArcWidth(),
                       (int)r.getArcHeight());
              
            }

    }

    public void renderMultiline(Graphics g, String name, RoundRectangle2D r, PeerUniverseObject object) {

        FontMetrics fm = g.getFontMetrics();
        Graphics2D g1 = (Graphics2D) g;
        int totalWidth = fm.stringWidth(name);
        String strTmp="";
          if(totalWidth>r.getWidth()) {
               
               
            
               int sizeDots =fm.stringWidth("...");
               int i=0;
               
               
               while(sizeDots+(totalWidth)+7>r.getWidth())
               {
                  strTmp=name.substring(0, (name.length())-i);
                   i++;
                   totalWidth=fm.stringWidth(strTmp);
                }
               
                name=(strTmp)+"...";
                totalWidth = fm.stringWidth(name);
            }
            
            int x = 5;
            int y=(int) (((r.getHeight()- fm.getHeight()) / 2) + fm.getAscent());
            
            if(object.errorMode){
                
                g.setColor(Color.red);
                g1.setStroke(new BasicStroke(2));
                g.drawString(name, (int)r.getX()+x, (int)r.getY()+y );
                g.drawRoundRect((int)r.getX(), 
                        (int)r.getY(), 
                        (int)r.getWidth(), 
                        (int)r.getHeight(),
                        (int)r.getArcWidth(),
                        (int)r.getArcHeight());
                g.setColor(Color.PINK);
                
               
                g1.setStroke(new BasicStroke(3));
                g.drawRoundRect((int)r.getX()-2,
                        (int) r.getY()-2, 
                        (int)r.getWidth()+4, 
                        (int)r.getHeight()+4,
                        (int)r.getArcWidth()+2,
                        (int)r.getArcHeight()+2);
            
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
               g.drawRoundRect((int)r.getX(), 
                       (int)r.getY(), 
                       (int)r.getWidth(), 
                       (int)r.getHeight(),
                       (int)r.getArcWidth(),
                       (int)r.getArcHeight());
              
            

        }
    }
}
