package com.nineinfosys.financialcalculator.Amortization;

/**
 * Created by Divya on 28-02-2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.nineinfosys.financialcalculator.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Divya on 18-01-2017.
 */

public class AmortizationAdapter extends RecyclerView.Adapter<AmortizationAdapter.ListViewHolder> {

    Context context;
    LayoutInflater inflater;
    private List<AmortizationCalculation.AmortizationResults> results;
    AmortizationCalculation.AmortizationResults result;



    public AmortizationAdapter(Context context2, List<AmortizationCalculation.AmortizationResults> results) {
        this.context = context2;
        this.results = results;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_amortization_item, parent, false);

        return new ListViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        int i ;

        int length = results.size();
        for( i = 1; i <length; i++) {
            result  =  results.get(position);

            //setting value to textview
            holder.textViewID.setText(new DecimalFormat("##.##").format(result.getId()));
            holder.textViewAmount.setText(new DecimalFormat("##.##").format( result.getEmi()));
            holder.textViewInterest.setText(new DecimalFormat("##.##").format(result.getIntrest()));
            holder.textViewPrincipal.setText(new DecimalFormat("##.##").format(result.getPrincipal()));
            holder.textViewRemainingbalance.setText(new DecimalFormat("##.##").format(result.getBalance()));
        }
        animate(holder);

    }


    @Override
    public int getItemCount() {
        return results.size();
    }


    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAmount,textViewInterest,textViewPrincipal,textViewRemainingbalance,textViewID;
        ImageView iv_delete;

        public ListViewHolder(View itemView) {
            super(itemView);
            textViewID = (TextView) itemView.findViewById(R.id.AmortizationId);
            textViewAmount = (TextView) itemView.findViewById(R.id.AmortizationAmount);
            textViewInterest = (TextView) itemView.findViewById(R.id.AmortizationInterest);
            textViewPrincipal = (TextView) itemView.findViewById(R.id.AmortizationPrincipal);
            textViewRemainingbalance = (TextView) itemView.findViewById(R.id.AmortizationRemainingBalance);
        }
    }
    //animation method
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

}
