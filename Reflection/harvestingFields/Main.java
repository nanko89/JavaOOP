package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		Field[] declaredFields = RichSoilLand.class.getDeclaredFields();

		while (!input.equals("HARVEST")){
			switch (input){
				case "private":
					print("private", declaredFields);
					break;
				case "protected":
					print("protected", declaredFields);
					break;
				case "public":
					print("public", declaredFields);
					break;
				case "all":
					for (Field field : declaredFields) {
						System.out.printf("%s %s %s%n",Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());
					}
					break;
			}

			input = scanner.nextLine();

		}

	}

	private static void print(String name, Field[] declaredFields) {
		for (Field field : declaredFields) {
		String modifier = Modifier.toString(field.getModifiers());
		if (modifier.equals(name)){
			System.out.printf("%s %s %s%n", name, field.getType().getSimpleName(), field.getName());
		}

		}
	}


}
