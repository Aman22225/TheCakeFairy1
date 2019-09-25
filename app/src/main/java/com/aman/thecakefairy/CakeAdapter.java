package com.aman.thecakefairy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class   CakeAdapter extends RecyclerView.Adapter<CakeViewHolder> {
    private Context mContext;
    private List<Cakedata> myCakeList;

    public CakeAdapter(Context mContext, List<Cakedata> myCakeList) {
        this.mContext = mContext;
        this.myCakeList = myCakeList;
    }

    @Override
    public CakeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item,viewGroup,false);
        return new CakeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CakeViewHolder cakeViewHolder , int i) {
     cakeViewHolder.imageview.setImageResource(myCakeList.get(i).getItemImage());
     cakeViewHolder.mTitle.setText(myCakeList.get(i).getItemName());
     cakeViewHolder.mDescription.setText(myCakeList.get(i).getItemDescription());
     cakeViewHolder.mPrice.setText(myCakeList.get(i).getItemPrice());
     cakeViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(mContext,CakeDetail.class);
             intent.putExtra("Image",myCakeList.get(cakeViewHolder.getAdapterPosition()).getItemImage());
             intent.putExtra("Title",myCakeList.get(cakeViewHolder.getAdapterPosition()).getItemName());
             intent.putExtra("Description",myCakeList.get(cakeViewHolder.getAdapterPosition()).getItemDescription());
             intent.putExtra("Price",myCakeList.get(cakeViewHolder.getAdapterPosition()).getItemPrice());


             mContext.startActivity(intent);
         }
     });

    }

    @Override
    public int getItemCount() {
        return myCakeList.size();
    }
}
class  CakeViewHolder extends RecyclerView.ViewHolder{

    ImageView imageview;
    TextView mTitle,mDescription,mPrice;
    CardView mCardView;
    public CakeViewHolder(@NonNull View itemView) {
        super(itemView);
        imageview=itemView.findViewById(R.id.ivImage);
        mTitle=itemView.findViewById(R.id.tvTitle);
        mDescription=itemView.findViewById(R.id.tvDescription);
        mPrice=itemView.findViewById(R.id.tvPrice);

        mCardView=itemView.findViewById(R.id.myCardView);
    }
}
