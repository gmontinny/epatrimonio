package br.gov.mt.saude.cuiaba.e_patrimonio.adapter;

import android.content.Context;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Filter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.gov.mt.saude.cuiaba.e_patrimonio.R;


public class SpinnerArrayAdapter<T> extends ArrayAdapter<SpinnerArrayAdapter<T>.ItemProxy> {

    private NoFilter noFilter;

    public SpinnerArrayAdapter(Context context, List<T> objects) {
        super(context, R.layout.support_simple_spinner_dropdown_item);
        setNotifyOnChange(false);
        clear();
        addAll(wrapItems(objects));
        notifyDataSetChanged();
    }

    public SpinnerArrayAdapter(Context context, T[] objects) {
        this(context, Arrays.asList(objects));
    }

    /**
     * <p>Por padrão, o ArrayAdapter utiliza o <code>toString()</code> para exibição dos items, e em alguns casos, como quando utilizado o framework Realm Database, não é possível sobrescrever o <code>toString()</code> das classes de modelo.
     * Sendo assim, foi criada a solução com Proxy (classe para envolver o objeto), o que permite a customização do <code>toString()</code> dos proxys. </p>
     * <p>Este método faz o trabalho de envolver as classes de modelo originais em Proxy.</p>
     *
     * @param objects
     * @return
     */
    private List<ItemProxy> wrapItems(List<T> objects) {
        List<ItemProxy> proxies = new ArrayList<>(objects.size());
        for (T item : objects) {
            ItemProxy proxy = new ItemProxy(item);
            proxies.add(proxy);
        }
        return proxies;
    }

    /**
     * <p>Converte o item em <code>String</code>.</p>
     * <p>Sobreescreva este método para customizar a visualização do item.</p>
     *
     * @param item
     * @return
     */
    public String itemToString(T item) {
        return item.toString();
    }

    /**
     * <p>Converte a <code>String</code> em objeto.</p>
     * <p>Este método pode ser utilizado para detectar o item que está selecionado no Spinner.</p>
     * <p>Exemplo:</p>
     * <code>
     * <pre>
     *
     * MaterialBetterSpinner spPessoa;
     * SpinnerArrayAdapter<Pessoa> adapterPessoa;
     * ....
     * Pessoa pSelecionada = adapterPessoa.stringToItem(spPessoa.getText())
     *
     * </pre>
     * </code>
     *
     * @param toString Texto selecionado no Spinner.
     * @return Objeto Pessoa convertido a partir do texto selecionado.
     */
    public T stringToItem(String toString) {
        for (int i = 0; i < getCount(); i++) {
            T item = getItem(i).object;
            if (itemToString(item).equals(toString)) {
                return item;
            }
        }
        return null;
    }

    /**
     * <p>Converte a <code>String</code> em objeto.</p>
     * <p>Este método pode ser utilizado para detectar o item que está selecionado no Spinner.</p>
     * <p>Exemplo:</p>
     * <code>
     * <pre>
     *
     * MaterialBetterSpinner spPessoa;
     * SpinnerArrayAdapter<Pessoa> adapterPessoa;
     * ....
     * Pessoa pSelecionada = adapterPessoa.stringToItem(spPessoa.getText())
     *
     * </pre>
     * </code>
     *
     * @param toString Texto selecionado no Spinner.
     * @return Objeto convertido a partir do texto selecionado.
     */
    public T stringToItem(CharSequence toString) {
        return stringToItem(toString.toString());
    }

    /**
     * Classe que implementa o padrão de projeto Proxy para o SpinnerArrayAdapter genérico.
     */
    public class ItemProxy {
        final T object;

        protected ItemProxy(T object) {
            this.object = object;
        }

        @Override
        public String toString() {
            return itemToString(object);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SpinnerArrayAdapter.ItemProxy)) return false;

            ItemProxy itemProxy = (ItemProxy) o;

            return !(object != null ? !object.equals(itemProxy.object) : itemProxy.object != null);
        }

        @Override
        public int hashCode() {
            return object != null ? object.hashCode() : 0;
        }
    }

    /**
     * Override ArrayAdapter.getFilter() to return our own filtering.
     */
    @Override
    public Filter getFilter() {
        if (noFilter == null) {
            noFilter = new NoFilter();
        }
        return noFilter;
    }

    /**
     * Class which does not perform any filtering.
     * Filtering is already done by the web service when asking for the list,
     * so there is no need to do any more as well.
     * This way, ArrayAdapter.mOriginalValues is not used when calling e.g.
     * ArrayAdapter.add(), but instead ArrayAdapter.mObjects is updated directly
     * and methods like getCount() return the expected result.
     */
    private class NoFilter extends Filter {
        protected FilterResults performFiltering(CharSequence prefix) {
            return new FilterResults();
        }

        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // Do nothing
        }
    }
}
