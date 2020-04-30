package example.abhiandriod.tablelayoutexample.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import example.abhiandriod.tablelayoutexample.ADMListar;
import example.abhiandriod.tablelayoutexample.Model.Formulario;
import example.abhiandriod.tablelayoutexample.R;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.MyViewHolder> implements Filterable {
    private List<Formulario> formularioList;
    private List<Formulario> formulariolistFiltered;
    private FormularioAdapterListener listener;
    private Formulario deletedItem;

    public FormAdapter(List<Formulario> formularioLista, ADMListar admListar) {
        this.formularioList = formularioLista;
        this.listener = listener;

        this.formulariolistFiltered= formularioList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;


        public MyViewHolder(View view) {
            super(view);
            titulo1 = view.findViewById(R.id.titleFirstLbl);
            titulo2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(formulariolistFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    @NonNull
    @Override
    public FormAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FormAdapter.MyViewHolder holder, int position) {

        final Formulario form = formulariolistFiltered.get(position);
        holder.titulo1.setText(form.getId());
        holder.titulo2.setText(form.getNombre());
        holder.description.setText(form.getApellido());
    }

    @Override
    public int getItemCount() {
        return formulariolistFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = formulariolistFiltered.remove(position);
        Iterator<Formulario> iter = formularioList.iterator();
        while (iter.hasNext()) {
            Formulario aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (formulariolistFiltered.size() == formularioList.size()) {
            formulariolistFiltered.add(position, deletedItem);
        } else {
            formulariolistFiltered.add(position, deletedItem);
            formularioList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }
    public Formulario getSwipedItem(int index) {
        if (this.formularioList.size() == this.formulariolistFiltered.size()) { //not filtered yet
            return formularioList.get(index);
        } else {
            return formulariolistFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (formularioList.size() == formulariolistFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(formularioList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(formularioList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(formulariolistFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(formulariolistFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    formulariolistFiltered = formularioList;
                } else {
                    List<Formulario> filteredList = new ArrayList<>();
                    for (Formulario row : formularioList) {
                        // filter use two parameters
                        if (String.valueOf(row.getId()).toLowerCase().contains(charString.toLowerCase()) || row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    formulariolistFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = formulariolistFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                formulariolistFiltered = (ArrayList<Formulario>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface  FormularioAdapterListener{
        void onContactSelected(Formulario form);
    }
}
