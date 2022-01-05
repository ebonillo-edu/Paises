package es.android.paises;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.android.paises.placeholder.PlaceholderContent;


/**
 * A fragment representing a list of Items.
 */
public class PaisesFragment extends Fragment {
    private int mColumnCount = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paises_list, container, false);

        SharedPreferences prefs = getDefaultSharedPreferences(getContext());
        
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            String tipoVisualizacion = prefs.getString("tipo_visualizacion", "listado");
            if (tipoVisualizacion.equals("listado")) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            }
            boolean useDivider = prefs.getBoolean("linea", false);
            if(useDivider) {
                DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        LinearLayout.VERTICAL);
                recyclerView.addItemDecoration(verticalDecoration);
            }

            PlaceholderContent placeholderContent = new PlaceholderContent(getResources(), getContext().getPackageName());
            recyclerView.setAdapter(new PaisRecyclerViewAdapter(PlaceholderContent.PAISES));
        }
        return view;
    }
}