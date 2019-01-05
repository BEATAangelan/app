package ningjiaxin1.bwie.com.myjxs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.bean.Circle;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder>{
    private Context context;
    private List<Circle.ResultBean> list;

    public CircleAdapter(Context context, List<Circle.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Circle.ResultBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addList(List<Circle.ResultBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CircleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.circle_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CircleAdapter.ViewHolder viewHolder, int i) {
        viewHolder.text_time.setText(list.get(i).getCreateTime()+"");
        viewHolder.circle_title.setText(list.get(i).getNickName());
        viewHolder.text_head.setText(list.get(i).getHeadPic());
        Glide.with(context).load(list.get(i).getImage()).into(viewHolder.image_head);
        Glide.with(context).load(list.get(i).getContent()).into(viewHolder.image_shop);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_head,image_shop;
        TextView text_time,circle_title,text_head;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_head = itemView.findViewById(R.id.image_head);
            image_shop = itemView.findViewById(R.id.image_shop);
            text_time = itemView.findViewById(R.id.text_time);
            circle_title = itemView.findViewById(R.id.circle_title);
            text_head = itemView.findViewById(R.id.text_head);
        }
    }
}
