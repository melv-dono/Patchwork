package View;

import java.util.Objects;

import Model.Label;

public record ViewLabel(Label label) {

	public ViewLabel {
		Objects.requireNonNull(label);
	}
	
	@Override
	public String toString() {
		return "(" + label.button() + "buttons, " 
				+ label.price() + " price, " 
				+ label.movement() + " movement)"; 
	}
}
