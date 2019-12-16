package constants;

/** Messages sent from server to the client. */
public class Messages {

		public static final String NO_EMPTY = "Podane poje jest zajęte.";
		public static final String SUICIDE = "Podany ruch jest samobójczy.";
		public static final String KO = "Ruch ko nie jest dozwolony.";
		public static final String NO_TURN = "Poczekaj na swoją kolej.";
		public static final String COLOR_WHITE = "Wylosowales kolor bialy.";
		public static final String COLOR_BLACK = "Wylosowales kolor czarny.";
		
		public static final String SERVER = "[SERVER] ";
		public static final String CLIENT = "[CLIENT] ";
		public static final String THIS = "[THIS] ";
		public static final String NO_CLIENT = "Przeciwnik sie rozlaczyl";
		private Messages() {}
}
