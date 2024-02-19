import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Locale;

public class Test {

	public static void main(String[] args) throws Exception {
		File file = new File("./src/main/resources/data/celestial_artifacts/recipes/");
		File out = new File("./src/main/java/com/xiaoyue/celestial_artifacts/data/gen.txt");
		var ps = new PrintStream(out);
		for (var sub : file.listFiles()) {
			if (sub.isDirectory()) {
				for (var json : sub.listFiles()) {
					if (json.isFile() && json.getName().endsWith(".json")) {
						JsonObject obj = new JsonParser().parse(new JsonReader(new FileReader(json.getPath()))).getAsJsonObject();
						if (obj.get("type").getAsString().equals("minecraft:crafting_shaped")) {
							if (obj.size() == 4) {
								String ans = parseShaped(obj);
								ps.println(ans);
							} else System.out.println("File " + json.getName() + " is special shaped recipe");
						} else {
							System.out.println("File " + json.getName() + " is " + obj.get("type"));
						}
					}
				}
			}
		}
	}

	private static String parseShaped(JsonObject obj) {
		String template = "unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, $1, 1)::unlockedBy, $2)$3.save(pvd);";
		String pattern = ".pattern(\"$1\")";
		String ingredient = ".define('$1', $2)";
		StringBuilder content = new StringBuilder();
		for (var e : obj.getAsJsonArray("pattern")) {
			content.append(pattern.replace("$1", e.getAsString()));
		}
		String first = null;
		for (var e : obj.getAsJsonObject("key").entrySet()) {
			var ing = parseIngredient(e.getValue());
			if (first == null) first = ing;
			content.append(ingredient.replace("$1", e.getKey()).replace("$2", ing));
		}
		assert first != null;
		return template.replace("$1", parseResult(obj.get("result")))
				.replace("$2", first)
				.replace("$3", content.toString());
	}

	private static String parseIngredient(JsonElement item) {
		if (item.isJsonObject()) {
			if (item.getAsJsonObject().entrySet().size() == 1) {
				if (item.getAsJsonObject().has("item")) {
					var val = item.getAsJsonObject().get("item");
					return parseItem(val);
				}
			}
		}
		System.out.println("Special ingredient" + item);
		return "";
	}

	private static String parseResult(JsonElement item) {
		if (item.isJsonObject()) {
			if (item.getAsJsonObject().entrySet().size() == 1) {
				if (item.getAsJsonObject().has("item")) {
					var val = item.getAsJsonObject().get("item");
					return parseItem(val);
				}
			}
		}
		System.out.println("Special result" + item);
		return "";
	}

	private static String parseItem(JsonElement val) {
		if (val.isJsonPrimitive()) {
			String str = val.getAsString();
			String[] arr = str.split(":");
			if (arr.length == 2) {
				switch (arr[0]) {
					case "minecraft":
						return "Items." + arr[1].toUpperCase(Locale.ROOT);
					case "celestial_core":
						return "CCItems." + arr[1].toUpperCase(Locale.ROOT) + ".get()";
					case "celestial_artifacts":
						return "CAItems." + arr[1].toUpperCase(Locale.ROOT) + ".get()";
				}
			}
		}
		System.out.println("Special item" + val);
		return "";
	}

}
