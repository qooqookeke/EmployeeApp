package com.qooke.employeeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qooke.employeeapp.model.Employee;

public class UpdateActivity extends AppCompatActivity {

    TextView txtName;
    EditText editSalary;
    EditText editAge;
    Button btnSave;

    Employee employee;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setTitle("직원 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtName = findViewById(R.id.txtName);
        editSalary = findViewById(R.id.editSalary);
        editAge = findViewById(R.id.editAge);
        btnSave = findViewById(R.id.btnSave);

        // 인덱스를 받아온다.
        index = getIntent().getIntExtra("index", 0);

        // 데이터 받아오기, 선택한 사람의 정보를 화면에 먼저 보여준다.
        employee = (Employee) getIntent().getSerializableExtra("employee");

        // 화면에 보여주기
        txtName.setText(employee.name);
        editSalary. setText(""+employee.salary);
        editAge.setText(""+employee.age);


        // 수정 버튼 누르면 수정된 데이터를 메인 액티비티에 돌려준다.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSalary = editSalary.getText().toString().trim();
                String strAge = editAge.getText().toString().trim();

                if (strSalary.isEmpty() || strAge.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "필수 항목 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(strAge);
                int salary = Integer.parseInt(strSalary);

                // 데이터 수정
                employee.age = age;
                employee.salary = salary;

                // 데이터 보내주기(돌려주기)
                Intent intent = new Intent();
                intent.putExtra("index", index);
                intent.putExtra("employee", employee);
                setResult(200, intent);

                finish();
            }
        });
    }

    // 화살표 처리(뒤로가기)
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update, menu);
        return true;
    }

    // 버튼 눌렀을 때
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuSave) {
            String strSalary = editSalary.getText().toString().trim();
            String strAge = editAge.getText().toString().trim();

            if (strSalary.isEmpty() || strAge.isEmpty()) {
                Toast.makeText(UpdateActivity.this, "필수 항목 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                return false;
            }

            int age = Integer.parseInt(strAge);
            int salary = Integer.parseInt(strSalary);

            // 데이터 수정
            employee.age = age;
            employee.salary = salary;

            // 데이터 보내주기(돌려주기)
            Intent intent = new Intent();
            intent.putExtra("index", index);
            intent.putExtra("employee", employee);
            setResult(200, intent);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}