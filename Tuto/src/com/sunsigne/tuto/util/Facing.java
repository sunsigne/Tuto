package com.sunsigne.tuto.util;

public class Facing {

	////////// DIRECTION ////////////

	public enum DIRECTION {
		NULL(-1), LEFT(0), RIGHT(1), UP(2), DOWN(3);

		private int num;

		DIRECTION(int num) {
			this.num = num;
		}

		public int getNum() {
			return num;
		}
	}
}
