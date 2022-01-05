package es.android.paises;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
        iv.setImageResource(getImageId(mPais.foto));
        TextView tv = binding.descpcion;
        tv.setText(mPais.detalles);

        return binding.getRoot();
    }

    private int getImageId(String imagePath) {
        String imageName = imagePath.substring(imagePath.lastIndexOf("/")+1, imagePath.lastIndexOf("."));
        return getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());
    }
}