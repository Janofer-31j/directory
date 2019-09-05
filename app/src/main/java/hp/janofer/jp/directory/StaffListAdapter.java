package hp.janofer.jp.directory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.MyViewHolder> {
    private List<Staff> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvMail, tvDept;

        public MyViewHolder(View itemView) {
            super(itemView);
            Log.d("Recycler adapter : ", "Connecting StaffView elements.");
            tvName=itemView.findViewById(R.id.tvName);
            tvMail = itemView.findViewById(R.id.tvMail);
            tvDept = itemView.findViewById(R.id.tvDept);
        }
    }

    public StaffListAdapter(List<Staff> mDataset) {
        Log.d("Recycler adapter ", "adapter constructor.");
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.staff_cardview, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffListAdapter.MyViewHolder viewHolder, int position) {
        Log.d("Recycler adapter : ", "Setting values to StaffView.");
        Staff staff=mDataset.get(position);
        viewHolder.tvName.setText(staff.getName());
        viewHolder.tvMail.setText(staff.getMail());
        viewHolder.tvDept.setText(staff.getDepartment());
    }

    @Override
    public int getItemCount() {
        int size=0;
        try {
            if(mDataset.size()==0){/*Do nothing*/}
            else
                size=mDataset.size();
        }catch (Exception e){
            Log.d("BinListAdapter",e.toString());
        }
        return size;
    }
}
