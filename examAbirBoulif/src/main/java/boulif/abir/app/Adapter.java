package boulif.abir.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.StudentViewHolder> {
    private List<StudentList> student;
    private Context context ;

    public Adapter(List<StudentList> student, Context context) {
        this.student = student;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.display(student.get(position));

    }

    @Override
    public int getItemCount() {
        return student.size();
    }


    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView mail;
        private TextView number;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            mail=itemView.findViewById(R.id.email) ;
            number = itemView.findViewById(R.id.number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        StudentList ClickDataItem = student.get(pos) ;
                        Intent intent = new Intent(context, DetailsActivity.class) ;
                        intent.putExtra("name" , student.get(pos).getName().getFirst()+" "+student.get(pos).getName().getLast()) ;
                        intent.putExtra("image" , student.get(pos).getPicture()) ;
                        intent.putExtra("number" , student.get(pos).getPhone()) ;
                        intent.putExtra("linkedin" , student.get(pos).getLinkedin()) ;


                        intent.putExtra("address" ,
                                student.get(pos).getAddress().getStreet()+"\t"+
                                        student.get(pos).getAddress().getZip()+student.get(pos).getAddress().getCity()+"\t"+
                                        student.get(pos).getAddress().getState()) ;


                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(),ClickDataItem.getName().getFirst(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }

        void display(StudentList student){
            name.setText(student.getName().getFirst()+" "+student.getName().getLast());
            mail.setText(student.getEmail());
            number.setText(student.getPhone());
            Glide.with(context).load(student.getPicture()).into(image) ;


        }


    }
}
