package com.example.android.health.adapter;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.health.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Android on 25-01-2017.
 */
public class ListOfDoctorAdapter extends BaseAdapter implements Filterable{
    Context context;
    LayoutInflater inflater=null;

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public List<ListOfDoctorData> getListOfDoctors() {
        return listOfDoctors;
    }

    public void setListOfDoctors(List<ListOfDoctorData> listOfDoctors) {
        this.listOfDoctors = listOfDoctors;
    }

    List<ListOfDoctorData> listOfDoctors;

    public ListOfDoctorAdapter(Context context){
        this.context=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listOfDoctors=new ArrayList<ListOfDoctorData>();
        String texts[]=context.getResources().getStringArray(R.array.doctors_fields);
        int images[]={R.drawable.ic_my_location,R.drawable.cardiologist,R.drawable.dentist,R.drawable.dermi,R.drawable.dietitian
                ,R.drawable.earnose,R.drawable.gastro,R.drawable.generalphysician,R.drawable.gynecologist
                ,R.drawable.homeopathy,R.drawable.neurologist
                ,R.drawable.ophthalmologist,R.drawable.orthopedist,R.drawable.pediatrician,
                R.drawable.physiotherepist,
                R.drawable.phychiatrist,
                R.drawable.urologist,R.drawable.ayurveda};
        for(int i=0;i<images.length;i++){

            listOfDoctors.add(new ListOfDoctorData(images[i],texts[i]));
        }
    }

    @Override
    public int getCount() {
        return getListOfDoctors().size();
    }

    @Override
    public Object getItem(int i) {
        return getListOfDoctors().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=getInflater().inflate(R.layout.single_item_in_doctor_list,viewGroup,false);
        ImageView imageView= (ImageView) view.findViewById(R.id.doctorsImage);
        TextView textView= (TextView) view.findViewById(R.id.doctorsName);
        imageView.setImageResource(getListOfDoctors().get(i).getImage());
        textView.setText(getListOfDoctors().get(i).getText());
        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
