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

import java.util.List;

import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.bean.Shop;

public class QualityAdapter extends RecyclerView.Adapter<QualityAdapter.ViewHolder> {
    private Context context;
    private List<Shop.ResultBean.PzshBean.CommodityListBeanX> list;

    public QualityAdapter(Context context, List<Shop.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QualityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.quality_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QualityAdapter.ViewHolder viewHolder, int i) {
        viewHolder.quality_title.setText(list.get(i).getCommodityName());
        viewHolder.quality_price.setText(list.get(i).getPrice()+"");
        Glide.with(context).load(list.get(i).getMasterPic()).into(viewHolder.quality_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView quality_image;
        TextView quality_title,quality_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quality_image = itemView.findViewById(R.id.quality_image);
            quality_title = itemView.findViewById(R.id.quality_title);
            quality_price = itemView.findViewById(R.id.quality_price);
        }
    }
}
