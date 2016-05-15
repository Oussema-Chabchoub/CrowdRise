package notification.notification;

public enum Notifications implements Notification {

	INFORMATION("/ressources/info.png", "#2C54AB"),
	NOTICE("/ressources/notice.png", "#8D9695"),
	SUCCESS("/ressources/success.png", "#009961"),
	WARNING("/ressources/warning.png", "#E23E0A"),
	ERROR("/ressources/error.png", "#CC0033");

	private final String urlResource;
	private final String paintHex;

	Notifications(String urlResource, String paintHex) {
		this.urlResource = urlResource;
		this.paintHex = paintHex;
	}

	@Override
	public String getURLResource() {
		return urlResource;
	}

	@Override
	public String getPaintHex() {
		return paintHex;
	}

}
