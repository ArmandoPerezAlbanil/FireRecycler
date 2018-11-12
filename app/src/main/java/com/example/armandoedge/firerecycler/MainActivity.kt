package com.example.armandoedge.firerecycler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_layout.view.*
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView:RecyclerView
    lateinit var mDatabase:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDatabase=FirebaseDatabase.getInstance().getReference("Users")
        mRecyclerView=findViewById(R.id.listview)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        logRecyclerView()

    }

    private fun logRecyclerView() {

        var FirebaseRecyclerAdapter =object :FirebaseRecyclerAdapter<Users,UsersViewHolder>(
            Users::class.java,
            R.layout.list_layout,
            UsersViewHolder::class.java,
            mDatabase
        ){
           override fun populateViewHolder(viewHolder: UsersViewHolder,model:Users,position: Int){
               viewHolder.mView.userName.setText(model.name)
               viewHolder.mView.userStatus.setText(model.status)
               Picasso.with(this@MainActivity).load(model.image).into(viewHolder.mView.userimageView)

           }
        }
        mRecyclerView.adapter=FirebaseRecyclerAdapter
    }

    class UsersViewHolder(var mView: View):RecyclerView.ViewHolder(mView){

    }
}