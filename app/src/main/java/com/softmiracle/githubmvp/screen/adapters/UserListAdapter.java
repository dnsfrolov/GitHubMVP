package com.softmiracle.githubmvp.screen.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.SempraApplication;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dnsfrolov on 09.05.2017.
 */

public class UserListAdapter extends RecyclerView.Adapter {

    private List<User> mList;
    private OnItemClickListener<User> mListener;

    public UserListAdapter(OnItemClickListener<User> listener) {
        this.mList = new ArrayList<>();
        this.mListener = listener;
    }

    public void setData(List<User> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(list);
    }

    public void restoreData() {
        mList = null;
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_list, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final UserHolder userHolder = (UserHolder) holder;
        userHolder.initData(holder.getAdapterPosition());

        userHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(mList.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class UserHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview_user_list)
        CardView mCardView;

        @BindView(R.id.avatar_user_list)
        CircleImageView mAvatar;

        @BindView(R.id.tv_username_user_list)
        TextView mUsername;

        UserHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void initData(int adapterPosition) {
            final User user = mList.get(adapterPosition);
            Glide.with(SempraApplication.getInstance()).load(user.getAvatar()).into(mAvatar);
            mUsername.setText(user.getLogin());
        }
    }
}
