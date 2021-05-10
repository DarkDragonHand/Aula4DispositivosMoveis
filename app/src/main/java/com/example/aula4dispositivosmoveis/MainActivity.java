package com.example.aula4dispositivosmoveis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtNome, txtTelefone;
    private ImageView imageView;
    private final int SELECIONAR_CONTATO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        imageView = findViewById(R.id.imagem);

    }

    public void contatoClick(View view) {
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);

        startActivityForResult(intent, SELECIONAR_CONTATO);
    }

    public void webClick(View view) {
    }

    public void chamadaClick(View view) {
    }

    public void mapa1Click(View view) {
    }

    public void mapa2Click(View view) {
    }

    public void mapa3Click(View view) {
    }

    public void cameraClick(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECIONAR_CONTATO){
            if(resultCode == RESULT_OK){
                Uri uri = data.getData();

                Cursor c = getContentResolver().query(uri, null, null, null, null);
                c.moveToNext();

                int nameCol = c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
                int idCol = c.getColumnIndexOrThrow(ContactsContract.Contacts._ID);

                String nome = c.getString(nameCol);
                String id = c.getString(idCol);

                c.close();

                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                phones.moveToNext();

                String phoneNumber = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                phones.close();

            }else{ ///RESULT_CANCEL

            }
        }else{

        }
    }
}