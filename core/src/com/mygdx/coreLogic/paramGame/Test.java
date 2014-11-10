package com.mygdx.coreLogic.paramGame;


public class Test {

	static String path = "/home/abdelhak/workspace/IceBox/core/src/com/mygdx/coreLogic/levels/";

	
//	public static Niveau GetNiveau(int niveau) {
//		
//		System.out.println("/*/*/*/*/*/*/*/*/*Niveau 1/*/*/*/*/*/*/*/*/*");
//		Niveau niv1 = new Niveau();
//		niv1.charger(path + "niveau" + niveau + ".xml");
//		niv1.affiche();
//		System.out.println("");
//		
//		return niv1;
//	}
//	
	
	
	public static Chapitre buildChapitre() {
		Chapitre chapitre = new Chapitre();
		
		for (int i = 1; i < 10; i++) {
			System.out.println("/*/*/*/*/*/*/*/*/*Niveau " + i + "/*/*/*/*/*/*/*/*/*");
			Niveau niv = new Niveau();
			niv.charger(path + "niveau" + i + ".xml");
			niv.affiche();
			System.out.println("");
			
			chapitre.addNiveau(niv);
		}
		
		return chapitre;
		
	}

}
