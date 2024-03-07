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
import android.widget.Toast;

import com.qooke.employeeapp.model.Employee;

public class AddActivity extends AppCompatActivity {

    EditText editName;
    EditText editSalary;
    EditText editAge;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // 액션바 셋팅
        getSupportActionBar().setTitle("직원 추가");
        // 액션바에 뒤로 가기 버튼 띄우기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 액션바에 브이 체크 버튼(저장 버튼)

        editName = findViewById(R.id.editName);
        editSalary = findViewById(R.id.editSalary);
        editAge = findViewById(R.id.editAge);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String strSalary = editSalary.getText().toString().trim();
                String strAge = editAge.getText().toString().trim();

                if (name.isEmpty() || strSalary.isEmpty() || strAge.isEmpty()) {
                    Toast.makeText(AddActivity.this, "필수 항목입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int salary = Integer.parseInt(strSalary);
                int age = Integer.parseInt(strAge);

                Employee employee = new Employee(name, salary, age);

                Intent intent = new Intent();
                intent.putExtra("employee", employee);
                setResult(100, intent);

                finish();
            }
        });
    }

    // 액션바 뒤로가기 화살표 버튼을 누르면 동작하는 함수
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuSave) {
            String name = editName.getText().toString().trim();
            String strSalary = editSalary.getText().toString().trim();
            String strAge = editAge.getText().toString().trim();

            if (name.isEmpty() || strSalary.isEmpty() || strAge.isEmpty()) {
                Toast.makeText(AddActivity.this, "필수 항목입니다.", Toast.LENGTH_SHORT).show();
                return false;
            }

            int salary = Integer.parseInt(strSalary);
            int age = Integer.parseInt(strAge);

            Employee employee = new Employee(name, salary, age);

            Intent intent = new Intent();
            intent.putExtra("employee", employee);
            setResult(100, intent);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}