package modules.Config.model;

import java.io.IOException;
import java.util.Properties;

public class Language extends Properties {

	private static final long serialVersionUID = 1L;
	private static Language Instance;
	private String language;

	public Language() {
		switch (Config.getInstance().getLanguage()) {
		case "EN":
			getProperties("english.properties");
			break;
		case "ES":
			getProperties("spanish.properties");
			break;
		case "VAL":
			getProperties("valencian.properties");
			break;
		default:
			getProperties("english.properties");
		}
	}

	public static Language getInstance() {
		if (Instance == null) {
			Instance = new Language();
		}
		return Instance;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage() {

		switch (Config.getInstance().getLanguage()) {
		case "EN":
			getProperties("english.properties");
			break;
		case "ES":
			getProperties("spanish.properties");
			break;
		case "VAL":
			getProperties("valencian.properties");
			break;
		default:
			getProperties("english.properties");
		}
	}

	public void getProperties(String language) {
		try {
			this.load(getClass().getResourceAsStream(language));
		} catch (IOException ex) {
		}
	}

}
