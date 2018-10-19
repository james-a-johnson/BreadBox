package spacepirates.breadbox;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import spacepirates.breadbox.model.DonationItem;

import java.util.List;

public class DonationItemRecyclerAdapter extends RecyclerView.Adapter<DonationItemRecyclerAdapter.DonationViewHolder> {

    List<DonationItem> donations;

    //recycler allows a total of one card to be expanded, and uses this refernce to collapse old expanded card
    private DonationViewHolder expandedViewHolder;

    public static class DonationViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView itemName;
        TextView description;
        TextView price;

        DonationViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.donation_item_card);
            itemName = (TextView)itemView.findViewById(R.id.donation_card_name);
            description = (TextView)itemView.findViewById(R.id.donation_card_description);
            price = (TextView) itemView.findViewById(R.id.donation_card_price);

        }
    }

    //expands description of card view
    private void expandDonationView(DonationViewHolder v) {
        v.description.setVisibility(View.VISIBLE);
    }

    //collapses description in card view
    private void collapseDonationView(DonationViewHolder v) {
        v.description.setVisibility(View.GONE);
    }

    DonationItemRecyclerAdapter(List<DonationItem> donations){
        this.donations = donations;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DonationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donation_item_card, viewGroup, false);
        DonationViewHolder dvh = new DonationViewHolder(view);
        return dvh;
    }

    @Override
    public void onBindViewHolder(final DonationViewHolder donationViewHolder, final int i) {
        donationViewHolder.itemName.setText(donations.get(i).getName());
        donationViewHolder.description.setText(donations.get(i).getDescription());
        donationViewHolder.price.setText("- " + donations.get(i).getPrice());

        //default for card view is collapsed form.
        collapseDonationView(donationViewHolder);

        donationViewHolder.cv.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO navigate to Donation detail view.

                //On expand
                if (expandedViewHolder != null) {
                    //collapse old expanded view if one is expanded
                    collapseDonationView(expandedViewHolder);

                    if (expandedViewHolder.equals(donationViewHolder)) {
                        //If this is view was expanded. Set expanded null. All cards should be collapsed.
                        expandedViewHolder = null;
                    } else {
                        //expand new view and relocate flag
                        expandDonationView(donationViewHolder);
                        expandedViewHolder = donationViewHolder;
                    }
                } else {
                    //a differnt view is expanded
                    //expand this description and mark as expanded
                    expandedViewHolder = donationViewHolder;
                    expandDonationView(donationViewHolder);
                }
            }


        }));
    }

    @Override
    public int getItemCount() {
        return donations.size();
    }
}