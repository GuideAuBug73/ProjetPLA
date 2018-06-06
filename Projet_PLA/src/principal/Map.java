package principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import basic.Cellule;

public class Map {
	// tableau de cellules
	public Cellule cellules[][] = new Cellule[16][30];
	// nombre de cellules
	int m_w;
	int m_h;
	// taille de la fenetre
	int m_sizew;
	int m_sizeh;
	Entity a = null;
	// sprites
	BufferedImage m_spritefield;
	BufferedImage m_spritewall;
	
	Arbre abr;

    //Generation de la map
    int maxTunnels = 80;
    int maxLength = 11;

    public Map(int h, int w, int sizeh, int sizew, BufferedImage spritewall, BufferedImage spritefield) {
        m_w = w;
        m_h = h;
        m_sizew = sizew;
        m_sizeh = sizeh;
        m_spritefield = spritefield;
        m_spritewall = spritewall;

        int[][] map = generateMap();
        /*for (int i = 0; i < m_h; i++) {
            System.out.println("\n________________________________________________________________________________");
            for (int j = 0; j < m_w; j++) {
                System.out.print(" | " + map[i][j]);
            }
        }*/

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 0) {
                    cellules[i][j] = new Cellule(null, true, j * m_sizew / m_w, i * m_sizeh / m_h);
                } else {
                    cellules[i][j] = new Cellule(null, false, j * m_sizew / m_w, i * m_sizeh / m_h);

                }
            }
        }
    }


    //initialisation du tableau de 0-1 pour la map
    int[][] createArray(int num, int hauteur, int largeur) {
        int array[][] = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                array[i][j] = num;
            }
        }
        return array;
    }

    //generation de la map aleatoire
    int[][] generateMap() {
        int[][] map = createArray(1, m_h, m_w);
        int currentLigne = (int) Math.floor(Math.random() * m_h);
        int currentColonne = (int) Math.floor(Math.random() * m_w);
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[] lastDirection = new int[m_w * m_h];
        int[] randomDirection;

        while (maxTunnels != 0 && m_h != 0 && m_w != 0 && maxLength != 0) {

            do {
                randomDirection = directions[(int) Math.floor(Math.random() * directions.length)];
            } while ((randomDirection[0] == -lastDirection[0] &&
                    randomDirection[1] == -lastDirection[1]) ||
                    (randomDirection[0] == lastDirection[0] &&
                            randomDirection[1] == lastDirection[1]));

            int randomLongeur = (int) Math.ceil(Math.random() * maxLength);
            int tunnelLongueur = 0;

            while (tunnelLongueur < randomLongeur) {
                if (((currentLigne == 0) && (randomDirection[0] == -1)) ||
                        ((currentColonne == 0) && (randomDirection[1] == -1)) ||
                        ((currentLigne == m_h - 1) && (randomDirection[0] == 1)) ||
                        ((currentColonne == m_w - 1) && (randomDirection[1] == 1))) {
                    break;
                } else {
                    map[currentLigne][currentColonne] = 0;
                    currentLigne += randomDirection[0];
                    currentColonne += randomDirection[1];
                    tunnelLongueur++;
                }
            }
            if (tunnelLongueur != 0) {
                lastDirection = randomDirection;
                maxTunnels--;
            }
        }

        return map;
    }
    
    public boolean testMap(Spawn mob[], Cellule start) {
		abr = new Arbre(start);
		cellules[abr.c.y / Options.TAILLE_CELLULE][abr.c.x / Options.TAILLE_CELLULE].visite = true;
		for (int i = 0; i < mob.length; i++) {
			if (!testSpawn(mob[i], abr))
				return false;
		}
		return true;

	}

	private boolean testSpawn(Spawn s, Arbre a) {
		if (a.c.x / Options.TAILLE_CELLULE == s.i && a.c.y / Options.TAILLE_CELLULE == s.j) {
			return true;
		}
		if (a.c.x >= Options.nb_px_x_min && a.c.y - Options.TAILLE_CELLULE >= Options.nb_px_y_min
				&& a.c.x < Options.nb_px_x_max && a.c.y - Options.TAILLE_CELLULE < Options.nb_px_y_max
				&& !(cellules[(a.c.y - Options.TAILLE_CELLULE) / Options.TAILLE_CELLULE][a.c.x / Options.TAILLE_CELLULE].visite)) {
			cellules[(a.c.y - Options.TAILLE_CELLULE) / Options.TAILLE_CELLULE][a.c.x / Options.TAILLE_CELLULE].visite = true;
			a.filsN = new Arbre(cellules[a.c.y - Options.TAILLE_CELLULE][a.c.x / Options.TAILLE_CELLULE]);
			if (a.filsN.c.libre && testSpawn(s, a.filsN)) {
				return true;
			}

		}
		if (a.c.x + Options.TAILLE_CELLULE >= Options.nb_px_x_min && a.c.y >= Options.nb_px_y_min && a.c.x + Options.TAILLE_CELLULE < Options.nb_px_x_max && a.c.y < Options.nb_px_y_max
				&& !(cellules[a.c.y / Options.TAILLE_CELLULE][(a.c.x + Options.TAILLE_CELLULE) / Options.TAILLE_CELLULE].visite)) {
			cellules[a.c.y / Options.TAILLE_CELLULE][(a.c.x + Options.TAILLE_CELLULE) / Options.TAILLE_CELLULE].visite = true;
			a.filsE = new Arbre(cellules[a.c.y / Options.TAILLE_CELLULE][(a.c.x + Options.TAILLE_CELLULE) / Options.TAILLE_CELLULE]);
			if (a.filsE.c.libre && testSpawn(s, a.filsE)) {
				return true;
			}
		}
		if (a.c.x >= Options.nb_px_x_min && a.c.y + Options.TAILLE_CELLULE >= Options.nb_px_y_min && a.c.x < Options.nb_px_x_max && a.c.y + Options.TAILLE_CELLULE < Options.nb_px_y_max
				&& !(cellules[(a.c.y + Options.TAILLE_CELLULE) / Options.TAILLE_CELLULE][a.c.x / Options.TAILLE_CELLULE].visite)) {
			cellules[(a.c.y + Options.TAILLE_CELLULE) / Options.TAILLE_CELLULE][a.c.x / Options.TAILLE_CELLULE].visite = true;
			a.filsS = new Arbre(cellules[(a.c.y + Options.TAILLE_CELLULE) / Options.TAILLE_CELLULE][a.c.x / Options.TAILLE_CELLULE]);
			if (a.filsS.c.libre && testSpawn(s, a.filsS)) {
				return true;
			}

		}
		if (a.c.x - Options.TAILLE_CELLULE >= Options.nb_px_x_min && a.c.y >= Options.nb_px_y_min
				&& a.c.x - Options.TAILLE_CELLULE < Options.nb_px_x_max && a.c.y < Options.nb_px_y_max
				&& !(cellules[a.c.y / Options.TAILLE_CELLULE][(a.c.x - Options.TAILLE_CELLULE)
						/ Options.TAILLE_CELLULE].visite)) {
			cellules[a.c.y / Options.TAILLE_CELLULE][(a.c.x - Options.TAILLE_CELLULE)
					/ Options.TAILLE_CELLULE].visite = true;
			a.filsO = new Arbre(cellules[a.c.y / Options.TAILLE_CELLULE][(a.c.x - Options.TAILLE_CELLULE)
					/ Options.TAILLE_CELLULE]);
			if (a.filsO.c.libre && testSpawn(s, a.filsO)) {
				return true;
			}
		}
		return false;

	}

    public void paint(Graphics g) {
        Image img;
        for (int i = 0; i < m_h; i++) {
            for (int j = 0; j < m_w; j++) {
                if (cellules[i][j].libre == true) {
                    img = m_spritefield;
                } else
                    img = m_spritewall;
                g.drawImage(img, cellules[i][j].x, cellules[i][j].y, m_sizew / m_w, m_sizeh / m_h, null);
            }
        }
    }
}