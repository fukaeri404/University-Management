package modules.database;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class NumberBean {
	private DoubleProperty number;

	public NumberBean() {
		
	}

	public double getNumber() {
		return number.get();
	}

	public void setNumber(double number) {
		this.numberProperty().set(number);
	}

	public DoubleProperty numberProperty() {
		if (this.number == null)
			this.number = new SimpleDoubleProperty(0.00);
		return this.number;

	}
}
