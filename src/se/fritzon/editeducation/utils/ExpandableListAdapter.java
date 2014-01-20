package se.fritzon.editeducation.utils;

import java.util.List;
import java.util.Map;
 
import se.fritzon.editeducation.R;
 
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    private Activity context;
    private Map<String, List<String>> educationCollections;
    private List<String> courses;
 
    public ExpandableListAdapter(Activity context, List<String> courses,
            Map<String, List<String>> educationCollections) {
        this.context = context;
        this.educationCollections = educationCollections;
        this.courses = courses;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return educationCollections.get(courses.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String course = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }
        
        TextView item = (TextView) convertView.findViewById(R.id.course);
        
        
        item.setText(course);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return educationCollections.get(courses.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return courses.get(groupPosition);
    }
 
    public int getGroupCount() {
        return courses.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.course);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}