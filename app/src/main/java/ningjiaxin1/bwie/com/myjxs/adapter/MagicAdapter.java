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

public class MagicAdapter extends RecyclerView.Adapter<MagicAdapter.ViewHolder> {
    private Context context;
    private List<Shop.ResultBean.MlssBean.CommodityListBean> list;

    public MagicAdapter(Context context, List<Shop.ResultBean.MlssBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MagicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.magic_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MagicAdapter.ViewHolder viewHolder, int i) {
         viewHolder.magic_title.setText(list.get(i).getCommodityName());
         viewHolder.magic_price.setText(list.get(i).getPrice()+"");
        Glide.with(context).load(list.get(i).getMasterPic()).into(viewHolder.magic_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView magic_image;
        TextView magic_title,magic_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            magic_image = itemView.findViewById(R.id.magic_image);
            magic_price = itemView.findViewById(R.id.magic_price);
            magic_title = itemView.findViewById(R.id.magic_title);
        }
    }
}
