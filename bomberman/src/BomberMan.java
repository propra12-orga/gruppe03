import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 








public class BomberMan extends PApplet {

	


final int WINDOW_WIDTH = 600,  // SPielfeld breite
WINDOW_HEIGHT = 600, // SPielfeld höhe
TILE_SIZE = 30,
FRAME_RATE = 20,
EXPLODE_R = 3;

Field map = new Field(WINDOW_WIDTH, WINDOW_HEIGHT);
Player dau1 = new Player(map, 0, 0, "Spieler 1"); //position des 1. Spielers
Player dau2 = new Player(map, 18, 18, "Spieler 2"); // pos. des 2. Spielers

boolean stop = false;

public void setup() {
  size(WINDOW_WIDTH, WINDOW_HEIGHT);
  smooth();
}

public void keyPressed() {
  switch(key) {

  // Steuerung 1 Spieler
  case '4':
    dau1.moveLeft();
    break;        

  case '6':
    dau1.moveRight();
    break; 

  case '8':
    dau1.moveUp();
    break;  

  case '5':
    dau1.moveDown(); 
    break;

  case '0':
    dau1.setBomb();
    break;        
    // Steuerung Spieler 2
  case 'a':
    dau2.moveLeft();
    break;        

  case 'd':
    dau2.moveRight();
    break; 

  case 'w':
    dau2.moveUp();
    break;  

  case 's':
    dau2.moveDown(); 
    break;

  case ' ':
    dau2.setBomb();
    break;        


  default: 
    break;
  }
}


public void draw() {  

  if(!stop) {

    dau1.checkBombAndPlayer();
    dau2.checkBombAndPlayer();

    background(255);
    map.display();
  }
  else 
    background(0);
}




class BlockedTile extends Tile {

  BlockedTile() {
    super("blocked");
  }

  public void display(int x, int y) {
    fill(0);
    rect(x, y, TILE_SIZE, TILE_SIZE);
  }

  public String getType() {
    return "blocked";
  }

  public boolean usable() {
    return false;
  }
}




class Bomb {
  float timer, timeStart, timerEnd;
  boolean explode, operating;
  int positionX, positionY, explodeRadius;
  Field area;

  Bomb(Field f) {
    area = f;
    operating = false;
  }
 // timer für die explosion der bombe
  public void setBomb(int x, int y, int z, float t) {
    positionX = x;
    positionY = y;
    explodeRadius = z;
    timerEnd = t;
    area.box[positionX][positionY].setBomb();
    operating = true;
    timeStart = (float)frameCount / FRAME_RATE;    
  }

  public void checkExplode() {
    timer = (float)frameCount / FRAME_RATE - timeStart;

    if(timer >= timerEnd)
      explode();
  }
// explosion in allen richtungen
  public void explode() {

    //nach rechts
    if( positionX+1 < area.tilesX) { 
      if( area.box[positionX+1][positionY].getType() != "blocked" ) {
        for( int x = positionX; x <= positionX + explodeRadius; x++ ) {
          if( x < area.tilesX ) {     
            if( area.box[x][positionY].getType() == "wall" ) {
              area.box[x][positionY].setType("explode");  
              break;
            } 
            else
              area.box[x][positionY].setType("explode");
          }
        }
      }
    }

    //nach links
    if( positionX-1 >= 0) {
      if( area.box[positionX-1][positionY].getType() != "blocked" ) {
        for( int x = positionX; x >= positionX - explodeRadius; x-- ) {   
          if( x >= 0 ) {
            if( area.box[x][positionY].getType() == "wall" ) {
              area.box[x][positionY].setType("explode");  
              break;
            } 
            else
              area.box[x][positionY].setType("explode");
          }
        }
      }
    }

    //nach unten
    if( positionY+1 < area.tilesY ) {
      if( area.box[positionX][positionY+1].getType() != "blocked" ) {
        for( int y = positionY; y <= positionY + explodeRadius; y++ ) {   
          if( y < area.tilesY ) {
            if( area.box[positionX][y].getType() == "wall" ) {
              area.box[positionX][y].setType("explode");  
              break;
            } 
            else
              area.box[positionX][y].setType("explode");
          }
        }
      }
    }
    
    //nach oben
    if( positionY-1 >= 0) {
      if( area.box[positionX][positionY-1].getType() != "blocked" ) {
        for( int y = positionY; y >= positionY - explodeRadius; y-- ) {   
          if( y >= 0 ) {
            if( area.box[positionX][y].getType() == "wall" ) {
              area.box[positionX][y].setType("explode");  
              break;
            } 
            else
              area.box[positionX][y].setType("explode");
          }
        }
      }
    }


    area.box[positionX][positionY].delBomb();
    operating = false;
    timer = 0;

  }

  public boolean isWorking() {
    return operating;
  }

}












//Spielfeld
class Field {
  int width, height, stdX, stdY,
  tilesX, tilesY;

  Tile[][] box;

  Field(int a, int b) {
    width = a;
    height = b;

    tilesX = a/TILE_SIZE;
    if(tilesX%2 == 0) {
      tilesX--;
      stdX = (WINDOW_WIDTH - tilesX * TILE_SIZE) / 2;
    }

    tilesY = b/TILE_SIZE;
    if(tilesY%2 == 0) {
      tilesY--;
      stdY = (WINDOW_HEIGHT - tilesY * TILE_SIZE) / 2;
    }

    box = new Tile[tilesX][tilesY];
    for( int i = 0; i < tilesX; i++ ) {
      for( int j = 0; j < tilesY; j++ ) {
        if( (i+1)%2 == 0 && (j+1)%2 == 0) 
          box[i][j] = new BlockedTile();
        else if (  (i+1)%2 == 0 || (j+1)%2 == 0 ) 
          box[i][j] = new Tile("wall");
        else
          box[i][j] = new Tile("free");
      }
    }
  }

  public void display() {
    for( int i = 0; i < tilesX; i++ ) {
      for( int j = 0; j < tilesY; j++ ) {
        box[i][j].display( stdX + i*TILE_SIZE, stdY + j*TILE_SIZE );
      }
    }
  }

  public String getTileContent(int x, int y) {
    return box[x][y].getType();
  }

  public void setTileContent(int x, int y, String t) {
    box[x][y].setType(t);
  }

}


class Player {
  //Positonen der Spieler / oben links und unten rechts
  int ppX, ppY;
  String name;
  
  
  Field area;
  Bomb bomb;

  Player(Field f, int x, int y, String n) {
    area = f;
    bomb = new Bomb(area);
    ppX = x;
    ppY = y;
    name = n;
    area.box[ppX][ppY].setType(name);
    if( ppX+1 < area.tilesX ) 
      area.box[ppX+1][ppY].setType("free");
    if ( ppX-1 >= 0 ) 
      area.box[ppX-1][ppY].setType("free");
    if( ppY+1 < area.tilesY ) 
      area.box[ppX][ppY+1].setType("free");
    if ( ppY-1 >= 0 )
      area.box[ppX][ppY-1].setType("free");
  }

  public void moveLeft() {      
    if( ppX - 1 >= 0 && area.box[ppX-1][ppY].usable() ) {
      area.box[ppX][ppY].setType("free");
      area.box[ppX][ppY].delPlayer();
      ppX--;
      checkKilled(ppX, ppY);
      area.box[ppX][ppY].setType(name);
      area.box[ppX][ppY].setPlayer();
    }
  }

  public void moveRight() {      
    if( ppX + 1 < area.tilesX && area.box[ppX+1][ppY].usable() ) {
      area.box[ppX][ppY].setType("free");
      area.box[ppX][ppY].delPlayer();
      ppX++;
      checkKilled(ppX, ppY);
      area.box[ppX][ppY].setType(name);
      area.box[ppX][ppY].setPlayer();
    }
  }

  public void moveUp() {      
    if( ppY - 1 >= 0 && area.box[ppX][ppY-1].usable() ) {
      area.box[ppX][ppY].setType("free");
      area.box[ppX][ppY].delPlayer();
      ppY--;
      checkKilled(ppX, ppY);
      area.box[ppX][ppY].setType(name);
      area.box[ppX][ppY].setPlayer();
    }
  }

  public void moveDown() {      
    if( ppY + 1 < area.tilesY && area.box[ppX][ppY+1].usable() ) {
      area.box[ppX][ppY].setType("free");
      area.box[ppX][ppY].delPlayer();
      ppY++;
      checkKilled(ppX, ppY);
      area.box[ppX][ppY].setType(name);
      area.box[ppX][ppY].setPlayer();
    }
  }

  public void setBomb() {
    if(! bomb.isWorking() ) {
      bomb.setBomb( ppX, ppY, EXPLODE_R , 3);    
    }
  }

  public void checkBombAndPlayer() {
    if( area.box[ppX][ppY].getType() == "explode" )
      killed();
    if( bomb.isWorking() )
      bomb.checkExplode();
  }

  public void checkKilled(int x, int y) {
    if( area.box[x][y].getType() == "explode" )
      killed();
  }

  public void killed() {
    println( name + " killed!");
    stop = true;
  }

}








//felder
class Tile {
  String type;
  boolean usedByPlayer, bombed, exploding;
  int frameCounter;
  
  Tile (String t) {
    type = t;
    bombed = false;
    exploding = false;
    usedByPlayer = false;
  }
  
  public void display(int x, int y) {
    fill(255);
    rect(x, y, TILE_SIZE, TILE_SIZE);
    
    if(type == "wall") {
        fill(000, 000, 255);
        rect( x+5, y+5, TILE_SIZE-10, TILE_SIZE-10);
    }    
    
    if(type == "Spieler 1") {
      fill(0xaaaa00FF);
      ellipse( x+TILE_SIZE/2, y+TILE_SIZE/2, TILE_SIZE*3/4, TILE_SIZE*3/4 );
    }
    
    if(type == "Spieler 2") {
      fill(0xffFFFF00);
      ellipse( x+TILE_SIZE/2, y+TILE_SIZE/2, TILE_SIZE*3/4, TILE_SIZE*3/4 );
    }
      
    
    if(type == "explode") {       
      if(!exploding) {
        exploding = true;
        frameCounter = 0;
      }
      frameCounter++;
      if( frameCounter/FRAME_RATE == 1 ) {
       type = "free";
       exploding = false;
      } 
      else {
        fill(0xffFF0000);
        rect( x+5, y+5, TILE_SIZE-10, TILE_SIZE-10);
      } 
      
    }
    
    if(bombed) {
      fill(0);
      ellipse( x+TILE_SIZE/2, y+TILE_SIZE/2, TILE_SIZE*1/2, TILE_SIZE*1/2);
    } 
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String t) {
    type = t; 
  }
  
  public void setBomb() {
    bombed = true;
  }
  
  public void delBomb() {
    bombed = false;
  }
  
  public void setPlayer() {
    usedByPlayer = true;
  }
  
  public void delPlayer() {
    usedByPlayer = false;
  }
  
  public boolean usable() {
    if( type != "wall" && !usedByPlayer) 
      return true;
    else
      return false;
  }
  
} //Applet start inkl. Menü
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#aaaa00", "BomberMan" });
  }
}