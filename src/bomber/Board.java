package bomber;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.*;

public class Board extends JPanel implements ActionListener {
	private Figur bomber1, bomber2;
	private Image pfeilLinks, pfeilRechts, pfeilOben, pfeilUnten, startscreen, restartscreen, block0, block1, block2, bomb, explo, broeckel, bogen, triforce, stiefel,
			bombentasche, reichweite, winner1, winner2, draw;
	private Timer time;
	private Field spielfeld = new Field();
	public static boolean running, neuesSpiel;
	public final int blocksize = 60;
	private int restartzaehler;
	public static int winner;
	public File lvltext;

	// winner = {1,2,3} 3<=>draw

	public Board() {
		restartzaehler = 0;
		running = false;
		neuesSpiel = false;
		bomber1 = new Figur(60 * spielfeld.player1x, 60 * spielfeld.player1y, 1);
		bomber2 = new Figur(60 * spielfeld.player2x, 60 * spielfeld.player2y, 1);
		addKeyListener(new AL());
		setFocusable(true);

		ImageIcon istart = new ImageIcon("bilder/Startscreen.jpg");
		startscreen = istart.getImage();
		ImageIcon iWinner1 = new ImageIcon("bilder/Restartscreen-1won.jpg");
		winner1 = iWinner1.getImage();
		ImageIcon iWinner2 = new ImageIcon("bilder/Restartecreen-2won.jpg");
		winner2 = iWinner2.getImage();
		ImageIcon iDraw = new ImageIcon("bilder/Restartscreen-draw.jpg");
		draw = iDraw.getImage();

		ImageIcon irestart = new ImageIcon("bilder/Restartecreen.jpg");
		restartscreen = irestart.getImage();
		ImageIcon i0 = new ImageIcon("bilder/block0.jpg");
		block0 = i0.getImage();
		ImageIcon i1 = new ImageIcon("bilder/block1.jpg");
		block1 = i1.getImage();
		ImageIcon i2 = new ImageIcon("bilder/block2.jpg");
		block2 = i2.getImage();
		ImageIcon i10 = new ImageIcon("bilder/blauebombe2.png");
		bomb = i10.getImage();
		ImageIcon i11 = new ImageIcon("bilder/explosion2.jpg");
		explo = i11.getImage();
		ImageIcon i12 = new ImageIcon("bilder/broeckel.jpg");
		broeckel = i12.getImage();
		ImageIcon i7 = new ImageIcon("bilder/triforce.gif");
		triforce = i7.getImage();
		ImageIcon i20 = new ImageIcon("bilder/Kleine_Bombentasche.png");
		bombentasche = i20.getImage();
		ImageIcon i21 = new ImageIcon("bilder/explosion.jpg");
		reichweite = i21.getImage();
		ImageIcon i22 = new ImageIcon("bilder/pegasusboots.png");
		stiefel = i22.getImage();
		ImageIcon i23 = new ImageIcon("bilder/Eisenbogen.png");
		bogen = i23.getImage();
		ImageIcon iarrow = new ImageIcon("bilder/pfeil_links.jpg");
		pfeilLinks = iarrow.getImage();
		// TODO pfeile laden
		time = new Timer(5, this);
		time.start();

	}

	// Spielfeld größe 15x11
	public void actionPerformed(ActionEvent e) {
		if (Frame.laden) {
			if (Frame.leveltoload == 1) {
				lvltext = new File("level/level1.txt");
				spielfeld.loadlevel(lvltext);
			} else if (Frame.leveltoload == 2) {
				lvltext = new File("level/level2.txt");
				spielfeld.loadlevel(lvltext);
			} else if (Frame.leveltoload == 3) {
				lvltext = new File("level/level3.txt");
				spielfeld.loadlevel(lvltext);
			}
		}
		if (running) {
			bomber1.Perma(spielfeld);
			bomber2.Perma(spielfeld);
			if (bomber1.lost()) {
				if (bomber2.isAlive() == false)
					winner = 3; // 3 heisst DRAW
				else
					winner = 2;
				running = false;
				neuesSpiel = true;
			} else if (bomber2.lost()) {
				if (bomber1.isAlive() == false)
					winner = 3; // 3 heisst DRAW
				else
					winner = 1;
				running = false;
				neuesSpiel = true;
			}
		} else if (neuesSpiel) {
			if (restartzaehler == 200) {
				restartzaehler = 0;
				spielfeld.restart();
				bomber1.restart(60 * spielfeld.player1x, 60 * spielfeld.player1y);
				bomber2.restart(60 * spielfeld.player2x, 60 * spielfeld.player2y);
				neuesSpiel = false;
				running = true;
			} else {
				restartzaehler += 1;
			}
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (running) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 11; j++) {

					if (spielfeld.getArry(i, j) == Field.block0) {
						g2d.drawImage(block0, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.block1) {
						g2d.drawImage(block1, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.block2) {
						g2d.drawImage(block2, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.bombe) {
						g2d.drawImage(bomb, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.explosion) {
						g2d.drawImage(explo, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.broeckeln) {
						g2d.drawImage(broeckel, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.ausgang) {
						g2d.drawImage(triforce, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.bombentasche) {
						g2d.drawImage(bombentasche, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.reichweite) {
						g2d.drawImage(reichweite, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.stiefel) {
						g2d.drawImage(stiefel, i * blocksize, j * blocksize, null);
					} else if (spielfeld.getArry(i, j) == Field.bogen) {
						g2d.drawImage(bogen, i * blocksize, j * blocksize, null);
					}
				}
			}
			g2d.drawImage(bomber1.getImage(), bomber1.getX(), bomber1.getY(), null);
			g2d.drawImage(bomber2.getImage(), bomber2.getX(), bomber2.getY(), null);
			if (bomber1.arrowIsWorking) { // TODO pfeilrichtungsabfrage

				g2d.drawImage(pfeilRechts, bomber1.arrowPosX, bomber1.arrowPosY, null);
			}
		} else if (running == false && neuesSpiel == false) {
			g2d.drawImage(startscreen, 0, 0, null);
		} else if (neuesSpiel == true) {
			if (winner == 0)
				g2d.drawImage(restartscreen, 0, 0, null);
			if (winner == 1)
				g2d.drawImage(winner1, 0, 0, null);
			else if (winner == 2)
				g2d.drawImage(winner2, 0, 0, null);
			else if (winner == 3)
				g2d.drawImage(draw, 0, 0, null);
		}
	}

	// KEY ABFRAGE
	// TODO Arrows !
	private class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE)
				System.exit(0);
			if (running) {
				// bomber1
				if (key == KeyEvent.VK_LEFT) {
					bomber1.setdxl(-bomber1.getMovementspeed());
				} else if (key == KeyEvent.VK_RIGHT) {
					bomber1.setdxr(bomber1.getMovementspeed());
				} else if (key == KeyEvent.VK_UP) {
					bomber1.setdyl(-bomber1.getMovementspeed());
				} else if (key == KeyEvent.VK_DOWN) {
					bomber1.setdyr(bomber1.getMovementspeed());
				} else if (key == KeyEvent.VK_CONTROL) {
					bomber1.setBomb(spielfeld);
				}

				// bomber2
				if (key == KeyEvent.VK_A) {
					bomber2.setdxl(-bomber2.getMovementspeed());
				} else if (key == KeyEvent.VK_D) {
					bomber2.setdxr(bomber2.getMovementspeed());
				} else if (key == KeyEvent.VK_W) {
					bomber2.setdyl(-bomber2.getMovementspeed());
				} else if (key == KeyEvent.VK_S) {
					bomber2.setdyr(bomber2.getMovementspeed());
				} else if (key == KeyEvent.VK_SPACE) {
					bomber2.setBomb(spielfeld);
				}
			} else {
				if (key == KeyEvent.VK_ENTER && neuesSpiel == false) {
					Board.running = true;
				}

			}
		}

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (running) {
				// bomber1
				if (key == KeyEvent.VK_LEFT)
					bomber1.setdxl(0);
				if (key == KeyEvent.VK_RIGHT)
					bomber1.setdxr(0);
				if (key == KeyEvent.VK_UP)
					bomber1.setdyl(0);
				if (key == KeyEvent.VK_DOWN)
					bomber1.setdyr(0);
				// bomber2
				if (key == KeyEvent.VK_A)
					bomber2.setdxl(0);
				if (key == KeyEvent.VK_D)
					bomber2.setdxr(0);
				if (key == KeyEvent.VK_W)
					bomber2.setdyl(0);
				if (key == KeyEvent.VK_S)
					bomber2.setdyr(0);

			}
		}
	}
}
