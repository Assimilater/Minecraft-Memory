package Project1.Debug;

public class Bug {
	private String Type, Category, Key, Value;
	
	public Bug(String type) {
		Type = type;
	}
	public Bug(String type, String category) {
		Type = type;
		Category = category;
	}
	public Bug(String type, String category, String key) {
		Type = type;
		Category = category;
		Key = key;
	}
	public Bug(String type, String category, String key, String value) {
		Type = type;
		Category = category;
		Key = key;
		Value = value;
	}
	public boolean equals(Bug rho) {
		return
			(
				(this.Type == null && rho.Type == null) ||
				(this.Type != null && rho.Type != null && this.Type.equals(rho.Type))
			) &&
			(
				(this.Category == null && rho.Category == null) ||
				(this.Category != null && rho.Category != null && this.Category.equals(rho.Category))
			) &&
			(
				(this.Key == null && rho.Key == null) ||
				(this.Key != null && rho.Key != null && this.Key.equals(rho.Key))
			) &&
			(
				(this.Value == null && rho.Value == null) ||
				(this.Value != null && rho.Value != null && this.Value.equals(rho.Value))
			);
	}
}
