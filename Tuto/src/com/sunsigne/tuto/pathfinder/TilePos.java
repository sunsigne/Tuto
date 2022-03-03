package com.sunsigne.tuto.pathfinder;

public class TilePos {

	public int getTilePos(int pos) {
		int tileSize = 3 * 32;
		
		int modulus = pos % tileSize;

		int tilePos = modulus < tileSize / 2 ? pos - modulus : pos - modulus + tileSize;
		return tilePos;
	}
	
}
