package Interface;

import projet_monopoly.Case.Cases;

public class Pion {
	private String Image;
	private int AxeX;
	private int AxeY;
	
	
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public int getAxeX() {
		return AxeX;
	}
	public void setAxeX(int axeX) {
		if(axeX<0 || axeX>10) {
			throw new IllegalArgumentException("axe x");
		}
		AxeX = axeX;
	}
	public int getAxeY() {
		return AxeY;
	}
	public void setAxeY(int axeY) {
		if(axeY<0 || axeY>10) {
			throw new IllegalArgumentException("axe y");
		}
		AxeY = axeY;
	}
	public Pion(String image) {
		super();
		this.setImage(image);
		this.AxeX = 10;
		this.AxeY = 10;
	}
	
	public void deplacerPion(int X, int Y) {
		this.setAxeX(X);
		this.setAxeY(Y);
		
	}
	
	public void deplacerPion(Cases c) {
		int num =c.getNumeroCase();
		switch(num)
		{
		case 0:deplacerPion(10,10);
		break;
		case 1:deplacerPion(9,10);
		break;
		case 2:deplacerPion(8,10);
		break;
		case 3:deplacerPion(7,10);
		break;
		case 4:deplacerPion(6,10);
		break;
		case 5:deplacerPion(5,10);
		break;
		case 6:deplacerPion(4,10);
		break;
		case 7:deplacerPion(3,10);
		break;
		case 8:deplacerPion(2,10);
		break;
		case 9:deplacerPion(1,10);
		break;
		case 10:deplacerPion(0,10);
		break;
		case 11:deplacerPion(0,9);
		break;
		case 12:deplacerPion(0,8);
		break;
		case 13:deplacerPion(0,7);
		break;
		case 14:deplacerPion(0,6);
		break;
		case 15:deplacerPion(0,5);
		break;
		case 16:deplacerPion(0,4);
		break;
		case 17:deplacerPion(0,3);
		break;
		case 18:deplacerPion(0,2);
		break;
		case 19:deplacerPion(0,1);
		break;
		case 20:deplacerPion(0,0);
		break;
		case 21:deplacerPion(1,0);
		break;
		case 22:deplacerPion(2,0);
		break;
		case 23:deplacerPion(3,0);
		break;
		case 24:deplacerPion(4,0);
		break;
		case 25:deplacerPion(5,0);
		break;
		case 26:deplacerPion(6,0);
		break;
		case 27:deplacerPion(7,0);
		break;
		case 28:deplacerPion(8,0);
		break;
		case 29:deplacerPion(9,0);
		break;
		case 30:deplacerPion(10,0);
		break;
		case 31:deplacerPion(10,1);
		break;
		case 32:deplacerPion(10,2);
		break;
		case 33:deplacerPion(10,3);
		break;
		case 34:deplacerPion(10,4);
		break;
		case 35:deplacerPion(10,5);
		break;
		case 36:deplacerPion(10,6);
		break;
		case 37:deplacerPion(10,7);
		break;
		case 38:deplacerPion(10,8);
		break;
		case 39:deplacerPion(10,9);
		}
	}
	
	
}
