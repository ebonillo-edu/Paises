package es.android.paises;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.android.paises.databinding.FragmentDetallePaisBinding;
import es.android.paises.placeholder.PlaceholderContent;

public class DetallePaisFragment extends Fragment {

    private FragmentDetallePaisBinding binding;
    private PlaceholderContent.Pais mPais;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPais = getArguments().getParcelable("pais");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentDetallePaisBinding.inflate(inflater, container, false);

        ImageView iv = binding.foto;
        iv.setImageDrawable(Drawable.createFromStream(getClass().getResourceAsStream("/" + mPais.foto), ""));
        iv.setOnLongClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("pais", mPais.nombre);
            args.putDouble("latitud", mPais.latitud);
            args.putDouble("longitud", mPais.longitud);

            Navigation.findNavController(v)
                    .navigate(R.id.action_detallePaisFragment_to_mapsFragment, args);
            return true;
        });

        TextView tv = binding.descpcion;
        tv.setText(mPais.detalles);

        return binding.getRoot();
    }
}