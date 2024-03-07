package com.qooke.employeeapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.qooke.employeeapp.MainActivity;
import com.qooke.employeeapp.R;
import com.qooke.employeeapp.UpdateActivity;
import com.qooke.employeeapp.model.Employee;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{

    Context context;
    ArrayList<Employee> employeeArrayList;

    public EmployeeAdapter(Context context, ArrayList<Employee> employeeArrayList) {
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_row, parent, false);
        return new EmployeeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employeeArrayList.get(position);
        holder.txtName.setText(employee.name);
        holder.txtAge.setText("나이 :"+ employee.age +"세");

        // 숫자에 콤마 넣기(Decimal format) -> 콤마가 들어가서 문자열이 됨
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String strSalary = decimalFormat.format(employee.salary);

        holder.txtSalary.setText("연봉 : $"+strSalary);

    }

    @Override
    public int getItemCount() {
        return employeeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtAge;
        TextView txtSalary;
        ImageView imgDelete;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // updateActivity 실행
                    int index = getAdapterPosition();
                    Employee employee = employeeArrayList.get(index);
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("index", index);
                    intent.putExtra("employee", employee);

                    ((MainActivity)context).launcher.launch(intent);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 알러트 다이얼로그 띄운다.
                    showAlertDialog();
                }
            });

        }

        private void showAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setTitle("삭제");
            builder.setMessage("삭제하시겠습니까?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int index = getAdapterPosition();
                    employeeArrayList.remove(index);
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("NO", null);
            builder.show();
        }
    }

}
