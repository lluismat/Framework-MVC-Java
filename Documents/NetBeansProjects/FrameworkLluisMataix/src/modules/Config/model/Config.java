package modules.Config.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;


import modules.users.admin.model.classes.admin;
import modules.users.client.model.classes.client;
import modules.users.userreg.model.classes.registered_user;
import modules.users.users.singleton;
import utils.Themes;
import utils.menus;

@XStreamAlias("Config")
public class Config implements Serializable {

	@XStreamAlias("Decimals")
	private int decimals;
	@XStreamAlias("Currency")
	private String currency;
	@XStreamAlias("Format_Date")
	private String formatDate;
	@XStreamAlias("Theme")
	private String theme;
	@XStreamAlias("Language")
	private String language;
	@XStreamAlias("Files")
	private String files;
	@XStreamAlias("Instance")
	private static Config Instance;

	protected Config() {
		decimals = 2;
		currency = "€";
		formatDate = "dd/mm/yyyy";
		theme = "Windows";
		language = "ES";
		files = "JSON";

	}

	public static Config getInstance() {
		if (Instance == null) {
			Instance = new Config();
			files_Config.OpenConfig();
			Themes.themes();

			singleton.useradmin = new ArrayList<admin>();
			singleton.userclient = new ArrayList<client>();
			singleton.userreg = new ArrayList<registered_user>();
                        
		}
		return Instance;

	}

	public int getDecimals() {
		return decimals;
	}

	public void setDecimals(int decimals) {
		this.decimals = decimals;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String toString() {
		return "Config [decimals=" + decimals + ", currency=" + currency + ", formatDate=" + formatDate + ", theme="
				+ theme + ", language=" + language + ", files=" + files + "]";
	}

}
