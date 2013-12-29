package com.afollestad.kitkattransprencydemo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.afollestad.kitkattransprencydemo.R;

/**
 * @author Aidan Follestad (afollestad)
 */
public class DemoFragment extends Fragment {

    private int mStartAt;
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStartAt = getArguments().getInt("value");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fill the content list view with 100 list items
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_main);
        for (int i = mStartAt; i <= 100; i++)
            adapter.add("Trim to #" + i);

        ListView list = (ListView) view.findViewById(android.R.id.list);
        //Add the padding to the listview.
        list.setPadding(0, getActionBarHeight()+getStatusBarHeight(), 0,0);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((FragmentDemoActivity) getActivity()).setFragment(new DemoFragment(), mStartAt + position);
            }
        });
    }
   
    public int getActionBarHeight(){
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
         // Checks if the os version has actionbar in it or not
           if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
              if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
           }
           return actionBarHeight;
    }
    
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
