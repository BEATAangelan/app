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
import ningjiaxin1.bwie.com.myjxs.bean.Hot;
import ningjiaxin1.bwie.com.myjxs.bean.Magic;

public class MagicxAdapter extends RecyclerView.Adapter<MagicxAdapter.ViewHolder> {
    private Context context;
    private List<Magic.ResultBean> list;

    public MagicxAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void setList(List<Magic.ResultBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addList(List<Magic.ResultBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MagicxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_hot, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MagicxAdapter.ViewHolder viewHolder, int i) {
        viewHolder.hot_title.setText(list.get(i).getCommodityName());
        viewHolder.hot_price.setText(list.get(i).getPrice()+"");
        Glide.with(context).load(list.get(i).getMasterPic()).into(viewHolder.hot_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hot_image;
        TextView hot_title,hot_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hot_image = itemView.findViewById(R.id.hot_image);
            hot_title = itemView.findViewById(R.id.hot_title);
            hot_price = itemView.findViewById(R.id.hot_price);
        }
    }
}
