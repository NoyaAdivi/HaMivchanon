package com.example.user.exams;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {
    private ArrayList<Memo> memos;
    private View.OnClickListener mOnItemClickListener;

    public MemoAdapter(ArrayList<Memo> memos) {
        this.memos = memos;
    }

    /**
     *View the reminders
     * @param parent Receiving the reminders
     * @param viewType Viewing the reminders
     * @return View the reminders
     */
    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View memoView = LayoutInflater.from (parent.getContext()).inflate(R.layout.recycle_memo, parent, false);
        return new MemoViewHolder(memoView);
    }

    /**
     * Click the listener
     * @param itemClickListener Click the item
     */
    public void setmOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener= itemClickListener;
    }

    /**
     *Getting the reminders from the database
     * @param holder get id
     * @param position get position
     */
    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        Memo memo= memos.get(position);
        holder.tvDescription.setText(memo.getDescription());
        holder.tvSubject.setText(memo.getSubject());

    }

    @Override
    public int getItemCount() {
        return memos.size();
    }


    public class MemoViewHolder extends RecyclerView.ViewHolder{
        public TextView tvSubject;
        public TextView tvDescription;




        public MemoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubject = (TextView) itemView.findViewById(R.id.tvSubject);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
