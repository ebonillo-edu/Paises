package es.android.paises;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.android.paises.databinding.FragmentPaisCardBinding;
import es.android.paises.placeholder.PlaceholderContent.Pais;
import es.android.paises.databinding.FragmentPaisBinding;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Pais}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PaisRecyclerViewAdapter extends RecyclerView.Adapter<PaisRecyclerViewAdapter.ViewHolder> {

    private final List<Pais> mValues;
    private final List<Pais> mOriginalValues;

    public PaisRecyclerViewAdapter(List<Pais> items) {
        mOriginalValues = new LinkedList<>(items);
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPaisCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        InputStream is = getClass().getResourceAsStream("/" + mValues.get(position).bandera);
        holder.mImageView.setImageDrawable(Drawable.createFromStream(is, ""));
        holder.mContentView.setText(mValues.get(position).nombre);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void filter(String text) {
        mValues.clear();
        if(text.isEmpty()){
            mValues.addAll(mOriginalValues);
        } else{
            text = text.toLowerCase();
            for(Pais pais: mOriginalValues){
                if(pais.nombre.toLowerCase().contains(text)){
                    mValues.add(pais);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mImageView;
        public final TextView mContentView;
        public Pais mItem;

        public ViewHolder(FragmentPaisCardBinding binding) {
            super(binding.getRoot());
            mImageView = binding.imageView;
            mContentView = binding.content;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            Bundle args = new Bundle();
            args.putParcelable("pais", mItem);
            args.putString("title", mItem.nombre);

            Navigation.findNavController(v)
                    .navigate(R.id.action_paisesFragment_to_detallePaisFragment, args);
        }
    }
}