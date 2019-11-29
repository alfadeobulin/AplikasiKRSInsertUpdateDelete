package com.example.aplikasikrs.Admin;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasikrs.Admin.Model.DefaultResult;
import com.example.aplikasikrs.Mahasiswa.HomeDosen;
import com.example.aplikasikrs.MainActivity;
import com.example.aplikasikrs.Network.DataDosenService;
import com.example.aplikasikrs.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateDosenActivity extends AppCompatActivity {
    EditText edtNama, edtNidn, edtAlamat, edtEmail, edtGelar;
    DataDosenService service;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dosen);
        this.setTitle("SI KRS - Hai Admin");

        Button btnDaftarKrs = (Button)findViewById(R.id.btnSimpanDosen);
        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateDosenActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(intent);
            }
        });
    }

    private void insertDosen(){
        retrofit2.Call<DefaultResult> call = service.insertDosen( "Dendy", "001", "Jogja",
                "dendy@dendy.com",  "S.kom", "dendy.jpg", "1");
        call.enqueue(new Callback<DefaultResult>(){
            @Override
            public void onResponse(retrofit2.Call<DefaultResult> call, Response<DefaultResult> response){
                System.out.println(response.body().getStatus());
            }
            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t){
                System.out.println("message : " +t.getMessage());
                Toast.makeText(CreateDosenActivity.this,
                        "Something went wrong... please try later!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
