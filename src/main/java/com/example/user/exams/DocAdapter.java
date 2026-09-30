package com.example.user.exams;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.DocViewHolder>  {
    private ArrayList<Doc> docs;
    private View.OnClickListener mOnItemClickListener;

    public DocAdapter(ArrayList<Doc> docs) {
        this.docs = docs;
    }

    /**
     *View the documents
     * @param parent Receiving the documents
     * @param viewType Viewing the documents
     * @return View the documents
     */
    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View docView = LayoutInflater.from (parent.getContext()).inflate(R.layout.recycle_doc, parent, false);
        return new DocAdapter.DocViewHolder(docView);
    }

    /**
     * Click the listener
     * @param itemClickListener Click the item
     */
    public void setmOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener= itemClickListener;
    }

    /**
     *Getting the documents from the database
     * @param holder get id
     * @param position get position
     */
    @Override
    public void onBindViewHolder(@NonNull DocAdapter.DocViewHolder holder, int position) {
        Doc doc= docs.get(position);
        holder.tvDoc.setText(doc.getIdDoc());
        holder.ivDoc.setImageResource(holder.tvDoc.getResources().getIdentifier("docs2","drawable",holder.tvDoc.getContext().getPackageName()));

    }

    @Override
    public int getItemCount() {
        return docs.size();
    }

    public class DocViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDoc;
        public ImageView ivDoc;

        public DocViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDoc = (TextView) itemView.findViewById(R.id.tvDoc);
            ivDoc = (ImageView) itemView.findViewById(R.id.ivDoc);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}