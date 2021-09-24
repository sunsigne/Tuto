package com.sunsigne.tuto.util;

public class ForceInit {

	private <T> Class<T> getClassObject(Class<T> object) {
		try {
			Class.forName(object.getName(), true, object.getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new AssertionError(e); // Can't happen
		}
		return object;
	}

	public <T> void forceInit(Class<T> object) {

		try {
			getClassObject(object).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
