package ningjiaxin1.bwie.com.myjxs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.bean.Shop;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private Context context;
    private List<Shop.ResultBean.RxxpBean.CommodityListBeanXX> list;

    public HotAdapter(Context context, List<Shop.ResultBean.RxxpBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotAdapter.ViewHolder viewHolder, int i) {
       viewHolder.title_hot.setText(list.get(i).getCommodityName());
       viewHolder.price_hot.setText(list.get(i).getPrice()+"");
        Glide.with(context).load(list.get(i).getMasterPic()).into(viewHolder.image_hot);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_hot;
        TextView title_hot,price_hot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_hot = itemView.findViewById(R.id.image_hot);
            title_hot = itemView.findViewById(R.id.title_hot);
            price_hot = itemView.findViewById(R.id.price_hot);
        }
    }
}
