package br.edu.controledeproducao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PecaAdapter extends BaseAdapter {

    private Context context;
    private List<Pecas> listaPecas;

    public PecaAdapter(Context context, List<Pecas> listaPecas) {
        this.context = context;
        this.listaPecas = listaPecas;
    }

    private static class PecaHolder{
        public TextView textViewNomeListValor, textViewidListValor, textViewTipoListValor, textViewObraListValor, textViewClienteListValor;

    }
    @Override
    public int getCount() {
        return listaPecas.size();
    }

    @Override
    public Object getItem(int i) {
        return listaPecas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        PecaHolder holder;

        if(view ==null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_listagem, viewGroup, false);

            holder = new PecaHolder();

            holder.textViewNomeListValor = view.findViewById(R.id.textViewNomeListValor);
            holder.textViewTipoListValor = view.findViewById(R.id.textViewTipoListValor);
            holder.textViewidListValor = view.findViewById(R.id.textViewidListValor);
            holder.textViewObraListValor = view.findViewById(R.id.textViewObraListValor);
            holder.textViewClienteListValor = view.findViewById(R.id.textViewClienteListValor);

            view.setTag(holder);

        } else {

            holder = (PecaHolder) view.getTag();

        }

        holder.textViewNomeListValor.setText(listaPecas.get(i).getNome());
        holder.textViewTipoListValor.setText(listaPecas.get(i).getTipo());
        holder.textViewidListValor.setText(String.valueOf(listaPecas.get(i).getId()));
        holder.textViewObraListValor.setText(listaPecas.get(i).getObra().getNomeObra());
        holder.textViewClienteListValor.setText(listaPecas.get(i).getObra().getNomeCliente());

        return view;
    }

}
