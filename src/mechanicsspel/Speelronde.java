package mechanicsspel;
import java.util.ArrayList;

public class Speelronde {

	private ArrayList<Wedstrijd> wedstrijden;
	private int speelronde;
	private ArrayList<Uitslag> uitslagen;

	public Speelronde(int a) {
		wedstrijden = new ArrayList<Wedstrijd>();
		this.speelronde = a;
		uitslagen = new ArrayList<Uitslag>();
	}

	public Wedstrijd getWedstrijd(int i) {
		return wedstrijden.get(i);
	}

	public void addWedstrijd(Wedstrijd a) {
		wedstrijden.add(a);
	}

	public void addUitslag(Uitslag u) {
		uitslagen.add(u);
	}

	public Uitslag getUitslag(int i) {
		if (i < uitslagen.size()) {
			return uitslagen.get(i);
		} else {
			return null;
		}
	}

	public String toString() {
		String a = "";
		for (int i = 0; i < this.length(); i++) {
			a = a + "Speelronde " + speelronde + ": "
					+ (wedstrijden.get(i)).toString() + "\n";
		}

		return a;
	}

	public String toXML() {
		String a = "<speelronde id=\"" + speelronde + "\">";
		for (int i = 0; i < wedstrijden.size(); i++) {
			a = a + "\r\n" + wedstrijden.get(i).toXML();
		}
		a = a + "\r\n</speelronde>";
		return a;
	}

	public int length() {
		return wedstrijden.size();
	}

	public boolean equals(Object obj){
		boolean res = false;
		if(obj instanceof Speelronde){
			Speelronde that = (Speelronde)obj;
			res = true;
			if(!(this.wedstrijden.size()==that.wedstrijden.size()
					&& this.uitslagen.size()==that.uitslagen.size()
					&& this.speelronde==that.speelronde)){
				return false;
			}
			for(int i=0; i<this.wedstrijden.size(); i++){
				if(!this.getWedstrijd(i).equals(that.getWedstrijd(i))){
					return false;
				}
			}
			for(int i=0; i<this.uitslagen.size(); i++){
				if(!this.uitslagen.get(i).equals(that.uitslagen.get(i))){
					return false;
				}
			}
		}
		return res;
	}
}
