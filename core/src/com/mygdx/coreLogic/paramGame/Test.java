package com.mygdx.coreLogic.paramGame;


public class Test {

	static String path = "/home/abdelhak/workspace/IceBox/core/src/com/mygdx/coreLogic/levels/";

	public static Niveau GetNiveau(int niveau) {
		
		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 1/*/*/*/*/*/*/*/*/*");
		Niveau niv1 = new Niveau();
		niv1.charger(path + "niveau" + niveau + ".xml");
		niv1.affiche();
		System.out.println("");
		
		return niv1;
	}
	
//	public static void test() {
//
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 2/*/*/*/*/*/*/*/*/*");
//		Niveau niv2 = new Niveau();
//		niv2.charger(path + "niveau2.xml");
//		niv2.affiche();
//		System.out.println("");
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 3/*/*/*/*/*/*/*/*/*");
//		Niveau niv3 = new Niveau();
//		niv3.charger(path + "niveau3.xml");
//		niv3.affiche();
//		System.out.println("");
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 4/*/*/*/*/*/*/*/*/*");
//		Niveau niv4 = new Niveau();
//		niv4.charger(path + "niveau4.xml");
//		niv4.affiche();
//		System.out.println("");
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 5/*/*/*/*/*/*/*/*/*");
//		Niveau niv5 = new Niveau();
//		niv5.charger(path + "niveau5.xml");
//		niv5.affiche();
//		System.out.println("");
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 6/*/*/*/*/*/*/*/*/*");
//		Niveau niv6 = new Niveau();
//		niv6.charger(path + "niveau6.xml");
//		niv6.affiche();
//		System.out.println("");
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 7/*/*/*/*/*/*/*/*/*");
//		Niveau niv7 = new Niveau();
//		niv7.charger(path + "niveau7.xml");
//		niv7.affiche();
//		
//		System.out.println("");
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 8/*/*/*/*/*/*/*/*/*");
//		Niveau niv8 = new Niveau();
//		niv8.charger(path + "niveau8.xml");
//		niv8.affiche();
//		System.out.println("");
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 9/*/*/*/*/*/*/*/*/*");
//		Niveau niv9 = new Niveau();
//		niv9.charger(path + "niveau9.xml");
//		niv9.affiche();
//		System.out.println("");
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 10/*/*/*/*/*/*/*/*/*");
//		Niveau niv10 = new Niveau();
//		niv10.charger(path + "niveau10.xml");
//		niv10.affiche();
//		System.out.println("");
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Chapitre/*/*/*/*/*/*/*/*/*");
//		Chapitre chapitre= new Chapitre();
//		chapitre.addNiveau(niv1);
//		chapitre.addNiveau(niv2);
//		chapitre.addNiveau(niv3);
//		chapitre.addNiveau(niv4);
//		chapitre.addNiveau(niv5);
//		chapitre.addNiveau(niv6);
//		chapitre.addNiveau(niv7);
//		chapitre.addNiveau(niv8);
//		chapitre.addNiveau(niv9);
//		chapitre.addNiveau(niv10);
//		
//		chapitre.afficheChap();
//	}

}
