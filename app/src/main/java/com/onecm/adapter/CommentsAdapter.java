package com.onecm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.onecm.bean.Comment;
import com.onecm.power.R;
import com.onecm.util.LoaderUtils;

import java.util.List;

/**
 * Created by Kaming on 2015/5/18 0018.
 */
public class CommentsAdapter extends BaseAdapter {
    private List<Comment> mCommentList;
    private LayoutInflater mInflater;
    private ImageLoader loader = ImageLoader.getInstance();

    public CommentsAdapter(Context context, List<Comment> mComments) {
        mCommentList = mComments;
        mInflater = LayoutInflater.from(context);
        loader.init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    public int getCount() {
        return mCommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Comment comment;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.comment_item, null);
            viewHolder.mComContent = (TextView) convertView.findViewById(R.id.comment_content);
            viewHolder.mIcon = (ImageView) convertView.findViewById(R.id.nike_icon);
            viewHolder.mNikeName = (TextView) convertView.findViewById(R.id.comment_nikename);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        comment = mCommentList.get(position);
        viewHolder.mComContent.setText(getSummary(comment.getContent()));
        if (comment.getIconUrl() == "" || comment.getIconUrl().length() == 0) {
            viewHolder.mIcon.setImageResource(R.drawable.ic_mood_grey600_48dp);
        }else{
            loader.displayImage(comment.getIconUrl(), viewHolder.mIcon, LoaderUtils.getDisplayImageOptions());
        }
        viewHolder.mNikeName.setText(comment.getNickName());
        return convertView;
    }

    private String getSummary(String content) {
        String summary;
        if (content.length() > 60) {
            summary = "    " + content.substring(0, 54) + "...";
        } else {
            summary = "    " + content;
        }
        return summary;
    }

    private class ViewHolder {
        ImageView mIcon;
        TextView mNikeName;
        TextView mComContent;
    }


}
