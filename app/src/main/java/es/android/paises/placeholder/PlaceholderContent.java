package es.android.paises.placeholder;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import es.android.paises.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<Pais> PAISES = new ArrayList<Pais>();

    public PlaceholderContent(Resources resources, String packageName) {
        if(PAISES.size() == 0) {
            String[] countries = resources.getStringArray(R.array.paises);
            for (int i = 0; i < countries.length; i++) {
                PAISES.add(getItem(resources, packageName, countries[i], i));
            }
        }
    }

    private static Pais getItem(Resources resources, String packageName, String country, int id) {
        String [] countryDetail = resources
                .getStringArray(resources.getIdentifier(country, "array", packageName));
        String [] coordenadas = countryDetail[4].split(",");
        return new Pais(id+"", countryDetail[0], countryDetail[1], countryDetail[2], countryDetail[3], Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1]));
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class Pais implements Parcelable {
        public final String id;
        public final String nombre;
        public final String foto;
        public final String detalles;
        public final String bandera;
        public final double latitud;
        public final double longitud;


        public Pais(String id, String nombre, String foto, String detalles, String bandera, double latitud, double longitud) {
            this.id = id;
            this.nombre = nombre;
            this.foto = foto;
            this.detalles = detalles;
            this.bandera = bandera;
            this.latitud = latitud;
            this.longitud = longitud;
        }

        protected Pais(Parcel in) {
            id = in.readString();
            nombre = in.readString();
            foto = in.readString();
            detalles = in.readString();
            bandera = in.readString();
            latitud = in.readDouble();
            longitud = in.readDouble();
        }

        public static final Creator<Pais> CREATOR = new Creator<Pais>() {
            @Override
            public Pais createFromParcel(Parcel in) {
                return new Pais(in);
            }

            @Override
            public Pais[] newArray(int size) {
                return new Pais[size];
            }
        };

        @Override
        public String toString() {
            return "Pais{" + nombre + '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(nombre);
            dest.writeString(foto);
            dest.writeString(detalles);
            dest.writeString(bandera);
            dest.writeDouble(latitud);
            dest.writeDouble(longitud);
        }
    }
}