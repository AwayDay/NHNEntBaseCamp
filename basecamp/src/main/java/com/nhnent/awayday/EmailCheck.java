package com.nhnent.awayday;

@FunctionalInterface
public interface EmailCheck {
	public boolean isEmail(String email);
}
